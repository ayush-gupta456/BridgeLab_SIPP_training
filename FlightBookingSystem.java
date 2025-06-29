package com.gla;

import java.util.*;

class Flight {
    int flightId;
    String destination;
    String time;

    public Flight(int flightId, String destination, String time) {
        this.flightId = flightId;
        this.destination = destination;
        this.time = time;
    }

    public String toString() {
        return "Flight ID: " + flightId + ", Destination: " + destination + ", Time: " + time;
    }
}

class Booking {
    Flight flight;
    String passengerName;

    public Booking(Flight flight, String passengerName) {
        this.flight = flight;
        this.passengerName = passengerName;
    }

    public String toString() {
        return passengerName + " booked -> " + flight.toString();
    }
}

public class FlightBookingSystem {
    static Flight[] flights = {
        new Flight(101, "Delhi", "10:00 AM"),
        new Flight(102, "Mumbai", "11:30 AM"),
        new Flight(103, "Chennai", "01:15 PM"),
        new Flight(104, "Kolkata", "03:45 PM")
    };

    static List<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void searchFlight(String destination) {
        System.out.println("Available flights to " + destination + ":");
        for (Flight flight : flights) {
            if (flight.destination.equalsIgnoreCase(destination)) {
                System.out.println(flight);
            }
        }
    }

    public static void bookFlight(int flightId, String passengerName) {
        for (Flight flight : flights) {
            if (flight.flightId == flightId) {
                bookings.add(new Booking(flight, passengerName));
                System.out.println("Booking successful!");
                return;
            }
        }
        System.out.println("Flight ID not found.");
    }

    public static void displayBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("--- Flight Booking System ---");
            System.out.println("1. Search Flight");
            System.out.println("2. Book Flight");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter destination to search: ");
                    String dest = sc.nextLine();
                    searchFlight(dest);
                    break;
                case 2:
                    System.out.print("Enter Flight ID to book: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Passenger Name: ");
                    String name = sc.nextLine();
                    bookFlight(id, name);
                    break;
                case 3:
                    displayBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the system!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
