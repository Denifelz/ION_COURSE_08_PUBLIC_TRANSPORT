package com.transport.management;

import org.json.simple.JSONObject;

import java.util.List;
import java.util.Scanner;

public class StationsProcessor {
    public static Station readNewStation() {
        System.out.println("Station:");

        System.out.print("- name: ");
        String name = new Scanner(System.in).nextLine();

        return new Station(name);
    }

    public static void readModStation(Station station) {
        System.out.println("Station:");

        System.out.print("- name: ");
        station.setName(new Scanner(System.in).nextLine());
    }

    public static Station findByName(List<Station> stations, String name) {
        return stations.stream().filter(station -> station.getName().equals(name)).findFirst().orElse(null);
    }

    public static Station selectStation(List<Station> stations) {
        return selectStation(stations, null, true);
    }

    public static Station selectStation(List<Station> stations, boolean allowNull) {
        return selectStation(stations, null, allowNull);
    }

    public static Station selectStation(List<Station> stations, Station excludedStation) {
        return selectStation(stations, excludedStation, false);
    }

    public static Station selectStation(List<Station> stations, Station excludedStation, boolean allowNull) {
        if (stations.size() == 0) {
            System.out.println("Empty Stations List!");
        } else {
            System.out.println("--------------------------------------------------------");
            if (excludedStation == null) {
                stations.forEach(station -> System.out.println((stations.indexOf(station) + 1) + ". " + station.getName()));
            } else {
                stations.forEach(station -> {
                    if (!station.equals(excludedStation)) {
                        System.out.println((stations.indexOf(station) + 1) + ". " + station.getName());
                    }
                });
            }
            if (allowNull) System.out.println("99. RETURN");

            System.out.print("COMMAND: ");
            byte option = new Scanner(System.in).nextByte();

            while (option != 99 || !allowNull) {
                if (option > 0 && option <= stations.size()) {
                    return stations.get(option - 1);
                } else {
                    System.out.println("Wrong Option!");
                }

                option = new Scanner(System.in).nextByte();
            }
        }

        return null;
    }

    public static JSONObject toJSON(Station station) {
        JSONObject stationJSON = new JSONObject();
        stationJSON.put("name", station.getName());

        return stationJSON;
    }

    public static Station fromJSON(JSONObject stationJSON) {
        Station station = new Station();
        station.setName((String) stationJSON.get("name"));

        return station;
    }
}
