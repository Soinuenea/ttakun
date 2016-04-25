package Txalaparta;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.Scrollable;

public class Editor extends JPanel implements Scrollable, MouseMotionListener,
		MouseListener, MouseWheelListener {
	private Regleta regleta;
	private Partitura partitura;
	private PanelBotonesTablon botones;
	private int anchoMax, altoMax; // Ancho y alto maximos del editor
	private Point puntoOrigenSeleccion, puntoActualSeleccion;
	private int oldMomento;
	private boolean seleccionando = false;
	private boolean moviendo = false;
	private int tiempoMovido = 0;
	private Color cSeleccion = new Color(255, 255, 0, 100);
	public boolean changesSaved; // Indica si hemos guardado los cambios
	private PanelSonido panelSonido;
	private long comienzoReproduccion = -1;
	private Timer timerReproduccion;
	private ListaMusicos musicosReproduccion;
	private int anchoGolpes = 2;
	private ReproductorGolpes player;
	public JEditorPane panelObservaciones;

	// private Thread t; // thread que reproduce los players

	public Editor() {
		regleta = new Regleta();
		addMouseMotionListener(this);
		addMouseListener(this);
		addMouseWheelListener(this);
		anchoMax = regleta.getWidth();
		altoMax = 600;
		Dimension miDim = new Dimension(anchoMax, 600);
		partitura = new Partitura(this, 0, 0, miDim);

		this.setPreferredSize(this.getPreferredSize());
		puntoOrigenSeleccion = new Point();
		puntoActualSeleccion = new Point();
		changesSaved = true;
		this.setFocusable(false);
		// player = new ReproductorGolpes();
	}

	public Editor(ArrayList<Tablon> listaT, ArrayList<Golpe> listaG) {
		regleta = new Regleta();
		addMouseMotionListener(this);
		addMouseListener(this);
		addMouseWheelListener(this);
		anchoMax = regleta.getWidth();
		altoMax = 600;
		Dimension miDim = new Dimension(anchoMax, 500);
		partitura = new Partitura(this, 0, 0, miDim, listaG);
		this.setPreferredSize(this.getPreferredSize());
		puntoOrigenSeleccion = new Point();
		puntoActualSeleccion = new Point();
		for (int i = 0; i < listaT.size(); i++) {
			partitura.addTablon(listaT.get(i));
		}
		changesSaved = true;
		this.setFocusable(false);
		// player = new ReproductorGolpes();
	}

	public void setPanelSonido(PanelSonido p) {
		panelSonido = p;
	}

	public void setListaMusicos(ListaMusicos lista) {
		partitura.setListaMusicos(lista);
	}

	public void setBotones(PanelBotonesTablon b) {
		this.botones = b;
		partitura.setBotones(b);
	}

	public Dimension getPreferredSize() {
		return new Dimension(anchoMax, altoMax);

	}

	public Regleta getRegleta() {
		return regleta;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(anchoGolpes));
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		panelSonido.dibujarMarcaRedondeo(g2D, this.getWidth(),
				this.getHeight(), regleta);
		// regleta.dibujar(g);
		partitura.dibujar(g, regleta);
		if (seleccionando) {
			dibujarSeleccion(g);
		}
		if (comienzoReproduccion > 0) {
			java.util.Date date = new java.util.Date();
			int tiempoReproduccion = (int) ((date.getTime() - comienzoReproduccion)
					* panelSonido.getPlaybackSpeed() / 100);
			// player.reproducirMomento(tiempoReproduccion);
			if (tiempoReproduccion > regleta.getMaxValor() && player.finalizado) {
				if (panelSonido.chckbxLoop.isSelected()) {
					//System.out.println("Tiempo de reproduccion: " + tiempoReproduccion);
					this.reproducir(musicosReproduccion);
					tiempoReproduccion = 0;
					
				} else {
					if (tiempoReproduccion > regleta.getMaxValor())
						pararRepdoduccion();
				}
			}
			if (tiempoReproduccion != -1) {
				int posReproduccion = regleta.timeToPixel(player.getMomentoCursor());
				panelSonido.dibujarMarca(posReproduccion, g, this.getHeight());
			}

		}
	}

	private void dibujarSeleccion(Graphics g) {
		g.setColor(cSeleccion);
		int x = Math.min(puntoActualSeleccion.x, puntoOrigenSeleccion.x);
		int y = Math.min(puntoActualSeleccion.y, puntoOrigenSeleccion.y);
		int w = Math.abs(puntoActualSeleccion.x - puntoOrigenSeleccion.x);
		int h = Math.abs(puntoActualSeleccion.y - puntoOrigenSeleccion.y);
		g.fillRect(x, y, w, h);
		g.setColor(new Color(0, 0, 0, 100));
		g.drawRect(x, y, w, h);
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if (seleccionando) {
			puntoActualSeleccion.x = e.getX();
			puntoActualSeleccion.y = e.getY();
		}

		if (moviendo) {
			int momento = regleta.pixelToTime(e.getX());
			tiempoMovido += momento - oldMomento;

			partitura.moverGolpesSeleccionados(momento - oldMomento);
			oldMomento = momento;
			puntoOrigenSeleccion.x = e.getX();

		}
		this.mouseMoved(e);
		this.scrollRectToVisible(new Rectangle(Math.max(0, e.getX() - 10), 1,
				20, 1));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		regleta.setMousePos(e.getX());
		regleta.repaint();
		this.redibujar();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton() == MouseEvent.BUTTON1) {
			if (arg0.getX() <= regleta.getMaxPixel() && arg0.getX() >= 0) {
				int tiempo = regleta.pixelToTime(arg0.getX());
				if (tiempo >= 0 && tiempo <= regleta.getMaxValor()) {
					partitura
							.addGolpe(
									regleta.redondeo(tiempo,
											panelSonido.getRedondeo()),
									arg0.getY(),
									panelSonido.getFuerza(arg0.getY()), true);
					this.redibujar();
				}
				partitura.desSeleccionar();
			}
		} else {
			if (arg0.getButton() == MouseEvent.BUTTON2) {
				//System.out.println("Rueda mouse presionada");
				ajustarAVentana();

			} else {
				if (arg0.getButton() == MouseEvent.BUTTON3) {
					//System.out.println("Click derecho");
					partitura.desSeleccionar();
				}
			}
		}
		seleccionando = false;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		panelSonido.habilitarFocus(true);
		panelObservaciones.setFocusable(true);

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		panelSonido.habilitarFocus(false);
		panelObservaciones.setFocusable(false);
		if (arg0.getButton() == MouseEvent.BUTTON3) {
			seleccionando = true;
			if (!arg0.isControlDown())
				partitura.desSeleccionar();
			puntoOrigenSeleccion.x = arg0.getX();
			puntoOrigenSeleccion.y = arg0.getY();
			puntoActualSeleccion.x = arg0.getX();
			puntoActualSeleccion.y = arg0.getY();
		}
		if (arg0.getButton() == MouseEvent.BUTTON1) {
			partitura.comienzoMoverGolpesSeleccionados();
			moviendo = true;
			puntoOrigenSeleccion.x = arg0.getX();
			puntoOrigenSeleccion.y = arg0.getY();
			oldMomento = regleta.pixelToTime(arg0.getX());
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (seleccionando) {
			seleccionando = false;
			Point inicio = new Point(
					regleta.pixelToTime(puntoOrigenSeleccion.x),
					puntoOrigenSeleccion.y);
			Point fin = new Point(regleta.pixelToTime(puntoActualSeleccion.x),
					puntoActualSeleccion.y);
			partitura.seleccionar(2, inicio, fin);
			this.redibujar();
		}

		if (moviendo) {
			moviendo = false;
			partitura.finMoverGolpesSeleccionados(tiempoMovido);
			tiempoMovido = 0;
			this.redibujar();
		}

	}

	public void setRegleta(Regleta r) {
		regleta = r;
		anchoMax = regleta.getWidth();
		partitura.duracion = regleta.getMaxValor();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// Scroll respecto a la posicion del mouse

		int dif = e.getX() - getVisibleRect().x; // Obtenemos distancia en
													// pixeles desde el mouse al
													// comienzo de la pantalla
		int tiempo = regleta.pixelToTime(e.getX()); // Obtenemos el momento al
													// que apunta el mouse
		int aux = e.getWheelRotation();
		if (aux < 0)
			regleta.zoomIn(aux);
		else
			regleta.zoomOut(aux);
		anchoMax = regleta.getWidth();
		partitura.setWidth(anchoMax);
		this.setPreferredSize(this.getPreferredSize());
		this.setSize(this.getPreferredSize());
		// Ponemos la vista enfocando a la posicion del momento al que estaba
		// apuntando el mouse
		this.scrollRectToVisible(new Rectangle(regleta.timeToPixel(tiempo)
				- dif, e.getY(), this.getVisibleRect().width, 1));
		regleta.repaint();
		this.redibujar();
	}

	// Soporte a funciones de partitura

	public void addTablon() {
		Tablon t = partitura.addTablon();
		botones.addBotonesTablon(t.posY, t.getId());
		this.redibujar();
		botones.revalidate();
		altoMax = partitura.getAlto();
		this.revalidate();
	}

	public void seleccionarTablonSiguiente() {
		partitura.siguienteTablon();
		actualizarBotones();
		// botones.desSeleccionarTodos(partitura.getTablonSeleccionado().getId());
		this.redibujar();
	}

	public void seleccionarTablonAnterior() {
		partitura.tablonAnterior();
		actualizarBotones();
		// botones.desSeleccionarTodos(partitura.getTablonSeleccionado().getId());
		this.redibujar();
		botones.repaint();
	}

	public void seleccionarTablon(int id) {
		partitura.seleccionarTablon(id);
		this.redibujar();
	}

	public void subirTablonSeleccionado() {
		int id = partitura.subirTablonSeleccionado();
		botones.moveSelected(id);
		botones.repaint();
		this.redibujar();
	}

	public void cambiarColorTablon(int id) {
		partitura.cambiarColorTablonId(id);
		this.redibujar();
	}

	public void cambiarColorTablon() {
		partitura.cambiarColorTablon();
		this.redibujar();
	}

	public void cambiarTonoTablon() {
		partitura.cambiarTonoTablon();
	}

	public void cambiarTonoTablon(int id) {
		partitura.cambiarTonoTablonId(id);
	}

	public void bajarTablonSeleccionado() {
		int id = partitura.bajarTablonSeleccionado();
		botones.moveSelected(id);
		this.redibujar();
		botones.repaint();
	}

	public int bajarTablon(int id) {
		int vuelta = partitura.bajarTablonId(id);
		this.redibujar();
		return vuelta;
	}

	public int subirTablon(int id) {
		int vuelta = partitura.subirTablonId(id);
		this.redibujar();
		return vuelta;
	}

	public void reproducir(ListaMusicos musicos) {
		java.util.Date date = new java.util.Date();
		musicosReproduccion = musicos;
		// partitura.reproducir(panelSonido.getPlaybackSpeed(), musicos);
		comienzoReproduccion = date.getTime()
				- panelSonido.getComienzoReproduccion();
		if (timerReproduccion != null)
			timerReproduccion.cancel();
		timerReproduccion = new Timer();
		timerReproduccion.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				repaint();
			}
		}, 0, 33);

		if (player != null)
			player.finReproduccion = true;
		player = new ReproductorGolpes(partitura.getListaTablones(),
				partitura.getListaGolpes(),
				musicos.getListaIdMusicosMuteados(),
				panelSonido.getPlaybackSpeed());
		player.duracion = regleta.getMaxValor();
		player.loop = panelSonido.chckbxLoop;
		player.puntoReproduccion = panelSonido.getComienzoReproduccion();
		Thread t = new Thread(player);
		// player.listaReproductores =
		// partitura.getReproductores(panelSonido.getPlaybackSpeed(), musicos);
		t.start();
	}

	public void pararRepdoduccion() {
		if (player != null)
			player.finReproduccion = true;
		comienzoReproduccion = -1;
		if (timerReproduccion != null) {
			timerReproduccion.cancel();
		}
		this.redibujar();

	}

	public void deshacer() {
		partitura.deshacer();
		partitura.desSeleccionar();
		this.redibujar();
	}

	public void rehacer() {
		partitura.rehacer();
		partitura.desSeleccionar();
		this.redibujar();
	}

	public void ajustarAVentana() {
		regleta.ajustarAncho(this.getParent().getWidth());
		anchoMax = regleta.getWidth();
		partitura.setWidth(anchoMax);
		this.setPreferredSize(this.getPreferredSize());
		this.setSize(this.getPreferredSize());
		this.redibujar();
		regleta.repaint();

	}

	public void eliminarGolpesSeleccionados() {
		partitura.eliminarGolpesSeleccionados();
		this.redibujar();
	}

	public void setModoLinea(boolean activado) {
		partitura.setModoLinea(activado);
		this.redibujar();
	}

	public void removeTablonSeleccionado() {
		partitura.removeTablonSeleccionado();
		altoMax = partitura.getAlto();
		this.revalidate();

	}

	public void setDuracion(int d) {
		setDuracion(d, false);
		panelSonido.setDuracion(d);

	}

	public void setDuracion(int d, boolean esTraza) {
		if (esTraza)
			partitura.duracion = d;
		else
			partitura.setDuracion(d);
		regleta.setMaxValor(d);
		partitura.setWidth(regleta.getWidth());
		anchoMax = regleta.getWidth();
		partitura.checkGolpes(d);
		this.setPreferredSize(this.getPreferredSize());
		this.setSize(this.getPreferredSize());
		this.getRootPane().repaint();
		this.getRootPane().revalidate();

	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
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

		return 1;
	}

	public void duplicateSelected() {
		partitura.duplicateSelected();

	}

	public void selectAll() {
		partitura.selectAll();
		this.redibujar();

	}

	public void zoomIn() {
		regleta.zoomIn(1);
		anchoMax = regleta.getWidth();
		partitura.setWidth(anchoMax);
		this.setPreferredSize(this.getPreferredSize());
		this.setSize(this.getPreferredSize());
		regleta.repaint();
		this.redibujar();
	}

	public void zoomOut() {
		regleta.zoomOut(1);
		anchoMax = regleta.getWidth();
		partitura.setWidth(anchoMax);
		this.setPreferredSize(this.getPreferredSize());
		this.setSize(this.getPreferredSize());
		this.redibujar();
		regleta.repaint();
	}

	public void saveAs(String path, String observaciones, int gramaje, int ritmo) {
		partitura.save(path, observaciones, gramaje, ritmo);

	}
	
	public void eliminarGolpesDuplicados(){
		partitura.eliminarGolpesDuplicados();
	}

	public void actualizarBotones() {
		partitura.actualizarBotones();

	}

	public void redibujar() {
		if (comienzoReproduccion == -1) {
			this.repaint();
		}
	}

	public void redondearGolpes() {
		partitura.redondearGolpes(panelSonido.getRedondeo());
		this.redibujar();
	}

	public void cambiarColorMusico(Musico musico) {
		partitura.cambiarColorMusico(musico);
		this.repaint();

	}

	public void tocarTablon(int id) {
		partitura.tocarTablon(id);
	}

	public ArrayList<Golpe> getListaGolpes() {
		return partitura.getListaGolpes().listaGolpes;
	}

	public ListaTablones getListaTablones() {
		return partitura.getListaTablones();
	}

	public void normalizarTablones() {
		partitura.normalizarTablones();
	}

	public int getDuracion() {
		return partitura.duracion;
	}

	public void eliminarGolpes(Musico m) {
		partitura.getListaGolpes().eliminarGolpes(m);
		this.repaint();
	}
}
