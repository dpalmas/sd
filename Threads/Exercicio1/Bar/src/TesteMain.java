public class TesteMain 
{
    public static void main(String[] args) throws Exception 
    {
        Bar bar = new Bar();
        Client client = new Client(bar);
        Client client2 = new Client(bar);
        Waiter waiter = new Waiter();

        // Client client3 = new Client();
        // Client client4 = new Client();

        // Waiter waiter2 = new Waiter();
        
        bar.waitersQueue.offer(waiter);
        waiter.clientsQueue.offer(client);
        waiter.clientsQueue.offer(client2);

        // waiter2.clientsQueue.offer(client3);
        // waiter2.clientsQueue.offer(client4);

        // waitersQueue.offer(waiter2);

        bar.start();
        waiter.start();
        // waiter2.start();
        client.start();
        client2.start();
        // client3.start();
        // client4.start();

        // try
        // {
        //     bar.join();
        //     waiter.join();
        //     client.join();
        //     client2.join();
        // }
        // catch (Exception e)
        // {

        // }
    }
}
