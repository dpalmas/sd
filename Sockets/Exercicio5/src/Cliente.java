import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) {
        try {
            Socket socket;
            if (args.length == 1) {
                socket = new Socket(args[0], 8181);
            } else {
                // InetAddress addr = InetAddress.getByAddress("localhost");
                // socket = new Socket(addr,8181);
            }
            System.out.println("Socket:");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            String s = in.readLine();

            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            s = teclado.readLine();

            while (!s.toLowerCase().equals("sair")) {

                s = in.readLine();
                System.out.println(s);

                s = teclado.readLine();
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}