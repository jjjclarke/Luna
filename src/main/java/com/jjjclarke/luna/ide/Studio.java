package com.jjjclarke.luna.ide;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

public class Studio {

	private JFrame frmLunaStudio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					Studio window = new Studio();
					window.frmLunaStudio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Studio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLunaStudio = new JFrame();
		frmLunaStudio.setTitle("Luna Studio");
		frmLunaStudio.setBounds(100, 100, 613, 558);
		frmLunaStudio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmLunaStudio.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnExamples = new JMenu("Sample Code");
		mnFile.add(mnExamples);

		JMenuItem mntmHelloWorld = new JMenuItem("Hello, World!");
		mnExamples.add(mntmHelloWorld);

		JMenuItem mntmFibonacci = new JMenuItem("Fibonacci Sequence");
		mnExamples.add(mntmFibonacci);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnSource = new JMenu("Source");
		menuBar.add(mnSource);

		JMenuItem mntmRun1 = new JMenuItem("Run...");
		mnSource.add(mntmRun1);

		JMenuItem mntnRun2 = new JMenuItem("Run in terminal...");
		mnSource.add(mntnRun2);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		frmLunaStudio.getContentPane().setLayout(new BorderLayout(0, 0));

		RSyntaxTextArea txtpnSrcInput = new RSyntaxTextArea();
//		txtpnSrcInput.setText("print \"Hi, from Luna Studio!\";");
//		txtpnSrcInput.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
//		frmLunaStudio.getContentPane().add(txtpnSrcInput);

		RTextScrollPane scrollPane = new RTextScrollPane(txtpnSrcInput);
		frmLunaStudio.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		frmLunaStudio.getContentPane().add(textArea, BorderLayout.SOUTH);

	}
}
