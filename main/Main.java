package main;
import classes.Lista;
import classes.Tree;

@SuppressWarnings("unused")
public class Main {

    public static void main(String[] args) {

        Lista lista = new Lista();
        lista.addElemento(18, true);
        lista.addElemento(15, true);
        lista.addElemento(11, true);
        lista.addElemento(17, true);
        lista.addElemento(99, true);
        lista.addElemento(20, true);
        lista.addElemento(12, true);
        lista.addElemento(14, true);
        lista.addElemento(15, true);
        lista.addElemento(30, true);
        lista.addElemento(31, true);
        lista.addElemento(1000, true);
        
        Tree tree = new Tree(21);
        tree.addLista(lista);

        System.out.println("Altura do árvore: " + tree.altura());
        System.out.println("Quantidade de nós: " + tree.numNo());
        tree.printPreOrder();
        System.out.println();

        System.out.printf("Lista a partir da árvore: ");
        Lista listaDaTree = tree.toList();
        listaDaTree.printLista();
        System.out.println();

        System.out.printf("Lista apenas com folhas da árvore: ");
        Lista listaDaTreeLeefOnly = tree.toListLeefOnly();
        listaDaTreeLeefOnly.printLista();
        System.out.println();

        tree.removeNo(tree.localizarNoIncompleto());
        tree.printPreOrder();

    }

}
