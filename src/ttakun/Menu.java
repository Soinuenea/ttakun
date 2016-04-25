package Txalaparta;

import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.KeyStroke;

public class Menu {

	private static Menu instance;
	
	// public String textosMenuFile[];
	// public String tooltipMenuFile[];
	// public String textosMenuEdit[];
	// public String tooltipMenuEdit[];
	// public String textosMenuInsert[];
	// public String tooltipMenuInsert[];
	// public String textosMenuView[];
	// public String tooltipMenuView[];
	// public String textosMenuSettings[];
	// public String tooltipMenuSettings[];
	// public String textosBotones[];
	public ArrayList<Evento> listaEventos = new ArrayList<Evento>();
	public String textoPartitura = "New sheet";
	public String shortcutId[];

	public ArrayList<Texto> menuFile, menuEdit, menuInsert, menuView,
			listaBotones, menuSettings, panelSonido, listaAlerts, panelMusico,
			panelAcercaDe, panelExportar, panelConfiguracion;

	public class Evento {
		public String sender;
		public KeyStroke key;

		public Evento(String s, KeyStroke k) {
			sender = s;
			key = k;
		}
	}

	public class Texto {
		public String t;
		public String ttip;

		public Texto() {

		}

		public Texto(String texto, String tooltip) {
			t = texto;
			ttip = tooltip;
		}
	}

	private Menu() {

	}

