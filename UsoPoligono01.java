package act2;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * @author frasco2001
 *
 */
public class UsoPoligono01 {

	private static Scanner lector = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Peticiones por pantalla
		System.out.println("Introduce el numero de vertices del poligono (minimo 4 puntos):");
		int puntos = leerEntero();
		Punto[] vertices = new Punto[puntos];

		System.out.println("Introduce los puntos de forma correlativa en el sentido contrario a las agujas del reloj");
		for (int i = 0; i < vertices.length; i++) {
			System.out.println("Introduce abcisa punto " + (i + 1) + ":");
			double abcisa = leerDouble();
			System.out.println("Introduce ordenada punto " + (i + 1) + ":");
			double ordenada = leerDouble();
			Punto nuevo = new Punto(abcisa, ordenada);
			vertices[i] = nuevo;
		}
		Poligono01 poligono = new Poligono01(vertices);

		// Soluciones

		// (ES POlIGONO)
		if (Poligono01.esPoligono(vertices)) {
			// (AREA)
			System.out.println("El area del poligono es: ");
			System.out.println(Poligono01.areaPoligono(vertices));
			// (CENTROIDE)
			System.out.println("El centroide del poligono es el punto: ");
			System.out.println(Poligono01.centroidePoligono(vertices));
			// (CONCAVIDAD)
			System.out.println("¿Es Concavo el poligono?");
			System.out.println(Poligono01.esConcavo(vertices));
			// (PUNTOS DEL POLIGONO)
			System.out.println(poligono);

			// crea marco para objeto PoligonosJPanel
			JFrame marco = new JFrame("Dibujo de poligonos");
			marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			PoligonosJPanel poligonosJPanel = new PoligonosJPanel(vertices);
			marco.add(poligonosJPanel); // agrega poligonosJPanel al marco
			marco.setSize(400, 400); // establece el tamaño del marco
			marco.setVisible(true); // muestra el marco
		} else {
			System.out.println(Poligono01.seraPoligono(vertices));
		}
	}

	/**
	 * 
	 * @return
	 */
	public static double leerDouble() {
		return lector.nextDouble();
	}

	/**
	 * 
	 * @return
	 */
	public static int leerEntero() {
		return lector.nextInt();
	}
}
