package models;

import java.sql.Time;

public class Cinema {
private int cinemaid;
private String name, address, city;
private Time openhr,closehr;



public Cinema() {
	super();
}

public Cinema(int cinemaid, String name, String address, String city,
		Time openhr, Time closehr) {
	super();
	this.cinemaid = cinemaid;
	this.name = name;
	this.address = address;
	this.city = city;
	this.openhr = openhr;
	this.closehr = closehr;
}

public int getCinemaid() {
	return cinemaid;
}
public void setCinemaid(int cinemaid) {
	this.cinemaid = cinemaid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public Time getOpenhr() {
	return openhr;
}
public void setOpenhr(Time openhr) {
	this.openhr = openhr;
}
public Time getClosehr() {
	return closehr;
}
public void setClosehr(Time closehr) {
	this.closehr = closehr;
}

}
