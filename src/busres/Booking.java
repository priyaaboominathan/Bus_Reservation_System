package busres;

import java.sql.SQLException;

import java.util.Scanner;

public class Booking {

    String passenger_name;
    int Bus_No;

    int No_of_seats;
    int Total_Amt;
    Booking()  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        passenger_name = scanner.next();
        System.out.println("Enter the Bus.No: ");
        Bus_No = scanner.nextInt();
        System.out.println("Enter the number of seats: ");
        No_of_seats = scanner.nextInt();
    }
    public boolean isAvailable() throws SQLException {
        busDAO busDAO = new busDAO();
        int seats = busDAO.getseats(Bus_No);
        return (seats>=No_of_seats);
    }
    public void setAmt() throws SQLException {
        busDAO busDAO = new busDAO();
        int price = busDAO.getprice(Bus_No);
        Total_Amt = No_of_seats*price;
        busDAO.updateseats(Bus_No,No_of_seats);
    }


}
