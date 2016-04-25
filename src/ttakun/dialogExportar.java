package Txalaparta;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class dialogExportar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tbCompasesLinea;
	private JTextField tbDistanciaLineas;
	public PrintCanvas printCanvas;
	public String titulo;
	public ListaTablones listaTablones;
	private int duracionPartitura;
	private int golpesPorCompas;
	private JCheckBox checkTablonUnico, checkMostrarNumeros;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//dialogExportar dialog = new dialogExportar();
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialogExportar(int duracion, int golpesCompas) {
		duracionPartitura = duracion;
		golpesPorCompas = golpesCompas;
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Menu textos = Menu.getInstance();
		setBounds(100, 100, 450, 166);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			Box verticalBox = Box.createVerticalBox();

			contentPanel.add(verticalBox, BorderLayout.SOUTH);
			{
				Box horizontalBox = Box.createHorizontalBox();
				verticalBox.add(horizontalBox);
				{
					checkMostrarNumeros = new JCheckBox(textos.panelExportar.get(0).t);
					horizontalBox.add(checkMostrarNumeros);
					checkMostrarNumeros
							.setHorizontalAlignment(SwingConstants.LEFT);
				}
				{
					Component horizontalGlue = Box.createHorizontalGlue();
					horizontalBox.add(horizontalGlue);
				}
			}
			{
				Box horizontalBox = Box.createHorizontalBox();
				verticalBox.add(horizontalBox);
				{
					checkTablonUnico = new JCheckBox(textos.panelExportar.get(1).t);
					horizontalBox.add(checkTablonUnico);
				}
				{
					Component horizontalGlue = Box.createHorizontalGlue();
					horizontalBox.add(horizontalGlue);
				}
			}
			{
				Box horizontalBox = Box.createHorizontalBox();
				verticalBox.add(horizontalBox);
				{
					JLabel lblCompasesLinea = new JLabel(textos.panelExportar.get(2).t);
					horizontalBox.add(lblCompasesLinea);
				}
				{
					tbCompasesLinea = new JTextField();
					horizontalBox.add(tbCompasesLinea);
					tbCompasesLinea.setColumns(10);
				}
			}
			{
				Box horizontalBox = Box.createHorizontalBox();
				verticalBox.add(horizontalBox);
				{
					JLabel lblDistanciaLineas = new JLabel(
							textos.panelExportar.get(3).t);
					horizontalBox.add(lblDistanciaLineas);
				}
				{
					tbDistanciaLineas = new JTextField();
					horizontalBox.add(tbDistanciaLineas);
					tbDistanciaLineas.setColumns(10);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (ChooserManager.getInstance().exportChooser
								.showSaveDialog(null) != JFileChooser.APPROVE_OPTION)
							return;
						File folder = ChooserManager.getInstance().exportChooser
								.getSelectedFile();
						if (folder != null) {
							printCanvas.compasesPorFila = Integer
									.parseInt(tbCompasesLinea.getText());
							printCanvas.offsetFilas = Integer
									.parseInt(tbDistanciaLineas.getText());
							printCanvas.dibujarNumeros = checkMostrarNumeros
									.isSelected();
							printCanvas.recalcularFilasPaginas(duracionPartitura, golpesPorCompas );
							printCanvas.dibujar(folder.getAbsolutePath(), titulo, listaTablones,
									checkTablonUnico.isSelected());
							guardarConfig();
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void mostrar() {
		Configuracion config = Configuracion.getInstance();
		checkMostrarNumeros.setSelected(config.mostrarNumeros);
		checkTablonUnico.setSelected(config.mostrarAgrupados);
		tbCompasesLinea.setText(config.compasesPorFila + "");
		tbDistanciaLineas.setText(config.offsetFilas + "");
		setVisible(true);
	}

	private void guardarConfig() {
		Configuracion config = Configuracion.getInstance();
		config.mostrarNumeros = checkMostrarNumeros.isSelected();
		config.mostrarAgrupados = checkTablonUnico.isSelected();
		config.compasesPorFila = Integer.parseInt(tbCompasesLinea.getText());
		config.offsetFilas = Integer.parseInt(tbDistanciaLineas.getText());
		config.guardar();
	}

}
