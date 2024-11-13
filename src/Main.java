package src;

import src.classes.Lista;
import src.classes.Tree;

// @SuppressWarnings("unused")
public class Main {

    public static void main(String[] args) {

        Tree tree = Tree.getTree(21, 5);

        System.out.println("Altura do árvore: " + tree.altura());
        System.out.println("Quantidade de nós: " + tree.numNo());
        tree.printPreOrder();
        System.out.println();

        System.out.print("Lista a partir da árvore: ");
        Lista listaDaTree = tree.toList();
        listaDaTree.printLista();
        System.out.println();

        System.out.print("Lista apenas com folhas da árvore: ");
        Lista listaDaTreeLeefOnly = tree.toListLeefOnly();
        listaDaTreeLeefOnly.printLista();
        System.out.println();

        tree.removeNo(tree.localizarNoIncompleto());
        System.out.println("Altura do árvore: " + tree.altura());
        System.out.println("Quantidade de nós: " + tree.numNo());
        tree.printPreOrder();
        System.out.println();

    }

}
