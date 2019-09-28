//Joszef Barrionuevo

package Tree;

public class Node {

    private int key;
    private char character;
    private String cod;
    private Node left;
    private Node right;

    public Node(int k, char c) {
        key = k;
        character = c;
        cod = "";
        left = right = null;
    }

    public Node() {
        key = 0;
        character = ' ';
        cod = "";
        left = right = null;
    }

    public boolean leaf() {
        return (left == null && right == null);
    }

    public void setKey(int k) {
        key = k;
    }

    public int getKey() {
        return key;
    }

    public void setLeft(Node n) {
        left = n;
    }

    public Node getLeft() {
        return left;
    }

    public void setRight(Node n) {
        right = n;
    }

    public Node getRight() {
        return right;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char caracter) {
        this.character = caracter;
    }

    public String getId() {
        return cod;
    }

    public void setId(String cod) {
        this.cod = cod;
    }

    public String toString() {
        return ( character + ": " +Integer.toString(key));
    }

    private void codNode(String path) {
        if ((left == null) && (right == null)) {
            cod = path;
        }
        if (left != null) {
            left.codNode(path + '1');
        }
        if (right != null) {
            right.codNode(path + '0');
        }
    }

    public static void generatesCoding(Node tree) {
        tree.codNode("");
    }
}
