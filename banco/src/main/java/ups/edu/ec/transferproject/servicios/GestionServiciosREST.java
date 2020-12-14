package ups.edu.ec.transferproject.servicios;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.ups.edu.transferproject.on.CreditoON;
import ec.ups.edu.transferproject.on.CuentaON;
import ec.ups.edu.transferproject.on.TransaccionON;
import ec.ups.edu.transferproject.on.UsuarioON;
import ups.edu.ec.modelos.Cliente;
import ups.edu.ec.modelos.Credito;
import ups.edu.ec.modelos.Creditoapp;
import ups.edu.ec.modelos.Cuenta;
import ups.edu.ec.modelos.Mensajes;
import ups.edu.ec.modelos.Transferencia;
import ups.edu.ec.modelos.Usuario;

@Path("/servicios")
public class GestionServiciosREST {	
	@Inject
	private CuentaON con;	
	@Inject
	private UsuarioON usuarioOn;
	@Inject
	private TransaccionON transaccionOn;
	@Inject
	private CreditoON creditoOn;
 
	@POST
	@Path("/retiro")
	@Produces("application/json")
	public Mensajes realizarRetiro(@FormParam(value =  "cuenta") final String cuenta,@FormParam(value= "valor") final double valor) {
		Mensajes m=new Mensajes();
		List<Usuario> us =usuarioOn.consultarCuentaUsuario(cuenta);
		if(valor>us.get(0).getCuenta().getSaldoCuenta() || us.isEmpty()) {
			m.setId(99);
			m.setMensaje("No cuenta con el saldo suficiente o la cuenta es incorrecta");
		}else {
			con.retiro(valor, us.get(0));
			m.setId(1);
			m.setMensaje("El retiro se ha realizado con exito");
		}
		return m;
	}

	@POST
	@Path("/deposito")
	@Produces("application/json")
	@Consumes("application/json")
	public Mensajes realizarDeposito(Cuenta c ) {
		Mensajes m=new Mensajes();
		System.out.println(c.getNumCuenta());
		List<Usuario> us=usuarioOn.consultarCuentaUsuario(c.getNumCuenta());
		if(us.isEmpty()) {
			m.setId(400);
			m.setMensaje("Los datos ingresados son incorrectos");
		}else {
		con.deposito(c.getSaldoCuenta(), us.get(0));
		m.setId(200);
		m.setMensaje("El deposito se ha realizado con exito");
		}
		return m;
	}

	@POST
	@Path("/transferencia")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensajes realizarTransferencia(Transferencia t) {
		Mensajes m=new Mensajes();
		if(!con.transferencia(t)) {
			m.setId(400);
			m.setMensaje("Los datos ingresados son incorrectos");
		}else {
		
		m.setId(200);
		m.setMensaje("El transaccion se ha realizado con exito");
		}
		return m;
	}
	
	@GET
	@Path("usuario")
	@Produces("application/json")
	@Consumes("application/json")
	public Cliente login(@QueryParam("email") String email,@QueryParam("pass") String pass) {
		return usuarioOn.validarUsuario(email, pass);
		
	}
	@POST
	@Path("/password")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensajes resetPassword(Cliente c) {
		Mensajes m = new Mensajes();
		if(usuarioOn.resetPassword(c)) {
			m.setId(200);
			m.setMensaje("El cambio de contrasenia fue correcto");
		}else {
			m.setId(400);
			m.setMensaje("Error en la solicutud");
		}
		
		return m;
	}
	@GET
	@Path("credito")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Creditoapp> getCredito(@QueryParam("cuenta") String cuenta) {
		
		return creditoOn.listarCreditosApp(cuenta);
		
	}
	
}
