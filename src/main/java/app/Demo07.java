package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;
import model.Usuario;

// GUI!!!

public class Demo07 {
	
	// Listado de los Productos
	public static void main(String[] args) {
		// 1. obtener la conexion --> llamar a la unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		// 2. crear un manejador de entidades segun la conexion
	    EntityManager em = fabrica.createEntityManager();
		
		// select * from tb_productos ----> colección --> List
	    String jpql = "SELECT p FROM Producto p"; 
		List<Producto> lstProductos = em.createQuery(jpql, Producto.class).getResultList();

		System.out.println("Listado de Productos");
		System.out.println("---------------------------------");
		for (Producto p : lstProductos) {
			System.out.println("Código....: " + p.getId_prod());
			System.out.println("Producto..: " + p.getDes_prod());
			System.out.println("Stock.....: " + p.getStk_prod());
			System.out.println("Precio....: " + p.getPre_prod());
			System.out.println("Categoria.: " + p.getObjCategoria().getDescripcion());
			System.out.println("Estado....: " + p.getEst_prod());
			System.out.println("Proveedor.: " + p.getObjProveedor().getNombre_rs());
			System.out.println("-------------------------------");
		}
		em.close();
	}
}

 
