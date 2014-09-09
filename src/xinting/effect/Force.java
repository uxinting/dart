package xinting.effect;

/**
 * 
* @ClassName: Force
* @Description: 力，有施力和受力的实体
* @author xinting
* @date 2014年8月19日 下午9:22:41
*
 */
public class Force extends ThreeVector {
	
	public Force( ThreeVector tv ) {
		super( tv.x(), tv.y(), tv.z() );
	}

	public Force( double x, double y, double z ) {
		super( x, y, z );
	}

	@Override
	public Force add( ThreeVector tv ) {
		return new Force( super.add( tv ) );
	}

	@Override
	public Force minus( ThreeVector tv ) {
		return new Force( super.minus( tv ) );
	}

	@Override
	public Force multi( double lambda ) {
		return new Force( super.multi( lambda ) );
	}
	
}
