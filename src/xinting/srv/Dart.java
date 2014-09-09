package xinting.srv;

import xinting.effect.Orientation;
import xinting.effect.Position;
import xinting.effect.Velocity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Dart implements Entity {
	
	private double length = 0.2;
	private int color = Color.BLUE;
	private Position pos = null;
	private Velocity vel = null;
	private Orientation ori = null;
	
	private double ratio = 1.0;
	
	private Paint paint = null;
	
	public Dart() {
		super();
		
		paint = new Paint();
		paint.setColor( color );
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

	public int getColor() {
		return color;
	}

	public void setColor( int color ) {
		this.color = color;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos( Position pos ) {
		this.pos = pos;
	}

	public Velocity getVel() {
		return vel;
	}

	public void setVel( Velocity vel ) {
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
