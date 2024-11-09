package classes;

public class Tree {

    Node raiz;

    public Tree(int valor) {
        this.raiz = new Node(valor);
    }

    public static Tree getTree(int rootValue, int finalSizeOfTree) {
        if (finalSizeOfTree <= 0) {
            return null;
        }

        Tree newTree = new Tree(rootValue);
        for (int i = 0; i < finalSizeOfTree - 1; i++) {
            newTree.addNo(
                    Randomize.getRandomBoolean(),
                    Randomize.getRandomInt(1, 100),
                    newTree.localizarNoIncompleto());
        }
        return newTree;
    }

    // Questão 2
    public void addNo(Boolean dir, int valor, Node refNo) {

        if (refNo == null) {
            return;
        }

        Node novoNode = new Node(valor);

        if (dir) {
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
    public Node localizarNoIncompleto() {
        return localizaNoIncompletoRecursivo(this.raiz);
    }

    // Questão 3
    private Node localizaNoIncompletoRecursivo(Node node) {

        if (node == null) {
            return null;
        }

        if (node.hasLeft() && node.hasRight()) {
            return this.localizaNoIncompletoRecursivo(Randomize.getRandomBoolean()
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
            return Randomize.getRandomBoolean()
                    ? this.localizaFolhaRecursivo(node.getDireita())
                    : this.localizaFolhaRecursivo(node.getEsquerda());
        }

        return node.hasRight()
                ? this.localizaFolhaRecursivo(node.getDireita())
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
                    Randomize.getRandomBoolean(),
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

    private Node chooseReplacementChild(Node noPai) {
        if (noPai.hasRight() && noPai.hasLeft()) {
            return Randomize.getRandomBoolean()
                    ? noPai.getDireita()
                    : noPai.getEsquerda();
        } else {
            return (noPai.hasRight())
                    ? noPai.getDireita()
                    : noPai.getEsquerda();
        }
    }

    private void replaceChild(Node paiAlvo, Node alvo, Node filhoElegido) {
        if (paiAlvo.getDireita() == alvo) {
            paiAlvo.setDireita(filhoElegido);
        } else {
            paiAlvo.setEsquerda(filhoElegido);
        }
    }

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
