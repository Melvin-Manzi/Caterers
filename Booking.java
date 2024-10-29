public abstract class Booking {
    private String customerName;
    private String eventDate;
    private int numberOfGuests;

    public Booking(String customerName, String eventDate, int numberOfGuests) {
        this.customerName = customerName;
        this.eventDate = eventDate;
        this.numberOfGuests = numberOfGuests;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public abstract void displayDetails();
}
