package com.mycompany.trabalho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComunicadorObjetos {

    public static String recebeObjeto(Socket s) {
        try {
            //Cria um objeto de fluxo de dados de entrada, para poder receber um objeto do tipo Pessoa de um socket s
            ObjectInputStream leitor = new ObjectInputStream(s.getInputStream());
            String p = (String) leitor.readObject();
            return p;
        } catch (Exception e) {
            return null;
        }
    }

    public static void enviaObjeto(Socket s, String p) {
        try {
            //Cria um objeto de fluxo de dados de de saída, para poder enviar dados pelo socket s
            ObjectOutputStream escritor = new ObjectOutputStream(s.getOutputStream());

            new Thread(() -> {
                System.out.println("Enviarei: \n" + p + "\n");
                
            }).start();

            new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    try {
                        escritor.writeObject(p);
                    } catch (IOException ex) {
                        Logger.getLogger(ComunicadorObjetos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();        
        } catch (Exception e) {
        }
    }
}
