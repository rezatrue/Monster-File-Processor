package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main1 {
	 
	
	  public static void main(String[] args) throws Exception
	  {
	    final JFrame frame = new JFrame("Java TextArea with Background Image");
	    final JavaTextAreaWithBackgroundImage textArea = new JavaTextAreaWithBackgroundImage(new File("star1.png"));
	    final JScrollPane scrollPane = new JScrollPane(textArea);
	 
	    SwingUtilities.invokeLater(new Runnable()
	    {
	      public void run()
	      {
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        scrollPane.setPreferredSize(new Dimension(360,225));
	        frame.getContentPane().add(scrollPane);
	        textArea.setText("Some sample text here ...");
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	      }
	    });
	     
	  }
	
	
	
	
	
	/**
	 * A Java text area (JTextArea) with a background image.
	 * You can use this class to add a watermark image to a Java text area,
	 * or any other background image you like.
	 * Alvin Alexander, <a href="http://alvinalexander.com" title="http://alvinalexander.com">http://alvinalexander.com</a>
	 */
	public static class JavaTextAreaWithBackgroundImage extends JTextArea
	{
	  private final BufferedImage bufferedImage;
	  private final TexturePaint texturePaint;
	 
	  /**
	   * Supply an image file as an input field.
	   * TODO - It would be a good idea to accept an image url as a separate constructor.
	   */
	  public JavaTextAreaWithBackgroundImage(File file) throws IOException
	  {
	    super();
	    bufferedImage = ImageIO.read(file);
	    Rectangle rect = new Rectangle(0, 0, bufferedImage.getWidth(null), bufferedImage.getHeight(null));
	    texturePaint = new TexturePaint(bufferedImage, rect);
	    setOpaque(false);
	  }
	 
	  /**
	   * Override the painComponent method to do our image drawing.
	   */
	  public void paintComponent(Graphics g)
	  {
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setPaint(texturePaint);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    super.paintComponent(g);
	  }
	 

	}


}
