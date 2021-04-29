import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author davi on 4/19/21
 * @project BarbeiroDorminhoco
 */

public class BarbeiroDorminhoco {
    boolean dormindo = false;
    boolean cortando = false;
    //int contadorClientesAtendidos = 0; 

    List<Cliente> clientesAtendidos = new ArrayList<Cliente>();
    PriorityBlockingQueue<Cliente> filaDeClientes = new PriorityBlockingQueue<>(); 
    
    Cliente cliente;

    BarbeiroDorminhoco() 
    {

    }

    public synchronized void Trabalhando()
    {
        try 
        {
            while(filaDeClientes.size() == 0)
            {
                System.out.println("Fila de clientes vazia, sleep time");
                dormindo = true; 
                wait();
                System.out.println("Back to work");
            }

            if(filaDeClientes.size() > 0)
            {
                filaDeClientes.poll();
                cortando = true; 
                System.out.println("Atendendo...");
                Thread.sleep(3000);

            }

            notifyAll();

        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    // aguardando vez
        //if cliente não foi atendido 
            // if a quantidade de clientes em espera for menor que a quantidade de cadeiras disponiveis 
                // atendo o cliente 
            // else
                // cliente vai embora 

        // while a quantidade de clientes em espera for menor que a quantidade de cadeiras disponiveis 
            // if dormindo
                // acorda e atende o cliente 

}
