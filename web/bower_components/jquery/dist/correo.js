/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var req;
function validarCorreo(campo) {
    if(campo.value==='') return false;
    var url = "vrc?txtCorreo=" + escape(campo.value);
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
            if (req.responseText.toString() === alert("El correo no se encuentra registrado")) {
                document.getElementById('empResult1').innerHTML = "";  //se limpia el div rojo
                document.getElementById('empResult3').innerHTML = req.responseText;  // se escribe en verde
                document.getElementById('reestablecer').removeAttribute('disabled', 'true');  // se habilita el boton registrar
            } else {
               document.getElementById('empResult1').innerHTML = req.responseText; //se escribe en rojo
               document.getElementById('empResult3').innerHTML="";  // se limpia el div verde
               document.getElementById('reestablecer').setAttribute('disabled','true');   // se dehabilita el boton       
               
            }
        }
    }
}


function focusIn( ) {
    document.getElementById("txtCorreo").focus( );
}
