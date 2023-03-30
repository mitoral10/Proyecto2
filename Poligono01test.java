package act2;
import static org.junit.Assert.*;

import org.junit.*;

public class Poligono01test {

	Punto[] triangulo;
	Punto[] cuadrado;
	Punto[] poli5puntos;
	Poligono01 cuatroPuntos, cincoPuntos;
	
	@Before
	public void setUp() throws Exception {
		Punto uno = new Punto (0,0);
		Punto dos = new Punto (2,0);
		Punto tres = new Punto (2,2);
		Punto cuatro = new Punto (0,2);
		Punto cinco = new Punto (1,1);
		
		triangulo = new Punto[3];
		triangulo[0] = uno;
		triangulo[1] = dos;
		triangulo[2] = tres;
		
		cuadrado = new Punto[4];
		cuadrado[0] = uno;
		cuadrado[1] = dos;
		cuadrado[2] = tres;
		cuadrado[3] = cuatro;	
	
		poli5puntos = new Punto[5];
		poli5puntos[0] = uno;
		poli5puntos[1] = dos;
		poli5puntos[2] = tres;
		poli5puntos[3] = cinco;
		poli5puntos[4] = cuatro;
		
		cuatroPuntos = new Poligono01(cuadrado);
		cincoPuntos = new Poligono01(poli5puntos);
	}
	@Test
	public void testNumeroDeVertices(){
		assertTrue(cuatroPuntos.getPoligono().length==4);
		assertTrue(cincoPuntos.getPoligono().length==5);
		assertFalse(cuatroPuntos.getPoligono().length!=4);
		assertFalse(cincoPuntos.getPoligono().length!=5);
	}
	
	@Test
	public void testEsPoligono() {
		assertEquals("ES POLIGONO", Poligono01.seraPoligono(cuadrado));
		assertNotEquals("No tiene más de 3 vertices, por lo que NO ES POLIGONO", Poligono01.seraPoligono(triangulo));
		assertEquals("ES POLIGONO", Poligono01.seraPoligono(poli5puntos));
	}
	
	@Test
	public void testConcavo() {
		assertFalse(Poligono01.esConcavo(cuadrado));
		assertTrue(Poligono01.esConcavo(poli5puntos));
	}
}
