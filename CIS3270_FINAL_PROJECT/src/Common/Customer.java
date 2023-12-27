package Common;

public class Customer {
//private String ssn;
//public String password;
	private String bookedFlightId;
	 
	private String bookedFlightTime;
	 
	private String bookedArrivalCity;
	 
	private String bookedDepartureCity;
	 
	private String bookedFlightDate;
	 
	private String bookedAirlineName;
	
	private String reservationDate;
	
	private int bookedPrice;
	
	private int bookedReservationID;
	 
	private int numOfTravelers;
	 private String username;
	 
	 private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBookedFlightId() {
		return bookedFlightId;
	}

	public void setBookedFlightId(String bookedFlightId) {
		this.bookedFlightId = bookedFlightId;
	}

	public String getBookedFlightTime() {
		return bookedFlightTime;
	}

	public void setBookedFlightTime(String bookedFlightTime) {
		this.bookedFlightTime = bookedFlightTime;
	}

	public String getBookedArrivalCity() {
		return bookedArrivalCity;
	}

	public void setBookedArrivalCity(String bookedArrivalCity) {
		this.bookedArrivalCity = bookedArrivalCity;
	}

	public String getBookedFlightDate() {
		return bookedFlightDate;
	}

	public void setBookedFlightDate(String bookedFlightDate) {
		this.bookedFlightDate = bookedFlightDate;
	}

	public String getBookedAirlineName() {
		return bookedAirlineName;
	}

	public void setBookedAirlineName(String bookedAirlineName) {
		this.bookedAirlineName = bookedAirlineName;
	}

	public String getBookedDepartureCity() {
		return bookedDepartureCity;
	}

	public void setBookedDepartureCity(String bookedDepartureCity) {
		this.bookedDepartureCity = bookedDepartureCity;
	}

	public int getBookedPrice() {
		return bookedPrice;
	}

	public void setBookedPrice(int bookedPrice) {
		this.bookedPrice = bookedPrice;
	}

	public int getBookedReservationID() {
		return bookedReservationID;
	}

	public void setBookedReservationID(int bookedReservationID) {
		this.bookedReservationID = bookedReservationID;
	}

	public int getNumOfTravelers() {
		return numOfTravelers;
	}

	public void setNumOfTravelers(int numOfTravelers) {
		this.numOfTravelers = numOfTravelers;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
}
