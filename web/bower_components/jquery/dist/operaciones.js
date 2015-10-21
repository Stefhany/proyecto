/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validarFechaNacimiento() {
    var temp = document.getElementById("txtfechanacimiento").value;
    var y = temp.split("-")
    var fechaNacimiento = new Date(y[0], y[1] - 1, y[2]); // se forma la fecha que viene del formulario
    var fechaActual = new Date();   //Fecha actual
    var ftemp = new Date(); // Variable con la fecha actual
    var ftemp2 = new Date();
    var fechaMinima = new Date(ftemp.getTime() - (5 * 24 * 3600 * 1000));   //Sumo 5 dias a la fecha actual para obtener la fecha mínima
    var fechaMaxima = new Date(ftemp2.getTime() + (30 * 24 * 3600 * 1000));  // sumo 30 días a la fecha actual para

    //alert("Actual  : "+fechaActual + "  fecha calendario : "+fechaSolicitud+ "la fecha mínima es : "+fechaMinima);

    if (fechaNacimiento < fechaActual) {
        document.getElementById("result").innerHTML = "Eres menor de edad";
        document.getElementById("txtfechanacimiento").focus();
    } else if (fechaNacimiento >= fechaActual && fechaSolicitud < fechaMinima) {
        document.getElementById("result").innerHTML = "En ese tiempo no se alcanzaa tenr el pedido";
        document.getElementById("txtfechanacimiento").focus();
    } else if (fechaNacimiento >= fechaMaxima) {
        document.getElementById("result").innerHTML = "NO hacemos pedidos con tanta anticipación";
        document.getElementById("txtfechanacimiento").focus();
    } else {
        document.getElementById("result").innerHTML = "ok";
    }
}

