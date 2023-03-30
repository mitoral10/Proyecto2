package act2;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Rectatest {
	Recta normal, pasaPorOrigen, paralelaAX, paralelaAY, prueba0, prueba1, prueba2;
	Recta normal1, pasaPorOrigen1, paralelaAX1, prueba01, prueba11, prueba21;
	Recta normal2, pasaPorOrigen2, paralelaAX2;
	Recta normal3, pasaPorOrigen3, paralelaAX3, paralelaAY3;
	Recta normal4, normal5, normal6, normal7;
	Recta unaParalela, dosParalela, tresParalela;
	Recta corte1, corte2, corte3, corte4;
	Recta normal8;

	@Before
	public void setUp() throws Exception {
		Punto uno = new Punto(0, 0);
		Punto dos = new Punto(1, 3);
		Punto tres = new Punto(3, 5);
		Punto cuatro = new Punto(0, 2);
		Punto cinco = new Punto(2, 0);
		Punto seis = new Punto(0, -2);
		Punto siete = new Punto(-2, 0);

		normal = new Recta(dos, tres);
		normal1 = new Recta(2 / 3, 3);
		normal2 = new Recta(3, tres);
		normal3 = new Recta(1, 2, 3);
		normal4 = new Recta(cuatro);
		normal5 = new Recta(cinco);
		normal6 = new Recta(seis);
		normal7 = new Recta(siete);
		normal8 = new Recta(uno,cuatro);
		pasaPorOrigen = new Recta(uno, dos);
		pasaPorOrigen1 = new Recta(2, 0);
		pasaPorOrigen2 = new Recta(4, uno);
		pasaPorOrigen3 = new Recta(1, 1, 0);
		paralelaAX = new Recta(cuatro);
		paralelaAX1 = new Recta(0, 2);
		paralelaAX2 = new Recta(0, dos);
		paralelaAX3 = new Recta(0, 1, 2);
		paralelaAY = new Recta(cinco);
		paralelaAY3 = new Recta(1, 0, 2);
		prueba0 = new Recta(tres, cuatro);
		prueba1 = new Recta(cuatro, cinco);
		prueba2 = new Recta(uno, cinco);
		unaParalela = new Recta(0,1,0);
		dosParalela = new Recta(1,2,2);
		tresParalela = new Recta(2,4,3);
		corte1 = new Recta(1,0,-1);
		corte2 = new Recta(0,1,-2);
		corte3 = new Recta(1,0,0);
		corte4 = new Recta(0,1,0);
	}

	@Test
	public void test2Puntos() {
		assertEquals("La ecuacion de la Recta es: X-Y+2=0", normal.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: 3*X-Y=0", pasaPorOrigen.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: X-2=0", paralelaAX.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: Y-2=0", paralelaAY.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: X-Y+2=0", prueba0.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: -X-Y+2=0", prueba1.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: Y=0", prueba2.ecuacionRecta());
	}

	@Test
	public void testPendienteOrdenada() {
		assertEquals("La ecuacion de la Recta es: -Y+3=0", normal1.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: 2*X-Y=0", pasaPorOrigen1.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: -Y+2=0", paralelaAX1.ecuacionRecta());
	}

	@Test
	public void testPendientePunto() {
		assertEquals("La ecuacion de la Recta es: 3*X-Y-4=0", normal2.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: 4*X-Y=0", pasaPorOrigen2.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: -Y+3=0", paralelaAX2.ecuacionRecta());
	}

	@Test
	public void testImplicita() {
		assertEquals("La ecuacion de la Recta es: X+2*Y+3=0", normal3.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: X+Y=0", pasaPorOrigen3.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: Y+2=0", paralelaAX3.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: X+2=0", paralelaAY3.ecuacionRecta());
	}

	@Test
	public void testVerticales() {
		assertEquals("La ecuacion de la Recta es: X-2=0", normal4.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: X+2=0", normal6.ecuacionRecta());
	}

	@Test
	public void testHorizontales() {
		assertEquals("La ecuacion de la Recta es: Y-2=0", normal5.ecuacionRecta());
		assertEquals("La ecuacion de la Recta es: Y+2=0", normal7.ecuacionRecta());
	}
	
	@Test
	public void testParalelas() {
		assertFalse(Recta.sonParalelas(unaParalela, dosParalela));
		Recta.puntoCorte(unaParalela, dosParalela);
		assertTrue(Recta.sonParalelas(tresParalela,dosParalela));
	}
	
	@Test
	public void testPuntoCorte(){
		assertEquals(new Punto(1.0, 2.0).toString(),Recta.puntoCorte(corte1, corte2).toString());
		assertEquals(new Punto(0.0, 0.0).toString(),Recta.puntoCorte(corte3, corte4).toString());
		
	}
}
