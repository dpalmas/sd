import java.util.Random;

/**
 * @author davi on 5/5/21
 * @project Bar
 */

public class Cliente implements Runnable {
    Bar bar;
    String nome;
    Garcom garcom;
    boolean fezPedido = false;
    boolean esperando = true;
    boolean recebeuPedido = false;
    boolean atendido = false;
    boolean fechouBar = false;

    public Cliente(Bar bar, String nome) {
        this.bar = bar;
        this.nome = nome;

    }

    @Override
    public void run() {
        synchronized (bar) {
            while (!fechouBar) {
                fazPedido();
                esperaPedido();
                recebePedido();
                consomePedido(); //tempo variavel
                atendido = false;
                fezPedido = false;
                esperando = true;
                recebeuPedido = false;
            }

            while (fechouBar) {
                bar.notifyAll();
                try {
                    bar.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void consomePedido() {
        Random rnd = new Random();
        int tempo = rnd.nextInt(500) + 500; //ate 1s

        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[TERMINO CONSUMO] " + this.nome + " terminou de consumir a bebida!");
        garcom.consumidos++;

        bar.notifyAll();
        try {
            bar.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void recebePedido() {
        while (!recebeuPedido) {
            try {
                bar.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        bar.notifyAll();
        try {
            bar.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void fazPedido() {
        while (!bar.recebendoPedidos) {
            bar.notifyAll();
            try {
                bar.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (!fezPedido) {
            //enquanto garçom não atende o cliente
            while (!atendido) {
                try {
                    bar.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("[ANOTA PEDIDO] " + garcom.nome + " recebeu o pedido de " + this.nome);
            fezPedido = true;
            garcom.pedidos++;
            garcom.atendendo = false;
        }
    }

    void esperaPedido() {
        while (esperando) {
            try {
                bar.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return this.nome;
    }
}