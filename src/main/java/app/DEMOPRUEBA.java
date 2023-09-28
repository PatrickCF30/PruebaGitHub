package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class DEMOPRUEBA {
	//Variables globales

	//Obtener la conexión usando DAO => especificar unidad de persistencia a utilizar
	static EntityManagerFactory fabrica=Persistence.createEntityManagerFactory("mysql");
	//establecer el manejador de las entidades
	static EntityManager em=fabrica.createEntityManager();
	
	//Registrar un nuevo usuario
	public static void main(String[] args) {
		//Objeto donde se guardan los datos
		Usuario u=new Usuario(7, "Jose", "Perez", "jpichula", "123456", "2000/05/10", 1, 1,null);
		//Proceso de registro empezando con una transacción
		em.getTransaction().begin();
		//En lugar de insert into...
		em.persist(u);
		//Confirmar registro
		em.getTransaction().commit();
		System.out.println("Registro Ok");
		em.close();
	}
}
