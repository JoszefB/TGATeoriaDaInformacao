//Joszef Barrionuevo

package Huffman;

import java.util.ArrayList;
import Tree.Node;

public class Huffman {

    private static ArrayList<Node> character = new ArrayList();
    private static char marker = '~';

    public static String searchId(char c, ArrayList<Node> character) {
        String id = "";
        for (int i = 0; i < character.size(); i++) {
            if (character.get(i).getCharacter() == c) {
                id = character.get(i).getId();
            }
        }
        return id;
    }

    public static char searchCharacter(String stringBinary, ArrayList<Node> character) {
        char c = 0;
        for (int i=0;i<character.size();i++){
            if (character.get(i).getId().equals(stringBinary) )
                c = character.get(i).getCharacter();
        }
        return c;
    }

    public static Node minimum(ArrayList<Node> vector) {
        for (int i = vector.size(); i >= 1; i--) {
            for (int j = 1; j < i; j++) {
                if (vector.get(j - 1).getKey() > vector.get(j).getKey()) {
                    Node aux = vector.get(j);
                    vector.set(j, vector.get(j - 1));
                    vector.set(j - 1, aux);
                }
            }
        }
        return vector.get(0);
    }

    public ArrayList<Node> getCharacter(){
        return character;
    }

    public char getMarker(){
        return marker;
    }

}
