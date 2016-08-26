package model;

public class CoffeeShopBean {
	int store_number;
	String name;
	String phone_number;
	String strt;
	String city;
	int zip;
	double	latitude;
	double longitude;
	double distance;


	public CoffeeShopBean(){

	}
	
	public CoffeeShopBean(int store_number, String name, String phone_number,
			String strt, String city, int zip, double d, double e) {
		super();
		this.store_number = store_number;
		this.name = name;
		this.phone_number = phone_number;
		this.strt = strt;
		this.city = city;
		this.zip = zip;
		this.latitude = d;
		this.longitude = e;
	
	}

	public CoffeeShopBean(int store_number, String name, String phone_number,
			String strt, String city, int zip, double d, double e,double distance) {
		super();
		this.store_number = store_number;
		this.name = name;
		this.phone_number = phone_number;
		this.strt = strt;
		this.city = city;
		this.zip = zip;
		this.latitude = d;
		this.longitude = e;
		this.distance=distance;
	}

	public int getStore_number() {
		return store_number;
	}

	public void setStore_number(int store_number) {
		this.store_number = store_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getStrt() {
		return strt;
	}

	public void setStrt(String strt) {
		this.strt = strt;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}


}
