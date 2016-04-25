package Txalaparta;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Partitura {
	private Parser parser;
	private int offsetX, offsetY;
	private ListaMusicos listaMusicos;
	private Dimension dim;
	private ListaTablones listaTablones;
	public Color cFondo = Color.white;
	private ListaOrdenadaGolpes listaGolpes;
	public Trazabilidad trazabilidad;
	private boolean modoLinea = true;
	public int duracion = 0;
	public Editor padre;

	public Partitura(Editor editor, int xOffset, int yOffset, Dimension d) {
		offsetX = xOffset;
		offsetY = yOffset;
		dim = new Dimension(d.width, d.height);
		listaTablones = new ListaTablones(150);
		listaGolpes = new ListaOrdenadaGolpes();
		trazabilidad = new Trazabilidad();
		padre = editor;
	}

	public Partitura(Editor editor, int xOffset, int yOffset, Dimension d,
			ArrayList<Golpe> listaG) {
		offsetX = xOffset;
		offsetY = yOffset;
		dim = new Dimension(d.width, d.height);
		listaTablones = new ListaTablones(150);
		listaGolpes = new ListaOrdenadaGolpes(listaG);
		trazabilidad = new Trazabilidad();
		padre = editor;
	}

	public void setListaMusicos(ListaMusicos l) {
		listaMusicos = l;
	}

	public void dibujar(Graphics g, Regleta regleta) {
		listaTablones.dibujar(g, offsetX, regleta.getMaxPixel() - offsetX);
		listaGolpes.dibujar(g, regleta, listaTablones);

	}

	public Tablon addTablon() {
		if (listaTablones.size() > 0) {
			trazabilidad.addTraza(null, listaTablones, null);
		}
		return listaTablones.addTablon();

	}

	public void addTablon(Tablon t) {
		// TODO Auto-generated method stub
		listaTablones.addTablon(t);
	}

	public void removeTablonSeleccionado() {
		if (listaTablones.numTablones() > 1) {
			trazabilidad.addTraza(listaGolpes, listaTablones, null);
			int idTablon = listaTablones.getTablon().getId();
			listaGolpes.eliminarGolpesTablon(idTablon);
			listaTablones.removeTablon();
			listaTablones.actualizarBotones();
		}
	}

	public void addGolpe(int momento, int y, int fuerza) {

		addGolpe(momento, y, fuerza, false);
	}

	public void addGolpe(int momento, int y, int fuerza, boolean sustituir) {

		trazabilidad.addTraza(listaGolpes, null, null);
		Tablon tSeleccionado = listaTablones.getTablon();
		Musico mSeleccionado = listaMusicos.getMusicoSeleccionado();
		if (listaMusicos.getNumMusicos() == 2) {
			if (tSeleccionado.posY < y)
				mSeleccionado = listaMusicos.getMusicoByPos(1);
			else
				mSeleccionado = listaMusicos.getMusicoByPos(0);
		}
		Golpe g = new Golpe(mSeleccionado.getId(), tSeleccionado.getId(),
				momento, tSeleccionado.posY > y, modoLinea,
				mSeleccionado.getColor());
		if (fuerza != y) {
			g.setFuerza(fuerza);
		} else {
			g.setFuerza(Math.abs(y - tSeleccionado.posY));
		}

		listaGolpes.addGolpe(g, sustituir);
	}

	public void setWidth(int value) {
		dim.width = value;
	}

	public void setHeight(int value) {
		dim.height = value;
	}

	public void siguienteTablon() {
		listaTablones.seleccionarSiguiente();
	}

	public void tablonAnterior() {
		listaTablones.seleccionarAnterior();
	}

	public void seleccionarTablon(int id) {
		listaTablones.seleccionarId(id);
	}

	// public void reproducir(int porcentaje, ListaMusicos musicos) {
	// listaGolpes.reproducirGolpes(listaTablones, porcentaje, musicos);
	// }

	// public Reproducir[] getReproductores(int porcentaje, ListaMusicos
	// musicos){
	// return listaGolpes.getListaReproducir(listaTablones, porcentaje,
	// musicos);
	// }

	// public void pararReproduccion(){
	// listaGolpes.pararReproduccion();
	// }

	public void deshacer() {
		trazabilidad.deshacer(this);
		listaTablones.actualizarBotones();
	}

	public void rehacer() {
		trazabilidad.rehacer(this);
		listaTablones.actualizarBotones();
	}

	public int subirTablonSeleccionado() {

		return listaTablones.subirTablonSeleccionado();
	}

	public int bajarTablonSeleccionado() {
		return listaTablones.bajarTablonSeleccionado();
	}

	public int bajarTablonId(int id) {
		int salida = listaTablones.bajarTablonId(id);
		listaTablones.actualizarBotones();
		return salida;
	}

	public int subirTablonId(int id) {
		int salida = listaTablones.subirTablonId(id);
		listaTablones.actualizarBotones();
		return salida;
	}

	public void cambiarColorTablonId(int id) {
		trazabilidad.addTraza(null, listaTablones, null);
		listaTablones.cambiarColor(id);
	}

	public void cambiarTonoTablonId(int id) {
		trazabilidad.addTraza(null, listaTablones, null);
		listaTablones.cambiarTono(id);
	}

	public void seleccionar(int tipo, Point tiempoInicio, Point tiempoFin) {
		listaGolpes.seleccionar(tipo, tiempoInicio, tiempoFin, listaTablones);
	}

	public void desSeleccionar() {
		listaGolpes.desSeleccionar();
	}

	public void eliminarGolpesSeleccionados() {
		trazabilidad.addTraza(listaGolpes, null, null);
		ArrayList<Golpe> seleccionados = listaGolpes.getSeleccionados();
		if (!seleccionados.isEmpty()) {
			trazabilidad.addTraza(listaGolpes, listaTablones, listaMusicos);
			listaGolpes.eliminarGolpes(seleccionados);

		}
	}

	public void moverGolpesSeleccionados(int tiempo) {
		listaGolpes.moverSeleccionados(tiempo);
	}

	public void comienzoMoverGolpesSeleccionados() {
		trazabilidad.addTraza(listaGolpes, null, null);
	}

	public void finMoverGolpesSeleccionados(int cantTiempo) {

		if (cantTiempo == 0)
			trazabilidad.eliminarTraza();

	}

	public void setModoLinea(boolean activado) {
		modoLinea = activado;
		listaTablones.setModoLinea(activado);
		listaGolpes.setModoLinea(activado);
	}

	public Tablon getTablonSeleccionado() {
		return listaTablones.getTablon();

	}

	public void cambiarTonoTablon() {
		trazabilidad.addTraza(null, listaTablones, null);
		listaTablones.getTablon().cambiarTono();
	}

	public void cambiarColorTablon() {
		trazabilidad.addTraza(null, listaTablones, null);
		listaTablones.getTablon().cambiarColor();

	}

	public void checkGolpes(int d) {
		listaGolpes.checkGolpes(d);

	}

	public void duplicateSelected() {
		trazabilidad.addTraza(listaGolpes, null, null);
		if (listaGolpes.getSeleccionados().size() > 0) {
			listaGolpes.duplicateSelected();
		}

	}

	public void selectAll() {
		listaGolpes.selectAll();

	}

	public ListaMusicos getListaMusicos() {
		return listaMusicos;
	}

	public ListaOrdenadaGolpes getListaGolpes() {
		return listaGolpes;
	}

	public ListaTablones getListaTablones() {
		return listaTablones;
	}
	
	public void eliminarGolpesDuplicados(){
		listaGolpes.elminarDuplicados();
	}

	public void save(String path, String observaciones, int gramaje, int ritmo) {
		listaGolpes.elminarDuplicados();
		//System.out.println(path);
		parser = new Parser();
		parser.beginWriteTransaction(path);
		parser.writeDuracion(this.duracion);
		ArrayList<Tablon> listaT = listaTablones.getCopiaListaTablones();
		for (int i = 0; i < listaT.size(); i++) {
			parser.writeTablon(listaT.get(i));
		}

		ArrayList<Musico> listaM = listaMusicos.getCopiaListaMusicos();
		for (int i = 0; i < listaM.size(); i++) {
			parser.writeMusico(listaM.get(i));
		}

		ArrayList<Golpe> listaG = listaGolpes.getCopiaListaGolpes();
		for (int i = 0; i < listaG.size(); i++) {
			parser.writeGolpe(listaG.get(i));
		}

		parser.writeObservaciones(observaciones);
		parser.writeGramaje(gramaje);
		parser.writeRitmo(ritmo);
		parser.endWriteTransaction();
	}

	public void setBotones(PanelBotonesTablon b) {
		listaTablones.setBotones(b);

	}

	public void actualizarBotones() {
		listaTablones.actualizarBotones();

	}

	public void redondearGolpes(int valor) {
		listaGolpes.redondearA(valor);
	}

	public void cambiarColorMusico(Musico musico) {
		listaGolpes.cambiarColor(musico);

	}

	public void setDuracion(int valor) {
		trazabilidad.addTraza(listaGolpes, listaTablones, null, duracion);
		duracion = valor;
	}

	public void tocarTablon(int id) {
		for (int i = 0; i < listaTablones.listaTablones.size(); i++) {
			Tablon t = listaTablones.listaTablones.get(i);
			if (t.getId() == id) {
				t.golpear(100);

			}
		}

	}

	public int getAlto() {
		return listaTablones.getAlto();
	}

	public void normalizarTablones() {
		listaTablones.normalizarPosiciones();
	}

}
