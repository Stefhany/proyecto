/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validar() {
    var temp = document.getElementById("txtFechaSolicitud").value;
    var y = temp.split("-")
    var fechaSolicitud = new Date(y[0], y[1] - 1, y[2]); // se forma la fecha que viene del formulario
    var fechaActual = new Date();   //Fecha actual
    var ftemp = new Date(); // Variable con la fecha actual
    var ftemp2 = new Date();
    var fechaMinima = new Date(ftemp.getTime() + (5 * 24 * 3600 * 1000));   //Sumo 5 dias a la fecha actual para obtener la fecha mínima
    var fechaMaxima = new Date(ftemp2.getTime() + (30 * 24 * 3600 * 1000));  // sumo 30 días a la fecha actual para

    //alert("Actual  : "+fechaActual + "  fecha calendario : "+fechaSolicitud+ "la fecha mínima es : "+fechaMinima);

    if (fechaSolicitud < fechaActual) {
        document.getElementById("result").innerHTML = "Esta seleccionando una fecha anterior a la actual";
        document.getElementById("txtFechaSolicitud").focus();
    } else if (fechaSolicitud >= fechaActual && fechaSolicitud < fechaMinima) {
        document.getElementById("result").innerHTML = "En ese tiempo no se alcanza a tener el pedido";
        document.getElementById("txtFechaSolicitud").focus();
    } else if (fechaSolicitud >= fechaMaxima) {
        document.getElementById("result").innerHTML = "NO hacemos pedidos con tanta anticipación";
        document.getElementById("txtFechaSolicitud").focus();
    } else {
        document.getElementById("result").innerHTML = "ok";
    }

}

function validarSolicitud() {
    var temp = document.getElementById("txtFechaSolicitud").value;
    var y = temp.split("-")
    var fechaSolicitud = new Date(y[0], y[1] - 1, y[2]); // se forma la fecha que viene del formulario
    var fechaActual = new Date();   //Fecha actual
    var ftemp = new Date(); // Variable con la fecha actual
    var ftemp2 = new Date();
    var fechaMinima = new Date(ftemp.getTime() + (3 * 24 * 3600 * 1000));   //Sumo 5 dias a la fecha actual para obtener la fecha mínima
    var fechaMaxima = new Date(ftemp2.getTime() + (30 * 24 * 3600 * 1000));  // sumo 30 días a la fecha actual para

    //alert("Actual  : "+fechaActual + "  fecha calendario : "+fechaSolicitud+ "la fecha mínima es : "+fechaMinima);

    if (fechaSolicitud < fechaActual) {
        document.getElementById("result").innerHTML = "Esta seleccionando una fecha anterior a la actual";
        document.getElementById("txtFechaSolicitud").focus();
        document.getElementById('btnSolicitar').removeAttribute('disabled', 'true');
    } else if (fechaSolicitud >= fechaActual && fechaSolicitud < fechaMinima) {
        document.getElementById("result").innerHTML = "En ese tiempo no se alcanza a realizar el pedido";
        document.getElementById("txtFechaSolicitud").focus();
        document.getElementById('btnSolicitar').removeAttribute('disabled');  // se habilita el boton registrar
    } else if (fechaSolicitud >= fechaMaxima) {
        document.getElementById("result").innerHTML = "NO se hacen pedidos con tanta anticipación";
        document.getElementById("txtFechaSolicitud").focus();
        document.getElementById('btnSolicitar').removeAttribute('disabled');
    } else {
        
        document.getElementById('btnSolicitar').removeAttribute('disabled','true');
    }
}

function validarFechaNacimiento() {
    var temp = document.getElementById("txtfechanacimiento").value;
    var y = temp.split("-")
    var fechaNacimiento = new Date(y[0], y[1] - 1, y[2]); // se forma la fecha que viene del formulario
    var fechaActual = new Date();   //Fecha actual
    var ftemp = new Date(); // Variable con la fecha actual
    var ftemp2 = new Date();
    var fechaMinima = new Date(ftemp.getTime() + (5 * 24 * 3600 * 1000));   //Sumo 5 dias a la fecha actual para obtener la fecha mínima
    var fechaMaxima = new Date(ftemp2.getTime() + (30 * 24 * 3600 * 1000));  // sumo 30 días a la fecha actual para

    //alert("Actual  : "+fechaActual + "  fecha calendario : "+fechaSolicitud+ "la fecha mínima es : "+fechaMinima);

    if (fechaSolicitud < fechaActual) {
        document.getElementById("result").innerHTML = "Esta seleccionando una fecha anterior a la actual";
        document.getElementById("txtfechanacimiento").focus();
    } else if (fechaSolicitud >= fechaActual && fechaSolicitud < fechaMinima) {
        document.getElementById("result").innerHTML = "En ese tiempo no se alcanzaa tenr el pedido";
        document.getElementById("txtfechanacimiento").focus();
    } else if (fechaSolicitud >= fechaMaxima) {
        document.getElementById("result").innerHTML = "NO hacemos pedidos con tanta anticipación";
        document.getElementById("txtfechanacimiento").focus();
    } else {
        document.getElementById("result").innerHTML = "ok";
    }

}

function validarOferta() {
    var temp = document.getElementById("txtFechaSolicitud").value;
    var y = temp.split("-")
    var fechaSolicitud = new Date(y[0], y[1] - 1, y[2]); // se forma la fecha que viene del formulario
    var fechaActual = new Date();   //Fecha actual
    var ftemp = new Date(); // Variable con la fecha actual
    var ftemp2 = new Date();
    var fechaMinima = new Date(ftemp.getTime() + (3 * 24 * 3600 * 1000));   //Sumo 5 dias a la fecha actual para obtener la fecha mínima
    var fechaMaxima = new Date(ftemp2.getTime() + (15 * 24 * 3600 * 1000));  // sumo 30 días a la fecha actual para

    //alert("Actual  : "+fechaActual + "  fecha calendario : "+fechaSolicitud+ "la fecha mínima es : "+fechaMinima);

    if (fechaSolicitud < fechaActual) {
        document.getElementById("result").innerHTML = alert("Esta seleccionando una fecha anterior a la actual");
        document.getElementById("txtFechaSolicitud").focus();
    } else if (fechaSolicitud >= fechaActual && fechaSolicitud < fechaMinima) {
        document.getElementById("result").innerHTML = alert("En ese tiempo no se alcanza a realizar el pedido");
        document.getElementById("txtFechaSolicitud").focus();
    } else if (fechaSolicitud >= fechaMaxima) {
        document.getElementById("result").innerHTML = alert("NO se hacen pedidos con tanta anticipación");
        document.getElementById("txtFechaSolicitud").focus();
    } else {
        document.getElementById("result").innerHTML = "ok";
    }
}