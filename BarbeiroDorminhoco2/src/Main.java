import java.util.ArrayList;

/**
 * @author davi on 5/2/21
 * @project BarbeiroDorminhoco2
 */

public class Main {

    public static ArrayList<Cadeira> cadeiras = new ArrayList<>();

    public static void main(String[] args) {
        int nCadeiras = 3;
        int nClientes = 5;
        for (int i = 0; i < nCadeiras; i++) {
            Cadeira cadeira = new Cadeira();
            cadeiras.add(cadeira);
        }
        BarbeiroDorminhoco barbeiro = new BarbeiroDorminhoco();
        Thread cons = new Thread(barbeiro, "Barbeiro");

        for (int i = 0; i < nClientes; i++) {
            Thread prod = new Thread(new Cliente(barbeiro, i), "Cliente " + i);
            prod.start();
        }
        cons.start();

    }
}