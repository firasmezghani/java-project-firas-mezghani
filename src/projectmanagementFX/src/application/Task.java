package application;

import java.util.ArrayList;
import java.util.List;

public class Task {
    public enum Status { PENDING, IN_PROGRESS, COMPLETED }

    private String name;
    private Status status;
    private List<TeamMember> assignedMembers;
    private long startTime;
    private long totalTime;

    public Task(String name) {
        this.name = name;
        this.status = Status.PENDING;
        this.assignedMembers = new ArrayList<>();
        this.totalTime = 0;
        this.startTime = 0;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public long getTotalTime() {
        if (status == Status.IN_PROGRESS) {
            return totalTime + (System.currentTimeMillis() - startTime);
        }
        return totalTime;
    }

    public List<TeamMember> getAssignedMembers() {
        return assignedMembers;
    }

    public void assignMember(TeamMember member) {
        if (!assignedMembers.contains(member)) {
            assignedMembers.add(member);
            if (status == Status.PENDING) {
                status = Status.IN_PROGRESS;
            }
        }
    }

    public void removeMember(TeamMember member) {
        assignedMembers.remove(member);
        if (assignedMembers.isEmpty()) {
            status = Status.PENDING;
        }
    }

    public void updateStatus(Status newStatus) {
        if (assignedMembers.isEmpty()) {
            System.out.println("Cannot update status. Task has no assigned members.");
        } else {
            this.status = newStatus;
            if (newStatus == Status.IN_PROGRESS) {
                startTime = System.currentTimeMillis();
            } else if (newStatus == Status.COMPLETED || newStatus == Status.PENDING) {
                totalTime += System.currentTimeMillis() - startTime;
                startTime = 0;
            }
        }
    }

    @Override
    public String toString() {
        return "Task: " + name + " | Status: " + status + " | Assigned Members: " + assignedMembers.size() + " | Time: " + (getTotalTime() / 1000) + " seconds";
    }
}
