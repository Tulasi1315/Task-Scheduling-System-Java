import java.util.*;

// Task Class
class Task implements Comparable<Task> {

    int id;
    String name;
    int priority;
    String deadline;
    String status;

    public Task(int id, String name, int priority, String deadline) {

        this.id = id;
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
        this.status = "Pending";
    }

    // Higher priority tasks first
    @Override
    public int compareTo(Task t) {
        return Integer.compare(t.priority, this.priority);
    }

    @Override
    public String toString() {

        return "\nTask ID      : " + id +
               "\nTask Name    : " + name +
               "\nPriority     : " + priority +
               "\nDeadline     : " + deadline +
               "\nStatus       : " + status + "\n";
    }
}

public class TaskScheduler {

    static Scanner sc = new Scanner(System.in);

    // Priority Queue for scheduling
    static PriorityQueue<Task> pq = new PriorityQueue<>();

    // HashMap for fast searching
    static HashMap<Integer, Task> taskMap = new HashMap<>();

    static int idCounter = 1;

    // Add Task
    public static void addTask() {

        System.out.print("Enter Task Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Priority (Higher Number = Higher Priority): ");
        int priority = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Deadline: ");
        String deadline = sc.nextLine();

        Task task = new Task(idCounter, name, priority, deadline);

        pq.add(task);
        taskMap.put(idCounter, task);

        System.out.println("\nTask Added Successfully!");
        System.out.println("Task ID: " + idCounter);

        idCounter++;
    }

    // View Tasks
    public static void viewTasks() {

        if (pq.isEmpty()) {

            System.out.println("\nNo Tasks Available!\n");
            return;
        }

        PriorityQueue<Task> temp = new PriorityQueue<>(pq);

        System.out.println("\n===== TASK LIST =====");

        while (!temp.isEmpty()) {
            System.out.println(temp.poll());
        }
    }

    // Execute Highest Priority Task
    public static void executeTask() {

        if (pq.isEmpty()) {

            System.out.println("\nNo Tasks to Execute!\n");
            return;
        }

        Task task = pq.poll();

        task.status = "Completed";

        System.out.println("\nExecuting Task...");
        System.out.println(task);

        taskMap.remove(task.id);
    }

    // Search Task
    public static void searchTask() {

        System.out.print("Enter Task ID: ");
        int id = sc.nextInt();

        if (taskMap.containsKey(id)) {

            System.out.println("\n===== TASK FOUND =====");
            System.out.println(taskMap.get(id));

        } else {

            System.out.println("\nTask Not Found!\n");
        }
    }

    // Delete Task
    public static void deleteTask() {

        System.out.print("Enter Task ID to Delete: ");
        int id = sc.nextInt();

        if (taskMap.containsKey(id)) {

            Task task = taskMap.get(id);

            pq.remove(task);
            taskMap.remove(id);

            System.out.println("\nTask Deleted Successfully!\n");

        } else {

            System.out.println("\nTask Not Found!\n");
        }
    }

    // Update Task Priority
    public static void updatePriority() {

        System.out.print("Enter Task ID: ");
        int id = sc.nextInt();

        if (taskMap.containsKey(id)) {

            Task task = taskMap.get(id);

            pq.remove(task);

            System.out.print("Enter New Priority: ");
            int newPriority = sc.nextInt();

            task.priority = newPriority;

            pq.add(task);

            System.out.println("\nPriority Updated Successfully!\n");

        } else {

            System.out.println("\nTask Not Found!\n");
        }
    }

    // Main Method
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== ADVANCED TASK SCHEDULER SYSTEM =====");

            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Execute Highest Priority Task");
            System.out.println("4. Search Task");
            System.out.println("5. Delete Task");
            System.out.println("6. Update Task Priority");
            System.out.println("7. Exit");

            System.out.print("Enter Your Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addTask();
                    break;

                case 2:
                    viewTasks();
                    break;

                case 3:
                    executeTask();
                    break;

                case 4:
                    searchTask();
                    break;

                case 5:
                    deleteTask();
                    break;

                case 6:
                    updatePriority();
                    break;

                case 7:
                    System.out.println("\nExiting System...");
                    System.exit(0);

                default:
                    System.out.println("\nInvalid Choice!\n");
            }
        }
    }
}