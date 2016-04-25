package Txalaparta;

import java.awt.Graphics;
import java.util.ArrayList;

public class ListaTablones {

	public ArrayList<Tablon> listaTablones;
	public int pixelesEntreTablones = 25; // Distancia entre tablones en pixeles
	public int posTablonCentral = 150; // Posicion del tablon central en pixeles
	private int idTablonActual = 1; // Id del ultimo tablon que se ha generado
	private int posTablonSeleccionado = 0; // Indice del tablon seleccionado
	private boolean modoLinea = true; // Modo de vision de la aplicacion
	private PanelBotonesTablon botones;
	private boolean recrearBotones = false; //Si se activa esta variable a la hora de dibujar se recrean los botones y se desactiva la variable.

	public ListaTablones(int PosTablonCentral) {
		listaTablones = new ArrayList<Tablon>();
		posTablonCentral = PosTablonCentral;
	}

	public ArrayList<Tablon> getCopiaListaTablones() {
		ArrayList<Tablon> aux = new ArrayList<Tablon>();
		for (int i = 0; i < listaTablones.size(); i++) {
			aux.add(new Tablon(listaTablones.get(i)));
		}
		return aux;
	}

	/**
	 * 
	 * @return Devuelve la posicion en la que deberia de ir un tablon nuevo
	 */
	private int getPosTablonNuevo() {
		//System.out.println(posTablonCentral
		//		+ (pixelesEntreTablones * (listaTablones.size() - 1)));
		return posTablonCentral
				+ (pixelesEntreTablones * (listaTablones.size() - 1));
	}

	/**
	 * Añade un tablon a la lista de tablones en la posicion correspondiente
	 */
	public Tablon addTablon() {
		int pos = getPosTablonNuevo();
		Tablon t = new Tablon(idTablonActual, pos, pixelesEntreTablones,
				modoLinea);
		if (listaTablones.size() == 0) {
			t.seleccionar();
		}else{
			posTablonSeleccionado++;
		}
		listaTablones.add(0,t);
		idTablonActual++;
		recrearBotones = true;
		return t;
	}

	public void addTablon(Tablon t) {
		if (listaTablones.size() == 0) {
			t.seleccionar();
		}else{
		 posTablonSeleccionado ++;
		}
		listaTablones.add(t);
		idTablonActual++;
		recrearBotones = true;
	}

	/**
	 * Elimina el tablon t de la lista
	 * 
	 * @param t
	 */
	public void removeTablon() {
		if (listaTablones.size() > 1) {
			listaTablones.remove(posTablonSeleccionado);
			seleccionarId(listaTablones.get(0).getId());
			recrearBotones = true;
		}
	}

	/**
	 * Elimina el tablon que esta en el pixel pos
	 * 
	 * @param pos
	 */
	public void removeTablon(int pos) {
		listaTablones.remove(pos);
		recrearBotones = true;
	}

	/**
	 * Dibuja la lista de tablones
	 * 
	 * @param g
	 * @param xOffset
	 *            Posicion de inicio
	 * @param width
	 *            Longitud del tablon
	 */
	public void dibujar(Graphics g, int xOffset, int width) {
		for (int i = 0; i < listaTablones.size(); i++) {
			listaTablones.get(i).posY =  posTablonCentral + i * pixelesEntreTablones;
			listaTablones.get(i).dibujar(g, xOffset, width);
		}
		if (recrearBotones) { 
		actualizarBotones();
		recrearBotones = false;
		}
	}

	public void dibujar(Graphics g, int xOffset, int width, int yOffset) {
		for (int i = 0; i < listaTablones.size(); i++) {
listaTablones.get(i).posY = yOffset + i * pixelesEntreTablones;
			listaTablones.get(i).dibujar(g, xOffset, width,
					yOffset + i * pixelesEntreTablones);
		}

	}

	public int getPosRelativaTablon(int idTablon) {
		for (int i = 0; i < listaTablones.size(); i++) {
			if (listaTablones.get(i).getId() == idTablon)
				return i * pixelesEntreTablones;
		}
		return -1;
	}

	/**
	 * Devuelve el talbon cuyo id corresponde con el indicado
	 * 
	 * @param id
	 *            Id del tablon que deseamos obtener
	 * @return
	 */
	public Tablon getTablon(int id) {
		for (int i = 0; i < listaTablones.size(); i++) {
			if (listaTablones.get(i).getId() == id)
				return listaTablones.get(i);
		}
		return listaTablones.get(0);
	}

	/**
	 * 
	 * @return Devuelve el tablon seleccionado
	 */
	public Tablon getTablon() {
		return listaTablones.get(posTablonSeleccionado);
	}

	/**
	 * 
	 * @return Devuelve la posicion que ocupa el tablon en la lista de tablones
	 */
	public int getPosTablonSeleccionado() {
		return posTablonSeleccionado;
	}

	/**
	 * Desselecciona el tablon seleccionado y selecciona el siguiente en la
	 * lista
	 */
	public void seleccionarSiguiente() {
		listaTablones.get(posTablonSeleccionado).desSeleccionar();
		
		if (posTablonSeleccionado == listaTablones.size()-1) posTablonSeleccionado = 0; else posTablonSeleccionado++;
		listaTablones.get(posTablonSeleccionado).seleccionar();
	}

