interface Exporter {
    void export();
    default void exportToJSON() { System.out.println("Exporting to JSON"); }
}

class CSVExporter implements Exporter {
    public void export() { System.out.println("Exporting CSV"); }
}

class PDFExporter implements Exporter {
    public void export() { System.out.println("Exporting PDF"); }
}

public class DataExport {
    public static void main(String[] args) {
        Exporter csv = new CSVExporter();
        Exporter pdf = new PDFExporter();
        csv.export(); csv.exportToJSON();
        pdf.export(); pdf.exportToJSON();
    }
}
