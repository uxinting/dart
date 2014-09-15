package xinting.srv;

import xinting.effect.Orientation;
import xinting.effect.Position;
import xinting.effect.ThreeVector;
import xinting.effect.Velocity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Dart implements Entity {
	
	private double length = 0.2;
	private int color = Color.BLUE;
	private ThreeVector pos = null;
	private ThreeVector vel = null;
	private Orientation ori = null;
	
	private double ratio = 1.0;
	
	private Paint paint = null;
	
	private double mass = 2;
	
	public Dart() {
		super();
		
		paint = new Paint();
		paint.setColor( color );
		paint.setStrokeWidth( 3 );
	}

	@Override
	public void draw( Canvas canvas ) {
		if ( pos == null ) return;
		
		float x1 = (float) ( pos.x() - ori.x() );
		float y1 = (float) ( pos.y() + ori.y() );
		float x2 = (float) ( pos.x() + ori.x() );
		float y2 = (float) ( pos.y() - ori.y() );
		
		canvas.drawLine( x1, y1, x2, y2, paint );
	}
	
	public boolean move( long timeinmils ) {
		/*ThreeVector force = new ThreeVector( 0, -mass, 0 );
		ThreeVector v = vel.add( force.multi( timeinmils ) );
		
		ThreeVector tv = getVel().multi( timeinmils ).add( force.multi( Math.pow( timeinmils, 2 ) / mass / 2 ) );*/
		ThreeVector tv = getVel().multi( timeinmils );
		setPos( getPos().add( tv ) );
		//setVel( v );
		return true;
	}

	public int getColor() {
		return color;
	}

	public void setColor( int color ) {
		this.color = color;
	}

	public ThreeVector getPos() {
		return pos;
	}

	public void setPos( ThreeVector pos ) {
		this.pos = pos;
	}

	public ThreeVector getVel() {
		return vel;
	}

	public void setVel( ThreeVector vel ) {
		this.vel = vel;
	}

	public Orientation getOri() {
		return ori;
	}

	public void setOri( Orientation ori ) {
		this.ori = ori.scale( length * ratio * 0.5 / ori.model() );
	}

	public double getLength() {
		return length;
	}

	public void setLength( double length ) {
		this.length = length;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio( double ratio ) {
		this.ratio = ratio;
	}

}
