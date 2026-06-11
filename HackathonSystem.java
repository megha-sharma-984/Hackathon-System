
import java.util.Scanner;
class Team {
    // Strings: Storing team names and project titles
    private String teamName;
    private String projectTitle;
    private double projectScore;

    // Constructor
    public Team(String teamName, String projectTitle) {
        this.teamName = teamName;
        this.projectTitle = projectTitle;
        this.projectScore = -1; // -1 indicates the project hasn't been scored yet
    }

    // Getters and Setters
    public String getTeamName() {
        return teamName;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public double getProjectScore() {
        return projectScore;
    }

    public void setProjectScore(double projectScore) {
        this.projectScore = projectScore;
    }

    // Method: Display individual team details
    public void displayTeamDetails() {
        String scoreDisplay = (projectScore == -1) ? "Not Scored Yet" : String.valueOf(projectScore);
        System.out.println("Team Name: " + teamName + " | Project: " + projectTitle + " | Score: " + scoreDisplay);
    }
}

public class HackathonSystem {
    // Array: Fixed-size storage for up to 20 teams
    private static Team[] teams = new Team[20];
    private static int teamCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Loop: Keeps the application running until the user decides to exit
        while (true) {
            // Menu Display
            System.out.println("\n===== HACKATHON MANAGEMENT SYSTEM =====");
            System.out.println("1. Register a Team");
            System.out.println("2. Display All Teams");
            System.out.println("3. Search for a Team");
            System.out.println("4. Grade/Score a Team");
            System.out.println("5. Declare Hackathon Winner");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // If-Else Ladder: Directing user flow based on menu choice
            if (choice == 1) {
                registerTeam();
            } else if (choice == 2) {
                displayAllTeams();
            } else if (choice == 3) {
                searchTeam();
            } else if (choice == 4) {
                scoreTeam();
            } else if (choice == 5) {
                declareWinner();
            } else if (choice == 6) {
                System.out.println("Exiting System");
                break;
            } else {
                System.out.println("Invalid choice! Please select a valid option");
            }
        }
    }

    // Method: Register Team with Validation
    private static void registerTeam() {
        // Validation: Check if array is full
        if (teamCount >= teams.length) {
            System.out.println("Error: Registration full! Maximum capacity reached.");
            return;
        }

        System.out.print("Enter Team Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Project Title: ");
        String title = scanner.nextLine();

        // Validation: Check for empty input strings
        if (name.isEmpty() || title.isEmpty()) {
            System.out.println("Error: Team Name and Project Title cannot be blank!");
            return;
        }

        // Store Team object inside the Array
        teams[teamCount] = new Team(name, title);
        teamCount++;
        System.out.println("Success: Team '" + name + "' registered successfully!");
    }

    // Method & Loop: Display all registered teams
    private static void displayAllTeams() {
        // Validation: Check if any teams exist
        if (teamCount == 0) {
            System.out.println("No teams registered yet.");
            return;
        }

        System.out.println("\n--- Registered Teams List ---");
        // Loop: Iterating through the occupied slots of the array
        for (int i = 0; i < teamCount; i++) {
            teams[i].displayTeamDetails();
        }
    }

    // Method & Search: Find a team by its name (Case-Insensitive)
    private static void searchTeam() {
        if (teamCount == 0) {
            System.out.println("No teams available to search.");
            return;
        }

        System.out.print("Enter the Team Name to search: ");
        String searchName = scanner.nextLine();
        boolean found = false;

        // Loop & If-Else: Linear search across the array
        for (int i = 0; i < teamCount; i++) {
            if (teams[i].getTeamName().equalsIgnoreCase(searchName)) {
                System.out.println("\n[Team Found!]");
                teams[i].displayTeamDetails();
                found = true;
                break; // Stop loop once found
            }
        }

        if (!found) {
            System.out.println("Team '" + searchName + "' not found.");
        }
    }

    // Helper Method: Used to score/grade a team's project
    private static void scoreTeam() {
        if (teamCount == 0) {
            System.out.println("No teams available to score.");
            return;
        }

        System.out.print("Enter Team Name to score: ");
        String searchName = scanner.nextLine();
        
        for (int i = 0; i < teamCount; i++) {
            if (teams[i].getTeamName().equalsIgnoreCase(searchName)) {
                System.out.print("Enter project score (0.0 to 100.0): ");
                double score = scanner.nextDouble();
                
                // Validation: Score boundaries
                if (score < 0 || score > 100) {
                    System.out.println("Invalid Score! Must be between 0 and 100.");
                } else {
                    teams[i].setProjectScore(score);
                    System.out.println("Successfully updated score for team " + teams[i].getTeamName());
                }
                return;
            }
        }
        System.out.println("Team not found.");
    }

    // Method & If-Else: Identify and declare the winner
    private static void declareWinner() {
        if (teamCount == 0) {
            System.out.println("No teams registered to evaluate");
            return;
        }

        Team winner = null;
        double highestScore = -1;

        // Loop: Evaluating the highest score
        for (int i = 0; i < teamCount; i++) {
            if (teams[i].getProjectScore() > highestScore) {
                highestScore = teams[i].getProjectScore();
                winner = teams[i];
            }
        }

        // If-Else: Final Validation and output
        if (winner == null || highestScore == -1) {
            System.out.println("None of the teams have been scored yet.");
        } else {
            System.out.println("\nHACKATHON WINNER!!");
            System.out.println("Winner Team: " + winner.getTeamName());
            System.out.println("Project Title: " + winner.getProjectTitle());
            System.out.println("Winning Score: " + winner.getProjectScore());
            System.out.println("-----------------------------");
        }
    }
}