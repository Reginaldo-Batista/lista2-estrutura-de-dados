package main;
import classes.Lista;
import classes.Tree;

@SuppressWarnings("unused")
public class Main {

    public static void main(String[] args) {

        Tree tree = new Tree(21);

        Lista lista = new Lista();

        lista.addElemento(18, true);
        lista.addElemento(15, true);
        lista.addElemento(13, true);
        lista.addElemento(77, true);
        lista.addElemento(51, true);

        tree.addLista(lista);

        System.out.println("Altura do tree: " + tree.altura());
        System.out.println("Quantidade de nós: " + tree.numNo());
        tree.printPreOrder();

        Lista listaDaTree = tree.toList();

        listaDaTree.printLista();

    }

}
