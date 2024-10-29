public class WeddingBooking extends Booking implements CateringService {
    private String weddingTheme;

    public WeddingBooking(String customerName, String eventDate, int numberOfGuests, String weddingTheme) {
        super(customerName, eventDate, numberOfGuests);
        this.weddingTheme = weddingTheme;
    }

    public String getWeddingTheme() {
        return weddingTheme;
    }

    public void setWeddingTheme(String weddingTheme) {
        this.weddingTheme = weddingTheme;
    }

    @Override
    public void displayDetails() {
        System.out.println("Wedding Booking for " + getCustomerName());
        System.out.println("Date: " + getEventDate() + ", Guests: " + getNumberOfGuests());
        System.out.println("Wedding Theme: " + weddingTheme);
    }

    @Override
    public void provideService() {
        System.out.println("Providing catering with a " + weddingTheme + " theme.");
    }
}
