import java.sql.*;

public class JdbcOldStyleSelect {




//  access Mysql by JDBC
// This is a stand alone program, just run,it will show you the data in user table


    public static void main(String[] args) {
        int x = 100;
        Connection con;
        Class<?> d;
        String driver ="com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/dbdemo";
        // String url="jdbc:mysql://localhost:3306/dbdemo?useUnicode=true&characterEncoding=utf8&useSSL=true";

        String user="root";
        String password="123456";
        System.out.println("Testing ... ");

        try {
            d = Class.forName(driver);
            // Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if (!con.isClosed()) {
                System.out.println("connected");
            }
            Statement statement = con.createStatement();
            String sql="select * from user";
            ResultSet rs= statement.executeQuery(sql);
            System.out.println("rs ...");
            String col0,col1,col2,col3;
            while (rs.next()){
                col0 =rs.getString("seqno");
                col1 =rs.getString("name");
                col2 =rs.getString("id");
                col3 =rs.getString("email");

                System.out.println(col0 + " " + col1+" " + col2 + " " +  col3);
            }
            rs.close();
            statement.close();
            con.close();

            // this is an old version style, for new one, will auto close the handler.
        } catch(ClassNotFoundException e ) {
            e.printStackTrace();
        }        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("connect end");
        }

        System.out.println("Test passed");
    }
}