	/**
	 * Desselecciona el tablon seleccionado y selecciona el siguiente en la
	 * lista
	 */
	public void seleccionarAnterior() {
		listaTablones.get(posTablonSeleccionado).desSeleccionar();
		if (posTablonSeleccionado == 0) posTablonSeleccionado = listaTablones.size()-1; else posTablonSeleccionado--;
		listaTablones.get(posTablonSeleccionado).seleccionar();
	}

	/**
	 * Intercambia la posicion del tablon seleccionado con el que esta
	 * inmediatamente superior en pixeles
	 */
	public int subirTablonSeleccionado() {
		int indexSuperior = posTablonSeleccionado;
		Tablon seleccionado = listaTablones.get(posTablonSeleccionado);
		if (indexSuperior < listaTablones.size()-1) {
			recrearBotones = true;
			listaTablones.remove(indexSuperior);
			listaTablones.add(indexSuperior+1, seleccionado);
			posTablonSeleccionado ++;
		}
		
		return listaTablones.get(indexSuperior).getId();
	}

	/**
	 * Intercambia la posicion del tablon seleccionado con el que esta
	 * inmediatamente inferior en pixeles
	 */
	public int bajarTablonSeleccionado() {
		int indexInferior = posTablonSeleccionado;
		Tablon seleccionado = listaTablones.get(posTablonSeleccionado);
		if (indexInferior > 0) {
			recrearBotones = true;
			listaTablones.remove(indexInferior);
			listaTablones.add(indexInferior - 1, seleccionado);
		}
		return listaTablones.get(indexInferior).getId();
	}

	public void seleccionarId(int id) {
		for (int i = 0; i < listaTablones.size(); i++) {
			listaTablones.get(i).desSeleccionar();
			if (listaTablones.get(i).getId() == id) {
				listaTablones.get(i).seleccionar();
				posTablonSeleccionado = i;
			}
		}
	}

	public void cambiarTono(int id) {
		for (int i = 0; i < listaTablones.size(); i++) {
			if (listaTablones.get(i).getId() == id) {
				listaTablones.get(i).cambiarTono();
				break;
			}
		}
	}

	public void cambiarColor(int id) {
		for (int i = 0; i < listaTablones.size(); i++) {
			if (listaTablones.get(i).getId() == id) {
				listaTablones.get(i).cambiarColor();
				break;
			}
		}
	}

	/**
	 * Elije entre un modo de vision u otro
	 * 
	 * @param activado
	 */
	public void setModoLinea(boolean activado) {
		modoLinea = activado;
		for (int i = 0; i < listaTablones.size(); i++) {
			listaTablones.get(i).linea = activado;
		}
	}

	public int numTablones() {
		return listaTablones.size();
	}

	public int bajarTablonId(int id) {
		int idAux = listaTablones.get(posTablonSeleccionado).getId();
		seleccionarId(id);
		bajarTablonSeleccionado();
		recrearBotones = true;
		seleccionarId(idAux);
		return idAux;
	}

	public int subirTablonId(int id) {
		int idAux = listaTablones.get(posTablonSeleccionado).getId();
		seleccionarId(id);
		subirTablonSeleccionado();
		recrearBotones = true;
		seleccionarId(idAux);
		return idAux;
	}

	public void setLista(ArrayList<Tablon> listaTablones) {
		this.listaTablones = listaTablones;

	}

	public void setPosTablonSeleccionado(int indexSeleccionado) {
		posTablonSeleccionado = indexSeleccionado;

	}

	public int size() {
		return listaTablones.size();
	}

	public void setBotones(PanelBotonesTablon b) {
		botones = b;

	}

	public void actualizarBotones() {
		botones.removeAll();
		for (int i = 0; i < listaTablones.size(); i++) {
			botones.addBotonesTablon(listaTablones.get(i).posY, listaTablones
					.get(i).getId());
		}
		botones.desSeleccionarTodos(listaTablones.get(posTablonSeleccionado)
				.getId());
		botones.revalidate();
		botones.repaint();
	}

	public int getAlto() {
		return (posTablonCentral + 100 + (listaTablones.size() * pixelesEntreTablones));
	}

	public void normalizarPosiciones() {
		ArrayList<Tablon> listaOrdenada = new ArrayList<Tablon>();
		listaOrdenada.add(listaTablones.get(0));
		for (int i = 1; i < listaTablones.size(); i++) {
			boolean encontrado = false;
			for (int k = 0; k < listaOrdenada.size(); k++) {
				if (!encontrado
						&& listaOrdenada.get(k).posY > listaTablones.get(i).posY) {
					encontrado = true;
					listaOrdenada.add(k, listaTablones.get(i));
					//System.out.println(listaTablones.get(i).posY);
				}
			}
			if (!encontrado) {
				listaOrdenada.add(listaTablones.get(i));
			}
		}
		for (int i = 0; i < listaOrdenada.size(); i++) {
			listaOrdenada.get(i).posY = posTablonCentral + i
					* pixelesEntreTablones;
		}

	}
}
