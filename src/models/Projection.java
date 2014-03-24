package models;

public class Projection {

	private int id;
	private String language;
	private String date;
	private String start;
	private String end;
	private int theaterNr;
	private String movie;
	public Projection(int id, String language, String date, String start,
			String end, int theaterNr, String movie) {
		super();
		this.id = id;
		this.language = language;
		this.date = date;
		this.start = start;
		this.end = end;
		this.theaterNr = theaterNr;
		this.movie = movie;
	}
	@Override
	public String toString() {
		return "Projection [id=" + id + ", language=" + language + ", date="
				+ date + ", start=" + start + ", end=" + end + ", theaterNr="
				+ theaterNr + ", movie=" + movie + "]";
	}
	
	public Projection() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getTheaterNr() {
		return theaterNr;
	}
	public void setTheaterNr(int theaterNr) {
		this.theaterNr = theaterNr;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	
	
}
