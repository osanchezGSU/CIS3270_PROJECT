package Common;

public class Flight {
	private String dateString;
	private String departureCity;
	private String arrivalCity;
	private String flightID;
	private String time;
	private Integer price;
	private String travel;
	private String imgSrc;
	private String airlineName;
	private String filteredTime;
	private Integer numOfTravelers;
	private Integer openSeats;
	
	private Double taxes = 0.075;
	private Double securityFee = 5.60;
	private Double governmentExcise = 4.00;
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getTravel() {
		return travel;
	}
	public void setTravel(String travel) {
		this.travel = travel;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getFlightID() {
		return flightID;
	}
	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getDepartureCity() {
		return departureCity;
	}
	
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public String getFilteredTime() {
		return filteredTime;
	}
	public void setFilteredTime(String filteredTime) {
		this.filteredTime = filteredTime;
	}
	public Integer getNumOfTravelers() {
	    return numOfTravelers;
	}

	public void setNumOfTravelers(Integer numOfTravelers) {
	    this.numOfTravelers = numOfTravelers;
	}
	public Double getTaxes() {
		return taxes;
	}
	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}
	public Double getSecurityFee() {
		return securityFee;
	}
	public void setSecurityFee(Double securityFee) {
		this.securityFee = securityFee;
	}
	public Double getGovernmentExcise() {
		return governmentExcise;
	}
	public void setGovernmentExcise(Double governmentExcise) {
		this.governmentExcise = governmentExcise;
	}
	public Integer getOpenSeats() {
		return openSeats;
	}
	public void setOpenSeats(Integer openSeats) {
		this.openSeats = openSeats;
	}
	
	

}
