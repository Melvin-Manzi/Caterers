public class PartyBooking extends Booking implements CateringService {
    private String partyType;

    public PartyBooking(String customerName, String eventDate, int numberOfGuests, String partyType) {
        super(customerName, eventDate, numberOfGuests);
        this.partyType = partyType;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    @Override
    public void displayDetails() {
        System.out.println("Party Booking for " + getCustomerName());
        System.out.println("Date: " + getEventDate() + ", Guests: " + getNumberOfGuests());
        System.out.println("Party Type: " + partyType);
    }

    @Override
    public void provideService() {
        System.out.println("Providing catering for a " + partyType + " party.");
    }
}
