/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
      
var req;
function validarCedula(campo) {
    if(campo.value==='') return false;
    var url = "../vc?txtcedula=" + escape(campo.value);
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
            if (req.responseText.toString() === "Cédula disponible.") {
                document.getElementById('empResult').innerHTML = "";  //se limpia el div rojo
                document.getElementById('empResult2').innerHTML = req.responseText;  // se escribe en verde
                document.getElementById('btnRegistrarUsuario').removeAttribute('disabled');  // se habilita el boton registrar
                document.getElementById('btnRegistrarAsociacion').removeAttribute('disabled');  // se habilita el boton registrar
            } else {
               document.getElementById('empResult').innerHTML = req.responseText; //se escribe en rojo
               document.getElementById('empResult2').innerHTML="";  // se limpia el div verde
               document.getElementById('btnRegistrarUsuario').setAttribute('disabled','true');   // se dehabilita el boton       
               document.getElementById('btnRegistrarAsociacion').removeAttribute('disabled','true');  // se habilita el boton registrar
            }
        }
    }
}


function focusIn( ) {
    document.getElementById("txtcedula").focus( );
}