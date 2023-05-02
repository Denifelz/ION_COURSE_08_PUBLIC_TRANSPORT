package com.transport.management;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;
import java.util.List;

public class CitiesProcessor {
    public static City readNewCity() {
        System.out.println("City:");

        System.out.print("- name: ");
        String name = new Scanner(System.in).next();
        System.out.print("- population: ");
        int population = new Scanner(System.in).nextInt();

        return new City(name, population);
    }

    public static void readModCity(City city) {
        System.out.println("City:");

        System.out.print("- name: ");
        city.setName(new Scanner(System.in).next());
        System.out.print("- population: ");
        city.setPopulation(new Scanner(System.in).nextInt());
    }

    public static City selectCity(List<City> cities) {
        return selectCity(cities, false);
    }

    public static City selectCity(List<City> cities, boolean allowNull) {
        if (cities.size() == 0) {
            System.out.println("Empty Cities List!");
        } else {
            System.out.println("--------------------------------------------------------");
            cities.forEach(city -> System.out.println((cities.indexOf(city) + 1) + ". " + city.getName()));
            if (allowNull) System.out.println("99. RETURN");

            System.out.print("COMMAND: ");
            byte option = new Scanner(System.in).nextByte();

            while (option != 99 || !allowNull) {
                if (option > 0 && option <= cities.size()) {
                    return cities.get(option - 1);
                } else {
                    System.out.println("Wrong Option!");
                }

                option = new Scanner(System.in).nextByte();
            }
        }

        return null;
    }

    public static JSONObject toJSON(City city) {
        JSONObject cityJSON = new JSONObject();
        cityJSON.put("name", city.getName());
        cityJSON.put("population", city.getPopulation());

        JSONArray stationsJSON = new JSONArray();
        city.getStations().forEach(station -> stationsJSON.add(StationsProcessor.toJSON(station)));
        cityJSON.put("stations", stationsJSON);

        JSONArray roadsJSON = new JSONArray();
        city.getRoads().forEach(road -> roadsJSON.add(RoadsProcessor.toJSON(road)));
        cityJSON.put("roads", roadsJSON);

        JSONArray routesJSON = new JSONArray();
        city.getRoutes().forEach(route -> routesJSON.add(RoutesProcessor.toJSON(route)));
        cityJSON.put("routes", routesJSON);

        //JSONArray publicTransportsJSON = new JSONArray();
        //city.getPublicTransports().forEach(station -> {});
        //cityJSON.put("publicTransports", publicTransportsJSON);

        return cityJSON;
    }

    public static City fromJSON(JSONObject cityJSON) {
        City city = new City();
        city.setName((String) cityJSON.get("name"));
        city.setPopulation(((Long) cityJSON.get("population")).intValue());

        JSONArray stationsJSON = (JSONArray) cityJSON.get("stations");
        stationsJSON.forEach(station -> city.getStations().add(StationsProcessor.fromJSON((JSONObject) station)));

        JSONArray roadsJSON = (JSONArray) cityJSON.get("roads");
        roadsJSON.forEach(road -> city.getRoads().add(RoadsProcessor.fromJSON((JSONObject) road, city.getStations())));

        JSONArray routesJSON = (JSONArray) cityJSON.get("routes");
        routesJSON.forEach(route -> city.getRoutes().add(RoutesProcessor.fromJSON((JSONObject) route, city.getStations())));

        //JSONArray publicTransportsJSON = (JSONArray) cityJSON.get("publicTransports");
        //publicTransportsJSON.forEach(publicTransport -> { /*city.getStations().add(StationProcessor.fromJSON((JSONObject) station));*/ });

        return city;
    }
}
