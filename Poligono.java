package act2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import javafx.scene.canvas.GraphicsContext;
import utils.DoublyLinkedList;

public class Poligono {
	
	private static final int MAX = 100;
	private DoublyLinkedList<Punto> vertices;
	
	public Poligono(DoublyLinkedList<Punto> vertices){
		this.vertices = vertices;
	}
	
	public Poligono(Punto[] x) {
		this.vertices = new DoublyLinkedList<Punto>();
		for(Punto punto: x){
			this.vertices.add(punto);
		}
	}
	
	public Poligono(){
		this.vertices = new DoublyLinkedList<Punto>();
	}
	
	public static boolean esPoligono(Punto[] z) {
		if (seraPoligono(z).equals("ES POLIGONO"))
			return true;
		return false;
	}
	
	public static String seraPoligono(Punto[] z) {
		String salida = "ES POLIGONO";
		String flagNoVertices = "";
		int contador = 0;
		Punto vertice, izquierdo, derecho;
		for (int i = 0; i < z.length; i++) {
			vertice = z[i];
			if (i == 0) {
				izquierdo = z[z.length - 1];
			} else {
				izquierdo = z[i - 1];
			}
			if (i == z.length - 1) {
				derecho = z[0];
			} else {
				derecho = z[i + 1];
			}
			// Triangulo puntos3 = new Triangulo(izquierdo, vertice, derecho);
			double angulo = Triangulo.angulo(vertice, izquierdo, derecho);
			if (angulo == 180) {
				flagNoVertices += vertice.toString() + " no es un vertice.\n";
			} else {
				contador++;
			}
		}
		if (contador < 4) {
			salida = "No tiene más de 3 vertices, por lo que NO ES POLIGONO.\n";
		}
		if (contador != z.length) {
			salida += flagNoVertices;
		}
		return salida;
	}
	
	public static boolean esConcavo(Punto[] z) {
		boolean salida = false;
		double resultado = 0;
		double signo = 0;
		Punto a, b;
		for (int i = 0; i < z.length; i++) {
			a = z[i];
			if (z.length - i == 1)
				b = z[0]; // circular
			else
				b = z[i + 1];
			resultado = (a.getX() * b.getY()) - (a.getY() * b.getX());
			//System.out.println(resultado);
			if (i == 0) {
				signo = resultado;
			} else {
				if (signo != 0) {
					if (signo > 0 && resultado < 0) {
						salida = true;
					} else {
						if (signo < 0 && resultado > 0) {
							salida = true;
						}
					}
				} else {
					signo = resultado;
				}
			}
			if (i != 0 && i != z.length - 1 && resultado == 0) {
				salida = true;
			}
		}
		return salida;
	}
	
	/**
	 * 
	 * @param z
	 * @return
	 */

	public static double areaPoligono(Punto[] z) {
		double area = 0;
		Punto a, b;
		for (int i = 0; i < z.length; i++) {
			a = z[i];
			if (z.length - i == 1)
				b = z[0];
			else
				b = z[i + 1];
			area += ((a.getX() * b.getY()) - (b.getX() * a.getY()));
		}
		return area / 2.0;
	}

	/**
	 * 
	 * @param z
	 * @return
	 */
	public static Punto centroidePoligono(Punto[] z) {
		Punto a, b;
		double x = 0;
		double y = 0;
		double area = areaPoligono(z);
		for (int i = 0; i < z.length; i++) {
			a = z[i];
			if (z.length - i == 1)
				b = z[0];
			else
				b = z[i + 1];
			x += (b.getX() + a.getX()) * (a.getX() * b.getY() - b.getX() * a.getY());
			y += (b.getY() + a.getY()) * (a.getX() * b.getY() - b.getX() * a.getY());
		}
		x = x / (6 * area);
		y = y / (6 * area);
		return (new Punto(x, y));
	}

	/**
	 * 
	 * @param poligono
	 * @return
	 */
	public int getNumeroTriangulos(Punto[] poligono) {
		return poligono.length - 2;
	}

