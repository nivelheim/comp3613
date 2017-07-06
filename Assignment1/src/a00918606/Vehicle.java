/**
 * 
 */
package a00918606;

/**
 * @author Velaciela
 *
 */
public class Vehicle {
	int id;
	String manufacturer;
	String model;
	int year;
	int mileage;
	String addon;
	int price;
	
	public Vehicle(int id, String mnf, String model, int year, int mil, String add, int price) {
		this.id = id;
		manufacturer = mnf;
		this.model = model; 
		this.year = year;
		mileage = mil;
		addon = add;
		this.price = price;
	}
	
	public Vehicle()
	{
		//def
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getAddon() {
		return addon;
	}

	public void setAddon(String addon) {
		this.addon = addon;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
