package act2;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author frasco2001
 *
 */
public class Triangulo {
	Punto[] triangulo = new Punto[3];

	/**
	 * 
	 * @param z
	 */
	public Triangulo(Punto[] z) {
		this.triangulo = z;
	}

	/**
	 * 
	 * @param izquierdo
	 * @param vertice
	 * @param derecho
	 */
	public Triangulo(Punto izquierdo, Punto vertice, Punto derecho) {
		// triangulo = new Punto[3];
		triangulo[0] = vertice;
		triangulo[1] = derecho;
		triangulo[2] = izquierdo;
	}

	/**
	 * Funcion que calcula la diferencia de angulos entre dos rectas formadas
	 * por tres puntos a->b y a->c , es decir el angulo formado por las dos
	 * rectas (angulo(a->c)-angulo(a->b)), pero siempre positivo. Por lo tanto,
	 * obtenemos el angulo en sentido antihorario.
	 * 
	 * @param vertice
	 *            : Primer punto, y sobre el que se realizara el calculo del
	 *            angulo.
	 * @param dos
	 *            : Segundo punto, punto a la derecha del vertice, inicio del
	 *            calculo del angulo en sentido antihorario.
	 * @param tres
	 *            : Tercer punto, punto a la izquierda del vertice, fin del
	 *            calculo del angulo.
	 * @return : Angulo en radianes [0-2PI] con la diferencia entre las rectas
	 *         vertice-izquierda y vertice-derecha, pero formateado en
	 *         gradianes.
	 */
	public static double angulo(Punto vertice, Punto derecha, Punto izquierda) {

		// transladamos al origen de coordenadas los tres puntos
		Punto pi = new Punto(derecha.x - vertice.x, derecha.y - vertice.y);
		Punto pj = new Punto(izquierda.x - vertice.x, izquierda.y - vertice.y);

		// calculamos su angulo de coordenada polar
		double ang_pi = Math.atan2((double) pi.x, (double) pi.y);
		double ang_pj = Math.atan2((double) pj.x, (double) pj.y);

		// hallamos la diferencia
		double ang = ang_pj - ang_pi;

		// Si el angulo es negativo le sumamos 2PI para obtener el
		// angulo en el intervalo [0-2PI];
		// siempre obtenemos ángulos positivos (en sentido antihorario)
		if (ang < 0.0)
			return Math.toDegrees(ang + (2.0 * Math.PI));
		else
			return Math.toDegrees(ang);
	}// fin angulo

