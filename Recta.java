package act2;
import java.text.DecimalFormat;

/**
 * @author frasco2001
 *
 */
public class Recta {
	double coeficienteA, coeficienteB, coeficienteC;

	/**
	 * 
	 * @param v
	 * @param w
	 */
	public Recta(Punto v, Punto w) {
		if (!v.equals(w)) {
			if (v.getX() == w.getX()) {
				this.coeficienteA = 1;
				this.coeficienteB = 0;
				this.coeficienteC = -v.getX();
			} else {
				if (v.getY() == w.getY()) {
					this.coeficienteA = 0;
					this.coeficienteB = 1;
					this.coeficienteC = -v.getY();
				} else {
					this.coeficienteA = (w.y - v.y) / (w.x - v.x);
					this.coeficienteB = -1;
					this.coeficienteC = this.coeficienteA * (-v.x) + v.y;
				}
			}
		}
	}

	/**
	 * 
	 * @param pendiente
	 * @param ord0
	 */
	public Recta(double pendiente, double ord0) {
		this.coeficienteA = pendiente;
		this.coeficienteB = -1;
		this.coeficienteC = ord0;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	public Recta(double a, double b, double c) {
		this.coeficienteA = a;
		this.coeficienteB = b;
		this.coeficienteC = c;
	}

	/**
	 * 
	 * @param pendiente
	 * @param v
	 */
	public Recta(double pendiente, Punto v) {
		this.coeficienteA = pendiente;
		this.coeficienteB = -1;
		this.coeficienteC = -this.coeficienteA * v.x + v.y;
	}

	/**
	 * 
	 * @param a
	 */
	public Recta(Punto a, boolean eje) {
		if (eje) {
			this.coeficienteA = 1;
			this.coeficienteB = 0;
			this.coeficienteC = -a.y;
		}
		else {
			this.coeficienteA = 0;
			this.coeficienteB = 1;
			this.coeficienteC = -a.x;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String ecuacionRecta() {
		String salida = "La ecuacion de la Recta es: ";
		if (this.coeficienteA != 0) {
			if (this.coeficienteA == 1) {
				salida += "X";
			} else {
				if (this.coeficienteA == -1) {
					salida += "-X";
				} else {
					salida += new DecimalFormat("#.##").format(this.coeficienteA) + "*X";
				}
			}
		}
		if (this.coeficienteB != 0) {
			if (this.coeficienteB == 1) {
				if (this.coeficienteA != 0) {
					salida += "+";
				}
			} else {
				if (this.coeficienteB == -1) {
					salida += "-";
				} else {
					salida += "+" + new DecimalFormat("#.##").format(this.coeficienteB) + "*";
				}
			}
			salida += "Y";
		}
		if (this.coeficienteC != 0) {
			if (this.coeficienteC > 0) {
				salida += "+";
			}
			salida += new DecimalFormat("#.##").format(this.coeficienteC);
		}
		salida += "=0";
		System.out.println(salida);
		return salida;
	}

	/**
	 * 
	 * @param primera
	 * @param segunda
	 * @return
	 */
	public static boolean sonParalelas(Recta primera, Recta segunda) {
		if (primera.getCoeficienteA() / primera.getCoeficienteB() != segunda.getCoeficienteA()
				/ segunda.getCoeficienteB()) {
			return false;
		}
		return true;
	}

	public static Punto puntoCorte(Recta primera, Recta segunda) {
		if (!sonParalelas(primera, segunda)) {
			Punto puntoCorte = null;
			double x = 0;
			double y = 0;
			double parteZ1 = 0;
			double parteZ2 = 0;
			double componenteC = 0;
			double componenteZZ = 0;
			if (primera.getCoeficienteB() != 0 && segunda.coeficienteB != 0) {
				parteZ1 = -primera.getCoeficienteC() / primera.getCoeficienteB();
				parteZ2 = -primera.getCoeficienteA() / primera.getCoeficienteB();
				componenteC = -segunda.getCoeficienteC()
						+ (segunda.getCoeficienteB() * primera.getCoeficienteA() / primera.getCoeficienteB());
				componenteZZ = segunda.getCoeficienteA()
						- (segunda.getCoeficienteB() * primera.getCoeficienteA() / primera.getCoeficienteB());
				if (componenteZZ != 0) {
					x = componenteC / componenteZZ;
				}
				y = parteZ1 - parteZ2 * x;
			} else {
				if (primera.getCoeficienteA() != 0 && segunda.getCoeficienteA() != 0) {
					parteZ1 = -primera.getCoeficienteC() / primera.getCoeficienteA();
					parteZ2 = -primera.getCoeficienteB() / primera.getCoeficienteA();
					componenteC = -segunda.getCoeficienteC()
							+ (segunda.getCoeficienteA() * primera.getCoeficienteC() / primera.getCoeficienteA());
					componenteZZ = segunda.getCoeficienteB()
							- (segunda.getCoeficienteA() * primera.getCoeficienteB() / primera.getCoeficienteA());
					if (componenteZZ != 0) {
						y = componenteC / componenteZZ;
					}
					x = parteZ1 - parteZ2 * y;
				} else {
					if ((primera.getCoeficienteA() == 0 && primera.getCoeficienteB() != 0)
							&& (segunda.getCoeficienteA() != 0 && segunda.getCoeficienteB() == 0)) {
						x = -segunda.getCoeficienteC();
						y = -primera.getCoeficienteC();
					} else {
						x = -primera.getCoeficienteC();
						y = -segunda.getCoeficienteC();
					}
				}
			}
			puntoCorte = new Punto(x, y);
			return puntoCorte;
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public double getCoeficienteA() {
		return coeficienteA;
	}

	/**
	 * 
	 * @param coeficienteA
	 */
	public void setCoeficienteA(double coeficienteA) {
		this.coeficienteA = coeficienteA;
	}

	/**
	 * 
	 * @return
	 */
	public double getCoeficienteB() {
		return coeficienteB;
	}

	/**
	 * 
	 * @param coeficienteB
	 */
	public void setCoeficienteB(double coeficienteB) {
		this.coeficienteB = coeficienteB;
	}

	/**
	 * 
	 * @return
	 */
	public double getCoeficienteC() {
		return coeficienteC;
	}

	/**
	 * 
	 * @param coeficienteC
	 */
	public void setCoeficienteC(double coeficienteC) {
		this.coeficienteC = coeficienteC;
	}
}
