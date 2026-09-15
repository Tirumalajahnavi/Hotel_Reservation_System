package hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

public class Cancel {
    static boolean CHECKcancel(int roomno,int cusid) throws Exception
    {
        Connection con=DBconnection.connection();
        String query="SELECT cusid,roomno FROM roombookings";
        PreparedStatement ps=con.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            if(rs.getInt(1)==cusid && rs.getInt(2)==roomno)
            {
                return true;
            }
        }
        con.close();
        ps.close();
        return false;
    }
    static int cancelroom(int cusid) throws Exception
    {
        Connection con=DBconnection.connection();
        String q="DELETE FROM roombookings WHERE cusid=?";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setInt(1, cusid);
        int c1=ps.executeUpdate();
        String q1="SELECT cusid,cusname,phoneno,roomno,booking,checkin,checkout FROM customer WHERE cusid=?";
        PreparedStatement ps1=con.prepareStatement(q1);
        ps1.setInt(1, cusid);
        ResultSet rs=ps1.executeQuery();
        rs.next();
        int cusId=rs.getInt(1);
        String cusname=rs.getString(2);
        String phoneno=rs.getString(3);
        int roomno=rs.getInt(4);
        Date booking=rs.getDate(5);
        Date checkin=rs.getDate(6);
        Date checkout=rs.getDate(7);
        String q2="INSERT INTO cancelbooking(cusid,cusname,phoneno,roomno,booking,checkin,checkout,cancel) VALUES(?,?,?,?,?,?,?,SYSDATE)";
        PreparedStatement ps2=con.prepareStatement(q2);
        ps2.setInt(1, cusId);
        ps2.setString(2, cusname);
        ps2.setString(3, phoneno);
        ps2.setInt(4, roomno);
        ps2.setDate(5, booking);
        ps2.setDate(6, checkin);
        ps2.setDate(7, checkout);
        int c2=ps2.executeUpdate();
        String q3="DELETE FROM customer WHERE cusid=?";
        PreparedStatement ps3=con.prepareStatement(q3);
        ps3.setInt(1, cusid);
        int c3=ps3.executeUpdate();
        if(c1==1 && c2==1 && c3==1)
        {
            con.close();
            ps.close();
            ps1.close();
            ps2.close();
            ps3.close();
            return 1;
        }
        con.close();
        ps.close();
        ps1.close();
        ps2.close();
        ps3.close();
        return 0;
    }


}
