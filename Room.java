/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel_Reservation_System;

/**
 *
 * @author Ukasha Gamer
 */
public class Room {

    private String roomId;
    private String roomNo;
    private String roomType;
    private String pricePerNight;
    private String status;

    public Room(String roomId, String roomNo, String roomType, String pricePerNight, String status) {
        this.roomId = roomId;
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    // Getters
    public String getRoomId() {
        return roomId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getPricePerNight() {
        return pricePerNight;
    }

    public String getStatus() {
        return status;
    }
}
