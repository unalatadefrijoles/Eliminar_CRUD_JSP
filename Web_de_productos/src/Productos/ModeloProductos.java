package Productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.sql.DataSource;

public class ModeloProductos {

	private DataSource origenDatos;
	
	public ModeloProductos(DataSource origenDatos){
			
		this.origenDatos = origenDatos;
	}
	
	public List<Productos> getProductos() throws Exception{
		
		List<Productos> productos = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResulset = null;
		
		//Establecer conexion
		miConexion= origenDatos.getConnection();
		
		//Sentencia SLQ
		String miSql = "SELECT * FROM PRODS";
		miStatement = miConexion.createStatement();
		
		//Ejecutar SQL
		miResulset = miStatement.executeQuery(miSql);
		
		while(miResulset.next()) {
			String c_art=miResulset.getString("CODIGOARTICULO");
			String seccion=miResulset.getString("SECCION");
			String n_art=miResulset.getString("NOMBREARTICULO");
			double precio=miResulset.getDouble("PRECIO");
			Date fecha=miResulset.getDate("FECHA");
			String importado=miResulset.getString("IMPORTADO");
			String p_orig=miResulset.getString("PAISDEORIGEN");
			
			Productos temProd = new Productos(c_art,seccion,n_art,precio,fecha,importado,p_orig);
			
			productos.add(temProd);
			
		}
		
		return productos;
		
	}

	public void agregarElNuevoProducto(Productos nuevoProducto) throws Exception {
		// TODO Auto-generated method stub
		
		//Obtener la conexion con la BD
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		
		try {
			miConexion = origenDatos.getConnection();
		
		
		//Crear la instruccion SQL que inserte el producto
		
		String sql = "INSERT INTO PRODS (CODIGOARTICULO, SECCION, NOMBREARTICULO, PRECIO, FECHA, IMPORTADO, PAISDEORIGEN)"+
		"VALUES(?,?,?,?,?,?,?)";
		miStatement = miConexion.prepareStatement(sql);
		
		
		//Establecer los parametros para el producto
		
		miStatement.setString(1,nuevoProducto.getcArt());
		miStatement.setString(2,nuevoProducto.getSeccion());
		miStatement.setString(3,nuevoProducto.getnArt());
		miStatement.setDouble(4,nuevoProducto.getPrecio());
		
		java.util.Date utilDate = nuevoProducto.getFecha();
		java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
		miStatement.setDate(5, fechaConvertida);
		
		
		miStatement.setString(6,nuevoProducto.getImportado());
		miStatement.setString(7,nuevoProducto.getpOrig());
		
		//Ejecutar el SQL
		
		miStatement.execute();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			miStatement.execute();
			miStatement.close();
		}
	}

	public Productos getProducto(String codigoArticulo) {
		// TODO Auto-generated method stub
		
		Productos elProducto = null;
		Connection miConexion= null;
		PreparedStatement miStatement = null;
		ResultSet miResulSet = null;
		
		String cArticulo = codigoArticulo;
		
		try {
		//Establecer la conexion con la bd
		miConexion = origenDatos.getConnection();
		
		//Crear el sql que busque el producto
		String sql="SELECT * FROM PRODS WHERE CODIGOARTICULO = ?";
		
		//Crear la consulta preparada
		miStatement = miConexion.prepareStatement(sql);
		
		//Establecer los parametros
		miStatement.setString(1, codigoArticulo);
		
		//Ejecutar sql
		miResulSet = miStatement.executeQuery();
		
		//Obtener la respuesta
		if(miResulSet.next()) {

			String c_art=miResulSet.getString("CODIGOARTICULO");
			String seccion=miResulSet.getString("SECCION");
			String n_art=miResulSet.getString("NOMBREARTICULO");
			double precio=miResulSet.getDouble("PRECIO");
			Date fecha=miResulSet.getDate("FECHA");
			String importado=miResulSet.getString("IMPORTADO");
			String p_orig=miResulSet.getString("PAISDEORIGEN");
			
			elProducto = new Productos(c_art,seccion,n_art,precio,fecha,importado,p_orig);
			
		}else {
			throw new Exception("No hemos encontrado el producto con el codigo: "+ codigoArticulo);
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return elProducto;
	}

	public void actualizarProducto(Productos productoActualizado) throws Exception {
		// TODO Auto-generated method stub
		//Establecer la conexion
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		
		try {
		
		miConexion = origenDatos.getConnection();
		
		//Crear el sql
		String sql = "UPDATE PRODS SET SECCION =?, NOMBREARTICULO=?, PRECIO=?, FECHA=?, IMPORTADO=?, PAISDEORIGEN=?"+
				"WHERE CODIGOARTICULO=?";
		
		//Crear consulta preparada
		miStatement=miConexion.prepareStatement(sql);
		
		//Establecer parametros
		miStatement.setString(1, productoActualizado.getSeccion());
		miStatement.setString(2, productoActualizado.getnArt());
		miStatement.setDouble(3, productoActualizado.getPrecio());

		
		java.util.Date utilDate = productoActualizado.getFecha();
		java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
		miStatement.setDate(4, fechaConvertida);
		
		miStatement.setString(5, productoActualizado.getImportado());
		miStatement.setString(6, productoActualizado.getpOrig());
		miStatement.setString(7, productoActualizado.getcArt());
		
		//Ejecutar el sql
		miStatement.execute();
		}finally {
			miStatement.execute();
			 miStatement.close();
		}
		
	}

	public void eliminarProducto(String codArticulo) throws Exception {
		// TODO Auto-generated method stub
		
		//Establecer la conexion con la BD
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		
		try {
		miConexion = origenDatos.getConnection();
		
		//Crear el SQL para eliminar
		String sql = "DELETE FROM PRODS WHERE CODIGOARTICULO =?";
		
		//Preparar la consulta
		miStatement = miConexion.prepareStatement(sql);
		
		//Establecer parametros
		miStatement.setString(1, codArticulo);
		
		//Ejecutar el sql
		miStatement.execute();
		}finally{
			 miStatement.execute();
			 miStatement.close();
		}
	}
	
}
