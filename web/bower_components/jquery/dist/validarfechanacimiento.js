/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function calcularEdad(fechaNacimiento){
    //se calcula la fecha de hoy
    today = new Date();
    
    /**
     * Se debe calcular la fecha que recibo en  un array
     * 
     * Se utiliza el split para separar los caracteres que se recibieron de acuerdo al string
     * con el signo (/).       
     * 
     * Si el array no tiene tres partes la fecha ingresada es incorrecta. 
     * 
     */
    var listaFecha = fechaNacimiento.split("/");
    
    if (listaFecha.length !== 3) {
        return false;
    }
   
    //Compruebo que el año, el mes y el día ingresado son correctos.
    var year = parseInt(listaFecha[2]);
    
    if (isNaN(year)) {
        return false;
    }
    
    var month = parseInt(listaFecha[1]);
    
    if (isNaN(month)) {
        return false;
    }
    
    var day = parseInt(listaFecha[0]);
    
    if (isNaN(day)) {
        return false;
    }
    
    /**Se coloca el menos uno (-1), para saber si la persona ya ha cumplío 
     * años este año.
     */
    edad = today.getYear() - year - 1;
    
    /**
     * Si resto los meses y me da menor que cero (0), entonces no han cumplido años.
     * 
     * Si es mayor a uno o igual es porque ya lo cumplieron. 
     */
    
    if (today.getMonth() + 1 - month < 0) { // Se coloca más uno (+1), porque los meses empiezan en cero (0). 
        return edad;
    }
    
    if (today.getMonth() + 1 - month > 0){
        return edad + 1;
    }
    
    if(today.getUTCDay() - day >= 0){
        return edad + 1;
    }
    
    return edad;
}