package gui;

import gui.Main1.JavaTextAreaWithBackgroundImage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ExtendedPanel extends JPanel {
	private JLabel sourceLebel;
	private JTextArea textArea;

	public ExtendedPanel(){
		setLayout(new BorderLayout());
		sourceLebel = new JLabel(
				"                                 Dedicated to SJ INNOVATION LLC");

		//textArea = new JTextArea();
	    JavaTextAreaWithBackgroundImage textArea = null;
		try {
			textArea = new JavaTextAreaWithBackgroundImage(new File("pic/star3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(360, 325));

		add(sourceLebel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.SOUTH);
		
		textArea.append("\n\r \n\r \n\r \n\r \n\r \n\r  \n\r "
				+"                         On behalf of SJI Dhaka Team   \n\r "
				+"\n\r"
				+"                                   Nasreen Ahmed           \n\r "		
				+"\n\r"
				+"      Day Team     -     Evening Team    -        Night Team\n\r "			
				+"\n\r"
				+"        Sumon                      Shafil                     Shanto\n\r "
				+"      Ali Reza                      Reaz                        Saud\n\r "
				+"       Nadim                       Sujit                       Mostafa\n\r "
				+"       Habiba                  Sakhawat                 Maheraj\n\r "
				+"       Faysal                      Enamul                   Mamun\n\r "
				+"       Jakia                        Mehedi                    Roman\n\r "
				+"       Maruf                         Irfan                      Shibly\n\r "
				+"       Siddiq                        Bappi                       Mojo\n\r "
				+"        Runa                         Rasel                   Mohiuddin\n\r "
				+"       Harun                        Ismail                       Babu\n\r "
				+"       Sourove                     Robin                       Nurul\n\r "
				+"       Happy                       Plaabon                     Robin\n\r "
				+"       Munni                        Shojib                      Asad\n\r "
				+"       Enayet                       Ehsan                      Orko\n\r"
				+"       Shohidul                     Foysal                    Shakil\n\r"
				+"       Romana                        Joy                       Minhaz\n\r"
				+"                                           Monty		\n\r"
				+"                                           \n\r"
				+"                              Developed by Ali Ahmed Reza		\n\r"
				
				);
		
		
		
		textArea.setCaretPosition(0);
		Font font = new Font("Century Schoolbook Bold", Font.ITALIC, 14);
		textArea.setFont(font);
		textArea.setForeground(Color.BLACK);
		textArea.setEditable(false);
	}
	
	
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
