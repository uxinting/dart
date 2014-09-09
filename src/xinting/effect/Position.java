package xinting.effect;

public class Position extends ThreeVector {
	
	public Position( ThreeVector tv ) {
		super( tv.x(), tv.y(), tv.z() );
	}

	public Position( double x, double y, double z ) {
		super( x, y, z );
	}

	@Override
	public Position add( ThreeVector tv ) {
		return new Position( super.add( tv ) );
	}

	@Override
	public Position minus( ThreeVector tv ) {
		return new Position( super.minus( tv ) );
	}

	@Override
	public Position multi( double lambda ) {
		return new Position( super.multi( lambda ) );
	}

}
