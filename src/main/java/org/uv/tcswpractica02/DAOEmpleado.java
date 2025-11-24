package org.uv.tcswpractica02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOEmpleado implements IDAOGeneral<PojoEmpleado, Long> {

    @Override
    public boolean guardar(PojoEmpleado pojo) {
        ConexionDB con = ConexionDB.getInstance();
        TransactionDB t = new TransactionDB<PojoEmpleado>(pojo) {

            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "insert into empleados2 (clave, nombre, direccion, telefono)"
                            + "values (?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setLong(1, pojoDB.getClave());
                    pst.setString(2, pojoDB.getNombre());
                    pst.setString(3, pojoDB.getDireccion());
                    pst.setString(4, pojoDB.getTelefono());
                    pst.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        return con.execute(t);

//        String sql = "insert into empleados2 (clave, nombre, direccion, telefono)"
//                + "values ('" + pojo.getClave() + "','" + pojo.getNombre() + "','" + pojo.getDireccion() + "','" + pojo.getTelefono() + "')";
//
//        ConexionDB con = ConexionDB.getInstance();
//        return con.exectute(sql);
    }

    @Override
    public PojoEmpleado eliminar(Long id) {
        PojoEmpleado pojo = findByID(id);

        String sql = "DELETE FROM empleados2 WHERE clave = ?";

            try (Connection conn = (Connection) ConexionDB.getInstance(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                Logger.getLogger(DAOEmpleado.class.getName())
                        .log(Level.INFO, "Empleado eliminado");
                return pojo;
            } else {
                return null;
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOEmpleado.class.getName())
                    .log(Level.SEVERE, "Error eliminando empleado", e);
            return null;
        }
    }

    @Override
    public PojoEmpleado modificar(PojoEmpleado pojo, Long id) {
        String sql = "UPDATE empleados2 "
                + "SET nombre = ?, "
                + "direccion = ?, "
                + "telefono = ? "
                + "WHERE clave = ?";

        try (Connection conn = ConexionDB.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pojo.getNombre());
            ps.setString(2, pojo.getDireccion());
            ps.setString(3, pojo.getTelefono());
            ps.setLong(4, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                Logger.getLogger(DAOEmpleado.class.getName())
                        .log(Level.INFO, "Empleado actualizado");
                return findByID(id); // Devuelve el registro ya modificado
            } else {
                return null; // No se actualizó ninguna fila
            }

        } catch (SQLException e) {
            Logger.getLogger(DAOEmpleado.class.getName())
                    .log(Level.SEVERE, "Error al actualizar el empleado", e);
            return null;
        }
    }

    @Override
    public PojoEmpleado findByID(Long id) {
        String sql = "SELECT * FROM empleados2 WHERE clave = ?";

        try (Connection conn = ConexionDB.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    PojoEmpleado pojo = new PojoEmpleado();
                    pojo.setClave(res.getLong("clave"));
                    pojo.setNombre(res.getString("nombre"));
                    pojo.setDireccion(res.getString("direccion"));
                    pojo.setTelefono(res.getString("telefono"));
                    return pojo;
                } else {
                    return null; // No hay registro con ese id
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName())
                    .log(Level.SEVERE, "Error buscando empleado por ID", ex);
            return null;
        }
    }

    @Override
    public List<PojoEmpleado> findAll() {
        String sql = "SELECT * FROM empleados2";
        List<PojoEmpleado> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet res = ps.executeQuery()) {

            while (res.next()) {
                PojoEmpleado pojo = new PojoEmpleado();
                pojo.setClave(res.getLong("clave"));
                pojo.setNombre(res.getString("nombre"));
                pojo.setDireccion(res.getString("direccion"));
                pojo.setTelefono(res.getString("telefono"));
                lista.add(pojo);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName())
                    .log(Level.SEVERE, "Error al listar empleados", ex);
            return null;
        }
    }

}
