package Txalaparta;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.Scrollable;

public class ListaMusicos extends JPanel implements Scrollable, ActionListener {

	Musico mSeleccionado = null;
	Dimension miSize;
	Box listaPaneles;
	ArrayList<Musico> listaMusicos;
	int idNuevoMusico = 0;
	public JButton btnNewMusico;
	public JButton btnDelMusico;
	private Editor editor;

	public ListaMusicos() {
		Menu textos = Menu.getInstance();
		listaMusicos = new ArrayList<Musico>();
		listaPaneles = Box.createVerticalBox();
		add(listaPaneles);
		this.setPreferredSize(miSize);
		btnNewMusico = new JButton(textos.listaBotones.get(4).t);
		btnDelMusico = new JButton(textos.listaBotones.get(6).t);
		btnNewMusico.setFocusable(false);
		btnDelMusico.setFocusable(false);

	}

	public ListaMusicos(ArrayList<Musico> listaM) {
		listaMusicos = listaM;
		Menu textos = Menu.getInstance();
		listaPaneles = Box.createVerticalBox();
		add(listaPaneles);
		miSize = new Dimension(0, 10);
		for (int i = 0; i < listaM.size(); i++) {
			Musico musico = listaM.get(i);
			if (i == 0) {

				mSeleccionado = musico;
				musico.rdbtnSelected.setSelected(true);
				miSize = new Dimension(0, 10);
				miSize.width = musico.getWidth();
			}
			listaPaneles.add(musico);
			musico.rdbtnSelected.addActionListener(this);
			musico.panelColor.addActionListener(this);
			miSize.height += musico.getHeight() + 4;

		}
		idNuevoMusico = listaM.get(listaM.size() - 1).getId() + 1;
		// miSize = new Dimension(listaMusicos.get(0).getWidth(),
		// listaMusicos.get(0).getHeight()*100);
		this.setPreferredSize(miSize);
		btnNewMusico = new JButton(textos.listaBotones.get(4).t);
		btnDelMusico = new JButton(textos.listaBotones.get(6).t);
		btnNewMusico.setFocusable(false);
		btnDelMusico.setFocusable(false);
	}

	public void addMusico() {
		Color c = JColorChooser.showDialog(null,
				"Elige un color para el musico", Color.blue);
		if (c != null) {
			addMusico(c);
		}

	}

	public void addMusico(Color c) {
		Musico musico = new Musico(idNuevoMusico++, c);
		musico.panelColor.addActionListener(this);
		musico.rdbtnSelected.addActionListener(this);
		if (listaMusicos.isEmpty()) {
			mSeleccionado = musico;
			musico.rdbtnSelected.setSelected(true);
			miSize = new Dimension(0, 10);
			miSize.width = musico.getWidth();
		}
		listaMusicos.add(musico);
		listaPaneles.add(musico);

		miSize.height += musico.getHeight() + 4;
		this.revalidate();
	}

	public void removeMusico() {
		listaMusicos.remove(mSeleccionado);
		listaPaneles.remove(mSeleccionado);

	}

	public Musico getMusicoSeleccionado() {
		return mSeleccionado;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musico> getCopiaListaMusicos() {
		return (ArrayList<Musico>) listaMusicos.clone();
	}

	public void setLista(ArrayList<Musico> lista) {
		listaMusicos = lista;

	}

	public int getNumMusicos() {
		return listaMusicos.size();
	}

	public Musico getMusicoByPos(int i) {
		return listaMusicos.get(i);
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return listaMusicos.get(0).getHeight();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object btn = arg0.getSource();
		for (int i = 0; i < listaMusicos.size(); i++) {
			if (btn == listaMusicos.get(i).panelColor) {
				listaMusicos.get(i).cambiarColor();
				editor.cambiarColorMusico(listaMusicos.get(i));
			}

			if (btn == listaMusicos.get(i).rdbtnSelected) {
				mSeleccionado.rdbtnSelected.setSelected(false);
				mSeleccionado = listaMusicos.get(i);

			}
		}
	}

	public void setEditor(Editor editor2) {
		editor = editor2;

	}

	public ArrayList<Integer> getListaIdMusicosMuteados() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < listaMusicos.size(); i++) {
			if (listaMusicos.get(i).isMuted)
				lista.add(listaMusicos.get(i).getId());
		}
		return lista;

	}

}
