package act2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JPanel;

/**
 * @author frasco2001
 *
 */

public class TriangulosJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	Polygon[] poligonos;
	Graphics g;
	Punto[] puntos;
	double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;

	public TriangulosJPanel(Triangulo[] triangulos) {
		poligonos = new Polygon[triangulos.length];	
		for (int i = 0; i < triangulos.length; i++) {
			Punto a = triangulos[i].getDerecha();
			if(a.getX()<minX){
				minX=a.getX();
			}
			if(a.getY()<minY){
				minY=a.getY();
			}
			Punto b = triangulos[i].getIzquierda();
			if(b.getX()<minX){
				minX=b.getX();
			}
			if(b.getY()<minY){
				minY=b.getY();
			}
			Punto c = triangulos[i].getVertice();
			if(c.getX()<minX){
				minX=c.getX();
			}
			if(c.getY()<minY){
				minY=c.getY();
			}
		}
		if (minX<0){
			minX=-minX;
		}
		if (minY<0){
			minY=-minY;
		}
		puntos = new Punto[triangulos.length*3];
		int contador=0;
		for (int i = 0; i < triangulos.length; i++) {
			Punto a = triangulos[i].getDerecha();
			Punto b = triangulos[i].getIzquierda();
			Punto c = triangulos[i].getVertice();
			poligonos[i] = new Polygon();
			poligonos[i].addPoint((int) (a.getX()+minX+1) * 50, (int) (a.getY()+minY+1) * 50);
			poligonos[i].addPoint((int) (b.getX()+minX+1) * 50, (int) (b.getY()+minY+1) * 50);
			poligonos[i].addPoint((int) (c.getX()+minX+1) * 50, (int) (c.getY()+minY+1) * 50);
			puntos[i+contador++]=a;
			puntos[i+contador++]=b;
			puntos[i+contador]=c;
		}
	}

	/**
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // llama al método paintComponent de la
									// superclase
		// dibuja polígono
		g.setColor(Color.RED);
		for (int i = 0; i < poligonos.length; i++) {
			g.drawPolygon(poligonos[i]);
		}
		g.setColor(Color.BLACK);
		for (int i = 0; i < puntos.length; i++) {
			g.drawOval((int)(puntos[i].getX()+minX+1)*50, (int)(puntos[i].getY()+minY+1)*50, 4, 4);
			g.drawString(puntos[i].toString(), (int)(puntos[i].getX()+minX+1)*50, (int)(puntos[i].getY()+minY+1)*50);
		}
	}
}
