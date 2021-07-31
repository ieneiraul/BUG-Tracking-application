package repos.jdbc;

import model.Programator;
import org.hibernate.SessionFactory;
import repos.RepositoryProgramator;

import java.sql.*;

public class RepositoryProgramatorDB implements RepositoryProgramator {
    private SessionFactory sessionFactory;
    static final String JDBC_DRIVER = "org.jdbc.RepositoryProgramatorDB";
    static final String DB_URL = "jdbc:sqlite:D:\\informatica\\facultate\\an II\\semestrul 2\\ISS\\exemple\\ISSAngajati\\monitorizareDB";

    public RepositoryProgramatorDB(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public boolean findById(int id) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("repos.jdbc.RepositoryProgramatorDB");
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();

            String sql = "SELECT user_id FROM Programatori WHERE user_id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Programator programator = new Programator();
                programator.setId(rs.getInt("user_id"));
                return true;
            }
            rs.close();
        } catch(Exception se){
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException ignored){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return false;
    }

}
