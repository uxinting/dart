package xinting.view;

import java.util.HashSet;
import java.util.Set;

import xinting.effect.Orientation;
import xinting.effect.Position;
import xinting.srv.Coordinate;
import xinting.srv.Dart;
import xinting.srv.Observer;
import xinting.srv.Player;
import xinting.srv.Subject;
import xinting.srv.Target;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class YardView extends ImageView implements Subject {
	
	private Set<Observer> observers;
	
	private Set<Player> players;
	private Player curPlayer;
	private Dart dart;
	private Target target;
	
	private boolean begin = false;

	public YardView( Context context ) {
		super( context );
	}

	public YardView( Context context, AttributeSet attrs ) {
		super( context, attrs );
		
		observers = new HashSet<Observer>();
		players = new HashSet<Player>();
	}

	public YardView( Context context, AttributeSet attrs, int defStyle ) {
		super( context, attrs, defStyle );
	}

	@Override
	protected void onDraw( Canvas canvas ) {
		super.onDraw( canvas );
		
		drawBackgroud( canvas );
		drawPlayers( canvas );
		drawDart( canvas );
		drawTarget( canvas );
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent( MotionEvent event ) {

		switch ( event.getAction() ) {
		case MotionEvent.ACTION_DOWN:
			if ( curPlayer.isScreenPointInHand( new Position( event.getX(), event.getY(), 0 ) )) {
				begin = true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if ( begin ) {
				curPlayer.setHandSrn( new Position( event.getX(), event.getY(), 0 ) );
				dart.setPos( new Position( event.getX(), event.getY(), 0 ) );
				invalidate();
			}
			break;
			
		case MotionEvent.ACTION_UP:
			begin = false;
			break;

		default:
			break;
		}
		return true;
		//return super.onTouchEvent( event );
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
		
		int w = MeasureSpec.getSize(widthMeasureSpec);
		int h = MeasureSpec.getSize(heightMeasureSpec);
		
		Coordinate.setOrigin( new Position( 0, h, 0 ) );
		
		super.onMeasure( widthMeasureSpec, heightMeasureSpec );
	}

	private void drawTarget( Canvas canvas ) {
		target.draw( canvas );
	}

	private void drawDart( Canvas canvas ) {
		double ratio = getWidth() / 1.74;
		
		Orientation arm = curPlayer.getOriArm();

		dart.setOri( new Orientation( arm.normal() ) );
		dart.setRatio( ratio );
		dart.draw( canvas );
	}

	private void drawPlayers( Canvas canvas ) {
		double ratio = getWidth() / 1.74;
		for ( Player p : players ) {
			p.setRatio( ratio );
			p.draw( canvas );
		}
	}

	private void drawBackgroud( Canvas canvas ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Attach( Observer observer ) {
		observers.add( observer );
	}

	@Override
	public void Detach( Observer observer ) {
		observers.remove( observer );
	}

	@Override
	public void Notify() {
		for ( Observer ob : observers ) {
			ob.update( this );
		}
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void addPlayer( Player player ) {
		players.add( player );
		curPlayer = player;
	}

	public Dart getDart() {
		return dart;
	}

	public void setDart( Dart dart ) {
		this.dart = dart;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget( Target target ) {
		this.target = target;
	}

}
