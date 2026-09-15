package hotel;

import java.sql.*;
import java.time.temporal.ChronoUnit;

public class bill {
    static void getbill(int cusid,int roomno) throws Exception
    {
        Connection con=DBconnection.connection();
        String q="SELECT cusname,phoneno,checkin,checkout FROM customer WHERE cusid=?";
        PreparedStatement ps=con.prepareStatement(q);
        ps.setInt(1, cusid);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String q1="SELECT roomtype,price FROM rooms WHERE roomno=?";
        PreparedStatement ps1=con.prepareStatement(q1);
        ps1.setInt(1, roomno);
        ResultSet rs1=ps1.executeQuery();
        rs1.next();
        String cusname=rs.getString(1);
        String phoneno=rs.getString(2);
        Date checkin=rs.getDate(3);
        Date checkout=rs.getDate(4);
        long no_of_days=ChronoUnit.DAYS.between(checkin.toLocalDate(), checkout.toLocalDate());
        String roomtype=rs1.getString(1);
        int price=rs1.getInt(2);
        long totalprice=no_of_days*price;
        long gst=(long)(totalprice*0.18);
        long total=totalprice+gst;
        System.out.println("\n ===================================================");
        System.out.println("\n\t\tHOTEL RESERVATION SYSTEM");
        System.out.println("\n ===================================================");
        System.out.println("\n Customer ID: "+cusid);
        System.out.println("\n Customer Name: "+cusname);
        System.out.println("\n Customer PhoneNo: "+phoneno);
        System.out.println("\n\n Room Number: "+roomno);
        System.out.println("\n RoomType: "+roomtype);
        System.out.println("\n\n Check In Date: "+checkin);
        System.out.println("\n Check Out Date: "+checkout);
        System.out.println("\n Total Days: "+no_of_days);
        System.out.println("\n\nRoom Charge/Day: "+price+"\\-");
        System.out.println("\n-----------------------------------------");
        System.out.println("\n Total Room Charges: "+totalprice+"\\-");
        System.out.println("\n GST(18%): "+gst+"\\-");
        System.out.println("\n------------------------------------------");
        System.out.println("\n Total Amount: "+total+"\\-");
        System.out.println("\n------------------------------------------");
        System.out.println("\n\n\n \t\tTHANKYOU! VISIT AGAIN.");
        System.out.println("\n =====================================================");
        String q2="INSERT INTO revenue(checkoutdate,roomcharges,gstcharges) VALUES(SYSDATE,?,?)";
        PreparedStatement ps2=con.prepareStatement(q2);
        ps2.setLong(1,totalprice);
        ps2.setLong(2, gst);
        ps2.executeUpdate();
        con.close();
        ps.close();
        ps1.close();
        ps2.close();
    }
}
