package Txalaparta;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class DialogConfigurar extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();

	private JTextField tbDefaultSound;
	private JTextField tbFolderExport, tbFolderSheet, tbLanguajeFile,
			tbDefaultDuration;
	private JButton btnFolderExport, btnFolderSheet, btnLanguajeFile,
			btnDefaultsound;
	private JCheckBox checkHighlightSelectedPlank;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogConfigurar dialog = new DialogConfigurar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogConfigurar() {
		
		Menu textos = Menu.getInstance();

		setBounds(100, 100, 438, 225);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 109, 211, 83, 0 };
		gbl_contentPanel.rowHeights = new int[] { 24, 24, 24, 24, 24, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1000.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel labelFolderSheet = new JLabel(textos.panelConfiguracion.get(0).t);
		GridBagConstraints gbc_labelFolderSheet = new GridBagConstraints();
		gbc_labelFolderSheet.anchor = GridBagConstraints.WEST;
		gbc_labelFolderSheet.fill = GridBagConstraints.VERTICAL;
		gbc_labelFolderSheet.insets = new Insets(0, 0, 5, 5);
		gbc_labelFolderSheet.gridx = 0;
		gbc_labelFolderSheet.gridy = 0;
		contentPanel.add(labelFolderSheet, gbc_labelFolderSheet);
		tbFolderSheet = new JTextField();
		tbFolderSheet.setEditable(false);
		GridBagConstraints gbc_tbFolderSheet = new GridBagConstraints();
		gbc_tbFolderSheet.fill = GridBagConstraints.BOTH;
		gbc_tbFolderSheet.insets = new Insets(0, 0, 5, 5);
		gbc_tbFolderSheet.gridx = 1;
		gbc_tbFolderSheet.gridy = 0;
		contentPanel.add(tbFolderSheet, gbc_tbFolderSheet);
		btnFolderSheet = new JButton(textos.panelConfiguracion.get(5).t);
		btnFolderSheet.addActionListener(this);
		GridBagConstraints gbc_btnFolderSheet = new GridBagConstraints();
		gbc_btnFolderSheet.fill = GridBagConstraints.BOTH;
		gbc_btnFolderSheet.insets = new Insets(0, 0, 5, 0);
		gbc_btnFolderSheet.gridx = 2;
		gbc_btnFolderSheet.gridy = 0;
		contentPanel.add(btnFolderSheet, gbc_btnFolderSheet);
		JLabel labelLanguajeFile = new JLabel(textos.panelConfiguracion.get(1).t);
		GridBagConstraints gbc_labelLanguajeFile = new GridBagConstraints();
		gbc_labelLanguajeFile.anchor = GridBagConstraints.WEST;
		gbc_labelLanguajeFile.fill = GridBagConstraints.VERTICAL;
		gbc_labelLanguajeFile.insets = new Insets(0, 0, 5, 5);
		gbc_labelLanguajeFile.gridx = 0;
		gbc_labelLanguajeFile.gridy = 1;
		contentPanel.add(labelLanguajeFile, gbc_labelLanguajeFile);
		tbLanguajeFile = new JTextField();
		tbLanguajeFile.setEditable(false);
		GridBagConstraints gbc_tbLanguajeFile = new GridBagConstraints();
		gbc_tbLanguajeFile.fill = GridBagConstraints.BOTH;
		gbc_tbLanguajeFile.insets = new Insets(0, 0, 5, 5);
		gbc_tbLanguajeFile.gridx = 1;
		gbc_tbLanguajeFile.gridy = 1;
		contentPanel.add(tbLanguajeFile, gbc_tbLanguajeFile);
		btnLanguajeFile = new JButton(textos.panelConfiguracion.get(5).t);
		btnLanguajeFile.addActionListener(this);
		GridBagConstraints gbc_btnLanguajeFile = new GridBagConstraints();
		gbc_btnLanguajeFile.fill = GridBagConstraints.BOTH;
		gbc_btnLanguajeFile.insets = new Insets(0, 0, 5, 0);
		gbc_btnLanguajeFile.gridx = 2;
		gbc_btnLanguajeFile.gridy = 1;
		contentPanel.add(btnLanguajeFile, gbc_btnLanguajeFile);
		JLabel lblFolderExport = new JLabel(textos.panelConfiguracion.get(2).t);
		GridBagConstraints gbc_lblFolderExport = new GridBagConstraints();
		gbc_lblFolderExport.anchor = GridBagConstraints.WEST;
		gbc_lblFolderExport.fill = GridBagConstraints.VERTICAL;
		gbc_lblFolderExport.insets = new Insets(0, 0, 5, 5);
		gbc_lblFolderExport.gridx = 0;
		gbc_lblFolderExport.gridy = 2;
		contentPanel.add(lblFolderExport, gbc_lblFolderExport);
		tbFolderExport = new JTextField();
		tbFolderExport.setEditable(false);
		GridBagConstraints gbc_tbFolderExport = new GridBagConstraints();
		gbc_tbFolderExport.fill = GridBagConstraints.BOTH;
		gbc_tbFolderExport.insets = new Insets(0, 0, 5, 5);
		gbc_tbFolderExport.gridx = 1;
		gbc_tbFolderExport.gridy = 2;
		contentPanel.add(tbFolderExport, gbc_tbFolderExport);
		tbFolderExport.setColumns(10);

		btnFolderExport = new JButton(textos.panelConfiguracion.get(5).t);
		btnFolderExport.addActionListener(this);
		GridBagConstraints gbc_btnFolderExport = new GridBagConstraints();
		gbc_btnFolderExport.fill = GridBagConstraints.BOTH;
		gbc_btnFolderExport.insets = new Insets(0, 0, 5, 0);
		gbc_btnFolderExport.gridx = 2;
		gbc_btnFolderExport.gridy = 2;
		contentPanel.add(btnFolderExport, gbc_btnFolderExport);
		JLabel labelDefaultDuration = new JLabel(textos.panelConfiguracion.get(4).t);
		GridBagConstraints gbc_labelDefaultDuration = new GridBagConstraints();
		gbc_labelDefaultDuration.anchor = GridBagConstraints.WEST;
		gbc_labelDefaultDuration.fill = GridBagConstraints.VERTICAL;
		gbc_labelDefaultDuration.insets = new Insets(0, 0, 5, 5);
		gbc_labelDefaultDuration.gridx = 0;
		gbc_labelDefaultDuration.gridy = 4;
		contentPanel.add(labelDefaultDuration, gbc_labelDefaultDuration);
		tbDefaultDuration = new JTextField();
		GridBagConstraints gbc_tbDefaultDuration = new GridBagConstraints();
		gbc_tbDefaultDuration.fill = GridBagConstraints.BOTH;
		gbc_tbDefaultDuration.insets = new Insets(0, 0, 5, 5);
		gbc_tbDefaultDuration.gridx = 1;
		gbc_tbDefaultDuration.gridy = 4;
		contentPanel.add(tbDefaultDuration, gbc_tbDefaultDuration);
		JPanel huecoDefaultDuration = new JPanel();
		GridBagConstraints gbc_huecoDefaultDuration = new GridBagConstraints();
		gbc_huecoDefaultDuration.fill = GridBagConstraints.BOTH;
		gbc_huecoDefaultDuration.insets = new Insets(0, 0, 5, 0);
		gbc_huecoDefaultDuration.gridx = 2;
		gbc_huecoDefaultDuration.gridy = 4;
		contentPanel.add(huecoDefaultDuration, gbc_huecoDefaultDuration);

		JLabel lblDefaultSound = new JLabel(textos.panelConfiguracion.get(3).t);
		GridBagConstraints gbc_lblDefaultSound = new GridBagConstraints();
		gbc_lblDefaultSound.anchor = GridBagConstraints.WEST;
		gbc_lblDefaultSound.fill = GridBagConstraints.VERTICAL;
		gbc_lblDefaultSound.insets = new Insets(0, 0, 5, 5);
		gbc_lblDefaultSound.gridx = 0;
		gbc_lblDefaultSound.gridy = 3;
		contentPanel.add(lblDefaultSound, gbc_lblDefaultSound);

		tbDefaultSound = new JTextField();
		tbDefaultSound.setEditable(false);
		GridBagConstraints gbc_tbDefaultSound = new GridBagConstraints();
		gbc_tbDefaultSound.fill = GridBagConstraints.BOTH;
		gbc_tbDefaultSound.insets = new Insets(0, 0, 5, 5);
		gbc_tbDefaultSound.gridx = 1;
		gbc_tbDefaultSound.gridy = 3;
		contentPanel.add(tbDefaultSound, gbc_tbDefaultSound);
		tbDefaultSound.setColumns(10);

		btnDefaultsound = new JButton(textos.panelConfiguracion.get(5).t);
		btnDefaultsound.addActionListener(this);
		GridBagConstraints gbc_btndefaultsound = new GridBagConstraints();
		gbc_btndefaultsound.insets = new Insets(0, 0, 5, 0);
		gbc_btndefaultsound.fill = GridBagConstraints.HORIZONTAL;
		gbc_btndefaultsound.gridx = 2;
		gbc_btndefaultsound.gridy = 3;
		contentPanel.add(btnDefaultsound, gbc_btndefaultsound);
		
		checkHighlightSelectedPlank = new JCheckBox("Highlight selected plank");
		GridBagConstraints gbc_checkHighlightSelectedPlank = new GridBagConstraints();
		gbc_checkHighlightSelectedPlank.anchor = GridBagConstraints.WEST;
		gbc_checkHighlightSelectedPlank.gridwidth = 2;
		gbc_checkHighlightSelectedPlank.insets = new Insets(0, 0, 0, 5);
		gbc_checkHighlightSelectedPlank.gridx = 0;
		gbc_checkHighlightSelectedPlank.gridy = 5;
		contentPanel.add(checkHighlightSelectedPlank, gbc_checkHighlightSelectedPlank);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFolderExport) {
			JFileChooser fc = ChooserManager.getInstance().exportChooser;
			if (fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
				return;
			File f = fc.getSelectedFile();
			if (f != null) {
				tbFolderExport.setText(f.getAbsolutePath());
			}

		} else {
			if (e.getSource() == btnFolderSheet) {
				JFileChooser fc = new JFileChooser(
						Configuracion.getInstance().folderDefaultSheet);
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
					return;
				File f = fc.getSelectedFile();
				if (f != null) {
					tbFolderSheet.setText(f.getAbsolutePath());
				}

			} else {
				if (e.getSource() == btnLanguajeFile) {
					JFileChooser fc = ChooserManager.getInstance().languajeChooser;
					if (fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
						return;
					File f = fc.getSelectedFile();
					if (f != null) {
						String path = f.getAbsolutePath();
						tbLanguajeFile.setText(path.replace(
								Configuracion.getInstance().pathBase, "."));
					}

				} else {
					if (e.getSource() == btnDefaultsound) {
						JFileChooser fc = ChooserManager.getInstance().soundChooser;
						if (fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
							return;
						File f = fc.getSelectedFile();
						if (f != null) {
							String path = f.getAbsolutePath();
							tbDefaultSound.setText(path.replace(
									Configuracion.getInstance().pathBase, "."));
						}

					}
				}
			}

		}

	}

	public void cargar() {
		Configuracion config = Configuracion.getInstance();
		tbDefaultDuration.setText(config.duracion + "");
		tbDefaultSound.setText(config.sonidoDefault);
		tbFolderExport.setText(config.folderDefaultExport);
		tbLanguajeFile.setText(config.language);
		tbFolderSheet.setText(config.folderDefaultSheet);
		checkHighlightSelectedPlank.setSelected(config.resaltarTablones);

	}

	public void guardar() {
		Configuracion config = Configuracion.getInstance();
		config.duracion = Integer.parseInt(tbDefaultDuration.getText());
		config.sonidoDefault = tbDefaultSound.getText();
		config.folderDefaultExport = tbFolderExport.getText();
		config.language = tbLanguajeFile.getText();
		config.folderDefaultSheet = tbFolderSheet.getText();
		config.resaltarTablones = checkHighlightSelectedPlank.isSelected();
		config.guardar();
	}

}
