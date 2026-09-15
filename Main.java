package hotel;

import java.sql.Date;
import java.util.Scanner;


/**
 * Main
 */
public class Main {
    public static void main(String[] args) throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("\n***************************WELCOME TO HOTEL RESERVATION SYSTEM**************************\n\n");
        System.out.println("1.RECEPTIONIST\n");
        System.out.println("2.MANAGER\n");
        System.out.println("Enter Your Role: ");
        int role=sc.nextInt();
        sc.nextLine();
        if(role==1)
        {
            System.out.println("\n**********************LOGIN********************\n");
            System.out.println("Enter username: ");
            String username=sc.nextLine();
            System.out.println("Enter password: ");
            String password=sc.nextLine();
            if(Login.CheckReceptionist(username, password))
            {
                System.out.println("\n\n************LOGIN SUCCESSFUL***************\n\n");
                boolean q=true;
                while(q)
                {
                    System.out.println("1.VIEW ROOMS\n");
                    System.out.println("2.BOOK ROOMS\n");
                    System.out.println("3.CHECK IN\n");
                    System.out.println("4.CHECK OUT\n");
                    System.out.println("5.CANCEL BOOKING\n");
                    System.out.println("6.LOGOUT\n");
                    System.out.println("Enter your choice: ");
                    int ch=sc.nextInt();
                    sc.nextLine();
                    int roomno=0;
                    Date fromdate=Date.valueOf("2026-07-20"),todate=Date.valueOf("2026-07-20");
                    switch (ch) {
                        case 1:
                            System.out.println("\t\t\tTHE ROOMS IN THE HOTEL ARE:");
                            booking.ViewAvailableRooms();
                            break;
                        case 2:
                            boolean t=true,p=true;
                            while(t)
                            {
                                System.out.println("Enter the room no that you need to book: ");
                                roomno=sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter the date that you want to CHECKIN(yyyy-mm-dd):");
                                String from=sc.nextLine();
                                fromdate=Date.valueOf(from);
                                System.out.println("Enter the date that you want to CHECKOUT(yyyy-mm-dd):");
                                String to=sc.nextLine();
                                todate=Date.valueOf(to);
                                if(todate.compareTo(fromdate)<=0)
                                {
                                    System.out.println("\n*********PLEASE ENTER CORRECT DATES**************");
                                    p=false;
                                    break;
                                }
                                String availability=booking.CheckAvailability(roomno,fromdate,todate);
                                if(availability.equals("OCCUPIED"))
                                {
                                    System.out.println("\nTHE ROOM YOU HAVE SELECTED IS ALREADY OCCUPIED \n **************PLEASE SELECT ANOTHER ROOM***************** ");
                                    System.out.println("\n1.DO YOU WANT TO GO TO MENU PAGE\n 2. DO YOU WANT TO BOOK ANY OTHER AVAILABLE ROOM:");
                                    int opinion=sc.nextInt();
                                    if(opinion==1)
                                    {
                                        t=false;
                                        p=false;
                                    }
                                    else
                                    {
                                        t=true;
                                    }
                                }
                                else if(availability.equals("BOOKED"))
                                {
                                    System.out.println("\nTHE ROOM YOU HAVE SELECTED IS ALREADY BOOKED\n*********************PLEASE SELECT ANOTHER ROOM******************");
                                    System.out.println("\n1.DO YOU WANT TO GO TO MENU PAGE\n2.DO YOU WANT TO BOOK ANY OTHER AVAILABLE ROOM:");
                                    int opinion=sc.nextInt();
                                    if(opinion==1)
                                    {
                                        t=false;
                                        p=false;
                                    }
                                    else
                                    {
                                        t=true;
                                    }
                                }
                                else 
                                {
                                    System.out.println("\n****************THE ROOM IS AVAILABLE*****************");
                                    t=false;
                                }
                            }
                            if(p)
                            {
                                System.out.println("\nEnter your EmployeeId:");
                                int empId=sc.nextInt();
                                sc.nextLine();
                                System.out.println("\n Enter Customer Id: ");
                                int cusid=sc.nextInt();
                                sc.nextLine();
                                System.out.println("\nEnter customer name: ");
                                String cusName=sc.nextLine();
                                System.out.println("\nEnter customer phone number: ");
                                String phoneno=sc.nextLine();
                                int c1=Customer.CustomerBookingEntry(cusid,empId, cusName, phoneno,roomno,fromdate,todate);
                                int c2=BookingData.BookingEntry(cusid,roomno,fromdate,todate);
                                int c3=booking.BookRoom(roomno);
                                if(c1>0 && c2>0 && c3>0)
                                {
                                    System.out.println("\n******************THE ROOM BOOKED SUCCESSFULLY********************************");
                                }
                                break;
                            }
                            else
                            {
                                break;
                            }
                        case 3:
                            System.out.println("\n Enter The Room No: ");
                            int room=sc.nextInt();
                            System.out.println("\nEnter Customer Id:");
                            int cus=sc.nextInt();
                            sc.nextLine();
                            if(CheckInandCheckOut.CHECKcheckin(room,cus))
                            {
                                int c=CheckInandCheckOut.RoomCheckin(room);
                                if(c>0)
                                {
                                    System.out.println("\n*********************CHECKIN WAS SUCCESSFUL***********************\nTAKE THE KEYS FROM RECEPTIONIST\n");
                                }
                            }
                            else
                            {
                                System.out.println("\nSORRY YOUR BOOKING IS NOT AVAILABLE\nENTER CORRECT ROOMNO AND CUSTOMER ID\n");
                            }
                            break;
                        case 4:
                            System.out.println("\n Enter The Room No: ");
                            int Room=sc.nextInt();
                            System.out.println("\nEnter Customer Id:");
                            int Cus=sc.nextInt();
                            sc.nextLine();
                            if(CheckInandCheckOut.CHECKcheckout(Room,Cus))
                            {
                                int c=CheckInandCheckOut.RoomCheckout(Room);
                                if(c>0)
                                {
                                    System.out.println("\n*********************CHECKOUT WAS SUCCESSFUL***********************\nHANDOVER THE KEYS TO RECEPTIONIST\n");
                                    System.out.println("************HERE IS YOUR BILL**************\n");
                                    bill.getbill(Cus, Room);
                                }
                                else
                                {
                                    System.out.println("\n ****SOME PROBLEM OCCURED******\n");
                                }
                            }
                            else
                            {
                                System.out.println("\nSORRY YOUR BOOKING IS NOT AVAILABLE\nENTER CORRECT ROOMNO AND CUSTOMER ID\n");
                            }
                            break;
                        case 5:
                            System.out.println("\n Enter The Room No: ");
                            int ROOM=sc.nextInt();
                            System.out.println("\n Enter customer Id: ");
                            int CUSID=sc.nextInt();
                            sc.nextLine();
                            if(Cancel.CHECKcancel(ROOM, CUSID))
                            {
                                int c=Cancel.cancelroom(CUSID);
                                if(c>0)
                                {
                                    System.out.println("\n*******************************ROOM CANCELLED SUCCESSFULLY*************************\n");
                                }
                                else
                                {
                                    System.out.println("\n ******SOME PROBLEM OCCURED**********\n");
                                }
                            }
                            else
                            {
                                System.out.println("\nSORRY YOUR BOOKING IS NOT AVAILABLE\nENTER CORRECT ROOMNO AND CUSTOMER ID\n");
                            }
                            break;
                        case 6:
                            System.out.println("\n***************LOGGING OUT***************\n");
                            q=false;
                            break;
                        default:
                            System.out.println("\nPLEASE CHOOSE THE CORRECT OPTION\n");
                            break;
                    }
                }
            }
            else
            {
                System.out.println("\n******************PLEASE ENTER CORRECT USERNAME AND PASSWORD************\n");
            }
        }
        else if(role==2)
        {
            System.out.println("\n**********************LOGIN********************\n");
            System.out.println("Enter username: ");
            String username=sc.nextLine();
            System.out.println("Enter password: ");
            String password=sc.nextLine();
            if(Login.CheckManager(username,password))
            {
                System.out.println("\n\n************LOGIN SUCCESSFUL***************\n\n");
                boolean q=true;
                while(q)
                {
                    System.out.println("1.ADD ROOM\n");
                    System.out.println("2.REMOVE ROOM\n");
                    System.out.println("3.UPDATE ROOM PRICE\n");
                    System.out.println("4.ADD EMPLOYEE DETAILS\n");
                    System.out.println("5.VIEW EMPLOYEE CHECK IN COUNT\n");
                    System.out.println("6.VIEW BOOKING DETAILS\n");
                    System.out.println("7.VIEW CUSTOMER DETAILS\n ");
                    System.out.println("8.REPORT\n");
                    System.out.println("9.LOGOUT\n");
                    System.out.println("\nEnter Your Choice: ");
                    int ch=sc.nextInt();
                    sc.nextLine();
                    switch (ch) {
                        case 1:
                            System.out.println("\nNo.of Rooms that you are going to add:  ");
                            int a=sc.nextInt();
                            for(int i=0;i<a;i++)
                            {
                                System.out.println("\nEnter Room No: ");
                                int roomno=sc.nextInt();
                                sc.nextLine();
                                System.out.println("\n Enter Room Type: ");
                                String roomtype=sc.nextLine();
                                System.out.println("\n Enter Price: ");
                                int price=sc.nextInt();
                                sc.nextLine();
                                int c=ManagerWorks.AddRoom(roomno, roomtype, price);
                                if(c>0)
                                {
                                    System.out.println("\n*******THE ROOM DETAILS ADDED SUCCESSFULLY*******");
                                }
                            }
                            break;
                        case 2:
                            System.out.println("\nEnter the RoomNo that you want to remove: ");
                            int room=sc.nextInt();
                            sc.nextLine();
                            int c=ManagerWorks.DeleteRoom(room);
                            if(c>0)
                            {
                                System.out.println("\n********THE ROOM DETAILS DELETED SUCCESSFULLY********");
                            }
                            break;
                        case 3:
                            System.out.println("\nEnter the RoomNo that you want to update the price: ");
                            int RoomNo=sc.nextInt();
                            sc.nextLine();
                            System.out.println("\nEnter the updated price of the room: ");
                            int price=sc.nextInt();
                            sc.nextLine();
                            int co=ManagerWorks.UpdatePrice(RoomNo, price);
                            if(co>0)
                            {
                                System.out.println("\n**********THE ROOM PRICE WAS UPDATED SUCCESSFULLY***************");
                            }
                            break;
                        case 4:
                            System.out.println("\nEnter Employee Id: ");
                            int empid=sc.nextInt();
                            sc.nextLine();
                            System.out.println("\n Enter Employee Name: ");
                            String empname=sc.nextLine();
                            System.out.println("\n Enter Employee User Name: ");
                            String user=sc.nextLine();
                            System.out.println("\n Enter Password: ");
                            String pass=sc.nextLine();
                            int cou=ManagerWorks.EmployeeEntry(empid, empname, user, pass);
                            if(cou>0)
                            {
                                System.out.println("\n********EMPLOYEE DETAILS ARE ENTERED SUCCESSFULLY****************");
                            }
                            break;
                        case 5:
                            System.out.println("Enter EmployeeId: ");
                            int empId=sc.nextInt();
                            sc.nextLine();
                            ManagerWorks.EmployeeCheckInCount(empId);
                            break;
                        case 6:
                            System.out.println("\n********BOOKING DETAILS ARE**********\n");
                            ManagerWorks.BookingDetails();
                            break;
                        case 7:
                            System.out.println("\n*************CUSTOMER DETAILS ARE***********\n");
                            ManagerWorks.CustomerDetails();
                            break;
                        case 8:
                            System.out.println("\n**************TODAY'S REPORT*****************\n");
                            ManagerWorks.Report();
                            break;
                        case 9:
                            System.out.println("**********************LOGGING OUT********************\n");
                            q=false;
                            break;
                        default:
                            System.out.println("\n*****ENTER CORRECT OPTION********\n");
                            break;
                    }

                }
            }
            else
            {
                System.out.println("\n**************ENTER CORRECT USERNAME AND PASSWORD***********************\n");
            }
        }
        sc.close();
    }
}