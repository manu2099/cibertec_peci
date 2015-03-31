package pe.edu.cibertec.proyemp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departamento {

	@Id
	@GeneratedValue
	// generatedvalue, utilice el autoincrement....
	private Long id;

	private String nombre;

	// Uno a Muchos
	// Cascada Persist= Cuando guarde, guarde tambien los empleados COMO CASCADA
	@OneToMany(mappedBy = "departamento", cascade = CascadeType.PERSIST)
	private List<Empleado> empleados = new ArrayList<Empleado>();

	public Departamento() {
	}

	public Departamento(String nombre) {
		super();
		this.nombre = nombre;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
