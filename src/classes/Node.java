package src.classes;

// Questão 1
public class Node {

    private int valor;
    private Node esquerda;
    private Node direita;
    // Questão 13
    private int peso;

    public Node(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Node getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }

    public Node getDireita() {
        return direita;
    }

    public void setDireita(Node direita) {
        this.direita = direita;
    }

    public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public boolean hasLeft() {
        return (this.esquerda != null);
    }

    public boolean hasRight() {
        return (this.direita != null);
    }

    public boolean isLeaf() {
        return (this.esquerda == null && this.direita == null);
    }

    @Override
    public String toString() {
        return "" + this.valor;
    }

}
