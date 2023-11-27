package laboratorio12;

import java.io.IOException;
import java.util.Scanner;

public class Laboratorio12 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcion = showMenu(sc);

        while (opcion < 6) {
            while (opcion == -1) {
                System.out.println("Esa no es una opcion valida. Ingresa una opcion valida");
                opcion = showMenu(sc);
            }
            resultado(sc, opcion);
            opcion = showMenu(sc);
        }
        encabezado("HASTA PRONTO");
        sc.nextLine();
        limpiarPantalla();
    }

    static int showMenu(Scanner sc) {
        System.out.print("Presione enter para continuar...");
        sc.nextLine();
        limpiarPantalla();
        encabezado("MENU DE OPCIONES");
        String mensaje
                = "1.- MCD DE 2 NUMEROS ENTEROS"
                + "\n2.- DIVISORES DE UN NUMERO ENTERO"
                + "\n3.- FACTORES PRIMOS DE UN NUMERO ENTERO"
                + "\n4.- NUMERO PRIMO O NO"
                + "\n5.- NUMEROS AMIGOS"
                + "\n6.- SALIR"
                + "\nIngrese su opcion: ";
        System.out.print(mensaje);
        int opcion = Integer.parseInt(sc.nextLine());
        if (opcion > 6 || opcion < 1) {
            opcion = -1;
        }

        return opcion;
    }

    static void mcd(Scanner sc) {
        int num1 = getInt(sc);
        int num2 = getInt(sc);
        System.out.print("El MCD de " + num1 + " y " + num2 + " es: ");
        int mcd = 1;
        for (int i = 2; i <= num1 && i <= num2; i++) {
            if (num1 % i == 0 && num2 % i == 0) {
                mcd *= i;
                num1 /= i;
                num2 /= i;
                i = 1; //Se coloca en 1 para que el i++ lo vuelva 2 a la otra vuelta
            }
        }
        System.out.println(mcd);
    }

    static void divisores(Scanner sc) {
        int num = getInt(sc);
        System.out.println("Los divisores de " + num + " son: ");

        getDivisores(num, true, false);
    }

    static void factoresPrimos(Scanner sc) {
        int num = getInt(sc);
        System.out.println("Los factores primos de " + num + " son: ");

        getDivisores(num, true, true);
    }

    static void esPrimo(Scanner sc) {
        int num = getInt(sc);
        System.out.print("El numero " + num + " ");

        boolean primo = true;

        if (num == 1) {
            primo = false;
        }
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                primo = false;
            }
        }
        if (primo) {
            System.out.println("es primo.");
        } else {
            System.out.println("no es primo.");
        }
    }

    static void amigos(Scanner sc) {
        int num1 = getInt(sc);
        int num2 = getInt(sc);

        System.out.print("Los numeros " + num1 + " y " + num2 + " ");

        int suma1 = getDivisores(num1, false, false);
        int suma2 = getDivisores(num2, false, false);

        if (num1 == suma2 && num2 == suma1) {
            System.out.println("son amigos.");
        } else {
            System.out.println("no son amigos.");
        }
    }

    static void resultado(Scanner sc, int opcion) {
        limpiarPantalla();
        switch (opcion) {
            case 1:
                encabezado("MCD DE 2 NUMEROS ENTEROS");
                mcd(sc);
                break;
            case 2:
                encabezado("DIVISORES DE UN NUMERO ENTERO");

                divisores(sc);
                break;
            case 3:
                encabezado("FACTORES PRIMOS DE UN NUMERO ENTERO");
                factoresPrimos(sc);
                break;
            case 4:
                encabezado("NUMERO PRIMO O NO");
                esPrimo(sc);
                break;
            case 5:
                encabezado("NUMEROS AMIGOS");
                amigos(sc);
                break;
        }
    }

    static void encabezado(String titulo) {
        String formato = "";
        for (int i = 0; i < titulo.length(); i++) {
            formato += "=";
        }
        System.out.println(formato);
        System.out.println(titulo);
        System.out.println(formato);
    }

    static void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }

    static int getInt(Scanner sc) {
        String mensaje = "Ingrese un numero: ";
        System.out.print(mensaje);
        return Integer.parseInt(sc.nextLine());
    }

    static int getDivisores(int num, boolean imprimir, boolean primos) {
        int suma = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                suma += i;
                if (imprimir) {
                    System.out.println("- " + i);
                }
            }
            while (primos && num % i == 0 && i != 1) {
                num /= i;
            }
        }
        return suma - num;
    }
}