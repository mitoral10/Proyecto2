package examen2;

import static org.junit.Assert.*;
import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

public class InterpolacionTest {

	@Test
	public void interpolTestMismos() {
		try {
			Assert.assertTrue((Interpolacion.interpol(0.2, -1, 0)) != -666);
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block

			return;

		}

	}

	@Test
	public void sustitucionTest() {

		Assert.assertTrue((Interpolacion.sustitucion(1)) == -2);
		Assert.assertTrue((Interpolacion.sustitucion(-1)) == -6);
		Assert.assertTrue((Interpolacion.sustitucion(0)) == -3);
		Assert.assertTrue(Interpolacion.sustitucion(1.66) == 6.585894697599998);

	}

	@Test
	public void precisionTest() {
		try {
			Assert.assertTrue((Interpolacion.interpol(-0.7, -1, 0)) != -666);
			fail();
		} catch (Exception e) {
			

			return;

		}

	}

	@Test
	public void interTestGranError() {
		try {
			Assert.assertTrue((Interpolacion.interpol(0.6, 0, 2)) == 1.2405);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// la raiz esta en 1.29486

	@Test
	public void interTestPeqError() {
		try {
			// si la raiz esta en el punto 1.29 y le damos un error del 0.1 sale
			// 1.28

			Assert.assertTrue((Interpolacion.interpol(0.001, 0, 7)) == 1.2947);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
