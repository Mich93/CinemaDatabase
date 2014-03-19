package models;

public class Theater {
int theaterno, capacity,cinemaid;

public int getTheaterno() {
	return theaterno;
}

public Theater() {
	super();
}

public Theater(int theaterno, int capacity, int cinemaid) {
	super();
	this.theaterno = theaterno;
	this.capacity = capacity;
	this.cinemaid = cinemaid;
}

public void setTheaterno(int theaterno) {
	this.theaterno = theaterno;
}

public int getCapacity() {
	return capacity;
}

public void setCapacity(int capacity) {
	this.capacity = capacity;
}

public int getCinemaid() {
	return cinemaid;
}

public void setCinemaid(int cinemaid) {
	this.cinemaid = cinemaid;
}

}
