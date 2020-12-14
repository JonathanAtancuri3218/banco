package ec.ups.edu.banco.bean;

import java.io.Serializable;
import java.util.Properties;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.ups.edu.banco.on.CuentaON;
import ec.ups.edu.banco.on.UsuarioON;

@ViewScoped
public class GestioCopBean implements Serializable{
	@Inject
	private CuentaON cuenta;
	@Inject
	private UsuarioON usuario;
	private String to;
	private String us;
	private String mensajeErrorNombre;
	


	public String getMensajeErrorNombre() {
		return mensajeErrorNombre;
	}

	public void setMensajeErrorNombre(String mensajeErrorNombre) {
		this.mensajeErrorNombre = mensajeErrorNombre;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getUs() {
		return us;
	}

	public void setUs(String us) {
		this.us = us;
	}

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
	
	public String generarNumeroCuenta() {
		char[] chars = "0123456789".toCharArray();
		int charsLength = chars.length;
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			buffer.append(chars[random.nextInt(charsLength)]);
		}
		System.out.println("cuenta " + buffer.toString());
		return buffer.toString();
	}
	
	public String enviar() {
		
		if(mensajeErrorNombre=="") {
			//cuenta.sendMail(this.to, us);
		}else{
			mensajeErrorNombre="Debe cambiar el usuario para poder continuar";	
		}
		return null;
	}
	
	public Void validacionusuario() {
		if(!usuario.validarUsuario(this.us)) {
			mensajeErrorNombre="";
		}else {
			System.out.println("usuario invalido");
			mensajeErrorNombre="Usuario no valido";
		}
		return null;

	}
	
}
