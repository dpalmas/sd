import java.util.Deque;
import java.util.LinkedList;

/**
 * @author davi on 5/5/21
 * @project Bar
 */

public class Garcom implements Runnable {

    String nome;
    Deque<Cliente> clientes = new LinkedList<>();
    Bar bar;
    int rodada;
    boolean atendendo = false;
    int pedidos;
    int atendidos;
    int consumidos;

    public Garcom(Bar bar, String nome) {
        this.bar = bar;
        this.nome = nome;
    }

    @Override
    public void run() {

        synchronized (bar) {
            rodada = 1;
            pedidos = 0;
            atendidos = 0;
            consumidos = 0;

            while (bar.existemClientesNoBar && rodada <= bar.numRodadas) {
                recebeMaximoPedidos();
                registraPedidos();
                entregaPedidos();
                aguardaRodada();
                rodada++;
                atendidos = 0;

            }
            for (Cliente c : clientes)
                c.fechouBar = true;

            System.out.println("[ULTIMA RODADA] " + nome + " terminou de entregar a sua ultima rodada");

            while (rodada > bar.numRodadas) {
                bar.notifyAll();
                try {
                    bar.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void entregaPedidos() {
        System.out.println("[RODADA" + rodada + "] " + this.nome + " trouxe a rodada " + rodada + " para seus clientes");

        for (Cliente c : clientes) {
            c.esperando = false;
            c.recebeuPedido = true;
            System.out.println("[ENTREGA PEDIDO] " + this.nome + " entregou o pedido de " + c.nome);
            bar.notifyAll();
            try {
                bar.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void registraPedidos() {
        System.out.println("[BUSCAR PEDIDO] " + this.nome + " foi buscar os pedidos para seus clientes: " + this.clientes.toString());
        bar.notifyAll();
        try {
            bar.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void aguardaRodada() {
        while (consumidos < clientes.size()) {
            bar.notifyAll();
            try {
                bar.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        consumidos = 0;
    }

    void recebeMaximoPedidos() {

        if (rodada == 1) {
            while (clientes.size() < bar.capGarcons && bar.sendoAtendidos < bar.numClientes) {
                for (Cliente c : bar.clientes) {
                    if (!c.atendido) {
                        c.atendido = true;
                        System.out.println("[ATENDIMENTO] " + this.nome + " esta atendendo " + c.nome);
                        c.garcom = this;
                        clientes.add(c);
                        bar.notifyAll();
                        atendendo = true;
                        bar.sendoAtendidos++;
                        break;
                    }
                }

                while (atendendo) {
                    try {
                        bar.notifyAll();
                        bar.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            for (Cliente c : clientes) {
                if (!c.atendido) {
                    c.atendido = true;
                    System.out.println("[ATENDIMENTO] " + this.nome + " esta atendendo " + c.nome);
                    c.garcom = this;
                    bar.notifyAll();
                    atendendo = true;

                    while (atendendo) {
                        try {
                            bar.notifyAll();
                            bar.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}