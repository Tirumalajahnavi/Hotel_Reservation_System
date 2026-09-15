# Hotel Reservation System

A console-based Hotel Reservation System developed using Java, Oracle Database, and JDBC. The application helps receptionists manage room reservations and customer check-in/check-out, while managers can manage rooms, employees, bookings, customers, and reports.

## Features

### Receptionist
- Receptionist login
- View room details and availability
- Book rooms
- Store customer and booking details
- Check-in customers
- Check-out customers
- Generate bills with 18% GST
- Cancel bookings
- Logout

### Manager
- Manager login
- Add and remove rooms
- Update room prices
- Add receptionist details
- View employee check-in count
- View booking details
- View customer details
- View room and revenue reports
- Logout

## Technologies Used

- Java
- Oracle Database 11g XE
- JDBC
- VS Code

## Project Structure

Hotel Reservation System/
└── hotel/
    ├── Main.java
    ├── DBconnection.java
    ├── Login.java
    ├── CheckInandCheckOut.java
    ├── booking.java
    ├── BookingData.java
    ├── Customer.java
    ├── Cancel.java
    ├── bill.java
    └── ManagerWorks.java

## Database Tables

The project uses the following Oracle tables:

- rooms
- receptionistdetails
- ManagerDetails
- roombookings
- customer
- cancelbooking
- revenue

## Requirements

- Java JDK
- Oracle Database 11g XE or compatible Oracle database
- Oracle JDBC driver, such as `ojdbc11.jar`
- VS Code or any Java IDE

## Database Configuration

Update the database details in `DBconnection.java`:

```java
String url = "jdbc:oracle:thin:@localhost:1521:XE";
String username = "your_username";
String password = "your_password";

How to Run:

Install Java and Oracle Database.

Start the Oracle database service.

Create the required database tables.

Add the Oracle JDBC driver to the project.

Update the database credentials in DBconnection.java.

Compile the Java files.

Run hotel.Main.


Future Enhancements:

Add a graphical interface using Java Swing or JavaFX.

Improve input validation.

Add secure password hashing.

Use database transactions.

Add booking modification functionality.
