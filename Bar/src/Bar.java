package src;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author davi on 5/5/21
 * @project Bar
 */

public class Bar {
    int numClientes;
    int numGarcons;
    int capGarcons;
    int numRodadas;
    int rodada;
    int sendoAtendidos;

    boolean fechouBar = true;
    boolean existemClientesNoBar = false;
    boolean recebendoPedidos = false;
    Queue<Cliente> clientes = new LinkedList<>();
    Queue<Garcom> garcons = new LinkedList<>();
    Queue<Thread> threads = new LinkedList<>();


    Bar(int numClientes, int numGarcons, int capGarcons, int numRodadas) {
        this.numClientes = numClientes;
        this.numGarcons = numGarcons;
        this.capGarcons = capGarcons;
        this.numRodadas = numRodadas;
        this.rodada = 1;

        for (int i = 1; i <= numGarcons; i++) {
            garcons.add(new Garcom(this, "Garçom" + i));
            System.out.println("Garçom" + i + " estará a trabalho hoje.");
        }

        for (int i = 1; i <= numClientes; i++) {
            clientes.add(new Cliente(this, "Cliente" + i));
            System.out.println("Cliente" + i + " estará no buteco.");
        }

        for (Garcom garcom : garcons) {
            threads.add(new Thread(garcom, garcom.nome));
        }

        for (Cliente cliente : clientes) {
            threads.add(new Thread(cliente, cliente.nome));
        }
    }

    public static void main(String[] args) {
        int clientes = 5;
        int garcons = 2;
        int capacidadeGarcons = 3;
        int rodadas = 10;
        Bar bar = new Bar(clientes, garcons, capacidadeGarcons, rodadas);
        bar.iniciaCorreria();
    }

    public void iniciaCorreria() {
        System.out.println("O bar está oficialmente aberto!");
        recebendoPedidos = true;
        this.fechouBar = false;
        this.existemClientesNoBar = true;

        for (Thread thread : threads) {
            thread.start();
        }
    }
}