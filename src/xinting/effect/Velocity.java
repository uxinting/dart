package xinting.effect;

public class Velocity extends ThreeVector {
	
	public Velocity( ThreeVector tv ) {
		super( tv.x(), tv.y(), tv.z() );
	}

	public Velocity( double x, double y, double z ) {
		super( x, y, z );
	}

	@Override
	public Velocity add( ThreeVector tv ) {
		return new Velocity( super.add( tv ) );
	}

	@Override
	public Velocity minus( ThreeVector tv ) {
		return new Velocity( super.minus( tv ) );
	}

	@Override
	public Velocity multi( double lambda ) {
		return new Velocity( super.multi( lambda ) );
	}

}
