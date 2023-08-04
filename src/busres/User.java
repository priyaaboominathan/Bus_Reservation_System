package busres;

import java.sql.SQLException;
import java.util.Scanner;

public class User {
    public static void main(String[] args) throws SQLException {

        System.out.println("Welcome to LOTUS travels");
        boolean bool = true;

        while (bool){
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Enter 1 for displaying bus details\nEnter 2 for booking tickets" +
                    "\nEnter 3 for cancel booking\nEnter 4 for details of successful bookings");
            System.out.println("----------------------------------------------------------------------");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    busDAO bus = new busDAO();
                    bus.displayBus();
                    break;
                case 2:
                    Booking booking = new Booking();
                    if (booking.isAvailable()){
                        booking.setAmt();
                        bookingDAO bookingDAO = new bookingDAO();
                        bookingDAO.addBooking(booking);
                    }
                    else System.out.println("Sorry seats are full");
                    break;
                case 3:
                    bookingDAO bookingDAO = new bookingDAO();
                    bookingDAO.cancellation();
                    break;
                case 4:
                    bookingDAO bookingDAO1 = new bookingDAO();
                    bookingDAO1.displayBookings();
                    break;
                default:
                    bool=false;
            }
        }
    }
}
