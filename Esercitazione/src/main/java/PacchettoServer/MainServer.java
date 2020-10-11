package PacchettoServer;

public class MainServer {
    public static void main(String[] args){
        ServerStr servente = new ServerStr();
        servente.attendi();
        servente.comunica();
    }
}