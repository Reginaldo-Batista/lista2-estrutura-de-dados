package classes;

class Bloco {

    int valor;
    Bloco prox;

}

public class Lista {
    
    Bloco inicio;

    public Bloco addElemento(int valor, boolean posicaoIncio) {

        Bloco novo = null;

        if (posicaoIncio == true) {
            novo = this.addElementoInicio(valor);
        } else {
            novo = this.addElementoFim(valor);
        }
        return novo;
    }

    public Bloco addElemento(int valor, int pos) {

        if (pos <= 1) {
            return this.addElemento(valor, true);
        }

        Bloco blocoAnterior = this.localizarBloco(pos - 1);

        while (blocoAnterior == null) {
            this.addElemento(0, false);
            blocoAnterior = this.localizarBloco(pos - 1);
        }

        Bloco novo = new Bloco();
        novo.valor = valor;
        novo.prox = blocoAnterior.prox;
        blocoAnterior.prox = novo;
        return novo;

    }

    public Bloco removerElementoFim() {

        Bloco result = null;

        if (this.isVazia()) {
            return result;
        }

        if (this.tamanho() == 1) {
            // this.inicio = null;
            result = this.removerElementoInicio();

        } else {

            Bloco target = this.localizarBloco(this.tamanho() - 1);

            result = target.prox;
            target.prox = null;
        }

        return result;
    }

    public Bloco removerElementoInicio() {

        Bloco result = this.inicio;
        if (!this.isVazia()) {

            this.inicio = this.inicio.prox;
        }

        return result;
    }

    public boolean isVazia() {

        return (this.inicio == null);
    }

    private Bloco addElementoInicio(int valor) {

        Bloco novo = new Bloco();
        novo.valor = valor;

        novo.prox = this.inicio;
        this.inicio = novo;

        return novo;
    }

    private Bloco addElementoFim(int valor) {

        Bloco novo = null;

        Bloco fim = this.localizarBloco(this.tamanho());

        if (fim == null) {
            // Se a lista estiver vazia:
            novo = this.addElementoInicio(valor);
        } else {

            novo = new Bloco();
            novo.valor = valor;

            fim.prox = novo;
        }

        return novo;
    }

    public Bloco localizarBloco(int pos) {

        Bloco aux = this.inicio;
        int cont = 0;

        while (aux != null && cont < pos - 1) {
            cont++;
            aux = aux.prox;
        }
        return aux;
    }

    public int tamanho() {

        Bloco aux = this.inicio;
        int cont = 0;

        while (aux != null) {

            cont++;
            aux = aux.prox;

        }

        return cont;
    }

    public void printLista() {

        Bloco aux = this.inicio;

        if (aux == null) {
            System.out.println("Vazia!");
            return;
        }

        System.out.printf("Existem %d bloco(s):\n", this.tamanho());
        while (aux != null) {
            System.out.printf(aux.valor + " -> ");
            aux = aux.prox;
        }
        System.out.println("null");
    }

    public Lista inverteLista() {
        Bloco aux = this.inicio;
        Lista listaInvert = new Lista();
        while (aux != null) {
            listaInvert.addElemento(aux.valor, true);
            aux = aux.prox;
        }
        return listaInvert;
    }

    public void removeDuplicado() {

        Bloco referencia = this.inicio;
        Bloco anteriorAnalisado;
        Bloco analisado;

        while (referencia != null) {

            anteriorAnalisado = referencia;
            analisado = referencia.prox;

            while (analisado != null) {

                if (referencia.valor == analisado.valor) {

                    anteriorAnalisado.prox = analisado.prox;
                    analisado.prox = null; // Opcional
                    analisado = anteriorAnalisado.prox;

                } else {

                    analisado = analisado.prox;
                    anteriorAnalisado = anteriorAnalisado.prox;

                }

            }

            referencia = referencia.prox;

        }

    }
}
