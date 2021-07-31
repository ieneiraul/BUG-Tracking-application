package repos.jdbc;

import model.Verificator;
import org.hibernate.SessionFactory;
import repos.RepositoryVerificator;

import java.sql.*;

public class RepositoryVerificatorDB implements RepositoryVerificator {
    private SessionFactory sessionFactory;
    static final String JDBC_DRIVER = "org.jdbc.RepositoryVerificatorDB";
    static final String DB_URL = "jdbc:sqlite:D:\\informatica\\facultate\\an II\\semestrul 2\\ISS\\exemple\\ISSAngajati\\monitorizareDB";

    public RepositoryVerificatorDB(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public boolean findById(int id) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("repos.jdbc.RepositoryVerificatorDB");
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();

            String sql = "SELECT user_id FROM Verificatori WHERE user_id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Verificator v = new Verificator();
                v.setId(rs.getInt("user_id"));
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
