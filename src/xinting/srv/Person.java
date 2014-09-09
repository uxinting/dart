package xinting.srv;

import xinting.effect.Orientation;
import xinting.effect.Position;
import xinting.effect.ThreeVector;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Person extends Player {
	
	final private double head = 0.1272;
	final private double body = 0.3410;
	final private double leg  = 0.5318;
	final private double leg1 = 0.4891;
	final private double leg2 = 0.5109;
	
	final private double arm1 = 0.5;
	final private double arm2 = 0.5;
	
	private Paint paint = null;

	public Person() {
		super();
		
		paint = new Paint();
		paint.setColor( Color.BLACK );
		
		oriArm = new Orientation( 0, 0, 0 );
	}

	@Override
	public void draw( Canvas canvas ) {
		
		if ( stand == null ) return;
		if ( hand == null ) {
			hand = new Position( stand );
			
			double y = high * (1-head) - arm;
			hand.y( y );
		}
		
		drawHead( canvas );
		drawBody( canvas );
		drawLeg( canvas );
		drawArm( canvas );
		drawHand( canvas );
	}

	private void drawHand( Canvas canvas ) {
		float cx = (float) Coordinate.toScreenX( hand.x() * ratio );
		float cy = (float) Coordinate.toScreenY( hand.y() * ratio );
		
		paint.setStrokeWidth( 1 );
		canvas.drawCircle( cx, cy, (float) (handSize * ratio), paint );
	}

	private void drawArm( Canvas canvas ) {
		double arm1 = this.arm * ratio * this.arm1;
		double arm2 = this.arm * ratio * this.arm2;
		
		double shouldery = stand.y() * ratio + high * ratio * (1-head);
		double shoulderx = stand.x() * ratio;
		double handx = hand.x() * ratio;
		double handy = hand.y() * ratio;
		
		double ab = Math.sqrt( Math.pow( handx-shoulderx, 2 ) + Math.pow( handy-shouldery, 2 ) );
		double cosarm = ( ab*ab + arm1*arm1 - arm2*arm2 ) / ( 2*ab*arm1 );
		double anger = Math.acos( cosarm );
		ThreeVector tv = new ThreeVector( handx-shoulderx, handy-shouldery, 0 ).rotate( anger ).scale( arm1 / ab );

		float x1 = (float) Coordinate.toScreenX( shoulderx );
		float y1 = (float) Coordinate.toScreenY( shouldery );
		float x2 = (float) Coordinate.toScreenX( handx );
		float y2 = (float) Coordinate.toScreenY( handy );
		float x = (float) Coordinate.toScreenX( tv.x() + shoulderx );
		float y = (float) Coordinate.toScreenY( tv.y() + shouldery );
		
		oriArm.x( handx-tv.x()-shoulderx );
		oriArm.y( handy-tv.y()-shouldery );
		
		canvas.drawLine( x1, y1, x, y, paint );
		canvas.drawLine( x, y, x2, y2, paint );
	}

	private void drawLeg( Canvas canvas ) {
		double leg = high * ratio * this.leg;
		double leg2 = leg * this.leg2;
		
		float x = (float) Coordinate.toScreenX( stand.x() * ratio );
		float y1 = (float) Coordinate.toScreenY( stand.y() * ratio + leg );
		float y2 = (float) Coordinate.toScreenY( stand.y() * ratio + leg2 );
		float y3 = (float) Coordinate.toScreenY( stand.y() * ratio );
		
		canvas.drawLine( x, y1+2, x, y2, paint );
		canvas.drawLine( x, y2+2, x, y3, paint );
	}

	private void drawBody( Canvas canvas ) {
		double body = high * ratio * this.body;
		double bodyy1 = stand.y() * ratio + high * ratio * (1-head);
		
		float x = (float) Coordinate.toScreenX( stand.x() * ratio );
		float y1 = (float) Coordinate.toScreenY( bodyy1 );
		float y2 = (float) Coordinate.toScreenY( bodyy1-body );
		
		paint.setStrokeWidth( 2 );
		canvas.drawLine( x, y1+2, x, y2, paint );
	}

	private void drawHead( Canvas canvas ) {
		double head = high * ratio * this.head;
		double headcy = stand.y() * ratio + high * ratio - head / 2;
		
		float cx = (float) Coordinate.toScreenX( stand.x() * ratio );
		float cy = (float) Coordinate.toScreenY( headcy );
		
		paint.setStrokeWidth( 1 );
		canvas.drawCircle( cx, cy, (float) ( head / 2 ), paint );
	}

	@Override
	public void setHand( Position hand ) {
		Position shoulder = new Position( stand.x(), high * (1-head), 0 );
		double distance = hand.minus( shoulder ).model();
		if ( distance > arm ) return;

		super.setHand( hand );
	}

	@Override
	public void update( Subject subject ) {
		// TODO Auto-generated method stub
		
	}

}
