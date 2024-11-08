package classes;

import java.util.Random;

public class Tree {

    Node raiz;

    public Tree(int valor) {
        this.raiz = new Node(valor);
    }

    // Questão 2
    public void addNo(Boolean dir, int valor, Node refNo) {

        if (refNo == null) {
            return;
        }

        Node novoNode = new Node(valor);

        if (dir == true) {
            while (refNo.hasRight()) {
                refNo = refNo.getDireita();
            }
            refNo.setDireita(novoNode);

        } else {
            while (refNo.hasLeft()) {
                refNo = refNo.getEsquerda();
            }
            refNo.setEsquerda(novoNode);
        }

    }

    // Questão 3
    public Boolean getRandomBoolean() {
        return (new Random().nextBoolean());
    }

    // Questão 3
    public Node localizarNoIncompleto() {
        return localizaNoIncompletoRecursivo(this.raiz);
    }

    // Questão 3
    private Node localizaNoIncompletoRecursivo(Node node) {

        if (node == null) {
            return null;
        }

        if (node.hasLeft() && node.hasRight()) {
            return this.localizaNoIncompletoRecursivo(this.getRandomBoolean()
                    ? node.getDireita()
                    : node.getEsquerda());
        } else {
            return node;
        }

    }

    public Node localizaFolha() {
        return this.localizaFolhaRecursivo(this.raiz);
    }

    private Node localizaFolhaRecursivo(Node node) {
        if (node == null) {
            return null;
        }

        if (node.isLeaf()) {
            return node;
        }

        if (node.hasLeft() && node.hasRight()) {
            return this.getRandomBoolean() ? this.localizaFolhaRecursivo(node.getDireita())
                    : this.localizaFolhaRecursivo(node.getEsquerda());
        }

        return node.hasRight() ? this.localizaFolhaRecursivo(node.getDireita())
                : this.localizaFolhaRecursivo(node.getEsquerda());
    }

    public int altura() {
        return this.alturaRecursivo(this.raiz);
    }

    private int alturaRecursivo(Node node) {
        if (node == null) {
            return 0;
        }
        int alturaDireita = this.alturaRecursivo(node.getDireita());
        int alturaEsquerda = this.alturaRecursivo(node.getEsquerda());

        // Se for igual sempre retornará a altura direita
        return Math.max(alturaDireita, alturaEsquerda) + 1;
    }

    public void addLista(Lista lista) {
        Bloco aux = lista.inicio;

        while (aux != null) {
            this.addNo(
                    this.getRandomBoolean(),
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
            int quantidadeDireita = this.numNoRecursivo(node.getDireita());
            int quantidadeEsquerda = this.numNoRecursivo(node.getEsquerda());
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
            lista.addElemento(node.getValor(), true);
            this.toListRecursivo(node.getEsquerda(), lista);
            this.toListRecursivo(node.getDireita(), lista);
        }
    }

    public Lista toListLeefOnly() {
        Lista listaDeFolhas = new Lista();

        this.toListLeefOnlyRecursivo(this.raiz, listaDeFolhas);

        return listaDeFolhas.inverteLista();
    }

    private void toListLeefOnlyRecursivo(Node node, Lista listaDeFolhas) {
        if (node != null) {
            if (node.isLeaf()) {
                listaDeFolhas.addElemento(node.getValor(), true);
            } else {
                this.toListLeefOnlyRecursivo(node.getDireita(), listaDeFolhas);
                this.toListLeefOnlyRecursivo(node.getEsquerda(), listaDeFolhas);
            }
        }
    }

    private Node localizaNodePaiRecursivo(Node noPai, Node noAlvo) {
        if (noPai == null) {
            return null;
        }

        if (noPai.getDireita() == noAlvo || noPai.getEsquerda() == noAlvo) {
            return noPai;
        }

        Node ladoDireito = this.localizaNodePaiRecursivo(noPai.getDireita(), noAlvo);
        if (ladoDireito != null) {
            return ladoDireito;
        }
        return this.localizaNodePaiRecursivo(noPai.getEsquerda(), noAlvo);
    }

    public void removeNo(Node noAlvo) {
        if (noAlvo == null) {
            return;
        }

        if (this.raiz == noAlvo) {
            handleRootRemoval(noAlvo);
            return;
        }

        Node paiDoAlvo = this.localizaNodePaiRecursivo(this.raiz, noAlvo);
        if (paiDoAlvo == null) {
            return;
        }

        Node filhoElegido = chooseReplacementChild(noAlvo);

        if (filhoElegido != null) {
            replaceChild(paiDoAlvo, noAlvo, filhoElegido);
            moveUnchosenChild(noAlvo, filhoElegido);
        }

        clearNodeReferences(noAlvo);
    }

    private void handleRootRemoval(Node noAlvo) {
        Node filhoElegido = chooseReplacementChild(noAlvo);

        if (filhoElegido != null) {
            this.raiz = filhoElegido;
            Node folha = this.localizaFolhaRecursivo(filhoElegido);
            if (filhoElegido == noAlvo.getDireita()) {
                folha.setEsquerda(noAlvo.getEsquerda());
            } else {
                folha.setDireita(noAlvo.getDireita());
            }
        } else {
            this.raiz = null;
        }
        clearNodeReferences(noAlvo);
    }

    private Node chooseReplacementChild(Node noAlvo) {
        if (noAlvo.hasRight() && noAlvo.hasLeft()) {
            return this.getRandomBoolean() ? noAlvo.getDireita() : noAlvo.getEsquerda();
        } else {
            return (noAlvo.hasRight()) ? noAlvo.getDireita() : noAlvo.getEsquerda();
        }
    }

    private void replaceChild(Node paiDoAlvo, Node noAlvo, Node filhoElegido) {
        if (paiDoAlvo.getDireita() == noAlvo) {
            paiDoAlvo.setDireita(filhoElegido);
        } else {
            paiDoAlvo.setEsquerda(filhoElegido);
        }
    }

    private void moveUnchosenChild(Node noAlvo, Node filhoElegido) {
        Node folhaFilhoElegido = this.localizaFolhaRecursivo(filhoElegido);
        if (noAlvo.getDireita() == filhoElegido) {
            folhaFilhoElegido.setEsquerda(noAlvo.getEsquerda());
        } else {
            folhaFilhoElegido.setDireita(noAlvo.getDireita());
        }
    }

    private void clearNodeReferences(Node noAlvo) {
        noAlvo.setDireita(null);
        noAlvo.setEsquerda(null);
    }

    public void printPreOrder() {
        System.out.printf("Impressão da árvore: ");
        this.printPreOrderRecursive(this.raiz);
        System.out.println();
    }

    private void printPreOrderRecursive(Node node) {
        if (node != null) {
            System.out.printf(node.getValor() + " ");
            this.printPreOrderRecursive(node.getEsquerda());
            this.printPreOrderRecursive(node.getDireita());
        }
    }

}
