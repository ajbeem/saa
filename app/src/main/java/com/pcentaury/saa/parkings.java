package com.pcentaury.saa;

public class parkings {

//region getters
    public String getParkingName() {
        return parkingName;
    }

    public String getParkingAdress() {
        return parkingAdress;
    }

    public String getDistance() {
        return distance;
    }

    public double getParkingRating() {
        return parkingRating;
    }
//endregion
//region Setters

    public void setParkingRating(double parkingRating) {
        this.parkingRating = parkingRating;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setParkingAdress(String parkingAdress) {
        this.parkingAdress = parkingAdress;
    }
    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }
//endregion
    private String parkingName, parkingAdress, distance;
    private double parkingRating;

    public parkings(String nombreEst, String direcest, String distancia, double califPark) {
        this.parkingName = nombreEst;
        this.parkingAdress = direcest;
        this.distance = distancia;
        this.parkingRating = califPark;
    }
}
