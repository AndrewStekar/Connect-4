package connectMiniMax;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.fazecast.jSerialComm.SerialPort;

public class JavaToArduino {

	static SerialPort chosenPort;

	public static void main(String[] args) {

		File inFile = new File("computerMove.txt");// stores the player's chosen move

		// create and configure the window
		JFrame window = new JFrame();
		window.setTitle("Arduino");
		window.setSize(400, 75);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create a drop-down box and connect button, then place them at the top of the
		// window
		JComboBox<String> portList = new JComboBox<String>();
		JButton connectButton = new JButton("Connect");
		JPanel topPannel = new JPanel();
		topPannel.add(portList);
		topPannel.add(connectButton);
		window.add(topPannel, BorderLayout.NORTH);

		// populate the drop-down box
		SerialPort[] portNames = SerialPort.getCommPorts();
		for (int i = 0; i < portNames.length; i++)
			portList.addItem(portNames[i].getSystemPortName());

		// create the line graph
		XYSeries series = new XYSeries("Light Sensor Readings");
		XYSeriesCollection dadaset = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory.createXYLineChart("Light Sensor Reading", "Time (seconds)", "ADC Reading",
				dadaset);
		window.add(new ChartPanel(chart), BorderLayout.CENTER);

		// configure the connect button and use another thread to listen for data
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (connectButton.getText().equals("Connect")) {
					// attempt to connect to the serial port
					chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
					chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
					if (chosenPort.openPort()) {
						connectButton.setText("Dissconnect");
						portList.setEnabled(false);

						Thread thread = new Thread() {
							@Override
							public void run() {
								String line;
								while (true) {// main loop
									while (true) {// searching for move
										try {
											Scanner freader = new Scanner(inFile);
											line = freader.nextLine();
											freader.close();
											// erasing contents of text file to avoid double action
											BufferedWriter writer = new BufferedWriter(new FileWriter(inFile));
											writer.close();
											break;
										} catch (Exception e) {
											continue;
										}
									}
									int move = Integer.parseInt(line);
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
									}
									PrintWriter output = new PrintWriter(chosenPort.getOutputStream());

									// enter code to send here
									output.print(move);
									output.flush();
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
									}

								}
							}

						};
						thread.start();
					}

				} else {
					// disconnect from the serial port
					chosenPort.closePort();
					portList.setEnabled(true);
					connectButton.setText("Connect");
				}
			}
		});

		// show the window
		window.setVisible(true);
	}

}
