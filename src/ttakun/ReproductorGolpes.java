package Txalaparta;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JToggleButton;

public class ReproductorGolpes implements Runnable {
	public int puntoReproduccion = 0;
	private int velocidad;
	public boolean finalizado = false;
	public boolean finReproduccion = false;
	public JToggleButton loop;
	public int duracion = 1000;
	private int posCursor = 0;
	private long puntoComienzoReproduccion = 0;
	private ListaTablones listaTablones;
	private ListaOrdenadaGolpes listaGolpes;
	private ArrayList<Integer> listaMusicosMuteados;

	public ReproductorGolpes(ListaTablones tablones,
			ListaOrdenadaGolpes golpes, ArrayList<Integer> musicos, int speed) {
		listaTablones = tablones;
		listaGolpes = golpes;
		listaMusicosMuteados = musicos;
		velocidad = speed;
	}

	@Override
	public void run() {
		finalizado = false;
		finReproduccion = false;
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		Date date = new Date();
		puntoComienzoReproduccion = date.getTime();
		posCursor = 0;
		while (!finalizado) {

			if (posCursor >= duracion) {
				date = new Date();
				puntoComienzoReproduccion = date.getTime();
				posCursor = 0;
				puntoReproduccion = 0;
			}
			posCursor = puntoReproduccion;
			for (int i = 0; i < listaGolpes.listaGolpes.size(); i++) {
				Golpe golpe = listaGolpes.listaGolpes.get(i);
				if (golpe.momento >= posCursor) {
					try {
						Thread.sleep((golpe.momento - posCursor) * 100
								/ velocidad);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					posCursor = golpe.momento;
					if (finReproduccion)
						break;
					if (!listaMusicosMuteados.contains(golpe.idMusico)) {
						listaTablones.getTablon(golpe.idTablon).golpear(
								golpe.fuerza);
					}
				}
			}

			if (posCursor < duracion) {
				try {
					Thread.sleep((duracion - posCursor) * 100 / velocidad);
					posCursor = duracion;
					puntoReproduccion = duracion;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.gc();
			finalizado = finReproduccion || !loop.isSelected();
			System.out.println("Iterando");
		}
	}

	public int getMomentoCursor() {
		Date date = new Date();
		return (int) ((date.getTime() - puntoComienzoReproduccion) * velocidad / 100);
	}

}
