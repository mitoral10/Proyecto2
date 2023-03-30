package utils;
import java.util.Scanner;

public class lector {

	private static Scanner lector = new Scanner(System.in);

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
