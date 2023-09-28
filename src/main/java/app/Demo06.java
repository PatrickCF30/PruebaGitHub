package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

// GUI!!!
public class Demo06 {
	// Listado de los Usuarios
	// 1. obtener la conexion --> llamar a la unidad de persistencia
	static EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	// 2. crear un manejador de entidades segun la conexion
	static EntityManager em = fabrica.createEntityManager();

	public static void main(String[] args) {
		// select * from tb_usuarios ----> colección --> List

		List<Usuario> lstUsuarios = 
				em.createQuery("select u from Usuario u", Usuario.class).getResultList();

		System.out.println("Listado de usuarios");
		System.out.println("---------------------------------");
		for (Usuario u : lstUsuarios) {
			System.out.println("Código...: " + u.getCod_usua());
			System.out.println("Nombre...: " + u.getNom_usua());
			System.out.println("Apellido.: " + u.getApe_usua());
			System.out.println("Tipo.....: " + u.getIdtipo() + " - " + u.getObjTipo().getDescripcion());
			System.out.println("-------------------------------");
		}
	}
}
