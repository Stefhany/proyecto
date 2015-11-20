/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var req;
function validarCorreoReestablecerClave(campo) {
    if(campo.value==='') return false;
    var url = "../vrc?txtCorreo=" + escape(campo.value);
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    req.open("Get", url, true);
    req.onreadystatechange = callback;
    req.send(null);
}


function callback( ) {
    if (req.readyState === 4) {  
        if (req.status === 200) {
            //para efectos visuales se utilizan dos div con colores rojo y verde
            if (req.responseText.toString() === "El correo se encuentra registrado!") {
                document.getElementById('empResult').innerHTML = "";  //se limpia el div rojo
                document.getElementById('empResult2').innerHTML = req.responseText;  // se escribe en verde
                document.getElementById('btnEnviarCorreoReestablecerClave').removeAttribute('disabled');  // se habilita el boton registrar
            } else {
               document.getElementById('empResult').innerHTML === ("El correo no se encuentra registrado."); //se escribe en rojo
               document.getElementById('empResult2').innerHTML="";  // se limpia el div verde
               document.getElementById('btnEnviarCorreoReestablecerClave').setAttribute('disabled','true');   // se dehabilita el boton       
               
            }
        }
    }
}


function focusIn( ) {
    document.getElementById("txtCorreo").focus( );
}




