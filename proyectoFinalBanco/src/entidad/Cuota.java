package entidad;

import java.util.Date;

public class Cuota {
    private int idPrestamo; 
    private int numeroCuota; 
    private Date fechaVencimiento; 
    private double importeCuota; 
    private boolean EstadoPago;

    public Cuota() {

    }

    public Cuota(int idPrestamo, int numeroCuota, Date fechaVencimiento, double importeCuota ,boolean estadoPago) {
        this.idPrestamo = idPrestamo;
        this.numeroCuota = numeroCuota;
        this.fechaVencimiento = fechaVencimiento;
        this.importeCuota = importeCuota;
        this.EstadoPago = estadoPago;
    }

    // Getters y setters

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getImporteCuota() {
        return importeCuota;
    }

    public void setImporteCuota(double importeCuota) {
        this.importeCuota = importeCuota;
    }

	public boolean isEstadoPago() {
		return EstadoPago;
	}

	public void setEstadoPago(boolean estadoPago) {
		EstadoPago = estadoPago;
	}
    
    
}
