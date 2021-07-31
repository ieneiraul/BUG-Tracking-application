package repos;

import model.User;

public interface RepositoryUser {
    User verificareDateLogin(String username, String parola);
    User findById(int id);
}
