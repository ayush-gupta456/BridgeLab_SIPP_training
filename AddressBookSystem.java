
import java.io.*;
import java.util.*;

class Contact implements Serializable {
    private String name;
    private String phone;
    private String email;
    private String address;

    public Contact(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email + ", Address: " + address;
    }
}

class AddressBook<T extends Contact> {
    private Map<String, T> contacts = new HashMap<>();

    public void addContact(T contact) {
        contacts.put(contact.getPhone(), contact);
    }

    public void removeContact(String phone) {
        contacts.remove(phone);
    }

    public T searchByPhone(String phone) {
        return contacts.get(phone);
    }

    public List<T> searchByName(String name) {
        List<T> result = new ArrayList<>();
        for (T contact : contacts.values()) {
            if (contact.getName().equalsIgnoreCase(name)) {
                result.add(contact);
            }
        }
        return result;
    }

    public List<T> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }

    public void saveToFile(String filename) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(contacts);
        oos.close();
    }

    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        contacts = (Map<String, T>) ois.readObject();
        ois.close();
    }

    public boolean hasSameAddress(String phone1, String phone2) {
        T c1 = contacts.get(phone1);
        T c2 = contacts.get(phone2);
        return c1 != null && c2 != null && c1.getAddress().equalsIgnoreCase(c2.getAddress());
    }

    public List<T> sortByName() {
        List<T> sorted = getAllContacts();
        sorted.sort(Comparator.comparing(Contact::getName));
        return sorted;
    }

    public List<T> sortByPhone() {
        List<T> sorted = getAllContacts();
        sorted.sort(Comparator.comparing(Contact::getPhone));
        return sorted;
    }
}

public class Main {
    public static void main(String[] args) {
        AddressBook<Contact> book = new AddressBook<>();

        book.addContact(new Contact("Aarav Sharma", "9876543210", "aarav@gmail.com", "Lucknow"));
        book.addContact(new Contact("Priya Verma", "9123456789", "priya@gmail.com", "Mumbai"));
        book.addContact(new Contact("Rahul Singh", "9988776655", "rahul@gmail.com", "Lucknow"));
        book.addContact(new Contact("Neha Patel", "9090909090", "neha@gmail.com", "Ahmedabad"));
        book.addContact(new Contact("Anjali Mehta", "9012345678", "anjali@gmail.com", "Mumbai"));

        System.out.println("All Contacts:");
        for (Contact c : book.getAllContacts()) System.out.println(c);

        System.out.println("\nSearch by Name (Rahul Singh):");
        for (Contact c : book.searchByName("Rahul Singh")) System.out.println(c);

        System.out.println("\nSearch by Phone (9123456789):");
        System.out.println(book.searchByPhone("9123456789"));

        System.out.println("\nSorted by Name:");
        for (Contact c : book.sortByName()) System.out.println(c);

        System.out.println("\nSorted by Phone:");
        for (Contact c : book.sortByPhone()) System.out.println(c);

        System.out.println("\nHas same address (9876543210 and 9988776655):");
        System.out.println(book.hasSameAddress("9876543210", "9988776655"));

        try {
            book.saveToFile("contacts.dat");
            AddressBook<Contact> loadedBook = new AddressBook<>();
            loadedBook.loadFromFile("contacts.dat");
            System.out.println("\nContacts loaded from file:");
            for (Contact c : loadedBook.getAllContacts()) System.out.println(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
