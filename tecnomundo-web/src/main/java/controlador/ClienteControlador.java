package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modeloDAO.ClienteDAO;

public class ClienteControlador extends HttpServlet {

    private final String PagNuevo = "PagRegistrarCliente.jsp";
    private ClienteDAO cliDao = new ClienteDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "nuevo";  // Valor por defecto si action es null
        }
        try {
            switch (action) {
                case "nuevo":
                    Nuevo(request, response);
                    break;
                case "guardar":
                    Guardar(request, response);
                    break;
                default:
                    throw new ServletException("Acción no válida: " + action);  // Manejamos el error de forma más controlada
            }
        } catch (Exception ex) {
            ex.printStackTrace();  // Imprimir detalles de la excepción en la consola del servidor
            throw new ServletException("Error al procesar la solicitud: " + ex.getMessage(), ex);
        }
    }

    protected void Guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Cliente obj = new Cliente();
        obj.setNombres(request.getParameter("nombres"));
        obj.setApellidos(request.getParameter("apellidos"));
        obj.setTelefono(request.getParameter("telefono"));
        obj.setCorreo(request.getParameter("correo"));
        obj.setPassword(request.getParameter("password"));
        
        if(cliDao.ExisteCorreo(obj.getCorreo().trim()) == false){
            int result = cliDao.Guardar(obj);
        
        if(result > 0){
            request.getSession().setAttribute("success", "Cuenta Registrada");
            response.sendRedirect("ClienteControlador?action=nuevo");
            return;
        }else{
            request.getSession().setAttribute("error", "No se pudo Registrar la Cuenta");
        }
        }else{
            request.getSession().setAttribute("error", "El correo "+obj.getCorreo()+" "
                    + "ya se encuentra registrado");
        }
        
        
        
        
        
        
        request.setAttribute("cliente", obj);
        request.getRequestDispatcher(PagNuevo).forward(request, response);
    }

    protected void Nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("cliente", new Cliente());
        request.getRequestDispatcher(PagNuevo).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
