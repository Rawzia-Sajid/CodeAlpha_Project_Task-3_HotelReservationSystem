/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel_Reservation_System;

/**
 *
 * @author Ukasha Gamer
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static Connection con = null;

    public static Connection getConnection() {
        try {
             // Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelReservationSystem ", "root", "Rs882004");
        } catch (Exception e) {
            e.printStackTrace(); // Prints the stack trace for debuggin ;
        }
        return con;
    }
}
