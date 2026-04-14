package ejercicios.lambdas.permission2;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Productos {
    public static final String SIN_PERMISOS = "No tiene los permisos necesarios";
    private List<Producto> productos;
    private SecuritySubSystem security;

    public Productos(List<Producto> productos, SecuritySubSystem security) {
        this.security = security;
        this.productos = productos;
    }

    public <T> T ejecutarSitienePermiso(Supplier<Boolean> permissionCheck, Supplier<T> supplier) {
        if (!permissionCheck.get()) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        return supplier.get();
    }

    public void addProducto(String userId, Producto producto) {
        ejecutarSitienePermiso(() -> this.security.checkAddPermission(userId), () -> this.productos.add(producto));
    }

    public void removeProducto(String userId, Producto producto) {
        ejecutarSitienePermiso(() -> this.security.checkRemovePermission(userId), () -> this.productos.remove(producto));
    }

    public List<Producto> listAll(String userId) {
        return ejecutarSitienePermiso(() -> this.security.checkListPermission(userId), () -> Collections.unmodifiableList(this.productos));

    }

    int cantidad() {
        return this.productos.size();
    }

    boolean contiene(Producto unProducto) {
        return this.productos.contains(unProducto);
    }
}
