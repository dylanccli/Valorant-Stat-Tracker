package com.dylan.valoranttrackerdatabaseapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ValorantTrackerDatabaseApplication {

    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\dylan\\OneDrive\\Desktop\\File\\ELE 8th Semester\\COE848\\DylanLi_Lab5\\ValorantTrackerDatabaseApplication\\Valorant.db";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            System.out.println("Welcome to the Valorant Tracker Database Application:");
            while (true) {
                System.out.println("\nSelect an action:");
                // Update menu options for Valorant entities
                System.out.println("1. List Players");
                System.out.println("2. List Teams");
                System.out.println("3. List Coaches");
                System.out.println("4. List Guns");
                System.out.println("5. List Matches");
                System.out.println("6. Add a new Player");
                System.out.println("7. Add a new Team");
                System.out.println("8. Add a new Coach");
                System.out.println("9. Add a new Gun");
                System.out.println("10. Add a new Match");
                System.out.println("11. Common Query Questions");
                System.out.println("12. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        listEntities(conn, "Player");
                        break;
                    case 2:
                        listEntities(conn, "Team");
                        break;
                    case 3:
                        listEntities(conn, "Coach");
                        break;
                    case 4:
                        listEntities(conn, "Gun");
                        break;
                    case 5:
                        listEntities(conn, "Match");
                        break;
                    case 6:
                        addPlayer(conn);
                        break;
                    case 7:
                        addTeam(conn);
                        break;
                    case 8:
                        addCoach(conn);
                        break;
                    case 9:
                        addGun(conn);
                        break;
                    case 10:
                        addMatch(conn);
                        break;
                    case 11:
                        handleQueriesSubmenu(conn);
                        break;
                    case 12:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
    // List the Tables
    private static void listEntities(Connection conn, String tableName) throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getString(1) + ": " + rs.getString(2));
            }
        }
    }
    // ADD PLAYER
    private static void addPlayer(Connection conn) throws SQLException {
        System.out.print("Enter player height in cm: ");
        double playerHeight = scanner.nextDouble();

        System.out.print("Enter player age: ");
        int playerAge = scanner.nextInt();
        scanner.nextLine(); // Handle next line character

        System.out.print("Enter player name: ");
        String playerName = scanner.nextLine();

        System.out.print("Enter player earnings: ");
        int playerEarnings = scanner.nextInt();
        scanner.nextLine(); // Handle next line character

        System.out.print("Enter player accolades: ");
        String playerAccolades = scanner.nextLine();

        System.out.print("Enter player role: ");
        String playerRole = scanner.nextLine();

        System.out.print("Enter player region: ");
        String playerRegion = scanner.nextLine();

        System.out.print("Enter player stats: ");
        double playerStats = scanner.nextDouble();
        scanner.nextLine(); // Handle next line character

        System.out.print("Enter player IGN: ");
        String playerIGN = scanner.nextLine();

        String sql = "INSERT INTO player (playerHeight, playerAge, playerName, playerEarnings, playerAccolades, playerRole, playerRegion, playerStats, playerIGN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, playerHeight);
            pstmt.setInt(2, playerAge);
            pstmt.setString(3, playerName);
            pstmt.setInt(4, playerEarnings);
            pstmt.setString(5, playerAccolades);
            pstmt.setString(6, playerRole);
            pstmt.setString(7, playerRegion);
            pstmt.setDouble(8, playerStats);
            pstmt.setString(9, playerIGN);
            pstmt.executeUpdate();
            System.out.println("Player added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding the player: " + e.getMessage());
        }
        
        /*
        System.out.print("Enter team name the player is associated with: ");
        String teamName = scanner.nextLine();

        // Check if the team exists
        String teamCheckSql = "SELECT teamID FROM team WHERE teamName = ?";
        try (PreparedStatement pstmtCheck = conn.prepareStatement(teamCheckSql)) {
            pstmtCheck.setString(1, teamName);
            ResultSet rs = pstmtCheck.executeQuery();
            if (!rs.isBeforeFirst()) { // If the team does not exist
                System.out.println("No team found with the name " + teamName + ".");
            } else {
                rs.next(); // Move to the first record
                int teamId = rs.getInt("teamID");

                // Assuming you have the player ID (you would get this from the player insertion step)
                // For simplicity, let's assume it's stored in a variable named `playerId`
                int playerId = // the logic to retrieve or store the last inserted player ID goes here;

                // Update the player record with the team ID (if using a direct relationship)
                String updateSql = "UPDATE player SET teamID = ? WHERE playerID = ?";
                try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateSql)) {
                    pstmtUpdate.setInt(1, teamId);
                    pstmtUpdate.setInt(2, playerId);
                    pstmtUpdate.executeUpdate();
                    System.out.println("Player associated with team successfully.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error associating the player with the team: " + e.getMessage());
        }
*/
    }
    // ADD TEAM
    private static void addTeam(Connection conn) throws SQLException {
        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();

        System.out.print("Enter team rankings: ");
        int teamRankings = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter team accolades (Press enter if none): ");
        String teamAccolades = scanner.nextLine();

        System.out.print("Enter number of losses: ");
        int teamLoss = scanner.nextInt();

        System.out.print("Enter number of wins: ");
        int teamWin = scanner.nextInt();

        System.out.print("Enter current streak: ");
        int teamStreak = scanner.nextInt();

        String sql = "INSERT INTO team (teamName, teamRankings, teamAccolades, teamLoss, teamWin, teamStreak) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teamName);
            pstmt.setInt(2, teamRankings);
            pstmt.setString(3, teamAccolades);
            pstmt.setInt(4, teamLoss);
            pstmt.setInt(5, teamWin);
            pstmt.setInt(6, teamStreak);
            pstmt.executeUpdate();
            System.out.println("Team added successfully.");
        }
    }
    // ADD COACH 
    private static void addCoach(Connection conn) throws SQLException {
        System.out.print("Enter coach age: ");
        int coachAge = scanner.nextInt();
        scanner.nextLine(); // Handle the next line character

        System.out.print("Enter coach name: ");
        String coachName = scanner.nextLine();

        String sql = "INSERT INTO coach (coachAge, coachName) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, coachAge);
            pstmt.setString(2, coachName);
            pstmt.executeUpdate();
            System.out.println("Coach added successfully.");
        }
    }
    private static void addGun(Connection conn) throws SQLException {
        System.out.print("Enter gun type: ");
        String gunType = scanner.nextLine();

        System.out.print("Enter gun cost: ");
        int gunCost = scanner.nextInt();
        scanner.nextLine(); // Handle the next line character

        System.out.print("Enter gun skin (Press enter if none): ");
        String gunSkin = scanner.nextLine();

        System.out.print("Enter gun damage: ");
        double gunDamage = scanner.nextDouble();
        scanner.nextLine(); // Handle the next line character

        System.out.print("Enter gun name: ");
        String gunName = scanner.nextLine();

        String sql = "INSERT INTO gun (gunType, gunCost, gunSkin, gunDamage, gunNAME) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, gunType);
            pstmt.setInt(2, gunCost);
            pstmt.setString(3, gunSkin);
            pstmt.setDouble(4, gunDamage);
            pstmt.setString(5, gunName);
            pstmt.executeUpdate();
            System.out.println("Gun added successfully.");
        }
    }
    private static void addMatch(Connection conn) throws SQLException {
        System.out.print("Enter match date (YYYY-MM-DD): ");
        String matchDate = scanner.nextLine();

        System.out.print("Enter match score: ");
        String matchScore = scanner.nextLine();

        System.out.print("Enter match time: ");
        String matchTime = scanner.nextLine();

        String sql = "INSERT INTO match (matchDate, matchScore, matchTime) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, matchDate);
            pstmt.setString(2, matchScore);
            pstmt.setString(3, matchTime);
            pstmt.executeUpdate();
            System.out.println("Match added successfully.");
        }
    }
    
    // ----------------------------------------------------- QUERY QUESTIONS -------------------------------------------------//
    private static void handleQueriesSubmenu(Connection conn) throws SQLException {
        while (true) {
            System.out.println("\n--- Queries Submenu ---");
            System.out.println("1. Find the team of a Player");
            System.out.println("2. Find the team of a coach");
            System.out.println("3. Find the team with the most wins");
            System.out.println("4. Find the player with the highest rating");
            System.out.println("5. Find the highest paid player");
            System.out.println("6. Find the main role of the player");
            System.out.println("7. Find the gun price");
            System.out.println("8. Find the team with the most losses");
            System.out.println("9. Find the team with the highest rank");
            System.out.println("10. Find the gun that does the most damage");
            System.out.println("11. Return to main menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    findPlayersTeams(conn);
                    break;
                case 2:
                    findCoachesTeam(conn);
                    break;
                case 3:
                    findTeamWithMostWins(conn);
                    break;
                case 4:
                    findPlayerWithHighestRating(conn);
                    break;
                case 5:
                    findPlayerWithHighestEarnings(conn);
                    break;
                case 6:
                    findPlayerRole(conn);
                    break;
                case 7:
                    findGunPrice(conn);
                    break;
                case 8:
                    findTeamWithMostLosses(conn);
                    break;
                case 9: 
                    findHighestRankedTeam(conn);
                    break;
                case 10:
                    findGunWithMostDamage(conn);
                    break;
                case 11:
                    return; // Exit the submenu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    // Finding a players team
    private static void findPlayersTeams(Connection conn) throws SQLException {
        System.out.print("Enter player ID: ");
        int playerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        String sql = "SELECT t.teamName FROM team t JOIN PlayerTeam pt ON t.teamID = pt.teamID JOIN player p ON pt.playerID = p.playerID WHERE p.playerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("No teams found for the given player ID.");
                } else {
                    while (rs.next()) {
                        String teamName = rs.getString("teamName");
                        System.out.println("Team: " + teamName);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding the player's teams: " + e.getMessage());
        }
    }
        // Finding a coaches team
    private static void findCoachesTeam(Connection conn) throws SQLException {
        System.out.print("Enter coach ID: ");
        int coachId = scanner.nextInt();
        scanner.nextLine();
        String sql = "SELECT t.teamName FROM TeamCoach tc " +
                     "JOIN team t ON tc.teamID = t.teamID " +
                     "JOIN coach c ON tc.coachID = c.coachID " +
                     "WHERE c.coachID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, coachId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("No team found for the given coach ID.");
                } else {
                    System.out.println("The coach with an ID of " + coachId + " coaches for team:");
                    while (rs.next()) {
                        String teamName = rs.getString("teamName");
                        System.out.println("- " + teamName);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding the coach's team(s): " + e.getMessage());
        }
    }
    
    // Find team with most wins
    private static void findTeamWithMostWins(Connection conn) throws SQLException {
        String sql = "SELECT teamName, teamWin FROM team ORDER BY teamWin DESC LIMIT 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String teamName = rs.getString("teamName");
                int teamWins = rs.getInt("teamWin");
                System.out.println("The team with the most wins is " + teamName + " with " + teamWins + " wins.");
            } else {
                System.out.println("No team data found.");
            }
        } catch (SQLException e) {
            System.out.println("Error finding the team with the most wins: " + e.getMessage());
        }
    }
    
    // Find player with Highest Rating 
    private static void findPlayerWithHighestRating(Connection conn) throws SQLException {
        String sql = "SELECT playerIGN, playerStats FROM player ORDER BY playerStats DESC LIMIT 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String playerIGN = rs.getString("playerIGN");
                double playerRating = rs.getDouble("playerStats");
                System.out.println("The player with the highest rating is " + playerIGN + " with a rating of " + playerRating + ".");
            } else {
                System.out.println("No player data found.");
            }
        } catch (SQLException e) {
            System.out.println("Error finding the player with the highest rating: " + e.getMessage());
        }
    }
    
    // Find player with Highest Earnings
    private static void findPlayerWithHighestEarnings(Connection conn) throws SQLException {
        String sql = "SELECT playerIGN, playerEarnings FROM player ORDER BY playerEarnings DESC LIMIT 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String playerIGN = rs.getString("playerIGN");
                int playerEarnings = rs.getInt("playerEarnings");
                System.out.println("The player with the highest earnings is " + playerIGN + " with earnings of $" + playerEarnings + ".");
            } else {
                System.out.println("No player data found.");
            }
        } catch (SQLException e) {
            System.out.println("Error finding the player with the highest earnings: " + e.getMessage());
        }
    }
    
    // Find player Role 
    private static void findPlayerRole(Connection conn) throws SQLException {
        System.out.print("Enter player IGN: ");
        String playerIGN = scanner.nextLine();
        String sql = "SELECT playerName, playerRole FROM player WHERE playerIGN = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, playerIGN);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String playerName = rs.getString("playerName");
                    String playerRole = rs.getString("playerRole");
                    System.out.println(playerIGN + " (" + playerName + ") plays as a " + playerRole + ".");
                } else {
                    System.out.println("No player found with the IGN " + playerIGN + ".");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding the player's role by IGN: " + e.getMessage());
        }
    }
    
    // Finding the price of a gun
    private static void findGunPrice(Connection conn) throws SQLException {
        System.out.print("Enter gun name: ");
        String gunName = scanner.nextLine();
        String sql = "SELECT gunCost FROM gun WHERE gunName = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, gunName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int gunCost = rs.getInt("gunCost");
                    System.out.println("The cost of " + gunName + " is $" + gunCost + ".");
                } else {
                    System.out.println("No gun found with the name " + gunName + ".");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding the cost of the gun: " + e.getMessage());
        }
    }
    
    // Finding the team with the most losses
    private static void findTeamWithMostLosses(Connection conn) throws SQLException {
        String sql = "SELECT teamName, teamLoss FROM team ORDER BY teamLoss DESC LIMIT 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String teamName = rs.getString("teamName");
                int teamLoss = rs.getInt("teamLoss");
                System.out.println("The team with the most losses is " + teamName + " with " + teamLoss + " losses.");
            } else {
                System.out.println("No team data found.");
            }
        } catch (SQLException e) {
            System.out.println("Error finding the team with the most losses: " + e.getMessage());
        }
    }
    
    // Finding the highest ranked team
    private static void findHighestRankedTeam(Connection conn) throws SQLException {
        String sql = "SELECT teamName, teamRankings FROM team ORDER BY teamRankings ASC LIMIT 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String teamName = rs.getString("teamName");
                int teamRankings = rs.getInt("teamRankings");
                System.out.println("The highest-ranked team is " + teamName + " with a ranking of " + teamRankings + ".");
            } else {
                System.out.println("No team data found.");
            }
        } catch (SQLException e) {
            System.out.println("Error finding the highest-ranked team: " + e.getMessage());
        }
    }
    
    // Finding the gun that does the most damage
    private static void findGunWithMostDamage(Connection conn) throws SQLException {
        String sql = "SELECT gunName, gunDamage FROM gun ORDER BY gunDamage DESC LIMIT 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String gunName = rs.getString("gunName");
                double gunDamage = rs.getDouble("gunDamage");
                System.out.println("The gun with the most damage is " + gunName + " with a damage of " + gunDamage + ".");
            } else {
                System.out.println("No gun data found.");
            }
        } catch (SQLException e) {
            System.out.println("Error finding the gun with the most damage: " + e.getMessage());
        }
    }
}
