package ec.ups.edu.transferproject.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.ups.edu.transferproject.on.CuentaON;
import ec.ups.edu.transferproject.on.TransaccionON;
import ec.ups.edu.transferproject.on.UsuarioON;
import ups.edu.ec.modelos.Cuenta;
import ups.edu.ec.modelos.Transaccion;
import ups.edu.ec.modelos.Usuario;

@ManagedBean
@ViewScoped
public class GestionProyectoBean implements Serializable{

	private Usuario usuario;
	private Cuenta cuenta;
	private Transaccion transaccion;
	private List<Usuario> listaUsuarios;
	private List<Usuario> listaUsuariosTipo;
	private List<Usuario> listarClientes;
	public static List<Usuario> UsuarioL=new ArrayList<>(); 
	@Inject
	private UsuarioON usuarioON;
	
	@Inject
	private CuentaON cuentaON;
	
	////////validacion////
	private String mensajeErrorNombre="";
	private String mensajeErrorCedula="";

	
	
	@PostConstruct
	public void init() {
		ListarUsuarios();
		ListarUsuariosTipo();
		ListarClientes();
		usuario=new Usuario();
		cuenta=new Cuenta();
		transaccion=new Transaccion();
	}
	
	

	public String getMensajeErrorNombre() {
		return mensajeErrorNombre;
	}



	public void setMensajeErrorNombre(String mensajeErrorNombre) {
		this.mensajeErrorNombre = mensajeErrorNombre;
	}



	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	public List<Usuario> getListaUsuariosTipo() {
		return listaUsuariosTipo;
	}

	public void setListaUsuariosTipo(List<Usuario> listaUsuariosTipo) {
		this.listaUsuariosTipo = listaUsuariosTipo;
	}
	
	public List<Usuario> getListarClientes() {
		return listarClientes;
	}

	public void setListarClientes(List<Usuario> listarClientes) {
		this.listarClientes = listarClientes;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public String ListarUsuarios() {
		listaUsuarios=usuarioON.listarUsuarios();
		return "lista de usuarios";
	}

	public String ListarUsuariosTipo() {
		listaUsuariosTipo=usuarioON.listarUsuariosPTipo();
		return "lista de usuarios por tipo";
	}
	public String ListarClientes() {
		listarClientes=usuarioON.listarClientes();
		return "lista de clientes";
	}
	
	public void emailConexion() {
		
	}

	public String getMensajeErrorCedula() {
		return mensajeErrorCedula;
	}



	public void setMensajeErrorCedula(String mensajeErrorCedula) {
		this.mensajeErrorCedula = mensajeErrorCedula;
	}



	public String iniciarSesion() {
		System.out.println("----------------usuario "+usuario.getEmailUsuario());
		for(Usuario us:listaUsuarios) {
			
			System.out.println("------ us"+ us.getEmailUsuario());
			if(usuario.getContraseniaUsuario().equals(us.getContraseniaUsuario()) && usuario.getEmailUsuario().equals(us.getEmailUsuario()) ) {
				 System.out.println(usuarioON.determinarInicioSesion(us));
				 UsuarioL=usuarioON.buscarNumCuentaUsuario(usuario.getEmailUsuario());
				 Usuario uem=cuentaON.emailConexion(us,0);
				 usuarioON.actualizar(uem);
				 return usuarioON.determinarInicioSesion(us);

			
			}if(!usuario.getContraseniaUsuario().equals(us.getContraseniaUsuario()) && usuario.getEmailUsuario().equals(us.getEmailUsuario())){
				System.out.println(usuarioON.determinarInicioSesion(us));
				 Usuario uem=cuentaON.emailConexion(us,1);
				 usuarioON.actualizar(uem);
				 return "Login";
			}
		}
		return null;
	}
	public String crearCuenta() throws Exception {
		
		
		System.out.println(usuario.getEmailUsuario() + " -------------- "+ usuario.getUsuarioNombreU());
		System.out.println(mensajeErrorCedula + " ---------- "+mensajeErrorNombre);
		if(mensajeErrorNombre=="" && mensajeErrorCedula=="") {
			System.out.println(usuario.getEmailUsuario() + " "+ usuario.getUsuarioNombreU()+" Se envio la cuenta a su correo");
			Cuenta cuenta= new Cuenta();
			cuenta.setNumCuenta(cuentaON.generarNumeroCuenta());
			cuenta.setSaldoCuenta(0.00);
			cuenta.setTipo("Cuenta Ahorro");
			cuentaON.crearCuenta(cuenta);
			usuario.setCuenta(cuenta);
			usuario.setTipoUsuario("Cliente");
			usuario.setContraseniaUsuario(usuarioON.generarPassword());
			usuarioON.crearCliente(usuario);
			cuentaON.sendMail(usuario);
			
			return "Login";
			
		}else{
			System.out.println("aquiiii");
		}
		return null;
			}
	public String crearCuentaAdministrador() throws Exception {
		System.out.println(usuario.getEmailUsuario() + " -------------- "+ usuario.getUsuarioNombreU());
		if(mensajeErrorNombre=="") {
			System.out.println(usuario.getEmailUsuario() + " "+ usuario.getUsuarioNombreU()+" Se envio la cuenta a su correo");
			usuario.setContraseniaUsuario(usuarioON.generarPassword());
			usuarioON.crearCliente(usuario);
		}else{
			System.out.println("chaleeee");
		}
		return "Se ha creado la cuenta de un nuevo administrador";
			}
	
	public void validacionUsuario() {
			
		if(!usuarioON.validarUsuario(usuario.getUsuarioNombreU())) {
			mensajeErrorNombre="";
		}else {
			mensajeErrorNombre="Usuario no valido";
		}
	}
	public void validacionCedulaUsuario() {
		
	if(usuarioON.validarCedula(usuario.getCedulaUsuario())) {
		mensajeErrorNombre=" ";
	}else {
		mensajeErrorNombre="cedula no valida";
	}
    }
	public boolean existe() {
		for (Usuario user : listarClientes) {
			if(user.getCedulaUsuario().equals(usuario.getCedulaUsuario())) {
				return true;
			}
		}
		return false;
	}

	
	public void validarCedulaU() throws Exception {
		if(usuarioON.validarCedula(usuario.getCedulaUsuario())) {
			
			if(existe()) {
				mensajeErrorCedula="ya existe la cedula ingresada";
			}else {
			mensajeErrorCedula="";
			}
			
	       }
	        else {
	    		mensajeErrorCedula="cedula no valida";
	        }
	       
	}
	
	  
	
}
