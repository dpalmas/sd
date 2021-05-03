import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author davi on 5/2/21
 * @project BarbeiroDorminhoco2
 */
public class BarbeiroDorminhoco implements Runnable {

    boolean dormindo = false;
    Cliente cliente;

    BarbeiroDorminhoco() {
    }

    @Override
    public void run() {
        while (true) {
            if (dormindo) {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                    }
                }
            } else {
                synchronized (Main.cadeiras) {
                    for (int i = 0; i < Main.cadeiras.size(); i++) {
                        if (Main.cadeiras.get(i).livre == false) {
                            System.out.println("Barbeiro está atendendo o Cliente " + Main.cadeiras.get(i).cliente.id);
                            try {
                                sleep(100);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(BarbeiroDorminhoco.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Main.cadeiras.get(i).livre = true;
                            Main.cadeiras.get(i).cliente.atendido = true;
                            System.out.println("Barbeiro atendeu o cliente "+ Main.cadeiras.get(i).cliente.id);
                            synchronized (Main.cadeiras.get(i).cliente) {
                                Main.cadeiras.get(i).cliente.notify();
                                Main.cadeiras.get(i).cliente = null;
                            }

                        }
                    }
                }
                synchronized (this) {
                    try {
                        System.out.println("Barbeiro não tinha clientes e Dormiu");
                        this.dormindo = true;
                        this.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BarbeiroDorminhoco.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        }
    }
}