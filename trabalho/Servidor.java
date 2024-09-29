package com.mycompany.trabalho;

import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    ServerSocket servidor;
    Socket cliente;

    public Servidor() {
        criaServerSocket();
        aguardaClientes();
    }

    private void criaServerSocket() {
        try {
            servidor = new ServerSocket(1234);
            System.out.println("Server escutando na porta 1234");
        } catch (Exception ex) {
        }
    }

    private void aguardaClientes() {
        while (true) {
            try {
                /*  Bloqueia esperando por uma conexão através do accept()
                    Ao receber a conexão, ele receberá como retorno uma referência do Socket do cliente*/
                cliente = servidor.accept();
                System.out.println("Recebi uma conexão de um cliente");
                String carteiro = ComunicadorObjetos.recebeObjeto(cliente);
                ArrayList<Integer> lista = new ArrayList<>();
                for (String e : carteiro.split(",")){
                    lista.add(Integer.parseInt(e));
                }
                lista.sort(Integer::compareTo);
                carteiro = "";
                for (int e : lista){
                    carteiro += String.valueOf(e) + ",";
                }
                carteiro = carteiro.substring(0,carteiro.length() - 2);
                ComunicadorObjetos.enviaObjeto(cliente, carteiro);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Servidor s = new Servidor();
    }
}
