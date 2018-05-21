
var json;

function __connect(){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			json = JSON.parse(xhttp.responseText);
		}
	};
	xhttp.open("GET", "http://www.apilayer.net/api/live?access_key=dd8b5c4bb09a49a9477c56a344e451a7", true);
	xhttp.send();
}

function __get(){
	
	var N = document.getElementById("da-euro").value;
	
	var sel_da = document.getElementById("da_sel");
	var sel_a = document.getElementById("a_sel");
	
	var da = sel_da[sel_da.selectedIndex].value;
	var a = sel_a[sel_a.selectedIndex].value;
	
	var passaggio_intermedio = json.quotes["USD"+""+da]; 
	var passaggio_due = json.quotes["USD"+""+a]; 
	
	
	alert(passaggio_intermedio + " : "+ passaggio_due);
	
	var ris = N * ((1/passaggio_intermedio) * (passaggio_due));
	
	document.getElementById("demo").innerHTML = ris;
}

