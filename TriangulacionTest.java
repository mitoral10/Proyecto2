package act2;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class TriangulacionTest {
	
	@Before
	public void setUp() throws Exception {
		Punto uno = new Punto (0,0);
		Punto dos = new Punto (2,0);
		Punto tres = new Punto (2,2);
		Punto cuatro = new Punto (0,2);
		Punto cinco = new Punto (1.5,1);
		Punto seis = new Punto (0.5,1);
		
		
	}

	@Test
	public void test() {
		Poligono p = new Poligono();
		Punto p1 = new Punto(1, 1);
		Punto p2 = new Punto(2, 1);
		Punto p3 = new Punto(2, 2);
		Punto p4 = new Punto(1, 2);
		p.addVertice(p1);
		p.addVertice(p2);
		p.addVertice(p3);
		p.addVertice(p4);
		LinkedList<Punto[]> triangulos = p.triangulacion();
	}

}
