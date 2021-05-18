import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerException;
import java.util.ArrayList;

public class Servidor {
    private int port = 7000;
    private ServerSocket serverSocket;

    public  Servidor() throws SecurityException, IOException {

        serverSocket = new ServerSocket(port);

        System.out.println("#### Servidor Iniciado #### Porta: "+ port);

        while (true){

            Socket s = serverSocket.accept();
            String ip = s.getInetAddress().getHostAddress();
            System.out.println("Conectado com: "+ ip);

            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());


            out.writeUTF("Digite algo");
            String num = "";
            int soma = 0;

            do {
                num = in.readUTF();
                int numC = Integer.parseInt(num);
                soma += numC;
                Integer.toString(soma);
                out.writeUTF("Soma:"+ soma);
                } while(!num.equals("soma"));

            s.close();
            System.out.println("Desconectou :"+ ip);
        }
        }
    public static void main(String[] args){

        try {
            new Servidor();
        }catch (ServerException e){
            System.out.println("Erro");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    }

