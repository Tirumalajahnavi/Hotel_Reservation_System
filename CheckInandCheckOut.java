package hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class CheckInandCheckOut
{
    static boolean CHECKcheckin(int roomno,int cusid) throws Exception
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
    static int RoomCheckin(int roomno) throws Exception
    {
        Connection con=DBconnection.connection();
        String query="UPDATE rooms SET availability='occupied' WHERE RoomNo=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1,roomno);
        int count=ps.executeUpdate();
        con.close();
        ps.close();
        return count;
    }
    static boolean CHECKcheckout(int roomno,int cusid) throws Exception
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
    static int RoomCheckout(int roomno) throws Exception
    {
        Connection con=DBconnection.connection();
        String query="UPDATE rooms SET availability='vacant' WHERE RoomNo=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1,roomno);
        int count=ps.executeUpdate();
        con.close();
        ps.close();
        return count;
    }
}