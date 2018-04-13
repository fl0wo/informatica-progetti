
// risultato massimo che potrebbe capitare
const MAX_RIS = 20;
const SFASAMENTO = 5;
const BTN = "button";

var main;   //div
var ultimo_el;  // button
var ultimo_x, ultimo_y; // int int
var gap = 50;   // int 

// <- boolean false = 0; true = !=0;
var or = Math.random() < .5; // isOrrizzontale 

var n_incrocio;

var operazioni = ["+", "-", "*", "/"];

/*
 * TX testo del nuovo elemento
 * T il tipo dell elemento da aggiungere
 * GX é il gap X tra un bottone e l altro
 * GY é il gap Y tra un bottone e l altro
 * 
 * Aggiunge un elemento tenendo conto 
 * della posizione dell ultimo aggiunto
 * e sfasando quello nuovo di un gap
 * che viene moltiplicato al boolean 
 *  or.
 */
function add(A, T, GX, GY) {
    //il nuovo bottone
    var E = document.createElement(T);

    E.textContent = A[A.length - 1];
    E.onclick = function () {
        //N%2==0 ? N+1 : N; se é pari lo fo dispari
        //N+=N%2;

        var N;

        if (or) {//quello nuovo sarà orrizzontale
            N = rand_eq_orr(A[A.length - 1]);
            ultimo_x -= gap;
        } else {
            N = rand_eq_ver(A[2]);
            n_incrocio = A[A.length - 1];
            ultimo_x -= (gap) * (A.length - 3);
            ultimo_y -= (gap) * (A.length - 2);
        }

        ultimo_el = add_ric(N, 4, BTN, gap * (or), gap * (!or));
        
        sposta_div(-20,-20);
    };

    set_pos(E, ultimo_x += GX, ultimo_y += GY);
    //window.scrollTo(10000, 10000); 

    main.appendChild(E);
    or = !or;

    return E;
}

function add_ric(A, N, T, GX, GY) {
    if (N > 0) {
        var E = document.createElement(T);
        E.textContent = A[A.length - N - 1];

        set_pos(E, ultimo_x += GX, ultimo_y += GY);
        main.appendChild(E);

        return add_ric(A, N - 1, T, GX, GY);
    } else {
        return add(A, BTN, GX, GY);
    }
}

//IMPOSTA LA POSIZIONE IN COORDINATE RELATIVE
function set_pos(elemento, x, y) {
    elemento.style.left = x + "px";
    elemento.style.top = y + "px";
}

// PARTE ALL AVVIO IN AUTOMATICO
window.onload = function () {
    main = document.getElementById("main");
    var btnTmp = document.createElement(BTN);
    
    ultimo_x = (window.innerWidth /2) - ( or* (gap*4)) + btnTmp.style.width/2;
    ultimo_y = (window.innerHeight/2) - ( !or* (gap*4)) - btnTmp.style.height/2;

    //array
    n_incrocio = rand_range(-MAX_RIS, MAX_RIS);
    var A = rand_equazione_by_ris(rand_range(-MAX_RIS, MAX_RIS));

    // moltiplico un boolean con un double 
    ultimo_el = add_ric(A, 4, BTN, gap * (or), gap * (!or));
};


function rand_range(min, max) {
    return min + Math.floor((Math.random() * (max - min)) + 1);
}

function rand_equazione_by_ris(ris) {
    var n2 = rand_range(-ris, +ris);
    return [ris - n2, "+", n2, "=", ris];
}

function rand_eq_ver(n2) {
    var ris = rand_range(-MAX_RIS / SFASAMENTO, +MAX_RIS * SFASAMENTO);
    var op = operazioni[rand_range(0, operazioni.length - 1)];
    return [n_incrocio, op, n2, "=", (expr(n_incrocio + "" + op + "" + n2)).toFixed(2)];
}
function rand_eq_orr(n1) {
    var ris = rand_range(-MAX_RIS / SFASAMENTO, +MAX_RIS * SFASAMENTO);
    var op = operazioni[rand_range(0, operazioni.length - 1)];
    return [n1, op, Math.floor(expr(ris + "" + op + "" + n1)), "=", ris];
}

function expr(expr) {

    var chars = expr.split("");
    var n = [], op = [], index = 0, oplast = true;

    n[index] = "";

    // Parse the expression
    for (var c = 0; c < chars.length; c++) {

        if (isNaN(parseInt(chars[c])) && chars[c] !== "." && !oplast) {
            op[index] = chars[c];
            index++;
            n[index] = "";
            oplast = true;
        } else {
            n[index] += chars[c];
            oplast = false;
        }
    }

    // Calculate the expression
    expr = parseFloat(n[0]);
    for (var o = 0; o < op.length; o++) {
        var num = parseFloat(n[o + 1]);
        switch (op[o]) {
            case "+":
                expr = expr + num;
                break;
            case "-":
                expr = expr - num;
                break;
            case "*":
                expr = expr * num;
                break;
            case "/":
                expr = expr / num;
                break;
        }
    }

    return expr;
}

function sposta_div(x_pos, y_pos) {
  main.style.left = (main.style.left+x_pos)+'px';
  main.style.top = (main.style.top + y_pos)+'px';
}