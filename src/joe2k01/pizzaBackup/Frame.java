package joe2k01.pizzaBackup;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Canvas;
import java.awt.Component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {

	private JPanel contentPane;
	public String choosenFileLoc;
	public static Image icon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setResizable(false);
					frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/icon.png")));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String choosenBackUp(Component button) {
		JFileChooser choosenFile = new JFileChooser();
		choosenFile.setCurrentDirectory(new File("."));
		choosenFile.setDialogTitle("Select BackUp location");
		choosenFile.showOpenDialog(button);
		choosenFileLoc = choosenFile.getSelectedFile().getAbsolutePath();
		System.out.println(choosenFileLoc);
		return choosenFileLoc;
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setTitle("PizzaBackup by joe2k01");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JButton btn_newBackup = new JButton("Create a new backup");
		btn_newBackup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				BackUpFrame.main(null);
			}
		});
		contentPane.add(btn_newBackup, BorderLayout.SOUTH);

		class ImageCanvas extends Canvas
		{
			private BufferedImage img;

	        public ImageCanvas() {
	            try {
	                img = ImageIO.read(getClass().getResource("/Images/PizzaBackup.png"));
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }

	        @Override
	        public void paint(Graphics g) {
	            super.paint(g);
	            if (img != null) {
	                int x = (getWidth() - img.getWidth()) / 2;
	                int y = (getHeight() - img.getHeight()) / 2;
	                g.drawImage(img, x, y, this);
	            }
	        }

		}
		
		contentPane.add(new ImageCanvas(), BorderLayout.CENTER);

		JButton btnRestoreABackup = new JButton("Restore a backup");
		btnRestoreABackup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				choosenBackUp(btn_newBackup);
				if (choosenFileLoc.endsWith(".ab")) {
					Runtime run = Runtime.getRuntime();
					try {
						Process exec = run.exec("adb restore " + choosenFileLoc);
						System.out.println("ADB backup found");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please, select a file that ends with the .ab extension");
					System.out.println("Not an ADB backup");
				}
			}
		});
		contentPane.add(btnRestoreABackup, BorderLayout.NORTH);
	}

}
