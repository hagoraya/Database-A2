import java.sql.*;

public class Assignment2 {

    // A connection to the database
    Connection connection;

    // Statement to run queries
    Statement sql;

    // Prepared Statement
    PreparedStatement ps;

    // Resultset for the query
    ResultSet rs;

    public static final String myDriver = "org.postgresql.Driver";

    // CONSTRUCTOR
    Assignment2() {
        System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");

        try {
            // Load the JDBC driver
            Class.forName(myDriver);

        } catch (ClassNotFoundException e) {
            return;
        }
    }

    // Using the input parameters, establish a connection to be used for this
    // session. Returns true if connection is sucessful
    public boolean connectDB(String URL, String username, String password) {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            return true;

        } catch (SQLException e) {
            return false;
        }

    }

    // Closes the connection. Returns true if closure was sucessful
    public boolean disconnectDB() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean insertPlayer(int pid, String pname, int globalRank, int cid) {
        String sqlText = "INSERT INTO a2.player (pid, pname, globalRank, cid) VALUES (" + pid + ", \'" + pname + "\',"
                + globalRank + "," + cid + ");";
        try {
            ps = connection.prepareStatement(sqlText);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public int getChampions(int pid) {
        return 0;
    }

    public String getCourtInfo(int courtid) {
        return "";
    }

    public boolean chgRecord(int pid, int year, int wins, int losses) {
        return false;
    }

    public boolean deleteMatcBetween(int p1id, int p2id) {
        return false;
    }

    public String listPlayerRanking() {
        return "";
    }

    public int findTriCircle() {
        return 0;
    }

    public boolean updateDB() {
        String updb = "DROP TABLE IF EXISTS a2.champtionPlayers;"
                + "CREATE TABLE a2.championPlayers (pid INTEGER, pname VARCHAR, nchampions INTEGER);"
                + "INSERT INTO a2.championPlayers (" + "SELECT player.pid, pname, count(tid)" + "FROM a2.player"
                + "INNER JOIN a2.champion ON player.pid = champion.pid" + "GROUP BY player.pid );";

        try {
            sql = connection.createStatement();
            sql.execute(updb);
            sql.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
