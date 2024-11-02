package classes;

class Node {

    int valor;
    Node esquerda;
    Node direita;

    public Node(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }

}

public class Tree {

    Node raiz;

    public void addNo(Boolean dir, int valor, Node refNo) {
        
        Node novoNode = new Node(valor);

        if (dir == true) {
            refNo.direita = novoNode;
        } else {
            refNo.esquerda = novoNode;
        }
        
    }
    
}
