package com.gla;
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

// ================= Interfaces =================
interface TransportService {
    String getName();
    List<ScheduleEntry> getLiveSchedule();

    default void printServiceDetails() {
        System.out.println(getName() + " services: " + getLiveSchedule().size());
    }

    default List<ScheduleEntry> filterAndSort(Predicate<ScheduleEntry> filter, Comparator<ScheduleEntry> sort) {
        return getLiveSchedule().stream().filter(filter).sorted(sort).collect(Collectors.toList());
    }
}

interface GeoUtils {
    static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371.0;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2)*Math.sin(dLat/2) + 
                   Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2))*Math.sin(dLon/2)*Math.sin(dLon/2);
        double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R*c;
    }
}

@FunctionalInterface
interface FareCalculator {
    double calculateFare(Trip trip);
}

interface EmergencyService {}

// ================= Models =================
class ScheduleEntry {
    public final String serviceName;
    public final String routeId;
    public final String origin;
    public final String destination;
    public final LocalTime departureTime;
    public final double fare;
    public final boolean peak;

    public ScheduleEntry(String serviceName, String routeId, String origin, String destination,
                         LocalTime departureTime, double fare, boolean peak) {
        this.serviceName = serviceName;
        this.routeId = routeId;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.fare = fare;
        this.peak = peak;
    }

    @Override
    public String toString() {
        return serviceName + " " + routeId + " " + origin + "->" + destination +
               " " + departureTime + " fare=" + fare + (peak ? " peak" : "");
    }
}

class Trip {
    public final String serviceName;
    public final String routeId;
    public final int passengers;
    public final double fareCollected;
    public final boolean peak;
    public final LocalDateTime time;

    public Trip(String serviceName, String routeId, int passengers, double fareCollected, boolean peak, LocalDateTime time) {
        this.serviceName = serviceName;
        this.routeId = routeId;
        this.passengers = passengers;
        this.fareCollected = fareCollected;
        this.peak = peak;
        this.time = time;
    }
}

class Passenger {
    public final String id;
    public final String name;
    public final String routeId;

    public Passenger(String id, String name, String routeId) {
        this.id = id;
        this.name = name;
        this.routeId = routeId;
    }
}

class FareUtils {
    public static double basicFare(Trip t) { return t.fareCollected; }
    public static double surgeFare(Trip t) { return t.peak ? t.fareCollected * 1.15 : t.fareCollected; }
}

// ================= Services =================
class BusService implements TransportService {
    private final List<ScheduleEntry> schedules;
    public BusService() {
        schedules = Arrays.asList(
            new ScheduleEntry("Bus","B12","Central","TechPark", LocalTime.of(8,10), 1.5, true),
            new ScheduleEntry("Bus","B12","Central","TechPark", LocalTime.of(8,40), 1.5, true),
            new ScheduleEntry("Bus","B22","North","Central", LocalTime.of(9,5), 1.2, false)
        );
    }
    public String getName() { return "Bus"; }
    public List<ScheduleEntry> getLiveSchedule() { return schedules; }
}

class MetroService implements TransportService {
    private final List<ScheduleEntry> schedules;
    public MetroService() {
        schedules = Arrays.asList(
            new ScheduleEntry("Metro","M1","Central","Airport", LocalTime.of(8,5), 2.2, true),
            new ScheduleEntry("Metro","M1","Central","Airport", LocalTime.of(8,35), 2.2, true),
            new ScheduleEntry("Metro","M2","OldTown","Central", LocalTime.of(9,0), 1.8, false)
        );
    }
    public String getName() { return "Metro"; }
    public List<ScheduleEntry> getLiveSchedule() { return schedules; }
}

class TaxiService implements TransportService {
    private final List<ScheduleEntry> schedules;
    public TaxiService() {
        schedules = Arrays.asList(
            new ScheduleEntry("Taxi","T-ON","OnDemand","Any", LocalTime.now(), 4.5, true),
            new ScheduleEntry("Taxi","T-ON","OnDemand","Any", LocalTime.now().plusMinutes(10), 3.9, false)
        );
    }
    public String getName() { return "Taxi"; }
    public List<ScheduleEntry> getLiveSchedule() { return schedules; }
}

class AmbulanceService implements TransportService, EmergencyService {
    private final List<ScheduleEntry> schedules;
    public AmbulanceService() {
        schedules = Collections.singletonList(
            new ScheduleEntry("Ambulance","EMS","Any","Hospital", LocalTime.now(), 0.0, true)
        );
    }
    public String getName() { return "Ambulance"; }
    public List<ScheduleEntry> getLiveSchedule() { return schedules; }
}

