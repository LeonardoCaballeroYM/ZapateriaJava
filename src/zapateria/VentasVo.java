/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapateria;

/**
 *
 * @author Belen
 */
public class VentasVo {
    
    private int id = 0;
    private int codigo = 0;
    private String nombre = "";
    private int precio = 0;
    private String fecha = "";
    private int cantidad = 0;
    private int total = 0;
    private int importe = 0;
    private int cambio = 0;
    private String Estatus = null;

    public VentasVo(){
        
    }
    
    public VentasVo(String Estatus){
        this.Estatus = Estatus;
    }
    
    public VentasVo(int codigo, String nombre, int precio, String fecha, int cantidad, int total, int importe, int cambio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
        this.importe = importe;
        this.cambio = cambio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getCambio() {
        return cambio;
    }

    public void setCambio(int cambio) {
        this.cambio = cambio;
    }

    /**
     * @return the Estatus
     */
    public String getEstatus() {
        return Estatus;
    }

    /**
     * @param Estatus the Estatus to set
     */
    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}
