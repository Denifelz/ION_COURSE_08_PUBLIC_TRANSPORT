package com.transport.management;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private String name;
    private List<Station> stations;
    private List<Vehicle> transports;

    public Route() {
        this.name = "";
        this.stations = null;
        this.transports = null;
    }

    public Route(String name) {
        this.name = name;
        this.stations = new ArrayList<>();
        this.transports = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public List<Vehicle> getTransports() {
        return transports;
    }
}
