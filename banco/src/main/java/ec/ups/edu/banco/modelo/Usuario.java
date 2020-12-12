package ec.ups.edu.banco.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Usuario {
	
	@Id
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String direccion;
	private String usuarioNombre;
	private String contraseña;
	private String tipoUsuario;
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "detalleS")
	private List<DetalleSesion> listaDS;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "numCuenta")
	private Cuenta cuenta;
	
	
	
	
}
