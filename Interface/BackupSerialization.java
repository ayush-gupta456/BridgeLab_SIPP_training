import java.io.*;

class Data implements Serializable {
    String value;
    Data(String v) { value = v; }
}

public class BackupSerialization {
    public static void main(String[] args) throws Exception {
        Data d = new Data("Backup");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"));
        oos.writeObject(d);
        oos.close();
        System.out.println("Data serialized");
    }
}