	/**
	 * 
	 * @param vertices
	 * @return
	 */
	public static LinkedList<Punto> seIntersectanAristas(Punto[] vertices) {
		LinkedList<Punto> lista1 = new LinkedList<Punto>();
		LinkedList<Punto> solucion = new LinkedList<Punto>();
		for (int i = 0; i < vertices.length; i++) {
			lista1.add(vertices[i]);
		}
		Punto PrimerPunto = vertices[0];
		int contador1 = 0;
		int contador2 = 0;
		ListIterator<Punto> iter1 = lista1.listIterator();
		while (iter1.hasNext()) {
			Punto uno = (Punto) iter1.next();
			Punto dos = new Punto(0, 0);
			if (iter1.hasNext()) {
				dos = (Punto) iter1.next();
				iter1.previous();
			} else {
				dos = PrimerPunto;
			}
			ListIterator<Punto> iter2 = lista1.listIterator();
			Recta primera = new Recta(uno, dos);
			while (iter2.hasNext()) {
				Punto tres = (Punto) iter2.next();
				Punto cuatro = new Punto(0, 0);
				if (iter2.hasNext()) {
					cuatro = (Punto) iter2.next();
					iter2.previous();
				} else {
					cuatro = PrimerPunto;
				}
				if (tres.equals(uno) || tres.equals(dos) || cuatro.equals(uno) || cuatro.equals(dos)) {
					contador2++;
					continue;
				}
				Recta segunda = new Recta(tres, cuatro);
				if (Recta.sonParalelas(primera, segunda)) {
					System.out
							.println("Los segmentos [" + (contador1 + 1) + "] y [" + (++contador2) + "] son paralelos");
				} else {
					// primera.ecuacionRecta();
					// segunda.ecuacionRecta();
					Punto corte = Recta.puntoCorte(primera, segunda);
					if (primera.getCoeficienteA() * corte.getX() + primera.getCoeficienteB() * corte.getY()
							+ primera.getCoeficienteC() == 0) {
						if (Math.min(uno.getX(), dos.getX()) <= corte.getX()
								&& corte.getX() <= Math.max(uno.getX(), dos.getX())
								&& Math.min(uno.getY(), dos.getY()) <= corte.getY()
								&& corte.getY() <= Math.max(uno.getY(), dos.getY())
								&& Math.min(tres.getX(), cuatro.getX()) <= corte.getX()
								&& corte.getX() <= Math.max(tres.getX(), cuatro.getX())
								&& Math.min(tres.getY(), cuatro.getY()) <= corte.getY()
								&& corte.getY() <= Math.max(tres.getY(), cuatro.getY())) {
							if (!(corte.equals(tres) || corte.equals(cuatro))) {
								System.out.println("El segmento [" + (contador1 + 1) + "] se corta con el segmento ["
										+ (++contador2) + "]");
								System.out.println(corte.toString());
								if (!solucion.contains(corte)){
									solucion.add(corte);
								}
							}
						} else {
							contador2++;
						}
					}
				}
			}
			contador1++;
			contador2 = 0;
		}
		return solucion;
	}

	/**
	 * 
	 * @param poligono
	 * @return
	 */
	public static String areaPoligonoToString(Punto[] poligono) {
		String salida = "------------------------------------------------\n" + "El area del poligono es: "
				+ areaPoligono(poligono) + " ud(2).\n" + "------------------------------------------------";
		return salida;
	}

	public static String noSeIntersectan() {
		return "------------------------------------------------\n" + "Las Aristas del Poligono No Se Intersectan\n"
				+ "------------------------------------------------";
	}

	/**
	 * 
	 * @param poligono
	 * @return
	 */
	public static String angulosPoligonoToString(Punto[] poligono) {
		String salida = "---------------------------------------------\n"
				+ "Los respectivos angulos de los vertices son: \n" + "---------------------------------------------\n";
		for (int i = 0; i < poligono.length; i++) {
			Punto[] puntos = new Punto[3];
			puntos[0] = poligono[i]; // [0] = vertice
			if (i == 0) {
				puntos[1] = poligono[i + 1]; // [1] = derecha
				puntos[2] = poligono[poligono.length - 1]; // [2] = izquierda
			} else {
				if (i == poligono.length - 1) {
					puntos[1] = poligono[0];
					puntos[2] = poligono[i - 1];
				} else {
					puntos[1] = poligono[i + 1];
					puntos[2] = poligono[i - 1];
				}
			}
			salida += (Triangulo.angulo(puntos[0], puntos[2], puntos[1])) + "\n";
		}
		return salida;
	}
	
