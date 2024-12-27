package application;

import java.util.*;

public class ProjectManagementApp {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Project> projects = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nProject Management System");
            System.out.println("1. Create Project");
            System.out.println("2. Add Team Member");
            System.out.println("3. Add Task to Project");
            System.out.println("4. Assign Task to Team Member");
            System.out.println("5. Generate Reports");
            System.out.println("6. Modify Task Status");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createProject();
                case 2 -> addTeamMember();
                case 3 -> addTaskToProject();
                case 4 -> assignTaskToMember();
                case 5 -> generateReports();
                case 6 -> modifyTaskStatus();
                case 7 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void createProject() {
        System.out.print("Enter project name: ");
        String name = scanner.nextLine();
        projects.add(new Project(name));
        System.out.println("Project '" + name + "' created.");
    }

    private static void addTeamMember() {
        Project project = selectProject();
        if (project != null) {
            System.out.print("Enter team member ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter team member name: ");
            String name = scanner.nextLine();
            project.addTeamMember(new TeamMember(id, name));
        }
    }

    private static void addTaskToProject() {
        Project project = selectProject();
        if (project != null) {
            System.out.print("Enter task name: ");
            String taskName = scanner.nextLine();
            project.addTask(new Task(taskName));
            System.out.println("Task added successfully.");
        }
    }

    private static void assignTaskToMember() {
        Project project = selectProject();
        if (project != null) {
            System.out.print("Enter team member ID: ");
            String memberId = scanner.nextLine();
            System.out.print("Enter task name: ");
            String taskName = scanner.nextLine();
            project.assignTaskToMember(memberId, new Task(taskName));
        }
    }

    private static void modifyTaskStatus() {
        Project project = selectProject();
        if (project != null) {
            List<Task> tasks = project.getTasks();
            if (tasks.isEmpty()) {
                System.out.println("No tasks in this project.");
                return;
            }
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            System.out.print("Select a task to modify: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();

            System.out.println("Choose new status: 1. Pending, 2. In Progress, 3. Completed");
            int statusChoice = scanner.nextInt();
            Task.Status newStatus = switch (statusChoice) {
                case 2 -> Task.Status.IN_PROGRESS;
                case 3 -> Task.Status.COMPLETED;
                default -> Task.Status.PENDING;
            };

            tasks.get(index).updateStatus(newStatus);
        }
    }

    private static void generateReports() {
        Project project = selectProject();
        if (project != null) {
            TimeReport.generateReport(project.getTasks());
        }
    }

    private static Project selectProject() {
        if (projects.isEmpty()) {
            System.out.println("No projects available.");
            return null;
        }
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". " + projects.get(i).getName());
        }
        System.out.print("Select project: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        return index >= 0 && index < projects.size() ? projects.get(index) : null;
    }
}



