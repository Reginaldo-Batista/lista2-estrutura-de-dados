package main;
import classes.Tree;

@SuppressWarnings("unused")
public class Main {

    public static void main(String[] args) {

        Tree tree = new Tree(21);

        tree.addNo(false, 5, tree.localizarNoIncompleto());
        tree.addNo(true, 6, tree.localizarNoIncompleto());
        tree.addNo(false, 7, tree.localizarNoIncompleto());
        tree.addNo(true, 8, tree.localizarNoIncompleto());

        System.out.println("Altura do tree: " + tree.altura());
        tree.printPreOrder();

    }

}
