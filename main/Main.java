package main;
import classes.Lista;
import classes.Tree;

@SuppressWarnings("unused")
public class Main {

    public static void main(String[] args) {

        Tree tree = new Tree(21);
        // Testando, depois fazer o git restore main/Main.java
        tree.addNo(tree.randomBoolean(), 12, tree.localizarNoIncompleto());
        tree.addNo(tree.randomBoolean(), 11, tree.localizarNoIncompleto());
        tree.addNo(tree.randomBoolean(), 10, tree.localizarNoIncompleto());
        tree.addNo(tree.randomBoolean(), 9, tree.localizarNoIncompleto());
        tree.addNo(tree.randomBoolean(), 1, tree.localizarNoIncompleto());

        tree.printPreOrder();

        // Lista lista = new Lista();

        // lista.addElemento(18, true);
        // lista.addElemento(15, true);
        // lista.addElemento(19, true);

        // tree.addLista(lista);

        // System.out.println("Altura do tree: " + tree.altura());
        // System.out.println("Quantidade de nós: " + tree.numNo());
        // tree.printPreOrder();
        // System.out.println();

        // System.out.printf("Lista a partir da árvore: ");
        // Lista listaDaTree = tree.toList();
        // listaDaTree.printLista();
        // System.out.println();

        // System.out.printf("Lista a partir da árvore, só com folhas: ");
        // Lista listaDaTreeLeefOnly = tree.toListLeefOnly();
        // listaDaTreeLeefOnly.printLista();
        // System.out.println();

    }

}
