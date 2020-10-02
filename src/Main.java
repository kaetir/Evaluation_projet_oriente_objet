import controller.*;

class Main {
    static public void main(String[] argv) {
        System.out.println("Hello?");

        Individu a = new Pirate();
        Individu b = new MaitrePirate();

        System.out.println(a instanceof Mechant);
        System.out.println(a instanceof Gentil);
        System.out.println(a instanceof Pirate);
        System.out.println(a instanceof MaitrePirate);
        System.out.println(b instanceof Pirate);
        // Ptet j'ai pas fait attention,  mais super utile!
    }
}