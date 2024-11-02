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

    public Tree(int valor) {
        this.raiz = new Node(valor);
    }

    // Questão 2
    public void addNo(Boolean dir, int valor, Node refNo) {

        Node novoNode = new Node(valor);

        if (dir == true) {
            refNo.direita = novoNode;
        } else {
            refNo.esquerda = novoNode;
        }

    }

    // Questão 3
    public Boolean randomBoolean() {
        return (new Random().nextBoolean());
    }

    // Questão 3
    public Node localizarNoIncompleto() {
        return localizaFolhaRecursivo(this.raiz);
    }

    // Questão 3
    private Node localizaFolhaRecursivo(Node node) {

        if (node.direita == null && node.esquerda == null) {
            return node;
        }

        else if (node.direita != null && node.esquerda != null) {
            return this.randomBoolean() ? this.localizaFolhaRecursivo(node.direita)
                    : this.localizaFolhaRecursivo(node.esquerda);
        }

        else {
            return (node.direita != null) ? this.localizaFolhaRecursivo(node.direita)
                    : this.localizaFolhaRecursivo(node.esquerda);
        }

    }

    public void printPreOrder() {
        this.printPreOrderRecursive(this.raiz);
    }

    private void printPreOrderRecursive(Node node) {
        if (node != null) {
            System.out.printf(node.valor + " ");
            this.printPreOrderRecursive(node.esquerda);
            this.printPreOrderRecursive(node.direita);
        }
    }

}
