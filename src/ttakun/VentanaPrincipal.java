package Txalaparta;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.DesktopPaneUI;

public class VentanaPrincipal implements ActionListener {

	private JFrame frame;
	private Menu textosMenu;
	JDesktopPane desktopPane;
	private ArrayList<JButton> botones = new ArrayList<JButton>();
	private boolean modoLinea = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
					window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
		crearEditor(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 919, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(new ImageIcon(AcercaDe.class
				.getResource("/Resources/ttakun3.png")).getImage());
		frame.setTitle("Ttakun 3.0");

		desktopPane = new JDesktopPane();
		desktopPane.setUI(new DesktopPaneUI() {
			@Override
			public void installUI(JComponent c) {
				// TODO Auto-generated method stub
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (Exception e) {
					e.printStackTrace();
				}
				super.installUI(c);
			}
		});
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		Configuracion config = Configuracion.getInstance();
		textosMenu = Menu.getInstance(config.language);
		Box verticalBox = Box.createVerticalBox();
		frame.getContentPane().add(verticalBox, BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		JButton btnFileNew = new JButton("");
		btnFileNew.setToolTipText(Menu.getInstance().menuFile.get(1).ttip);
		btnFileNew.setIcon(new ImageIcon("Iconos\\new.png"));
		horizontalBox.add(btnFileNew);
		botones.add(btnFileNew);

		JButton btnFileOpen = new JButton("");
		btnFileOpen.setIcon(new ImageIcon("Iconos\\open.png"));
		btnFileOpen.setToolTipText(Menu.getInstance().menuFile.get(2).ttip);
		horizontalBox.add(btnFileOpen);
		botones.add(btnFileOpen);

		JButton btnFileSave = new JButton("");
		btnFileSave.setIcon(new ImageIcon("Iconos\\save.png"));
		btnFileSave.setToolTipText(Menu.getInstance().menuFile.get(3).ttip);
		horizontalBox.add(btnFileSave);
		botones.add(btnFileSave);

		JButton btnFileSaveAs = new JButton("");
		btnFileSaveAs.setIcon(new ImageIcon("Iconos\\saveAs.png"));
		btnFileSaveAs.setToolTipText(Menu.getInstance().menuFile.get(4).ttip);

		horizontalBox.add(btnFileSaveAs);
		botones.add(btnFileSaveAs);

		JButton btnFileClear = new JButton("");
		btnFileClear.setIcon(new ImageIcon("Iconos\\clear.png"));
		btnFileClear.setToolTipText(Menu.getInstance().menuEdit.get(7).ttip);
		horizontalBox.add(btnFileClear);
		botones.add(btnFileClear);

		// Component horizontalStrut = Box.createHorizontalStrut(20);
		// horizontalBox.add(horizontalStrut);
		//
		// JButton btnCut = new JButton("");
		// btnCut.setIcon(new ImageIcon("Iconos\\cut.png"));
		// horizontalBox.add(btnCut);
		// botones.add(btnCut);
		//
		// JButton btnCopy = new JButton("");
		// btnCopy.setIcon(new ImageIcon("Iconos\\copy.png"));
		// horizontalBox.add(btnCopy);
		// botones.add(btnCopy);
		//
		// JButton btnPaste = new JButton("");
		// btnPaste.setIcon(new ImageIcon("Iconos\\paste.png"));
		// horizontalBox.add(btnPaste);
		// botones.add(btnPaste);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_1);

		JButton btnUndo = new JButton("");
		btnUndo.setIcon(new ImageIcon("Iconos\\undoDisabled.png"));
		btnUndo.setToolTipText(Menu.getInstance().menuEdit.get(1).ttip);
		horizontalBox.add(btnUndo);
		botones.add(btnUndo);

		JButton btnRedo = new JButton("");
		btnRedo.setIcon(new ImageIcon("Iconos\\redoDisabled.png"));
		btnRedo.setToolTipText(Menu.getInstance().menuEdit.get(2).ttip);
		horizontalBox.add(btnRedo);
		botones.add(btnRedo);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_2);

		JButton btnZoomIn = new JButton("");
		btnZoomIn.setIcon(new ImageIcon("Iconos\\zoomIn.png"));
		btnZoomIn.setToolTipText(Menu.getInstance().menuView.get(1).ttip);
		horizontalBox.add(btnZoomIn);
		botones.add(btnZoomIn);

		JButton btnZoomOut = new JButton("");
		btnZoomOut.setIcon(new ImageIcon("Iconos\\zoomOut.png"));
		btnZoomOut.setToolTipText(Menu.getInstance().menuView.get(2).ttip);
		horizontalBox.add(btnZoomOut);
		botones.add(btnZoomOut);

		JButton btnZoomFit = new JButton("");
		btnZoomFit.setIcon(new ImageIcon("Iconos\\fit.png"));
		btnZoomFit.setToolTipText(Menu.getInstance().menuView.get(3).ttip);
		horizontalBox.add(btnZoomFit);
		botones.add(btnZoomFit);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_1);

