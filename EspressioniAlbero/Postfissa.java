package alberoespressioni;

/**
 *
 * @author Florian
 */
class Postfissa {

    public String leggi(String input) {
        if (input == null) {
            return null;
        }
        char[] arrayChar = input.toCharArray();
        Pila<Character> pila = new Pila<>();
        StringBuilder stringa = new StringBuilder();

        for (int i = 0; i < arrayChar.length; i++) {
            switch (arrayChar[i]) {
                case '+':
                case '-':
                    while (pila.getLunghezza() != 0 && (pila.getFront() == '*' || pila.getFront() == '/')) {
                        stringa.append(' ');
                        stringa.append(pila.pop());
                    }
                    stringa.append(' ');
                    pila.push(arrayChar[i]);
                    break;
                case '*':
                case '/':
                    stringa.append(' ');
                    pila.push(arrayChar[i]);
                    break;
                case '(':
                    pila.push(arrayChar[i]);
                    break;
                case ')':
                    while (pila.getLunghezza() != 0 && pila.getFront() != '(') {
                        stringa.append(' ');
                        stringa.append(pila.pop());
                    }
                    pila.pop();
                    break;
                default:
                    stringa.append(arrayChar[i]);
                    break;
            }
        }

        while (pila.getLunghezza() != 0) {
            stringa.append(' ').append(pila.pop());
        }

        return stringa.toString();
    }
}
