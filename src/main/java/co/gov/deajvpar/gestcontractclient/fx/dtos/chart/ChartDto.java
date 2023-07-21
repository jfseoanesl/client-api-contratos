/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos.chart;

/**
 *
 * @author Jairo F
 */
public class ChartDto {
    private String descripcion;
    private int valor;

    public ChartDto() {
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion+" ("+this.valor+")";
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
    
}
