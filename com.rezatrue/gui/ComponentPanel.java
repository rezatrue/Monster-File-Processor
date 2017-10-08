package gui;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;



import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ComponentPanel extends JPanel {
	private JLabel sourceLebel;
	private JTextField textField;
	private JButton sourceButton;
	private JButton goButton;
	private JButton starButton;
	private GoListioner goListioner;
	private SourceListener sourceListener;
	private SizeListener sizeListener;
	private JFileChooser fileChooser;
	private Desktop desktop;
	
	public ComponentPanel(){
		
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		
		sourceLebel = new JLabel("Source :");
		textField = new JTextField(18);
		sourceButton = new JButton("Browse");
		goButton = new JButton("Start");
		//starButton = new JButton("# # Dedicated to # #");
		starButton = new JButton("* * Show Comments * *");

		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setDialogTitle("Select CV Folder");
		
		sourceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eev) {
				String path = null;
				if (fileChooser.showOpenDialog(ComponentPanel.this) == JFileChooser.APPROVE_OPTION) {
							File file = fileChooser.getSelectedFile();
							path = file.getAbsolutePath();
							textField.setText(path.toString());
						}
						if (sourceListener != null) {
							sourceListener.sourcechooser(path);
						}
			}
		});
		
		goButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (goListioner != null) {
					goListioner.start();
				}
			}
		});
		
		starButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				JButton btn = (JButton) ee.getSource();
				//if (btn.getText().equals("# # Dedicated to # #")) {
					//starButton.setText("# # Minimize Comments # #");
					if (btn.getText().equals("* * Show Comments * *")) {
						starButton.setText("* * Minimize Comments * *");	
					if (sizeListener != null) {
						sizeListener.sizechooser("big");
					}
				} else {
					//starButton.setText("# # Dedicated to # #");
					starButton.setText("* * Show Comments * *");
					if (sizeListener != null) {
						sizeListener.sizechooser("small");
					}
				}
			}
		});
		
		componentLayout();
		
	}

	private void componentLayout() {
		GridBagConstraints gc = new GridBagConstraints();

		        // first row 
				gc.gridx = 0;
				gc.gridy = 0;
				gc.weightx = 1;
				gc.weighty = 1;
				gc.gridheight = 1;
				gc.gridwidth = 1;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.LINE_START;
				add(sourceLebel, gc);
				
				gc.gridx++;
				gc.insets = new Insets(5, 0, 5, 0);
				add(textField, gc);
				
				gc.gridx++;
				gc.gridheight = 2;
				gc.fill = GridBagConstraints.BOTH;
				gc.insets = new Insets(5, 5, 0, 5);
				add(sourceButton, gc);
				
				gc.gridx++;
				gc.gridheight = 2;
				gc.fill = GridBagConstraints.BOTH;
				gc.anchor = GridBagConstraints.CENTER;
				add(goButton, gc);
				
				
				gc.gridx = 1;
				gc.gridy = 1;
				gc.weightx = 1;
				gc.weighty = 2;
				gc.gridheight = 1;
				gc.gridwidth = 1;
				gc.fill = GridBagConstraints.BOTH;
				gc.anchor = GridBagConstraints.LINE_START;
				gc.insets = new Insets(0, 0, 0, 5);
				add(starButton, gc);
	}

	public void setGoListener(GoListioner golistioner) {
		this.goListioner = golistioner;		
	}

	public void setSourceListener(SourceListener sListener) {
		this.sourceListener = sListener;
	}
	
	public void setSizeListener(SizeListener sizeListener) {
		this.sizeListener = sizeListener;
	}
	
	
}
