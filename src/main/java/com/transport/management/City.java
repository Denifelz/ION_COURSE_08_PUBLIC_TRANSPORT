package com.transport.management;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private int population;
    private final List<Station> stations;
    private final List<Road> roads;
    private final List<Route> routes;
    private final List<Vehicle> publicTransports;

    public City() {
        this.name = "";
        this.population = 0;
        this.stations = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.publicTransports = new ArrayList<>();
    }

    public City(String name, int population) {
        this.name = name;
        this.population = population;
        this.stations = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.publicTransports = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }


    public List<Station> getStations() {
        return stations;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Vehicle> getPublicTransports() {
        return publicTransports;
    }
}
