/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import connection.Conectar;
import daos.EvaluacionDAO;
import dtos.EvaluacionDTO;
import java.sql.Connection;
import java.util.LinkedList;

/**
 *
 * @author Mona
 */
public class FacadeEvaluacion {
    Connection cnn = null;
    EvaluacionDAO edao = null;
    EvaluacionDTO edto = null;
    
    public FacadeEvaluacion(){
        edao = new EvaluacionDAO();
        edto = new EvaluacionDTO();
        cnn = utilities.Connection.getInstance();
    }
    
    public LinkedList<EvaluacionDTO> contarRegistros(){
        return edao.contarRegistrosDao(cnn);
    }
}
