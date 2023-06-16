/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

/**
 *
 * @author Jairo F
 */
public interface IFormController {
    boolean validarEnvioFormulario();
    void activarDesactivarOpciones(boolean opt);
    void activarPrivilegiosModulo();
    void limpiarFormulario();
    void activarDesactivarPaneles(boolean opt);
}
