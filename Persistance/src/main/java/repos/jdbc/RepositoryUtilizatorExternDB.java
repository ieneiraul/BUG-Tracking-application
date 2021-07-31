package repos.jdbc;

import model.UtilizatorExtern;
import org.hibernate.SessionFactory;
import repos.RepositoryUtilizatorExtern;

import java.sql.*;

public class RepositoryUtilizatorExternDB implements RepositoryUtilizatorExtern {
    private SessionFactory sessionFactory;
    static final String JDBC_DRIVER = "org.jdbc.RepositoryUtilizatorExternDB";
    static final String DB_URL = "jdbc:sqlite:D:\\informatica\\facultate\\an II\\semestrul 2\\ISS\\exemple\\ISSAngajati\\monitorizareDB";

    public RepositoryUtilizatorExternDB(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public boolean findById(int id) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("repos.jdbc.RepositoryUtilizatorExternDB");
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();

            String sql = "SELECT user_id FROM UtilizatoriExterni WHERE user_id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                UtilizatorExtern u = new UtilizatorExtern();
                u.setId(rs.getInt("user_id"));
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
