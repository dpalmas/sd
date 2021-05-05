public class Client extends Thread
{
    Bar bar = new Bar();

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
                
                if (hasOrdered)
                {
                    WaitForOrder();
                    ReceiveOrder();
                    
                    ConsumeOrder();
                }
                    
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        //}
    }

    
    void Order()
    {
        // uma condição de porcentagem caso ele não beba. 
        
        hasOrdered = true; 
        System.out.println("Quero uma batata");
        //System.out.println(hasOrdered);
    }
    
    void WaitForOrder() throws InterruptedException    
    {
        synchronized(this)
        {
            System.out.println("Esperando pedido");
            
            wait();
        }
    }

    private void ReceiveOrder() throws InterruptedException 
    {
        System.out.println("Recebendo pedido");
        Thread.sleep(1000);
    }

    private void ConsumeOrder() throws InterruptedException
    {
        hasConsumed = true;
        System.out.println("Consumindo pedido");
        Thread.sleep(1000);
    }

    public void Wakeup()
    {
        synchronized (this) {
            notifyAll();
        }
    }
}
