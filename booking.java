package hotel;

import java.sql.*;

class booking
{
    static void ViewAvailableRooms() throws Exception
    {
        Connection con=DBconnection.connection();
        Statement st=con.createStatement();
        ResultSet rs =st.executeQuery("select * from rooms");
        System.out.println("RoomNo\tRoomType\tPrice\tAvailability");
        while(rs.next())
        {
            System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4));
        }
        con.close();
        st.close();
    }
    static String CheckAvailability(int roomno,Date from,Date to) throws Exception
    {
        Connection con=DBconnection.connection();
        String query="SELECT Availability FROM rooms WHERE RoomNo=? ";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1, roomno);
        ResultSet res=ps.executeQuery();
        res.next();
        if(res.getString(1).equals("occupied"))
        {
            String qu1="SELECT todate FROM RoomBookings WHERE roomno=?";
            PreparedStatement ps2=con.prepareStatement(qu1);
            ps2.setInt(1, roomno);
            ResultSet rs2=ps2.executeQuery();
            while(rs2.next())
            {
                if(from.compareTo(rs2.getDate(1))<=0)
                {
                    con.close();
                    ps.close();
                    ps2.close();
                    return "OCCUPIED";   
                }
            }
            con.close();
            ps.close();
            ps2.close();
            return "VACANT";   
        }
        else if(res.getString(1).equals("booked"))
        {
            String qu="SELECT fromdate,todate FROM RoomBookings WHERE roomno=?";
            PreparedStatement ps1=con.prepareStatement(qu);
            ps1.setInt(1, roomno);
            ResultSet rs1=ps1.executeQuery();
            while(rs1.next())
            {
                if(from.compareTo(rs1.getDate(2))<=0 && to.compareTo(rs1.getDate(1))>=0)
                {
                    con.close();
                    ps.close();
                    ps1.close();
                    return "BOOKED";
                }
            }
            con.close();
            ps.close();
            ps1.close();
            return "VACANT";
        }
        else{
            con.close();
            ps.close();
            return "VACANT";
        }
    }
    static int BookRoom(int roomno) throws Exception
    {
        Connection con=DBconnection.connection();
        String query="UPDATE rooms SET Availability=? where RoomNo=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,"booked");
        ps.setInt(2, roomno);
        int count=ps.executeUpdate();
        con.close();
        ps.close();
        return count;
    }
}