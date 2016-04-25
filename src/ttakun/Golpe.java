package Txalaparta;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Golpe {
	public int idMusico, idTablon, momento, fuerza;
	public boolean seleccionado = false;
	public boolean dibujarHaciaArriba;
	public Color cSeleccionado = new Color(196, 191, 12);
	public Color cNormal = Color.black;
	private int maxSize = 100;
	public boolean linea = true;

	public Golpe(int musicId, int tablonId, int timeMoment,
			boolean haciaArriba, boolean modoLinea, Color c) {
		idMusico = musicId;
		idTablon = tablonId;
		momento = timeMoment;
		dibujarHaciaArriba = haciaArriba;
		linea = modoLinea;
		fuerza = 100;
		this.cNormal = c;
		if (!linea) {
			maxSize = 40;
		} else {
			maxSize = 100;
		}

	}

	public Golpe(Golpe g) {
		this.idMusico = g.idMusico;
		this.idTablon = g.idTablon;
		this.momento = g.momento;
		this.fuerza = g.fuerza;
		this.dibujarHaciaArriba = g.dibujarHaciaArriba;
		this.cNormal = g.cNormal;
		this.maxSize = g.maxSize;
		this.linea = g.linea;
	}

	public Golpe(int musicId, int tablonId, int timeMoment,
			boolean haciaArriba, boolean modoLinea, Color c, int fuerzaGolpe) {
		idMusico = musicId;
		idTablon = tablonId;
		momento = timeMoment;
		dibujarHaciaArriba = haciaArriba;
		linea = modoLinea;
		fuerza = fuerzaGolpe;
		this.cNormal = c;

		if (!linea) {
			maxSize = 40;
		} else {
			maxSize = 100;
		}

	}

	public void dibujar(Graphics g, int x, int posTablon) {
		int size = maxSize * fuerza / 100;
		if (seleccionado)
			g.setColor(cSeleccionado);
		else
			g.setColor(cNormal);
		if (linea) {
			if (dibujarHaciaArriba) {
				g.drawLine(x, posTablon, x, posTablon - size);
				if (seleccionado)
					g.drawString("" + momento, x, posTablon - size);
			} else {
				g.drawLine(x, posTablon, x, posTablon + size);
				if (seleccionado)
					g.drawString("" + momento, x, posTablon + size + 10);
			}
		} else {
			size = size / 2;
			if (dibujarHaciaArriba) {
				g.drawLine(x, posTablon, x, posTablon - size);
				if (seleccionado)
					g.drawString("" + momento, x, posTablon - size);
			} else {
				g.drawLine(x, posTablon, x, posTablon + size);
				if (seleccionado)
					g.drawString("" + momento, x, posTablon + size + 10);
			}
		}
	}

	public void seleccionar() {
		seleccionado = !seleccionado;
	}

	public void desseleccionar() {
		seleccionado = false;
	}

	public void setModoLinea(boolean activado) {
		linea = activado;
		if (activado) {
			maxSize = 100;
		} else {
			maxSize = 40;
		}
	}

	public boolean inSelection(Point ini, Point end, ListaTablones lista) {
		int posTablon = lista.getTablon(idTablon).posY;
		Point selectionMin = new Point(Math.min(ini.x, end.x), Math.min(ini.y,
				end.y));
		Point selectionMax = new Point(Math.max(ini.x, end.x), Math.max(ini.y,
				end.y));

		int minY, maxY;
		if (linea) {
			if (dibujarHaciaArriba) {
				minY = posTablon - maxSize;
				maxY = posTablon;
			} else {
				maxY = posTablon + maxSize;
				minY = posTablon;
			}

		} else {
			maxY = posTablon + maxSize / 2;
			minY = posTablon - maxSize / 2;
		}

		if (selectionMin.x <= momento && selectionMax.x >= momento) {
			if (selectionMin.y <= maxY && minY <= selectionMax.y) {
				return true;
			}
		}

		return false;
	}

	public void redondear(int multiplo) {
		double r = (double) momento / multiplo;
		momento = (int) Math.round(r) * multiplo;
	}

	public boolean getHaciaArriba() {
		// TODO Auto-generated method stub
		return this.dibujarHaciaArriba;
	}

	/**
	 * 
	 * @param valor
	 *            numero entre 0 y 100
	 */
	public void setFuerza(int valor) {
		fuerza = Math.min(valor, maxSize) * 100 / maxSize;
	}

}
