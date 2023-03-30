package examen2;

import java.util.Scanner;
import java.util.TreeMap;

public class App {

	public static void main(String[] args) {
		polinomio clase=new polinomio();
		Scanner leer=new Scanner(System.in);
		TreeMap<Integer, Double> poli=new TreeMap<>();
		System.out.println("Bienvenid@ a nuestra aplicación.\n");
		boolean salir=false;
		while(!salir){
		System.out.println("1 - Insertar polinomio.");
		System.out.println("2 - Seleccionar operación.");
		System.out.println("Cualquier tecla para Salir.");
		System.out.print("Opción: ");
		String opcion=leer.nextLine();
		if("1".equals(opcion)){
			poli=new TreeMap<>();
			System.out.println("Se irán insertando bloques aXb.\na--> valor base.\nb-->valor exponente(no se pueden exponentes negativos).\nSi añade una base con el mismo exponente se sumaran las bases.");
			while(true){
				System.out.print("Inserte la base: ");
				double base=Double.parseDouble(leer.nextLine());
				System.out.print("Inserte el exponente: ");
				int exponente=Math.abs(Integer.parseInt(leer.nextLine()));
				
				if (poli.containsKey(exponente)) {
					poli.replace(exponente, poli.get(exponente)+base);
				} else {
					poli.put(exponente, base);
				}
				
				System.out.println("Su polinomio es "+clase.poliToString(poli, "x"));
				System.out.println("Para volver pulse 1.\nPara seguir añadiendo valores pulse cualquier tecla.");
				System.out.print("Opcion: ");
				
				if(leer.nextLine().equals("1")){
					break;
				}
			}
			clase.add(poli);
		}else if("2".equals(opcion)){
			while(true){
			System.out.println("1 - Suma de expresiones.");
			System.out.println("2 - Producto de expresiones.");
			System.out.println("3 - División método de Ruffini.");
			System.out.println("4 - Teorema de Routh-Hurwitz.");
			System.out.println("5 - Método de interpolación Lineal.");
			System.out.println("Cualquier tecla para volver.");
			System.out.print("Operación: ");
			String operacion=leer.nextLine();
			if(operacion.equals("1")){
				operaciones(clase,1);
			}else if(operacion.equals("2")){
				operaciones(clase,2);
			}else if(operacion.equals("3")){
				operaciones(clase,3);
			}else if(operacion.equals("4")){
				operaciones(clase,4);
			}else if(operacion.equals("5")){
				Interpolacion.main(args);
				Scanner aux=new Scanner(System.in);
				aux.nextLine();
			}else{
				break;
			}
			}
		}else{
			salir=true;
		} 
		}
	}

	private static void operaciones(polinomio clase,int i) {
		Scanner leer=new Scanner(System.in);
		System.out.println("Lista de polinomios:");
		System.out.println(clase.listaPolinomios());
		System.out.print("Seleccione el 1º polinomio: ");
		int p1=Integer.parseInt(leer.nextLine());
		if(i==3){
			System.out.print("Seleccione un monomio: ");
		}else if(i==4){
			
		}else{
			System.out.print("Seleccione el 2º polinomio: ");
		}
		int p2=0;
		if(i!=4){
			p2=Integer.parseInt(leer.nextLine());
		}
		
		if(i==1){
		System.out.println("\n"+clase.poliToString(clase.getPolinomio(p1), "x")+"\n+\n"+clase.poliToString(clase.getPolinomio(p2), "x")+"\n---------------------\n"+clase.poliToString(clase.suma(p1, p2), "x"));
		}else if(i==2){
			System.out.println("\n"+clase.poliToString(clase.getPolinomio(p1), "x")+"\n*\n"+clase.poliToString(clase.getPolinomio(p2), "x")+"\n---------------------\n"+clase.poliToString(clase.multiplicacion(p1, p2), "x"));	
		}else if(i==3){
			System.out.println("\n"+clase.poliToString(clase.getPolinomio(p1), "x")+"\n/\n"+clase.poliToString(clase.getPolinomio(p2), "x")+"\n---------------------\n"+clase.poliToString(clase.ruffini(p1, p2), "x")+"	Resto: "+clase.getResto());
		}else if(i==4){
			System.out.println("\n"+clase.routh(p1));
		}
		leer.nextLine();
		
	}
	
}
