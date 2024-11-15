package src.classe_especial;

import src.classes.Node;
import src.classes.Tree;

// Questão 12
public class TreeCriteria extends Tree {

    // Pode receber CriteriaOne, CriteriaTwo ou Neuro
    private Criteria criteria;

    public Node getNoFilho(Node refNode, int valor) {
        return criteria.apply(refNode, valor);
    }

    public TreeCriteria(int rootValue, Criteria criteria) {
        super(rootValue);
        this.criteria = criteria;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

}
