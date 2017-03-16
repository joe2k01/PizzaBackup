package joe2k01.pizzaBackup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class BackUpFrame extends JFrame {

	private JPanel contentPane;

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
	public BackUpFrame() {
		setDefaultCloseOperation(HIDE_ON_CLOSE);;
		setBounds(100, 100, 450, 300);
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
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnBackup, 6, SpringLayout.SOUTH, chckbxRecovery);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnBackup, 176, SpringLayout.WEST, contentPane);
		contentPane.add(btnBackup);
		btnBackup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String args = "";
				
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
				
				if(args != null)
				{
					Runtime run = Runtime.getRuntime();
					try {
						Process exec = run.exec("adb backup --twrp" + args);
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
				Runtime run = Runtime.getRuntime();
				try {
					Process exec = run.exec("adb backup --twrp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
}
