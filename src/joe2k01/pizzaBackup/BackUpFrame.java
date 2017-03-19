package joe2k01.pizzaBackup;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;

public class BackUpFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public String chosedSaveLoc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackUpFrame frame = new BackUpFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public String whereToSave(Component button)
	{
		JFileChooser saveLoc = new JFileChooser();
		saveLoc.setCurrentDirectory(new File("."));
		saveLoc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		saveLoc.setDialogTitle("Select save location");
		saveLoc.showOpenDialog(button);
		chosedSaveLoc = saveLoc.getSelectedFile().getAbsolutePath() + "\\";
		System.out.println(chosedSaveLoc);
		return chosedSaveLoc;
	}
	
	public BackUpFrame() {
		setDefaultCloseOperation(HIDE_ON_CLOSE);;
		setBounds(100, 100, 480, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		setContentPane(contentPane);
		
		JLabel lblWhatDoYou = new JLabel("What do you want to backup?");
		lblWhatDoYou.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWhatDoYou, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWhatDoYou, 140, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblWhatDoYou, -140, SpringLayout.EAST, contentPane);
		lblWhatDoYou.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWhatDoYou);
		
		JCheckBox chckbxSystem = new JCheckBox("System");
		sl_contentPane.putConstraint(SpringLayout.NORTH, chckbxSystem, 6, SpringLayout.SOUTH, lblWhatDoYou);
		sl_contentPane.putConstraint(SpringLayout.WEST, chckbxSystem, 181, SpringLayout.WEST, contentPane);
		contentPane.add(chckbxSystem);
		
		JCheckBox chckbxData = new JCheckBox("Data");
		sl_contentPane.putConstraint(SpringLayout.WEST, chckbxData, 0, SpringLayout.WEST, chckbxSystem);
		sl_contentPane.putConstraint(SpringLayout.EAST, chckbxData, -5, SpringLayout.EAST, lblWhatDoYou);
		contentPane.add(chckbxData);
		
		JCheckBox chckbxBoot = new JCheckBox("Boot");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, chckbxData, -6, SpringLayout.NORTH, chckbxBoot);
		sl_contentPane.putConstraint(SpringLayout.NORTH, chckbxBoot, 88, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, chckbxBoot, 0, SpringLayout.WEST, chckbxSystem);
		sl_contentPane.putConstraint(SpringLayout.EAST, chckbxBoot, -182, SpringLayout.EAST, contentPane);
		contentPane.add(chckbxBoot);
		
		JCheckBox chckbxRecovery = new JCheckBox("Recovery");
		sl_contentPane.putConstraint(SpringLayout.NORTH, chckbxRecovery, 6, SpringLayout.SOUTH, chckbxBoot);
		sl_contentPane.putConstraint(SpringLayout.WEST, chckbxRecovery, 0, SpringLayout.WEST, chckbxSystem);
		contentPane.add(chckbxRecovery);
		
		JButton btnOrSelectDirectly = new JButton("Or select directly on phone");

		sl_contentPane.putConstraint(SpringLayout.WEST, btnOrSelectDirectly, 131, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnOrSelectDirectly, -10, SpringLayout.SOUTH, contentPane);
		contentPane.add(btnOrSelectDirectly);
		
		JButton btnBackup = new JButton("Backup!");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnBackup, 191, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnBackup, -31, SpringLayout.NORTH, btnOrSelectDirectly);
		contentPane.add(btnBackup);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				chckbxSystem.setSelected(false);
				chckbxData.setSelected(false);
				chckbxBoot.setSelected(false);
				chckbxRecovery.setSelected(false);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, btnBackup);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, -24, SpringLayout.NORTH, btnBackup);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, -15, SpringLayout.EAST, contentPane);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCustom = new JLabel("Custom:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblCustom, 2, SpringLayout.NORTH, textField);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblCustom, 134, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblCustom, -82, SpringLayout.NORTH, btnOrSelectDirectly);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblCustom, -6, SpringLayout.WEST, textField);
		lblCustom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblCustom);
		btnBackup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String args = "";
				String text = textField.getText();
				
				if(chckbxSystem.isSelected())
				{
					args = args + " system";
				}
				if(chckbxData.isSelected())
				{
					args = args + " data";
				}
				if(chckbxBoot.isSelected())
				{
					args = args + " boot";
				}
				if(chckbxRecovery.isSelected())
				{
					args = args + " recovery";
				}
				
				if(args != "")
				{
					whereToSave(btnBackup);
					Runtime run = Runtime.getRuntime();
					try {
						Process exec = run.exec("cmd.exe /c cd " + chosedSaveLoc + " && adb backup " + " --twrp" + args);
						text = null;
						args = "";
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(text != null)
				{
					whereToSave(btnBackup);
					Runtime run = Runtime.getRuntime();
					try {
						Process exec = run.exec("cmd.exe /c cd " + chosedSaveLoc + " && adb backup " + " --twrp " + textField.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		btnOrSelectDirectly.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				whereToSave(btnOrSelectDirectly);
				Runtime run = Runtime.getRuntime();
				try {
					Process exec = run.exec("cmd.exe /c cd " + chosedSaveLoc + " && adb backup " + " --twrp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
}
