import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author davi on 4/19/21
 * @project BarbeiroDorminhoco
 */

public class BarbeiroDorminhoco {
    boolean dormindo = true;
    boolean cortando = false;
    int quantidadeCadeiras = 8; 

    //int contadorClientesAtendidos = 0; 

    List<Cliente> clientesRecebidos = new ArrayList<Cliente>();
    LinkedBlockingQueue<Cliente> filaDeClientes = new LinkedBlockingQueue<>(); 

    public synchronized void Trabalhando()
    {
        try 
        {
            while(filaDeClientes.size() == 0)
            {
                System.out.println("Barbeiro: Não tem ninguem na fila, vou dormir um pouquinho");
                dormindo = true; 
                wait();
            }

            int idCliente = filaDeClientes.peek().GetId();

            if(filaDeClientes.size() > 0)
            {
                filaDeClientes.poll();
                cortando = true; 
                System.out.println("Barbeiro: Estou atendendo o Cliente " + idCliente);
                Thread.sleep(3000);

            }
            
            notifyAll();

        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

    public synchronized void ManejarCliente(Cliente cliente)
    {
        int idCliente = cliente.GetId();
        try 
        {
            if (!clientesRecebidos.contains(cliente))
            {
                if(filaDeClientes.size() < quantidadeCadeiras)
                {
                    filaDeClientes.offer(cliente);
                    clientesRecebidos.add(cliente);
                }
                else
                {
                    System.out.println("Cliente " + idCliente + ": Não existe cadeira disponível, vou embora");
                    clientesRecebidos.add(cliente);
                    Thread.sleep(3000);
                }
            }

            while (filaDeClientes.size() < quantidadeCadeiras)
            {
                if(dormindo)
                {
                    System.out.println("Cliente " + idCliente + ": Acorda aí barbeiro");
                    System.out.println("Barbeiro: Back to work");
                    notify();
                    dormindo = false; 
                }
                //System.out.println("antes do wait do cliente");
                wait();
            }

            //notifyAll();
            
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
}
