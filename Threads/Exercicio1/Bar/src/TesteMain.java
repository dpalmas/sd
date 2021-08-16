public class TesteMain
{
    public static void main(String[] args) throws Exception
    {
        Bar bar = new Bar();
        Client client = new Client(bar);
        Client client2 = new Client(bar);
        Waiter waiter = new Waiter();

        bar.waitersQueue.offer(waiter);
        waiter.clientsQueue.offer(client);
        waiter.clientsQueue.offer(client2);

        bar.start();
        waiter.start();
        client.start();
        client2.start();
    }
}
