package Txalaparta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Musico extends JPanel implements ActionListener {

	private int id;
	private Color color;
	private Color mainColor;
	public JButton panelColor;
	public JRadioButton rdbtnSelected;
	public boolean isMuted = false;
	public JButton bMuted;
	private Box horizontalBox;

	public Musico(int id, Color c) {
		super();
		this.id = id;
		mainColor = c;
		color = mainColor;
		BufferedImage aux = new BufferedImage(20, 20,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D drawer = aux.createGraphics();
		drawer.setPaint(c);
		drawer.fillRect(0, 0, aux.getWidth(), aux.getHeight());
		ImageIcon icon = new ImageIcon(aux);
		setLayout(new BorderLayout(0, 0));
		// this.setSize(this.getSize().width,20);
		this.setSize(150, 20);

		horizontalBox = Box.createHorizontalBox();
		add(horizontalBox, BorderLayout.NORTH);

		rdbtnSelected = new JRadioButton(
				Menu.getInstance().panelMusico.get(0).t + (id+1));
		rdbtnSelected.setFocusable(false);
		horizontalBox.add(rdbtnSelected);

		panelColor = new JButton();
		panelColor.setFocusable(false);
		horizontalBox.add(panelColor);
		panelColor.setToolTipText("Change color of the musician");
		panelColor.setIcon(icon);
		panelColor.setBorder(null);

		bMuted = new JButton();
		bMuted.setFocusable(false);
		horizontalBox.add(bMuted);
		bMuted.setIcon(new ImageIcon("Iconos\\soundOnMini.png"));
		bMuted.setBorder(null);
		bMuted.addActionListener(this);
		bMuted.setToolTipText(Menu.getInstance().panelMusico.get(1).t);

	}

	public int getId() {
		return id;
	}

	public Color getColor() {
		return color;
	}

	public void cambiarColor() {
		Color c = JColorChooser.showDialog(null,
				Menu.getInstance().listaAlerts.get(3).t, mainColor);
		if (c != null) {
			mainColor = c;
			color = c;
			panelColor.setIcon(crearImagen(c));
		}
	}

	private ImageIcon crearImagen(Color c) {
		BufferedImage aux = new BufferedImage(20, 20,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D drawer = aux.createGraphics();
		drawer.setPaint(c);
		drawer.fillRect(0, 0, aux.getWidth(), aux.getHeight());
		return new ImageIcon(aux);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isMuted) {
			bMuted.setIcon(new ImageIcon("Iconos\\soundOnMini.png"));
			isMuted = false;
		} else {
			bMuted.setIcon(new ImageIcon("Iconos\\soundOffMini.png"));
			isMuted = true;
		}

	}
}
