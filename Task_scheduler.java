import java.util.*;

// Task class
class Task implements Comparable<Task> {
    String name;
    int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    // Higher priority tasks come first
    public int compareTo(Task t) {
        return t.priority - this.priority;
    }

    public String toString() {
        return name + " (Priority: " + priority + ")";
    }
}

public class Task_scheduler{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // HashMap to store task ID -> Task
        HashMap<Integer, Task> taskMap = new HashMap<>();

        // PriorityQueue to schedule tasks by priority
        PriorityQueue<Task> pq = new PriorityQueue<>();

        int idCounter = 1;

        while (true) {
            System.out.println("\nTask Scheduler Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Show Scheduled Tasks");
            System.out.println("3. Execute Next Task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter task priority (higher = more important): ");
                    int priority = sc.nextInt();
                    sc.nextLine(); // consume newline

                    Task task = new Task(name, priority);
                    taskMap.put(idCounter, task);
                    pq.add(task);
                    System.out.println("Task added with ID: " + idCounter);
                    idCounter++;
                    break;

                case 2:
                    System.out.println("\nScheduled Tasks (by priority):");
                    if (pq.isEmpty()) {
                        System.out.println("No tasks scheduled.");
                    } else {
                        PriorityQueue<Task> temp = new PriorityQueue<>(pq);
                        while (!temp.isEmpty()) {
                            System.out.println(temp.poll());
                        }
                    }
                    break;

                case 3:
                    if (pq.isEmpty()) {
                        System.out.println("No tasks to execute.");
                    } else {
                        Task nextTask = pq.poll();
                        System.out.println("Executing: " + nextTask);
                        taskMap.values().remove(nextTask); // Remove from HashMap
                    }
                    break;

                case 4:
                    System.out.println("Exiting Task Scheduler...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
