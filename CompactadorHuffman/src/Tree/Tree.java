//Joszef Barrionuevo

package Tree;

public class Tree {

    private Node root;

    public Tree(){
        root = null;
    }

    public boolean emptyTree(){
        return (root == null);
    }

    public void setRoot (Node n){
        root = n;
    }

    public Node getRoot (){
        return root;
    }

    public void insert(Node current, Node k){
        if (emptyTree()){
            root = k;
        }
        else{
            if (current.getKey() < k.getKey()){
                if (current.getRight() != null){
                    insert (current.getRight(), k);
                }
                else{
                    current.setRight(k);
                }
            }
            else {
                if (current.getLeft() != null){
                    insert (current.getLeft(), k);
                }
                else{
                    current.setLeft(k);
                }
            }
        }
        return;
    }

    public void inOrder(Node no) {
        if(no != null) {
            inOrder(no.getLeft());
            System.out.print(no.getKey()+" ");
            inOrder(no.getRight());
        }
    }
}