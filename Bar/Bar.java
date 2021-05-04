public class Bar 
{
    public static void main(String[] args) throws Exception 
    {
        Client client = new Client();

        client.start();

        try 
        {
            client.join();
        } 
        catch (Exception e) 
        {

        }
    }

    // int clientCount = 20;
    // int waiterCount = 2;

    // Waiter waiter = new Waiter();
    // Waiter waiter2 = new Waiter();

    // void WaiterAttendanceCapacity()
    // {
        // Acapacidade de atendimento dos garçon = vai ser o numero de clientes divido
    // pela numero de garçons
    //     int attendenceCapacity = clientCount / waiterCount;

    //     // para cada garçom 
    //         // adiciona a capacidade de atendimento 
    // }
}
