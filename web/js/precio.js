/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var xmlHttp;
function listarprecios(idProducto) {

    if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        alert("El navegador no soporta Ajax!");
        return;
    }

    var url = "listarprecioproducto.jsp?id=" + idProducto;
    xmlHttp.onreadystatechange = resultadoPrecios;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
    
}

function resultadoPrecios() {
    if (xmlHttp.readyState === 4) {
        document.getElementById("subcategoria2").innerHTML = xmlHttp.responseText;
    }
}



