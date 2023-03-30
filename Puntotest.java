package act2;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Puntotest {

	Punto uno,dos,tres,cuatro;
	@Before
	public void setUp() throws Exception {
		uno = new Punto (0,0);
		dos = new Punto (2,0);
		tres = new Punto (2,2);
		cuatro = new Punto(0,0);
		cuatro.setX(3);
		cuatro.setY(3);
	}
	@Test
	public void coordenadasTest(){
		assertEquals(0.0,uno.getX(),2);
		assertEquals(0.0,uno.getY(),2);
		assertEquals(2.0,uno.getX(),2);
		assertEquals(0.0,uno.getY(),2);
		assertEquals(2.0,uno.getX(),2);
		assertEquals(2.0,uno.getY(),2);
		assertNotEquals(0.0, cuatro.getX(),2);
		assertNotEquals(0.0, cuatro.getY(),2);
		assertEquals(3.0, cuatro.getX(),2);
		assertEquals(3.0, cuatro.getX(),2);
	}
	@Test
	public void puntoToString(){
		assertEquals("Punto [x=0.0, y=0.0]", uno.toString());
		assertEquals("Punto [x=2.0, y=0.0]", dos.toString());
		assertEquals("Punto [x=2.0, y=2.0]", tres.toString());
	}
}
