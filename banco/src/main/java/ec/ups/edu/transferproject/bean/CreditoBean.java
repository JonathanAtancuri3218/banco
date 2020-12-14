package ec.ups.edu.transferproject.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

import ec.ups.edu.transferproject.on.CreditoON;
import ec.ups.edu.transferproject.on.CuentaON;
import ups.edu.ec.modelos.Amortizacion;
import ups.edu.ec.modelos.Credito;
import ups.edu.ec.modelos.Cuenta;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.List;

@ManagedBean
@ViewScoped
public class CreditoBean implements Serializable {

	@Inject
	private CreditoON con;
	@Inject
	private CuentaON cuon;
	private Credito credito;
	private Part archivoCedula;
	private Part archivoPlanilla;
	private Part archivoRolPagos;
	private Cuenta cuenta;
	private Amortizacion amortizacion;
	private List<Credito> listarCreditoId;
	private List<Amortizacion> lista;
	private String fechaSistema;
	private List<Amortizacion> cuotas;
	private double valor;

	@PostConstruct
	public void init() {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.MONTH, 1);
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fecha = dia + "/" + mes + "/" + annio;
		fechaSistema = fecha;
		credito = new Credito();
		cuenta = new Cuenta();
		amortizacion = new Amortizacion();
		ListarCreditoCuenta();
		ListarCuotas();
	}

	public Part getArchivoCedula() {
		return archivoCedula;
	}

	public List<Amortizacion> getLista() {
		return lista;
	}

	public void setLista(List<Amortizacion> lista) {
		this.lista = lista;
	}

	public void setArchivoCedula(Part archivoCedula) {
		this.archivoCedula = archivoCedula;
	}

	public Part getArchivoPlanilla() {
		return archivoPlanilla;
	}

	public void setArchivoPlanilla(Part archivoPlanilla) {
		this.archivoPlanilla = archivoPlanilla;
	}

	public Part getArchivoRolPagos() {
		return archivoRolPagos;
	}

	public void setArchivoRolPagos(Part archivoRolPagos) {
		this.archivoRolPagos = archivoRolPagos;
	}

	public String getFechaSistema() {
		return fechaSistema;
	}

	public List<Amortizacion> getCuotas() {
		return cuotas;
	}

	public void setCuotas(List<Amortizacion> cuotas) {
		this.cuotas = cuotas;
	}

	public void setFechaSistema(String fechaSistema) {
		this.fechaSistema = fechaSistema;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public List<Credito> getListarCreditoId() {
		return listarCreditoId;
	}

	public void setListarCreditoId(List<Credito> listarCreditoId) {
		this.listarCreditoId = listarCreditoId;
	}

	public Amortizacion getAmortizacion() {
		return amortizacion;
	}

	public void setAmortizacion(Amortizacion amortizacion) {
		this.amortizacion = amortizacion;
	}

	public void ListarCreditoCuenta() {
		String c = GestionProyectoBean.UsuarioL.get(0).getCuenta().getNumCuenta();
		listarCreditoId = con.listarCreditoID(c);
		if (listarCreditoId.isEmpty()) {
			info();
		} else {
			if (con.listarAmortizacionID(listarCreditoId.get(0).getIdCredito()).isEmpty()) {
				System.out.println("Credito pendiente");
			} else {
				lista = con.listarAmortizacionID(listarCreditoId.get(0).getIdCredito());
			}

		}

	}

	public void crearCredito() {
		System.out.println(credito);
		String c = GestionProyectoBean.UsuarioL.get(0).getCuenta().getNumCuenta();
		System.out.println(archivoCedula);
		credito.setEstadoCredito("Pendiente");
		credito.setFechaCredito(fechaSistema);
		cuenta.setNumCuenta(c);
		credito.setCuenta(cuenta);
		con.crearCredito(credito);
	}

	public void archivoCedula() {
		System.out.println(archivoCedula);
		byte[] bytes = null;
		try {
			InputStream input = archivoCedula.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[10240];

			for (int length = 0; (length = input.read(buffer)) > 0;)
				output.write(buffer, 0, length);
			credito.setArchivoCedula(output.toByteArray());
			System.out.println(output.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void archivoPlanilla() {
		System.out.println(archivoPlanilla);
		byte[] bytes = null;
		try {
			InputStream input = archivoPlanilla.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[10240];

			for (int length = 0; (length = input.read(buffer)) > 0;)
				output.write(buffer, 0, length);
			credito.setArchivoPlanilla(output.toByteArray());
			System.out.println(output.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void archivoRol() {
		System.out.println(archivoRolPagos);
		byte[] bytes = null;
		try {
			InputStream input = archivoRolPagos.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[10240];

			for (int length = 0; (length = input.read(buffer)) > 0;)
				output.write(buffer, 0, length);
			credito.setArchivoRolPagos(output.toByteArray());
			System.out.println(output.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void info() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "No se encuentra registrado ningun credito"));
	}

	public void ListarCuotas() {
		if (listarCreditoId.isEmpty()) {
			System.out.println("Aun no hay registrado creditos");
		} else {
			cuotas = con.ListarCuotas(listarCreditoId.get(0).getIdCredito());
		}

	}

	public void pagar(int idAmortizacion) {
		String c = GestionProyectoBean.UsuarioL.get(0).getCuenta().getNumCuenta();
		System.out.println(idAmortizacion);
		Amortizacion a = con.buscarCuota(idAmortizacion);
		Cuenta cuen = cuon.BuscarCuenta(c);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa" + a.getCuotaAmortizacion());
		double saldo = GestionProyectoBean.UsuarioL.get(0).getCuenta().getSaldoCuenta();
		System.out.println(saldo);
		if (saldo > a.getCuotaAmortizacion()) {
			DecimalFormat df = new DecimalFormat("#.##");
			double saldoTotal = Double.parseDouble(df.format(saldo - a.getCuotaAmortizacion()).replace(",", "."));
			System.out.println("VALOR SALDO TOTAL"+saldoTotal);
			cuen.setSaldoCuenta(saldoTotal);
			cuon.actualizarCuenta(cuen);
			a.setCapitalPendienteAmortizacion(0);
			a.setEstadoAmortizacion("Cancelado");
			con.actualizarCuota(a);
		} else {
			System.out.println("Ud cuenta con saldo insuficiente");
		}
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void abonar() {
		String c = GestionProyectoBean.UsuarioL.get(0).getCuenta().getNumCuenta();
		double cuota = cuotas.get(0).getCapitalPendienteAmortizacion();
		double saldo = GestionProyectoBean.UsuarioL.get(0).getCuenta().getSaldoCuenta();
		Amortizacion a = con.buscarCuota(cuotas.get(0).getIdAmortizacion());
		Cuenta cuen = cuon.BuscarCuenta(c);
		if (valor > saldo) {
			System.out.println("Fondos insuficientes");
		} else if (valor <= saldo && cuota <= saldo) {
			DecimalFormat df = new DecimalFormat("#.##");
			double s = Double.parseDouble(df.format(saldo - valor).replace(",", "."));
			System.out.println("VALOR SSSSSSSSSSSSS"+s);
			double cp = Double.parseDouble(df.format(cuota - valor).replace(",", "."));
			//System.out.println("VALORRRR CPPPPPPPPPPPPP"+cp);
			cuen.setSaldoCuenta(s);
			cuon.actualizarCuenta(cuen);
			a.setCapitalPendienteAmortizacion(cp);
			if (cp == 0) {
				a.setEstadoAmortizacion("Cancelado");
			} else {
				a.setEstadoAmortizacion("Pendiente");
			}
			con.actualizarCuota(a);
		}
	}
}