	/**
	 * 
	 * @param triangulo
	 * @return
	 */
	public boolean esTriangulo(Punto[] triangulo) {
		if (triangulo.length != 3) {
			return false;
		}
		if (!triangulo[0].equals(triangulo[1]) || !triangulo[0].equals(triangulo[2])
				|| !triangulo[1].equals(triangulo[2])) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public Punto[] getTriangulo() {
		return triangulo;
	}

	/**
	 * 
	 * @param triangulo
	 */
	public void setTriangulo(Punto[] triangulo) {
		this.triangulo = triangulo;
	}

	/**
	 * 
	 * @return
	 */
	public Punto getVertice() {
		return triangulo[0];
	}

	/**
	 * 
	 * @return
	 */
	public Punto getDerecha() {
		return triangulo[1];
	}

	/**
	 * 
	 * @return
	 */
	public Punto getIzquierda() {
		return triangulo[2];
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return Arrays.toString(triangulo);
	}

	/**
	 * 
	 * @param poligono
	 * @return
	 */
	public static Triangulo[] triangulacionPoligono(Punto[] poligono) {
		int contadorTriangulos = 0;
		// Array con los triangulos de la solucion
		Triangulo[] trianguloSalida;
		// Lista original de vertices del poligono
		LinkedList<Punto> vertex = new LinkedList<Punto>();
		// Lista auxiliar de vertices
		LinkedList<Punto> auxVertex = new LinkedList<Punto>();
		// Lista de vertices para comprobar si estan contenidos en el triangulo
		LinkedList<Punto> contenido = new LinkedList<Punto>();
		// Lista de vertices eliminados en la triangulacion
		LinkedList<Punto> eliminados = new LinkedList<Punto>();

		for (int i = 0; i < poligono.length; i++) {
			vertex.add(poligono[i]);
		}
		// Instanciacion del array de la solucion
		trianguloSalida = new Triangulo[poligono.length - 2];
		// Creacion del iterador de la lista principal
		ListIterator<Punto> iter1 = vertex.listIterator();
		// Instanciacion de los puntos que formaran los posibles triangulos
		// del poligono
		Punto izquierdo = null, angulo = null, derecho = null;
		// Contador de vueltas o puntos que no han podido ser eliminados en
		// principio en el proceso de triangulacion
		int vueltas = 0;

		while (trianguloSalida.length != contadorTriangulos) {
			auxVertex.clear();
			for (int i = 0; i < poligono.length; i++) {
				if (!eliminados.contains(poligono[i])) {
					auxVertex.add(poligono[i]);
				}
			}
			// Creacion del iterador de la lista auxiliar
			ListIterator<Punto> iterAux1 = auxVertex.listIterator();
			// Colocacion de los iteradores
			for (int i = 0; i < vueltas; i++) {
				if (iterAux1.hasNext()) {
					iterAux1.next();
				} else {
					iterAux1 = auxVertex.listIterator();
					i++;
				}
			}
			// Salida por pantalla de los puntos de los posibles triangulos
			System.out.println(posibleTrianguloToString());
			if (iterAux1.hasPrevious()) {
				izquierdo = iterAux1.previous();
				iterAux1.next();
			} else {
				izquierdo = auxVertex.getLast();
			}
			System.out.println("Punto izquierdo:" + izquierdo);

			if (iterAux1.hasNext()) {
				angulo = iterAux1.next();
				if (iter1.hasNext()) {
					iter1.next();
				} else {
					iter1 = vertex.listIterator();
				}
			} else {
				iterAux1 = auxVertex.listIterator();
				angulo = iterAux1.next();
				if (!iter1.hasNext()) {
					iter1 = vertex.listIterator();
					iter1.next();
				}
			}
			System.out.println("Punto central  :" + angulo);

			if (iterAux1.hasNext()) {
				derecho = iterAux1.next();
			} else {
				derecho = auxVertex.getFirst();
			}
			System.out.println("Punto derecho  :" + derecho);

			// Comprobacion si el triangulo pertenece al poligono y sino
			// contiene otros puntos de este
			if (Triangulo.angulo(angulo, izquierdo, derecho) < 180) {
				contenido.clear();
				for (Iterator<Punto> iter = vertex.iterator(); iter.hasNext();) {
					Punto punto = (Punto) iter.next();
					if (!(punto.equals(derecho) || punto.equals(angulo) || punto.equals(izquierdo))
							&& !eliminados.contains(punto)) {
						contenido.add(punto);
					}
				}
				if (contenido.size() == 0 && auxVertex.size() == 3) {
					trianguloSalida[contadorTriangulos++] = new Triangulo(izquierdo, angulo, derecho);
					System.out.println(trianguloAñadidoOKToString());
				} else {
					int eliminar = 0;
					for (Iterator<Punto> iter = contenido.iterator(); iter.hasNext();) {
						Punto punto = (Punto) iter.next();
						System.out.println(angulosTrianguloRestoVerticesToString());
						System.out.println(Triangulo.angulo(izquierdo, punto, angulo));
						System.out.println(Triangulo.angulo(angulo, punto, derecho));
						System.out.println(Triangulo.angulo(derecho, punto, izquierdo));
						if (Triangulo.angulo(izquierdo, punto, angulo) > 180
								|| Triangulo.angulo(angulo, punto, derecho) > 180
								|| Triangulo.angulo(derecho, punto, izquierdo) > 180) {
							eliminar++;
						}
					}
					if (eliminar == contenido.size()) {
						trianguloSalida[contadorTriangulos++] = new Triangulo(izquierdo, angulo, derecho);
						eliminados.add(angulo);
						System.out.println(trianguloAñadidoOKToString());
					} else {
						vueltas++;
					}
				}
			} else {
				vueltas++;
			}
		}
		// return (Triangulo.arrayTriangulosToString(trianguloSalida));
		return trianguloSalida;
	}

	/**
	 * 
	 * @param triangulos
	 * @return
	 */
	public static String arrayTriangulosToString(Triangulo[] triangulos) {
		String salida = "---------------------------------------------\n" + "La solucion es: \n"
				+ "---------------------------------------------\n" + "Los triangulos son:\n"
				+ "---------------------------------------------\n"
				+ "***********************************************************************************\n";
		for (int i = 0; i < triangulos.length; i++) {
			salida += "***	" + triangulos[i].toString() + "	***\n";
		}
		return salida + "***********************************************************************************\n";
	}

	/**
	 * 
	 * @return
	 */
	public static String trianguloAñadidoOKToString() {
		return "*********************************************\n" + "Triangulo aorrecto añadido...........\n"
				+ "*********************************************\n";

	}

	/**
	 * 
	 * @return
	 */
	public static String posibleTrianguloToString() {
		return "------------------------------------------------\n" + "Posible Triangulo........:\n"
				+ "------------------------------------------------\n";
	}

	/**
	 * 
	 * @return
	 */
	public static String angulosTrianguloRestoVerticesToString() {
		return "----------------------------------------------------------------\n"
				+ "Angulos del triangulo con los restantes vertices del poligono...\n"
				+ "----------------------------------------------------------------\n";
	}
}
