package com.gla;

public class ParcelTracker {
    static class Stage {
        String name;
        Stage next;

        Stage(String name) {
            this.name = name;
            this.next = null;
        }
    }

    private Stage head;

    public void addStage(String name) {
        Stage newStage = new Stage(name);
        if (head == null) {
            head = newStage;
            return;
        }
        Stage temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStage;
    }

    public void insertCheckpointAfter(String afterStage, String checkpoint) {
        Stage temp = head;
        while (temp != null && !temp.name.equals(afterStage)) {
            temp = temp.next;
        }
        if (temp != null) {
            Stage checkpointStage = new Stage(checkpoint);
            checkpointStage.next = temp.next;
            temp.next = checkpointStage;
        }
    }

    public void markStageLost(String name) {
        Stage temp = head;
        while (temp != null) {
            if (temp.name.equals(name)) {
                temp.next = null;
                return;
            }
            temp = temp.next;
        }
    }

    public void displayTrackingPath() {
        Stage temp = head;
        while (temp != null) {
            System.out.print(temp.name);
            if (temp.next != null) {
                System.out.print(" → ");
            }
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ParcelTracker tracker = new ParcelTracker();
        tracker.addStage("Packed");
        tracker.addStage("Shipped");
        tracker.addStage("In Transit");
        tracker.addStage("Delivered");

        tracker.insertCheckpointAfter("Shipped", "At Regional Hub");
        tracker.markStageLost("In Transit");
        tracker.displayTrackingPath();
    }
}