	public boolean addVertice(Punto p){
		if(this.vertices.size() >= MAX) return false;
		this.vertices.add(p);
		return true;
	}
	
	public LinkedList<Punto[]> triangulacion(){
		LinkedList<Punto[]> triangulos = new LinkedList<Punto[]>();
		DoublyLinkedList<Punto> verticesC = (DoublyLinkedList<Punto>)vertices.clone();
		ListIterator<Punto> it = verticesC.iterator();
		ListIterator<Punto> itAux;
		Punto current, izq, dch, aux;
		boolean triangle;
		while(verticesC.size() != 3){
			triangle = true;
			if(!it.hasNext()) it = verticesC.iterator();
			current = it.next();
			it.previous();
			if(!it.hasPrevious()){
				izq = verticesC.getLast();
				it.next();
			}
			else{
				izq = it.previous();
				it.next();
				it.next();
			}
			if(!it.hasNext()) dch = verticesC.getFirts();
			else{
				dch = it.next();
				it.previous();
			}
			if(!this.area_triángulo_signo(izq, current, dch)) continue;
			itAux = verticesC.iterator();
			while(itAux.hasNext()){
				aux = itAux.next();
				if(aux.equals(izq) || aux.equals(dch) || aux.equals(current)) continue;
				if(this.area_triángulo_signo(izq, current, aux) && this.area_triángulo_signo(current, dch, aux) && this.area_triángulo_signo(dch, izq, aux)){
					triangle = false;
					break;
				}
			}
			if(!triangle) continue;
			Punto[] triangulo = {izq, current, dch};
			triangulos.add(triangulo);
			it.previous();
			it.remove();
		}
		it = verticesC.iterator();
		Punto[] triangulo = {it.next(), it.next(), it.next()};
		triangulos.add(triangulo);
		return triangulos;
	}
	
	private boolean area_triángulo_signo(Punto a, Punto b, Punto c){
		if((((a.getX()*b.getY())-(a.getY()*b.getX())+(a.getY()*c.getX())-(a.getX()*c.getY())+(b.getX()*c.getY())-(c.getX()*b.getY()))/2.0) <= 0) return false;
		return true;
	}
	
	public Punto[] toArray(){
		Punto[] toReturn = new Punto[this.vertices.size()];
		ListIterator<Punto> it = this.vertices.iterator();
		int i = 0;
		while(it.hasNext()){
			toReturn [i] = it.next();
			i++;
		}
		return toReturn;
	}
	
	@Override
	public String toString() {
		return "[Poligono=" + Arrays.toString(this.toArray()) + "]";
	}

	/**
	 * 
	 * @return
	 */
	public Punto[] getPoligono() {
		return this.toArray();
	}

	/**
	 * 
	 * @param poligono
	 */
	public void setPoligono(Punto[] poligono) {
		this.vertices = new DoublyLinkedList<Punto>();
		for(Punto punto: poligono){
			this.vertices.add(punto);
		}
	}
	
	public void draw(GraphicsContext gc){
		
		ListIterator<Punto> it = this.vertices.iterator();
		Punto primero = it.next();
		Punto anterior = primero;
		Punto actual;
		while(it.hasNext()){
			actual = it.next();
			gc.strokeLine(anterior.getX()*50 + 320, anterior.getY()*50 +235, actual.getX()*50 + 320, actual.getY()*50 + 235);
			anterior = actual;
		}
		gc.strokeLine(anterior.getX()*50 + 320, anterior.getY()*50 +235, primero.getX()*50 + 320, primero.getY()*50+235);
	}

}
