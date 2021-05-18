import java.io.DataInputStream;
import  java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {

        Socket s = new Socket("127.0.0.1",7000);

        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        Scanner teclado = new Scanner(System.in);
        System.out.println(in.readUTF());

        String str = "";

        do{
            str = teclado.nextLine();
            out.writeUTF(str);
            System.out.println(in.readUTF());
        } while (!str.equals("soma"));

        s.close();
    }
}
