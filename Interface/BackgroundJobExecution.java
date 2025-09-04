public class BackgroundJobExecution {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Job executed in background");
        new Thread(task).start();
    }
}
