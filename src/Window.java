import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window {
	public static int linenumber = 1;
	public static int FirstMessageFlag = 0;

	public static String getXthLine(int linenumber, String filepath) throws IOException {
		FileInputStream fs = new FileInputStream(filepath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fs));
		for (int i = 0; i < linenumber - 1; ++i)
			br.readLine();
		String XthLine = br.readLine();
		br.close();
		return XthLine;
	}

	public static String getTCPmessage(String filepath) throws IOException, InterruptedException {
		String message = "";
		message = getXthLine(linenumber, filepath);
		while (message == null) {
			message = getXthLine(linenumber, filepath);
			TimeUnit.MILLISECONDS.sleep(1000);
		}
		linenumber++;
		return message;
	}

	private static int status = 1;

	private static void evaluateMessage(String message) {
		if (status == 1)
			evalInitialisation(message);
		else if (status == 2)
			evalTransmission(message);
		else if (status == 3)
			evalFinish(message);
		// else we have no permited stauts
	}

	public static void updateImage(String str) {
		gui.Update("src/" + str);
	}

	// images 1,..., 4
	// For succes we need C1, (S1,S2) | (S2,S1), C2
	private static int evalIntStatus = 0;
	private static boolean lastWasS1 = false;

	private static void evalInitialisation(String message) {
		if (evalIntStatus == 0) {
			if (message.equals("Client1")) { // succes point
				evalIntStatus++;
				updateImage("02.png");
			} else {
				evalIntStatus = 0;
				updateImage("01.png");
			}
		} else if (evalIntStatus == 1) {
			if (message.equals("Server1")) {
				evalIntStatus++;
				lastWasS1 = true;
			} else if (message.equals("Server2")) {
				evalIntStatus++;
				lastWasS1 = false;
			} else {
				evalIntStatus = 0;
				updateImage("01.png");
			}
		} else if (evalIntStatus == 2) {
			if (message.equals("Server2") && lastWasS1) {

				evalIntStatus++;
				updateImage("03.png");
			} else if (message.equals("Server1") && lastWasS1 == false) {
				evalIntStatus++;
				updateImage("03.png");
			} else {
				evalIntStatus = 0;
				updateImage("01.png");
			}
		} else if (evalIntStatus == 3) {
			if (message.equals("Client2")) {
				status = 2;
				lastWasS1 = false;
				updateImage("04.png");
			} else {
				evalIntStatus = 0;
				updateImage("01.png");
			}
		}
	}

	// images 5,..., 8
	// For succes we need C1, S2, C1, S2
	private static int evalConStatus = 0;

	private static void evalTransmission(String message) {
		if (evalConStatus == 0) {
			if (message.equals("Client1")) {
				evalConStatus++;
				updateImage("05.png");
			} else {
				evalConStatus = 0;
				updateImage("04.png");
			}
		} else if (evalConStatus == 1) {
			if (message.equals("Server2")) {
				evalConStatus++;
				updateImage("06.png");
			} else {
				evalConStatus = 0;
				updateImage("04.png");
			}
		} else if (evalConStatus == 2) {
			if (message.equals("Client1")) {
				evalConStatus++;
				updateImage("07.png");
			} else {
				evalConStatus = 0;
				updateImage("04.png");
			}
		} else if (evalConStatus == 3) {
			if (message.equals("Server2")) {
				status++;
				updateImage("08.png");
			} else {
				evalConStatus = 0;
				updateImage("04.png");
			}
		}
	}

	// images 8,9,..., 11
	// For succes we need C1, (S1,S2) | (S2,S1), C2
	private static int evalFinStatus = 0;

	private static void evalFinish(String message) {
		if (evalFinStatus == 0) {
			if (message.equals("Client1")) { // succes point
				evalFinStatus++;
				updateImage("09.png");
			} else {
				evalFinStatus = 0;
				updateImage("08.png");
			}
		} else if (evalFinStatus == 1) {
			if (message.equals("Server1")) {
				evalFinStatus++;
				lastWasS1 = true;
			} else if (message.equals("Server2")) {
				evalFinStatus++;
				lastWasS1 = false;

			} else {
				evalFinStatus = 0;
				updateImage("08.png");
			}
		} else if (evalFinStatus == 2) {
			if (message.equals("Server2") && lastWasS1) {
				evalFinStatus++;
				updateImage("10.png");
			} else if (message.equals("Server1") && lastWasS1 == false) {
				evalFinStatus++;
				updateImage("10.png");
			} else {
				evalFinStatus = 0;
				updateImage("08.png");
			}
		} else if (evalFinStatus == 3) {
			if (message.equals("Client2")) {
				status = 4;
				lastWasS1 = false;
				updateImage("11.png");
			} else {
				evalFinStatus = 0;
				updateImage("08.png");
			}
		}
	}

	private static void ClientServer(File picName) throws IOException {
		gui = new TCPImage(picName);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setBackground(Color.white);
		gui.setSize(500, 500);
		gui.setVisible(false);
		gui.pack();
		gui.setTitle("Arduino: TCP");
	}

	public static TCPImage gui = null;

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		// location of textfile generated by arduino stream
		String filepath = "/Users/darkonedic/TCP.txt";
		ClientServer(new File("src/01.png"));
		while (true) {
			String message = getTCPmessage(filepath);
			System.out.println(message);
			evaluateMessage(message);
			if (status == 4) {
				break;
			}
		}
		JFrame frame = new JFrame("Popup");
		JOptionPane.showMessageDialog(frame, "Herzlichen Glückwunsch!\nDu hast die Webseite erfolgreich geladen.",
				"Glückwunsch!", JOptionPane.PLAIN_MESSAGE);

	}
}
