/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var req;
function validarClave(campo) {
    if(campo.value==='') return false;
    var url = "../vcla?txtClaveActual=" + escape(campo.value);
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
            if (req.responseText.toString() === "Correcta") {
                document.getElementById('empResult1').innerHTML = "";  //se limpia el div rojo
                document.getElementById('empResult1').innerHTML = req.responseText;  // se escribe en verde
                document.getElementById('btnReestablecer').removeAttribute('disabled');  // se habilita el boton registrar
            } else {
               document.getElementById('empResult1').innerHTML = req.responseText; //se escribe en rojo
               document.getElementById('empResult3').innerHTML="";  // se limpia el div verde
               document.getElementById('btnRegistrarUsuario').setAttribute('disabled','true');   // se dehabilita el boton       
               
            }
        }
    }
}


function focusIn( ) {
    document.getElementById("txtClaveActual").focus( );
}

