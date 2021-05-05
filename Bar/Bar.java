public class Bar 
{
    public static void main(String[] args) throws Exception 
    {
        Client client = new Client();
        Waiter waiter = new Waiter();

        waiter.clientsQueue.offer(client);
        
        client.start();
        waiter.start();

        try 
        {
            client.join();
            waiter.join();
        } 
        catch (Exception e) 
        {

        }

        waiter.DeliverDrink();
    }

    public synchronized void WaitForOrder()
    {
        try 
        {
            wait();
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        notifyAll();
    }

    public synchronized void DeliverDrink()
    {
        try 
        {
            wait();
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        notifyAll();
    }



    // int clientCount = 20;
    // int waiterCount = 2;

    // Waiter waiter = new Waiter();
    // Waiter waiter2 = new Waiter();

    // void WaiterAttendanceCapacity()
    // {
        // Acapacidade de atendimento dos garçon = vai ser o numero de clientes divido
    // pela numero de garçons
    //     int attendenceCapacity = clientCount / waiterCount;

    //     // para cada garçom 
    //         // adiciona a capacidade de atendimento 
    // }
}
