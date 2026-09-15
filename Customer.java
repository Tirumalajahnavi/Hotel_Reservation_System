package hotel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class Customer
{
    static int CustomerBookingEntry(int cusid,int empId,String cusname,String phoneno,int roomno,Date fromDate,Date toDate) throws Exception
    {
        Connection con=DBconnection.connection();
        String query="INSERT INTO customer(cusid,empid,cusname,phoneno,roomno,booking,checkin,checkout) VALUES(?,?,?,?,?,SYSDATE,?,?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1,cusid);
        ps.setInt(2,empId);
        ps.setString(3, cusname);
        ps.setString(4,phoneno);
        ps.setInt(5, roomno);
        ps.setDate(6, fromDate);
        ps.setDate(7, toDate);
        int count=ps.executeUpdate();
        con.close();
        ps.close();
        return count;
    }
}