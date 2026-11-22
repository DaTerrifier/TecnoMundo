package modeloDAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Pedido;
import modelo.DetallePedido;
import java.util.List;
import modelo.Producto;

public class PedidoDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int GenerarPedido(Pedido obj) {
        int result = 0;
        try {
            cn = Conexion.getConnection();
            cn.setAutoCommit(false); // Iniciar transacción

            // Inserción en la tabla Pedido
            String sqlPedido = "INSERT INTO Pedido (id_cli, fecha_ped, total, estado) VALUES (?, NOW(), ?, ?)";
            ps = cn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, obj.getCliente().getIdCliente());
            ps.setDouble(2, obj.getTotal());
            ps.setString(3, obj.getEstado());
            result = ps.executeUpdate();

            if (result > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idPed = rs.getInt(1);

                    ps = cn.prepareStatement("INSERT INTO Detalle_Pedido"
                            + "(id_ped, id_prod, precio, cantidad) VALUES (?, ?, ?, ?)");

                    for (DetallePedido carrito : obj.getDetalles()) {
                        ps.setInt(1, idPed);
                        ps.setInt(2, carrito.getProducto().getIdProd());
                        ps.setDouble(3, carrito.getProducto().getPrecio());
                        ps.setInt(4, carrito.getCantidad());
                        ps.executeUpdate();
                    }
                    cn.commit(); // Confirmar transacción    

                }
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                    result = 0;
                }
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception closeEx) {
                closeEx.printStackTrace();
            }
        }

        return result;
    }

    public ArrayList<Pedido> ListarPorIdCliente(int id) {
        ArrayList<Pedido> lista = new ArrayList<>();
        try {
            cn = Conexion.getConnection();
            String sql = "select * from Pedido where id_cli = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pedido obj = new Pedido();
                obj.setIdPedido(rs.getInt("id_ped"));
                obj.setFecha(rs.getString("fecha_ped"));
                obj.setTotal(rs.getDouble("total"));
                obj.setEstado(rs.getString("estado"));
                obj.setDetalles(ListaDetallePorIdPed(obj.getIdPedido()));
                lista.add(obj);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        return lista;
    }

    public ArrayList<DetallePedido> ListaDetallePorIdPed(int idPed) {
        ArrayList<DetallePedido> lista = new ArrayList<>();
        try {
            Connection cn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            String sql = "SELECT p.imagen, p.nombre, p.precio, d.cantidad\n"
                    + "FROM Detalle_Pedido d\n"
                    + "INNER JOIN Producto p ON p.id_prod = d.id_prod\n"
                    + "WHERE d.id_ped = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idPed);
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto();
                DetallePedido d = new DetallePedido();
                p.setImagen(rs.getString("imagen"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setProducto(p);
                lista.add(d);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
        return lista;
    }

}
