/**
 * @author davi on 5/2/21
 * @project BarbeiroDorminhoco2
 */
public class Cliente implements Runnable {

    boolean esperando = false;
    int id;
    Cadeira p;
    boolean atendido = false;
    BarbeiroDorminhoco barbeiro;

    Cliente(BarbeiroDorminhoco babeiro, int id) {
        this.barbeiro = babeiro;
        this.id = id;
    }

    public void run() {
        synchronized (Main.cadeiras) {
            for (int i = 0; i < Main.cadeiras.size(); i++) {
                if (p == null && Main.cadeiras.get(i).livre == true) {
                    Main.cadeiras.get(i).livre = false;
                    this.p = Main.cadeiras.get(i);
                    Main.cadeiras.get(i).cliente = this;
                    System.out.println("Cliente " + id + " pegou a cadeira" + i);
                    break;
                }
            }
        }
        if (p == null) {
            System.out.println("O cliente " + this.id + " não encontrou cadeiras vazias e foi embora.");
            atendido = true;
        }
        while (!atendido) {
            synchronized (barbeiro) {
                while (barbeiro.dormindo) {
                    barbeiro.dormindo = false;
                    barbeiro.cliente = this;
                    barbeiro.notifyAll();
                    System.out.println(Thread.currentThread().getName() + " Acordou o barbeiro");

                }
            }
            if (atendido) {
                System.out.println("Cliente " + id + " está pronto e saindo do barbeiro");
            }
        }
    }
}