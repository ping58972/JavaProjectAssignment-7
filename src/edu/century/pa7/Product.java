package edu.century.pa7;
/********************************************************************************
 * Author		:	Nalonsone Danddank										   	*
 * Class		:	CSCI 1082												   	*
 * Due Date		:	05/01/2018												   	*
 * Description 	:	Product class is defined to getting the data from the 	   	*
 * 					product like name, id, description and price. Then, the 	*
 * 					class will store to the ArrayList for sorting and saving 	*
 * 					to CSV file.												*
 * ******************************************************************************/
import java.util.Comparator;

public class Product {
	//Initiate instance variables
	private String name;
	private int uid;
	private String description;
	private double price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return uid  +", "+ price +", "+ name +", "+ description;
	}
	//Create Comparator Interface for using compare by ID.
	public static Comparator<Product> CompareById = new 
			Comparator<Product>() { 
			@Override
			public int compare(Product o1, Product o2) {
				return( o1.uid - o2.uid);
			}
	};
	//Create Comparator Interface for using compare by Name.
	public static Comparator<Product> CompareByName = new 
			Comparator<Product>() { 
			@Override
			public int compare(Product o1, Product o2) {
				return( o1.name.compareToIgnoreCase(o2.name));
			}
	};
	//Create Comparator Interface for using compare by Description.
	public static Comparator<Product> CompareByDescription = new 
			Comparator<Product>() { 
			@Override
			public int compare(Product o1, Product o2) {
				return( o1.description.compareToIgnoreCase(o2.description));
			}
	};
	//Create Comparator Interface for using compare by Price.
	public static Comparator<Product> CompareByPrice = new 
			Comparator<Product>() { 
			@Override
			public int compare(Product o1, Product o2) {
				if((o1.price - o2.price)>0)
				return 1;
				else if ((o1.price - o2.price)<0)
					return -1;
				else return 0;
			}
	};	
}
