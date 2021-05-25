public class Client extends Thread
{
    Bar bar = new Bar();

    boolean hasOrdered = false; 
    boolean hasConsumed = false; 
    boolean isWaiting = false; 

    Client(Bar bar)
    {
        this.bar = bar;
    }

    @Override
    public void run() 
    {
        while(bar.GetBarIsOpen()) 
        {
            try 
            {
                if(hasConsumed) { return; }

                if (hasOrdered)
                {
                    if (!isWaiting)
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
        isWaiting = true; 
        wait();
    }

    private void ReceiveOrder() throws InterruptedException 
    {
        isWaiting = false;
        System.out.println("Cliente: Recebendo pedido");
        Thread.sleep(1000);
    }

    private void ConsumeOrder() throws InterruptedException
    {
        hasConsumed = true;
        hasOrdered = false;  

        System.out.println("Cliente: Consumindo pedido");
        Thread.sleep(1000);
    }

    synchronized public void Wakeup()
    {
        notifyAll();
    }

    public boolean GetHasOrdered()
    {
        return hasOrdered;
    }

    public boolean GetHasConsumed()
    {
        return hasConsumed; 
    }

    public void SetHasConsumed(boolean value)
    {
        hasConsumed = value; 
    }
}
