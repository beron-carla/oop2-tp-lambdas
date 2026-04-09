package ejercicios;

interface A {
    void metodo();
}

interface B {
    void metodo(String b);
}

interface C {
    boolean metodo(String c);
}

interface D<T, R> {
    R metodo(T c);
}

class AprendiendoLambdas {
    public void unMetodo(A a) {
        a.metodo();
    } //primero

    public void unMetodo(B b) {
        b.metodo("unString");
    } //segundo

    public void unMetodo(C c) { //tercero
        System.out.println(c.metodo("abcdefg") ? "true" : "false");
    }

    public void unMetodo(D<Long, Long> d) { //cuarto
        d.metodo(10L);
    }

}

public class Ejercicio1 {
    public static void main(String[] args) {
        AprendiendoLambdas a = new AprendiendoLambdas();

        //Invoca al segundo metodo. Es la misma firma, recibe un parametro tipo String
        a.unMetodo((b) -> {
            System.out.println("abcd" + b);
        });

        //invoca al primer metodo
        a.unMetodo(() -> System.out.println("abcd"));

        //Invoca al tercer metodo
        a.unMetodo((String variable) -> {
            System.out.println("abcd");
            return true;
        });

        //Invoca al cuarto metodo
        a.unMetodo((Long variable) -> {
            System.out.println("abcd");
            return 10L;
        });

        //Utilizando un lambda dado que imprima true si el largo del String es par, false en caso
        //contrario
        a.unMetodo((String variable) -> {
            if (variable.length() % 2 == 0) {
                return true;
            }
            return false;
        });

        //b. Utilizando un lambda dado que imprima true si el String empieza con “a” minúscula,false en caso contrario.

        a.unMetodo((String variable) -> Character.isLowerCase(variable.charAt(0)) && variable.charAt(0) == 'a');
    }
}
