package entidad;

import java.time.LocalDate;

public class Cuenta {
    private int numeroCuenta;
    private Cliente cliente;
    private LocalDate fechaCreacion;
    private TipoDeCuenta tipoDeCuenta;
    private String CBU;
    private double saldo;
    private boolean Estado;

    // Constructor
    public Cuenta() {}
    
    public Cuenta(Cliente cliente, LocalDate fechaCreacion, TipoDeCuenta tipoDeCuenta, double saldo, String cbu, boolean estado) {
        this.cliente = cliente;
        this.fechaCreacion = fechaCreacion;
        this.tipoDeCuenta = tipoDeCuenta;
        this.saldo = saldo;
        this.CBU = cbu;
        this.Estado = estado;
    }

    // Getters y Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public TipoDeCuenta getTipoDeCuenta() {
        return tipoDeCuenta;
    }

    public void setTipoDeCuenta(TipoDeCuenta tipoDeCuenta) {
        this.tipoDeCuenta = tipoDeCuenta;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getCBU() {
        return CBU;
    }

    public void setCBU(String CBU) {
        this.CBU = CBU;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public boolean isEstado() {
        return Estado;
    }
    
    public void setEstado(boolean estado) {
        this.Estado = estado;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta=" + numeroCuenta +
                ", cliente=" + cliente +
                ", fechaCreacion=" + fechaCreacion +
                ", tipoDeCuenta=" + tipoDeCuenta +
                ", CBU='" + CBU + '\'' +
                ", saldo=" + saldo +
                '}';
    }

}
