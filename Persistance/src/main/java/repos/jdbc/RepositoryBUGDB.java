package repos.jdbc;

import model.BUG;
import model.StatusBug;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repos.RepositoryBUG;

import java.util.List;

public class RepositoryBUGDB implements RepositoryBUG {
    private SessionFactory sessionFactory;

    public RepositoryBUGDB(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addBUG(BUG bug) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                bug.setId(countBuguri() + 1);
                session.save(bug);
                tx.commit();
            } catch (RuntimeException ex){
                if(tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void updateBUG(int idBUG,StatusBug statusBug, int idProgramator) {
        String status="";
        if(statusBug==StatusBug.INREGISTRAT) status="INREGISTRAT";
        else if(statusBug==StatusBug.VERIFICAT) status="VERIFICAT";
        else if(statusBug==StatusBug.REZOLVAT) status="REZOLVAT";
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Query query = session.createQuery("UPDATE BUG SET status = :status, id_programator = :id_programator  WHERE id = :id")
                        .setParameter("id", idBUG)
                        .setParameter("id_programator", idProgramator)
                        .setParameter("status", status);
                query.executeUpdate();
                tx.commit();
            } catch (RuntimeException ex){
                if(tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public int countBuguri() {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<BUG> buguri = session.createQuery("from BUG", BUG.class).list();
                tx.commit();
                return buguri.size();
            } catch (RuntimeException ex){
                if(tx != null)
                    tx.rollback();
            }
        }
        return 0;
    }

    @Override
    public List<BUG> findAllByStatus(StatusBug statusBug) {
        String status="";
        if(statusBug==StatusBug.INREGISTRAT) status="INREGISTRAT";
        else if(statusBug==StatusBug.VERIFICAT) status="VERIFICAT";
        else if(statusBug==StatusBug.REZOLVAT) status="REZOLVAT";
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<BUG> buguri = session.createQuery("from BUG where status = :status", BUG.class).setParameter("status", status).list();
                tx.commit();
                return buguri;
            } catch (RuntimeException ex){
                if(tx != null)
                    tx.rollback();
            }
        }
        return null;
    }
}
