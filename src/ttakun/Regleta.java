package Txalaparta;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class Regleta extends JComponent {
	private int incremento; // Milisegundos
	private int distanciaEntreNumeros; // Pixeles
	private int maxValor; // Milisegundos
	private int mousePos;
	private Dimension dim;
	private int alto;
	private int minIncremento = 10;
	public Color cFondo = Color.red;
	private int offsetX = 100;

	private int oldIncremento = 0;
	private int oldDistanciaEntreNumeros = 0;
	private int oldMaxValor = 0;
	private BufferedImage oldRegleta;

	public Regleta() {
		incremento = 500;
		distanciaEntreNumeros = 50;
		maxValor = 5000;
		alto = 50;
		float ratio = maxValor / incremento;
		dim = new Dimension((int) (ratio * distanciaEntreNumeros), alto);
		this.setBorder(BorderFactory.createEtchedBorder());
	}

	public Regleta(int duracion) {
		incremento = 500;
		distanciaEntreNumeros = 50;
		maxValor = duracion;
		alto = 50;
		dim = new Dimension(timeToPixel(duracion), alto);
	}

	public void setOffset(int valor) {
		offsetX = valor;
	}

	public void paintComponent(Graphics g) {
		dibujar(g);
	}

	public int pixelToTime(int pos) {
		float ratio = (float) (pos - offsetX) * incremento;
		int result = (int) Math.floor((ratio / distanciaEntreNumeros));
		return result;
	}

	public int redondeo(int numero, int multiplo) {
		double r = (double) numero / multiplo;
		return (int) Math.round(r) * multiplo;
	}

	public int timeToPixel(int time) {
		float ratio = (float) time * distanciaEntreNumeros;
		int result = (int) Math.floor((ratio / incremento));
		return result + offsetX;
	}

	public int getIncremento() {
		return incremento;
	}

	public void setIncremento(int incremento) {
		this.incremento = incremento;
		updateDim();
	}

	public int getDistanciaEntreNumeros() {
		return distanciaEntreNumeros;
	}

	public void setDistanciaEntreNumeros(int distanciaEntreNumeros) {
		this.distanciaEntreNumeros = distanciaEntreNumeros;
		updateDim();
	}

	public int getHeight() {
		return alto;
	}

	public int getMaxValor() {
		return maxValor;
	}

	public void setMaxValor(int maxValor) {
		this.maxValor = maxValor;
		updateDim();
	}

	public int getMaxPixel() {
		return timeToPixel(maxValor);
	}

	public int getWidth() {
		return dim.width + 1;
	}

	private void updateDim() {
		dim.width = timeToPixel(maxValor);
		this.setPreferredSize(dim);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}

	public void dibujar(Graphics gCanvas) {
		FontMetrics font = gCanvas.getFontMetrics();
		int val = 0;
		final int lineaIni = dim.height / 2;
		final int textPos = dim.height / 2;
		int anchoVentana = java.awt.GraphicsEnvironment
				.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
		anchoVentana = Math.max(anchoVentana, dim.width);
		if (incremento != oldIncremento || maxValor != oldMaxValor
				|| distanciaEntreNumeros != oldDistanciaEntreNumeros) {
			oldRegleta = new BufferedImage(anchoVentana, dim.height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = oldRegleta.getGraphics();

			g.setColor(cFondo);
			g.fillRect(offsetX, 0, anchoVentana, dim.height);
			g.setColor(Color.black);
			g.drawRect(offsetX, 0, anchoVentana, dim.height);

			for (int i = offsetX; i <= anchoVentana; i += distanciaEntreNumeros) {
				g.drawLine(i, lineaIni, i, dim.height);
				String texto = (double) val / 1000 + "";
				g.drawString(texto, i - font.stringWidth(texto) / 2, textPos);
				val += incremento;
			}
			oldIncremento = incremento;
			oldMaxValor = maxValor;
			oldDistanciaEntreNumeros = distanciaEntreNumeros;
		}
		((Graphics2D) gCanvas).drawImage(oldRegleta, null, 0, 0);
		gCanvas.setColor(Color.orange);
		gCanvas.drawLine(mousePos, dim.height - 50, mousePos, lineaIni);
		String texto = (double) pixelToTime(mousePos) / 1000 + "";
		gCanvas.drawString(texto, mousePos - font.stringWidth(texto) / 2,
				textPos + 10);
	}

	public void setMousePos(int pos) {
		mousePos = pos;

	}

	public void zoomIn(int factor) {
		if (incremento != minIncremento) {
			distanciaEntreNumeros = distanciaEntreNumeros + 10;
			if (distanciaEntreNumeros >= 200) {
				distanciaEntreNumeros = distanciaEntreNumeros - 100;
				incremento = incremento / 2;
				incremento = incremento - incremento % 5;
				incremento = Math.max(incremento, minIncremento);

			}
		}
		updateDim();

	}

	public void zoomOut(int factor) {
		distanciaEntreNumeros = distanciaEntreNumeros - 10;
		if (distanciaEntreNumeros <= 50) {
			distanciaEntreNumeros = distanciaEntreNumeros + 50;
			incremento = incremento * 2;
			incremento = incremento - incremento % 5;
		}
		updateDim();

	}

	public void ajustarAncho(int pixeles) {
		distanciaEntreNumeros = 50;
		int numPasos = pixeles / distanciaEntreNumeros;
		int nuevoIncremento = maxValor / numPasos;
		int redondeo = 10;
		while (maxValor / redondeo > redondeo * 10) {
			redondeo *= 10;
		}
		nuevoIncremento = (nuevoIncremento + redondeo) - nuevoIncremento
				% redondeo;
		setIncremento(nuevoIncremento);

		float numPasos2 = maxValor / incremento;
		distanciaEntreNumeros = (int) ((pixeles) / numPasos2);
		updateDim();
	}

}
