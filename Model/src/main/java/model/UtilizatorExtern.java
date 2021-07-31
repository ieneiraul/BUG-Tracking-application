package model;

public class UtilizatorExtern extends User{
    private int nrBuguriRaportate;

    public UtilizatorExtern() {
        nrBuguriRaportate=0;
    }

    public UtilizatorExtern(int id, String nume, String prenume, String username, String parola, int nrBuguriRaportate) {
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
        return "UtilizatorExtern{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", username='" + username + '\'' +
                ", nrBuguriRaportate=" + nrBuguriRaportate +
                '}';
    }
}
