package entidad;

import java.util.Date;


public class pagoCuotaPrestamo {
	 int IdPrestamo;
	 int CuotasTotales;
	 int CuotasPagadas;
	 Date FechaPagoCompletoCuotas_Prestamo = new Date();
	public int getIdPrestamo() {
		return IdPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		IdPrestamo = idPrestamo;
	}
	public int getCuotasTotales() {
		return CuotasTotales;
	}
	public void setCuotasTotales(int cuotasTotales) {
		CuotasTotales = cuotasTotales;
	}
	public int getCuotasPagadas() {
		return CuotasPagadas;
	}
	public void setCuotasPagadas(int cuotasPagadas) {
		CuotasPagadas = cuotasPagadas;
	}
	public Date getFechaPagoCompletoCuotas_Prestamo() {
		return FechaPagoCompletoCuotas_Prestamo;
	}
	public void setFechaPagoCompletoCuotas_Prestamo(Date fechaPagoCompletoCuotas_Prestamo) {
		FechaPagoCompletoCuotas_Prestamo = fechaPagoCompletoCuotas_Prestamo;
	}
	 
	 
}
