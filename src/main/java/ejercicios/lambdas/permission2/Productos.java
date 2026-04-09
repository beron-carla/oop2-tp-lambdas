package ejercicios.lambdas.permission2;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Productos {
    public static final String SIN_PERMISOS = "No tiene los permisos necesarios";
    private List<Producto> productos;
    private SecuritySubSystem security;

    public Productos(List<Producto> productos, SecuritySubSystem security) {
        this.security = security;
        this.productos = productos;
    }

    public void addProducto(String userId, Producto producto) {
        checkId(userId, (id) -> this.security.checkAddPermission(userId));
        this.productos.add(producto);
    }

    private void checkId(String userId, Predicate<String> verificarID) {
        if (!verificarID.test(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
    }

    public void removeProducto(String userId, Producto producto) {
        checkId(userId, (id) -> this.security.checkRemovePermission(userId));
        this.productos.remove(producto);
    }

    public List<Producto> listAll(String userId) {
        checkId(userId, (id) -> this.security.checkListPermission(userId));
        return Collections.unmodifiableList(this.productos);
    }

    int cantidad() {
        return this.productos.size();
    }

    boolean contiene(Producto unProducto) {
        return this.productos.contains(unProducto);
    }
}
