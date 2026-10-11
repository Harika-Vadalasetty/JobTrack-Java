# Job Application Tracker

A console-based Java application that helps a job seeker keep track of the companies they have applied to, the roles they applied for, and the current status of each application.

Built with **Core Java only** (no frameworks, no database, no external libraries).

---

## Problem Statement

While applying to many companies, it is easy to lose track of where you applied, for which role, and what happened next (assessment, interview, result). Spreadsheets and notes become messy. This project provides a simple, menu-driven tool to add, view, search, update and delete job applications from the console.

---

## Features

1. **Add** a job application with a company name and job role.
2. **Automatic unique ID** for every application (1, 2, 3, ...). IDs are never reused during a run.
3. **View all** saved applications in a neat table.
4. **Search by company name** - case-insensitive, and partial matches work (searching `google` finds `Google` and `Google Cloud`).
5. **Update status** of an application. Supported statuses:
   `Applied`, `Assessment`, `Interview`, `Selected`, `Rejected`.
   New applications start as `Applied`.
6. **Delete** an application by its ID.
7. **Input validation** - empty text is rejected, non-numeric input and invalid menu choices are handled without crashing.
8. **Friendly messages** when there are no records or an application ID is not found.
9. **Menu-driven interface** that keeps running until the user chooses Exit.

---

## Technologies Used

- Java (Core Java, tested with JDK 21; works with JDK 8 or later)
- Eclipse IDE
- Git and GitHub

---

## Java Concepts Demonstrated

- Classes and objects (`JobApplication`, `JobApplicationTracker`)
- Encapsulation (private fields with getters and one setter)
- Constructors
- `ArrayList` for storing objects
- Methods (one method per feature)
- Loops (`while`, `for`, enhanced `for`)
- `switch` statement for the menu
- Exception handling (`NumberFormatException`, `NoSuchElementException`)
- Basic CRUD operations (Create, Read, Update, Delete)
- Arrays (`String[]` for the allowed statuses)
- `static` members and the `Scanner` class for console input

---

## Project Structure

```
JobApplicationTracker/
├── src/
│   └── JobApplicationTracker.java
├── README.md
└── .gitignore
```

Total project files: **3**.

> Eclipse may also create files such as `.classpath`, `.project`, `.settings/` and a `bin/` folder on your computer. These are generated automatically, are not part of the project deliverables, and are excluded from Git by `.gitignore`.

---

## Prerequisites

- JDK 8 or later installed (check with `java -version` and `javac -version`)
- Eclipse IDE (optional - only needed for the Eclipse steps)
- Git (only needed to upload the project to GitHub)

---

## How to Run in Eclipse

1. Open Eclipse and choose **File > New > Java Project**.
2. Enter the project name `JobApplicationTracker` and click **Finish** (choose "Don't Create" if asked about `module-info.java`).
3. In Package Explorer, right-click the **src** folder > **New > Class**.
4. Leave the package field empty, type the name `JobApplicationTracker` and click **Finish**.
5. Replace everything in the new file with the code from `src/JobApplicationTracker.java`, then save with `Ctrl + S`.
6. Right-click the file > **Run As > Java Application**.
7. Type your choices in the **Console** tab at the bottom of Eclipse.

---

## How to Run from the Terminal

Open a terminal inside the `JobApplicationTracker` folder (the folder that contains `src`), then run:

```
mkdir bin
javac -d bin src/JobApplicationTracker.java
java -cp bin JobApplicationTracker
```

On Windows Command Prompt, `mkdir bin` works the same way. The `bin` folder holds the compiled `.class` files and is ignored by Git.

---

## Sample Program Output

Text after a prompt (for example `1` after `Enter your choice (1-6):`) is what the user typed.

```
=====================================
      JOB APPLICATION TRACKER
=====================================

------------- MAIN MENU -------------
1. Add Job Application
2. View All Applications
3. Search by Company Name
4. Update Application Status
5. Delete Application
6. Exit
-------------------------------------
Enter your choice (1-6): 2

--- All Applications ---
No applications found. Add one using option 1.

Enter your choice (1-6): 1

--- Add Job Application ---
Enter company name: Google
Enter job role: Java Developer
Application added successfully! Assigned ID: 1

Enter your choice (1-6): 1

--- Add Job Application ---
Enter company name: Google Cloud
Enter job role: ML Engineer
Application added successfully! Assigned ID: 2

Enter your choice (1-6): 2

--- All Applications ---
ID    Company                Role                       Status
-------------------------------------------------------------------
1     Google                 Java Developer             Applied
2     Google Cloud           ML Engineer                Applied
Total applications: 2

Enter your choice (1-6): 3

--- Search by Company Name ---
Enter company name to search: GOOGLE
ID    Company                Role                       Status
-------------------------------------------------------------------
1     Google                 Java Developer             Applied
2     Google Cloud           ML Engineer                Applied
Matches found: 2

Enter your choice (1-6): 4

--- Update Application Status ---
Enter application ID: 1
Current status: Applied
Choose the new status:
1. Applied
2. Assessment
3. Interview
4. Selected
5. Rejected
Enter status number (1-5): 3
Status updated successfully to: Interview

Enter your choice (1-6): 5

--- Delete Application ---
Enter application ID to delete: 2
Application with ID 2 (Google Cloud) deleted successfully.

Enter your choice (1-6): 5

--- Delete Application ---
Enter application ID to delete: 2
Application with ID 2 was not found.

Enter your choice (1-6): 9

Invalid choice. Please enter a number from 1 to 6.

Enter your choice (1-6): 6

Thank you for using Job Application Tracker. Goodbye!
```

(The menu is printed again before every "Enter your choice" prompt; it is shortened here to keep the sample readable.)

---

## Current Limitations

- **No data persistence:** applications are stored only in an in-memory `ArrayList`. **All records are lost when the program closes.**
- Everything is in a single source file, which is fine for a small project but would not scale.
- Only the company name can be searched (not role or status).
- Application details (company, role) cannot be edited after they are added - only the status can be updated.
- No dates, notes or contact details are stored for an application.
- No automated unit tests; testing is done manually through the console.

---

## Future Enhancements

These are **not implemented yet**; they are ideas for future versions.

- Save and load applications using a file (CSV or text) or a MySQL database with JDBC so data survives restarts.
- Split the code into separate classes (model, service, user interface) and add JUnit tests.
- Store the application date and notes, and add filtering and sorting by status or date.
- Edit company name and role of an existing application.
- Build a graphical or web interface (for example with Spring Boot).

---

## Example GitHub Repository Description

> Console-based Job Application Tracker in Core Java - add, view, search, update and delete job applications with status tracking. Built with ArrayList, OOP and exception handling.

Suggested topics: `java`, `core-java`, `oop`, `console-application`, `crud`, `beginner-project`