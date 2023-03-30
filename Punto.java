package act2;
/**
 * @author frasco2001
 *
 */
public class Punto {

	double x, y;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Punto(double x, double y) {
		if (x==-0.0){
			x=0.0;
		}
		if (y==-0.0){
			y=0.0;
		}
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * 
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * 
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Punto [x=" + x + ", y=" + y + "]";
	}
	
	@Override
	public boolean equals(Object o){
		Punto p = (Punto)o;
		if(p.getX() == this.x && p.getY() == this.y) return true;
		return false;
	}
}
