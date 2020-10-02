import controller.*;

class Main {
    static public void main(String[] argv) {
        System.out.println("Hello?");

        Individual a = new Pirate();
        Individual b = new MasterPirate();

        System.out.println(a instanceof Mechant);
        System.out.println(a instanceof Good);
        System.out.println(a instanceof Pirate);
        System.out.println(a instanceof MasterPirate);
        System.out.println(b instanceof Pirate);
        // Ptet j'ai pas fait attention,  mais super utile!
    }
}