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

    public boolean temEsquerda() {
        return (this.esquerda != null);
    }

    public boolean temDireita() {
        return (this.direita != null);
    }

    @Override
    public String toString() {
        return "" + this.valor;
    }

}

public class Tree {

    Node raiz;

    public Tree(int valor) {
        this.raiz = new Node(valor);
    }

    // Questão 2
    public void addNo(Boolean dir, int valor, Node refNo) {

	if(refNo == null){
		return;
	}

        Node novoNode = new Node(valor);

        if (dir == true) {
            while (refNo.direita != null) {
                refNo = refNo.direita;
            }
            refNo.direita = novoNode;

        } else {
            while (refNo.esquerda != null) {
                refNo = refNo.esquerda;
            }
            refNo.esquerda = novoNode;
        }

    }

    // Questão 3
    public Boolean randomBoolean() {
        return (new Random().nextBoolean());
    }

    // Questão 3
    public Node localizarNoIncompleto() {
        return localizaNoIncompletoRecursivo(this.raiz);
    }

    // Questão 3
    private Node localizaNoIncompletoRecursivo(Node node) {

	if (node == null){
		return null;			
	}

        if (node.direita != null && node.esquerda != null) {
            return this.localizaNoIncompletoRecursivo(this.randomBoolean()
                    ? node.direita
                    : node.esquerda);
        } else {
            return node;
        }

    }

    public Node localizaFolha() {
        return this.localizaFolhaRecursivo(this.raiz);
    }

    private Node localizaFolhaRecursivo(Node node) {
	
	if (node == null){
		return null;
	}

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

    private Node localizaNodePaiRecursivo(Node noPai, Node noAlvo) {
        if (noPai == null) {
            return null;
        }

        if (noPai.direita == noAlvo || noPai.esquerda == noAlvo) {
            return noPai;
        }

        Node ladoDireito = this.localizaNodePaiRecursivo(noPai.direita, noAlvo);
        if (ladoDireito != null) {
            return ladoDireito;
        }
        return this.localizaNodePaiRecursivo(noPai.esquerda, noAlvo);
    }

    public void removeNo(Node noAlvo) {
	
	if (noAlvo == null){
		return;
	}

        if (this.raiz == noAlvo) {
            if (noAlvo.direita != null || noAlvo.esquerda != null) {
                this.raiz = (noAlvo.direita != null) ? noAlvo.direita : noAlvo.esquerda;
                Node folha = this.localizaFolhaRecursivo(this.raiz);
                if (this.raiz == noAlvo.direita) {
                    folha.esquerda = noAlvo.esquerda; 
                } else {
                    folha.direita = noAlvo.direita; 
                }
            } else {
                this.raiz = null;
            }
            noAlvo.direita = noAlvo.esquerda = null;
            return;
        }

        Node paiDoAlvo = this.localizaNodePaiRecursivo(this.raiz, noAlvo);
        if (paiDoAlvo == null) {
            return;
        }

        Node filhoElegido;

        // Decide qual filho do nó alvo tomará seu lugar, pode retornar null
        if (noAlvo.direita != null && noAlvo.esquerda != null) {
            filhoElegido = this.randomBoolean() ? noAlvo.direita : noAlvo.esquerda;
        } else {
            filhoElegido = (noAlvo.direita != null) ? noAlvo.direita : noAlvo.esquerda;
        }

        if (filhoElegido != null) {
            // Verifica de qual lado o nó alvo está em relação ao seu pai
            if (paiDoAlvo.direita == noAlvo) {
                paiDoAlvo.direita = filhoElegido;
            } else {
                paiDoAlvo.esquerda = filhoElegido;
            }

            // Verifica de qual lado o filhoElegido está em relação ao nó alvo
            Node folhaFilhoElegido = this.localizaFolhaRecursivo(filhoElegido);
            if (noAlvo.direita == filhoElegido) {
                folhaFilhoElegido.esquerda = noAlvo.esquerda; // Mover o filho não escolhido
            } else {
                folhaFilhoElegido.direita = noAlvo.direita; // Mover o filho não escolhido
            }
        }
        noAlvo.direita = noAlvo.esquerda = null; // Limpa as referências
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
