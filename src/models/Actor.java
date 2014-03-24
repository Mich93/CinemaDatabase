package models;

public class Actor {
private String name,surname;
private int actorid;

public Actor() {
	super();
}
public Actor(String name, String surname, int actorid) {
	super();
	this.name = name;
	this.surname = surname;
	this.actorid = actorid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSurname() {
	return surname;
}
public void setSurname(String surname) {
	this.surname = surname;
}
public int getActorid() {
	return actorid;
}
public void setActorid(int actorid) {
	this.actorid = actorid;
}
@Override
public String toString() {
	return "Actor [name=" + name + ", surname=" + surname + "]";
}


}
