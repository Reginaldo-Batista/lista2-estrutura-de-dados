package src.classe_especial;

import src.classes.Node;

// Questão 12
public class CriteriaTwo extends Criteria {

    @Override
    public Node apply(Node refNo, int valor) {
        if (refNo == null) {
            return new Node(valor);
        }
        if (valor < refNo.getValor()) {
            return refNo.getDireita();
        } else { // Se igual ou maior, retorna o nó da esquerda
            return refNo.getEsquerda();
        }
    }

}
