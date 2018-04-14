import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class UserGUI extends JPanel {
	
	
	  private JButton scrapeButton;
	  private JButton closeButton;
	  private JLabel label;
	  private JPanel main_panel;
	  private JTextArea textarea;
	  private JScrollPane jp;
	  
	  // add more UI components as needed
	  private Scraper scraper;
	
	
	  private String url;
	  
	  public UserGUI() throws IOException {
		 
		  // uses the url provided in the document
		 Scraper scraper = new Scraper(url);

	    initGUI();
	    doTheLayout();

	    scrapeButton.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            try {
					scrape();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            }
	    });
	    
	    closeButton.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            close();
	            }
	    });
	  } // end of constructor
	  
	  // Creates and initialize the GUI objects
	  private void initGUI(){
		  scrapeButton = new JButton("Scrape NFL Page");
		  closeButton = new JButton("Close");
		  label = new JLabel("Output");
		  
		  main_panel = new JPanel(new BorderLayout());
		  textarea = new JTextArea(30,100);
		  textarea.setEditable(false);
		  textarea.setLineWrap(true);
		  textarea.setWrapStyleWord(true);
		  jp = new JScrollPane(textarea);
		  jp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		  jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		  
	  }// end of creating objects method
	  
	  // Layouts the UI components as shown in the project document
	  private void doTheLayout(){
		  main_panel.add(scrapeButton, BorderLayout.NORTH);
		  main_panel.add(textarea, BorderLayout.CENTER);
		  main_panel.add(closeButton, BorderLayout.SOUTH);
		  
		  add(main_panel);
	  }// end of Layout method

	 
	// Uses the Scraper object reference to return and display the data as shown in the project document 
	 void scrape() throws IOException{
		 scraper = new Scraper(url);
		 scraper.parseData();
	 }// end of scrape action event method
	  
	 
	  void close(){
	      System.exit(0);
	  }// end of close action event method


	public static void main(String[] args) throws IOException {
	   JFrame f = new JFrame("NFL Stats");
	   f.setLocationRelativeTo(null);
	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   Container contentPane = f.getContentPane();
	   contentPane.add( new UserGUI());
	   
	   f.pack();
	   f.setVisible(true);
	}// end of main method

}// end of class
