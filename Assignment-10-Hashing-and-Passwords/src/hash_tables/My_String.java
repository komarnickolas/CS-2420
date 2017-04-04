/**
 * 
 */
package hash_tables;

/**
 * @author H. James de St. Germain
 * @date   Spring 2007
 * 
 * a dummy wrapper for a string to allow us experiment with hash functions
 */
public class My_String implements Comparable<My_String> 
{
  public String value;
  
  /**
   * @param name
   */
  public My_String(String name)
  {
    this.value = name;
  }

  /**
   * FIXME: you should come up with your own hash function for strings here.  
   * Do not use Javas. Use your own intuition and ingenuity.
   * 
   * @return the hash code for the "value" field of the My_String
   */
//  public int hashCode()
//  {
//      return this.value.length();
//  }
  @Override
  public int hashCode()
  {
    int intLength = value.length() / 4;
    long sum = 0;
    for (int j = 0; j < intLength; j++) {
      char c[] = value.substring(j * 4, (j * 4) + 4).toCharArray();
      long mult = 1;
      for (int k = 0; k < c.length; k++) {
        sum += c[k] * mult;
        mult *= 256;
      }
    }

    char c[] = value.substring(intLength * 4).toCharArray();
    long mult = 1;
    for (int k = 0; k < c.length; k++) {
      sum += c[k] * mult;
      mult *= 256;
    }

    return (int) Math.abs(sum);
  }
  
  /**
   * string value equality
   */
  @Override
  public boolean equals( Object other )
  {
    if ( other instanceof My_String)
      {
        return this.value.equals(((My_String)other).value);
      }
    return false;
  }

  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(My_String o)
  {
    return this.value.compareTo(o.value);
  }
  
  public String toString()
  {
    return this.value;
  }
  
}
