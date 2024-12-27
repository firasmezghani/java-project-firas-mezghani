package application;

import java.util.List;

public class TimeReport {
    public static void generateReport(List<Task> tasks) {
        int pending = 0, inProgress = 0, completed = 0;

        System.out.println("\n--- Time Report ---");
        for (Task task : tasks) {
            System.out.println(task);
            switch (task.getStatus()) {
                case PENDING -> pending++;
                case IN_PROGRESS -> inProgress++;
                case COMPLETED -> completed++;
            }
        }
        System.out.println("\nSummary:");
        System.out.println("Pending: " + pending);
        System.out.println("In Progress: " + inProgress);
        System.out.println("Completed: " + completed);
    }
}

