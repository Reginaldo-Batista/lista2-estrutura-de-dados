package classes;

public class Lista {

    Bloco inicio;

    public Bloco addElemento(int valor, boolean posicaoInicio) {

        Bloco novo = null;

        if (posicaoInicio == true) {
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
        novo.setValor(valor);
        novo.setProx(blocoAnterior.getProx());
        blocoAnterior.setProx(novo);
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

            result = target.getProx();
            target.setProx(null);
        }

        return result;
    }

    public Bloco removerElementoInicio() {

        Bloco result = this.inicio;
        if (!this.isVazia()) {

            this.inicio = this.inicio.getProx();
        }

        return result;
    }

    public boolean isVazia() {

        return (this.inicio == null);
    }

    private Bloco addElementoInicio(int valor) {

        Bloco novo = new Bloco();
        novo.setValor(valor);

        novo.setProx(this.inicio);
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
            novo.setValor(valor);

            fim.setProx(novo);
        }

        return novo;
    }

    public Bloco localizarBloco(int pos) {

        Bloco aux = this.inicio;
        int cont = 0;

        while (aux != null && cont < pos - 1) {
            cont++;
            aux = aux.getProx();
        }
        return aux;
    }

    public int tamanho() {

        Bloco aux = this.inicio;
        int cont = 0;

        while (aux != null) {

            cont++;
            aux = aux.getProx();

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
            System.out.printf(aux.getValor() + " -> ");
            aux = aux.getProx();
        }
        System.out.println("null");
    }

    public Lista inverteLista() {
        Bloco aux = this.inicio;
        Lista listaInvert = new Lista();
        while (aux != null) {
            listaInvert.addElemento(aux.getValor(), true);
            aux = aux.getProx();
        }
        return listaInvert;
    }

    public void removeDuplicado() {

        Bloco referencia = this.inicio;
        Bloco anteriorAnalisado;
        Bloco analisado;

        while (referencia != null) {

            anteriorAnalisado = referencia;
            analisado = referencia.getProx();

            while (analisado != null) {

                if (referencia.getValor() == analisado.getValor()) {

                    anteriorAnalisado.setProx(analisado.getProx());
                    analisado.setProx(null); // Opcional
                    analisado = anteriorAnalisado.getProx();

                } else {

                    analisado = analisado.getProx();
                    anteriorAnalisado = anteriorAnalisado.getProx();

                }

            }

            referencia = referencia.getProx();

        }

    }
}
