package PacchettoClient;

public class MainClient {
    public static void main(String[] args){
        ClientStr cliente = new ClientStr();
        cliente.connetti();
        cliente.comunica();
    }
}
