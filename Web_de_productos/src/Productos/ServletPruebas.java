package Productos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;


/**
 * Servlet implementation class ServletPruebas
 */
@WebServlet("/ServletPruebas")
public class ServletPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Definir o establecer el DataSource
	
	@Resource(name="jdbc/Productos")
	private DataSource miPool;

    /**
     * Default constructor. 
     */
    public ServletPruebas() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Crear el objeto printWritter
		PrintWriter salida = response.getWriter();
		response.setContentType("text/plain");
		
		//Crear la conexion con la BD
		
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResulset = null;
		
		try {
			miConexion = miPool.getConnection();
			String miSql = "SELECT * FROM PRODS";
			miStatement = miConexion.createStatement();
			
			miResulset = miStatement.executeQuery(miSql);
			
			while(miResulset.next()) {
				String nombre_articulo = miResulset.getString(3);
				salida.println(nombre_articulo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
