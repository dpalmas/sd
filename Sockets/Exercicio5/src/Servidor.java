import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor 
{
    public static void main(String[] args) 
    {
        try 
        {
            ServerSocket s = null;
            s = new ServerSocket(8181);
            System.out.println("Servidor Inciado");

            while (true) 
            {
                Socket socket = s.accept();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        
        Conta minhaConta = new Conta();
        Scanner leitura = new Scanner(System.in);
        String nome;
        int num, op;
        float saldo, valor, lim;
        do 
        {
            System.out.println("----- * Menu * > Conta Bancaria-----");
            System.out.println("1- Inserir dados:");
            System.out.println("2- Mostrar dados");
            System.out.println("3- Depositar valor");
            System.out.println("4- Sacar valor");
            System.out.println("5- Mostrar saldo");
            System.out.println("0 - Sair ---------------------------");
            leitura.nextLine();
            op = leitura.nextInt();
            switch (op) 
            {
                case 1:
                    System.out.println("Entre com o nome:");
                    nome = leitura.nextLine();

                    System.out.println("Entre com o Numero: ");
                    num = leitura.nextInt();

                    System.out.println("Entre com o saldo: ");
                    saldo = leitura.nextFloat();

                    System.out.println("Entre com o limite");
                    lim = leitura.nextFloat();

                    minhaConta.inseredados(nome, num, saldo, lim);

                    break;
                case 2:
                    minhaConta.mostrardado();
                    break;
                case 3:
                    System.out.println("Entre com o valor a ser depositado: ");
                    valor = leitura.nextFloat();
                    minhaConta.deposita(valor);
                    break;
                case 4:
                    minhaConta.mostrarsal();
                    System.out.println("Entre com o valor a ser sacado: ");
                    valor = leitura.nextFloat();
                    minhaConta.saca(valor);
                    break;
                case 5:
                    minhaConta.mostrarsal();
                    break;
            }
        } while (op != 0);

    }

}

class Conta 
{
    private int Numero;
    public int op;
    private String Dono;
    private double Saldo;

    public Conta() 
    {
        this.Numero = 0;
        this.Dono = " ";
        this.Saldo = 0;

    }

    public int getNumero() 
    {
        return Numero;
    }

    public void setNumero(int Numero) 
    {
        this.Numero = Numero;
    }

    public String getDono() 
    {
        return Dono;
    }

    public void setDono(String Dono) 
    {
        this.Dono = Dono;
    }

    public double getSaldo() 
    {
        return Saldo;
    }

    public void setSaldo(double Saldo) 
    {
        this.Saldo = Saldo;
    }

    boolean saca(double quantidade) 
    {
        if (this.Saldo < quantidade)
            return false;
        else {
            this.Saldo = this.Saldo - quantidade;
            return true;
        }
    }

    void deposita(double quantidade) 
    {
        this.Saldo = this.Saldo + quantidade;
    }

    void inseredados(String a, int b, float c, float l) 
    {
        this.Dono = a;
        this.Numero = b;
        this.Saldo = c;
    }

    void mostrardado() 
    {
        System.out.println("Dono da Conta: " + this.getDono());
        System.out.println("Numero da Conta: " + this.getNumero());
        System.out.println("Saldo atual: " + this.getSaldo());
    }

    void mostrarsal() 
    {
        System.out.println("Saldo Atual: " + this.getSaldo());

    }
}
