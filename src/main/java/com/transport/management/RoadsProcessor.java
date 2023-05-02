package com.transport.management;

import org.json.simple.JSONObject;

import java.util.List;
import java.util.Scanner;

public class RoadsProcessor {
    public static Road readNewRoad(List<Station> stations, Station fromStation) {
        System.out.println("Road:");

        System.out.println("- from Station: " + fromStation.getName());
        System.out.println("- to Station: ");
        Station toStation = StationsProcessor.selectStation(stations, fromStation);
        System.out.print("- trolleybus line (yes/no): ");
        boolean hasTrolleybusLine = new Scanner(System.in).next().equals("yes") ? true : false;
        System.out.print("- distance (km): ");
        float distance = new Scanner(System.in).nextFloat();

        return new Road(fromStation, toStation, hasTrolleybusLine, distance);
    }

    public static void readModRoad(Road road) {
        System.out.println("Road:");

        System.out.print("- trolleybus line (yes/no): ");
        road.setHasTrolleybusLine(new Scanner(System.in).next().equals("yes") ? true : false);
        System.out.print("- distance (km): ");
        road.setDistance(new Scanner(System.in).nextFloat());
    }

    public static Road selectRoad(List<Road> roads) {
        return selectRoad(roads, false);
    }

    public static Road selectRoad(List<Road> roads, boolean allowNull) {
        if (roads.size() == 0) {
            System.out.println("Empty Roads List!");
        } else {
            System.out.println("--------------------------------------------------------");
            roads.forEach(road -> System.out.println((roads.indexOf(road) + 1) + ". to " + road.getToStation().getName()));
            if (allowNull) System.out.println("99. RETURN");

            System.out.print("COMMAND: ");
            byte option = new Scanner(System.in).nextByte();

            while (option != 99 || !allowNull) {
                if (option > 0 && option <= roads.size()) {
                    return roads.get(option - 1);
                } else {
                    System.out.println("Wrong Option!");
                }

                option = new Scanner(System.in).nextByte();
            }
        }

        return null;
    }

    public static JSONObject toJSON(Road road) {
        JSONObject roadJSON = new JSONObject();
        roadJSON.put("fromStation", road.getFromStation().getName());
        roadJSON.put("toStation", road.getToStation().getName());
        roadJSON.put("distance", road.getDistance());
        roadJSON.put("hasTrolleybusLine", road.getHasTrolleybusLine());

        return roadJSON;
    }

    public static Road fromJSON(JSONObject roadJSON, List<Station> stations) {
        Road road = new Road();
        road.setFromStation(StationsProcessor.findByName(stations, (String) roadJSON.get("fromStation")));
        road.setToStation(StationsProcessor.findByName(stations, (String) roadJSON.get("toStation")));
        road.setDistance(((Double) roadJSON.get("distance")).floatValue());
        road.setHasTrolleybusLine((boolean) roadJSON.get("hasTrolleybusLine"));

        return road;
    }
}
