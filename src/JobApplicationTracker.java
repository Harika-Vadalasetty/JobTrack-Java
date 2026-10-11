import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * JobApplication represents ONE job application.
 * It uses encapsulation: the fields are private and are accessed only
 * through public getter methods. Only the status can be changed later.
 */
class JobApplication {

    private final int id;              // unique ID, never changes
    private final String companyName;  // company applied to
    private final String jobRole;      // role applied for
    private String status;             // current status, can be updated

    public JobApplication(int id, String companyName, String jobRole, String status) {
        this.id = id;
        this.companyName = companyName;
        this.jobRole = jobRole;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

/**
 * JobApplicationTracker is a console-based, menu-driven program that lets a
 * user add, view, search, update and delete job applications.
 *
 * NOTE: Data is stored only in an ArrayList (in memory). All records are
 * lost when the program closes.
 */
public class JobApplicationTracker {

    // The five allowed statuses. Menu option N maps to STATUSES[N - 1].
    private static final String[] STATUSES = {
        "Applied", "Assessment", "Interview", "Selected", "Rejected"
    };

    // In-memory storage for all applications
    private static final ArrayList<JobApplication> applications = new ArrayList<>();

    // Counter used to give every new application a unique ID (1, 2, 3, ...)
    private static int nextId = 1;

    // One Scanner shared by the whole program
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("      JOB APPLICATION TRACKER");
        System.out.println("=====================================");

        boolean running = true;

        try {
            while (running) {
                printMenu();
                int choice = readInt("Enter your choice (1-6): ");

                switch (choice) {
                    case 1:
                        addApplication();
                        break;
                    case 2:
                        viewAllApplications();
                        break;
                    case 3:
                        searchByCompany();
                        break;
                    case 4:
                        updateStatus();
                        break;
                    case 5:
                        deleteApplication();
                        break;
                    case 6:
                        System.out.println("\nThank you for using Job Application Tracker. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please enter a number from 1 to 6.");
                }
            }
        } catch (NoSuchElementException e) {
            // Happens if the input stream ends unexpectedly (e.g. Ctrl+D / Ctrl+Z)
            System.out.println("\nInput ended. Exiting the program. Goodbye!");
        }

        scanner.close();
    }

    // ---------------------------------------------------------------
    // Menu
    // ---------------------------------------------------------------

    private static void printMenu() {
        System.out.println("\n------------- MAIN MENU -------------");
        System.out.println("1. Add Job Application");
        System.out.println("2. View All Applications");
        System.out.println("3. Search by Company Name");
        System.out.println("4. Update Application Status");
        System.out.println("5. Delete Application");
        System.out.println("6. Exit");
        System.out.println("-------------------------------------");
    }

    // ---------------------------------------------------------------
    // Features (CRUD operations)
    // ---------------------------------------------------------------

    /** CREATE: adds a new application with the default status "Applied". */
    private static void addApplication() {
        System.out.println("\n--- Add Job Application ---");
        String company = readNonEmptyText("Enter company name: ");
        String role = readNonEmptyText("Enter job role: ");

        JobApplication application = new JobApplication(nextId, company, role, STATUSES[0]);
        applications.add(application);
        nextId++; // the next application gets the next ID

        System.out.println("Application added successfully! Assigned ID: " + application.getId());
    }

    /** READ: shows every saved application in a table. */
    private static void viewAllApplications() {
        System.out.println("\n--- All Applications ---");

        if (applications.isEmpty()) {
            System.out.println("No applications found. Add one using option 1.");
            return;
        }

        printTableHeader();
        for (JobApplication application : applications) {
            printApplicationRow(application);
        }
        System.out.println("Total applications: " + applications.size());
    }

    /** READ: finds applications whose company name contains the typed text (case-insensitive). */
    private static void searchByCompany() {
        System.out.println("\n--- Search by Company Name ---");

        if (applications.isEmpty()) {
            System.out.println("No applications found. Add one using option 1.");
            return;
        }

        String keyword = readNonEmptyText("Enter company name to search: ");
        String keywordLower = keyword.toLowerCase();

        int matches = 0;
        for (JobApplication application : applications) {
            if (application.getCompanyName().toLowerCase().contains(keywordLower)) {
                if (matches == 0) {
                    printTableHeader(); // print header only once, before the first match
                }
                printApplicationRow(application);
                matches++;
            }
        }

        if (matches == 0) {
            System.out.println("No application found for company: " + keyword);
        } else {
            System.out.println("Matches found: " + matches);
        }
    }

    /** UPDATE: changes the status of one application, chosen by ID. */
    private static void updateStatus() {
        System.out.println("\n--- Update Application Status ---");

        if (applications.isEmpty()) {
            System.out.println("No applications found. Add one using option 1.");
            return;
        }

        int id = readInt("Enter application ID: ");
        if (id < 1) {
            System.out.println("Invalid ID. Please enter a positive whole number.");
            return;
        }

        JobApplication application = findById(id);

        if (application == null) {
            System.out.println("Application with ID " + id + " was not found.");
            return;
        }

        System.out.println("Current status: " + application.getStatus());
        System.out.println("Choose the new status:");
        for (int i = 0; i < STATUSES.length; i++) {
            System.out.println((i + 1) + ". " + STATUSES[i]);
        }

        int statusChoice = readInt("Enter status number (1-" + STATUSES.length + "): ");

        if (statusChoice < 1 || statusChoice > STATUSES.length) {
            System.out.println("Invalid status choice. Status was not changed.");
            return;
        }

        application.setStatus(STATUSES[statusChoice - 1]);
        System.out.println("Status updated successfully to: " + application.getStatus());
    }

    /** DELETE: removes one application, chosen by ID. */
    private static void deleteApplication() {
        System.out.println("\n--- Delete Application ---");

        if (applications.isEmpty()) {
            System.out.println("No applications found. Add one using option 1.");
            return;
        }

        int id = readInt("Enter application ID to delete: ");
        if (id < 1) {
            System.out.println("Invalid ID. Please enter a positive whole number.");
            return;
        }

        JobApplication application = findById(id);

        if (application == null) {
            System.out.println("Application with ID " + id + " was not found.");
            return;
        }

        applications.remove(application);
        System.out.println("Application with ID " + id + " (" + application.getCompanyName()
                + ") deleted successfully.");
    }

    // ---------------------------------------------------------------
    // Helper methods
    // ---------------------------------------------------------------

    /** Returns the application with the given ID, or null if it does not exist. */
    private static JobApplication findById(int id) {
        for (JobApplication application : applications) {
            if (application.getId() == id) {
                return application;
            }
        }
        return null;
    }

    /**
     * Reads a whole number from the user. If the user types something that is
     * not a number (letters, symbols, empty line), this returns -1 instead of
     * crashing. Every caller treats -1 as invalid because valid menu choices,
     * IDs and status numbers are always 1 or more, and each caller prints its
     * own friendly error message.
     */
    private static int readInt(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /** Keeps asking until the user types some text that is not empty. */
    private static String readNonEmptyText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static void printTableHeader() {
        System.out.printf("%-5s %-22s %-26s %-12s%n", "ID", "Company", "Role", "Status");
        System.out.println("-------------------------------------------------------------------");
    }

    private static void printApplicationRow(JobApplication application) {
        System.out.printf("%-5d %-22s %-26s %-12s%n",
                application.getId(),
                application.getCompanyName(),
                application.getJobRole(),
                application.getStatus());
    }
}