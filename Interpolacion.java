package examen2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

import javax.xml.bind.ParseConversionEvent;

public class Interpolacion {

	static double c = 0;
	static double raiz = 0;

	public static double interpol(double precision, double xizq, double xder) throws Exception {
		int iterador = 0;

		if (precision < 0)
			throw new Exception("La precision debe ser positiva");
		// solo para la primera llamada, el resto de recursivas entran en el
		// otro
		if (((sustitucion(xizq) * sustitucion(xder)) > 0) && iterador == 0) {
			throw new Exception("Los 2 puntos están en el mismo lado del eje de abscisas");

		} else {
			c = calculoXr(xizq, xder);
			if (Math.abs(sustitucion(c)) <= precision) {
				raiz = (double) ((int) (c * 10000)) / 10000;

				System.out.println("la raiz se encuentra en " + raiz);

			} else {
				iterador++;
				if ((sustitucion(xizq) * sustitucion(c)) < 0) {
					xder = c;
				} else {
					xizq = c;
				}

				interpol(precision, xizq, xder);
			}
		}

		return raiz;
	}

	public static double sustitucion(double punto) {
		double resultado = 0.0;

		TreeMap<Integer, Double> pol1 = new TreeMap<>();
		// f(x)=x 5 -x 4 +x 3 -3
		pol1.put(5, 1.0);
		pol1.put(4, -1.0);
		pol1.put(3, 1.0);
		pol1.put(2, 0.0);
		pol1.put(1, 0.0);
		pol1.put(0, -3.0);

		// polinomio = new polinomio(pol1);
		// this.polinomioActual = pol1;
		// this.polinomios = new ArrayList<>();
		// this.polinomios.add(polinomioActual);

		for (Iterator<Integer> it = pol1.keySet().iterator(); it.hasNext();) {
			int key = it.next();
			double coeficiente = pol1.get(key);
			int grado = key;
			if (coeficiente == 0) {
				resultado += 0;

			} else {
				resultado += coeficiente * (Math.pow(punto, grado));

			}

		}

		System.out.println("el punto sustituido tiene un valor de " + resultado);

		return resultado;
	}

	private static double calculoXr(double xizq2, double xder2) {
		// TODO Auto-generated method stub
		double resultado;
		double fdeIzq = sustitucion(xizq2);
		double fdeDer = sustitucion(xder2);
		double c = xder2 - (fdeDer / (fdeDer - fdeIzq)) * (xder2 - xizq2);

		return c;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		Double tol, a, b;
		System.out.println("Esto es un programa para obtener la raiz de la función\n"
				+ "f(x)=x^5-x^4+x^3-3 (la raiz se encuentra en 1.29486)\n"
				+ "mediante el método de interpolacion lineal,\n"
				+ "por favor introduzca un valor positivo para la precisión en formato\n"
				+ "double (0,0):");
		tol=sc.nextDouble();
		System.out.println("Ahora introduzca el punto del limite izquierdo del intervalo: ");
		a=sc.nextDouble();
		System.out.println("Por último introduzca el punto del limite derecho del intervalo: ");
		b=sc.nextDouble();
		
		try {
			
			System.out.println("El resultado del calculo de la raiz es: "+interpol(tol, a, b));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
