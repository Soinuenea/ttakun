package Txalaparta;

import java.util.ArrayList;

public class TrazaGlobal implements Traza {
	ArrayList<Golpe> listaGolpesInicial, listaGolpesPos;
	ArrayList<Tablon> listaTablonesInicial, listaTablonesPos;
	int oldTablonSeleccionado, newTablonSeleccionado;
	ArrayList<Musico> listaMusicosInicial, listaMusicosPos;
	int oldDuration, newDuration;

	public TrazaGlobal(ListaOrdenadaGolpes listaG, ListaTablones listaT,
			ListaMusicos listaM) {
		init(listaG, listaT, listaM, -1);
	}

	public TrazaGlobal(ListaOrdenadaGolpes listaG, ListaTablones listaT,
			ListaMusicos listaM, int duracion) {
		init(listaG, listaT, listaM, duracion);
	}

	private void init(ListaOrdenadaGolpes listaG, ListaTablones listaT,
			ListaMusicos listaM, int duracion) {

		if (listaG != null)
			listaGolpesInicial = listaG.getCopiaListaGolpes();
		if (listaT != null) {
			listaTablonesInicial = listaT.getCopiaListaTablones();
			oldTablonSeleccionado = listaT.getPosTablonSeleccionado();
		}
		if (listaM != null)
			listaMusicosInicial = listaM.getCopiaListaMusicos();
		oldDuration = duracion;
	}

	@Override
	public void desHacer(Partitura p) {
		if (listaGolpesInicial != null) {
			listaGolpesPos = p.getListaGolpes().getCopiaListaGolpes();
			p.getListaGolpes().setLista(listaGolpesInicial);
		}

		if (listaTablonesInicial != null) {
			listaTablonesPos = p.getListaTablones().getCopiaListaTablones();
			newTablonSeleccionado = p.getListaTablones()
					.getPosTablonSeleccionado();
			p.getListaTablones().setLista(listaTablonesInicial);
			p.getListaTablones()
					.setPosTablonSeleccionado(oldTablonSeleccionado);

		}

		if (listaMusicosInicial != null) {
			listaMusicosPos = p.getListaMusicos().getCopiaListaMusicos();
			p.getListaMusicos().setLista(listaMusicosInicial);
		}

		if (oldDuration != -1) {
			newDuration = p.duracion;
			p.padre.setDuracion(oldDuration, true);
		}

	}

	@Override
	public void reHaceR(Partitura p) {
		if (listaGolpesInicial != null)
			p.getListaGolpes().setLista(listaGolpesPos);
		if (listaTablonesInicial != null) {
			p.getListaTablones().setLista(listaTablonesPos);
			p.getListaTablones()
					.setPosTablonSeleccionado(newTablonSeleccionado);
		}
		if (listaMusicosInicial != null)
			p.getListaMusicos().setLista(listaMusicosPos);

		if (oldDuration != -1) {
			p.padre.setDuracion(newDuration, true);
		}

	}

}
