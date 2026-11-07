package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.estructuras.ArbolABB;

public class PruebaABB {

    public static void main(String[] args) {

        ArbolABB<Integer> arbol = new ArbolABB<>();

        int[] numeros = {
            45, 33, 29, 28, 22, 32, 34, 39,
            36, 43, 60, 47, 46, 50, 49, 55,
            70, 69, 65, 86, 79, 88
        };

        for (int numero : numeros) {
            arbol.insertar(numero);
        }

        System.out.println("PUNTO 5 - ARBOL ABB");
        System.out.println();

        System.out.println("Preorden:");
        System.out.println(arbol.preorden());

        System.out.println("Inorden:");
        System.out.println(arbol.inorden());

        System.out.println("Postorden:");
        System.out.println(arbol.postorden());

        System.out.println("Amplitud:");
        System.out.println(arbol.amplitud());
    }
}