		for (int i = 0; i < botones.size(); i++) {
			botones.get(i).addActionListener(this);
		}

		JMenu menuFile = new JMenu(textosMenu.menuFile.get(0).t);
		menuBar.add(menuFile);
		// menuFile.setMnemonic(KeyEvent.VK_F);
		for (int i = 1; i < textosMenu.menuFile.size(); i++) {
			JMenuItem item = new JMenuItem(textosMenu.menuFile.get(i).t);
			menuFile.add(item);
			item.addActionListener(this);
			item.setToolTipText(textosMenu.menuFile.get(i).ttip);
		}

		JMenu menuEdit = new JMenu(textosMenu.menuEdit.get(0).t);
		menuBar.add(menuEdit);
		for (int i = 1; i < textosMenu.menuEdit.size(); i++) {
			JMenuItem item = new JMenuItem(textosMenu.menuEdit.get(i).t);
			menuEdit.add(item);
			item.addActionListener(this);
			item.setToolTipText(textosMenu.menuEdit.get(i).ttip);
		}

		JMenu menuInsert = new JMenu(textosMenu.menuInsert.get(0).t);
		menuBar.add(menuInsert);
		for (int i = 1; i < textosMenu.menuInsert.size(); i++) {
			JMenuItem item = new JMenuItem(textosMenu.menuInsert.get(i).t);
			menuInsert.add(item);
			item.addActionListener(this);
			item.setToolTipText(textosMenu.menuInsert.get(i).ttip);
		}

		JMenu menuView = new JMenu(textosMenu.menuView.get(0).t);
		menuBar.add(menuView);

		for (int i = 1; i < textosMenu.menuView.size(); i++) {
			JMenuItem item = new JMenuItem(textosMenu.menuView.get(i).t);
			menuView.add(item);
			item.addActionListener(this);
			item.setToolTipText(textosMenu.menuView.get(i).ttip);
		}

		JMenu menuSettings = new JMenu(textosMenu.menuSettings.get(0).t);
		menuBar.add(menuSettings);
		// menuFile.setMnemonic(KeyEvent.VK_F);
		for (int i = 1; i < textosMenu.menuSettings.size(); i++) {
			JMenuItem item = new JMenuItem(textosMenu.menuSettings.get(i).t);
			menuSettings.add(item);
			item.addActionListener(this);
			item.setToolTipText(textosMenu.menuSettings.get(i).ttip);
		}

		for (int i = 0; i < textosMenu.listaEventos.size(); i++) {
			Menu.Evento aux = textosMenu.listaEventos.get(i);
			registerActions(aux.sender, aux.key);
		}

