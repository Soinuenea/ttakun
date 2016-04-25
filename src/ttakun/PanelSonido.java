package Txalaparta;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class PanelSonido extends JPanel {
	public JTextField redondeo;
	public JButton btnPlay;
	public JButton btnStop;
	private Editor editor;
	private JTextField playbackSpeed;
	public JSlider slider;
	public JButton btnAjustarGolpes;
	private Box horizontalBox_3;
	private Box horizontalBox_4;
	private JTextField tGolpesCompas;
	private Box verticalBox_1;
	private Component verticalStrut;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut;

	final static float dash1[] = { 10.0f };
	final static BasicStroke dashed = new BasicStroke(1.0f,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

	private Box horizontalBox_5;
	private JTextField tFuerzaGolpe;
	private JCheckBox chckbxFuerzaGolpe;
	private Box verticalBox_2;
	private Box horizontalBox_6;
	private Box horizontalBox_7;
	private Box horizontalBox_8;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private Component verticalStrut_3;
	private Box horizontalBox_9;
	private JTextField tComienzoReproduccion;
	private JLabel lblNewLabel_2;
	private Box verticalBox_3;
	private Box horizontalBox_10;
	private Component verticalStrut_4;
	private Component verticalStrut_5;

	private int oldRedondeo = 0;
	private int oldMaxValor = 0;
	private int oldDistancia = 0;
	private BufferedImage oldMarcaRedondeo;
	private Component horizontalStrut_1;
	private Box verticalBox_4;
	private JTextField tDuracion;
	private JLabel lblDuracion;
	private Component verticalStrut_6;
	private Box horizontalBox_11;
	private Component verticalStrut_7;
	private JButton btnCambiarDuracion;
	public JToggleButton chckbxLoop;

	public PanelSonido(Editor e) {
		Menu textos = Menu.getInstance();
		editor = e;
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		horizontalBox_3 = Box.createHorizontalBox();
		add(horizontalBox_3);

		verticalBox_2 = Box.createVerticalBox();
		horizontalBox_3.add(verticalBox_2);

		horizontalBox_6 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_6);

		JLabel lblNewLabel = new JLabel(textos.panelSonido.get(0).t);
		horizontalBox_6.add(lblNewLabel);

		verticalStrut_1 = Box.createVerticalStrut(20);
		horizontalBox_6.add(verticalStrut_1);

		horizontalBox_7 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_7);

		lblNewLabel_1 = new JLabel(textos.panelSonido.get(1).t);
		horizontalBox_7.add(lblNewLabel_1);

		verticalStrut_2 = Box.createVerticalStrut(20);
		horizontalBox_7.add(verticalStrut_2);

		horizontalBox_8 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_8);

		chckbxFuerzaGolpe = new JCheckBox("fGolpe");
		horizontalBox_8.add(chckbxFuerzaGolpe);
		chckbxFuerzaGolpe.setSelected(true);
		chckbxFuerzaGolpe.setText(Menu.getInstance().panelSonido.get(4).t);

		verticalStrut_3 = Box.createVerticalStrut(20);
		horizontalBox_8.add(verticalStrut_3);

		Box verticalBox = Box.createVerticalBox();
		horizontalBox_3.add(verticalBox);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		redondeo = new JTextField();
		horizontalBox.add(redondeo);
		redondeo.setHorizontalAlignment(SwingConstants.LEFT);
		redondeo.setColumns(10);
		redondeo.putClientProperty("terminateEditOnFocusLost", true);
		redondeo.setText("1");

		btnAjustarGolpes = new JButton(textos.listaBotones.get(0).t);
		horizontalBox.add(btnAjustarGolpes);
		btnAjustarGolpes.setFocusable(false);

		horizontalBox_4 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_4);

		tGolpesCompas = new JTextField();
		tGolpesCompas.setText("1");
		horizontalBox_4.add(tGolpesCompas);
		tGolpesCompas.setColumns(10);

		horizontalBox_5 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_5);

		tFuerzaGolpe = new JTextField();
		tFuerzaGolpe.setText("100");
		horizontalBox_5.add(tFuerzaGolpe);
		tFuerzaGolpe.setColumns(10);

		tGolpesCompas.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				editor.repaint();
			}
		});

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut);

		verticalBox_1 = Box.createVerticalBox();
		horizontalBox_3.add(verticalBox_1);

		horizontalBox_10 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_10);

		JLabel lblSpeed = new JLabel(textos.panelSonido.get(2).t);
		horizontalBox_10.add(lblSpeed);
		lblSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);

		verticalStrut_4 = Box.createVerticalStrut(31);
		horizontalBox_10.add(verticalStrut_4);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_2);

		verticalStrut_5 = Box.createVerticalStrut(20);
		horizontalBox_2.add(verticalStrut_5);

		horizontalBox_9 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_9);

		verticalStrut = Box.createVerticalStrut(20);
		horizontalBox_9.add(verticalStrut);

		lblNewLabel_2 = new JLabel("Start position");
		lblNewLabel_2.setText(textos.panelSonido.get(5).t);
		horizontalBox_9.add(lblNewLabel_2);

		verticalBox_3 = Box.createVerticalBox();
		horizontalBox_3.add(verticalBox_3);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox_3.add(horizontalBox_1);
		horizontalBox_1.setAlignmentY(Component.CENTER_ALIGNMENT);

		playbackSpeed = new JTextField();
		horizontalBox_1.add(playbackSpeed);
		playbackSpeed.setColumns(10);

		playbackSpeed.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent action) {

				int valor;
				try {
					valor = Integer.parseInt(playbackSpeed.getText());
				} catch (Exception e) {
					valor = 100;
					playbackSpeed.setText("100");
				}
				slider.setValue(valor);
			}
		});

		btnPlay = new JButton("");
		ImageIcon iconoPlay = new ImageIcon(".\\Iconos\\btnPlay.png");
	btnPlay.setPreferredSize(new Dimension(iconoPlay.getIconWidth(), iconoPlay.getIconHeight()));
		btnPlay.setIcon(iconoPlay);
		btnPlay.setBorderPainted(false);
		btnPlay.setContentAreaFilled(false);
		btnPlay.setFocusPainted(false);
		btnPlay.setOpaque(false);
		
		btnPlay.setFocusable(false);
		horizontalBox_1.add(btnPlay);

		btnStop = new JButton("");

		btnStop.setPreferredSize(new Dimension(iconoPlay.getIconWidth(), iconoPlay.getIconHeight()));
		btnStop.setIcon(new ImageIcon(".\\Iconos\\btnStop.png"));
		btnStop.setBorderPainted(false);
		btnStop.setContentAreaFilled(false);
		btnStop.setFocusPainted(false);
		btnStop.setOpaque(false);
		horizontalBox_1.add(btnStop);
		btnStop.setFocusable(false);

		slider = new JSlider(1, 500);
		verticalBox_3.add(slider);
		slider.setValue(100);
		slider.setFocusable(false);

		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				playbackSpeed.setText(slider.getValue() + "");

			}
		});
		playbackSpeed.setText(slider.getValue() + "");
		
		chckbxLoop = new JToggleButton("");
		chckbxLoop.setPreferredSize(new Dimension(iconoPlay.getIconWidth(), iconoPlay.getIconHeight()));
		chckbxLoop.setSelectedIcon(new ImageIcon(".\\Iconos\\btnLoopOn.png"));
		chckbxLoop.setIcon(new ImageIcon(".\\Iconos\\btnLoop.png"));
		chckbxLoop.setBorderPainted(false);
		chckbxLoop.setContentAreaFilled(false);
		chckbxLoop.setFocusPainted(false);
		chckbxLoop.setOpaque(false);
		horizontalBox_1.add(chckbxLoop);
		tComienzoReproduccion = new JTextField();
		// tComienzoReproduccion.setEnabled(false);
		tComienzoReproduccion.setText("0");
		verticalBox_3.add(tComienzoReproduccion);
		tComienzoReproduccion.setColumns(10);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1);

		verticalBox_4 = Box.createVerticalBox();
		add(verticalBox_4);

		horizontalBox_11 = Box.createHorizontalBox();
		verticalBox_4.add(horizontalBox_11);

		lblDuracion = new JLabel("labelDuracion ");
		lblDuracion.setText(textos.panelSonido.get(6).t);
		horizontalBox_11.add(lblDuracion);

		tDuracion = new JTextField();
		horizontalBox_11.add(tDuracion);
		tDuracion.setColumns(10);

		btnCambiarDuracion = new JButton("btnCambiarDuracion");
		btnCambiarDuracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.setDuracion(Integer.parseInt(tDuracion.getText()));
			}
		});
		btnCambiarDuracion.setText(textos.listaBotones.get(5).t);
		btnCambiarDuracion.setToolTipText(textos.listaBotones.get(5).ttip);
		btnCambiarDuracion.setFocusable(false);
		horizontalBox_11.add(btnCambiarDuracion);

		verticalStrut_6 = Box.createVerticalStrut(20);
		verticalBox_4.add(verticalStrut_6);

		verticalStrut_7 = Box.createVerticalStrut(20);
		verticalBox_4.add(verticalStrut_7);

		redondeo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				btnAjustarGolpes.requestFocus();
				editor.repaint();

			}
		});

	}

	public int getRedondeo() {
		final String t = redondeo.getText();
		if (t == "") {
			redondeo.setText("1");
			return 1;
		} else {
			int result = 1;
			try {
				result = Integer.parseInt(t);
			} catch (Exception e) {
				result = 1;
				redondeo.setText("1");
			}
			return result;
		}

	}

	public void dibujarMarca(int posReproduccion, Graphics g, int sizeY) {
		g.setColor(new Color(100, 100, 0, 100));
		final int posY = sizeY - 3;
		final int nuevaPos = posReproduccion;
		g.fillRect(0, 0, nuevaPos, posY);

	}

	public void dibujarMarcaRedondeo(Graphics2D gCanvas, int sizeX, int sizeY,
			Regleta r) {

		final int redondeo = getRedondeo();
		final int distancia = r.timeToPixel(redondeo);
		final int maxValor = r.getMaxValor();
		if (distancia > 7) {
			if (redondeo != oldRedondeo || distancia != oldDistancia
					|| maxValor != oldMaxValor) {
				oldMarcaRedondeo = new BufferedImage(sizeX, sizeY,
						BufferedImage.TYPE_INT_RGB);
				Graphics2D g = oldMarcaRedondeo.createGraphics();
				g.setColor(new Color(255, 255, 255));
				g.fillRect(0, 0, sizeX, sizeY);
				final Stroke aux = g.getStroke();
				g.setStroke(dashed);
				int numGolpes = 0;
				for (int i = 0; i < maxValor; i += redondeo) {
					int pos = r.timeToPixel(i);
					if (numGolpes % getHitsPerCompas() == 0) {
						g.setColor(new Color(0, 0, 255, 150));
						g.drawLine(pos, 0, pos, sizeY);
						g.setColor(new Color(0, 0, 0, 50));
					} else {
						g.drawLine(pos, 0, pos, sizeY);
					}
					numGolpes++;
				}

				oldRedondeo = redondeo;
				oldMaxValor = maxValor;
				oldDistancia = distancia;
				g.setStroke(aux);
			}
			gCanvas.drawImage(oldMarcaRedondeo, null, 0, 0);
		}

	}

	public int getPlaybackSpeed() {
		return Integer.parseInt(playbackSpeed.getText());
	}

	public void setHitsPerCompas(int value) {
		tGolpesCompas.setText(value + "");
	}

	public int getHitsPerCompas() {
		return Integer.parseInt(tGolpesCompas.getText());
	}

	public int getFuerza(int valor) {
		if (!chckbxFuerzaGolpe.isSelected())
			return valor;
		try {
			return Integer.parseInt(tFuerzaGolpe.getText());
		} catch (Exception e) {
			tFuerzaGolpe.setText("100");
			return 100;
		}

	}

	public void habilitarFocus(boolean valor) {

		tFuerzaGolpe.setFocusable(valor);
		tGolpesCompas.setFocusable(valor);
		redondeo.setFocusable(valor);
		playbackSpeed.setFocusable(valor);
		tComienzoReproduccion.setFocusable(valor);
		tDuracion.setFocusable(valor);
		btnCambiarDuracion.setFocusable(valor);
		chckbxFuerzaGolpe.setFocusable(valor);
	}

	public int getComienzoReproduccion() {
		return Integer.parseInt(tComienzoReproduccion.getText());
	}

	public void setDuracion(int duracion) {
		tDuracion.setText(duracion + "");
	}
}
