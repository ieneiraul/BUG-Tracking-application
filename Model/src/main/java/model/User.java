package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Useri")
public class User implements HasID<Integer>, Serializable {
    @Id
    @Column(name = "id")
    protected int id;

    @Column(name = "nume")
    protected String nume;

    @Column(name = "prenume")
    protected String prenume;

    @Column(name = "username")
    protected String username;

    @Column(name = "parola")
    protected String parola;

    public User(){
        this.username = "";
        this.parola = "";
        this.nume = "";
        this.prenume = "";
    }

    public User(int id, String nume, String prenume, String username, String parola) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "User{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }
}
