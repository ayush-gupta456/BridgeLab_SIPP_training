public class Manager extends Employee {
    public Manager(int id, String dept, double salary) {
        super(id, dept, salary);
    }

    public void displayInfo() {
        System.out.println("Manager ID: " + employeeID + ", Dept: " + department);
    }
}