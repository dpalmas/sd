import java.util.concurrent.LinkedBlockingQueue;

public class Waiter extends Thread 
{
    LinkedBlockingQueue<Client> clientsQueue = new LinkedBlockingQueue<>();

    Bar bar = new Bar();
    
    @Override
    public void run() 
    {
        // while(true) // enquanto tem clientes no bar 
        // {
            try 
            {   
                CheckOrders();
                CollectDrinks();
                DeliverDrinks();
            } 
            catch (InterruptedException e)            
            {
                e.printStackTrace();
            }
        //}
    }

    void CheckOrders() throws InterruptedException
    {
        for (Client client : clientsQueue) 
        {
            client.Order();
            System.out.println("Waiter: Recebendo pedido dos clientes");
            // if (!client.hasOrdered) 
            // {
            //     System.out.println("Falta um pedido a ser atendido");
            // }
        }

        // se todos os pedidos forem atendidos 
        // colect drinks
    }

    void CollectDrinks() throws InterruptedException
    {
        Thread.sleep(1000);
        System.out.println("Waiter: Coletando pedido dos clientes");
    }

    void DeliverDrinks() throws InterruptedException
    {

        for (Client client:clientsQueue)
        {
            Thread.sleep(3000);
            System.out.println("Waiter: Acordando cliente");

            client.Wakeup();
        }

        // Thread.sleep(1000);
        // System.out.println("Waiter: Acordando cliente");

        // Client client = clientsQueue.peek();
        // client.Wakeup();
    }

    // add clientes to queue
        // foreach or something like that
        // add cliente to the queue 



    
}
