package com.mycompany.trabalho;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Cliente {

    Socket socket;

    public Cliente() {
        criaClientSocket();
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int quant = 0;
        
        do {
            System.out.print("Insira a quantidade de números: ");
            try {
                quant = input.nextInt();
            } catch (Exception e) {
                quant = 0;
                input.nextLine();
            }
        } while (quant <= 0);
        System.out.println("=======================================");
        String lista = "";
        for (int i = 0; i < quant - 1; i++){
            lista += Integer.toString(random.nextInt(2147483647)) + ",";
        }
        lista += Integer.toString(random.nextInt(2147483647));
        
        ComunicadorObjetos.enviaObjeto(socket, lista);
        String p2 = ComunicadorObjetos.recebeObjeto(socket);
        
        System.out.println("Recebi: \n" + p2);
    }

    private void criaClientSocket() {
        try {
            //cria um socket TCP para se conectar ao servidor de ip "localhost" porta 1234
            socket = new Socket("localhost", 1234);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Cliente c = new Cliente();
    }
}
