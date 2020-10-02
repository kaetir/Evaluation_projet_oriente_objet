package controller;

public class Case {
    protected Pion pion = null;

    public Case() {

    }

    public void setPion(Pion pion) {
        this.pion = pion;
    }

    public Pion getPion() {
        return pion;
    }
}
