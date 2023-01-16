package zapateria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Belen
 */
public class ReporteVo {
    private int totalVentas;
    private int ganancias;
    private int paresVendidos;
    
    public ReporteVo(){
        
    }

    public ReporteVo(int totalVentas, int ganancias, int paresVendidos) {
        this.totalVentas = totalVentas;
        this.ganancias = ganancias;
        this.paresVendidos = paresVendidos;
    }

    public int getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(int totalVentas) {
        this.totalVentas = totalVentas;
    }

    public int getGanancias() {
        return ganancias;
    }

    public void setGanancias(int ganancias) {
        this.ganancias = ganancias;
    }

    public int getParesVendidos() {
        return paresVendidos;
    }

    public void setParesVendidos(int paresVendidos) {
        this.paresVendidos = paresVendidos;
    }
    
}
