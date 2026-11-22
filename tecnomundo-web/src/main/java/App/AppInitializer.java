package App;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import modeloDAO.ProductoDAO;


@WebListener
public class AppInitializer implements ServletContextListener {
        private ProductoDAO prodDAO = new ProductoDAO();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("productos", prodDAO.ListarTodos());
        ServletContext context = sce.getServletContext();
        context.log("Aplicación iniciada");
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("Aplicación finalizada");
        // Código para liberar recursos o cerrar conexiones
    }
}
