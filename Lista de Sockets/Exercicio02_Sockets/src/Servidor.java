import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private int port = 7000;
    private ServerSocket serverSocket;

    public Servidor() throws ServerException, IOException {
        // Cria o ServerSocket na porta especificada se estiver disponivel
        serverSocket = new ServerSocket(port);

        System.out.println("Servidor iniciado na porta " + port);

        List<String> lista= new ArrayList<String>();
        lista.add("Aqui uma frase motivacional! ");
        lista.add("Está é uma frase muito longa!");
        lista.add("Lionel Messi não tem copa !");
        lista.add("Cristiano Ronaldo não tem copa!");
        lista.add("Grêmio é tri da América! - 2017");
        lista.add("Já o inter foi vice do América! - 2017");


        while (true) {

            Socket s = serverSocket.accept();
            String ip = s.getInetAddress().getHostAddress();
            System.out.println("Conectado com " + ip);

            // Cria um DataInputStream para o canal de entrada de dados do socket
            DataInputStream in  = new DataInputStream(s.getInputStream());

            // Cria um DataOutputStream para o canal de sa�da de dados do socket
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            // Aguarda por algum dado e imprime a linha recebida quando recebe

            String nova;
            String str = "";
            out.writeUTF("Mostras frases == 1  Digitar frase == 2");

            while(true) {
                str = in.readUTF();

                switch (str) {
                    case "1":

                        int aleatorio = ( int ) (Math.random() * (lista.size()) );
                        out.writeUTF(lista.get(aleatorio));
                        break;

                        case "2":

                        nova = "Digite uma nova frase";
                        for(int i = 0; i < lista.size(); i++) {
                            nova = nova + "\n" + lista.get(i) + " (" + i + ")";
                        }

                        nova = nova + "\nExit (exit)";
                        out.writeUTF(nova);
                        boolean exitSet = true;

                        while(exitSet) {
                            str = in.readUTF();
                            String newFrase = str;

                            switch (str) {
                                case "exit": exitSet = false; out.writeUTF("GET-FORTUNE(get)   SET-FORTUNE(set)"); break;
                                default:

                                    out.writeUTF("Digite a posi��o (?)");
                                    boolean insertIndex = true;

                                    while(insertIndex) {
                                        str = in.readUTF();

                                        switch (str) {
                                            case "exit": exitSet = false; out.writeUTF("Exit (exit)"); break;
                                            default:
                                                if(Integer.parseInt(str) <= lista.size()) {
                                                    lista.set(Integer.parseInt(str), newFrase);
                                                    nova = "Digite uma nova frase";
                                                    for(int i = 0; i < lista.size(); i++) {
                                                        nova = nova + "\n" + lista.get(i) + " (" + i + ")";
                                                    }

                                                    nova = nova + "\nExit (exit)";
                                                    out.writeUTF(nova);
                                                    insertIndex = false;

                                                }
                                                break;
                                        }
                                    }

                                    break;

                            }
                        }
                        break;

                    default:
                        out.writeUTF("Mensagem não entendida, digite novamente;");
                        break;


                }

            }
        }
    }



    public static void main(String[] args) {

        try {
            new Servidor();
        } catch (ServerException e) {
            System.out.println("Fechou conexão");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}