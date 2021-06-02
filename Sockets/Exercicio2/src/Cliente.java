import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        // Cria o socket com o recurso desejado na porta especificada
        Socket s = new Socket("127.0.0.1", 7000);

        // Cria a Stream de saida de dados
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream ps = new DataOutputStream(s.getOutputStream());

        Scanner teclado = new Scanner(System.in);
        System.out.println(in.readUTF());

        String str = "";
        do {
            str = teclado.nextLine();
            ps.writeUTF(str);
            System.out.println(in.readUTF());
        } while (!str.equals("tchau"));

        // Encerra o socket cliente
        s.close();
    }
}
