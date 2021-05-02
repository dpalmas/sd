/**
 * @author davi on 4/19/21
 * @project BarbeiroDorminhoco
 */

public class Cliente extends Thread {
    int id;
    boolean atendido = false;
    boolean esperando = false;
    
    Cadeira cadeira;
    BarbeiroDorminhoco barbeiroDorminhoco;

    Cliente(BarbeiroDorminhoco barbeiroDorminhoco, int id) {
        this.barbeiroDorminhoco = barbeiroDorminhoco;
        this.id = id;
    }

    @Override
    public void run() 
    {
        // while (true) 
        // {
            try 
            {
                Thread.sleep(3000);
                barbeiroDorminhoco.ManejarCliente(this);
            } catch (Exception e) { }
        //}
    }

    public int GetId()
    {
        return id; 
    }
}
