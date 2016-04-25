package Txalaparta;

import java.awt.Color;
import java.awt.Graphics;

public class BotonTablon {
	public Color cBoton = Color.gray;
	public Color cBorde = Color.black;
	private int x, y, w, h;
	private String texto = "";

	public BotonTablon(int _X, int _Y, int _W, int _H) {
		x = _X;
		y = _Y;
		w = _W;
		h = _H;
	}

	public void setText(String text) {
		texto = text;
	}

	public boolean click(int _X, int _Y) {
		return (x <= _X && x + w >= _X && y <= _Y && y + h >= _Y);
	}

	public void draw(Graphics g) {
		g.setColor(cBoton);
		g.fillRect(x, y, w, h);
		g.setColor(cBorde);
		g.drawRect(x, y, w, h);

		g.drawString(texto, x + 3, y + h / 2);
	}

}
