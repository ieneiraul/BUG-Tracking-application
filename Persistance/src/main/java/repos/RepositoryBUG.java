package repos;

import model.BUG;
import model.StatusBug;

import java.util.List;

public interface RepositoryBUG {
    void addBUG(BUG bug);
    void updateBUG(int idBUG,StatusBug statusBug, int idProgramator);
    int countBuguri();
    List<BUG> findAllByStatus(StatusBug statusBug);
}
