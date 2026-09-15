package hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class Login{
    static boolean CheckReceptionist(String username,String password) throws Exception
    {
        Connection con=DBconnection.connection();
        String query="SELECT username,password FROM receptionistdetails";
        PreparedStatement st=con.prepareStatement(query);
        ResultSet rs=st.executeQuery();
        while(rs.next())
        {
            if(rs.getString(1).equals(username) && rs.getString(2).equals(password))
            {
                con.close();
                st.close();
                return true;
            }
        }
        con.close();
        st.close();
        return false;
    }
    static boolean CheckManager(String username,String password) throws Exception
    {
        Connection con=DBconnection.connection();
        String query="SELECT username,password FROM ManagerDetails";
        PreparedStatement st=con.prepareStatement(query);
        ResultSet rs=st.executeQuery();
        while(rs.next())
        {
            if(rs.getString(1).equals(username) && rs.getString(2).equals(password))
            {
                con.close();
                st.close();
                return true;
            }
        }
        con.close();
        st.close();
        return false;
    }
}