package busres;

import java.sql.*;
import java.util.Scanner;

public class bookingDAO {

    public void addBooking(Booking booking) throws SQLException {
        Connection con = DBcon.getCon();
        String query = "Insert into booking (passenger_name , Bus_no , No_of_seats , Total_Amt)  values(?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,booking.passenger_name);
        pst.setInt(2,booking.Bus_No);
        pst.setInt(3,booking.No_of_seats);
        pst.setInt(4,booking.Total_Amt);
        pst.executeUpdate();

        //to get booking id in booking obj:
        query = "select booking_id from booking where passenger_name = ? and Bus_no = ? and No_of_seats = ? ";
        pst = con.prepareStatement(query);
        pst.setString(1,booking.passenger_name);
        pst.setInt(2,booking.Bus_No);
        pst.setInt(3,booking.No_of_seats);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int bookingID = rs.getInt(1);
        displayTicket(bookingID);
        con.close();
    }
    public void displayTicket(int num) throws SQLException{
        Connection con = DBcon.getCon();
        String query = "Select * from booking where booking_id = "+num;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        System.out.println("Booking successful");
        while (rs.next()){
            System.out.println("Booking id "+rs.getInt(1));
            System.out.println("Passenger_name: "+rs.getString(2));
            System.out.println("Bus No: "+rs.getInt(3));
            System.out.println("No Of Seats: "+rs.getInt(4));
            System.out.println("Total amount: "+rs.getInt(5));
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }
    public void cancellation() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your booking id to cancel tickets: ");
        int id_to_cancel = scanner.nextInt();
        if(checkID(id_to_cancel)) {cancel_ticket(id_to_cancel);}
        else{
            System.out.println("Kindly check your booking Id and try once again.");
        }
    }
    public boolean checkID(int id_to_cancel) throws SQLException {
        Connection con = DBcon.getCon();
        String query = "Select booking_id from booking where booking_id = "+id_to_cancel;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
       try {
           rs.getInt(1);
           return true;
       }
       catch (Exception e){
           return false;
       }


    }
    public void cancel_ticket(int id_to_cancel) throws SQLException {
        Connection con = DBcon.getCon();

        String query = "select Total_Amt from booking where booking_id = "+id_to_cancel;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();

            System.out.println("Booking cancelled successfully");
            System.out.println("Rs. "+rs.getInt(1)+" will be refunded shortly.");
            query = "delete from booking where booking_id = "+id_to_cancel;
            st = con.createStatement();
            st.executeUpdate(query);

        con.close();
    }
    public void displayBookings() throws SQLException {
        Connection con = DBcon.getCon();
        String query = "select * from Booking";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            System.out.println("Booking id "+rs.getInt(1));
            System.out.println("Passenger_name: "+rs.getString(2));
            System.out.println("Bus No: "+rs.getInt(3));
            System.out.println("No Of Seats: "+rs.getInt(4));
            System.out.println("Total amount: "+rs.getInt(5));
            System.out.println("----------------------------------------------------------------------");
        }
    }
}
