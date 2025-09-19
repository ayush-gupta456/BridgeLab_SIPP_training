package com.gla;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class CollectorsExamples {
    static class Student {
        String name;
        String grade;
        Student(String name, String grade) { this.name = name; this.grade = grade; }
        String getName() { return name; }
        String getGrade() { return grade; }
    }

    static class Order {
        String customer;
        double total;
        Order(String customer, double total) { this.customer = customer; this.total = total; }
        String getCustomer() { return customer; }
        double getTotal() { return total; }
    }

    static class Employee {
        String department;
        double salary;
        Employee(String department, double salary) { this.department = department; this.salary = salary; }
        String getDepartment() { return department; }
        double getSalary() { return salary; }
    }

    static class Book {
        String genre;
        int pages;
        Book(String genre, int pages) { this.genre = genre; this.pages = pages; }
        String getGenre() { return genre; }
        int getPages() { return pages; }
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alice", "A"),
                new Student("Bob", "B"),
                new Student("Charlie", "A"),
                new Student("David", "C")
        );
        Map<String, List<String>> studentsByGrade = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade, Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println(studentsByGrade);

        String paragraph = "hello world hello java java stream world";
        Map<String, Long> wordFrequency = Arrays.stream(paragraph.split(" "))
                .collect(Collectors.toMap(Function.identity(), w -> 1L, Long::sum));
        System.out.println(wordFrequency);

        List<Order> orders = Arrays.asList(
                new Order("Alice", 200.5),
                new Order("Bob", 150.0),
                new Order("Alice", 100.0)
        );
        Map<String, Double> revenueByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(Order::getTotal)));
        System.out.println(revenueByCustomer);

        List<Employee> employees = Arrays.asList(
                new Employee("IT", 50000),
                new Employee("IT", 60000),
                new Employee("HR", 40000),
                new Employee("HR", 45000)
        );
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalaryByDept);

        List<Book> books = Arrays.asList(
                new Book("Fiction", 300),
                new Book("Fiction", 500),
                new Book("Science", 200),
                new Book("Science", 400)
        );
        Map<String, IntSummaryStatistics> bookStats = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre, Collectors.summarizingInt(Book::getPages)));
        System.out.println(bookStats);
    }
}
