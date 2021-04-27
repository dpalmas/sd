/**
 * @author davi on 4/19/21
 * @project BarbeiroDorminhoco
 */

public class Cliente {
    int id;
    boolean atendido = false;
    boolean esperando = false;
    
    Cadeira cadeira;
    BarbeiroDorminhoco barbeiro;

    Cliente(BarbeiroDorminhoco barbeiro, int id) {
        this.barbeiro = barbeiro;
        this.id = id;
    }
}
