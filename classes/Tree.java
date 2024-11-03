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

    public int altura() {
        return this.alturaRecursivo(this.raiz);
    }

    private int alturaRecursivo(Node node) {
        if (node == null) {
            return 0;
        } else {
            int tamanhoDireita = this.alturaRecursivo(node.direita);
            int tamanhoEsquerda = this.alturaRecursivo(node.esquerda);

            if (tamanhoDireita > tamanhoEsquerda) {
                return (tamanhoDireita + 1);
            } else {
                return (tamanhoEsquerda + 1);
            }
        }
    }

    public void addLista(Lista lista) {

        Bloco aux = lista.inicio;

        while (aux != null) {
            this.addNo(
                    this.randomBoolean(),
                    aux.valor,
                    this.localizarNoIncompleto());
            aux = aux.prox;
        }

    }

    public int numNo() {
        return this.numNoRecursivo(this.raiz);
    }

    private int numNoRecursivo(Node node) {
        if (node == null) {
            return 0;
        } else {
            int quantidadeDireita = this.numNoRecursivo(node.direita);
            int quantidadeEsquerda = this.numNoRecursivo(node.esquerda);
            return quantidadeDireita + quantidadeEsquerda + 1;
        }
    }

    public Lista toList() {
        Lista lista = new Lista();
        this.toListRecursivo(this.raiz, lista);
        return lista.inverteLista();
    }

    private void toListRecursivo(Node node, Lista lista) {
        if (node != null) {
            lista.addElemento(node.valor, true);
            this.toListRecursivo(node.esquerda, lista);
            this.toListRecursivo(node.direita, lista);
        }
    }

    public Lista toListLeefOnly() {
        Lista listaDeFolhas = new Lista();

        this.toListLeefOnlyRecursivo(this.raiz, listaDeFolhas);

        return listaDeFolhas.inverteLista();
    }

    private void toListLeefOnlyRecursivo(Node node, Lista listaDeFolhas) {
        if (node != null) {
            if (node.direita == null && node.esquerda == null) {
                listaDeFolhas.addElemento(node.valor, true);
            } else {
                this.toListLeefOnlyRecursivo(node.direita, listaDeFolhas);
                this.toListLeefOnlyRecursivo(node.esquerda, listaDeFolhas);
            }
        }
    }

    public void printPreOrder() {
        System.out.printf("Impressão da árvore: ");
        this.printPreOrderRecursive(this.raiz);
        System.out.println();
    }

    private void printPreOrderRecursive(Node node) {
        if (node != null) {
            System.out.printf(node.valor + " ");
            this.printPreOrderRecursive(node.esquerda);
            this.printPreOrderRecursive(node.direita);
        }
    }

}
