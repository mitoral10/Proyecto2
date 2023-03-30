package examen2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class polinomio {
	private ArrayList<TreeMap<Integer, Double>> polinomios;
	private Double resto;

	public polinomio() {
		this.polinomios = new ArrayList<>();
	}
	
	public void add(TreeMap<Integer,Double> polinomio){
		this.polinomios.add(polinomio);
	}
	
    public String listaPolinomios(){
		String lista="";
		for (int i = 0; i < polinomios.size(); i++) {
			lista+=i+"--> "+poliToString(polinomios.get(i), "x");
		}
		return lista;
	}
	public TreeMap<Integer, Double> getPolinomio(int i){
		return polinomios.get(i);
	}
	public TreeMap<Integer, Double> suma(int poli1, int poli2) {
		TreeMap<Integer, Double> op1 = polinomios.get(poli1);
		TreeMap<Integer, Double> op2 = polinomios.get(poli2);
		Iterator<Integer> iterOp1 = op1.keySet().iterator();
		while (iterOp1.hasNext()) {
			int key = iterOp1.next();
			if (op2.get(key) != null) {
				op1.replace(key, op2.get(key) + op1.get(key));
				op2.remove(key);
			}
		}
		op1.putAll(op2);
		return op1;

	}

	public TreeMap<Integer, Double> multiplicacion(int poli1, int poli2) {
		TreeMap<Integer, Double> solucion = new TreeMap<>();
		TreeMap<Integer, Double> op1 = polinomios.get(poli1);
		TreeMap<Integer, Double> op2 = polinomios.get(poli2);
		Iterator<Integer> iterOp1 = op1.keySet().iterator();
		
		while (iterOp1.hasNext()) {
			int key = iterOp1.next();
			Iterator<Integer> iterOp2 = op2.keySet().iterator();
			while (iterOp2.hasNext()) {
				int key2 = iterOp2.next();

				int keys = key + key2;
				if (solucion.containsKey(keys)) {
					solucion.replace(keys, (solucion.get(keys)) + (op1.get(key) * op2.get(key2)));
				} else {
					solucion.put(keys, op1.get(key) * op2.get(key2));
				}
			}
		}
		return solucion;

	}

	public TreeMap<Integer, Double> ruffini(int poli1, int poli2) {
		TreeMap<Integer, Double> solucion = new TreeMap<>();
		TreeMap<Integer, Double> op1 = polinomios.get(poli1);
		double divisor = -1 * polinomios.get(poli2).get(0);
		// tiene que existir termino independiente ejemplo 3x2+2x -> 3x +2
		if (op1.get(0) == null || op1.get(0) == 0) {
			eliminarX(op1);
		}
		// tiene que tener 0 en los exponentes que no esten.
		for (int i = 0; i <= op1.lastKey(); i++) {
			if (op1.get(i) == null) {
				op1.put(i, (double) 0);
			}
		}

		Iterator<Integer> iterOp1 = op1.descendingKeySet().iterator();
		Double operador1 = (double) 0;
		while (iterOp1.hasNext()) {
			int key = iterOp1.next();

			if (key == op1.lastKey()) {
				operador1 = op1.get(key);
				solucion.put(key - 1, op1.get(key));
			} else if (key == 0) {
				operador1 = (operador1 * divisor) + op1.get(key);
			} else {
				operador1 = (operador1 * divisor) + op1.get(key);
				solucion.put(key - 1, operador1);
			}
		}
		this.resto = operador1;
		return solucion;

	}

	private void eliminarX(TreeMap<Integer, Double> op1) {
		TreeMap<Integer, Double> aux = new TreeMap<>();
		Iterator<Integer> it = op1.keySet().iterator();
		while (it.hasNext()) {
			int key = it.next();
			aux.put(key - op1.firstKey(), op1.get(key));
		}
		op1.clear();
		op1.putAll(aux);
	}

	public Double getResto() {
		return resto;
	}

	public String routh(int poli1){
		TreeMap<Integer, Double> op1 = polinomios.get(poli1);
		String solucion="";
		int positivo=0;
		for (int i = 0; i <= op1.lastKey(); i++) {
			if(op1.get(i)==null||op1.get(i)==0){
				solucion= "Sistema inestable elemento x"+i+" nulo.";
				break;
			}
			if (op1.get(i) > 0) {
				positivo++;
			}
		}
		
		if(positivo!=0&&(positivo-op1.size()!=0)){
				solucion="Sistema inestable, signo desigual.";
		}else{
		Double[][] matriz= new Double[op1.lastKey()+1][(int)Math.ceil(0.25+(op1.lastKey()+1)/2)];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = (double) 0;
			}
		}
		String parte1="";
		String parte2="";
		DecimalFormat f = new DecimalFormat("#0.00");
		for (int i = 0; i < Math.ceil(0.25+(op1.lastKey()+1)/2); i++) {
			if(op1.lastKey()-i*2>=0){
			matriz[0][i]=Math.abs(op1.get(op1.lastKey()-i*2));
			parte1+=f.format(matriz[0][i]).replace(",", ".")+"	";
			}
			if(op1.lastKey()-(i*2)-1>=0){
				matriz[1][i]=Math.abs(op1.get(op1.lastKey()-(i*2)-1));
			parte2+=f.format(matriz[1][i]).replace(",", ".")+"	";
			}

		}
		solucion+="G(s)="+poliToString(op1, "s");
		solucion+="\nS"+op1.lastKey()+"-->	"+parte1;
		solucion+="\nS"+(op1.lastKey()-1)+"-->	"+parte2+"\n";
		int count=0;
		boolean salir=false;
		boolean criticamenteEstable=false;
		boolean sistemaInestable=false;

		for (int i = 2; i < matriz.length; i++) {
			if(matriz[i].length-count>1){
				count++;
			}
			solucion+="S"+(op1.lastKey()-i)+"-->	";
			for (int j = 0; j < matriz[i].length-count; j++) {
				double aux = calculoRouth(i, j, matriz);
				
				if(aux==Double.NaN){
					matriz[i][j]=0.00;
				}else{
						solucion+=f.format(aux).replace(",", ".")+"	";
					
					if(aux==0 && j==0){
						if(calculoRouth(i, j+1, matriz)!=0){
							solucion+=f.format(calculoRouth(i, j+1, matriz)).replace(",", ".")+"	";
							salir=true;
							break;
						}else{
							criticamenteEstable=true;
							int coeficiente=0;
							for (int k = 0; k < matriz[i-1].length; k++) {
								if(matriz[i-1][k]!=0){
									coeficiente++;
								}
							}
							matriz[i][j]=coeficiente*matriz[i-1][0];
							solucion+="\nS"+(op1.lastKey()-i)+"-->	"+matriz[i][j];
						}
					}else{

						matriz[i][j]=aux;
						if(aux<0){
							sistemaInestable=true;
						}
					}
					
				}

			}
			solucion+="\n";
			if(salir){
				break;
			}
		}
		if(criticamenteEstable){
			solucion+="Sistema críticamente estable -->\nFila cero\nNo hay raíces positivas";
		}else if(sistemaInestable){
			solucion+="Sistema inestable --> dos raíces positivas (hay dos cambios de signo en primera columna)";
		}else{
			if(!salir){
			solucion+="Sistema estable --> no hay raíces en semiplano derecho";
			}else{
			solucion+="Degeneración en el Cálculo --> Primer elemento de fila es cero";
			}
		}
		}
		return solucion;
	}

	private double calculoRouth(int y, int x, Double[][] matriz) {
		double solucion = Double.NaN;
		if (matriz[y - 1][0] != 0) {
			solucion = ((matriz[y - 1][0] * matriz[y - 2][x + 1]) - (matriz[y - 2][0] * matriz[y - 1][x + 1]))
					/ matriz[y - 1][0];
		}
		return solucion;

	}
	
	public String poliToString(TreeMap<Integer, Double> poli,String variable){
		String polinomio="";
		for (int i = poli.lastKey(); i >= 0; i--) {
			if(poli.get(i)==null||poli.get(i)==0){
				
			}else{
				if(poli.get(i)==1){
					if(i==0){
						polinomio+="+1";
					}else if(i==1){
						polinomio+="+"+variable;
					}else{
						polinomio+="+"+variable+i;
					}
				}else if(poli.get(i)==-1){
					if(i==0){
						polinomio+="-1";
					}else if(i==1){
						polinomio+="-"+variable;
					}else{
						polinomio+="-"+variable+i;
					}
				}else if(poli.get(i)>0){
					if(i==0){
						polinomio+="+"+poli.get(i);
					}else if(i==1){
						polinomio+="+"+poli.get(i)+"*"+variable;
					}else{
						polinomio+="+"+poli.get(i)+"*"+variable+i;
					}
				}else if(poli.get(i)<0){
					if(i==0){
						polinomio+=poli.get(i);
					}else if(i==1){
						polinomio+=poli.get(i)+"*"+variable;
					}else{
						polinomio+=poli.get(i)+"*"+variable+i;
					}
				}
				
			}
			
		}
		if(poli.get(poli.lastKey())>0){
			return polinomio.substring(1);
		}else{
			return polinomio;
		}
		
	}
}
