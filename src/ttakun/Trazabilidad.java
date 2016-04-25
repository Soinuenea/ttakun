package Txalaparta;

import java.util.ArrayList;

public class Trazabilidad {

	private ArrayList<Traza> listaDeshacer, listaRehacer;

	public Trazabilidad() {
		listaDeshacer = new ArrayList<Traza>();
		listaRehacer = new ArrayList<Traza>();
	}

	public void addTraza(ListaOrdenadaGolpes listaGolpes,
			ListaTablones listaTablones, ListaMusicos listaMusicos) {
		TrazaGlobal traza = new TrazaGlobal(listaGolpes, listaTablones,
				listaMusicos);
		listaDeshacer.add(0, traza);
		listaRehacer.clear();
	}

	public void addTraza(ListaOrdenadaGolpes listaGolpes,
			ListaTablones listaTablones, ListaMusicos listaMusicos, int duracion) {
		TrazaGlobal traza = new TrazaGlobal(listaGolpes, listaTablones,
				listaMusicos, duracion);
		listaDeshacer.add(0, traza);
		listaRehacer.clear();
	}

	public void deshacer(Partitura partitura) {
		if (listaDeshacer.size() > 0) {
			Traza aux = listaDeshacer.get(0);
			listaDeshacer.remove(0);
			aux.desHacer(partitura);
			listaRehacer.add(0, aux);
		}
		//System.out.println("Rehacer: " + listaRehacer.size() + " Deshacer: "
		//		+ listaDeshacer.size());
	}

	public void rehacer(Partitura partitura) {
		if (listaRehacer.size() > 0) {
			Traza aux = listaRehacer.get(0);
			listaRehacer.remove(0);
			aux.reHaceR(partitura);
			listaDeshacer.add(0, aux);
		}
//		System.out.println("Rehacer: " + listaRehacer.size() + " Deshacer: "
//				+ listaDeshacer.size());
	}

	public void eliminarTraza() {
		listaDeshacer.remove(0);

	}

}
