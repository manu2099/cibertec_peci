package pe.edu.cibertec.proyemp.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pe.edu.cibertec.proyemp.domain.Departamento;
import pe.edu.cibertec.proyemp.domain.Empleado;

public class JpaTest {
	
	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	//Tarea cuenta en GitHub... Tarea que esta aplicacion. que funcione con EclipseLink
	//practicamente modificar el persistence.xml
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");
		//
		
		EntityManager manager = factory.createEntityManager();
		
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		try {
			//test.crearEmpleados();
			test.crearEmpleados2();
			test.modificarNombreDepartamento();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.listarEmpleados();
		

		System.out.println(".. done");
	}

	/*
	 * Primer MODO
	 * private void crearEmpleados2() {
		Departamento departamento = new Departamento("JAVA");
		
		List<Empleado> empleados = new ArrayList<Empleado>();
		Empleado bob= new Empleado("Bob");
		Empleado mike= new Empleado("Mike");
		
		empleados.add(bob);
		empleados.add(mike);
		
		departamento.setEmpleados(empleados);
		manager.persist(departamento); //Aqui recien creamos el depa.
		
	}*/
	
	
	/*
	 * Segundo MODO
	 * */
	private void crearEmpleados2() {
	Departamento departamento = new Departamento("PHP");
	
	//**List<Empleado> empleados = new ArrayList<Empleado>();
	Empleado bob= new Empleado("Tor");
	Empleado mike= new Empleado("Miky");
	
	//**empleados.add(bob);
	//**empleados.add(mike);
	
	departamento.setEmpleados(Arrays.asList(bob,mike));
	manager.persist(departamento); //Aqui recien creamos el depa.
	
	bob.setDepartamento(departamento);
	mike.setDepartamento(departamento);
}
	
	
	

	private void modificarNombreDepartamento() {

		//Departamento dep = manager.createQuery("from Departamento where id=1", Departamento.class).getSingleResult();
		// Segundo metodo tambien de JPA (clase, id)
		Departamento dep= manager.find(Departamento.class, new Long (1));
		dep.setNombre("PHITON");
		manager.persist(dep);
		//autorizar o para crear....
	}

	//metodo para actualizar empleados


//metodo para crear los empleados
	private void crearEmpleados() {
		int nroDeEmpleados = manager.createQuery("Select a From Empleado a", Empleado.class).getResultList().size();
		//
		
		if (nroDeEmpleados == 0) {
			System.out.println("Creando empleados");
			
			Departamento departamento = new Departamento("Java");
			manager.persist(departamento);

			manager.persist(new Empleado("Bob",departamento));
			manager.persist(new Empleado("Mike",departamento));

		}
	}


	private void listarEmpleados() {
		List<Empleado> resultList = manager.createQuery("Select a From Empleado a", Empleado.class).getResultList();
		System.out.println("nro de empleados:" + resultList.size());
		for (Empleado next : resultList) {
			System.out.println("siguiente empleado: " + next);
		}
	}
}
