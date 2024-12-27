package application;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private List<Task> tasks;
    private List<TeamMember> teamMembers;

    public Project(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        this.teamMembers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void addTeamMember(TeamMember member) {
        if (!teamMembers.contains(member)) {
            teamMembers.add(member);
        }
    }

    public void assignTaskToMember(String memberName, Task task) {
        TeamMember member = teamMembers.stream()
                .filter(m -> m.getName().equals(memberName))
                .findFirst()
                .orElse(null);

        if (member != null) {
            task.assignMember(member);
        } else {
            System.out.println("No team member with name: " + memberName);
        }
    }
}
