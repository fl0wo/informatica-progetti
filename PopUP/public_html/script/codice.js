
function httpGet(theUrl){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false per richiesta sincronizzata
	xmlHttp.send(null);
	
	alert(xmlHttp.responseText);
	
    return xmlHttp.responseText;
}

function clickato(id_bottone) {

}


function conferma() {

}

