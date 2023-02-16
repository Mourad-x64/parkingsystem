package com.parkit.parkingsystem.model;

import java.util.Date;

public class Ticket {
    private int id;
    private ParkingSpot parkingSpot;
    private String vehicleRegNumber;
    private double price;
    private Date inTime;
    private Date outTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingSpot getParkingSpot() {

        if(parkingSpot == null){
            return  null;
        }else{
            return parkingSpot.copy();
        }
    }





    public void setParkingSpot(ParkingSpot parkingSpot) {
        if(parkingSpot == null){
            this.parkingSpot = null;
        }else{
            this.parkingSpot = parkingSpot.copy();
        }

    }

    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getInTime() {

        if(inTime == null){
            return null;

        }else {
            return (Date) inTime.clone();
        }
    }

    public void setInTime(Date inTime) {
        this.inTime = (Date) inTime.clone();
    }

    public Date getOutTime() {
        if(outTime == null){
            return null;

        }else {
            return (Date) outTime.clone();
        }

    }

    public void setOutTime(Date outTime) {
        if(outTime != null){
            this.outTime = (Date) outTime.clone();
        }else {
            this.outTime = null;
        }

    }


}
