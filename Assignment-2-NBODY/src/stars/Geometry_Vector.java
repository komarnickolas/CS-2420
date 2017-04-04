
/**
 * 
 */
package stars;

import java.awt.geom.Point2D;

/**
 * @author germain
 *
 */
public class Geometry_Vector extends Point2D.Double
{

  /**
   *
   * Constructor
   *
   * @param xx - the X
   * @param yy - and Y values
   */
  public  Geometry_Vector(double xx, double yy)
  {
      super();
      this.x = xx;
      this.y = yy;
  }

  /**
   *
   * A "Copy" constructor.  Create ourself based on the given vector
   * @param the_copy 
   */
   public  Geometry_Vector(Geometry_Vector the_copy)
   {
     super();
     this.x = the_copy.x;
     this.y = the_copy.y;
   }

  /**
   * Add the components of the given vector to me.
   *
   * @param vector
   */
  public void add_to_me(Geometry_Vector vector)
  {
    this.x += vector.x;
    this.y += vector.y;
  }

  /**
   * Subtract the components of the given vector from me.
   * @param vector
   */
  public void subtract_from_me(Geometry_Vector vector)
  {
	  this.x -= vector.x;
	  this.y -= vector.y;
  }

  /**
   * Divide my components by the scalar
   * @param scalar
   */
  public void divide_by( double scalar )
  {
	  this.x /= scalar;
	  this.y /= scalar;
  }

  /**
   * Multiply my components by the scalar
   * @param scalar
   */
  public  void   multiply_me_by(double scalar)
  {
	  this.x *= scalar;
	  this.y *= scalar;
  }

  /**
   * @return my magnitude (the distance from the origin to my X,Y)
   * Think Pythagoras
   */
  public  double  magnitude()
  {
    double xSquared = x*x;
    double ySquared = y*y;
    double magnitude = Math.sqrt(xSquared+ySquared);
    return magnitude;
  }

  /**
   * @return an informative (but concise) description of this object
   */
  public  String toString()
  {
	  return "X: " + x+ " Y: "+ y + " Magnitude: " + magnitude();
  }

  /**
   * Take this vector and turn it into a vector of length 1.  This is done by
   * dividing each component (i.e., x,y)  by the magnitude.
   */
  public   void   normalize()
  {
	  this.x /= magnitude();
	  this.y /= magnitude();
  }
  
}
