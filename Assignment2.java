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
		String sqlText1 = "INSERT INTO a2.player (pid, pname, globalrank, cid) VALUES (" + pid + ", \'" + pname + "\', " + globalRank + ", " + cid + ");";
        try {
			ps = connection.prepareStatement(sqlText1);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public int getChampions(int pid) {
        String sqlText = "SELECT count(c.pid) AS numofChamps FROM a2.champion c WHERE c.pid = " + pid + ";";
		{
			try {
				ps = connection.prepareStatement(sqlText);
				rs = ps.executeQuery();

				int numChamps = 0;
				if (rs.next() == true) {
					numChamps = rs.getInt("numofChamps");
				}

        ps.close();
				rs.close();
				return numChamps;

			} catch (SQLException e) {
				return 0;
			}

		}
    }

    public String getCourtInfo(int courtid) {
        String sqlText = "SELECT c.courtid, c.courtname, c.capacity, c.tournamentname FROM a2.court c WHERE c.courtid = "
				+ courtid + ";";
		{
			try {
				ps = connection.prepareStatement(sqlText);
				rs = ps.executeQuery();

				String courtInfo = "";
				String cid = "";
				String cname = "";
				String capacity = "";
				String tournamentname = "";
				if (rs.next() == true) {
					cid = rs.getString(1);
					cname = rs.getString(2);
					capacity = rs.getString(3);
					tournamentname = rs.getString(4);

					courtInfo = (cid + ":" + cname + ":" + capacity + ":" + tournamentname);

          ps.close();
					rs.close();
					return courtInfo;
				} else {
					rs.close();
					return "";
				}

			} catch (SQLException e) {
				return "";

			}

		}
    }

    public boolean chgRecord(int pid, int year, int wins, int losses) {
        String sqlText = "UPDATE a2.record SET wins = " + wins + ", losses = " + losses + " WHERE pid = " + pid
				+ "and year = " + year + ";";
		{
			try {
				ps = connection.prepareStatement(sqlText);
				ps.executeUpdate();
				ps.close();
				return true;

			} catch (SQLException e) {
				return false;
			}

		}
    }

    public boolean deleteMatcBetween(int p1id, int p2id) {
        String sqlText = "DELETE FROM a2.event WHERE (event.winid = " + p1id + " AND event.lossid = " + p2id
				+ ") OR (event.winid = " + p2id + " AND event.lossid = " + p1id + ");";

		try {
			ps = connection.prepareStatement(sqlText);
			ps.executeUpdate();
			ps.close();
			return true;

		} catch (SQLException e) {
			return false;
		}

    }

    public String listPlayerRanking() {
        String sqlText = "SELECT p.pname, p.globalrank FROM a2.player AS p ORDER BY globalrank DESC;";
		{
			try {
				ps = connection.prepareStatement(sqlText);
				rs = ps.executeQuery();

				String rankList = "";
				String pName = "";
				String pRank = "";
				while (rs.next() == true) {
					pName = rs.getString(1);
					pRank = rs.getString(2);

					rankList = rankList + (pName + ":" + pRank + "\n");
				}

        ps.close();
				rs.close();
				return rankList;

			} catch (SQLException e) {
				return "";

			}

		}
    }

    public int findTriCircle() {
        String sqlText = "SELECT count(A.pid) AS numTris FROM a2.event AS A, a2.event AS B, a2.event AS C WHERE A.lossid = B.winid AND B.lossid = C.winid AND C.lossid = A.winid";
		{
			try {
				ps = connection.prepareStatement(sqlText);
				rs = ps.executeQuery();

				int numTris = 0;
				if (rs.next() == true) {
					numTris = rs.getInt("numTris");
				}

        ps.close();
				rs.close();
				return numTris;

			} catch (SQLException e) {
				return 0;
			}

		}
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
