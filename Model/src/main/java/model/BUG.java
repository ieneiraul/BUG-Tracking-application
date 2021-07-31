package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Buguri")
public class BUG implements HasID<Integer>, Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nume")
    private String nume;

    @Column(name = "descriere")
    private String descriere;

    @Column(name = "status")
    private String status;

    @Column(name = "id_creator")
    private int id_creator;

    @Column(name = "id_programator")
    private int id_programator;

    public BUG() {}

    public BUG(String nume, String descriere, String statusBug, int id_creator, int id_programator) {
        this.id = 0;
        this.nume = nume;
        this.descriere = descriere;
        this.status=statusBug;
        this.id_creator = id_creator;
        this.id_programator = id_programator;
    }

    @Override
    public Integer getId() {
        return id;
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

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getId_creator() {
        return id_creator;
    }

    public void setId_creator(int id_creator) {
        this.id_creator = id_creator;
    }

    public int getId_programator() {
        return id_programator;
    }

    public void setId_programator(int id_programator) {
        this.id_programator = id_programator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BUG{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", descriere='" + descriere + '\'' +
                ", id_creator=" + id_creator +
                ", id_programator=" + id_programator +
                '}';
    }
}
