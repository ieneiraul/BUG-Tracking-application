package model;

public class Programator extends User{
    private int nrBuguriRezolvate;

    public Programator() {
        nrBuguriRezolvate=0;
    }

    public Programator(int id, String nume, String prenume, String username, String parola, int nrBuguriRezolvate) {
        super(id, nume, prenume, username, parola);
        this.nrBuguriRezolvate = nrBuguriRezolvate;
    }

    public int getNrBuguriRezolvate() {
        return nrBuguriRezolvate;
    }

    public void setNrBuguriRezolvate(int nrBuguriRezolvate) {
        this.nrBuguriRezolvate = nrBuguriRezolvate;
    }

    @Override
    public String toString() {
        return "Programator{" +
                "nrBuguriRezolvate=" + nrBuguriRezolvate +
                ", id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
