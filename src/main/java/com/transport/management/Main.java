package com.transport.management;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final List<City> cities = new ArrayList<>();
    private static City currentCity = null;
    private static Route currentRoute = null;
    private static Station currentStation = null;
    private static Road currentRoad = null;

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        byte option = 99;

        do {
            switch (option) {
                case 1 -> currentCity = CitiesProcessor.selectCity(cities, true);
                case 2 -> {
                    currentCity = CitiesProcessor.readNewCity();
                    cities.add(currentCity);
                }
                case 8 -> exportData();
                case 9 -> importData();
                case 99 -> {
                }
                default -> System.out.println("Wrong Option!");
            }

            if (currentCity != null) {
                cityMenu();
                currentCity = null;
            }

            System.out.println("--------------------------------------------------------");
            System.out.println("1. Select City / 2. New City");
            System.out.println("----------------------------");
            System.out.println("8. Export Data / 9. Import Data");
            System.out.println("-------------------------------");
            System.out.println("99. EXIT");

            System.out.print("COMMAND: ");

            option = new Scanner(System.in).nextByte();
        } while (option != 99);
    }

    public static void cityMenu() {
        byte option = 99;

        do {
            switch (option) {
                case 1 -> currentRoute = RoutesProcessor.selectRoute(currentCity.getRoutes(), true);
                case 2 -> {
                    currentRoute = RoutesProcessor.readNewRoute();
                    currentCity.getRoutes().add(currentRoute);
                }
                case 3 -> currentStation = StationsProcessor.selectStation(currentCity.getStations(), true);
                case 4 -> {
                    currentStation = StationsProcessor.readNewStation();
                    currentCity.getStations().add(currentStation);
                }
                case 8 -> CitiesProcessor.readModCity(currentCity);
                case 9 -> {
                    cities.remove(currentCity);
                    currentCity = null;
                    return;
                }
                case 99 -> {
                }
                default -> System.out.println("Wrong Option!");
            }

            if (currentRoute != null) {
                RouteMenu();
                currentRoute = null;
            }
            if (currentStation != null) {
                stationMenu();
                currentStation = null;
            }

            printCurrentInfo();
            System.out.println("1. Select Route / 2. New Route");
            System.out.println("3. Select Station / 4. New Station");
            System.out.println("----------------------------------");
            System.out.println("8. Modify City / 9. Remove City");
            System.out.println("-------------------------------");
            System.out.println("99. RETURN");

            System.out.print("COMMAND: ");

            option = new Scanner(System.in).nextByte();
        } while (option != 99);
    }

    public static void RouteMenu() {
        byte option = 99;

        do {
            switch (option) {
                case 1 -> currentStation = StationsProcessor.selectStation(currentRoute.getStations(), true);
                case 2 -> {
                    currentRoute.getStations().add(StationsProcessor.selectStation(currentCity.getStations()));
                }
                case 8 -> RoutesProcessor.readModRoute(currentRoute);
                case 9 -> {
                    currentCity.getRoutes().remove(currentRoute);
                    currentRoute = null;
                    return;
                }
                case 99 -> {
                }
                default -> System.out.println("Wrong Option!");
            }

            if (currentStation != null) {
                stationMenu();
                currentStation = null;
            }

            printCurrentInfo();
            System.out.println("1. List Stations / 2. Add Station");
            System.out.println("----------------------------");
            System.out.println("8. Modify Route / 9. Remove Route");
            System.out.println("-------------------------------------");
            System.out.println("99. RETURN");

            System.out.print("COMMAND: ");

            option = new Scanner(System.in).nextByte();
        } while (option != 99);
    }

    public static void stationMenu() {
        byte option = 99;

        do {
            switch (option) {
                case 1 -> currentRoad = RoadsProcessor.selectRoad(currentCity.getRoads(), true);
                case 2 -> {
                    currentRoad = RoadsProcessor.readNewRoad(currentCity.getStations(), currentStation);
                    currentCity.getRoads().add(currentRoad);
                }
                case 8 -> StationsProcessor.readModStation(currentStation);
                case 9 -> {
                    currentCity.getStations().remove(currentStation);
                    currentStation = null;
                    return;
                }
                case 99 -> {
                }
                default -> System.out.println("Wrong Option!");
            }

            if (currentRoad != null) {
                //roadMenu();
                currentRoad = null;
            }

            printCurrentInfo();
            System.out.println("1. Select Road / 2. New Road");
            System.out.println("----------------------------");
            System.out.println("8. Modify Station / 9. Remove Station");
            System.out.println("-------------------------------------");
            System.out.println("99. RETURN");

            System.out.print("COMMAND: ");

            option = new Scanner(System.in).nextByte();
        } while (option != 99);
    }

    public static void exportData() {
        JSONObject dataJSON = new JSONObject();

        JSONArray citiesJSON = new JSONArray();
        cities.forEach(city -> citiesJSON.add(CitiesProcessor.toJSON(city)));
        dataJSON.put("cities", citiesJSON);

        try (FileWriter jsonFile = new FileWriter("transport.management.json")) {
            jsonFile.write(dataJSON.toJSONString());
            jsonFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void importData() {
        try (FileReader jsonFile = new FileReader("transport.management.json")) {
            JSONObject dataJSON = (JSONObject) new JSONParser().parse(jsonFile);

            JSONArray citiesJSON = (JSONArray) dataJSON.get("cities");

            citiesJSON.forEach(city -> cities.add(CitiesProcessor.fromJSON((JSONObject) city)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void printCurrentInfo() {
        System.out.println("--------------------------------------------------------");
        if (currentCity != null) {
            System.out.println("+++++ Current City: " + currentCity.getName() + "+++++");
        }
        if (currentRoute != null) {
            System.out.println("+++++ Current Route: " + currentRoute.getName() + "+++++");
        }
        if (currentStation != null) {
            System.out.println("+++++ Current Station: " + currentStation.getName() + "+++++");
        }
        if (currentRoad != null) {
            System.out.println("+++++ Current Road: " + currentRoad.getFromStation().getName() + "->" + currentRoad.getToStation().getName() + "+++++");
        }
        System.out.println("--------------------------------------------------------");
    }
}
