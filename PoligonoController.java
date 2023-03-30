package ui;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import act2.Poligono;
import act2.Punto;
import act2.Recta;
import act2.Triangulo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PoligonoController {
	
	private Poligono poligono;
	private Punto[] poligonoArray;
	private GraphicsContext gc;
	private Recta linea1;
	private Recta linea2;
	
	@FXML private Button addpoitnButton;
	@FXML private Canvas poligonoCanvas;
	@FXML private TextField xInput;
	@FXML private TextField yInput;
	@FXML private Text concavoInput;
	@FXML private Text centroideInput;
	
	@FXML private TextField c1x1;
	@FXML private TextField c1x2;
	@FXML private TextField c1y1;
	@FXML private TextField c1y2;
	
	@FXML private TextField c2Pendiente;
	@FXML private TextField c2Origen;
	
	@FXML private TextField c3Pendiente;
	@FXML private TextField c3x;
	@FXML private TextField c3y;
	
	@FXML private TextField c4a;
	@FXML private TextField c4b;
	@FXML private TextField c4c;
	
	@FXML private TextField c5Abcisa;
	@FXML private TextField c5Ordenada;
	
	@FXML private Button btnr1c1;
	@FXML private Button btnr2c1;
	@FXML private Button btnr1c2;
	@FXML private Button btnr2c2;
	@FXML private Button btnr1c3;
	@FXML private Button btnr2c3;
	@FXML private Button btnr1c4;
	@FXML private Button btnr2c4;
	@FXML private Button btnr1c5;
	@FXML private Button btnr2c5;
	
	@FXML private Text ecq1;
	@FXML private Text ecq2;
	@FXML private Text pCorte;
	
	public void initialize(){
		this.poligono = new Poligono();
		this.gc = poligonoCanvas.getGraphicsContext2D();
		this.gc.fill();
		
		btnr1c1.setOnAction(new CreateLine());
		btnr2c1.setOnAction(new CreateLine());
		btnr1c2.setOnAction(new CreateLine());
		btnr2c2.setOnAction(new CreateLine());
		btnr1c3.setOnAction(new CreateLine());
		btnr2c3.setOnAction(new CreateLine());
		btnr1c4.setOnAction(new CreateLine());
		btnr2c4.setOnAction(new CreateLine());
		btnr1c5.setOnAction(new CreateLine());
		btnr2c5.setOnAction(new CreateLine());
		
	}
	
	@FXML private void addPoint() throws IOException {
		try{
			Double x = Double.parseDouble(xInput.getText());
			Double y = Double.parseDouble(yInput.getText());
			xInput.setText("");
			yInput.setText("");
			Punto p = new Punto(x, y);
			this.poligono.addVertice(p);
			this.poligonoArray = poligono.toArray();
			if(Poligono.esPoligono(this.poligonoArray)) this.operaciones();
			else{
				gc.clearRect(0, 0, poligonoCanvas.getWidth(), poligonoCanvas.getHeight());
				poligono.draw(gc);
			}
		}catch(Exception e){}
		
	}
	
	@FXML private void clearPoligono() throws IOException {
		this.poligono = new Poligono();
		gc.clearRect(0, 0, poligonoCanvas.getWidth(), poligonoCanvas.getHeight());
		concavoInput.setText("");
		centroideInput.setText("");
	}
	
	private void operaciones() {
		gc.clearRect(0, 0, poligonoCanvas.getWidth(), poligonoCanvas.getHeight());
		Triangulo[] triangulos = Triangulo.triangulacionPoligono(poligono.toArray());
		drawTriangulos(triangulos);
		poligono.draw(gc);
		poligonoArray = poligono.toArray();
		if(Poligono.esConcavo(poligonoArray)) concavoInput.setText("Polígono concavo");
		else concavoInput.setText("Polígono convexo");
		Punto centroide = Poligono.centroidePoligono(poligonoArray);
		centroideInput.setText("Centroide en " + centroide.toString());
		gc.setStroke(Color.BLUE);
		gc.setFill(Color.BLUE);
		gc.fillOval((centroide.getX()*50 + 320) - 5, (centroide.getY()*50 + 235) - 5, 10, 10);
		gc.setStroke(Color.BLACK);
	}

	private void drawTriangulos(Triangulo[] triangulos) {
		gc.setStroke(Color.RED);
		for(Triangulo t: triangulos){
			Punto[] triangulo = t.getTriangulo();
			gc.strokeLine(triangulo[0].getX()*50 + 320, triangulo[0].getY()*50 + 235, triangulo[1].getX()*50 + 320, triangulo[1].getY()*50 + 235);
			gc.strokeLine(triangulo[1].getX()*50 + 320, triangulo[1].getY()*50 + 235, triangulo[2].getX()*50 + 320, triangulo[2].getY()*50 + 235);
			gc.strokeLine(triangulo[2].getX()*50 + 320, triangulo[2].getY()*50 + 235, triangulo[0].getX()*50 + 320, triangulo[0].getY()*50 + 235);
		}
		gc.setStroke(Color.BLACK);
	}
	
	private class CreateLine implements EventHandler<ActionEvent>{
	    @Override
	    public void handle(ActionEvent evt) {
	    	try{
		        if (evt.getSource().equals(btnr1c1) || evt.getSource().equals(btnr2c1)) {
		          Punto p1 = new Punto(Double.parseDouble(c1x1.getText()), Double.parseDouble(c1y1.getText()));
		          Punto p2 = new Punto(Double.parseDouble(c1x2.getText()), Double.parseDouble(c1y2.getText()));
		          c1x1.setText("");
		          c1x2.setText("");
		          c1y1.setText("");
		          c1y2.setText("");
		          if(evt.getSource().equals(btnr1c1)){
		        	  linea1 = new Recta(p1, p2);
		        	  ecq1.setText(linea1.ecuacionRecta());
		          }else{
		        	  linea2 = new Recta(p1, p2);
		        	  ecq2.setText(linea1.ecuacionRecta());
		          }
		        } else if (evt.getSource().equals(btnr1c2) || evt.getSource().equals(btnr2c2)) {
		          double pendiente = Double.parseDouble(c2Pendiente.getText());
		          double origen = Double.parseDouble(c2Origen.getText());
		          c2Pendiente.setText("");
		          c2Origen.setText("");
		          if(evt.getSource().equals(btnr1c2)){
		        	  linea1 = new Recta(pendiente, origen);
		        	  ecq1.setText(linea1.ecuacionRecta());
		          }else{
		        	  linea2 = new Recta(pendiente, origen);
		        	  ecq2.setText(linea2.ecuacionRecta());
		          }
		        } else if (evt.getSource().equals(btnr1c3) || evt.getSource().equals(btnr2c3)) {
		        	double pendiente = Double.parseDouble(c3Pendiente.getText());
		        	Punto p = new Punto(Double.parseDouble(c3x.getText()), Double.parseDouble(c3y.getText()));
		        	c3Pendiente.setText("");
		        	c3x.setText("");
			        c3y.setText("");
			        if(evt.getSource().equals(btnr1c3)){
			        	linea1 = new Recta(pendiente, p);
			        	ecq1.setText(linea1.ecuacionRecta());
			        }else{
			        	linea2 = new Recta(pendiente, p);
			        	ecq2.setText(linea2.ecuacionRecta());
			        }
			    } else if (evt.getSource().equals(btnr1c4) || evt.getSource().equals(btnr2c4)) {
			    	double a = Double.parseDouble(c4a.getText());
			    	double b = Double.parseDouble(c4b.getText());
			    	double c = Double.parseDouble(c4c.getText());
			    	c4a.setText("");
			        c4b.setText("");
			        c4c.setText("");
			        if(evt.getSource().equals(btnr1c4)){
			        	linea1 = new Recta(a, b, c);
			        	ecq1.setText(linea1.ecuacionRecta());
			        }else{
			        	linea2 = new Recta(a, b, c);
			        	ecq2.setText(linea2.ecuacionRecta());
			        }
			    } else if (evt.getSource().equals(btnr1c5) || evt.getSource().equals(btnr2c5)) {
			    	double x, y;
					boolean eje = false
					if(c5Abcisa.getText().equals("") && c5Ordenada.getText().equals("")) return;
			    	if(c5Abcisa.getText().equals("")){
			    		x = 0;		
			    	}else x = Double.parseDouble(c5Abcisa.getText());
			    	if(c5Ordenada.getText().equals("")){
			    		y = 0;
			    	}else y = Double.parseDouble(c5Ordenada.getText());
					if(x==0) eje = true;
			    	Punto p = new Punto(x, y);
			    	c5Abcisa.setText("");
			        c5Ordenada.setText("");
			        if(evt.getSource().equals(btnr1c5)){
			        	linea1 = new Recta(p, eje);
			        	ecq1.setText(linea1.ecuacionRecta());
			        }else{
			        	linea2 = new Recta(p, eje);
			        	ecq2.setText(linea2.ecuacionRecta());
			        }
			    }
		        intersectan();
	    	}catch(Exception e){}
	    }

		private void intersectan() {
			if(linea1 != null && linea2 != null){
				if(Recta.sonParalelas(linea1, linea2)) pCorte.setText("Lineas paralelas");
				else pCorte.setText("Se cortan en " + Recta.puntoCorte(linea1, linea2).toString());
			}
			
		}
	}

}
