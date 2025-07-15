import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReadingComparison {
    public static void readWithFileReader(String path) throws IOException {
        try (FileReader fr = new FileReader(path)) {
            while (fr.read() != -1);
        }
    }

    public static void readWithInputStreamReader(String path) throws IOException {
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(path))) {
            while (isr.read() != -1);
        }
    }

    public static void main(String[] args) throws IOException {
        String path = "sample.txt"; // Replace with actual 100MB file path

        long start = System.nanoTime();
        readWithFileReader(path);
        long end = System.nanoTime();
        System.out.println("FileReader: " + (end - start) / 1_000_000.0 + " ms");

        start = System.nanoTime();
        readWithInputStreamReader(path);
        end = System.nanoTime();
        System.out.println("InputStreamReader: " + (end - start) / 1_000_000.0 + " ms");
    }
}
