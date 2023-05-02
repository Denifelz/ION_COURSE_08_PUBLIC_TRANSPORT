package com.transport.management;

public class Station {
    private String name;

    public Station() {
        this.name = "";
    }

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Station station = (Station) other;

        return name.equals(station.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
