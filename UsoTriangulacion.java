package act2;
import java.util.LinkedList;
import javax.swing.JFrame;
import utils.lector;

/**
 * @author frasco2001
 *
 */
public class UsoTriangulacion {

	/**
	 * @param args
	 */
	@SuppressWarnings({})
	public static void main(String[] args) {

		// Variable con los vertices del poligono
		Punto[] vertices;
		Triangulo[] salida = null;

		// Peticion por pantalla del numero de vertices que tendra el poligono
		System.out.println("**** Este programa realiza la triangulación de un poligono regular,"
				+ " concavo o convexo, sin aristas que intersecten.\n");
		System.out.println("**** Introduzca el numero de vertices del poligono: \n");
		
		vertices = new Punto[lector.leerEntero()];

		// Peticion por pantalla de los vertices del poligono
		System.out.println("**** Introduce los vertices del poligono de forma correlativa,"
				+ " en el sentido contrario a las agujas del reloj\n");
		for (int i = 0; i < vertices.length; i++) {
			System.out.println("* Introduce abcisa punto " + (i + 1) + ":");
			double abcisa = lector.leerDouble();
			System.out.println("* Introduce ordenada punto " + (i + 1) + ":");
			double ordenada = lector.leerDouble();
			Punto nuevo = new Punto(abcisa, ordenada);
			vertices[i] = nuevo;
		}
		Poligono01 nuevo = new Poligono01(vertices);
		System.out.println(nuevo.toString()+"\n");

		
		// Inicio del proceso de triangulacion
		if (Poligono01.esPoligono(vertices)) {
			
			// Ejercicio nº1
			//---------------
			if (Poligono01.esConcavo(vertices)){
				System.out.println("***********************************************\n"+
								   "***		POLIGONO CONCAVO  	    ***\n"+
								   "***********************************************");
			} else {
				System.out.println("***********************************************\n"+
						   "***		POLIGONO CONVEXO  	    ***\n"+
						   "***********************************************\n");
			}
			// Ejercicio nº2
			//---------------
			// Salida por pantalla de los angulos de los segmentos que forman el
			// poligono
			//System.out.println(Poligono01.angulosPoligonoToString(vertices));
			LinkedList<Punto>cortes = Poligono01.seIntersectanAristas(vertices);
			System.out.println("\nAristas Secantes: " + cortes.size()+"\n");
			if(cortes.size()==0){
				
				// Ejercicio nº3
				//---------------
				System.out.println("***********************************************************\n"+
						   "***	CENTROIDE DEL POLIGONO	"+Poligono01.centroidePoligono(vertices)+"	***\n"+
						   "***********************************************************\n");				
				// Salida por pantalla de la solucion de la triangulacion del poligono
				System.out.println(Poligono01.noSeIntersectan());
				// Salida por pantalla del area del poligono
				System.out.println(Poligono01.areaPoligonoToString(vertices));
				salida = Triangulo.triangulacionPoligono(vertices);
				System.out.println(Triangulo.arrayTriangulosToString(salida));
				
				// crea marco para objeto PoligonosJPanel
				JFrame marco = new JFrame("Dibujo de poligonos");
				marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				TriangulosJPanel triangulosJPanel = new TriangulosJPanel(salida);
				marco.add(triangulosJPanel); // agrega poligonosJPanel al marco
				marco.setSize(400, 400); // establece el tamaño del marco
				marco.setVisible(true); // muestra el marco
			} else {
				System.out.println("**** No se pueden cortar las aristas del poligono para calcular los triangulos ****");
			}
		} else {
			System.out.println("**** No es un poligono ****");
			
		}
	}
}