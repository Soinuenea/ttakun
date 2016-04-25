package Txalaparta;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

	private FileWriter fs;
	private FileReader fr;
	private BufferedWriter out;
	private BufferedReader in;
	private String tagTablon = "<Tablon>";
	private String endtagTablon = "</Tablon>";
	private String tagGolpe = "<Golpe>";
	private String endtagGolpe = "</Golpe>";
	private String tagMusico = "<Musico>";
	private String endtagMusico = "</Musico>";
	private String tagDuracion = "<Duracion>";
	private String endtagDuracion = "</Duracion>";
	private String tagGramaje = "<Gramaje>";
	private String endtagGramaje = "</Gramaje>";
	private String tagRitmo = "<Ritmo>";
	private String endtagRitmo = "</Ritmo>";
	private String tagObservaciones = "<Observaciones>";
	private String endtagObservaciones = "</Observaciones>";

	public Parser() {

	}

	public void beginWriteTransaction(String path) {
		try {
			fs = new FileWriter(path);
			out = new BufferedWriter(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void beginReadTransaction(String path) {
		try {
			fr = new FileReader(path);
			in = new BufferedReader(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void endWriteTransaction() {
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void endReadTransaction() {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeDuracion(int duracion) {

		try {
			out.write(tagDuracion + "\n");
			out.write(duracion + "\n");
			out.write(endtagDuracion + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getDuracion() {
		String linea;

		try {
			while ((linea = in.readLine()) != null) {
				if (linea.equals(tagDuracion)) {
					int d = Integer.parseInt(in.readLine());
					return d;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public int getGramaje() {
		String linea;

		try {
			while ((linea = in.readLine()) != null) {
				if (linea.equals(tagGramaje)) {
					int d = Integer.parseInt(in.readLine());
					return d;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public int getRitmo() {
		String linea;

		try {
			while ((linea = in.readLine()) != null) {
				if (linea.equals(tagRitmo)) {
					int d = Integer.parseInt(in.readLine());
					return d;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public String getObservaciones() {
		String linea;

		try {
			while ((linea = in.readLine()) != null) {
				if (linea.equals(tagObservaciones)) {
					String texto = "";
					String linea2 = in.readLine();
					while (!linea2.equals(endtagObservaciones)
							&& linea2 != null) {
						texto = texto + linea2 + "\n";
						linea2 = in.readLine();
					}
					return texto;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void writeGolpe(Golpe g) {
		try {
			out.write(tagGolpe + "\n");
			out.write(g.momento + "\n");
			out.write(g.idTablon + "\n");
			out.write(g.idMusico + "\n");
			out.write(g.fuerza + "\n");
			out.write(g.getHaciaArriba() + "\n");
			out.write(g.cNormal.toString() + "\n");
			out.write(endtagGolpe + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Golpe> getGolpes() {
		ArrayList<Golpe> lista = new ArrayList<Golpe>();
		String linea;
		try {
			while ((linea = in.readLine()) != null) {
				if (linea.equals(tagGolpe)) {
					int momento = Integer.parseInt(in.readLine());
					int idTablon = Integer.parseInt(in.readLine());
					int idMusico = Integer.parseInt(in.readLine());
					int fuerza = Integer.parseInt(in.readLine());
					String bool = in.readLine();
					boolean haciaArriba = true;
					if (bool.equals("false")) {
						haciaArriba = false;
					}

					Color c = getColor(in.readLine());
					lista.add(new Golpe(idMusico, idTablon, momento,
							haciaArriba, true, c, fuerza));
				}
			}

			return lista;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void writeTablon(Tablon t) {
		try {
			out.write(tagTablon + "\n");
			out.write(t.getId() + "\n");
			out.write(t.getColor() + "\n");
			String aux = t.getTono();
			String path = aux
					.replace(Configuracion.getInstance().pathBase, ".");
			out.write(path + "\n");
			// out.write(t.getTono() + "\n");
			out.write(t.posY + "\n");
			out.write(t.getGrosor() + "\n");
			out.write(endtagTablon + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Tablon> getTablones() {
		ArrayList<Tablon> lista = new ArrayList<Tablon>();
		String linea;
		try {
			while ((linea = in.readLine()) != null) {
				if (linea.equals(tagTablon)) {
					int id = Integer.parseInt(in.readLine());
					Color c = getColor(in.readLine());
					String tono = in.readLine();
					int posY = Integer.parseInt(in.readLine());
					int grosor = Integer.parseInt(in.readLine());
					lista.add(new Tablon(id, c, tono, posY, grosor));
				}
			}

			return lista;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

	public void writeMusico(Musico m) {
		try {
			out.write(tagMusico + "\n");
			out.write(m.getId() + "\n");
			out.write(m.getColor() + "\n");
			out.write(endtagMusico + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Musico> getMusicos() {
		ArrayList<Musico> lista = new ArrayList<Musico>();
		String linea;
		try {
			while ((linea = in.readLine()) != null) {
				if (linea.equals(tagMusico)) {
					int id = Integer.parseInt(in.readLine());
					Color c = getColor(in.readLine());
					lista.add(new Musico(id, c));
				}
			}

			return lista;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

	public Color getColor(String textoColor) {
		String texto = textoColor;
		texto = texto.replace("java.awt.Color[r=", "");
		texto = texto.replace("g=", "");
		texto = texto.replace("b=", "");
		texto = texto.replace("]", "");
		String[] color = texto.split(",");
		return new Color(Integer.parseInt(color[0]),
				Integer.parseInt(color[1]), Integer.parseInt(color[2]));
	}

	public void writeRitmo(int valor) {
		try {
			out.write(tagRitmo + "\n");
			out.write(valor + "\n");
			out.write(endtagRitmo + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeObservaciones(String texto) {
		try {
			out.write(tagObservaciones + "\n");
			out.write(texto + "\n");
			out.write(endtagObservaciones + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeGramaje(int valor) {
		try {
			out.write(tagGramaje + "\n");
			out.write(valor + "\n");
			out.write(endtagGramaje + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
