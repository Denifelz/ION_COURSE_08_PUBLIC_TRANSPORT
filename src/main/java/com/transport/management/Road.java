package com.transport.management;

public class Road {
    private Station fromStation;
    private Station toStation;
    private Boolean hasTrolleybusLine;
    private float distance;

    public Road() {
        this.fromStation = null;
        this.toStation = null;
        this.hasTrolleybusLine = false;
        this.distance = 0.0f;
    }

    public Road(Station fromStation, Station toStation, Boolean hasTrolleybusLine, float distance) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.hasTrolleybusLine = hasTrolleybusLine;
        this.distance = distance;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public void setFromStation(Station fromStation) {
        this.fromStation = fromStation;
    }

    public Station getToStation() {
        return toStation;
    }

    public void setToStation(Station toStation) {
        this.toStation = toStation;
    }

    public Boolean getHasTrolleybusLine() {
        return hasTrolleybusLine;
    }

    public void setHasTrolleybusLine(Boolean hasTrolleybusLine) {
        this.hasTrolleybusLine = hasTrolleybusLine;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