	private Menu(String path) {
		cargarTextos_Eventos(path);
	}

	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu(null);
		}
		return instance;
	}

	public static Menu getInstance(String path) {
		if (instance == null) {
			instance = new Menu(path);
		}
		return instance;
	}

	private void cargarTextos_Eventos(String path) {
//      TEXTOS DEL MENU FILE
		menuFile = new ArrayList<Texto>();
		menuFile.add(new Texto("File", ""));
		menuFile.add(new Texto("New", "Creates a new sheet"));
		menuFile.add(new Texto("Open", "Opens a sheet from a file"));
		menuFile.add(new Texto("Save",
				"Saves the current sheet overwritting if exists"));
		menuFile.add(new Texto("Save as",
				"Saves the current sheet to a new file"));
		menuFile.add(new Texto("Close", "Closes the current sheet"));
		menuFile.add(new Texto("Export to image",
				"Exports the current sheet to image file"));

//      TEXTOS DEL MENU EDIT
		menuEdit = new ArrayList<Texto>();
		menuEdit.add(new Texto("Edit", ""));
		menuEdit.add(new Texto("Undo", "Undoes the last action"));
		menuEdit.add(new Texto("Redo", "Redoes an action that was undone"));
		menuEdit.add(new Texto("Next plank", "Sets the next plank as selected"));
		menuEdit.add(new Texto("Previous plank",
				"Sets the previous plank as selected"));
		menuEdit.add(new Texto("Delete selected hits",
				"Deletes the selected hits"));
		menuEdit.add(new Texto("Select all", "Select all the hits of the sheet"));
		menuEdit.add(new Texto("Delete duplicate hits", "Deletes all the hits of the sheet that are duplicated"));

//      TEXTOS DEL MENU INSERT
		menuInsert = new ArrayList<Texto>();
		menuInsert.add(new Texto("Sheet", ""));
		menuInsert.add(new Texto("Add plank", "Adds a new plank to the sheet"));
		menuInsert.add(new Texto("Remove selected plank",
				"Removes the selected plank from the sheet"));
		menuInsert.add(new Texto("Move plank down",
				"Moves the selected plank 1 position down"));
		menuInsert.add(new Texto("Move plank up",
				"Moves the selected plank 1 position up"));
		menuInsert.add(new Texto("Change color",
				"Changes the visual color of the selected plank"));
		menuInsert.add(new Texto("Change tone",
				"Changes the tone of the selected plank"));
		menuInsert.add(new Texto("Set duration",
				"Sets the duration of the sheet in miliseconds"));
		menuInsert.add(new Texto("Play", "Plays the current sheet"));

//      TEXTOS DEL MENU VIEW
		menuView = new ArrayList<Texto>();
		menuView.add(new Texto("View", ""));
		menuView.add(new Texto("Zoom in", "Increases the zoom"));
		menuView.add(new Texto("Zoom out", "Reduces the zoom"));
		menuView.add(new Texto("Fit to view",
				"Fits the zoom to the window size"));
		menuView.add(new Texto("Change style",
				"Switchs between LineView and BoxView"));

//                  TEXTOS DEL MENU SETTINGS
		menuSettings = new ArrayList<Texto>();
		menuSettings.add(new Texto("Settings", ""));
		menuSettings.add(new Texto("Configuration",
				"Manages the configuration of the application"));
		menuSettings.add(new Texto("About us", ""));
		
//      TEXTOS DEL PANEL DE SONIDO 
		panelSonido = new ArrayList<Texto>();
		panelSonido.add(new Texto("Ritmical weight", ""));
		panelSonido.add(new Texto("Beats by compas", ""));
		panelSonido.add(new Texto("Speed", ""));
		panelSonido.add(new Texto("Loop", ""));
		panelSonido.add(new Texto("Hit strength", ""));
		panelSonido.add(new Texto("Playback start", ""));
		panelSonido.add(new Texto("Duration", ""));

//		      TEXTOS DE LA LISTA DE BOTONES
		listaBotones = new ArrayList<Texto>();
		listaBotones.add(new Texto("Fit hits",
				"Fits the hits to the ritmical weight"));
		listaBotones.add(new Texto("Play", "Plays the current sheet"));
		listaBotones.add(new Texto("Stop", ""));
		listaBotones
				.add(new Texto("Add plank", "Add a new plank to the sheet"));
		listaBotones.add(new Texto("New musician",
				"Add a new musician to the sheet"));
		listaBotones.add(new Texto("Change",
				"Changes the duration of the sheet"));
		listaBotones.add(new Texto("Remove musician",
				"Removes a musician from the sheet"));

//		TEXTOS DEL ACCESO DIRECTO
		shortcutId = new String[1];
		shortcutId[0] = "Duplicate selected";

//      TEXTOS DE LOS ALERTS
		listaAlerts = new ArrayList<Texto>();
		listaAlerts
				.add(new Texto("You must restart to apply the changes.", ""));
		listaAlerts.add(new Texto("Choose a tone for the plank.", ""));
		listaAlerts.add(new Texto("Choose a color for the plank.", ""));
		listaAlerts.add(new Texto("Choose a color for the musician.", ""));
		listaAlerts.add(new Texto("Overwrite the file?", ""));
//      TEXTOS DEL PANEL DE MUSICOS 
		panelMusico = new ArrayList<Texto>();
		panelMusico.add(new Texto("Musician", ""));
		panelMusico.add(new Texto("Toggle mute", ""));
//      TEXTOS DEL ACERCA DE
		panelAcercaDe = new ArrayList<Texto>();
		panelAcercaDe.add(new Texto("About us", ""));
		// cargarTextos("idioma\\spanish.txt");
		
//      TEXTOS DEL PANEL DE CONFIGURACION
		panelConfiguracion = new ArrayList<Texto>();
		panelConfiguracion.add(new Texto("Sheet folder", ""));
		panelConfiguracion.add(new Texto("Languaje file", ""));
		panelConfiguracion.add(new Texto("Export folder", ""));
		panelConfiguracion.add(new Texto("Default sound", ""));
		panelConfiguracion.add(new Texto("Default sheet duration", ""));
		panelConfiguracion.add(new Texto("Change", ""));
		//TEXTOS DEL PANEL DE EXPORTAR
		panelExportar = new ArrayList<Texto>();
		panelExportar.add(new Texto("Show plank numbers", ""));
		panelExportar.add(new Texto("Only 1 plank", ""));
		panelExportar.add(new Texto("Compass per line", ""));
		panelExportar.add(new Texto("Distance between lines", ""));
		cargarTextos(path);
		cargarEventos();
		
	}

	private void cargarTextos(String path) {
		if (path != null) {
			FileReader fr;
			BufferedReader in;
			try {
				fr = new FileReader(path);
				in = new BufferedReader(fr);
				for (int i = 0; i < menuFile.size(); i++) {
					Texto t = menuFile.get(i);
					t.t = in.readLine();
					t.ttip = in.readLine();
				}

				for (int i = 0; i < menuEdit.size(); i++) {
					Texto t = menuEdit.get(i);
					t.t = in.readLine();
					t.ttip = in.readLine();
				}

				for (int i = 0; i < menuInsert.size(); i++) {
					Texto t = menuInsert.get(i);
					t.t = in.readLine();
					t.ttip = in.readLine();
				}

				for (int i = 0; i < menuView.size(); i++) {
					Texto t = menuView.get(i);
					t.t = in.readLine();
					t.ttip = in.readLine();
				}
				for (int i = 0; i < menuSettings.size(); i++) {
					Texto t = menuSettings.get(i);
					t.t = in.readLine();
					t.ttip = in.readLine();
				}

				for (int i = 0; i < panelSonido.size(); i++) {
					Texto t = panelSonido.get(i);
					t.t = in.readLine();
					t.ttip = in.readLine();
				}

				for (int i = 0; i < listaBotones.size(); i++) {
					Texto t = listaBotones.get(i);
					t.t = in.readLine();
					t.ttip = in.readLine();
				}

				for (int i = 0; i < listaAlerts.size(); i++) {
					Texto t = listaAlerts.get(i);
					t.t = in.readLine();
					t.ttip = in.readLine();
				}

				for (int i = 0; i < panelMusico.size(); i++) {
					Texto t = panelMusico.get(i);
					t.t = in.readLine();
					t.ttip = in.readLine();
				}
				
				for (int i = 0; i < panelConfiguracion.size(); i++) {
					Texto t = panelConfiguracion.get(i);
					t.t = in.readLine();
					t.ttip = in.readLine();
				}

				for (int i = 0; i < panelExportar.size(); i++) {
					Texto t = panelExportar.get(i);
					t.t = in.readLine();
					t.ttip = in.readLine();
				}
				in.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void cargarEventos() {
		listaEventos.add(new Evento(menuFile.get(1).t, KeyStroke.getKeyStroke(
				KeyEvent.VK_N, Toolkit.getDefaultToolkit()
						.getMenuShortcutKeyMask())));
		listaEventos.add(new Evento(menuEdit.get(1).t, KeyStroke.getKeyStroke(
				KeyEvent.VK_Z, Toolkit.getDefaultToolkit()
						.getMenuShortcutKeyMask())));
		listaEventos.add(new Evento(menuEdit.get(2).t, KeyStroke.getKeyStroke(
				KeyEvent.VK_Y, Toolkit.getDefaultToolkit()
						.getMenuShortcutKeyMask())));
		listaEventos.add(new Evento(menuEdit.get(3).t, KeyStroke.getKeyStroke(
				KeyEvent.VK_DOWN, 0)));
		listaEventos.add(new Evento(menuEdit.get(4).t, KeyStroke.getKeyStroke(
				KeyEvent.VK_UP, 0)));
		listaEventos.add(new Evento(menuEdit.get(5).t, KeyStroke.getKeyStroke(
				KeyEvent.VK_DELETE, 0)));
		listaEventos.add(new Evento(shortcutId[0], KeyStroke.getKeyStroke(
				KeyEvent.VK_D, InputEvent.SHIFT_DOWN_MASK)));
		listaEventos.add(new Evento(menuEdit.get(6).t, KeyStroke.getKeyStroke(
				KeyEvent.VK_A, Toolkit.getDefaultToolkit()
						.getMenuShortcutKeyMask())));

	}
}
