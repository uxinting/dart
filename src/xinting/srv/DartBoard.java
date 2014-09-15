package xinting.srv;

import xinting.effect.ThreeVector;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class DartBoard implements Entity {
	
	private ThreeVector normal = null;
	private ThreeVector position = null;
	
	private double radius = 0.453 / 2;
	private double thick  = 0.01;
	
	private double ratio = 1.0;
	
	private RectF rect = null;
	private Paint paint = null;
	
	public DartBoard() {
		super();
		
		paint = new Paint();
		paint.setColor( Color.BLACK );
		
		rect = new RectF( 0, 0, 0, 0 );
	}

	@Override
	public void draw( Canvas canvas ) {
		if ( normal == null || position == null ) return;
		
		double x = position.x() * ratio;
		double y = position.y() * ratio + radius * ratio;
		
		rect.left = (float) Coordinate.toScreenX( x );
		rect.top  = (float) Coordinate.toScreenY( y );
		rect.right = (float) Coordinate.toScreenX( x + thick * ratio );
		rect.bottom = (float) Coordinate.toScreenY( y - 2 * radius * ratio );
		
		canvas.drawRect( rect, paint );
	}
	
	public boolean touch( Dart dart ) {
		return dart.getPos().x() >= position.x();
	}

	public ThreeVector getNormal() {
		return normal;
	}

	public void setNormal( ThreeVector normal ) {
		this.normal = normal;
	}

	public ThreeVector getPosition() {
		return position;
	}

	public void setPosition( ThreeVector position ) {
		this.position = position;
	}

	public double getThick() {
		return thick;
	}

	public void setThick( double thick ) {
		this.thick = thick;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio( double ratio ) {
		this.ratio = ratio;
	}

}
