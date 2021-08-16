/*
 * Main.java
 *
 * Neste código, uma instância de ContadorCentral é compartilhada por duas
 * threads. Algumas execuções funcionam perfeitamente, enquanto outras apresentam
 * erros de contagem devido ao problema de "leitura suja"/"atualização perdida".
 * Exemplo:
 * thread 1                thread 2
 * ======================= =========================
 * Lê numPessoas = 0
 *                         Lê numPessoas = 0
 * soma 1
 *                         soma 3
 *                         Escreva 3 em numPessoas
 * Escreve 1 em numPessoas
 *
 * Resultado: perdeu-se o incremento feito pela thread 2
 *
 * * Rodar a partir da linha de comando
 * ===================================
 * 1. Salve este arquivo em <dir>/
 * 2. Lance o shell: executar -> cmd
 * 3. posicione no diretório <dir>
 * 4. compile: javac Main.java
 * 5. execute: java -cp . Main
 */

import java.text.*;

/**
 *
 * @author TACLA
 */

/**
 * Contador é um objeto compartilhado pelas threads entrada 1 e entrada 2.
 */
class ContadorCentral
{
    protected int numPessoas = 0;
}

/**
 * Roleta controla uma entrada do parque.
 */
class Roleta implements Runnable
{
    public int totPessoas = 0;
    public int incr = 0;
    public ContadorCentral contadorCentral;

    public void run()
    {
        Thread thread = Thread.currentThread();
        synchronized (contadorCentral)
        {
            try
            {
                for (int i = 0; i < 40000000; i++)
                {
                    totPessoas = totPessoas + incr;
                    contadorCentral.numPessoas = contadorCentral.numPessoas + incr;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

public class Roletas
{
    public static void main(String[] args)
    {
        ContadorCentral contador = new ContadorCentral();
        Roleta e1 = new Roleta();
        e1.contadorCentral = contador;
        e1.incr = 1;

        Roleta e2 = new Roleta();
        e2.contadorCentral = contador;
        e2.incr = 3;

        Thread t1 = new Thread(e1, "Entrada 1");
        Thread t2 = new Thread(e2, "Entrada 2");
        t1.start();
        t2.start();
        
        try
        {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e)
        {
            System.out.println("Target thread was interrupted");
        }
        finally
        {
            DecimalFormat estilo = new DecimalFormat("###,###,###,###");
            System.out.println("\n*** FIM DA CONTAGEM ***");
            System.out.println("*** Entrada 1: " + estilo.format(e1.totPessoas)
                    + " pessoas");
            System.out.println("*** Entrada 2: " + estilo.format(e2.totPessoas)
                    + " pessoas");
            System.out.println("*** Total: "
                    + estilo.format(e2.totPessoas + e1.totPessoas));
            System.out.println("*** Total CENTRALIZADO: "
                    + estilo.format(contador.numPessoas));
        }
    }
}
