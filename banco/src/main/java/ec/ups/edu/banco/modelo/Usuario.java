package ec.ups.edu.banco.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Usuario {
	
	
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
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getUsuarioNombre() {
		return usuarioNombre;
	}
	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public List<DetalleSesion> getListaDS() {
		return listaDS;
	}
	public void setListaDS(List<DetalleSesion> listaDS) {
		this.listaDS = listaDS;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	@Override
	public String toString() {
		return "Usuario [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", telefono=" + telefono + ", direccion=" + direccion + ", usuarioNombre=" + usuarioNombre
				+ ", contraseña=" + contraseña + ", tipoUsuario=" + tipoUsuario + ", listaDS=" + listaDS + ", cuenta="
				+ cuenta + "]";
	}
	
	
	
	
	
	
}
