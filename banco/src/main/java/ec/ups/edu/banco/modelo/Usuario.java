package ec.ups.edu.banco.modelo;

import java.util.List;
@Entity
public class Usuario {
	
	
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String direccion;
	private String usuarioNombre;
	private String contraseņa;
	private String tipoUsuario;
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "detalleS")
	private List<DetalleSesion> listaDS;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "numCuenta")
	private Cuenta cuenta;
	
	
	
	
}
