package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.w3c.dom.events.Event;

public class MainFrame extends JFrame {
	private ComponentPanel componentPanel;
	private ExtendedPanel extendedPanel;
	private FileSystem fileSystem;
	private HashMap<String, String> data;
	//private static File file; 
	private static String zipPath;

	public MainFrame(){
		super("Monster Data Processor");
		fileSystem = new FileSystem();

		data = new HashMap<String, String>();

		setLayout(new BorderLayout());

		componentPanel = new ComponentPanel();
		extendedPanel = new ExtendedPanel();

		add(componentPanel, BorderLayout.NORTH);
		add(extendedPanel, BorderLayout.SOUTH);

		componentPanel.setGoListener(new GoListioner() {
			public void start() {

				if (zipPath == null || zipPath == "") {
					// error massage: Select a Folder containing files
					JOptionPane.showMessageDialog(MainFrame.this,
							"Select a Folder containing files", "Error",
							JOptionPane.ERROR_MESSAGE);

				} else {
					// count & collect folder name

					//String[] fileName = null;
					String fileName = null;
					try {
						//fileName = filemanager.fileCounter(file);
						fileName = zipPath;
						System.out.println(fileName);
						if (fileName != null) {
							//data = filemanager.spaceFinder(fileName, file);
							File dir = new File("Monster-Data");
							while (dir.exists()) {
								// this method create problem
								// filemanager.deleteFolder(dir);
								JOptionPane
										.showMessageDialog(
												MainFrame.this,
												"Please remove previous file named \"Monster-Data\"",
												"Warning",
												JOptionPane.INFORMATION_MESSAGE);
							}
							dir.mkdir();

							System.out.println(dir.getAbsolutePath());
							ArrayList<String> conFile = fileSystem.CopyfromZip(fileName, dir.getAbsolutePath());
							// send massage : Rename completed
							JOptionPane
									.showMessageDialog(
											MainFrame.this,
											"Space Remove & Rename Completed \n\r Initiating Confidential file serach...",
											"Done",
											JOptionPane.INFORMATION_MESSAGE);
							
							if (conFile.isEmpty()) {
								System.out.println("lllllll");
							}else {
								for (int i = 0; i < conFile.size(); i++) {
									ProcessBuilder pb = new ProcessBuilder("Notepad.exe", conFile.get(i));
									pb.start();	
								}
							}
						} else {
							// send massage : Folder doesn't have an file
							JOptionPane.showMessageDialog(MainFrame.this,
									"Folder doesn't have an file", "Error",
									JOptionPane.ERROR_MESSAGE);

						}

					} catch (Exception e) {
						// send massage
						System.out.println("File Not Found");
						System.out
								.println("Please select right folder to processed");
					}
				}

			}

		});

		componentPanel.setSourceListener(new SourceListener() {
			public void sourcechooser(String zipPath) {
				MainFrame.zipPath = zipPath;
			}
		});
		
		componentPanel.setSizeListener(new SizeListener() {
			public void sizechooser(String size) {
				if (size.equals("big")) {
					setSize(new Dimension(450, 450));
				} else if (size.equals("small")) {
					setSize(new Dimension(450, 100));
				}
			}
		});
		
		setLocation(350, 0);
		setMinimumSize(new Dimension(450, 100));
		//setMaximumSize(new Dimension(450, 450));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

}
