package server;

import model.*;
import repos.jdbc.*;
import services.MonitorizareException;
import services.ObserverInterface;
import services.ServerInterface;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImplementation implements ServerInterface {
    private Map<String, ObserverInterface> loggedUsers;
    private RepositoryUserDB repoUserDB;
    private RepositoryUtilizatorExternDB repoUtilizatorExternDB;
    private RepositoryVerificatorDB repoVerificatorDB;
    private RepositoryProgramatorDB repoProgramatorDB;
    private RepositoryBUGDB repoBUGDB;


    public ServerImplementation(RepositoryUserDB repoUserDB, RepositoryUtilizatorExternDB repoUtilizatorExternDB, RepositoryVerificatorDB repoVerificatorDB, RepositoryProgramatorDB repoProgramatorDB, RepositoryBUGDB repoBUGDB) {
        this.repoUserDB = repoUserDB;
        this.repoUtilizatorExternDB = repoUtilizatorExternDB;
        this.repoVerificatorDB = repoVerificatorDB;
        this.repoProgramatorDB = repoProgramatorDB;
        this.repoBUGDB = repoBUGDB;
        this.loggedUsers = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized User login(User user, ObserverInterface observerInterface1, ObserverInterface observerInterface2, ObserverInterface observerInterface3) throws MonitorizareException {
        User foundUser = repoUserDB.verificareDateLogin(user.getUsername(), user.getParola());
        if (foundUser == null) {
            System.out.println("Error");
            throw new MonitorizareException("null");
        }
        System.out.println("da");
        if(this.loggedUsers.containsKey(user.getUsername())) {
            throw new MonitorizareException("User deja logat");
        } else {
            if (repoProgramatorDB.findById(foundUser.getId())) {
                System.out.println("pana aici");
                loggedUsers.put(foundUser.getUsername(), observerInterface1);
                System.out.println("logged users: " + this.loggedUsers.size());
                return new Programator(foundUser.getId(), foundUser.getNume(), foundUser.getPrenume(), foundUser.getUsername(), foundUser.getParola(),0);
            } else if (repoVerificatorDB.findById(foundUser.getId())) {
                loggedUsers.put(foundUser.getUsername(), observerInterface2);
                System.out.println("logged users: " + this.loggedUsers.size());
                return new Verificator(foundUser.getId(), foundUser.getNume(), foundUser.getPrenume(), foundUser.getUsername(), foundUser.getParola(),0);
            } else if (repoUtilizatorExternDB.findById(foundUser.getId())) {
                loggedUsers.put(foundUser.getUsername(), observerInterface3);
                System.out.println("logged users: " + this.loggedUsers.size());
                return new UtilizatorExtern(foundUser.getId(), foundUser.getNume(), foundUser.getPrenume(), foundUser.getUsername(), foundUser.getParola(),0);
            }
        }
        return new User();
    }

    @Override
    public void logout(User user){
        this.loggedUsers.remove(user.getUsername());
        System.out.println("logged users: " + this.loggedUsers.size());
    }

    @Override
    public List<BUG> getAllBugsByStatus(StatusBug statusBug) {
        return repoBUGDB.findAllByStatus(statusBug);
    }

    @Override
    public void modificareStatusBUG(BUG bug, StatusBug status, int idProgramator) {
        repoBUGDB.updateBUG(bug.getId(),status,idProgramator);
    }

    @Override
    public void addBUG(BUG bug) {
        repoBUGDB.addBUG(bug);
    }


}
