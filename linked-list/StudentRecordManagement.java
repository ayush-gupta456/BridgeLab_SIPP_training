public class StudentRecordManagement {
    class Node {
        int rollNumber;
        String name;
        int age;
        double grade;
        Node next;

        Node(int rollNumber, String name, int age, double grade) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.age = age;
            this.grade = grade;
            this.next = null;
        }
    }

    private Node head;

    public StudentRecordManagement() {
        head = null;
    }

    // Add at beginning
    public void addAtBeginning(int rollNumber, String name, int age, double grade) {
        Node newNode = new Node(rollNumber, name, age, grade);
        newNode.next = head;
        head = newNode;
    }

    // Add at end
    public void addAtEnd(int rollNumber, String name, int age, double grade) {
        Node newNode = new Node(rollNumber, name, age, grade);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Add at position
    public void addAtPosition(int position, int rollNumber, String name, int age, double grade) {
        if (position < 1) {
            System.out.println("Invalid position");
            return;
        }
        if (position == 1) {
            addAtBeginning(rollNumber, name, age, grade);
            return;
        }
        Node newNode = new Node(rollNumber, name, age, grade);
        Node current = head;
        for (int i = 1; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Position out of range");
            return;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    // Delete by roll number
    public void deleteByRollNumber(int rollNumber) {
        if (head == null) return;
        if (head.rollNumber == rollNumber) {
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null && current.next.rollNumber != rollNumber) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Roll number not found");
            return;
        }
        current.next = current.next.next;
    }

    // Search by roll number
    public void searchByRollNumber(int rollNumber) {
        Node current = head;
        while (current != null) {
            if (current.rollNumber == rollNumber) {
                System.out.println("Found: " + current.rollNumber + ", " + current.name + ", " + current.age + ", " + current.grade);
                return;
            }
            current = current.next;
        }
        System.out.println("Roll number not found");
    }

    // Display all records
    public void displayAll() {
        Node current = head;
        while (current != null) {
            System.out.println(current.rollNumber + ", " + current.name + ", " + current.age + ", " + current.grade);
            current = current.next;
        }
    }

    // Update grade
    public void updateGrade(int rollNumber, double newGrade) {
        Node current = head;
        while (current != null) {
            if (current.rollNumber == rollNumber) {
                current.grade = newGrade;
                System.out.println("Grade updated for roll number " + rollNumber);
                return;
            }
            current = current.next;
        }
        System.out.println("Roll number not found");
    }

    public static void main(String[] args) {
        StudentRecordManagement list = new StudentRecordManagement();
        list.addAtBeginning(1, "Alice", 20, 85.5);
        list.addAtEnd(2, "Bob", 21, 90.0);
        list.addAtPosition(2, 3, "Charlie", 19, 88.0);
        System.out.println("All records:");
        list.displayAll();
        list.searchByRollNumber(2);
        list.updateGrade(1, 95.0);
        list.deleteByRollNumber(3);
        System.out.println("After deletion:");
        list.displayAll();
    }
}