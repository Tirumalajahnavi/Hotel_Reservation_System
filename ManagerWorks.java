package hotel;
import java.sql.*;


public class ManagerWorks {
    static int AddRoom(int roomno,String roomtype,int price) throws Exception
    {
        Connection con=DBconnection.connection();
        String q="INSERT INTO rooms(roomno,roomtype,price,availability) VALUES (?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(q);
        String status="vacant";
        ps.setInt(1, roomno);
        ps.setString(2, roomtype);
        ps.setInt(3, price);
        ps.setString(4, status);
        int count=ps.executeUpdate();
        con.close();
        ps.close();
        return count;
    }
    static int DeleteRoom(int roomno) throws Exception
    {
        Connection con=DBconnection.connection();
        String q="DELETE FROM rooms WHERE roomno=? ";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setInt(1, roomno);
        int count=ps.executeUpdate();
        con.close();
        ps.close();
        return count;
    }
    static int UpdatePrice(int roomno,int price) throws Exception
    {
        Connection con=DBconnection.connection();
        String q="UPDATE rooms SET price= ? WHERE roomno= ?";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setInt(1, price);
        ps.setInt(2, roomno);
        int count=ps.executeUpdate();
        con.close();
        ps.close();
        return count;
    }
    static int EmployeeEntry(int empid,String empname,String username,String password) throws Exception
    {
        Connection con=DBconnection.connection();
        String q="INSERT INTO receptionistdetails(empid,empname,username,password) VALUES(?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setInt(1,empid);
        ps.setString(2, empname);
        ps.setString(3, username);
        ps.setString(4, password);
        int count=ps.executeUpdate();
        con.close();
        ps.close();
        return count;
    }
    static void BookingDetails() throws Exception
    {
        Connection con=DBconnection.connection();
        String q="SELECT * FROM roombookings";
        PreparedStatement ps=con.prepareStatement(q);
        ResultSet rs=ps.executeQuery();
        System.out.println("\n CustomerId \t\t RoomNumber \t\t FromDate \t\t ToDate\n");
        while(rs.next())
        {
            System.out.println(rs.getInt("cusid")+"\t\t\t"+rs.getInt("roomno")+"\t\t\t"+rs.getDate("fromdate")+"\t\t"+rs.getDate("todate")+"\n");
        }
        con.close();
        ps.close();
    }
    static void CustomerDetails() throws Exception
    {
        Connection con=DBconnection.connection();
        String q="SELECT * FROM customer";
        PreparedStatement ps=con.prepareStatement(q);
        ResultSet rs=ps.executeQuery();
        System.out.println("\n CustomerId \t\t CustomerName \t\t EmployeeId \t\t PhoneNumber \t\t RoomNumber \t\t Booking \t\t Checkin \t\t Checkout\n");
        while(rs.next())
        {
            System.out.println(rs.getInt("cusid")+"\t\t\t"+rs.getString("cusname")+"\t\t"+rs.getInt("empid")+"\t\t\t"+rs.getString("phoneno")+"\t\t"+rs.getInt("roomno")+"\t\t\t"+rs.getDate("booking")+"\t\t"+rs.getDate("checkin")+"\t\t"+rs.getDate("checkout")+"\n");
        }
        con.close();
        ps.close();
    }
    static void EmployeeCheckInCount(int empid) throws Exception
    {
        Connection con=DBconnection.connection();
        String q="SELECT empname FROM receptionistdetails WHERE empid= ? ";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setInt(1, empid);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String name=rs.getString("empname");
        System.out.println("\n Employee Id: "+empid);
        System.out.println("\n Employee Name: "+name);
        String q1="SELECT cusname,roomno FROM customer WHERE empid=?";
        PreparedStatement ps1=con.prepareStatement(q1);
        ps1.setInt(1, empid);
        ResultSet rs1=ps1.executeQuery();
        System.out.println("\n Customers Checked in: \n");
        while(rs1.next())
        {
            System.out.println("\n "+rs1.getString("cusname")+"----->"+rs1.getInt("roomno"));
        }
        String q2="SELECT COUNT(*) FROM customer WHERE empid=?";
        PreparedStatement ps2=con.prepareStatement(q2);
        ps2.setInt(1, empid);
        ResultSet rs2=ps2.executeQuery();
        rs2.next();
        System.out.println("\n Total Customers Checked In Through "+name+" is "+rs2.getInt(1));
        con.close();
        ps.close();
        ps1.close();
        ps2.close();
    }
    static void Report() throws Exception
    {
        Connection con=DBconnection.connection();
        String q="SELECT COUNT(*) FROM rooms WHERE availability=?";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setString(1, "vacant");
        ResultSet rs=ps.executeQuery();
        rs.next();
        System.out.println("\n No.of Rooms vacant: "+rs.getInt(1));
        String q1="SELECT COUNT(*) FROM rooms WHERE availability=?";
        PreparedStatement ps1=con.prepareStatement(q1);
        ps1.setString(1, "occupied");
        ResultSet rs1=ps1.executeQuery();
        rs1.next();
        System.out.println("\n No.of Rooms occupied: "+rs1.getInt(1));
        String q2="SELECT COUNT(*) FROM rooms WHERE availability=?";
        PreparedStatement ps2=con.prepareStatement(q2);
        ps2.setString(1, "booked");
        ResultSet rs2=ps2.executeQuery();
        rs2.next();
        System.out.println("\n No.of Rooms Booked: "+rs2.getInt(1)+"\n");
        String q3="SELECT SUM(roomcharges),SUM(gstcharges) FROM revenue WHERE TRUNC(checkoutdate)=TRUNC(SYSDATE)";
        PreparedStatement ps3=con.prepareStatement(q3);
        ResultSet rs3=ps3.executeQuery();
        rs3.next();
        System.out.println("\n Total Room Charges: "+rs3.getLong(1));
        System.out.println("\n Total GST Charges:  "+rs3.getLong(2));
        System.out.println("\n Total Revenue: "+(rs3.getLong(1)+rs3.getLong(2)));
        con.close();
        ps.close();
        ps1.close();
        ps2.close();
        ps3.close();

    }
}
