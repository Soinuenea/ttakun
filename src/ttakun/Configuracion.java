package Txalaparta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configuracion {
	 boolean resaltarTablones = true;
	private static Configuracion config;
	private static String path = "config.cfg";
	private static String beginWorkspace = "<workspace>";
	private static String endWorkspace = "</workspace>";
	private static String beginLanguage = "<language>";
	private static String endLanguage = "</language>";
	private static String beginDuracion = "<duration>";
	private static String endDuracion = "</duration>";
	private static String beginDefaultSound = "<defaultSound>";
	private static String endDefaultSound = "</defaultSound>";
	private static String beginExportConfig = "<exportConfig>";
	private static String endExportConfig = "</exportConfig>";
	private static String beginResaltarTablones = "<resaltarTablones>";
	private static String endResaltarTablones = "</resaltarTablones>";

	public String folderDefaultSheet = "./";
	public String folderDefaultSound = "./hotsak";
	public String folderDefaultExport = "./";
	public String folderDefaultLanguaje = "./Idioma";

	public String language = "./Idioma/English.txt";

	public int duracion = 10000;
	public String sonidoDefault = "hotsak/default.wav";

	public int compasesPorFila = 5;
	public int offsetFilas = 50;
	public boolean mostrarNumeros = true;
	public boolean mostrarAgrupados = false;
	public String pathBase;

	private Configuracion() {
		File f = new File("");
		pathBase = f.getAbsolutePath();
	}

	public static Configuracion getInstance() {
		if (config == null) {
			config = new Configuracion();
			config.cargar();
		}
		return config;
	}

	private void cargar() {
		FileReader fr;
		BufferedReader in;
		try {
			fr = new FileReader(path);
			in = new BufferedReader(fr);
			String linea;
			while ((linea = in.readLine()) != null) {
				if (linea.equals(beginWorkspace)) {
					folderDefaultSheet = in.readLine();
					folderDefaultSound = in.readLine();
					folderDefaultExport = in.readLine();
					folderDefaultLanguaje = in.readLine();
				} else {
					if (linea.equals(beginLanguage)) {
						language = in.readLine();
					} else {
						if (linea.equals(beginDuracion)) {
							duracion = Integer.parseInt(in.readLine());
						} else {
							if (linea.equals(beginDefaultSound)) {
								sonidoDefault = in.readLine();
							} else {
								if (linea.equals(beginExportConfig)) {
									compasesPorFila = Integer.parseInt(in
											.readLine());
									offsetFilas = Integer.parseInt(in
											.readLine());
									mostrarNumeros = (1 == Integer.parseInt(in
											.readLine()));
									mostrarAgrupados = (1 == Integer
											.parseInt(in.readLine()));
								}else{
									if (linea.equals(beginResaltarTablones)){
										resaltarTablones = (1 == Integer.parseInt(in
												.readLine()));
									}
								}
								
							}
						}
					}

				}
			}
			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			guardar();
			e.printStackTrace();
		}

	}

	private int booleanToInt(boolean value) {
		if (value)
			return 1;
		else
			return 0;
	}

	public void guardar() {
		FileWriter fw;
		BufferedWriter out;
		try {
			fw = new FileWriter(path);
			out = new BufferedWriter(fw);
			out.write(beginWorkspace + "\n" + folderDefaultSheet + "\n"
					+ folderDefaultSound + "\n" + folderDefaultExport + "\n"
					+ folderDefaultLanguaje + "\n" + endWorkspace + "\n");
			out.write(beginLanguage + "\n" + language + "\n" + endLanguage
					+ "\n");
			out.write(beginDuracion + "\n" + duracion + "\n" + endDuracion
					+ "\n");
			out.write(beginDefaultSound + "\n" + sonidoDefault + "\n"
					+ endDefaultSound + "\n");
			out.write(beginExportConfig + "\n" + compasesPorFila + "\n"
					+ offsetFilas + "\n" + booleanToInt(mostrarNumeros) + "\n"
					+ booleanToInt(mostrarAgrupados) + "\n" + endExportConfig + "\n");
			out.write(beginResaltarTablones + "\n" + booleanToInt(resaltarTablones) + "\n"
					+ endResaltarTablones + "\n");
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
