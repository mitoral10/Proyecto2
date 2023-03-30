package examen2;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class polinomioTest {

	public TreeMap<Integer, Double> tree1;
	public TreeMap<Integer, Double> tree2;
	public TreeMap<Integer, Double> tree3;
	public TreeMap<Integer, Double> tree4;
	public TreeMap<Integer, Double> tree5;
	public TreeMap<Integer, Double> tree6;
	public TreeMap<Integer, Double> tree7;
	public TreeMap<Integer, Double> tree8;
	public polinomio polinomio;
	public TreeMap<Integer, Double> monomio;

	@Before
	public void before() {
		tree1 = new TreeMap<>();
		tree2 = new TreeMap<>();
		tree3 = new TreeMap<>();
		tree4 = new TreeMap<>();
		tree5 = new TreeMap<>();
		tree6 = new TreeMap<>();
		tree7 = new TreeMap<>();
		tree8 = new TreeMap<>();

		tree1.put(3, 1.0);
		tree1.put(2, 4.0);
		tree1.put(1, 1.0);
		tree1.put(0, 10.0);
		// x^3 + 4x^2 + x + 10
		tree2.put(2, 2.0);
		tree2.put(1, 2.0);
		tree2.put(0, -7.0);
		// 2x^2 + 2x -7
		tree3.put(4, 5.0);
		tree3.put(3, -3.0);
		tree3.put(2, 2.0);
		tree3.put(1, -7.0);
		tree3.put(0, 3.0);
		// 5x^4 - 3x^3 + 2x^2 - 7x + 3
		tree4.put(0, 18.0);
		tree4.put(1, 18.0);
		tree4.put(2, 11.0);
		tree4.put(3, 2.0);
		tree4.put(4, 1.0);
		// x^4 + 2x^3 + 11x^2 + 18x + 18
		tree5.put(0, 6.0);
		tree5.put(1, 3.0);
		tree5.put(2, 5.0);
		tree5.put(3, 2.0);
		tree5.put(4, 4.0);
		tree5.put(5, 1.0);
		// x^4 + 2x^3 + 11x^2 + 18x + 18
		tree6.put(0, 124.0);
		tree6.put(1, 50.0);
		tree6.put(2, 35.0);
		tree6.put(3, 10.0);
		tree6.put(4, 1.0);
		// x^4 + 10x^3 + 35x^2 + 50x + 124
		tree7.put(0, 3.0);
		tree7.put(1, 3.0);
		tree7.put(2, 6.0);
		tree7.put(3, 2.0);
		tree7.put(4, 3.0);
		tree7.put(5, 1.0);
		// x^4 + 10x^3 + 35x^2 + 50x + 124
		tree8.put(0, 3.0);
		tree8.put(1, 0.0);
		tree8.put(2, 6.0);
		tree8.put(3, 2.0);
		tree8.put(4, 3.0);
		tree8.put(5, 1.0);
		// x^5 + 3x^4 + 2x^3 + 6x^2 + 3x + 3
		monomio = new TreeMap<>();
		monomio.put(1, 1.0);
		monomio.put(0, -1.0);
		// x-1
		polinomio = new polinomio();
		polinomio.add(tree1);
		polinomio.add(tree2);
		polinomio.add(tree3);
		polinomio.add(monomio);
		polinomio.add(tree4);
		polinomio.add(tree5);
		polinomio.add(tree6);
		polinomio.add(tree7);
		polinomio.add(tree8);
	}

	@Test
	public void Sumatest() {

		TreeMap<Integer, Double> treeSol = new TreeMap<>();
		treeSol.put(0, 3.0);
		treeSol.put(1, 3.0);
		treeSol.put(2, 6.0);
		treeSol.put(3, 1.0);
		// x^3 + 6x^2 + 3x + 3

		TreeMap<Integer, Double> treeResult = polinomio.suma(0, 1);

		Assert.assertTrue(this.equals(treeSol, treeResult));

	}

	@Test
	public void productoTest() {
		TreeMap<Integer, Double> treeSol = new TreeMap<>();
		treeSol.put(0, -70.0);
		treeSol.put(1, 13.0);
		treeSol.put(2, -6.0);
		treeSol.put(3, 3.0);
		treeSol.put(4, 10.0);
		treeSol.put(5, 2.0);
		// 2x^5 + 10x^4 + 3x^3 - 6x^2 + 13x - 70
		TreeMap<Integer, Double> treeResult = polinomio.multiplicacion(0, 1);
		if (treeSol.equals(treeResult)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void ruffiniTest() {
		TreeMap<Integer, Double> treeSol = new TreeMap<>();
		treeSol.put(0, -3.0);
		treeSol.put(1, 4.0);
		treeSol.put(2, 2.0);
		treeSol.put(3, 5.0);
		// 5x^3 + 2x^2 + 4x - 3

		TreeMap<Integer, Double> treeResult = polinomio.ruffini(2, 3);

		Assert.assertTrue(this.equals(treeSol, treeResult));
		Assert.assertTrue(polinomio.getResto() == 0);
	}

	public boolean equals(TreeMap<Integer, Double> pol1, TreeMap<Integer, Double> pol2) {
		Iterator<Integer> it1 = pol1.keySet().iterator();
		Iterator<Integer> it2 = pol2.keySet().iterator();
		Double et1;
		Double et2;
		int ex1;
		int ex2;

		while (it1.hasNext()) {
			ex1 = it1.next().intValue();
			et1 = pol1.get(ex1);

			ex2 = it2.next().intValue();
			et2 = pol2.get(ex2);

			if (!et1.equals(et2)) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void routhTest() {
		// Caso 1 : Sistema inestable
		String treeResult = polinomio.routh(0);

		String solu1 = "";
		solu1 = solu1 + "G(s)=s3+4.0*s2+s+10.0" + "\n";
		solu1 = solu1 + "S3-->" + "\t" + "1.00" + "\t" + "1.00" + "\t" + "\n";
		solu1 = solu1 + "S2-->" + "\t" + "4.00" + "\t" + "10.00" + "\t" + "\n";
		solu1 = solu1 + "S1-->" + "\t" + "-1.50" + "\t" + "0.00" + "\t" + "\n";
		solu1 = solu1 + "S0-->" + "\t" + "10.00" + "\t" + "\n";
		solu1 = solu1 + "Sistema inestable --> dos raíces positivas (hay dos cambios de signo en primera columna)";

		assertEquals(solu1, treeResult);

		// Caso 2, Sistema inestable
		String treeResult2 = polinomio.routh(1);
		String solu2 = "Sistema inestable, signo desigual.";
		assertEquals(solu2, treeResult2);

		// Caso 3, Sistema criticamente estable
		String treeResult4 = polinomio.routh(4);

		String solu3 = "";
		solu3 = solu3 + "G(s)=s4+2.0*s3+11.0*s2+18.0*s+18.0" + "\n";
		solu3 = solu3 + "S4-->" + "\t" + "1.00" + "\t" + "11.00" + "\t" + "18.00" + "\t" + "\n";
		solu3 = solu3 + "S3-->" + "\t" + "2.00" + "\t" + "18.00" + "\t" + "\n";
		solu3 = solu3 + "S2-->" + "\t" + "2.00" + "\t" + "18.00" + "\t" + "\n";
		solu3 = solu3 + "S1-->" + "\t" + "0.00" + "\t" + "\n";
		solu3 = solu3 + "S1-->" + "\t" + "4.0" + "\n";
		solu3 = solu3 + "S0-->" + "\t" + "18.00" + "\t" + "\n";
		solu3 = solu3 + "Sistema críticamente estable -->" + "\n";
		solu3 = solu3 + "Fila cero" + "\n";
		solu3 = solu3 + "No hay raíces positivas";
		assertEquals(solu3, treeResult4);

		// Caso 4, Sistema inestable
		String treeResult5 = polinomio.routh(5);

		String solu4 = "";
		solu4 = solu4 + "G(s)=s5+4.0*s4+2.0*s3+5.0*s2+3.0*s+6.0" + "\n";
		solu4 = solu4 + "S5-->" + "\t" + "1.00" + "\t" + "2.00" + "\t" + "3.00" + "\t" + "\n";
		solu4 = solu4 + "S4-->" + "\t" + "4.00" + "\t" + "5.00" + "\t" + "6.00" + "\t" + "\n";
		solu4 = solu4 + "S3-->" + "\t" + "0.75" + "\t" + "1.50" + "\t" + "0.00" + "\t" + "\n";
		solu4 = solu4 + "S2-->" + "\t" + "-3.00" + "\t" + "6.00" + "\t" + "\n";
		solu4 = solu4 + "S1-->" + "\t" + "3.00" + "\t" + "\n";
		solu4 = solu4 + "S0-->" + "\t" + "6.00" + "\t" + "\n";
		solu4 = solu4 + "Sistema inestable --> dos raíces positivas (hay dos cambios de signo en primera columna)";

		assertEquals(solu4, treeResult5);

		// Caso 5, Sistema estable
		String treeResult6 = polinomio.routh(6);

		String solu5 = "";
		solu5 += "G(s)=s4+10.0*s3+35.0*s2+50.0*s+124.0" + "\n";
		solu5 += "S4-->" + "\t" + "1.00" + "\t" + "35.00" + "\t" + "124.00" + "\t" + "\n";
		solu5 += "S3-->" + "\t" + "10.00" + "\t" + "50.00" + "\t" + "\n";
		solu5 += "S2-->" + "\t" + "30.00" + "\t" + "124.00" + "\t" + "\n";
		solu5 += "S1-->" + "\t" + "8.67" + "\t" + "\n";
		solu5 += "S0-->" + "\t" + "124.00" + "\t" + "\n";
		solu5 += "Sistema estable --> no hay raíces en semiplano derecho";

		assertEquals(solu5, treeResult6);

		// Caso 6, Degeneración en el cálculo
		String treeResult7 = polinomio.routh(7);

		String solu6 = "";
		solu6 += "G(s)=s5+3.0*s4+2.0*s3+6.0*s2+3.0*s+3.0" + "\n";
		solu6 += "S5-->" + "\t" + "1.00" + "\t" + "2.00" + "\t" + "3.00" + "\t" + "\n";
		solu6 += "S4-->" + "\t" + "3.00" + "\t" + "6.00" + "\t" + "3.00" + "\t" + "\n";
		solu6 += "S3-->" + "\t" + "0.00" + "\t" + "2.00" + "\t" + "\n";
		solu6 += "Degeneración en el Cálculo --> Primer elemento de fila es cero";

		assertEquals(solu6, treeResult7);

		// Caso 7, Sistema inestable, signo desigual.
		String treeResult8 = polinomio.routh(8);
		String solu7 = "Sistema inestable, signo desigual.";
		assertEquals(solu7, treeResult8);

	}

}
