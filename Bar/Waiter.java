import java.util.concurrent.LinkedBlockingQueue;

public class Waiter extends Thread 
{
    LinkedBlockingQueue<Client> clientsQueue = new LinkedBlockingQueue<>();

    boolean isWaiting = false;
    boolean canCollectOrders = false; 
    boolean waitingForNextRound = false; 

    Bar bar = new Bar();
    
    @Override
    public void run() 
    {
        //while(true) // enquanto tem clientes no bar 
        //{
            try 
            {   
                //CheckOrders();
                CollectOrders();
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
            if(!client.GetHasOrdered()) 
            { 

            }    
        }
        canCollectOrders = true;
    }

    private void CollectOrders() {
        for (Client client : clientsQueue) 
        {
            client.Order();
            System.out.println("Waiter: Recebendo pedido dos cliente");
            // if (!client.hasOrdered) 
            // {
            //     System.out.println("Falta um pedido a ser atendido");
            // }
        }

        // se todos os pedidos forem atendidos 
        // colect drinks
    }

    synchronized void CollectDrinks() throws InterruptedException
    {
        Thread.sleep(1000);
        System.out.println("Waiter: Registrando e esperando os pedidos dos clientes ficarem prontos");
        isWaiting = true; 
        //wait();
        System.out.println("Waiter: Coletando os pedidos dos clientes");
        Thread.sleep(1000);

    }

    synchronized void DeliverDrinks() throws InterruptedException
    {

        for (Client client:clientsQueue)
        {
            Thread.sleep(3000);
            System.out.println("Waiter: Acordando cliente");

            client.Wakeup();
        }

        waitingForNextRound = true; 
        //System.out.println(waitingForNextRound); 
    }

    synchronized public void Wakeup() 
    {
        notifyAll();
    }

    public boolean GetIsWaitingForNextRound()
    {
        return waitingForNextRound;
    }
}
