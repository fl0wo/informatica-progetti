
/**
 * Made by fI0w
 * @type type
 */

//pressione temperatura e grado di umidita
// Antartica Asia Africa NordAmerica SudAmerica Europa Oceania
//geoname_id,locale_code,continent_code,continent_name,country_iso_code,country_name,subdivision_1_iso_code,subdivision_1_name,subdivision_2_iso_code,subdivision_2_name,city_name,metro_code,time_zone,is_in_european_union

/*{
 
 toString(){
 return linee[10];
 },
 
 id: linee[0],
 code_locale: linee[1],
 code_continente: linee[2],
 nome_continente: linee[3],
 iso_paese: linee[4],
 nome_paese: linee[5],
 sigla_provinca: linee[8],
 nome_provinca: linee[9],
 nome_citta: linee[10]
 }
 
 */

var trie;
var continenti = ["eu", "na", "sa", "af", "as", "oc", "an"];

var id_continente_selezionato;

class Weather {

    static __load_weather() {

        var xhttp = new XMLHttpRequest();
        var ris = "";

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                ris = this.responseText;
            }
        };
        
        var e = document.getElementById("stato");
        var country = trie[id_continente_selezionato][e.options[e.selectedIndex].value][1].find(document.getElementById("find").value.toLowerCase());
        var city = trie[id_continente_selezionato][e.options[e.selectedIndex].value][0];
        
        xhttp.open("GET", "http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country[0] + "&APPID=209a1816d8e3783a3c3b5e8ec118424f", false);
        xhttp.send();

        return ris;
    }

    static __make_JSON(stringa) {
        this.json = JSON.parse(stringa);
    }

    static __get_temperature() {
        alert(this.json.main.temp + " Kelvin " +"\n"+((this.json.main.temp |0)-273.15)+" C°");
    }

    static __get_humidity() {
        alert(this.json.main.humidity + " g/m³");
    }

    static __get_pression() {
        alert(this.json.main.pressure + " Pascal");
    }

}

function __costruisci_albero() {
    var txtFile = new XMLHttpRequest();
    txtFile.open("GET", "files/citta.csv", true);
    txtFile.onreadystatechange = function () {
        if (txtFile.readyState === 4 && txtFile.status === 200) {

            var lines = txtFile.responseText.split("\n");

            trie = [[]];

            for (var l = 0; l < continenti.length; l++) {
                trie[continenti[l]] = [];
            }

            var linee;
            for (var i = 1; i < lines.length - 1; i++) {

                linee = lines[i].toLowerCase().split(",");

                if (trie[linee[2]][linee[4]] == null) {
                    trie[linee[2]][linee[4]] = [linee[5], new SuffixTrie()];
                }

                if(linee[10][0] == '"'){
                    linee[10] = linee[10].substring(1,linee[10].lenght-1);
                }

                trie[linee[2]][linee[4]][1].add(linee[10]);
            }

        }
    };
    txtFile.send();
}

function __printa() {
    var e = document.getElementById("stato");
    document.getElementById("suggerimenti").innerHTML = trie[id_continente_selezionato][e.options[e.selectedIndex].value][1].find(document.getElementById("find").value.toLowerCase());
}

function __aggiorna_continente() {

    var elemento = document.getElementById("continente");
    var stringa = elemento.options[elemento.selectedIndex].value;
    var index = stringa.substring(stringa.length - 3, stringa.length - 1).toLowerCase();
    var chiavi = Object.keys(trie[index]);

    var sel_stato = document.getElementById("stato");

    id_continente_selezionato = index;

    __rimuovi_opzioni(sel_stato);

    for (var i = 0; i <= chiavi.length; i++) {
        var opt = document.createElement('option');
        opt.value = chiavi[i];
        opt.innerHTML = trie[index][chiavi[i]][0];
        sel_stato.appendChild(opt);
    }

}

function __rimuovi_opzioni(selectbox) {
    var i;
    for (i = selectbox.options.length - 1; i >= 0; i--) {
        selectbox.remove(i);
    }
}



var SuffixTrie;
SuffixTrie = (function () {
    function SuffixTrie() {
        this.count = 0;
        this.structure = {};
        this.prefix = "";
    }
    SuffixTrie.prototype.add = function (string) {
        var chr, index, length, next, node;
        node = this.structure;
        length = string.length;
        index = 0;
        while (index < length) {
            chr = string[index++];
            next = node[chr];
            if (next) {
                node = next;
            } else {
                node[chr] = {};
                node = node[chr];
            }
        }
        if (node.terminator) {
            return false;
        } else {
            node.terminator = true;
            this.count++;
            return true;
        }
    };
    SuffixTrie.prototype.remove = function (string) {
        var chr, index, length, node;
        node = this.structure;
        length = string.length;
        index = 0;
        while (index < length) {
            chr = string[index++];
            node = node[chr];
            if (!node) {
                return false;
            }
        }
        if (node.terminator) {
            delete node.terminator;
            this.count--;
            return true;
        } else {
            return false;
        }
    };
    SuffixTrie.prototype.contains = function (string) {
        var node;
        node = this.findNode(string);
        return node !== null && node.terminator;
    };
    SuffixTrie.prototype.subTrie = function (prefix) {
        var node, subTrie;
        node = this.findNode(prefix);
        subTrie = new JsSuffixTrie;
        subTrie.structure = node;
        subTrie.prefix = prefix;
        return subTrie;
    };
    SuffixTrie.prototype.find = function (prefix) {
        return SuffixTrie.nodeToArray(this.findNode(prefix), prefix);
    };
    SuffixTrie.prototype.findNode = function (string) {
        var currentChar, index, length, node;
        node = this.structure;
        length = string.length;
        index = 0;
        while (index < length) {
            currentChar = string[index++];
            node = node[currentChar];
            if (!node) {
                return null;
            }
        }
        return node;
    };
    SuffixTrie.prototype.each = function (callback) {
        return SuffixTrie.each(callback, this.structure, 0, this.prefix);
    };
    SuffixTrie.each = function (callback, node, index, string) {
        var property;
        if (node.terminator) {
            callback(index++, string);
        }
        for (property in node) {
            index = this.each(callback, node[property], index, string + property);
        }
        return index;
    };
    SuffixTrie.prototype.size = function () {
        return this.count;
    };
    SuffixTrie.prototype.calculateSize = function (node) {
        var property, size;
        if (node == null) {
            node = this.structure;
        }
        size = node.terminator ? 1 : 0;
        for (property in node) {
            size += this.calculateSize(node[property]);
        }
        return size;
    };
    SuffixTrie.fromArray = function (array) {
        var i, length, trie;
        trie = new SuffixTrie;
        length = array.length;
        i = 0;
        while (i < length) {
            trie.add(array[i++]);
        }
        trie.count = i;
        return trie;
    };
    SuffixTrie.prototype.toArray = function () {
        return SuffixTrie.nodeToArray(this.structure, this.prefix);
    };
    SuffixTrie.nodeToArray = function (node, prefix) {
        var array;
        array = [];
        this.each(function (index, value) {
            return array[index] = value;
        }, node, 0, prefix);
        return array;
    };
    SuffixTrie.fromJSON = function (json) {
        var trie;
        trie = new SuffixTrie;
        trie.structure = JSON.parse(json);
        trie.calculateSize();
        return trie;
    };
    SuffixTrie.prototype.toJSON = function () {
        return JSON.stringify(this.structure);
    };
    return SuffixTrie;
})();
