
import java.sql.*;     // Use classes in java.sql package

public class JdbcInsertDeleteTest {    // Save as "JdbcUpdateTest.java"
    public static void main(String[] args) {
        try (
                // Step 1: Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/dbdemo?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "root", "123456"); // for MySQL only

                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();
        ) {
            // Step 3 & 4: Execute a SQL INSERT|DELETE statement via executeUpdate(),
            //   which returns an int indicating the number of rows affected.

            // DELETE records with id>=3000 and id<4000
            String sqlDelete = "delete from user where id >= 9 and id < 20";
            System.out.println("The SQL statement is: " + sqlDelete + "\n");  // Echo for debugging
            int countDeleted = stmt.executeUpdate(sqlDelete);
            System.out.println(countDeleted + " records deleted.\n");

            // INSERT a record
            String sqlInsert = "insert into user values (10, 'Gone Fishing', 'Kumar', 10)";
            System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
            int countInserted = stmt.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

            // INSERT multiple records
            sqlInsert = "insert into user values "
                    + "(12, 'Gone Fishing 2', 'Kumar', 12),"
                    + "(13, 'Gone Fishing 3', 'Kumar', 13)";
            System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
            countInserted = stmt.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

            // INSERT a partial record
 /*           sqlInsert = "insert into books (id, title, author) values (3004, 'Fishing 101', 'Kumar')";
            System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
            countInserted = stmt.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");
*/
            // Step 3 & 4: Issue a SELECT (via executeQuery()) to check the changes
            String strSelect = "select * from user";
            System.out.println("The SQL statement is: " + strSelect + "\n");  // Echo For debugging
            ResultSet rset = stmt.executeQuery(strSelect);
            while(rset.next()) {   // Move the cursor to the next row
                System.out.println(rset.getInt("id") + ", "
                        + rset.getString("name") + ", "
                        + rset.getString("email") + ", "

                        + rset.getInt("id"));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources
    }
}