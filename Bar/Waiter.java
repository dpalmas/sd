import java.util.concurrent.LinkedBlockingQueue;

public class Waiter extends Thread 
{
    LinkedBlockingQueue<Client> clientsQueue = new LinkedBlockingQueue<>();

    Bar bar = new Bar();
    Client client1 = new Client();
    Client client2 = new Client();
    Client client3 = new Client();
    
    @Override
    public void run() 
    {
        // while(true) // enquanto tem clientes no bar 
        // {
            try 
            {
                DeliverDrink();
            } 
            catch (InterruptedException e)            
            {
                e.printStackTrace();
            }
        //}
    }

    synchronized public void noticabrasilllllllll() throws InterruptedException {
        Thread.sleep(3000);
        notifyAll();
    }

    void CheckOrders()
    {
        for (Client client : clientsQueue) 
        {
            if (!client.hasOrdered) 
            {
                System.out.println("Falta um pedido a ser atendido");
            }
        }

        // se todos os pedidos forem atendidos 
        // colect drinks
    }

    void CollectDrinks()
    {

    }

    void DeliverDrink() throws InterruptedException
    {
        Thread.sleep(3000);
        System.out.println("Acordando cliente");

        Client client = clientsQueue.peek();
        client.Wakeup();
    }

    // add clientes to queue
        // foreach or something like that
        // add cliente to the queue 

    
}
