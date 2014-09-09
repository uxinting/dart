package xinting.effect;

/**
 * 
* @ClassName: ThreeVector
* @Description: 描述一个三维向量
* @author xinting
* @date 2014年8月19日 下午9:38:20
*
 */
public class ThreeVector {
	
	private double[] vector = null;
	
	public ThreeVector( double x, double y, double z ) {
		vector = new double[3];
		vector[0] = x;
		vector[1] = y;
		vector[2] = z;
	}
	
	public double x() {
		return vector[0];
	}
	
	public void x( double dx ) {
		vector[0] = dx;
	}
	
	public double y() {
		return vector[1];
	}
	
	public void y( double dy ) {
		vector[1] = dy;
	}
	
	public double z() {
		return vector[2];
	}
	
	public void z( double dz ) {
		vector[2] = dz;
	}
	
	public ThreeVector add( ThreeVector tv ) {
		return new ThreeVector( vector[0]+tv.x(), vector[1]+tv.y(), vector[2]+tv.z() );
	}
	
	public ThreeVector minus( ThreeVector tv ) {
		return new ThreeVector( vector[0]-tv.x(), vector[1] - tv.y(), vector[2] - tv.z() );
	}
	
	public double multi( ThreeVector tv ) {
		return vector[0]*tv.x() + vector[1]*tv.y() + vector[2]*tv.z();
	}
	
	public ThreeVector multi( double lambda ) {
		return new ThreeVector( vector[0]*lambda, vector[1]*lambda, vector[2]*lambda );
	}
	
	public double model() {
		return Math.sqrt( Math.pow( vector[1], 2 ) + Math.pow( vector[0], 2 ) + Math.pow( vector[2], 2 ) );
	}
	
	public ThreeVector rotate( double anger ) {
		double x = Math.cos( anger ) * vector[0] + Math.sin( anger ) * vector[1];
		double y = Math.cos( anger ) * vector[1] - Math.sin( anger ) * vector[0];
		return new ThreeVector( x, y, vector[2] );
	}
	
	public ThreeVector scale( double ratio ) {
		return new ThreeVector( vector[0] * ratio, vector[1] * ratio, vector[2] * ratio );
	}
	
	/**
	 * 
	* @Title: normal 
	* @Description: 求法向量
	* @param @return
	* @return ThreeVector
	* @throws
	 */
	public ThreeVector normal() {
		double y2 = 1;
		double x2 = - vector[1] / vector[0];
		
		if ( x2 < 0 ) {
			x2 = -x2;
			y2 = -y2;
		}
		
		return new ThreeVector( x2, y2, vector[2] );
	}
	
	@Override
	public String toString() {
		return String.format( "( %f, %f, %f )", vector[0], vector[1], vector[2] );
	}
}
