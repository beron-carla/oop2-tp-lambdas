package ejercicios.lambdas.permission1;


import java.util.Collections;
import java.util.List;

public class Productos {
    public static final String SIN_PERMISOS = "No tiene los permisos necesarios";
    private List<Producto> productos;
    private SecuritySubSystem security;

    public Productos(List<Producto> productos, SecuritySubSystem security) {
        this.security = security;
        this.productos = productos;
    }

    public void addProducto(String userId, Producto producto) {
        ejecutarConCheck(userId, () -> this.productos.add(producto));
    }

    private void ejecutarConCheck(String userId, Runnable runnable) {
        checkId(userId);
        runnable.run();
    }

    public void removeProducto(String userId, Producto producto) {
        ejecutarConCheck(userId, () -> this.productos.remove(producto));
    }

    public List<Producto> listAll(String userId) {
        checkId(userId);
        return Collections.unmodifiableList(this.productos);
    }

    private void checkId(String userId) {
        if (!this.security.checkPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
    }

    int cantidad() {
        return this.productos.size();
    }

    boolean contiene(Producto unProducto) {
        return this.productos.contains(unProducto);
    }
}

