package Txalaparta;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

public class Tablon {
	private File tono;
	private Color cDefault = Color.blue;
	private Color color = Color.blue;
	private String defaultDir = "./hotsak";
	public int alto = 40;
	private int id;
	public int posY;
	private BotonTablon bUp, bDown, bColor;
	public boolean linea = true;
	public boolean selected = false;
	float ratioFuerzaVolumen = -0.15F;
	byte[] sonidoPrecargado;
	AudioFormat formato;
	private static final float maxVolumen = (float) 6.0206;
	private static final float minVolumen = (float) -20.0;

	private void preCargarClip(File sourceFile) {
		SoundManager.getInstance().addSonido(sourceFile);
	}

	/**
	 * Abre un JFileChooser para elegir el archivo de musica con el tono.
	 */
	public Tablon(int tablonId, int yPos, int grosor, boolean modoLinea) {
		id = tablonId;
		linea = modoLinea;
		posY = yPos;
		alto = grosor;
		JFileChooser openF = ChooserManager.getInstance().soundChooser;
		if (openF.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			tono = openF.getSelectedFile();
		if (tono == null) {
			tono = new File(Configuracion.getInstance().sonidoDefault);
		}
		preCargarClip(tono);
		color = cDefault;

	}

	public Tablon(Tablon tablon) {
		this.tono = tablon.tono;
		this.color = tablon.color;
		this.id = tablon.id;
		this.cDefault = tablon.cDefault;
		this.defaultDir = tablon.defaultDir;
		this.alto = tablon.alto;
		this.posY = tablon.posY;
		this.bUp = tablon.bUp;
		this.bDown = tablon.bDown;
		this.bColor = tablon.bColor;
		this.linea = tablon.linea;
		this.selected = tablon.selected;
		this.ratioFuerzaVolumen = tablon.ratioFuerzaVolumen;
	}

	public Tablon(int idTablon, Color color, String tono, int posY, int grosor) {
		this.id = idTablon;
		this.cDefault = color;
		this.color = color;
		this.tono = new File(tono);
		this.posY = posY;
		this.alto = grosor;
		preCargarClip(this.tono);
	}

	/**
	 * Cambia el color con el que sera dibujado el tablon
	 * 
	 * @param value
	 *            Color con el que queremos que se dibuje
	 */
	public void seleccionar() {
		color = Color.green;
		selected = true;
	}

	public void desSeleccionar() {
		color = cDefault;
		selected = false;
	}

	/**
	 * Reproduce el sonido almacenado
	 * 
	 * @param fuerza
	 *            fuerza del golpe
	 */

	public void golpear(int fuerza) {
		float volumen = Math.min((100 - fuerza) * ratioFuerzaVolumen,
				maxVolumen);
		SoundManager.getInstance().sonar(tono, volumen);
	}

	/**
	 * Abre un JFileChooser para elegir el archivo de musica con el tono.
	 */
	public void cambiarTono() {
		JFileChooser openF = ChooserManager.getInstance().soundChooser;
		if (openF.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
			return;

		File fileSelected = openF.getSelectedFile();
		if (fileSelected != null) {
			tono = fileSelected;
			preCargarClip(tono);
		}
	}

	/**
	 * Dibuja el tablon en las coordenadas deseadas del buffer dado
	 * 
	 * @param g
	 *            Buffer en el que se va ha dibujar el tablon
	 * @param x
	 *            Coordenada x en la que comienza el tablon
	 * @param y
	 *            Coordenada y en la que comienza el tablon
	 * @param w
	 *            Ancho en pixeles del tablon
	 */
	public void dibujar(Graphics g, int x, int w) {
		dibujar(g, x, w, posY);
	}

	public void dibujar(Graphics g, int x, int w, int y) {
		if (!Configuracion.getInstance().resaltarTablones){
			g.setColor(cDefault);
			}else{
				g.setColor(color);
			}
		
		g.drawLine(x, y, x + w, y);
		if (!linea) {
			Color c = g.getColor();
			g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 120));
			g.fillRect(x, y - alto / 2, w, alto);
//			g.setColor(Color.black);
//			g.drawRect(x, y - alto / 2, w, alto);
		}

	}

	public void cambiarColor() {
		Color cSeleccionado = JColorChooser.showDialog(null,
				Menu.getInstance().listaAlerts.get(2).t, cDefault);
		if (cSeleccionado != null) {
			cDefault = cSeleccionado;
			if (!selected) {
				color = cDefault;
			}
		}
	}

	public int getId() {
		return id;
	}

	public Color getColor() {
		return this.cDefault;
	}

	public String getTono() {
		return this.tono.getPath();
	}

	public int getGrosor() {
		return this.alto;
	}

}
