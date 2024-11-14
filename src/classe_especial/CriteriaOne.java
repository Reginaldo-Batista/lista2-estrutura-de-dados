package src.classe_especial;

import src.classes.Node;

public class CriteriaOne extends Criteria {

    @Override
    public Node apply(Node refNo, int valor) {
        if (refNo == null) {
            return null;
        }

        if (valor < refNo.getValor()) {
            return refNo.getEsquerda();
        } else { // Se igual ou maior, retorna o nó da direita
            return refNo.getDireita();
        }
    }

}
