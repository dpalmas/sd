import java.util.ArrayList;

/**
 * @author davi on 4/19/21
 * @project BarbeiroDorminhoco
 */

public class Main {

    public static ArrayList<Cadeira> cadeiras = new ArrayList<>();

    public static void main(String[] args) 
    {
        BarbeiroDorminhoco barbeiroDorminhoco = new BarbeiroDorminhoco();

        // int nCadeiras = 3;
        // int nClientes = 5;

        // for (int i = 0; i < nCadeiras; i++) {
        //     Cadeira cadeira = new Cadeira();
        //     cadeiras.add(cadeira);
        // }

        Barbeiro barbeiroThread = new Barbeiro(barbeiroDorminhoco);
        Cliente clienteThread1 = new Cliente(barbeiroDorminhoco, 1);
        Cliente clienteThread2 = new Cliente(barbeiroDorminhoco, 2);
        Cliente clienteThread3 = new Cliente(barbeiroDorminhoco, 3);
        Cliente clienteThread4 = new Cliente(barbeiroDorminhoco, 4);
        Cliente clienteThread5 = new Cliente(barbeiroDorminhoco, 5);
        Cliente clienteThread6 = new Cliente(barbeiroDorminhoco, 6);
        Cliente clienteThread7 = new Cliente(barbeiroDorminhoco, 7);
        Cliente clienteThread8 = new Cliente(barbeiroDorminhoco, 8);
        Cliente clienteThread9 = new Cliente(barbeiroDorminhoco, 9);
        Cliente clienteThread10 = new Cliente(barbeiroDorminhoco, 10);

        barbeiroThread.start();
        clienteThread1.start();
        clienteThread2.start();
        clienteThread3.start();
        clienteThread4.start();
        clienteThread5.start();
        clienteThread6.start();
        clienteThread7.start();
        clienteThread8.start();
        clienteThread9.start();
        clienteThread10.start();

        try 
        {
            barbeiroThread.join();
            clienteThread1.join();
            clienteThread2.join();
            clienteThread3.join();
            clienteThread4.join();
            clienteThread5.join();
            clienteThread6.join();
            clienteThread7.join();
            clienteThread8.join();
            clienteThread9.join();
            clienteThread10.join();
            
        } 
        catch (Exception e) {  }
    }
}