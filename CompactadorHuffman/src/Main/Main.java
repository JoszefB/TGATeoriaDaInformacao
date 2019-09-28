//Joszef Barrionuevo

package Main;

import Archive.Archive;
import Huffman.Huffman;
import Huffman.Compress;
import Huffman.Unpack;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard=new Scanner(System.in);
        Huffman huf= new Huffman();
        Compress comp=new Compress();
        Unpack unp=new Unpack();
        Archive arc=new Archive();
        BufferedReader br = null;
        String in;
        String out;
        String line;
        String s = "";
        String codwords="";
        int op=0;
        char lineSeparator = '\n';
        do{
            System.out.print("\nMenu:\n1-Compactar\n2-Descompactar\n3-Sair\n->");
            op=keyboard.nextInt();
            switch (op) {
                case 1:
                    System.out.print("\nDigite o nome do arquivo para ser compctado:");
                    in = keyboard.next();
                    try {
                        br = arc.open(in);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        while ((line = br.readLine()) != null) {
                            s = s + line + lineSeparator;
                        }
                        out = comp.compact(s);
                        br.close();
                        for (int i = 0; i < huf.getCharacter().size(); i++) {
                            codwords = codwords + huf.getCharacter().get(i).getCharacter() + huf.getCharacter().get(i).getId() + huf.getMarker();
                        }

                        arc.save(in, codwords + "->" + lineSeparator, false);
                        arc.save(in, out, true);
                        System.out.println("\nRelatório de Compactação");
                        System.out.println("Tamanho do Arquivo Inicial: " + arc.getLengthInitial() + " bytes.");
                        System.out.println("Tamanho do Arquivo Final: " + arc.getLengthFinal() + " bytes.");
                        double a = (double) arc.getLengthInitial() / (double) arc.getLengthFinal();
                        double f = (1 - a);
                        System.out.println("Taxa de Compactação: " + f);
                        System.out.println("Compactação concluida");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.print("\nDigite o nome do arquivo para ser descompctado:");
                    in = keyboard.next();
                    try {
                        br = arc.open(in);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        while ((line = br.readLine()) != null) {
                            s = s + line + lineSeparator;
                        }
                        out = unp.unpackage(s);
                        br.close();
                        arc.save(in, out, false);
                        System.out.println("Descompactação concluida");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("\nFinalizando");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("...");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Aplicacao finalizada");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpcao invalida!");
                    break;
            }
        }
        while(op!=3);
    }

}
