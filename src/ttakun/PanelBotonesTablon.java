package Txalaparta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelBotonesTablon extends JPanel implements ActionListener {
	ArrayList<Box> listaBox = new ArrayList<Box>();
	private Editor editor;

	public PanelBotonesTablon() {
		this.setLayout(null);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}

	private JButton crearBoton(String pathImg, int size) {
		JButton b = new JButton("");

		ImageIcon img = new ImageIcon(pathImg);
		ImageIcon iResized = new ImageIcon(img.getImage().getScaledInstance(
				size, size, 1));
		b.setIcon(iResized);
		b.setSize(size, size);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setContentAreaFilled(false);
		return b;
	}

	public void setEditor(Editor e) {
		editor = e;
	}

	public void setEditor(Editor e, ArrayList<Tablon> listaT) {
		editor = e;
		for (int i = 0; i < listaT.size(); i++) {
			this.addBotonesTablon(listaT.get(i).posY, listaT.get(i).getId());
		}

	}

	public void addBotonesTablon(int posY, int idTablon) {
		Box horizontalBox = Box.createHorizontalBox();
		this.add(horizontalBox);

		JRadioButton rdbtnNewRadioButton = new JRadioButton(idTablon + "");
		rdbtnNewRadioButton.addActionListener(this);
		rdbtnNewRadioButton.setFocusable(false);
		if (listaBox.size() == 0) {
			rdbtnNewRadioButton.setSelected(true);
		}
		horizontalBox.add(rdbtnNewRadioButton);

		JButton bMoveDown = crearBoton("Iconos\\downArrow.jpg", 20);
		bMoveDown.addActionListener(this);
		bMoveDown.setFocusable(false);
		horizontalBox.add(bMoveDown);

		JButton bMoveUp = crearBoton("Iconos\\upArrow.jpg", 20);
		bMoveUp.addActionListener(this);
		bMoveUp.setFocusable(false);
		horizontalBox.add(bMoveUp);

		JButton bChangeColor = crearBoton("Iconos\\icono_paint.gif", 20);
		bChangeColor.addActionListener(this);
		horizontalBox.add(bChangeColor);
		bChangeColor.setFocusable(false);
		horizontalBox.setBounds(0, posY - 10, 100, 20);

		listaBox.add(horizontalBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int k = 0; k < listaBox.size(); k++) {

			Box horizontalBox = listaBox.get(k);
			JRadioButton r = (JRadioButton) horizontalBox.getComponent(0);
			for (int i = 0; i < horizontalBox.getComponents().length; i++) {
				if (e.getSource() == horizontalBox.getComponent(i)) {
					// System.out.println("boton (" + k + ", " + i + ") "
					// + (horizontalBox.getBounds().y));

					switch (i) {

					case 0:
						desSeleccionarTodos();
						editor.seleccionarTablon(Integer.parseInt(r.getText()));
						r.setSelected(true);
						// System.out.println("editor.tocarTablon(Integer.parseInt(r.getText())); sin implementar");
						editor.tocarTablon(Integer.parseInt(r.getText()));
						break;
					case 1:
						moveIdUp(Integer.parseInt(r.getText()));
						break;
					case 2:
						moveIdDown(Integer.parseInt(r.getText()));
						break;
					case 3:
						editor.cambiarColorTablon(Integer.parseInt(r.getText()));
						break;
					}

				}
			}
		}

	}

	private void desSeleccionarTodos() {
		for (int k = 0; k < listaBox.size(); k++) {

			Box horizontalBox = listaBox.get(k);
			JRadioButton r = (JRadioButton) horizontalBox.getComponent(0);
			r.setSelected(false);
		}
	}

	public void desSeleccionarTodos(int id) {
		for (int k = 0; k < listaBox.size(); k++) {

			Box horizontalBox = listaBox.get(k);
			JRadioButton r = (JRadioButton) horizontalBox.getComponent(0);
			r.setSelected(false);
			if (Integer.parseInt(r.getText()) == id) {
				r.setSelected(true);
			}
		}
	}

	public void moveSelected(int id) {
		JRadioButton select = null, cambio = null;
		for (int k = 0; k < listaBox.size(); k++) {
			Box horizontalBox = listaBox.get(k);
			JRadioButton r = (JRadioButton) horizontalBox.getComponent(0);
			if (r.isSelected()) {
				select = r;
			}
			if (Integer.parseInt(r.getText()) == id) {
				cambio = r;
			}
		}
		desSeleccionarTodos(id);
		String aux = select.getText();
		select.setText(cambio.getText());
		cambio.setText(aux);

	}

	public int getIdSeleccionado() {
		for (int k = 0; k < listaBox.size(); k++) {
			Box horizontalBox = listaBox.get(k);
			JRadioButton r = (JRadioButton) horizontalBox.getComponent(0);
			if (r.isSelected())
				return Integer.parseInt(r.getText());
		}
		return -1;
	}

	public void moveIdUp(int id) {
		editor.subirTablon(id);
		this.repaint();
	}

	public void moveIdDown(int id) {
		editor.bajarTablon(id);
		this.repaint();
	}

}
