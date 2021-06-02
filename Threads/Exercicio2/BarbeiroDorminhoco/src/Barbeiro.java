public class Barbeiro extends Thread
{
    BarbeiroDorminhoco barbeiroDorminhoco;

    public Barbeiro(BarbeiroDorminhoco barbeiroDorminhoco)
    {
        this.barbeiroDorminhoco = barbeiroDorminhoco;
    }

    @Override
    public void run() 
    {
        while(true)
        {
            try 
            {
                Thread.sleep(1000);
                barbeiroDorminhoco.Trabalhando();
            } 
            catch (Exception e) { }
        }
    }
}
