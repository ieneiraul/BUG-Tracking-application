package repos.jdbc;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repos.RepositoryUser;

public class RepositoryUserDB implements RepositoryUser {
    private SessionFactory sessionFactory;

    public RepositoryUserDB(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User verificareDateLogin(String uname, String pass) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                User user = session.createQuery("from User where username = :u and parola = :p", User.class)
                        .setParameter("u", uname)
                        .setParameter("p", pass)
                        .getSingleResult();
                tx.commit();
                return user;
            }catch(RuntimeException ex){
                if(tx != null)
                    tx.rollback();
                System.out.println(ex.getMessage());
            }
        } catch (Exception e){
        }
        return null;
    }

    @Override
    public User findById(int id) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                User user = session.createQuery("from User where id = :id", User.class).setParameter("id", id)
                        .getSingleResult();
                tx.commit();
                return user;
            }catch (RuntimeException ex){
                if(tx != null)
                    tx.rollback();
            }
        }
        return null;
    }
}
