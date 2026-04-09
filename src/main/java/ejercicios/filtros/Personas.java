package ejercicios.filtros;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Personas {
    List<Persona> personas;

    public Personas(Persona... personas) {
        this.personas = List.of(personas);
    }

    public List<Persona> filtroDePersonas(List<Persona> personas, Predicate<Persona> criterio) {
        List<Persona> resultado = new ArrayList<Persona>();
        for (Persona persona : personas) {
            if (criterio.test(persona)) {
                resultado.add(persona);
            }
        }
        return resultado;
    }


    public List<Persona> listaDePersonas() {
        return this.personas;
    }

}
