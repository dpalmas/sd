public class Client extends Thread
{
    boolean hasOrdered = false; 
    boolean hasConsumed = false; 

    @Override
    public void run() 
    {
        // while(true) // não fechou o bar 
        // {
            try 
            {
                Order();
                wait();
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        // }
    }

    void Order()
    {
        hasOrdered = true; 
        System.out.println("Quero uma batata");
        System.out.println(hasOrdered);
    }
}
