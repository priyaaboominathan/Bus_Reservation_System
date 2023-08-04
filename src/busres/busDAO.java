package busres;
import java.sql.*;



public class busDAO {
    public void displayBus() throws SQLException {
        Connection con = DBcon.getCon();
        String query = "select * from Bus";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            System.out.println("Bus.No: "+rs.getInt(1));
            System.out.println("Capacity of the bus: "+rs.getInt(2));
            System.out.println("Number of seats available: "+rs.getInt(3));
            System.out.println("Ticket price: "+rs.getInt(4));
            System.out.println("----------------------------------------------------------------------");
        }
    }
    public int getseats(int num) throws SQLException {
        Connection con = DBcon.getCon();
        String query = "select seats_available from Bus where Bus_no = "+num;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

       rs.next();
        return rs.getInt(1);

    }
    public int getprice(int num) throws SQLException {
        Connection con = DBcon.getCon();
        String query = "select ticket_price from Bus where Bus_no = "+num;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }
    public void updateseats(int Bus_no, int seats_booked) throws SQLException {
        Connection con = DBcon.getCon();
        String query = " select seats_available from Bus where Bus_no = "+Bus_no;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        int seats = rs.getInt(1)-seats_booked;

        query = "update Bus set seats_available = "+seats+" where Bus_no = "+Bus_no;
         st = con.createStatement();
        st.executeUpdate(query);
    }
}
