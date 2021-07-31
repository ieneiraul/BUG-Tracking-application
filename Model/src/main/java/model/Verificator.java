package model;

public class Verificator extends User{
    private int nrBuguriRaportate;

    public Verificator() {
        nrBuguriRaportate=0;
    }

    public Verificator(int id, String nume, String prenume, String username, String parola, int nrBuguriRaportate) {
        super(id, nume, prenume, username, parola);
        this.nrBuguriRaportate = nrBuguriRaportate;
    }

    public int getNrBuguriRaportate() {
        return nrBuguriRaportate;
    }

    public void setNrBuguriRaportate(int nrBuguriRaportate) {
        this.nrBuguriRaportate = nrBuguriRaportate;
    }

    @Override
    public String toString() {
        return "Verificator{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", username='" + username + '\'' +
                ", nrBuguriRaportate=" + nrBuguriRaportate +
                '}';
    }
}
