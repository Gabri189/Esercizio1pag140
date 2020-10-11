package PacchettoClient;

import java.io.*;
import java.net.*;

public class ClientStr {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String operazione;
    String primoNumero;
    String secondoNumero;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    
    public Socket connetti() {
        System.out.println("2 CLIENT in esecuzione...");
        try{
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        }catch(UnknownHostException e){
            System.out.println("Host sconosciuto");}
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        return miosocket;
    }
    
    public void comunica(){
        try{
            System.out.println("4...inserisci l'operazione da trasmettere al server" + '\n');
            operazione = tastiera.readLine();
            System.out.println("Inserisci il primo numero");
            primoNumero = tastiera.readLine();
            System.out.println("Inserisci il secondo numero");
            secondoNumero = tastiera.readLine();
            System.out.println("5...invio i dati al server e attendo...");
            operazione = operazione + " " + primoNumero + " " + secondoNumero;
            outVersoServer.writeBytes(operazione + '\n');
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("8...risposta dal server" + '\n' + stringaRicevutaDalServer);
            System.out.println("9 CLIENT: termina elaborazione e chiude la connessione...");
            miosocket.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server");
            System.exit(1);
        }
    }
}
