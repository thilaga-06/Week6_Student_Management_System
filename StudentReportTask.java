
public class StudentReportTask extends Thread {

    @Override
    public void run() {
        System.out.println("\nGenerating student report...");

        for (int i = 1; i <= 5; i++) {
            System.out.println("Report progress: " + (i * 20) + "%");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Report generation interrupted.");
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("Student report generated successfully!");
    }
}
