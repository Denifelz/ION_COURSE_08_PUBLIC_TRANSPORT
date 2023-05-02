package com.transport.management;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Scanner;

public class RoutesProcessor {
    public static Route readNewRoute() {
        System.out.println("Route:");

        System.out.print("- name: ");
        String name = new Scanner(System.in).next();

        return new Route(name);
    }

    public static void readModRoute(Route route) {
        System.out.println("Route:");

        System.out.print("- name: ");
        route.setName(new Scanner(System.in).next());
    }

    public static Route selectRoute(List<Route> routes) {
        return selectRoute(routes, false);
    }

    public static Route selectRoute(List<Route> routes, boolean allowNull) {
        if (routes.size() == 0) {
            System.out.println("Empty Routes List!");
        } else {
            System.out.println("--------------------------------------------------------");
            routes.forEach(route -> System.out.println((routes.indexOf(route) + 1) + ". to " + route.getName()));
            if (allowNull) System.out.println("99. RETURN");

            System.out.print("COMMAND: ");
            byte option = new Scanner(System.in).nextByte();

            while (option != 99 || !allowNull) {
                if (option > 0 && option <= routes.size()) {
                    return routes.get(option - 1);
                } else {
                    System.out.println("Wrong Option!");
                }

                option = new Scanner(System.in).nextByte();
            }
        }

        return null;
    }

    public static JSONObject toJSON(Route route) {
        JSONObject routeJSON = new JSONObject();
        routeJSON.put("name", route.getName());

        JSONArray stationsJSON = new JSONArray();
        route.getStations().forEach(station -> stationsJSON.add(station.getName()));
        routeJSON.put("stations", stationsJSON);

        return routeJSON;
    }

    public static Route fromJSON(JSONObject routeJSON, List<Station> stations) {
        Route route = new Route();
        route.setName(((String) routeJSON.get("name")));

        JSONArray stationsJSON = (JSONArray) routeJSON.get("stations");
        stationsJSON.forEach(stationJSON -> route.getStations().add((StationsProcessor.findByName(stations, (String) stationJSON))));

        return route;
    }
}
