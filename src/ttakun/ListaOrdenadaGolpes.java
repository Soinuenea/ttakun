package Txalaparta;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class ListaOrdenadaGolpes {
	public ArrayList<Golpe> listaGolpes;

	// public Reproducir[] reproductores;

	public ListaOrdenadaGolpes() {
		listaGolpes = new ArrayList<Golpe>();
	}

	public ListaOrdenadaGolpes(ArrayList<Golpe> listaG) {
		listaGolpes = listaG;
	}

	public ArrayList<Golpe> getCopiaListaGolpes() {
		ArrayList<Golpe> aux = new ArrayList<Golpe>();
		for (int i = 0; i < listaGolpes.size(); i++) {
			aux.add(new Golpe(listaGolpes.get(i)));
		}
		return aux;
	}

	public void addGolpe(Golpe g) {
		addGolpe(g, false);
	}

	public void addGolpe(Golpe g, boolean sustituir) {
		if (sustituir) {
			for (int i = 0; i < listaGolpes.size(); i++) {
				Golpe auxGolpe = listaGolpes.get(i);
				if (auxGolpe.momento == g.momento
						&& auxGolpe.idMusico == g.idMusico
						&& auxGolpe.idTablon == g.idTablon) {
					listaGolpes.remove(i);
					i--;
				}
			}
		}
		int pos = listaGolpes.size();
		for (int i = 0; i < listaGolpes.size(); i++) {
			if (listaGolpes.get(i).momento >= g.momento) {

				pos = i;
				break;
			}
		}
		listaGolpes.add(pos, g);
	}

	public void removeGolpe(Golpe g) {
		for (int i = 0; i < listaGolpes.size(); i++) {
			if (listaGolpes.get(i) == g) {
				listaGolpes.remove(i);
				break;
			}
		}
	}

	// public void reproducirGolpes(ListaTablones listaTablones, int porcentaje,
	// ListaMusicos musicos) {
	// pararReproduccion();
	// Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
	// Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
	//
	// ArrayList<Musico> listaMuteados = new ArrayList<Musico>();
	// for(int i = 0; i < musicos.listaMusicos.size();i++){
	// if(musicos.listaMusicos.get(i).isMuted){
	// listaMuteados.add(musicos.listaMusicos.get(i));
	// }
	// }
	// if(reproductores != null)
	// for(int i = 0; i < reproductores.length; i++){
	// if(reproductores[i] != null) reproductores[i].fin();
	// }
	// reproductores = new Reproducir[listaGolpes.size()];
	// for (int i = 0; i < listaGolpes.size(); i++) {
	//
	// Golpe g = listaGolpes.get(i);
	// boolean muteado = false;
	// for(int k= 0; k < listaMuteados.size(); k++){
	// if(g.idMusico == listaMuteados.get(k).getId()){
	// muteado = true;
	// break;
	// }
	// }
	// if (!muteado){
	// reproductores[i] = listaTablones.getTablon(g.idTablon).sonar(
	// g.fuerza, g.momento*100/porcentaje);
	//
	// }
	// }
	// threadSet = Thread.getAllStackTraces().keySet();
	// threadArray = threadSet.toArray(new Thread[threadSet.size()]);
	// System.out.println(threadArray.length);
	// }
	//
	// public void pararReproduccion() {
	// if (reproductores != null) {
	// for (int i = 0; i < reproductores.length; i++) {
	// if(reproductores[i]!= null) reproductores[i].reproducir = false;
	// }
	// }
	// }
	//
	// public Reproducir[] getListaReproducir(ListaTablones listaTablones, int
	// porcentaje, ListaMusicos musicos){
	// ArrayList<Musico> listaMuteados = new ArrayList<Musico>();
	// for(int i = 0; i < musicos.listaMusicos.size();i++){
	// if(musicos.listaMusicos.get(i).isMuted){
	// listaMuteados.add(musicos.listaMusicos.get(i));
	// }
	// }
	// reproductores = new Reproducir[listaGolpes.size()];
	// for (int i = 0; i < listaGolpes.size(); i++) {
	//
	// Golpe g = listaGolpes.get(i);
	// boolean muteado = false;
	// for(int k= 0; k < listaMuteados.size(); k++){
	// if(g.idMusico == listaMuteados.get(k).getId()){
	// muteado = true;
	// break;
	// }
	// }
	// if (!muteado){
	// reproductores[i] = listaTablones.getTablon(g.idTablon).sonar(
	// g.fuerza, g.momento*100/porcentaje);
	//
	// }
	// }
	// return reproductores;
	// }

	public void dibujar(Graphics g, Regleta regleta, ListaTablones listaTablones) {
		for (int i = 0; i < listaGolpes.size(); i++) {
			Golpe golpe = listaGolpes.get(i);
			int posX = regleta.timeToPixel(golpe.momento);

			int posTablon = listaTablones.getTablon(golpe.idTablon).posY;
			golpe.dibujar(g, posX, posTablon);
		}
	}

	public void seleccionar(int tipoSeleccion, Point inicio, Point fin,
			ListaTablones listaTablones) {

		for (int i = 0; i < listaGolpes.size(); i++) {
			switch (tipoSeleccion) {
			case 1: // Seleccion por franja de tiempo
				if ((listaGolpes.get(i).momento >= inicio.x && listaGolpes
						.get(i).momento <= fin.x)
						|| (listaGolpes.get(i).momento <= inicio.x && listaGolpes
								.get(i).momento >= fin.x)) {
					listaGolpes.get(i).seleccionar();
				}
				break;
			case 2:// Seleccion por colision
				if (listaGolpes.get(i).inSelection(inicio, fin, listaTablones))
					listaGolpes.get(i).seleccionar();
				break;
			}

		}
	}

	public void desSeleccionar() {
		for (int i = 0; i < listaGolpes.size(); i++) {
			listaGolpes.get(i).desseleccionar();
		}
	}

	public ArrayList<Golpe> getSeleccionados() {

		ArrayList<Golpe> listaSeleccionados = new ArrayList<Golpe>();
		for (int i = 0; i < listaGolpes.size(); i++) {
			Golpe g = listaGolpes.get(i);
			if (g.seleccionado) {

				listaSeleccionados.add(g);
			}
		}
		return listaSeleccionados;
	}

	public void eliminarGolpes(ArrayList<Golpe> lista) {

		for (int i = 0; i < lista.size(); i++) {
			listaGolpes.remove(lista.get(i));
		}
	}

	public void moverSeleccionados(int tiempo) {
		ArrayList<Golpe> listaGolpesMovidos = new ArrayList<Golpe>();
		for (int i = 0; i < listaGolpes.size(); i++) {
			Golpe g = listaGolpes.get(i);
			if (g.seleccionado) {
				listaGolpesMovidos.add(g);
				listaGolpes.remove(i);
				g.momento += tiempo;
				i--;
				// addGolpe(g);
			}
		}
		for (int i = 0; i < listaGolpesMovidos.size(); i++) {
			addGolpe(listaGolpesMovidos.get(i));
		}
	}

	public void setModoLinea(boolean activado) {
		for (int i = 0; i < listaGolpes.size(); i++) {
			listaGolpes.get(i).setModoLinea(activado);
		}
	}

	public void eliminarGolpesTablon(int tId) {
		for (int i = 0; i < listaGolpes.size(); i++) {
			if (listaGolpes.get(i).idTablon == tId) {
				listaGolpes.remove(i);
				i--;
			}
		}
	}

	public void checkGolpes(int d) {
		for (int i = 0; i < listaGolpes.size(); i++) {
			if (listaGolpes.get(i).momento > d) {
				listaGolpes.remove(i);
				i--;
			}
		}
	}

	public void duplicateSelected() {
		ArrayList<Golpe> aux = this.getSeleccionados();
		for (int i = 0; i < aux.size(); i++) {
			this.addGolpe(new Golpe(aux.get(i)));
		}
	}

	public void selectAll() {
		for (int i = 0; i < listaGolpes.size(); i++) {
			if (!listaGolpes.get(i).seleccionado) {
				listaGolpes.get(i).seleccionado = true;

			}
		}

	}

	public void redondearA(int valor) {
		for (int i = 0; i < listaGolpes.size(); i++) {
			listaGolpes.get(i).redondear(valor);
		}
	}

	public void setLista(ArrayList<Golpe> listaGolpes) {
		this.listaGolpes = listaGolpes;

	}

	public void cambiarColor(Musico musico) {
		for (int i = 0; i < listaGolpes.size(); i++) {
			Golpe g = listaGolpes.get(i);
			if (g.idMusico == musico.getId()) {
				g.cNormal = musico.getColor();
			}
		}

	}

	public void eliminarGolpes(Musico musico) {
		for (int i = 0; i < listaGolpes.size(); i++) {
			if (listaGolpes.get(i).idMusico == musico.getId()) {
				listaGolpes.remove(i);
				i--;
			}
		}

	}
	
	public void elminarDuplicados(){
		for (int i = 1; i < listaGolpes.size(); i++) {
			Golpe anterior = listaGolpes.get(i-1);
			Golpe actual = listaGolpes.get(i);
			if (anterior.momento == actual.momento  && anterior.idTablon == actual.idTablon && anterior.idMusico == actual.idMusico){
				listaGolpes.remove(i);
				i--;
			}
		}
	}
}
