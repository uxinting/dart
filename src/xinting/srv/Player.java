package xinting.srv;

import xinting.effect.Orientation;
import xinting.effect.Position;

public abstract class Player implements Observer, Entity {
	
	protected double high = 1.73;
	protected double arm = 0.53;
	
	protected double ratio = 1.0;
	
	protected Position stand = null;
	protected Position hand  = null;
	
	protected double   handSize = 0.05;
	protected Orientation oriArm = null;

	public boolean isScreenPointInHand( Position pos ) {
		double x = Coordinate.toScreenX( this.hand.x() * ratio );
		double y = Coordinate.toScreenY( this.hand.y() * ratio );
		double z = Coordinate.toScreenZ( this.hand.z() * ratio );
		
		Position hand = new Position( x, y, z );
		double handSizePx = handSize * ratio;
		return pos.minus( hand ).model() <= handSizePx;
	}

	public Orientation getOriArm() {
		return oriArm;
	}

	public void setOriArm( Orientation oriArm ) {
		this.oriArm = oriArm;
	}

	public Position getHandSrn() {
		float cx = (float) Coordinate.toScreenX( hand.x() * ratio );
		float cy = (float) Coordinate.toScreenY( hand.y() * ratio );
		return new Position( cx, cy, hand.z() );
	}

	public void setHandSrn( Position handSrn ) {
		double x = Coordinate.toLogicX( handSrn.x() );
		double y = Coordinate.toLogicY( handSrn.y() );
		double z = Coordinate.toLogicZ( handSrn.z() );
		
		setHand( new Position( x / ratio, y / ratio, z / ratio ) );
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio( double ratio ) {
		this.ratio = ratio;
	}

	public Position getStand() {
		return stand;
	}
	
	public void setStand( Position stand ) {
		this.stand = stand;
	}
	
	public Position getHand() {
		return hand;
	}
	
	public void setHand( Position hand ) {
		this.hand = hand;
	}
	
	public double getHigh() {
		return high;
	}
	
	public void setHigh( double high ) {
		this.high = high;
	}
	
	public double getArm() {
		return arm;
	}
	
	public void setArm( double arm ) {
		this.arm = arm;
	}
	
}
