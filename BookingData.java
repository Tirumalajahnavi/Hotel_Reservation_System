package hotel;

import java.sql.*;

class BookingData
{
    static int BookingEntry(int cusid,int roomno,Date from,Date to) throws Exception
    {
        Connection con=DBconnection.connection();
        String query="INSERT INTO roombookings(cusid,roomno,fromdate,todate) VALUES (?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1,cusid);
        ps.setInt(2,roomno);
        ps.setDate(3,from);
        ps.setDate(4,to);
        int count=ps.executeUpdate();
        con.close();
        ps.close();
        return count;
    }
}