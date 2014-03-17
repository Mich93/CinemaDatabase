package models;

public class Ticket {
	
	private int ticketid;
	private String accountid;
	private int seatnr;
	private String money;
	private int projectionid;
	
	
	public Ticket(int ticketid, String accountid, int seatnr, String money,
			int projectionid) {
		super();
		this.ticketid = ticketid;
		this.accountid = accountid;
		this.seatnr = seatnr;
		this.money = money;
		this.projectionid = projectionid;
	}


	public int getTicketid() {
		return ticketid;
	}


	public void setTicketid(int ticketid) {
		this.ticketid = ticketid;
	}


	public String getAccountid() {
		return accountid;
	}


	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}


	public int getSeatnr() {
		return seatnr;
	}


	public void setSeatnr(int seatnr) {
		this.seatnr = seatnr;
	}


	public String getMoney() {
		return money;
	}


	public void setMoney(String money) {
		this.money = money;
	}


	public int getProjectionid() {
		return projectionid;
	}


	public void setProjectionid(int projectionid) {
		this.projectionid = projectionid;
	}


	@Override
	public String toString() {
		return "Ticket [ticketid=" + ticketid + ", accountid=" + accountid
				+ ", seatnr=" + seatnr + ", money=" + money + ", projectionid="
				+ projectionid + "]";
	}

	
	
}
