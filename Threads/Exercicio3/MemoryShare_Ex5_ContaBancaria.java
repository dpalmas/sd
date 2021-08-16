class Conta
{
    int saldo = 0;

}

class Retirada extends Thread
{
    private Conta c;

    public Retirada(Conta c)
    {
        this.c = c;
    }

    public void run()
    {
        int vlrs[] =
        { 110, 20, 30, 40, 50, 60 };
        synchronized (c)
        {
            try
            {
                for (int i = 0; i < vlrs.length; i++)
                {
                    c.saldo -= vlrs[i];
                    System.out.println("Retirada: " + vlrs[i] + " - Saldo: " + c.saldo);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }
}

class Deposito extends Thread
{
    private Conta c;

    public Deposito(Conta c)
    {
        this.c = c;
    }

    public void run()
    {
        int vlrs[] =
        { 40, 50, 60, 10, 20, 30 };
        synchronized (c)
        {
            try
            {
                for (int i = 0; i < vlrs.length; i++)
                {
                    c.saldo += vlrs[i];
                    System.out.println("Deposito: " + vlrs[i] + " - Saldo: " + c.saldo);
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

public class MemoryShare_Ex5_ContaBancaria
{
    public static void main(String[] args)
    {
        Conta c = new Conta();

        Thread d = new Deposito(c);
        Thread r = new Retirada(c);

        d.start();
        r.start();

        try
        {
            d.join();
            r.join();
        }
        catch (InterruptedException e)
        {
            System.out.println("Target thread was interrupted");
        }

        System.out.println("Saldo=" + c.saldo);
    }
}
