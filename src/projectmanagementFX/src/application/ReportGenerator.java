package application;

import java.util.List;

public class ReportGenerator {
    public static void generateGraphicalReport(List<Task> tasks) {
        System.out.println("\n--- Graphical Report ---");
        for (Task task : tasks) {
            String bar = "=".repeat((int) (task.getTotalTime() / 1000));
            System.out.println(task.getName() + " [" + bar + "] " + task.getTotalTime() / 1000 + " seconds");
        }
        System.out.println("------------------------");
    }
}
