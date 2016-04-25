package Txalaparta;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class PrintCanvas extends JFrame {
	private String titulo;
	private int golpesCompas; // Cantidad de golpes que forman un compas
	public String observaciones;
	private int tiempoMinEntreGolpes; // Cantidad de ms minima entre golpe y
										// golpe (gramaje)
	private int posIniFilas; // Altura inicial de la primera fila de cada página
	private int distanciaFilas; // Distancia entre una fila y la siguiente
	public int offsetFilas = 75;
	private int posYNumeros; // Ofset de los numeros respecto a la posicion de
								// la linea
	public int compasesPorFila; // Cantidad de compases que tiene una fila
	private int margen = 30; // Margen en el ejeX a dejar tanto por la izq como
								// por la dch
	private int altura = 25; // Altura maxima que puede tener cada golpe en la
								// representacion
	private int numFilas; // Cantidad de filas que tiene la partitura
	private ArrayList<Golpe> listaGolpes; // Lista de golpes de la partitura
	private Point sizePagina = new Point(595, 842); // Ancho y alto de una
													// página
	private int filasPorPagina; // Cantidad de filas por página
								// (sizePagina.y-posIniFilas)/distanciaFilas
	private int numPaginas; // Cantidad de paginas de la partitura
	public boolean dibujarNumeros = true; // Indica si se quiere o no dibujar el
											// numero de tablon de cada golpe

	public PrintCanvas(ArrayList<Golpe> lista, int gramaje,
			int golpesPorCompas, int duracion) {
		init(lista, gramaje, golpesPorCompas, 70, 80, 40, 5, duracion);
	}

	private void init(ArrayList<Golpe> lista, int gramaje, int golpesPorCompas,
			int posIniFilas, int distanciaFilas, int posYNumeros,
			int compasesPorFila, int duracion) {
		listaGolpes = lista;
		tiempoMinEntreGolpes = gramaje; // Cantidad de ms minima entre golpe y
										// golpe
		golpesCompas = golpesPorCompas; // Cantidad de golpes que forman un
										// compas
		this.posYNumeros = posYNumeros; // Ofset de los numeros respecto a la
										// posicion de la linea
		this.distanciaFilas = distanciaFilas; // Distancia entre una fila y la
												// siguiente
		this.compasesPorFila = compasesPorFila; // Cantidad de compases que
												// tiene una fila
		this.posIniFilas = posIniFilas; // Altura inicial de la primera fila de
										// cada página
		numFilas = (int) Math.ceil((double) duracion / tiempoMinEntreGolpes
				/ golpesPorCompas / compasesPorFila); // Cantidad de filas que
														// contiene la partitura

		this.filasPorPagina = (int) Math
				.floor((this.sizePagina.y - posIniFilas) / this.distanciaFilas);
		this.numPaginas = (int) Math.ceil(this.numFilas / this.filasPorPagina);

		this.setSize(sizePagina.x, numPaginas * sizePagina.y);
	}

	public void dibujar(String titulo) {
		this.setVisible(true);
		this.setBackground(Color.white);
		this.titulo = titulo;
		BufferedImage img = new BufferedImage(this.sizePagina.x,
				(1 + this.numPaginas) * this.sizePagina.y,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D auxG = img.createGraphics();
		paintImagen(auxG, img.getWidth(), img.getHeight());
		auxG.dispose();
		try {
			for (int i = 0; i <= this.numPaginas; i++) {
				BufferedImage imgAux = img.getSubimage(0,
						i * this.sizePagina.y, this.sizePagina.x,
						this.sizePagina.y);
				Graphics2D gAux = imgAux.createGraphics();
				gAux.setColor(Color.black);
				gAux.drawString("" + (i + 1), this.sizePagina.x / 2,
						this.sizePagina.y - 20);
				gAux.dispose();
				ImageIO.write(imgAux, "png", new File(titulo + " (" + i
						+ ").png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(false);
	}

	public void dibujar(String path, String titulo, ListaTablones listaTablones,
			boolean dibujarAgrupados) {
		if (dibujarAgrupados) {
			this.distanciaFilas = 2 * listaTablones.pixelesEntreTablones
					+ offsetFilas;
			this.posYNumeros = 2 * listaTablones.pixelesEntreTablones;
		} else {
			this.distanciaFilas = (listaTablones.listaTablones.size() + 1)
					* listaTablones.pixelesEntreTablones + offsetFilas;
			this.posYNumeros = (listaTablones.listaTablones.size() + 1)
					* listaTablones.pixelesEntreTablones;
		}

		this.filasPorPagina = (int) Math
				.floor((this.sizePagina.y - posIniFilas) / this.distanciaFilas);
		this.setVisible(true);
		this.setBackground(Color.white);
		this.titulo = titulo;
		BufferedImage img = new BufferedImage(this.sizePagina.x,
				(1 + this.numPaginas) * this.sizePagina.y,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D auxG = img.createGraphics();
		if (dibujarAgrupados)
			paintImagen(auxG, img.getWidth(), img.getHeight());
		else
			paintImagen(auxG, img.getWidth(), img.getHeight(), listaTablones);
		auxG.dispose();
		try {
			new File(path + "/" + titulo + " (" + 0 + ").png")
					.getParentFile().mkdirs();
			for (int i = 0; i <= this.numPaginas; i++) {
				BufferedImage imgAux = img.getSubimage(0,
						i * this.sizePagina.y, this.sizePagina.x,
						this.sizePagina.y);
				Graphics2D gAux = imgAux.createGraphics();
				gAux.setColor(Color.black);
				gAux.drawString("" + (i + 1), this.sizePagina.x / 2,
						this.sizePagina.y - 20);
				gAux.dispose();

				ImageIO.write(imgAux, "png", new File(path + "/" + titulo + " (" + i + ").png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(false);
	}

	public void paintImagen(Graphics g, int width, int height) {
		// TODO Auto-generated method stub
		// super.paint(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		// Dibujamos el titulo
		Font auxF = g.getFont();
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.setColor(Color.black);

		g.drawString(titulo, (width / 2)
				- (int) (g.getFontMetrics().getStringBounds(titulo, g)
						.getWidth() / 2), 60);
		g.setFont(auxF);

		int posAct = this.posIniFilas;
		int pagAct = 0;
		// Dibujamos las observaciones
		String[] filasObservaciones = observaciones.split("\n");
		for (int i = 0; i < filasObservaciones.length; i++) {
			posAct += g.getFontMetrics().getHeight();
			g.drawString(filasObservaciones[i], margen, posAct);
		}

		posAct += this.altura + 20;

		int offsetPrimeraPagina = posAct;

		// Dibujamos las lineas
		for (int i = 0; i < numFilas; i++) {
			if (pagAct != (int) Math.floor(i / filasPorPagina)) {
				pagAct++;
				posAct = posIniFilas + sizePagina.y * pagAct;
			}

			dibujarLinea(g, posAct, this.altura);
			posAct += this.distanciaFilas;
		}

		// Dibujamos los golpes
		for (int i = 0; i < listaGolpes.size(); i++) {
			dibujarGolpe(g, listaGolpes.get(i), this.altura,
					offsetPrimeraPagina);
		}

	}

	public void paintImagen(Graphics g, int width, int height,
			ListaTablones listaTablones) {
		// TODO Auto-generated method stub
		// super.paint(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		// Dibujamos el titulo
		Font auxF = g.getFont();
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.setColor(Color.black);

		g.drawString(titulo, (width / 2)
				- (int) (g.getFontMetrics().getStringBounds(titulo, g)
						.getWidth() / 2), 60);
		g.setFont(auxF);

		int posAct = this.posIniFilas;
		int pagAct = 0;
		// Dibujamos las observaciones
		String[] filasObservaciones = observaciones.split("\n");
		for (int i = 0; i < filasObservaciones.length; i++) {
			posAct += g.getFontMetrics().getHeight();
			g.drawString(filasObservaciones[i], margen, posAct);
		}

		posAct += this.altura + 20;

		int offsetPrimeraPagina = posAct;

		// Dibujamos las lineas
		for (int i = 0; i < numFilas; i++) {
			if (pagAct != (int) Math.floor(i / filasPorPagina)) {
				pagAct++;
				posAct = posIniFilas + sizePagina.y * pagAct;
			}
			for (int k = 0; k < listaTablones.listaTablones.size(); k++) {

				dibujarLinea(
						g,
						posAct
								+ listaTablones
										.getPosRelativaTablon(listaTablones.listaTablones
												.get(k).getId()), this.altura,
						listaTablones.listaTablones.get(k).getId() + "");
			}
			posAct += this.distanciaFilas;
		}

		// Dibujamos los golpes
		for (int i = 0; i < listaGolpes.size(); i++) {
			dibujarGolpe(
					g,
					listaGolpes.get(i),
					20,
					offsetPrimeraPagina,
					listaTablones.getPosRelativaTablon(listaGolpes.get(i).idTablon));
		}

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// super.paint(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// Dibujamos el titulo
		Font auxF = g.getFont();
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.setColor(Color.black);
		g.drawString(titulo, (this.getWidth() / 2) - (titulo.length() * 10), 60);
		g.setFont(auxF);

		// Dibujamos las lineas
		int posAct = this.posIniFilas;
		int pagAct = 0;
		for (int i = 0; i < numFilas; i++) {
			if (pagAct != (int) Math.floor(i / filasPorPagina)) {
				pagAct++;
				posAct = posIniFilas + sizePagina.y * pagAct;
			}

			dibujarLinea(g, posAct, this.altura);
			posAct += this.distanciaFilas;
		}

		// Dibujamos los golpes
		for (int i = 0; i < listaGolpes.size(); i++) {
			dibujarGolpe(g, listaGolpes.get(i), this.altura, this.posIniFilas);
		}

	}

	private void dibujarLinea(Graphics g, int posY, int alto) {
		// Graphics2D gAux = (Graphics2D)g;
		// g.setColor(Color.black);
		// g.drawLine(margen, posY, this.getWidth()-margen, posY);
		// int sizeCompas = (this.getWidth()-2*margen)/(compasesPorFila);
		// int sizeGolpe = sizeCompas/golpesCompas;
		// Stroke aux = gAux.getStroke();
		// //Dibujamos el gramaje
		// for(int i =0; i < golpesCompas* compasesPorFila ;i++){
		// int posGolpe = i;
		// int compas = (int) Math.floor(posGolpe/golpesCompas);
		// int golpeDentroCompas = posGolpe%golpesCompas;
		// int compasDentroFila = compas%compasesPorFila;
		// int posX = sizeCompas* compasDentroFila + sizeGolpe*golpeDentroCompas
		// + margen;
		//
		// if(i % golpesCompas == 0 ) {
		// gAux.setColor(Color.black);
		// gAux.setStroke(PanelSonido.dashed);
		// gAux.drawLine(posX, posY-alto, posX, posY+alto);
		// gAux.setStroke(aux);
		// }
		// else {
		// gAux.setColor(new Color(0,0,255,50));
		// gAux.drawLine(posX, posY-5, posX, posY+5);
		// }
		//
		// }
		dibujarLinea(g, posY, alto, "");
	}

	/**
	 * Dibuja la linea que representa un tablon y su gramaje
	 * @param g
	 * @param posY
	 * @param alto
	 * @param nombre
	 */
	private void dibujarLinea(Graphics g, int posY, int alto, String nombre) {
		Graphics2D gAux = (Graphics2D) g;
		g.setColor(Color.black);
		g.drawLine(margen, posY, this.getWidth() - margen, posY);
		float sizeCompas = (this.getWidth() - 2 * margen) / (compasesPorFila);
		float sizeGolpe = sizeCompas / golpesCompas;
		Stroke aux = gAux.getStroke();
		// Dibujamos el gramaje
		for (int i = 0; i < golpesCompas * compasesPorFila; i++) {
			int posGolpe = i;
			int compas = (int) Math.floor(posGolpe / golpesCompas);
			int golpeDentroCompas = posGolpe % golpesCompas;
			int compasDentroFila = compas % compasesPorFila;
			float posX = sizeCompas * compasDentroFila + sizeGolpe
					* golpeDentroCompas + margen;

			if (i % golpesCompas == 0) {
				gAux.setColor(Color.black);
				gAux.setStroke(PanelSonido.dashed);
				gAux.drawLine((int)posX, posY - alto, (int) posX, posY + alto);
				gAux.setStroke(aux);
			} else {
				gAux.setColor(new Color(0, 0, 255, 50));
				gAux.drawLine((int)posX, posY - 5, (int) posX, posY + 5);
			}

			gAux.drawString(nombre, 20, posY);

		}

	}

	private void dibujarGolpe(Graphics g, Golpe golpe, int alto,
			int offsetPrimeraPagina) {
		// Graphics2D gAux = (Graphics2D) g;
		// gAux.setStroke(new BasicStroke(3));
		// int posGolpe = golpe.momento/this.tiempoMinEntreGolpes;
		// int compas = (int) Math.floor(posGolpe/golpesCompas);
		// int golpeDentroCompas = posGolpe%golpesCompas;
		// int fila = (int) Math.floor(compas/this.compasesPorFila);
		// int compasDentroFila = compas%compasesPorFila;
		// int sizeCompas = (this.getWidth()-2*margen)/(compasesPorFila);
		// int sizeGolpe = sizeCompas/golpesCompas;
		// int posX = sizeCompas* compasDentroFila + sizeGolpe*golpeDentroCompas
		// + margen;
		// int filaDentroPagina = fila%filasPorPagina;
		// int pagina = (int) Math.floor(fila/this.filasPorPagina);
		// int posY;
		// if(pagina == 0) posY =
		// offsetPrimeraPagina+filaDentroPagina*this.distanciaFilas +
		// pagina*sizePagina.y;
		// else posY = this.posIniFilas+filaDentroPagina*this.distanciaFilas +
		// pagina*sizePagina.y;
		// g.setColor(golpe.cNormal);
		// if(golpe.dibujarHaciaArriba)gAux.drawLine(posX, posY, posX,
		// posY-(alto * golpe.fuerza/100));
		// else gAux.drawLine(posX, posY, posX, posY+(alto* golpe.fuerza/100));
		// g.drawString(golpe.idTablon+ "", posX, posY+ posYNumeros);
		dibujarGolpe(g, golpe, alto, offsetPrimeraPagina, 0);
	}

	private void dibujarGolpe(Graphics g, Golpe golpe, int alto,
			int offsetPrimeraPagina, int offsetTablon) {
		Graphics2D gAux = (Graphics2D) g;
		gAux.setStroke(new BasicStroke(3));
		float posGolpe = (float)golpe.momento / this.tiempoMinEntreGolpes;
		float offsetGolpe = posGolpe - (int) posGolpe; //Para golpes que no estan ajustados al gramaje
		int compas = (int) Math.floor(posGolpe / golpesCompas);
		int golpeDentroCompas =(int) posGolpe % golpesCompas;
		int fila = (int) Math.floor(compas / this.compasesPorFila);
		int compasDentroFila = compas % compasesPorFila;
		float sizeCompas = (this.getWidth() - 2 * margen) / (compasesPorFila);
		float sizeGolpe = sizeCompas / golpesCompas;
		float posX = sizeCompas * compasDentroFila + sizeGolpe
				* (golpeDentroCompas + offsetGolpe) + margen;
		int filaDentroPagina = fila % filasPorPagina;
		int pagina = (int) Math.floor(fila / this.filasPorPagina);
		int posY;
		if (pagina == 0)
			posY = offsetPrimeraPagina + filaDentroPagina * this.distanciaFilas
					+ pagina * sizePagina.y;
		else
			posY = this.posIniFilas + filaDentroPagina * this.distanciaFilas
					+ pagina * sizePagina.y;
		int auxPosY = posY;
		posY += offsetTablon;
		g.setColor(golpe.cNormal);
		if (golpe.dibujarHaciaArriba)
			gAux.drawLine((int)posX, posY, (int)posX, posY - (alto * golpe.fuerza / 100));
		else
			gAux.drawLine((int)posX, posY, (int)posX, posY + (alto * golpe.fuerza / 100));
		if (dibujarNumeros)
			g.drawString(golpe.idTablon + "", (int)posX-2, auxPosY + posYNumeros);
	}

	public BufferedImage crearImagen(String nombre, String extension) {

		int w = this.getWidth();
		int h = this.getHeight();
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		this.paint(g);
		try {
			ImageIO.write(bi, extension, new File(nombre + "." + extension));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}
	
	public void recalcularFilasPaginas(int duracion, int golpesPorCompas){
		numFilas = (int) Math.ceil((double) duracion / tiempoMinEntreGolpes
				/ golpesPorCompas / compasesPorFila); // Cantidad de filas que
														// contiene la partitura
		this.filasPorPagina = (int) Math
				.floor((this.sizePagina.y - posIniFilas) / this.distanciaFilas);
		this.numPaginas = (int) Math.ceil(this.numFilas / this.filasPorPagina);
	
	}

}
