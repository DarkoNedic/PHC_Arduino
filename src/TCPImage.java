import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TCPImage extends JFrame {
	public static ImageIcon image = null;
	public static JLabel label = new JLabel();
	public static JFrame frame = new JFrame();

	public static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	public static int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	public static int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);

	public static void Update(String name) {
		//name = new File(name.getPath());
		image = new ImageIcon(name);
		Image tmp_img = new ImageIcon(name).getImage();
		//Image newIm = tmp_img.getScaledInstance(404, 623, java.awt.Image.SCALE_SMOOTH);
		Image newIm = tmp_img.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
		//System.out.println(label.getWidth());
		//System.out.println(label.getHeight());
		image = new ImageIcon(newIm);
		label.setIcon(image);
	}
	
	TCPImage(File name) throws IOException {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//application will be closed when you close frame
		frame.setSize(404, 623);
		frame.setLocation(350, 150);
		frame.setLocation(x-(404/2), y-(622/2));
		BufferedImage img = ImageIO.read(name);
		image = new ImageIcon(name.getPath());
		Image tmp_img = new ImageIcon(name.getPath()).getImage();
		Image newIm = tmp_img.getScaledInstance(404, 601, java.awt.Image.SCALE_SMOOTH);
		//Image newIm = tmp_img.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(newIm);
		label.setIcon(image);
		frame.getContentPane().add(label);
		frame.setResizable(false);
		frame.setVisible(true);
	}

}