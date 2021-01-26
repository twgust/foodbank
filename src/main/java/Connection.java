import java.sql.DriverManager;

public class Connection
{
    public Connection()
    {
        try
        {
            System.out.println("Trying to connect");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String user = "user";
            String pw = "123";

            String dbURL = "jdbc:sqlserver://localhost:1433;database=food";

            java.sql.Connection conn = DriverManager.getConnection(dbURL, user, pw);

            System.out.println("Connect successful");

            if (conn != null)
            {
                System.out.println("Connected");
            }
        }
        catch (Exception e)
        {
            System.out.println("Create connection: " + e.getMessage());
        }
    }
}
