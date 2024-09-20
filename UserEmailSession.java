/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel_Reservation_System;

 
public class UserEmailSession {

    private static UserEmailSession instance;
    private String userEmail;
    private boolean isLoggedIn;
    private String firstName;  
    private String lastName;  

    private UserEmailSession() {
    }

    public static UserEmailSession getInstance() {
        if (instance == null) {
            instance = new UserEmailSession();
        }
        return instance;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoginStatus(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
