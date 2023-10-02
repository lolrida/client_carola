package com.example;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientStr {
    String nomeServer = "127.0.0.1";
    int portaServer = 8734;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti(){
        System.out.println("\nCLIENT IN ESECUZIONE ...\n\n");

        try{
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            mioSocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.out.println("Host sconosciuto");
        } catch (Exception e ) {
            System.out.println("ERRORE DURANTE LA CONNESSIONE");
            System.exit(1);
        }
        return mioSocket;
    }


    public void comunica() throws IOException{
        try{
            System.out.println("Inserisci la stringa da trasmettere al server :" + '\n');
            stringaUtente = tastiera.readLine();
            System.out.println("...Invio la stringa al server e attendo ...");
            outVersoServer.writeBytes(stringaUtente + '\n');
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("... risposta dal server" + '\n' + stringaRicevutaDalServer);
            System.out.println("Client : termina elaborazione e chiude connessione");
            mioSocket.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server");
            System.exit(1);
        }
    }

}
