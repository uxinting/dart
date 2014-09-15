package xinting.view;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HandshakeCompletedListener;

import xinting.effect.Orientation;
import xinting.effect.Position;
import xinting.effect.ThreeVector;
import xinting.effect.Velocity;
import xinting.srv.Coordinate;
import xinting.srv.Dart;
import xinting.srv.Observer;
import xinting.srv.Player;
import xinting.srv.Subject;
import xinting.srv.DartBoard;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class YardView extends ImageView implements Subject {
	
	private Set<Observer> observers;
	
	private Set<Player> players;
	private Player curPlayer;
	private Dart dart;
	private DartBoard board;
	
	private boolean begin = false;
	private PointF old = null;
	private long  oldt = 0;
	
	private Handler handler = null;
	private Timer timer = null;

	public YardView( Context context ) {
		super( context );
	}

	public YardView( Context context, AttributeSet attrs ) {
		super( context, attrs );
		
		observers = new HashSet<Observer>();
		players = new HashSet<Player>();
		
		old = new PointF( 0, 0 );
		
		handler = new MsgHandler();
		timer = new Timer();
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
		drawBoard( canvas );
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent( MotionEvent event ) {

		switch ( event.getAction() ) {
		case MotionEvent.ACTION_DOWN:
			if ( curPlayer.isScreenPointInHand( new Position( event.getX(), event.getY(), 0 ) ) ) {
				begin = true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if ( begin ) {
				old.x = event.getX();
				old.y = event.getY();
				oldt  = System.currentTimeMillis();
				
				curPlayer.setHandSrn( new Position( event.getX(), event.getY(), 0 ) );
				dart.setPos( new Position( event.getX(), event.getY(), 0 ) );
				invalidate();
			}
			break;
			
		case MotionEvent.ACTION_UP:
			begin = false;
			
			/*double v = Math.sqrt( Math.pow( old.y - event.getY(), 2 ) + Math.pow( old.x - event.getY(), 2 ) ) / ( System.currentTimeMillis() - oldt );
			Orientation ori = dart.getOri();
			dart.setVel( new Velocity( ori.scale( v / ori.model() ) ) );*/
			dart.setVel( new ThreeVector( 5, 0, 0 ) );
			//if ( !dartfly.isAlive() ) dartfly.start();
			timer.schedule( new DartFly(), 0, 10 );
			break;

		default:
			break;
		}
		return true;
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
		
		int w = MeasureSpec.getSize(widthMeasureSpec);
		int h = MeasureSpec.getSize(heightMeasureSpec);
		
		Coordinate.setOrigin( new Position( 0, h, 0 ) );
		
		super.onMeasure( widthMeasureSpec, heightMeasureSpec );
	}

	private void drawBoard( Canvas canvas ) {
		double ratio = getWidth() / 1.74;
		
		board.setRatio( ratio );
		board.draw( canvas );
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

	public DartBoard getTarget() {
		return board;
	}

	public void setTarget( DartBoard target ) {
		this.board = target;
	}
	
	public Handler getHandler() {
		return handler;
	}
	
	private class MsgHandler extends Handler {
		
		@Override
		public void handleMessage( Message msg ) {
			super.handleMessage( msg );
			
			invalidate();
		}
		
	}
	
	private class DartFly extends TimerTask {
		
		@Override
		public void run() {

				dart.move( 30 );
				if ( board.touch( dart ) ) return;
				
				handler.sendEmptyMessage( 0 );
			}
		}
		
	}
