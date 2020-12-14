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
import ec.ups.edu.banco.modelo.Cuenta;
import ec.ups.edu.banco.modelo.DetalleSesion;
import ec.ups.edu.banco.modelo.Transferencia;
import ec.ups.edu.banco.modelo.Usuario;



@Stateless
public class CuentaON {
	@Inject
	private CuentaDAO cdao;
	@Inject
	private UsuarioON usuarioOn;
	@Inject
	private TransaccionON transaccionON;
	//@Inject
	//private CreditoDAO credao;

	
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
		final String from = "jhona441cc@gmail.com";
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
		//props.put("mail.smtp.user", "jhonnhuarez@gmail.com"); //remitente
		//props.put("mail.smtp.clave", "JOTA5613jhon"); //La clave de la cuenta

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "JOTA5613jhon");
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
	public void MailCreditoRechazado(Usuario us) {
		System.out.println("---------- " + us.getEmailUsuario());
		System.out.println("---------- " + us.getContraseniaUsuario());
		String subject = "Detalles credito";
		final String from = "jhona441cc@gmail.com";
		String body = "Un saludo cordial de Miutranfer cop.\nEl presente correo es para indicar que su credito ha sido rechazado por no cumplir con los requisitos necesarios\n"
				+ "Para mas informacion por favor acercarse a consultoria"+ "\n\n\n\n"
				+ "Gracias por elegirnos\nEste correo es solo informativo no debe ser respondido";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "JOTA5613jhon");
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
	
	
	/*
	public void MailCreditoAceptado(Usuario us, List<Amortizacion> ListAmortizacion) {
		String head=String.format("%30s %25s %10s %25s %10s", "#", "|", "Fecha($)", "|", "Cuota($)");
        String sep=String.format("%s", "---------------------------------------------------------------------------------------------------------");
        String a="";
        int z=0;
        for (Amortizacion am : ListAmortizacion) {
        	a+=String.format("%30s %25s %10s %25s %10s\n",++z , "|", am.getFechaCuota(), "|", String.valueOf(am.getCuotaAmortizacion()));
			
		}
        System.out.println(head);
        System.out.println(sep);
        System.out.println(a);
		System.out.println("---------- " + us.getEmailUsuario());
		String subject = "Detalles credito";
		String from = "jhona441cc@gmail.com";
		String body = "Un saludo cordial de Miutranfer cop.\nEl presente correo es para indicar que su credito ha sido aceptado a continuacion adjuntamos la tabla de amortizacion\n"
				+ "Cabe recalcar que esta informacion ya se encuentra subida en su pagina personal"+ "\n"+head+"\n"+sep+"\n"+a+"\n\n\n"
				+ "Gracias por elegirnos\nEste correo es solo informativo no debe ser respondido";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "JOTA5613jhon");
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
		final String from = "jhona441cc@gmail.com";
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
				return new PasswordAuthentication(from, "JOTA5613jhon");
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
		final String from = "jhona441cc@gmail.com";
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
				return new PasswordAuthentication(from, "JOTA5613jhon");
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
	public void transferenciaToEmail(Usuario us, Transferencia t) {
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
		final String from = "jhona441cc@gmail.com";
		String body = "";
		String detalle = "Transferencia";
		
		body = "MiauTranfer notifica que la" + detalle + " desde la cuenta: "+t.getCuentaOrigen()
				+ "hacia la cuenta: "+t.getCuentaDestino()+" el dia: " + date
				+ "\n "+""+"\n\n\n\nSi esta transaccion no fue realizada por ud o autorizada comuniquese con nosotros"
				+ "\nGracias por preferirnos";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "JOTA5613jhon");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(us.getEmailUsuario()));
			message.setSubject(subject);
			message.setText(body);
			transaccionON.guardarTransaccion(us.getCuenta(), t.getMonto(), "Transferencia");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	
	}
	public void transferenciaFromEmail(Usuario us,Transferencia t) {
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
		final String from = "jhona441cc@gmail.com";
		String body = "";
		String detalle = "Transferencia";
		
		body = "MiauTranfer notifica que la" + detalle + " desde su cuenta: "+t.getCuentaOrigen()
				+ "hacia la cuenta: "+t.getCuentaDestino()+" el dia: " + date
				+ "\n "+"Se realizo con exito"+"\n\n\n\nSi esta transaccion no fue realizada por ud o autorizada comuniquese con nosotros"
				+ "\nGracias por preferirnos";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "JOTA5613jhon");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(us.getEmailUsuario()));
			message.setSubject(subject);
			message.setText(body);
			transaccionON.guardarTransaccion(us.getCuenta(), t.getMonto(), "Transferencia");
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
	
	
	public boolean transferencia(Transferencia t) {
		Usuario us=usuarioOn.consultarCuentaUsuario(t.getCuentaOrigen()).get(0);
		Usuario us1=usuarioOn.consultarCuentaUsuario(t.getCuentaDestino()).get(0);
		
		if(us==null && us1==null || t.getMonto()>us.getCuenta().getSaldoCuenta()) {
			return false;
		}else {
		us.getCuenta().setSaldoCuenta(us.getCuenta().getSaldoCuenta()-t.getMonto());
		us1.getCuenta().setSaldoCuenta(us1.getCuenta().getSaldoCuenta()+t.getMonto());
		Cuenta cuenta= us.getCuenta();
		actualizarCuenta(cuenta);
		transferenciaFromEmail(us, t);
		Cuenta cuenta1= us1.getCuenta();
		transferenciaToEmail(us1, t);
		actualizarCuenta(cuenta1);
		return true;
		}
	}
	/*
	public void actualizarEstadoCredito(Credito credito){
		credao.actualizarCredito(credito);
	}
	*/
	
}