class FerryService implements TransportService {
    private final List<ScheduleEntry> schedules;
    public FerryService() {
        schedules = Arrays.asList(
            new ScheduleEntry("Ferry","F7","Harbor","BaySide", LocalTime.of(10,0), 2.5, false),
            new ScheduleEntry("Ferry","F8","Harbor","Island", LocalTime.of(11,0), 3.0, false)
        );
    }
    public String getName() { return "Ferry"; }
    public List<ScheduleEntry> getLiveSchedule() { return schedules; }
}

// ================= Dashboard =================
class Dashboard {
    public static void display(List<TransportService> services) {
        services.forEach(s -> s.getLiveSchedule().forEach(System.out::println));
    }
}

// ================= Main =================
public class App {
    public static void main(String[] args) {
        List<TransportService> services = Arrays.asList(
            new BusService(), new MetroService(), new TaxiService(), new AmbulanceService(), new FerryService()
        );

        Predicate<ScheduleEntry> morningCommute = s -> s.departureTime.isAfter(LocalTime.of(7,30)) && s.departureTime.isBefore(LocalTime.of(9,30));
        Comparator<ScheduleEntry> byTime = Comparator.comparing(se -> se.departureTime);
        Comparator<ScheduleEntry> byFare = Comparator.comparingDouble(se -> se.fare);

        List<ScheduleEntry> earliest = services.stream()
                .flatMap(s -> s.filterAndSort(morningCommute, byTime).stream())
                .limit(5)
                .collect(Collectors.toList());

        List<ScheduleEntry> cheapest = services.stream()
                .flatMap(s -> s.getLiveSchedule().stream())
                .sorted(byFare.thenComparing(byTime))
                .limit(5)
                .collect(Collectors.toList());

        services.forEach(TransportService::printServiceDetails);
        Dashboard.display(services);

        List<Passenger> passengers = Arrays.asList(
            new Passenger("P1","Asha","B12"), new Passenger("P2","Ishan","M1"),
            new Passenger("P3","Meera","B12"), new Passenger("P4","Ravi","M2"),
            new Passenger("P5","Kunal","B12")
        );

        Map<String, List<Passenger>> byRoute = passengers.stream().collect(Collectors.groupingBy(p -> p.routeId));

        List<Trip> trips = Arrays.asList(
            new Trip("Bus","B12",30,45.0,true, LocalDateTime.now()),
            new Trip("Metro","M1",120,264.0,true, LocalDateTime.now()),
            new Trip("Taxi","T-ON",10,39.0,false, LocalDateTime.now()),
            new Trip("Ferry","F7",60,150.0,false, LocalDateTime.now())
        );

        Map<Boolean, List<Trip>> peakVsOff = trips.stream().collect(Collectors.partitioningBy(t -> t.peak));
        DoubleSummaryStatistics revenue = trips.stream().collect(Collectors.summarizingDouble(FareUtils::basicFare));

        FareCalculator calc = FareUtils::surgeFare;
        double est = trips.stream().mapToDouble(calc::calculateFare).sum();

        List<TransportService> prioritized = services.stream()
                .sorted(Comparator.comparing((TransportService s) -> !(s instanceof EmergencyService)))
                .collect(Collectors.toList());

        double d = GeoUtils.calculateDistance(12.9611,77.6387,13.1986,77.7066);

        System.out.println("\n--- Earliest options ---");
        earliest.forEach(System.out::println);
        System.out.println("\n--- Cheapest options ---");
        cheapest.forEach(System.out::println);
        System.out.println("\n--- Passengers by route ---");
        byRoute.forEach((k,v)->System.out.println(k+" -> "+v.size()));
        System.out.println("\n--- Peak vs OffPeak ---");
        System.out.println("Peak: "+peakVsOff.get(true).size()+", Off: "+peakVsOff.get(false).size());
        System.out.println("\n--- Revenue Report ---");
        System.out.println("Total="+revenue.getSum()+" Avg="+revenue.getAverage());
        System.out.println("Estimated with surge="+est);
        System.out.println("\n--- Prioritized Services ---");
        prioritized.forEach(s->System.out.println(s.getName()));
        System.out.println("\n--- Distance Calculation ---");
        System.out.println("Distance sample km="+String.format("%.2f", d));
    }
}
