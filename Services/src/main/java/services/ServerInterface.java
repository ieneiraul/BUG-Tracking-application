package services;

import model.*;

import java.util.List;

public interface ServerInterface {
    User login(User user, ObserverInterface observerInterface1, ObserverInterface observerInterface2, ObserverInterface observerInterface3) throws MonitorizareException;
    void logout(User user);
    List<BUG> getAllBugsByStatus(StatusBug statusBug);
    void modificareStatusBUG(BUG bug, StatusBug status,int idProgramator);
    void addBUG(BUG bug);
}
