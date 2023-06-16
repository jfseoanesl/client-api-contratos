/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

/**
 *
 * @author Jairo F
 */
public enum GeneroPersonaDto {
    MASCULINO, FEMENINO, OTRO;

    public static GeneroPersonaDto get(String descricipcion) {
        GeneroPersonaDto gp;
        switch (descricipcion) {

            case "Masculino":
            case "MASCULINO":
                gp = MASCULINO;
                break;

            case "Femenino":
            case "FEMENINO":
                gp = FEMENINO;
                break;

            default:
                gp = OTRO;

        }
        return gp;

    }
    public static int getIndex(String descricipcion) {
        int index;
        index = switch (descricipcion) {
            case "Masculino", "MASCULINO" -> 0;
            case "Femenino", "FEMENINO" -> 1;
            case "Otro", "OTRO" -> 2;
            default -> -1;
        };
        return index;

    }
}
