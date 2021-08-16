public class Cliente extends Thread
{
    int id;
    boolean atendido = false;
    boolean esperando = false;

    Cadeira cadeira;
    BarbeiroDorminhoco barbeiroDorminhoco;

    Cliente(BarbeiroDorminhoco barbeiroDorminhoco, int id)
    {
        this.barbeiroDorminhoco = barbeiroDorminhoco;
        this.id = id;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(1000);
            barbeiroDorminhoco.ManejarCliente(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int GetId()
    {
        return id;
    }
}
