package ec.ups.edu.banco.on;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.metamodel.ListAttribute;


import ec.ups.edu.banco.dao.CuentaDAO;
import ec.ups.edu.banco.dao.UsuarioDAO;
import ec.ups.edu.banco.modelo.Amortizacion;
import ec.ups.edu.banco.modelo.Cuenta;
import ec.ups.edu.banco.modelo.DetalleSesion;
import ec.ups.edu.banco.modelo.Usuario;

@Stateless
public class CuentaON {
	@Inject
	private CuentaDAO cdao;
	@Inject
	private UsuarioON usuarioOn;
	@Inject
	private TransaccionON transaccionON;


	
	public Cuenta BuscarCuenta(String cuenta) {
		return cdao.consultarCuenta(cuenta);
	}
	/**
	 * Metodo para generar el numero de Cuenta aleatoriamente
	 * 
	 * @return retorna el numero de cuenta en cadena de texto
	 */
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

	/**
	 * Metodo para el envio de mensaje por email
	 * 
	 * @param to recibe como parameto a quien se le va a enviar
	 * @param us recibe como parametro el usuario de la cuenta
	 */
	public void sendMail(Usuario us) {
		System.out.println("---------- " + us.getEmailUsuario());
		System.out.println("---------- " + us.getContraseniaUsuario());
		String subject = "Creacion de Cuenta";
		String from = "jviscainoq@gmail.com";
		String body = "Un saludo cordial de Miutranfer cop.\nEl presente correo es para indicar que su cuenta fue creada correctamente\n"
				+ "Su numero de cuenta es:" + us.getCuenta().getNumCuenta()
				+ "\nPuede ingregar con su correo o usuario :" + us.getEmailUsuario() + " ----- " + us + " \n"
				+ "Su contraseña es : " + us.getContraseniaUsuario() + "\n\n\n\n"
				+ "Gracias por elegirnos\nEste correo es solo informativo no debe ser respondido";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "ENDneel.123");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(us.getEmailUsuario()));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 
	 * @param us
	 * @param estado
	 * @return
	 */
		/**
	 * 
	 * @param us
	 * @param estado
	 * @return
	 */
	public Usuario emailConexion(Usuario us, int estado) {
		System.out.println("----------sendemail " + us.getEmailUsuario());
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String pattern = "dd/MM/yy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		String subject = "Inicio de Sesion";
		String from = "jviscainoq@gmail.com";
		String body = "";
		String detalle = "";
		if (estado == 0) {
			detalle = "Exitosa";
		} else {
			detalle = "Erronea";
		}
		body = "MiauTranfer notifica que el ingreso a su cuenta fue " + detalle + " en el dispositivo: "
				+ address.getHostAddress() + " el dia: " + date
				+ "\n\n\n\nSi esta transaccion no fue realizada por ud o autorizada comuniquese con nosotros"
				+ "\nGracias por preferirnos";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "ENDneel.123");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(us.getEmailUsuario()));
			message.setSubject(subject);
			message.setText(body);
			DetalleSesion d = new DetalleSesion();
			List<DetalleSesion> lu=us.getListaDS();
			d.setDetalleSesion(detalle);
			d.setFechaSesion(date);
			d.setDireccionIPSesion(address.getHostAddress());
			d.setDetalleS(us);
			lu.add(d);
			us.setListaDS(lu);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return us;
		
	}
	
	public void resetPasswordEmail(Usuario us) {
		System.out.println("----------sendemail " + us.getEmailUsuario());
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String pattern = "dd/MM/yy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		String subject = "Inicio de Sesion";
		String from = "jviscainoq@gmail.com";
		String body = "";
		String detalle = "exitoso";
		
		body = "MiauTranfer notifica que el cambio de contraseña fue " + detalle + " en el dispositivo: "
				+ address.getHostAddress() + " el dia: " + date
				+ "\nsu nueva contraseña es: "+us.getContraseniaUsuario()+"\n\n\n\nSi esta transaccion no fue realizada por ud o autorizada comuniquese con nosotros"
				+ "\nGracias por preferirnos";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "ENDneel.123");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(us.getEmailUsuario()));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Metodo para crear cuenta, recibe como parametro el objeto cuenta
	 * @param cuenta
	 */
	public void crearCuenta(Cuenta cuenta) {
		cdao.crearCuenta(cuenta);
	}
	
	/**
	 * Metodo para actualizar cuenta, recibe como parametro el objeto cuenta
	 * @param cuenta
	 */
	public void actualizarCuenta(Cuenta cuenta) {
		cdao.actualizarCuenta(cuenta);
	}
	
	public void retiro(double monto, Usuario usu) {

		System.out.println(usu.getCuenta().getSaldoCuenta()-monto);
		usu.getCuenta().setSaldoCuenta(usu.getCuenta().getSaldoCuenta()-monto);
		Cuenta cuenta=usu.getCuenta();
		actualizarCuenta(cuenta);
		transaccionON.guardarTransaccion(cuenta, monto, "Retiro");
		}
	
	public void deposito(double monto,Usuario usu) {
		
		System.out.println(usu.getCuenta().getSaldoCuenta()+monto);
		usu.getCuenta().setSaldoCuenta(usu.getCuenta().getSaldoCuenta()+monto);
		Cuenta cuenta=usu.getCuenta();
		actualizarCuenta(cuenta);
		transaccionON.guardarTransaccion(cuenta, monto, "Deposito");
		
	}
	
	
	
	
}
