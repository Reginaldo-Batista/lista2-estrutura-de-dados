package src.classes;

// TODO verificar se é melhor criar um construtor que inicia a árvore com a raiz null, se sim, alterar o método getTree e addNo.
// Questão 1
public class Tree {

    Node raiz;

    public Tree(int valor) {
        this.raiz = new Node(valor);
    }

    public Node getRaiz() {
        return raiz;
    }

    public void setRaiz(Node raiz) {
        this.raiz = raiz;
    }

    // Apenas para facilitar a construção de árvores aleatórias
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
            return this.localizaNoIncompletoRecursivo(
                    Randomize.getRandomBoolean()
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

    // Questão 4
    public int altura() {
        return this.alturaRecursivo(this.raiz);
    }

    // Questão 4
    private int alturaRecursivo(Node node) {
        if (node == null) {
            return 0;
        }
        int alturaDireita = this.alturaRecursivo(node.getDireita());
        int alturaEsquerda = this.alturaRecursivo(node.getEsquerda());

        // Se for igual sempre retornará a altura direita
        return Math.max(alturaDireita, alturaEsquerda) + 1;
    }

    // Questão 5
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

    // Questão 6
    public int numNo() {
        return this.numNoRecursivo(this.raiz);
    }

    // Questão 6
    private int numNoRecursivo(Node node) {
        if (node == null) {
            return 0;
        } else {
            int quantidadeDireita = this.numNoRecursivo(node.getDireita());
            int quantidadeEsquerda = this.numNoRecursivo(node.getEsquerda());
            return quantidadeDireita + quantidadeEsquerda + 1;
        }
    }

    // Questão 7
    public Lista toList() {
        Lista lista = new Lista();
        this.toListRecursivo(this.raiz, lista);
        return lista.inverteLista();
    }

    // Questão 7
    private void toListRecursivo(Node node, Lista lista) {
        if (node != null) {
            lista.addElemento(node.getValor(), true);
            this.toListRecursivo(node.getEsquerda(), lista);
            this.toListRecursivo(node.getDireita(), lista);
        }
    }

    // Questão 8
    public Lista toListLeefOnly() {
        Lista listaDeFolhas = new Lista();
        this.toListLeefOnlyRecursivo(this.raiz, listaDeFolhas);
        return listaDeFolhas.inverteLista();
    }

    // Questão 8
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

    // Questão 9
    public void removeNo(Node noAlvo) {
        if (noAlvo == null) {
            return;
        }

        if (this.raiz == noAlvo) {
            removeNoRaiz(noAlvo);
            return;
        }

        Node paiDoAlvo = this.localizaNodePaiRecursivo(this.raiz, noAlvo);
        if (paiDoAlvo == null) {
            return;
        }

        Node filhoElegido = escolheNoFilho(noAlvo);

        if (filhoElegido != null) {
            replaceChild(paiDoAlvo, noAlvo, filhoElegido);
            moveUnchosenChild(noAlvo, filhoElegido);
        }

    }

    // Questão 9
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

    // Questão 9
    private void removeNoRaiz(Node raiz) {
        Node filhoElegido = escolheNoFilho(raiz);

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

    }

    // Questão 9
    private Node escolheNoFilho(Node noPai) {
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

    // Questão 9
    private void replaceChild(Node paiAlvo, Node alvo, Node filhoElegido) {
        if (paiAlvo.getDireita() == alvo) {
            paiAlvo.setDireita(filhoElegido);
        } else {
            paiAlvo.setEsquerda(filhoElegido);
        }
    }

    // Questão 9
    private void moveUnchosenChild(Node alvo, Node filhoElegido) {
        Node folhaFilhoElegido = this.localizaFolhaRecursivo(filhoElegido);
        if (alvo.getDireita() == filhoElegido) {
            folhaFilhoElegido.setEsquerda(alvo.getEsquerda());
        } else {
            folhaFilhoElegido.setDireita(alvo.getDireita());
        }
    }

    // Questão 10
    public void concatenaTree(Node targetNode, Tree treeToConcat) {

        if (targetNode == null
                || treeToConcat == null
                || treeToConcat.raiz == null) {
            return;
        }

        Node noIncompleto = this.localizaNoIncompletoRecursivo(targetNode);

        if (!noIncompleto.hasRight()) {
            noIncompleto.setDireita(treeToConcat.raiz);
        } else {
            noIncompleto.setEsquerda(treeToConcat.raiz);
        }

    }

    // Questão 11
    public void removeSubarvore(Node targetNode) {
        if (targetNode == null) {
            return;
        }

        if (targetNode == this.raiz) {
            this.raiz = null; // Remove a árvore inteira
        } else {
            Node parentNode = this.localizaNodePaiRecursivo(this.raiz, targetNode);
            if (parentNode.getDireita() == targetNode) {
                parentNode.setDireita(null);
            } else {
                parentNode.setEsquerda(null);
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
            System.out.printf(node.getValor() + " ");
            this.printPreOrderRecursive(node.getEsquerda());
            this.printPreOrderRecursive(node.getDireita());
        }
    }

}
