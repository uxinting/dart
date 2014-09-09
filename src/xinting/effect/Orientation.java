package xinting.effect;

public class Orientation extends ThreeVector {
	
	public Orientation( ThreeVector tv ) {
		super( tv.x(), tv.y(), tv.z() );
	}

	public Orientation( double x, double y, double z ) {
		super( x, y, z );
	}

	@Override
	public Orientation add( ThreeVector tv ) {
		return new Orientation( super.add( tv ) );
	}

	@Override
	public Orientation minus( ThreeVector tv ) {
		return new Orientation( super.minus( tv ) );
	}

	@Override
	public Orientation multi( double lambda ) {
		return new Orientation( super.multi( lambda ) );
	}

	@Override
	public Orientation scale( double ratio ) {
		return new Orientation( super.scale( ratio ) );
	}

}
