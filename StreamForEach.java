package com.gla;
import java.util.*;
import java.util.stream.*;
import java.time.*;

public class StreamForEach {
    static class Movie {
        String title;
        double rating;
        int year;
        Movie(String title, double rating, int year) { this.title = title; this.rating = rating; this.year = year; }
        String getTitle() { return title; }
        double getRating() { return rating; }
        int getYear() { return year; }
    }

    static class Doctor {
        String name;
        String specialty;
        boolean weekendAvailable;
        Doctor(String name, String specialty, boolean weekendAvailable) {
            this.name = name; this.specialty = specialty; this.weekendAvailable = weekendAvailable;
        }
        String getSpecialty() { return specialty; }
        boolean isWeekendAvailable() { return weekendAvailable; }
        String getName() { return name; }
    }

    static class Claim {
        String type;
        double amount;
        Claim(String type, double amount) { this.type = type; this.amount = amount; }
        String getType() { return type; }
        double getAmount() { return amount; }
    }

    static class Member {
        String name;
        LocalDate expiryDate;
        Member(String name, LocalDate expiryDate) { this.name = name; this.expiryDate = expiryDate; }
        LocalDate getExpiryDate() { return expiryDate; }
        String getName() { return name; }
    }

    public static void main(String[] args) {
        List<Movie> movies = Arrays.asList(
                new Movie("Movie A", 8.5, 2023),
                new Movie("Movie B", 9.0, 2022),
                new Movie("Movie C", 7.5, 2024),
                new Movie("Movie D", 8.9, 2024),
                new Movie("Movie E", 9.5, 2023),
                new Movie("Movie F", 9.2, 2024)
        );
        List<String> top5Movies = movies.stream()
                .sorted(Comparator.comparing(Movie::getYear).reversed().thenComparing(Movie::getRating).reversed())
                .limit(5)
                .map(Movie::getTitle)
                .toList();
        System.out.println(top5Movies);

        List<Doctor> doctors = Arrays.asList(
                new Doctor("Dr. A", "Cardiology", true),
                new Doctor("Dr. B", "Neurology", false),
                new Doctor("Dr. C", "Orthopedics", true)
        );
        List<String> weekendDoctors = doctors.stream()
                .filter(Doctor::isWeekendAvailable)
                .sorted(Comparator.comparing(Doctor::getSpecialty))
                .map(Doctor::getName)
                .toList();
        System.out.println(weekendDoctors);

        List<Claim> claims = Arrays.asList(
                new Claim("Health", 5000),
                new Claim("Car", 2000),
                new Claim("Health", 7000),
                new Claim("Car", 3000)
        );
        Map<String, Double> avgClaimByType = claims.stream()
                .collect(Collectors.groupingBy(Claim::getType, Collectors.averagingDouble(Claim::getAmount)));
        System.out.println(avgClaimByType);

        List<Member> members = Arrays.asList(
                new Member("John", LocalDate.now().plusDays(10)),
                new Member("Jane", LocalDate.now().plusDays(40)),
                new Member("Mike", LocalDate.now().plusDays(25))
        );
        List<String> expiringSoon = members.stream()
                .filter(m -> !m.getExpiryDate().isAfter(LocalDate.now().plusDays(30)))
                .map(Member::getName)
                .toList();
        System.out.println(expiringSoon);

        List<String> customers = Arrays.asList("alice", "bob", "charlie");
        List<String> transformedNames = customers.stream()
                .map(String::toUpperCase)
                .sorted()
                .toList();
        System.out.println(transformedNames);

        List<Double> stockPrices = Arrays.asList(101.5, 102.0, 99.8);
        stockPrices.forEach(System.out::println);

        List<String> attendees = Arrays.asList("Alice", "Bob", "Charlie");
        attendees.forEach(a -> System.out.println("Welcome " + a));

        List<Integer> sensorReadings = Arrays.asList(50, 75, 90, 40);
        sensorReadings.stream()
                .filter(r -> r > 60)
                .forEach(System.out::println);

        List<String> emails = Arrays.asList("a@test.com", "b@test.com");
        emails.forEach(email -> sendEmailNotification(email));

        List<String> transactionIds = Arrays.asList("TX1001", "TX1002", "TX1003");
        transactionIds.forEach(id -> System.out.println(LocalDateTime.now() + " - Transaction: " + id));
    }

    static void sendEmailNotification(String email) {
        System.out.println("Sending email to: " + email);
    }
}

