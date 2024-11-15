package src.classe_especial;

import src.classes.Node;

// Questão 13
public class Neuro extends Criteria {

    @Override
    public Node apply(Node refNo, int valor) {
        if (refNo == null) {
            return null;
        }
        
        if (valor < refNo.getPeso()) {
            return refNo.getEsquerda();
        } else { // Se igual ou maior, retorna o nó da direita
            return refNo.getDireita();
        }
    }
}
