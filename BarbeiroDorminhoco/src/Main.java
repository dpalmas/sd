import java.util.ArrayList;

/**
 * @author davi on 4/19/21
 * @project BarbeiroDorminhoco
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

        
    }
}