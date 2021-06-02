import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerException;

public class Servidor {
    private int port = 7000;
    private ServerSocket serverSocket;

    public Servidor() throws ServerException, IOException {

        serverSocket = new ServerSocket(port);

        System.out.println("#### Servidor iniciado na porta " + port);

        while (true) {

            Socket s = serverSocket.accept();
            String ip = s.getInetAddress().getHostAddress();
            System.out.println("### Conectado com " + ip);

            DataInputStream in = new DataInputStream(s.getInputStream());

            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String str = "";
            out.writeUTF("Olá Cliente,Eu sou o Servidor. Digite algo");

            do {
                str = in.readUTF();
                out.writeUTF(" ## Servidor ## O Cliente digitou: " + str);

            } while (!str.equals("sair"));

            System.out.println("Tchau amigo Cliente");
            s.close();
            System.out.println("### Desconectado de " + ip);
        }
    }

    public static void main(String[] args) {

        try {
            new Servidor();
        } catch (ServerException e) {
            System.out.println("#### erro #### ");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
