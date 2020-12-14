package ec.ups.edu.banco.on;


import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import ec.ups.edu.banco.dao.TransaccionDAO;
import ec.ups.edu.banco.dao.UsuarioDAO;


import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.banco.modelo.Cliente;
import ec.ups.edu.banco.modelo.Usuario;

@Stateless
public class UsuarioON {
	@Inject
	private UsuarioDAO udao;
	@Inject
	private TransaccionDAO trandao;
	@Inject
	private CuentaON con;
	/**
	 * Metodo para generar la contraseña aleatoriamente 
	 * @return retorna la contraseña en cadena de texto
	 */
	public String generarPassword() {
		char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		int charsLength = chars.length;
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			buffer.append(chars[random.nextInt(charsLength)]);
		}
		System.out.println("password " + buffer.toString());
		return buffer.toString();
	}
/**
 * Metodo para validar que el usuario no este repetido
 * @param usuario 
 * @return
 */
	public boolean validarUsuario(String usuario) {
		for (Usuario usu : udao.listarUsuario()) {
			System.out.println("------------- "+usu.getUsuarioNombreU());
			if (usu.getUsuarioNombreU().equals(usuario)) {
				System.out.println(usu.getUsuarioNombreU()+"     "+usuario);
				return true;
			}else {
				System.out.println("No hay");
			}
		}
		return false;
	}
	/**
	 * Metodo para determinar quien inicio sesion y redirigir a la pagina que se le asigna a cada uno
	 * @param usuario para obtener el tipo de usuario
	 * @return devuelve la pagina a la que se va a direccionar
	 */
	public String determinarInicioSesion(Usuario usuario) {
		String pagina="";
		if (usuario.getTipoUsuario().equals("Administrador")) {
			pagina="InicioAdmin";
		}else if(usuario.getTipoUsuario().equals("Cajero")) {
			pagina="InicioCajero";
		}else if (usuario.getTipoUsuario().equals("Jefe de credito")) {
			pagina="CreditoJefeCredito";
		}else {
			pagina="DetalleCuenta";
		}
		
		return pagina;
		
	}
	/**
	 * Devuelve una lista de usuarios
	 * @return
	 */
	public List<Usuario> listarUsuarios(){
		return udao.listarUsuario(); 
	}
	/**
	 * Devuelve una lista de los usuarios cuyo tipo de ral sea no nulo
	 * esto se hace para comprobar si es un cliente normal o una administrador, ya sea cajero, administrador o jefe de credito	
	 * @return
	 */
	public List<Usuario> listarUsuariosPTipo(){
		return udao.ConsultarUsuarioTipo(); 
	}
	/**Metodo para consultar el tipo de usuario en este caso unicamente los clientes no administradores
	 * Devuelve una listar de usuarios clientes
	 * @return
	 */
	public List<Usuario> listarClientes(){
		return udao.ConsultarUsuarioTipoCliente(); 
	}
	/**
	 * Metodo para crear cliente 
	 * @param usuario recibe el objeto usuario
	 */
	public void crearCliente(Usuario usuario) {
		
		udao.crearUsuario(usuario);
	}
	/**
	 * Metodo para consultar la cuenta del usuario especificado
	 * @param pasamos como parametro usuario que es el email de inicio de sesion
	 * @return devuelve una lista con los datos del usuario consultado
	 */
	public List<Usuario> buscarNumCuentaUsuario(String usuario) {
		return udao.ConsultarNumCuentaUsuario(usuario);
	}
	/**
	 * Actualizar usuario
	 * @param u revise el objeto usuario
	 */
	public void actualizar(Usuario u) {
		udao.actualizarUsuario(u);
	}
	/**
	 * Metodo para consultar el los datos del usuario con el numero de cuenta especificado
	 * @param cuenta numero de cuenta
	 * @return devuelve una lista de los datos del usuario especificado por el numero de cuenta
	 */
	public List<Usuario> consultarCuentaUsuario(String cuenta){
		return udao.ConsultarCuenta(cuenta);
		
	}
	/**
	 * Metodo para validar cedula
	 * @param cedula recibe como parametro la cedula del usuario
	 * @return devuelve true o false dependiendo de si la cedula esta correcta o no
	 */
	public boolean validarCedula(String cedula) {
		if(cedula.length()==10) {
			int verificador = Integer.parseInt(cedula.substring(cedula.length() - 1, cedula.length()));
	        int aux = 0;
	        int suma = 0;
	        int[] multiplicador = {2, 1, 2, 1, 2, 1, 2, 1, 2};
	        for (int i = 0; i < cedula.length() - 1; i++) {
	            int valor = Integer.parseInt(String.valueOf(cedula.charAt(i))) * multiplicador[i];
	            if (valor > 9) {
	                valor -= 9;
	            }
	            suma += valor;   
	        }
	        if (suma % 10 == 0) {
	            return true;
	        } else if ((10 - (suma % 10)) == verificador) {
	            return true;

	        } else {
	            return false;
	        }
	        }else {
	        	return false;
	        }
	

    }
	/**
	 * 
	 * @param usuario para validar la existencia de la cedula
	 * @throws Exception
	 */
	
	public void validarUsuario(Usuario usuario) throws Exception {
		if(!validarCedula(usuario.getCedulaUsuario())) {
	    	   throw new Exception("cedula incorrecta");
	       }
	        if(udao.ConsultarCuenta(usuario.getCedulaUsuario())!=null) {
	        
	        	throw new Exception("existe");
	        }
	       
	}
 
	public Cliente validarUsuario(String email,String pass) {
		List<Usuario> lu=listarUsuarios();
		Cliente u = new Cliente();
		
		for (Usuario usuario : lu) {
			if(usuario.getEmailUsuario().equals(email) && usuario.getContraseniaUsuario().equals(pass) && usuario.getTipoUsuario().equals("Cliente")) {
				u.setCodigo(200);
				u.setCedula(usuario.getCedulaUsuario());
				u.setNombre(usuario.getNombreUsuario());
				u.setApellido(usuario.getApellidoUsuario());
				u.setNumCuenta(usuario.getCuenta().getNumCuenta());
				u.setSaldoCuenta(usuario.getCuenta().getSaldoCuenta());
				u.setTipo(usuario.getTipoUsuario());
				u.setPassword(usuario.getContraseniaUsuario());
				u.setCorreo(usuario.getEmailUsuario());
				actualizar(con.emailConexion(usuario, 0));
				return u;
			}if(usuario.getEmailUsuario().equals(email) && !usuario.getContraseniaUsuario().equals(pass) && usuario.getTipoUsuario().equals("Cliente")) {
				u.setCodigo(400);
				actualizar(con.emailConexion(usuario, 1));
				return u;
			}
		}
		u.setCodigo(400);
		return u;
	
	}
	public boolean resetPassword(Cliente c) {
		List<Usuario> lu=listarClientes();
		
		for (Usuario usuario : lu) {
			System.out.println(usuario);
			if(usuario.getEmailUsuario().equals(c.getCorreo()) && usuario.getCedulaUsuario().equals(c.getCedula())){
				usuario.setContraseniaUsuario(generarPassword());
				udao.actualizarUsuario(usuario);
				con.resetPasswordEmail(usuario);
				return true;
			}	
		}
		return false;
		
	}

}
