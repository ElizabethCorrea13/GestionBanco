package entidad;

import java.sql.Timestamp;
import java.util.Date;

public class Prestamo {
	
	int idNumeroDePrestamo;
	Date fechaPeticion = new Date();
    Timestamp timestamp = new Timestamp(fechaPeticion.getTime());
    int nroCuenta_Cu; 
	float importeConIntereses;
	float importePedido;
	float montoCuota;
	int cantidadCuotas;
	boolean estado;
	boolean EstadoPagado;
    
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public int getIdNumeroDePrestamo() {
		return idNumeroDePrestamo;
	}
	public void setIdNumeroDePrestamo(int idNumeroDePrestamo) {
		this.idNumeroDePrestamo = idNumeroDePrestamo;
	}
	public Date getFechaPeticion() {
		return fechaPeticion;
	}
	public void setFechaActual(Date fechaActual) {
		this.fechaPeticion = fechaActual;
	}
	public int getNroCuenta_Cu() {
		return nroCuenta_Cu;
	}
	public void setNroCuenta_Cu(int nroCuenta_Cu) {
		this.nroCuenta_Cu = nroCuenta_Cu;
	}
	public float getImporteConIntereses() {
		return importeConIntereses;
	}
	public void setImporteConIntereses(float importeConIntereses) {
		this.importeConIntereses = importeConIntereses;
	}
	public float getImportePedido() {
		return importePedido;
	}
	public void setImportePedido(float importePedido) {
		this.importePedido = importePedido;
	}
	public float getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(float montoCuota) {
		this.montoCuota = montoCuota;
	}
	public int getCantidadCuotas() {
		return cantidadCuotas;
	}
	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}
	public boolean getEstadoPrestamo() {
		return estado;
	}
	public void setEstadoPrestamo(boolean estado) {
		this.estado = estado;
	}
	public boolean isEstadoPagado() {
		return EstadoPagado;
	}
	public void setEstadoPagado(boolean estadoPagado) {
		EstadoPagado = estadoPagado;
	}
	
	

}
