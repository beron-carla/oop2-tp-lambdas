package ejercicios.filtros;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        Persona p1 = new Persona("Mara", "Perez");
        Persona p2 = new Persona("Edu", "Perez");
        Persona p3 = new Persona("Tomas", "Perez");
        Persona p4 = new Persona("Lara", "Perez");
        var ps = new Personas(p1, p2, p3, p4);

//        var nombrePar = ps.nombresCuyaCantidadDeLetrasEsPar(ps.listaDePersonas());
//        var nombresConE = ps.nombresQueEmpiezanConE(ps.listaDePersonas());
//
//        for (Persona p : nombrePar) {
//            System.out.println(p);
//        }
//        for (Persona p : nombresConE) {
//            System.out.println(p);
//        }

        //parametrizar el comportamiento - uso de <Predicate<Persona> criterio>
        List<Persona> empiezaConE = ps.filtroDePersonas(ps.listaDePersonas(), p -> p.empiezaCon("E"));
        List<Persona> nombrePar = ps.filtroDePersonas(ps.listaDePersonas(), p -> p.nombrePar());

        //mostrar las listas
        empiezaConE.forEach(p -> System.out.println("empieza con e: " + p.nombre()));
        nombrePar.forEach(p -> System.out.println("es nombre par: " + p.nombre()));


    }
}
