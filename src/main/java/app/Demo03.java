package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	//Variables globales

	//Obtener la conexión usando DAO => especificar unidad de persistencia a utilizar
	static EntityManagerFactory fabrica=Persistence.createEntityManagerFactory("mysql");
	//establecer el manejador de las entidades
	static EntityManager em=fabrica.createEntityManager();
	
	//Buscar un usuario
	public static void main(String[] args) {
		//Objeto donde se guardan los datos
		Usuario u= em.find(Usuario.class, 7);
        if (u != null) {
    		System.out.println("--------------------------------");
            System.out.println("Código...: " + u.getCod_usua());
            System.out.println("Nombre...: " + u.getNom_usua());
            System.out.println("Apellido.: " + u.getApe_usua());
            System.out.println("Correo...: " + u.getUsr_usua());
            System.out.println("Clave....: " + u.getCla_usua());
            System.out.println("Cumple...: " + u.getFna_usua());
            System.out.println("Tipo.....: " + u.getIdtipo() + "-" + u.getObjTipo().getDescripcion());
            System.out.println("Estado...: " + u.getEst_usua());
            System.out.println("--------------------------------");
        } else {
            System.out.println("Usuario no encontrado.");
        }
	}
}
