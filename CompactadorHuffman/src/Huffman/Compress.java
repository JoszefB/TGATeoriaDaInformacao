//Joszef Barrionuevo

package Huffman;

import java.util.ArrayList;
import Tree.Node;

public class Compress extends Huffman {

    public String compact(String line) {
        String text = line;
        System.out.println("\nTexto a ser compactado: " + text);
        text = '^' + text;
        ArrayList<Node> vector = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            Node newNode = new Node(1, text.charAt(i));
            if (vector.isEmpty()) {
                vector.add(newNode);
                getCharacter().add(newNode);
            } else {
                boolean have = false;
                for (int j = 0; j < vector.size(); j++) {
                    if (newNode.getCharacter() == vector.get(j).getCharacter()) {
                        vector.get(j).setKey(vector.get(j).getKey() + 1);
                        have = true;
                    }
                }
                if (!have) {
                    vector.add(newNode);
                    getCharacter().add(newNode);
                }
            }
        }

        int a = vector.size();

        for (int i = 0; i < a - 1; i++) {
            Node z = new Node();
            int fx = 0, fy = 0;
            Node x = minimum(vector);
            z.setLeft(x);
            fx = x.getKey();
            vector.remove(x);

            Node y = minimum(vector);
            z.setRight(y);
            fy = y.getKey();
            vector.remove(y);

            z.setKey(fx + fy);
            vector.add(z);
        }
        
        Node.generatesCoding(vector.get(0));
        
        String codedText = "";
        System.out.println("Aguarde, compactando...");
        for (int i = 0; i < text.length(); i++) {
            codedText = codedText + searchId(text.charAt(i), getCharacter());
        }
        System.out.println("\nTexto codificado em binario: " + codedText);

        if (codedText.length() % 8 != 0) {
            int j = 8 - (codedText.length() % 8);
            for (int i = 0; i < j; i++) {
                codedText = "0" + codedText;
            }
        }
        System.out.println("\nTexto codificado em binario arrumado: " + codedText);

        String textFinal = "";
        for (int i = 0; i < codedText.length() / 8; i++) {
            String sub = "";
            sub = codedText.substring(i * 8, (i * 8) + 8);
            int codAscii = Integer.parseInt(sub, 2);
            char c = (char) codAscii;
            if(codAscii==13)
                textFinal = textFinal + c +"<13>";
            else
                textFinal = textFinal + c;
        }
        System.out.println("\nTexto codificado final: " + textFinal);

        return textFinal;
    }
}
