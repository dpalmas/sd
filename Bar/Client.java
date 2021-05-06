public class Client extends Thread
{
    Bar bar = new Bar();

    boolean hasOrdered = false; 
    boolean hasConsumed = false; 
    boolean hasWaiting = false; 

    @Override
    public void run() 
    {
        while(true) // não fechou o bar 
        {
            try 
            {
                if(hasConsumed) { return; }

                if (hasOrdered)
                {
                    if (!hasWaiting)
                    {
                        WaitForOrder();
                    }
                    ReceiveOrder();
                    ConsumeOrder();
                }  
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
    void Order()
    {
        // uma condição de porcentagem caso ele não beba. 
        hasOrdered = true; 
        System.out.println("Cliente: Quero uma batata");
    }
    
    synchronized void WaitForOrder() throws InterruptedException    
    {
        System.out.println("Cliente: Esperando pedido");
        hasWaiting = true; 
        wait();
    }

    private void ReceiveOrder() throws InterruptedException 
    {
        System.out.println("Cliente: Recebendo pedido");
        Thread.sleep(1000);
    }

    private void ConsumeOrder() throws InterruptedException
    {
        hasConsumed = true;
        //hasOrdered = false;  
        //hasWaiting = false;
        System.out.println("Cliente: Consumindo pedido");
        Thread.sleep(1000);
    }

    synchronized public void Wakeup()
    {
        notifyAll();
    }
}
