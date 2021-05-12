import java.util.concurrent.LinkedBlockingQueue;

public class Bar extends Thread 
{
    LinkedBlockingQueue<Waiter> waitersQueue = new LinkedBlockingQueue<>();

    boolean waiteresAreWaiting = true; 
    boolean barIsOpen = true; 
    int rounds = 5; 

    @Override
    public void run() 
    {
        while(barIsOpen)
        {   
            try 
            {
                RoundsController();
                VerifyWaiters();
                
                if(waiteresAreWaiting)
                {
                    rounds--;
                    System.out.println(rounds);
                    waiteresAreWaiting = false;    
                    RunAnotherRound();                 
                }
            } 
            catch (Exception e) 
            {

            }   
        } 
    }

    public void VerifyWaiters()
    {
        for (Waiter waiter : waitersQueue) 
        {
            if (waiter.GetIsWaitingForNextRound()) 
            { 
                continue; 
            }
            else
            {
                return; 
            }

        }
        waiteresAreWaiting = true; 
    }

    public void RunAnotherRound()
    {
        for (Waiter waiter : waitersQueue) 
        {
            waiter.run();
        }
    }

    public void RoundsController()
    {
        while(barIsOpen)
        {
            if(rounds <= 0)
            {
                barIsOpen = false; 
            }
        }
        
    }

    public boolean GetBarIsOpen()
    {
        return barIsOpen;
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
