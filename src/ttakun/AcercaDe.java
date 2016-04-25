package Txalaparta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AcercaDe extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private final JLabel lblImagen = new JLabel();
	private final JLabel lblNewLabel = new JLabel("TTAKUN 3.0");
	private final Box verticalBox_1 = Box.createVerticalBox();
	private final JLabel lblNewLabel_1 = new JLabel("Txalaparta musika idazteko programa informatikoa");
	private final JLabel lblNewLabel_2 = new JLabel("Juan Mari Beltran Argi\u00F1enaren ideia");
	private final JLabel lblNewLabel_3 = new JLabel("Gorka Monterok programatua.");
	private final JLabel lblEskerrikAskoProiektua = new JLabel("Eskerrik asko proiektua ahalbidetu duzuen guztioi, bereziki");
	private final JLabel lblHernaniMusikaEskola = new JLabel("Hernani Musika Eskola Publikoko Txalaparta Taldea");
	private final JLabel lblNewLabel_4 = new JLabel("Proiektu hau diruz lagundu du Eusko Jaurlaritzaren Kultura Sailak.");
	private final Component verticalStrut = Box.createVerticalStrut(20);
	private final Component verticalStrut_1 = Box.createVerticalStrut(20);
	private final Box verticalBox_3 = Box.createVerticalBox();
	private final JPanel panel = new JPanel();
	private final Box verticalBox_2 = Box.createVerticalBox();
	private final JPanel panel_1 = new JPanel();
	private final Component verticalStrut_2 = Box.createVerticalStrut(20);
	private final JPanel panel_2 = new JPanel();
	private final JLabel lblSoinuenea = new JLabel("");
	private final JLabel lblLicencia = new JLabel("");
	private final JPanel panel_3 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AcercaDe dialog = new AcercaDe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		getContentPane().setBackground(Color.WHITE);
		setTitle(Menu.getInstance().panelAcercaDe.get(0).t);
		setResizable(false);
		setBounds(100, 100, 661, 314);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		contentPanel.add(verticalBox_2, BorderLayout.WEST);
		verticalBox_2.add(lblImagen);
		lblImagen.setIcon(new ImageIcon(AcercaDe.class
				.getResource("/Resources/ttakun3.honiburuz.jpg")));
		panel.setBackground(Color.WHITE);
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel_1.setBackground(Color.WHITE);
		
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.add(verticalBox_1);
		
		verticalBox_1.add(verticalStrut);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		verticalBox_1.add(lblNewLabel_1);
		
		verticalBox_1.add(verticalStrut_1);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		verticalBox_1.add(lblNewLabel_2);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		verticalBox_1.add(lblNewLabel_3);
		panel_2.setBackground(Color.WHITE);
		
		contentPanel.add(panel_2, BorderLayout.SOUTH);
		lblEskerrikAskoProiektua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(verticalBox_3);
		verticalBox_3.add(lblEskerrikAskoProiektua);
		lblHernaniMusikaEskola.setFont(new Font("Tahoma", Font.PLAIN, 14));
		verticalBox_3.add(lblHernaniMusikaEskola);
		
		verticalBox_3.add(verticalStrut_2);
		panel_3.setBackground(Color.WHITE);
		
		verticalBox_3.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		lblSoinuenea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				URI uri;
				try {
					URL url = new URL("http://www.soinuenea.org/");
					uri = url.toURI();
					Desktop.getDesktop().browse(uri);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
			
		});
		panel_3.add(lblSoinuenea, BorderLayout.WEST);
		lblSoinuenea.setIcon(new ImageIcon(AcercaDe.class.getResource("/Resources/soinuenea.jpg")));
		lblLicencia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					URI uri;
					try {
						URL url = new URL("http://www.gnu.org/licenses/gpl.html");
						uri = url.toURI();
						Desktop.getDesktop().browse(uri);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				}
				
			});
		panel_3.add(lblLicencia, BorderLayout.EAST);
		lblLicencia.setIcon(new ImageIcon(AcercaDe.class.getResource("/Resources/licencia.jpg")));
		verticalBox_3.add(lblNewLabel_4);

		cargarTexto();

	}

	private void cargarTexto() {
	}

}
