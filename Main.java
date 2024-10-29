import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "bookings.dat";
    private static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        loadBookings();  // Load bookings from file at startup
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Catering Service Booking ---");
            System.out.println("1. Add Party Booking");
            System.out.println("2. Add Wedding Booking");
            System.out.println("3. View All Bookings");
            System.out.println("4. Edit a Booking");
            System.out.println("5. Delete a Booking");
            System.out.println("0. Exit");

            choice = getValidInt(scanner, "Enter your choice: ");
            switch (choice) {
                case 1 -> addPartyBooking(scanner);
                case 2 -> addWeddingBooking(scanner);
                case 3 -> viewAllBookings();
                case 4 -> editBooking(scanner);
                case 5 -> deleteBooking(scanner);
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        scanner.close();
    }

    private static void addPartyBooking(Scanner scanner) {
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Event Date: ");
        String date = scanner.nextLine();
        int guests = getValidInt(scanner, "Enter Number of Guests: ");
        System.out.print("Enter Party Type: ");
        String type = scanner.nextLine();

        bookings.add(new PartyBooking(name, date, guests, type));
        saveBookings();
        System.out.println("Party Booking added successfully!");
    }

    private static void addWeddingBooking(Scanner scanner) {
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Event Date: ");
        String date = scanner.nextLine();
        int guests = getValidInt(scanner, "Enter Number of Guests: ");
        System.out.print("Enter Wedding Theme: ");
        String theme = scanner.nextLine();

        bookings.add(new WeddingBooking(name, date, guests, theme));
        saveBookings();
        System.out.println("Wedding Booking added successfully!");
    }

    private static void viewAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        for (int i = 0; i < bookings.size(); i++) {
            System.out.println("\nBooking #" + (i + 1));
            bookings.get(i).displayDetails();
            if (bookings.get(i) instanceof CateringService) {
                ((CateringService) bookings.get(i)).provideService();
            }
        }
    }

    private static void editBooking(Scanner scanner) {
        int bookingNumber = getValidInt(scanner, "Enter booking number to edit: ");
        if (bookingNumber < 1 || bookingNumber > bookings.size()) {
            System.out.println("Invalid booking number.");
            return;
        }

        Booking booking = bookings.get(bookingNumber - 1);
        System.out.print("Enter new Event Date: ");
        booking.setEventDate(scanner.nextLine());
        booking.setNumberOfGuests(getValidInt(scanner, "Enter new Number of Guests: "));

        if (booking instanceof PartyBooking) {
            System.out.print("Enter new Party Type: ");
            ((PartyBooking) booking).setPartyType(scanner.nextLine());
        } else if (booking instanceof WeddingBooking) {
            System.out.print("Enter new Wedding Theme: ");
            ((WeddingBooking) booking).setWeddingTheme(scanner.nextLine());
        }

        saveBookings();
        System.out.println("Booking updated successfully!");
    }

    private static void deleteBooking(Scanner scanner) {
        int bookingNumber = getValidInt(scanner, "Enter booking number to delete: ");
        if (bookingNumber < 1 || bookingNumber > bookings.size()) {
            System.out.println("Invalid booking number.");
            return;
        }

        bookings.remove(bookingNumber - 1);
        saveBookings();
        System.out.println("Booking deleted successfully!");
    }

    private static void saveBookings() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(bookings);
        } catch (IOException e) {
            System.out.println("Error saving bookings: " + e.getMessage());
        }
    }

    private static void loadBookings() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            bookings = (List<Booking>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous bookings found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading bookings: " + e.getMessage());
        }
    }

    private static int getValidInt(Scanner scanner, String prompt) {
        int number;
        while (true) {
            try {
                System.out.print(prompt);
                number = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
