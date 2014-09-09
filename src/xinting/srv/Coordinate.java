package xinting.srv;

import xinting.effect.Orientation;
import xinting.effect.Position;

public class Coordinate {
	
	private static Orientation logicX = null;
	private static Orientation logicY = null;
	private static Orientation logicZ = null;
	private static Position    origin = null;
	
	private static Orientation screenX = null;
	private static Orientation screenY = null;
	private static Orientation screenZ = null;

	public static Orientation getLogicX() {
		return logicX;
	}

	public static void setLogicX( Orientation logicX ) {
		Coordinate.logicX = logicX;
	}

	public static Orientation getLogicY() {
		return logicY;
	}

	public static void setLogicY( Orientation logicY ) {
		Coordinate.logicY = logicY;
	}

	public static Orientation getLogicZ() {
		return logicZ;
	}

	public static void setLogicZ( Orientation logicZ ) {
		Coordinate.logicZ = logicZ;
	}

	public static Position getOrigin() {
		return origin;
	}

	public static void setOrigin( Position origin ) {
		Coordinate.origin = origin;
	}

	public static Orientation getScreenX() {
		return screenX;
	}

	public static void setScreenX( Orientation screenX ) {
		Coordinate.screenX = screenX;
	}

	public static Orientation getScreenY() {
		return screenY;
	}

	public static void setScreenY( Orientation screenY ) {
		Coordinate.screenY = screenY;
	}

	public static Orientation getScreenZ() {
		return screenZ;
	}

	public static void setScreenZ( Orientation screenZ ) {
		Coordinate.screenZ = screenZ;
	}

	public static Position toScreen( Position pos ) {
		Position spos = new Position( pos );
		spos.y( toScreenY( pos.y() ) );
		return spos;
	}
	
	public static double toScreenX( double x ) {
		return x;
	}
	
	public static double toScreenY( double y ) {
		return y*-1 + origin.y();
	}
	
	public static double toScreenZ( double z ) {
		return z;
	}
	
	public static double toLogicX( double x ) {
		return x;
	}
	
	public static double toLogicY( double y ) {
		return origin.y() - y;
	}
	
	public static double toLogicZ( double z ) {
		return z;
	}
}