		desktopPane.setInputMap(1, desktopPane.getInputMap());
	}

	public void registerActions(final String texto, KeyStroke key) {
		Action myAction = new AbstractAction() {
			String sender = texto;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				globalHandler(sender);
			}
		};
		desktopPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key,
				texto);
		desktopPane.getActionMap().put(texto, myAction);
	}

	public void crearEditor(boolean maximizado) {
		EditorWindow internalFrame = new EditorWindow(textosMenu.textoPartitura);
		desktopPane.add(internalFrame);
		desktopPane.setSelectedFrame(internalFrame);

		if (maximizado) {
			try {
				internalFrame.setMaximum(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}

		}
		internalFrame.setVisible(true);
		desktopPane.repaint();

	}

	private void crearEditor(boolean maximizado, int duracion, File file,
			ArrayList<Tablon> listaT, ArrayList<Musico> listaM,
			ArrayList<Golpe> listaG, String observaciones, int gramaje,
			int ritmo) {
		EditorWindow internalFrame = new EditorWindow(duracion, file, listaT,
				listaM, listaG, observaciones, gramaje, ritmo);
		desktopPane.add(internalFrame);
		desktopPane.setSelectedFrame(internalFrame);

		if (maximizado) {
			try {
				internalFrame.setMaximum(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}

		}
		internalFrame.setVisible(true);
		desktopPane.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String sender = e.getActionCommand();
		if (!handleButtons(e.getSource())) {
			globalHandler(sender);
		}

	}

	private void globalHandler(String sender) {
		if (handleShortcuts(sender) || handleEditMenu(sender)
				|| handleViewMenu(sender) || handleInsertMenu(sender)
				|| handleFileMenu(sender) || handleSettingsMenu(sender)) {
//			System.out.println("Accion \"" + sender + "\" tratada");
		} else {
//			System.out.println("Accion \"" + sender + "\" sin tratar");
		}
	}

	private boolean handleFileMenu(String sender) {
		if (sender == textosMenu.menuFile.get(1).t) {
			if (desktopPane.getSelectedFrame() != null) {
				try {
					desktopPane.getSelectedFrame().setIcon(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
			crearEditor(true);
			return true;
		}
		if (sender == textosMenu.menuFile.get(2).t) {
			cargarPartitura();
			return true;
		}
		if (sender == textosMenu.menuFile.get(3).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.save();
			return true;
		}
		if (sender == textosMenu.menuFile.get(4).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();

			editorSeleccionado.saveAs();
			return true;
		}
		if (sender == textosMenu.menuFile.get(5).t) {
			if (desktopPane.getSelectedFrame() != null) {
				desktopPane.remove(desktopPane.getSelectedFrame());
				desktopPane.repaint();
			}
			return true;
		}

		if (sender == textosMenu.menuFile.get(6).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.crearImagen();
			return true;
		}

		return false;
	}

	private boolean handleEditMenu(String sender) {
		if (sender == textosMenu.menuEdit.get(1).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.deshacer();
			return true;
		}

		if (sender == textosMenu.menuEdit.get(2).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.rehacer();
			return true;
		}

		if (sender == textosMenu.menuEdit.get(3).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.seleccionarTablonSiguiente();
			return true;
		}

		if (sender == textosMenu.menuEdit.get(4).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.seleccionarTablonAnterior();
			return true;
		}

		if (sender == textosMenu.menuEdit.get(5).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.eliminarGolpesSeleccionados();
			return true;
		}

		if (sender == textosMenu.menuEdit.get(6).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.selectAll();
			return true;
		}
		
		if (sender == textosMenu.menuEdit.get(7).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.eliminarGolpesDuplicados();
			return true;
		}
		return false;
	}

	private boolean handleInsertMenu(String sender) {
		if (sender == textosMenu.menuInsert.get(1).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.addTablon();
			return true;
		}
		if (sender == textosMenu.menuInsert.get(2).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.removeTablonSeleccionado();
			return true;
		}
		if (sender == textosMenu.menuInsert.get(3).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.subirTablonSeleccionado();
			return true;
		}
		if (sender == textosMenu.menuInsert.get(4).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.bajarTablonSeleccionado();
			return true;
		}
		if (sender == textosMenu.menuInsert.get(5).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.cambiarColorTablonSeleccionado();
			return true;
		}
		if (sender == textosMenu.menuInsert.get(6).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.cambiarTonoTablonSeleccionado();
			return true;
		}
		if (sender == textosMenu.menuInsert.get(7).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			try {
				int valor = Integer.parseInt((String) JOptionPane
						.showInputDialog(null, "New duration (milliseconds)",
								"New duration", 1, null, null,
								editorSeleccionado.getDuracion()));
				editorSeleccionado.setDuracion(valor);
			} catch (Exception e) {

			}

			return true;
		}
		if (sender == textosMenu.menuInsert.get(8).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.reproducir();
			return true;
		}
		return false;
	}

	private boolean handleViewMenu(String sender) {
		if (sender == textosMenu.menuView.get(1).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.zoomIn();
			return true;
		}
		if (sender == textosMenu.menuView.get(2).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.zoomOut();
			return true;
		}
		if (sender == textosMenu.menuView.get(3).t) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.ajustarAVentana();
			return true;
		}

		if (sender == textosMenu.menuView.get(4).t) {
			modoLinea = !modoLinea;
			for (int i = 0; i < desktopPane.getComponentCount(); i++) {
				EditorWindow actual = (EditorWindow) desktopPane
						.getComponent(i);
				actual.setModoLinea(modoLinea);
			}
			return true;
		}
		return false;
	}

	private boolean handleSettingsMenu(String sender) {

		if (sender == textosMenu.menuSettings.get(1).t) {
			setWorkspace();
			return true;
		}

		if (sender == textosMenu.menuSettings.get(2).t) {
			AcercaDe aboutUs = new AcercaDe();
			aboutUs.setVisible(true);
			return true;
		}

		return false;
	}

	private boolean handleShortcuts(String sender) {
		if (sender == textosMenu.shortcutId[0]) {
			EditorWindow editorSeleccionado = (EditorWindow) desktopPane
					.getSelectedFrame();
			editorSeleccionado.duplicateSelected();
			return true;
		}
		return false;
	}

	private boolean handleButtons(Object object) {
		boolean respuesta = false;
		for (int i = 0; i < botones.size(); i++) {
			if (botones.get(i) == object) {
				switch (i) {
				case 0:// new
					handleFileMenu(textosMenu.menuFile.get(1).t);
					respuesta = true;
					break;
				case 1:// open
					handleFileMenu(textosMenu.menuFile.get(2).t);
					respuesta = true;
					break;
				case 2:// save
					handleFileMenu(textosMenu.menuFile.get(3).t);
					respuesta = true;
					break;
				case 3:// save as
					handleFileMenu(textosMenu.menuFile.get(4).t);
					respuesta = true;
					break;
				case 4://Clear
					handleEditMenu(textosMenu.menuEdit.get(7).t);
					respuesta = true;
					break;
				case 5: // undo
					handleEditMenu(textosMenu.menuEdit.get(1).t);
					respuesta = true;
					break;
				case 6: // redo
					handleEditMenu(textosMenu.menuEdit.get(2).t);
					respuesta = true;
					break;
				case 7: // zoomin
					handleViewMenu(textosMenu.menuView.get(1).t);
					respuesta = true;
					break;
				case 8: // zoomout
					handleViewMenu(textosMenu.menuView.get(2).t);
					respuesta = true;
					break;
				case 9: // Fit view
					handleViewMenu(textosMenu.menuView.get(3).t);
					respuesta = true;
					break;

				}
			}
		}
		return respuesta;
	}

	private void cargarPartitura() {
		JFileChooser openF = ChooserManager.getInstance().sheetChooser;
		if (openF.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
			return;

		File file = openF.getSelectedFile();

		if (file != null) {

			String path = file.getAbsolutePath();
			Parser parser = new Parser();
			parser.beginReadTransaction(path);
			int duracion = parser.getDuracion();
			parser.endReadTransaction();
			parser.beginReadTransaction(path);
			ArrayList<Tablon> listaT = parser.getTablones();
			parser.endReadTransaction();
			parser.beginReadTransaction(path);
			ArrayList<Musico> listaM = parser.getMusicos();
			parser.endReadTransaction();
			parser.beginReadTransaction(path);
			ArrayList<Golpe> listaG = parser.getGolpes();
			parser.endReadTransaction();
			parser.beginReadTransaction(path);
			String observaciones = parser.getObservaciones();
			parser.endReadTransaction();
			parser.beginReadTransaction(path);
			int gramaje = parser.getGramaje();
			parser.endReadTransaction();
			parser.beginReadTransaction(path);
			int ritmo = parser.getRitmo();
			parser.endReadTransaction();

			if (desktopPane.getSelectedFrame() != null) {
				try {
					desktopPane.getSelectedFrame().setIcon(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
			crearEditor(true, duracion, file, listaT, listaM, listaG,
					observaciones, gramaje, ritmo);

		}

	}

	private void setWorkspace() {
		// Configuracion config = Configuracion.getInstance();
		// JFileChooser chooser = ChooserManager.getInstance().sheetChooser;
		// chooser.setDialogTitle("Choose new directory");
		// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// //
		// // disable the "All files" option.
		// //
		// chooser.setAcceptAllFileFilterUsed(false);
		// if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		// config.folderDefaultSheet = chooser.getSelectedFile().getPath();
		// config.guardar();
		// }
		DialogConfigurar dialog = new DialogConfigurar();
		dialog.setVisible(true);
		dialog.cargar();
	}

}
