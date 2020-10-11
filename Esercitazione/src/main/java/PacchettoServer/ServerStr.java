package PacchettoServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerStr {
    ServerSocket server = null;
    Socket client = null;
    String[] calcolo;
    String operazione = null;
    int primoNumero = 0;
    int secondoNumero = 0;
    int risultato = 0;
    BufferedReader inDalClient1;
    BufferedReader inDalClient2;
    BufferedReader inDalClient3;
    
    DataOutputStream outVersoClient;
    
    public Socket attendi(){
        try{
            System.out.println("1 Server in esecuzione...");
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalClient1 = new BufferedReader(new InputStreamReader(client.getInputStream()));
            inDalClient2 = new BufferedReader(new InputStreamReader(client.getInputStream()));
            inDalClient3 = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server.");
            System.exit(1);
        }
        return client;
    }
  
    public void comunica(){
        try{
            System.out.println("3 Benvenuto client, scrivi un'operazione da effettuare fra due numeri e la eseguiro'.");
            operazione = inDalClient1.readLine();
            calcolo = operazione.split(" ");
            operazione = calcolo[0];
            primoNumero = Integer.parseInt(calcolo[1]);
            secondoNumero = Integer.parseInt(calcolo[2]);
            System.out.println("6 Dati ricevuti dal client: " + operazione + " fra i numeri " + primoNumero + " e " + secondoNumero);
            if(operazione.equals("somma")){
                risultato = primoNumero + secondoNumero;
            }
            else if(operazione.equals("sottrazione")){
                risultato = primoNumero - secondoNumero;
            }
            else if(operazione.equals("moltiplicazione")){
                risultato = primoNumero * secondoNumero;
            }
            else if(operazione.equals("divisione")){
                risultato = primoNumero / secondoNumero;
            }
            System.out.println("7 Ti invio il risultato.");
            outVersoClient.writeBytes("Risultato: " + risultato + '\n');
            System.out.println("9 SERVER: fine elaborazione...");
            client.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la ricezione dei dati.");
            System.exit(1);
        }
    }
}
