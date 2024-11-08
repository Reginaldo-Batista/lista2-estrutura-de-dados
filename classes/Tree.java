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
                    aux.getValor(),
                    this.localizarNoIncompleto());
            aux = aux.getProx();
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

    /**
     * Handles the removal of the root node in the tree.
     * 
     * This method is responsible for removing the root node (noAlvo) from the tree.
     * It chooses a replacement child node (filhoElegido) to take the place of the
     * root.
     * If a replacement child is found, it sets this child as the new root and
     * adjusts
     * the tree structure accordingly. If no replacement child is found, it sets the
     * root
     * to null, effectively making the tree empty.
     * 
     * @param raiz The root to be removed
     */
    private void handleRootRemoval(Node raiz) {
        Node filhoElegido = chooseReplacementChild(raiz);

        if (filhoElegido != null) {
            this.raiz = filhoElegido;
            Node folha = this.localizaFolhaRecursivo(filhoElegido);
            if (filhoElegido == raiz.getDireita()) {
                folha.setEsquerda(raiz.getEsquerda());
            } else {
                folha.setDireita(raiz.getDireita());
            }
        } else {
            this.raiz = null;
        }
        clearNodeReferences(raiz);
    }

    /**
     * Chooses a replacement child node for the given target node.
     * If the target node has both left and right children, a random child is chosen.
     * If the target node has only one child, that child is chosen.
     * If the target node has no children, null is returned.
     *
     * @param noPai the target node for which a replacement child is to be chosen
     * @return the chosen replacement child node, or null if the target node has no children
     */
    private Node chooseReplacementChild(Node noPai) {
        if (noPai.hasRight() && noPai.hasLeft()) {
            return this.getRandomBoolean() ? noPai.getDireita() : noPai.getEsquerda();
        } else {
            return (noPai.hasRight()) ? noPai.getDireita() : noPai.getEsquerda();
        }
    }

    /**
     * Replaces the child node of a given parent node with a new child node.
     *
     * @param paiAlvo The parent node whose child is to be replaced.
     * @param alvo The current child node that is to be replaced.
     * @param filhoElegido The new child node that will replace the current child node.
     */
    private void replaceChild(Node paiAlvo, Node alvo, Node filhoElegido) {
        if (paiAlvo.getDireita() == alvo) {
            paiAlvo.setDireita(filhoElegido);
        } else {
            paiAlvo.setEsquerda(filhoElegido);
        }
    }

    /**
     * Moves the unchosen child of the target node to the leaf of the chosen child.
     *
     * @param alvo The target node whose unchosen child will be moved.
     * @param filhoElegido The chosen child node whose leaf will receive the unchosen child.
     */
    private void moveUnchosenChild(Node alvo, Node filhoElegido) {
        Node folhaFilhoElegido = this.localizaFolhaRecursivo(filhoElegido);
        if (alvo.getDireita() == filhoElegido) {
            folhaFilhoElegido.setEsquerda(alvo.getEsquerda());
        } else {
            folhaFilhoElegido.setDireita(alvo.getDireita());
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
