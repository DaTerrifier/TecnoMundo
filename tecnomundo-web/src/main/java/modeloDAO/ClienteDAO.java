package modeloDAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Producto;

public class ClienteDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public boolean ExisteCorreo(String correo) {
        try {
            cn = Conexion.getConnection();
            String sql = "select count(1) from Cliente where correo = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();

            if (rs.next()) {
                return (rs.getInt(1) > 0);
            }

        } catch (Exception ex) {
            ex.printStackTrace(); // Es importante imprimir la excepción para saber qué salió mal
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public int Guardar(Cliente obj) {
        int result = 0;

        try {
            cn = Conexion.getConnection();
            String sql = "insert into Cliente(nombres, apellidos, telefono, correo, password) "
                    + "values(?, ?, ?, ?, ?)";
            ps = cn.prepareStatement(sql);

            // Establecer los valores de los parámetros usando setString
            ps.setString(1, obj.getNombres().trim());    // Establece el valor de "nombres"
            ps.setString(2, obj.getApellidos().trim());  // Establece el valor de "apellidos"
            ps.setString(3, obj.getTelefono().trim());   // Establece el valor de "telefono"
            ps.setString(4, obj.getCorreo().trim());     // Establece el valor de "correo"
            ps.setString(5, obj.getPassword());   // Establece el valor de "password"

            // Ejecutar la consulta
            result = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace(); // Es importante imprimir la excepción para saber qué salió mal
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}
