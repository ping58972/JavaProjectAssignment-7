package edu.century.pa7;
/********************************************************************************
 * Author		:	Nalonsone Danddank										   	*
 * Class		:	CSCI 1082												   	*
 * Due Date		:	05/01/2018												   	*
 * Description 	:	ShoppingCartGUI class is defined for creating the frame 	*
 * 					that uses for sorting and saving the data which import from	*
 * 					out side, CSV file.  The class has created a ArrayList of	*
 * 					Product class that include name, id, description and price.	* 
 * 					store to the ArrayList for sorting and saving to CSV file.	*
 * ******************************************************************************/
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;

public class ShoppingCartGUI extends JFrame implements ActionListener {
	//Initiate instance variables
	private JPanel contentPane;
	private JTextArea textArea_L;
	private JTextField textFieldFileName;
	private JComboBox comboBox;
	private JTextArea textArea_R;
	private Product product;
	//Initiate arrayList of Product class
	private ArrayList<Product> productList;
	private PrintWriter printcsv;
	//Main method for call the frame.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShoppingCartGUI frame = new ShoppingCartGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 // Create constructor for build the frame.
	public ShoppingCartGUI() {
		//Setup the frame.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 700, 500);
		//create the content to hole all panel.
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//create panel for buttons commands.
		JPanel panelClicks = new JPanel();
		FlowLayout fl_panelClicks = (FlowLayout) panelClicks.getLayout();
		fl_panelClicks.setHgap(10);
		contentPane.add(panelClicks, BorderLayout.NORTH);
		//create the text fields.
		textFieldFileName = new JTextField("products.csv");
		panelClicks.add(textFieldFileName);
		textFieldFileName.setColumns(10);
		//
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(this);
		panelClicks.add(btnBrowse);
		//
		String [] listcomboBox = {"ID", "PRICE", "NAME", "DESCRIPTION"};
		comboBox = new JComboBox(listcomboBox);
		panelClicks.add(comboBox);
		//
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(this);
		panelClicks.add(btnSort);
		//
		JPanel panelTextArea = new JPanel();
		contentPane.add(panelTextArea, BorderLayout.CENTER);
		panelTextArea.setLayout(new GridLayout(0, 2, 5, 5));
		//
		JPanel panelOriginalFile = new JPanel();
		panelOriginalFile.setBorder(new TitledBorder(null, "Original File", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelTextArea.add(panelOriginalFile);
		panelOriginalFile.setLayout(new BorderLayout(0, 0));
		//
		JScrollPane scrollPane_L = new JScrollPane();
		panelOriginalFile.add(scrollPane_L, BorderLayout.CENTER);
		//
		textArea_L = new JTextArea();
		textArea_L.setEditable(false);
		scrollPane_L.setViewportView(textArea_L);
		//
		JPanel panelSortFile = new JPanel();
		panelSortFile.setBorder(new TitledBorder(null, "Sorted File", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelTextArea.add(panelSortFile);
		panelSortFile.setLayout(new BorderLayout(0, 0));
		//
		JScrollPane scrollPane_R = new JScrollPane();
		panelSortFile.add(scrollPane_R, BorderLayout.CENTER);
		//
		textArea_R = new JTextArea();
		textArea_R.setEditable(false);
		scrollPane_R.setViewportView(textArea_R);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//the string for hole command from a click.
		String command = e.getActionCommand();
		//when click the "Browse" button, 
		//openFile() method will process.
		if(command.equals("Browse"))
			openFile();
		//when click the "Sort" button, 
		//sortFile() method will process.
		if(command.equals("Sort"))
			sortFile();
	}
	//Method for opening a CSV file data to store in the ArrayList. 
	private void openFile() {
		JFileChooser folder = new JFileChooser();	
		if(folder.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			textArea_L.setText("");
			File file = folder.getSelectedFile(); 
			productList = new ArrayList<Product>();
			try{
				Scanner read = new Scanner(file);	
					while(read.hasNextLine()) {
						product = new Product();
						String data = read.nextLine();
						String [] sprit = data.split(", ");
						product.setUid(Integer.parseInt(sprit[0]));
						product.setPrice(Double.parseDouble(sprit[1]));
						product.setName(sprit[2]);
						product.setDescription(sprit[3]);					
						textArea_L.append(product.toString()+"\n");
						productList.add(product);
					}
				read.close();
				textFieldFileName.setText(file.getName());
			}catch(FileNotFoundException e) {			
				e.printStackTrace();
			}
		}else textArea_L.append("Prease! Browse a File again!\n");		
	}
	//Method for choice the options of sorting data. 
	private void sortFile() {
		if(productList==null) {
			//textArea_L.append("Prease! Browse a File first!\n");
			productList = new ArrayList<Product>();
			try{
				Scanner read = new Scanner(new File("products.csv"));	
					while(read.hasNextLine()) {
						product = new Product();
						String data = read.nextLine();
						String [] sprit = data.split(", ");
						product.setUid(Integer.parseInt(sprit[0]));
						product.setPrice(Double.parseDouble(sprit[1]));
						product.setName(sprit[2]);
						product.setDescription(sprit[3]);					
						textArea_L.append(product.toString()+"\n");
						productList.add(product);
					}
				read.close();
				//textFieldFileName.setText(file.getName());
			}catch(FileNotFoundException e) {			
				e.printStackTrace();
			}
			if(comboBox.getSelectedItem().toString().equals("ID")) {
				saveFile("SortIDFile.csv", Product.CompareById);
			}
			if(comboBox.getSelectedItem().toString().equals("PRICE")) {
				saveFile("SortPriceFile.csv", Product.CompareByPrice);
			}
			if(comboBox.getSelectedItem().toString().equals("DESCRIPTION")) {
				saveFile("SortDescriptionFile.csv", Product.CompareByDescription);
			}
			if(comboBox.getSelectedItem().toString().equals("NAME")) {
				saveFile("SortNameFile.csv", Product.CompareByName);			
			}
		}else {
			if(comboBox.getSelectedItem().toString().equals("ID")) {
				saveFile("SortIDFile.csv", Product.CompareById);
			}
			if(comboBox.getSelectedItem().toString().equals("PRICE")) {
				saveFile("SortPriceFile.csv", Product.CompareByPrice);
			}
			if(comboBox.getSelectedItem().toString().equals("DESCRIPTION")) {
				saveFile("SortDescriptionFile.csv", Product.CompareByDescription);
			}
			if(comboBox.getSelectedItem().toString().equals("NAME")) {
				saveFile("SortNameFile.csv", Product.CompareByName);			
			}
		}		
	}
	//Method for sorting data and saving to each CSV file.
	private void saveFile(String fileName, Comparator<Product> Compare) {
		textArea_R.setText("");
		Collections.sort(productList, Compare);
		for(int i=0; i<productList.size(); i++) 
			textArea_R.append(productList.get(i).toString()+"\n");
		try{
			printcsv = new PrintWriter(fileName);
			for(int i=0; i<productList.size(); i++) {
				printcsv.println(productList.get(i).toString());
			}
			printcsv.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
			textArea_L.setText("Prease! Browse a File first!");
		}
	}
}
