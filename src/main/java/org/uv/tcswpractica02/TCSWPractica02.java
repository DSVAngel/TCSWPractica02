package org.uv.tcswpractica02;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCSWPractica02 {

    private static final Logger logger = Logger.getLogger(TCSWPractica02.class.getName());
    private static final DAOEmpleado dao = new DAOEmpleado();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            logger.log(Level.INFO, "\n--- Menú Empleados ---\n"
                    + "1. Ver todos los empleados\n"
                    + "2. Buscar empleado por ID\n"
                    + "3. Insertar empleado\n"
                    + "4. Modificar empleado\n"
                    + "5. Eliminar empleado\n"
                    + "0. Salir\n"
                    + "Seleccione una opción:");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    verTodos();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    insertarEmpleado();
                    break;
                case 4:
                    modificarEmpleado();
                    break;
                case 5:
                    eliminarEmpleado();
                    break;
                case 0:
                    logger.log(Level.INFO, "Saliendo del sistema...");
                    break;
                default:
                    logger.log(Level.INFO, "Opción no válida, intente de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void verTodos() {
        List<PojoEmpleado> lista = dao.findAll();
        if (lista != null && !lista.isEmpty()) {
            for (PojoEmpleado pojo : lista) {
                String msg = "Clave: " + pojo.getClave()
                        + "\nNombre: " + pojo.getNombre()
                        + "\nDireccion: " + pojo.getDireccion()
                        + "\nTelefono: " + pojo.getTelefono();
                logger.log(Level.INFO, msg);
            }
        } else {
            logger.log(Level.INFO, "No se encontraron empleados.");
        }
    }

    private static void buscarPorId() {
        logger.log(Level.INFO, "Ingrese la clave del empleado:");
        Long id = sc.nextLong();
        sc.nextLine();
        PojoEmpleado pojo = dao.findByID(id);
        if (pojo != null) {
            String msg = "Clave: " + pojo.getClave()
                    + "\nNombre: " + pojo.getNombre()
                    + "\nDireccion: " + pojo.getDireccion()
                    + "\nTelefono: " + pojo.getTelefono();
            logger.log(Level.INFO, msg);
        } else {
            logger.log(Level.INFO, "Empleado no encontrado.");
        }
    }

    private static void insertarEmpleado() {
        PojoEmpleado pojo = new PojoEmpleado();
        logger.log(Level.INFO, "Ingrese clave:");
        pojo.setClave(sc.nextLong());
        sc.nextLine();
        logger.log(Level.INFO, "Ingrese nombre:");
        pojo.setNombre(sc.nextLine());
        logger.log(Level.INFO, "Ingrese direccion:");
        pojo.setDireccion(sc.nextLine());
        logger.log(Level.INFO, "Ingrese telefono:");
        pojo.setTelefono(sc.nextLine());

        boolean res = dao.guardar(pojo);
        if (res) {
            logger.log(Level.INFO, "Empleado guardado correctamente.");
        } else {
            logger.log(Level.INFO, "Error al guardar empleado.");
        }
    }

    private static void modificarEmpleado() {
        logger.log(Level.INFO, "Ingrese la clave del empleado a modificar:");
        Long id = sc.nextLong();
        sc.nextLine();

        PojoEmpleado pojo = new PojoEmpleado();
        pojo.setClave(id);
        logger.log(Level.INFO, "Nuevo nombre:");
        pojo.setNombre(sc.nextLine());
        logger.log(Level.INFO, "Nueva direccion:");
        pojo.setDireccion(sc.nextLine());
        logger.log(Level.INFO, "Nuevo telefono:");
        pojo.setTelefono(sc.nextLine());

        PojoEmpleado actualizado = dao.modificar(pojo, id);
        if (actualizado != null) {
            logger.log(Level.INFO, "Empleado actualizado correctamente.");
        } else {
            logger.log(Level.INFO, "Error al actualizar empleado.");
        }
    }

    private static void eliminarEmpleado() {
        logger.log(Level.INFO, "Ingrese la clave del empleado a eliminar:");
        Long id = sc.nextLong();
        sc.nextLine();
        PojoEmpleado eliminado = dao.eliminar(id);
        if (eliminado != null) {
            logger.log(Level.INFO, "Empleado eliminado correctamente.");
        } else {
            logger.log(Level.INFO, "Error al eliminar empleado.");
        }
    }
}

/*
ControllerMensaje controller = new ControllerMensaje();
IMensaje msg = new SaludoI();
controller.mostrar(new SaludoI());
controller.mostrar(new DespedidaI());
controller.mostrar(msg);

controller.mostrar( new IMensaje()){
@Override
public void imprimir(){
    Logger.getLogger(IMensaje.class.getName()).log(Level.INFO,"Otro...");
}};

*/
