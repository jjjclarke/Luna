package com.jjjclarke.luna.ide;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

public class Studio {

	private JFrame frmLunaStudio;
	private RSyntaxTextArea txtpnSrcInput;
	private JTextArea textArea;

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
		mntmHelloWorld.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadScript("luna/hello_world.luna");
			}
		});
		mnExamples.add(mntmHelloWorld);

		JMenuItem mntmFibonacci = new JMenuItem("Fibonacci Sequence");
		mntmFibonacci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadScript("luna/fibonacci.luna");
			}
		});
		mnExamples.add(mntmFibonacci);
		
		JMenuItem mntmInheritance = new JMenuItem("Inheritance Example");
		mntmInheritance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadScript("luna/inheritance.luna");
			}
		});
		mnExamples.add(mntmInheritance);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Block Scope Example");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadScript("luna/block_scope.luna");
			}
		});
		mnExamples.add(mntmNewMenuItem);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnSource = new JMenu("Source");
		menuBar.add(mnSource);

		JMenuItem mntmRun1 = new JMenuItem("Run...");
		mntmRun1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run();
			}
		});
		mnSource.add(mntmRun1);

		JMenuItem mntnRun2 = new JMenuItem("Run in terminal...");
		mnSource.add(mntnRun2);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(frmLunaStudio), "About Luna Studio", true);
				dialog.getContentPane().setLayout(new BorderLayout(10, 10));
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				
				JPanel panel = new JPanel(new BorderLayout(10, 10));
				panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				
				JPanel textPanel = new JPanel(new GridLayout(0, 1));
				textPanel.add(new JLabel("<html><b>Luna & Luna Studio</b></html>"));
				textPanel.add(new JLabel("<html><i>Version 0.2.0</i></html>"));
				textPanel.add(new JLabel(""));
				textPanel.add(new JLabel("<html>This program uses <b>RSyntaxTextArea</b> to display source code.</html>"));
				textPanel.add(new JLabel("The source code for Luna and Luna Studio can be found online at:"));
				textPanel.add(new JLabel("https://github.com/jjjclarke/Luna"));
				panel.add(textPanel, BorderLayout.CENTER);
				
				dialog.getContentPane().add(panel, BorderLayout.CENTER);
				
				JButton closeBtn = new JButton("Close");
				closeBtn.addActionListener(e2 -> dialog.dispose());
				JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				buttonPanel.add(closeBtn);
				dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
				
				dialog.pack();
				dialog.setLocationRelativeTo(frmLunaStudio);
				dialog.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		frmLunaStudio.getContentPane().setLayout(new BorderLayout(0, 0));

		txtpnSrcInput = new RSyntaxTextArea();
		txtpnSrcInput.setText("var day = \"Friday\";\r\n\r\nprint \"Today is \" + day;");
//		txtpnSrcInput.setText("print \"Hi, from Luna Studio!\";");
//		txtpnSrcInput.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
//		frmLunaStudio.getContentPane().add(txtpnSrcInput);

		RTextScrollPane scrollPane = new RTextScrollPane(txtpnSrcInput);
		frmLunaStudio.getContentPane().add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setEditable(false);
		frmLunaStudio.getContentPane().add(textArea, BorderLayout.SOUTH);

	}

	private void loadScript(String path) {
		try {
			String content = Files.readString(Paths.get(path));
			txtpnSrcInput.setText(content);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frmLunaStudio, "Error loading file: " + e.getMessage(), "File Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private void run() {
		textArea.setText("Running...\n");

		try {
			File tempFile = File.createTempFile("luna_", ".luna");
			tempFile.deleteOnExit();
			Files.writeString(tempFile.toPath(), txtpnSrcInput.getText());

			ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/classes", "com.jjjclarke.luna.Luna",
					tempFile.getAbsolutePath());
			pb.directory(new File(System.getProperty("user.dir")));

			Process process = pb.start();

			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line).append("\n");
			}

			BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = errorReader.readLine()) != null) {
				output.append("[ERROR] ").append(line).append("\n");
			}

			int exitCode = process.waitFor();
			if (output.length() == 0) {
				textArea.setText("(No output)");
			} else {
				textArea.setText(output.toString());
			}

			if (exitCode != 0) {
				textArea.append("\n[Process exited with code " + exitCode + "]");
			}
		} catch (IOException e) {
			textArea.setText("Error creating or running script: " + e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			textArea.setText("Execution interrupted: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
