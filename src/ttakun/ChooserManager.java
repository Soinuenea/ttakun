package Txalaparta;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class ChooserManager {

	public JFileChooser soundChooser, sheetChooser, exportChooser,
			languajeChooser;
	FileFilter soundFilter = new ExtensionFileFilter("WAV files",
			new String[] { "WAV" });
	FileFilter sheetFilter = new ExtensionFileFilter("Sheets",
			new String[] { "XML" });
	FileFilter languajeFilter = new ExtensionFileFilter("TXT files",
			new String[] { "TXT" });
	private static ChooserManager instancia;

	public static ChooserManager getInstance() {
		if (instancia == null)
			instancia = new ChooserManager();
		return instancia;
	}

	private ChooserManager() {
		soundChooser = new JFileChooser(
				Configuracion.getInstance().folderDefaultSound);
		exportChooser = new JFileChooser(
				Configuracion.getInstance().folderDefaultExport);
		sheetChooser = new JFileChooser(
				Configuracion.getInstance().folderDefaultSheet);
		languajeChooser = new JFileChooser(
				Configuracion.getInstance().folderDefaultLanguaje);

		soundChooser.setFileFilter(soundFilter);
		sheetChooser.setFileFilter(sheetFilter);
		languajeChooser.setFileFilter(languajeFilter);
		exportChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		soundChooser.setDialogTitle(Menu.getInstance().listaAlerts.get(1).t);

	}

}
