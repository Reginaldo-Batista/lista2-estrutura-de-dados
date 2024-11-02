package classes;

import java.util.Random;

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

    public Node localizarNoIncompleto() {

        Random random = new Random();
        Node nodeAlvo = this.raiz;
        Boolean existeEsquerda = nodeAlvo.direita != null;
        Boolean existeDireita = nodeAlvo.esquerda != null;

        while (existeEsquerda && existeDireita) {
            if (random.nextBoolean()) {
                nodeAlvo = nodeAlvo.direita;
            } else {
                nodeAlvo = nodeAlvo.esquerda;
            }
        }

        return nodeAlvo;

    }
    
}
