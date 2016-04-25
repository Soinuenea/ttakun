package Txalaparta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class EditorWindow extends JInternalFrame implements ActionListener,
		InternalFrameListener {
	private Editor editor;
	private Regleta regleta;
	private ListaMusicos musicos;
	public JScrollPane jScrollPane;
	private PanelBotonesTablon botones;
	private JButton bAddPlank;
	private String storePath = null;
	private PanelSonido panelSonido;
	private Box verticalBox;
	private JButton btnNewMusician, btnDelMusician;
	private Box horizontalBox;
	private Box horizontalBox_1;
	private Component horizontalGlue_3;
	private JEditorPane editorPane;
	private JScrollPane scrollPane;
	private Box verticalBox_1;
	private Component horizontalStrut;
	private Component verticalStrut;

	/**
	 * Create the frame.
	 * 
	 * @param nombreFrame
	 * @wbp.parser.constructor
	 */
	public EditorWindow(String nombreFrame) {
		super(nombreFrame);
		this.setSize(840, 400);
		// musicos.btnNewButton.addActionListener(this);
		editor = new Editor();
		jScrollPane = new JScrollPane();
		regleta = new Regleta();
		regleta.setOffset(0);
		regleta.setFocusable(false);
		regleta.setMaxValor(Configuracion.getInstance().duracion);
		jScrollPane.setColumnHeaderView(regleta);
		botones = new PanelBotonesTablon();
		botones.setFocusable(false);
		botones.setPreferredSize(new Dimension(100, (int) Toolkit
				.getDefaultToolkit().getScreenSize().getHeight()));
		botones.setEditor(editor);
		jScrollPane.setRowHeaderView(botones);

		jScrollPane.setViewportView(editor);
		editor.setRegleta(regleta);
		editor.setBotones(botones);

		JPanel buttonCorner = new JPanel();
		buttonCorner.setLayout(new BorderLayout(0, 0));
		jScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, buttonCorner);
		Menu textos = Menu.getInstance();
		bAddPlank = new JButton(textos.listaBotones.get(3).t);
		bAddPlank.setFocusable(false);
		bAddPlank.addActionListener(this);
		buttonCorner.add(bAddPlank, BorderLayout.CENTER);

		editor.setVisible(true);
		getContentPane().add(jScrollPane, BorderLayout.CENTER);
		initMusicos(true);
		initPanelSonido();
		editor.setPanelSonido(panelSonido);

		this.setResizable(true);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setIconifiable(true);
		editor.setDuracion(Configuracion.getInstance().duracion);
		this.setVisible(true);
		editor.addTablon();
		this.addInternalFrameListener(this);
		editor.panelObservaciones = editorPane;
	}

	private void initMusicos(boolean nueva) {
		if (nueva) {
			musicos = new ListaMusicos();
			musicos.addMusico(Color.black);
			musicos.addMusico(Color.red);
		}
		verticalBox = Box.createVerticalBox();
		getContentPane().add(verticalBox, BorderLayout.NORTH);

		horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		verticalBox_1 = Box.createVerticalBox();
		horizontalBox.add(verticalBox_1);
		JScrollPane panelMusicos = new JScrollPane(musicos);
		verticalBox_1.add(panelMusicos);

		panelMusicos.setSize(200, 60);
		panelMusicos.setPreferredSize(new Dimension(200, 90));
		panelMusicos.setMaximumSize(new Dimension(200, 90));
		panelMusicos
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelMusicos
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		horizontalStrut = Box.createHorizontalStrut(200);
		verticalBox_1.add(horizontalStrut);

		editorPane = new JEditorPane();
		scrollPane = new JScrollPane(editorPane);
		horizontalBox.add(scrollPane);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null));

		verticalStrut = Box.createVerticalStrut(90);
		horizontalBox.add(verticalStrut);

		btnNewMusician = musicos.btnNewMusico;
		btnDelMusician = musicos.btnDelMusico;
		btnNewMusician.addActionListener(this);
		btnDelMusician.addActionListener(this);
		horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		horizontalBox_1.add(btnNewMusician);
		horizontalBox_1.add(btnDelMusician);

		horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_3);
		btnNewMusician.setHorizontalAlignment(SwingConstants.LEFT);
		musicos.setEditor(editor);

	}

	private void initPanelSonido() {

		editor.setListaMusicos(musicos);

		panelSonido = new PanelSonido(editor);
		panelSonido.btnPlay.addActionListener(this);
		panelSonido.btnStop.addActionListener(this);
		panelSonido.btnAjustarGolpes.addActionListener(this);
		getContentPane().add(panelSonido, BorderLayout.SOUTH);
	}

	public EditorWindow(int duracion, File file, ArrayList<Tablon> listaT,
			ArrayList<Musico> listaM, ArrayList<Golpe> listaG,
			String observaciones, int gramaje, int ritmo) {
		super();

		String nombreFrame = file.getName();
		this.setTitle(nombreFrame);
		storePath = file.getAbsolutePath();
		this.setSize(500, 400);
		musicos = new ListaMusicos(listaM);

		editor = new Editor(listaT, listaG);
		jScrollPane = new JScrollPane();
		regleta = new Regleta();
		regleta.setOffset(0);
		regleta.setFocusable(false);
		regleta.setMaxValor(7000);
		jScrollPane.setColumnHeaderView(regleta);
		botones = new PanelBotonesTablon();
		botones.setFocusable(false);
		botones.setPreferredSize(new Dimension(100, (int) Toolkit
				.getDefaultToolkit().getScreenSize().getHeight()));
		botones.setEditor(editor);
		jScrollPane.setRowHeaderView(botones);

		jScrollPane.setViewportView(editor);
		regleta.setMaxValor(duracion);
		editor.setRegleta(regleta);
		editor.setBotones(botones);
		editor.setListaMusicos(musicos);

		JPanel buttonCorner = new JPanel();
		buttonCorner.setLayout(new BorderLayout(0, 0));
		jScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, buttonCorner);

		bAddPlank = new JButton("Add plank");
		buttonCorner.add(bAddPlank, BorderLayout.CENTER);
		bAddPlank.setFocusable(false);
		bAddPlank.addActionListener(this);

		editor.setVisible(true);
		getContentPane().add(jScrollPane, BorderLayout.CENTER);
		// getContentPane().add(musicos, BorderLayout.NORTH);

		this.setResizable(true);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setIconifiable(true);

		this.setVisible(true);
		editor.actualizarBotones();

		initPanelSonido();
		initMusicos(false);
		editor.setListaMusicos(musicos);
		editor.setPanelSonido(panelSonido);
		editor.setDuracion(duracion);
		musicos.setEditor(editor);

		editorPane.setText(observaciones);
		panelSonido.redondeo.setText(gramaje + "");
		panelSonido.setHitsPerCompas(ritmo);
		editor.panelObservaciones = editorPane;
	}

	@Override
	protected void paintComponent(Graphics g) {
		editor.repaint();
	}

	public void addTablon() {
		editor.addTablon();
	}

	public void seleccionarTablonSiguiente() {
		editor.seleccionarTablonSiguiente();
	}

	public void seleccionarTablonAnterior() {
		editor.seleccionarTablonAnterior();
	}

	public void reproducir() {
		editor.reproducir(musicos);
	}

	public void pararReproduccion() {
		editor.pararRepdoduccion();
	}

	public void deshacer() {
		editor.deshacer();
	}

	public void rehacer() {
		editor.rehacer();
	}

	public void ajustarAVentana() {
		editor.ajustarAVentana();
	}

	public void subirTablonSeleccionado() {
		editor.subirTablonSeleccionado();
	}

	public void bajarTablonSeleccionado() {
		editor.bajarTablonSeleccionado();
	}

	public void eliminarGolpesSeleccionados() {
		editor.eliminarGolpesSeleccionados();
	}

	public void setModoLinea(boolean activado) {
		editor.setModoLinea(activado);
	}

	public void removeTablonSeleccionado() {
		editor.removeTablonSeleccionado();
	}

	public void cambiarColorTablonSeleccionado() {
		editor.cambiarColorTablon();
	}

	public void cambiarTonoTablonSeleccionado() {
		editor.cambiarTonoTablon();
	}

	public void setDuracion(int valor) {
		editor.setDuracion(valor);
	}

	public void duplicateSelected() {
		editor.duplicateSelected();

	}

	public void selectAll() {
		editor.selectAll();

	}

	public void crearImagen() {

		PrintCanvas p = new PrintCanvas(editor.getListaGolpes(),
				panelSonido.getRedondeo(), panelSonido.getHitsPerCompas(),
				regleta.getMaxValor());
		p.observaciones = editorPane.getText();
		dialogExportar dialog = new dialogExportar(regleta.getMaxValor(),panelSonido.getHitsPerCompas() );
		dialog.printCanvas = p;
		dialog.titulo = this.title.replace(".xml", "");
		dialog.listaTablones = editor.getListaTablones();
		dialog.mostrar();
		// p.dibujar(this.title.replace(".xml", ""), editor.getListaTablones(),
		// true);
	}

	public void zoomIn() {
		editor.zoomIn();
	}

	public void zoomOut() {
		editor.zoomOut();
	}

	public void save() {
		if (storePath != null)
			editor.saveAs(storePath, editorPane.getText(),
					Integer.parseInt(panelSonido.redondeo.getText()),
					panelSonido.getHitsPerCompas());
		else {
			saveAs();
		}
	}

	public void saveAs() {
		File nombre = obtenerFicheroGuardado();
		if (nombre != null && nombre.exists()) {
			int dialogResult = JOptionPane.showConfirmDialog(null,
					Menu.getInstance().listaAlerts.get(4).t, "Warning", 0);
			if (dialogResult == JOptionPane.NO_OPTION) {
				return;
			}
		}
		if (nombre != null) {
			editor.saveAs(nombre.getPath(), editorPane.getText(),
					Integer.parseInt(panelSonido.redondeo.getText()),
					panelSonido.getHitsPerCompas());
			this.storePath = nombre.getPath();
			this.title = nombre.getName();
			this.repaint();
		}
	}

	private File obtenerFicheroGuardado() {
		JFileChooser chooser = ChooserManager.getInstance().sheetChooser;
		chooser.setSelectedFile(new File("Sheet.xml"));
		int respuesta = chooser.showSaveDialog(null);
		if (respuesta == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		} else {
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == bAddPlank) {
			editor.addTablon();
		}
		if (arg0.getSource() == panelSonido.btnPlay) {
			editor.reproducir(musicos);
		}

		if (arg0.getSource() == panelSonido.btnStop) {
			editor.pararRepdoduccion();

		}
		if (arg0.getSource() == panelSonido.btnAjustarGolpes) {
			editor.redondearGolpes();
		}

		if (arg0.getSource() == btnNewMusician) {
			musicos.addMusico();
			musicos.revalidate();
		}
		if (arg0.getSource() == btnDelMusician) {
			editor.eliminarGolpes(musicos.mSeleccionado);
			musicos.removeMusico();
			musicos.revalidate();

		}

	}

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		editor.pararRepdoduccion();
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
		editor.pararRepdoduccion();
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public void eliminarGolpesDuplicados(){
		editor.eliminarGolpesDuplicados();
	}

	public int getDuracion() {
		return editor.getDuracion();
	}
}
