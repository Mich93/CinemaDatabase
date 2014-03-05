package models;

public class Movie {
private String title, category, directorname;

public Movie(){
	
}



public Movie(String title, String category, String directorname) {
	super();
	this.title = title;
	this.category = category;
	this.directorname = directorname;
}



public String getTitle() {
	return title;
}



public void setTitle(String title) {
	this.title = title;
}



public String getCategory() {
	return category;
}



public void setCategory(String category) {
	this.category = category;
}



public String getDirectorname() {
	return directorname;
}



public void setDirectorname(String directorname) {
	this.directorname = directorname;
}



@Override
public String toString() {
	return "Movie [title=" + title + ", category=" + category
			+ ", directorname=" + directorname + "]";
}


}
