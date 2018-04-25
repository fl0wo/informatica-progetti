package calcolatrice;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calcolatore{
    private static double evaluate(String expression){
        char[] tokens = expression.toCharArray();
        Stack<Double> values = new Stack();
        Stack<Character> ops = new Stack();
        for(int i=0;i<tokens.length;i++){
            if(tokens[i] == ' ')
                continue;
            if(tokens[i] >= '0' && tokens[i] <= '9'){
                StringBuilder sbuf = new StringBuilder();
                while(i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.'))
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));
            }
            else if(tokens[i] == '(')
                ops.push(tokens[i]);
            else if(tokens[i] == ')'){
                while(ops.peek() != '(')
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
            else if(tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'){
                while(!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.push(tokens[i]);
            }
        }
        while(!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        return values.pop();
    }
    private static boolean hasPrecedence(char op1, char op2){
        if(op2 == '(' || op2 == ')')
            return false;
        if((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    private static double applyOp(char op, double b, double a){
        switch(op){
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if(b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
    public static String resolve(String expression){
        String temp = Double.toString(evaluate(initExpression(expression)));
        String result = new BigDecimal(Double.toString(Double.parseDouble(temp))).toPlainString().replace('.', ',');
        if(result.substring(result.indexOf(',')+1).equals("0"))
            return String.format("%.0f", Double.parseDouble(temp));
        return result;
    }
    private static String initExpression(String exp){
        if(isOperator(exp.charAt(exp.length()-1)))
            exp = exp.substring(0, exp.length()-1);
        Scanner input = new Scanner(System.in);
        String temp = checkBrackets(checkNegativeAndAbbreviations(formatExpression(exp)));
        String finalString = new String();
        for(int i=0;i<temp.length();i++){
            switch(temp.charAt(i)){
                case '+':
                case '-':
                case '*':
                case '/':
                    finalString += " " + temp.charAt(i) + " ";
                    break;
                case '(':
                    if(i == 0 || i < temp.length())
                        finalString += temp.charAt(i) + " ";
                    break;
                case ')':
                    finalString += " " + temp.charAt(i);
                    break;
                default:
                    finalString += temp.charAt(i);
                    break;
            }
        }
        ArrayList<Character> variables = new ArrayList();
        ArrayList<Object> variablesValues = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(finalString, ",|;:'£$%&=^?_§°ç#@E!ìèéùàò<>+-*/()0123456789. ");
        for(int i=0;stringTokenizer.hasMoreTokens();i++){
            char c = stringTokenizer.nextToken().charAt(0);
            if(variables.contains(c))
                continue;
            variables.add(c);
            System.out.print(variables.get(i) + " = ");
            String val = input.nextLine();
            switch(val.indexOf('.')){
                case -1:
                    variablesValues.add(Integer.parseInt(val));break;
                default:
                    variablesValues.add(Double.parseDouble(val));break;
            }
        }
        for(int j=0;j<variables.size();j++){
            while(finalString.indexOf(variables.get(j)) > -1){
                int pos = finalString.indexOf(variables.get(j));
                Object val = variablesValues.get(j);
                String value = val instanceof Integer ? Integer.toString((int)val) : Double.toString((double)val);
                finalString = finalString.substring(0, pos) + value + finalString.substring(pos+1);
            }
        }
        return finalString;
    }
    private static String formatExpression(String exp){
        exp = exp.replace(',', '.');
        exp = exp.replace('{', '(');
        exp = exp.replace('}', ')');
        exp = exp.replace('[', '(');
        exp = exp.replace(']', ')');
        exp = exp.replace(':', '/');
        exp = exp.replace('÷', '/');
        exp = exp.replace('×', '*');
        exp = exp.replaceAll("[;'£$%&=^?_§°ç#@!ìèéùàò<>| ]", "");
        return exp;
    }
    private static String checkNegativeAndAbbreviations(String exp){
        if(isOperator(exp.charAt(0)) && exp.charAt(1) >= '0' && exp.charAt(1) <= '9')
            exp = "0" + exp.charAt(0) + exp.substring(1);
        for(int i=0;i<exp.length();i++){
            if(i+1 <= exp.length() && exp.charAt(i) == '(' && exp.charAt(i+1) == '-')
                exp = exp.substring(0, i+1) + "0" + exp.substring(i+1);
            if(i+2 <= exp.length() && exp.charAt(i) == '(' && isOperator(exp.charAt(i+1)) && 
                    (i+1 == exp.length()-1 || exp.charAt(i+2) == ')' ))
                exp = exp.substring(0, i+1) + "0" + exp.substring(i+1, i+2) + "0" + exp.substring(i+2);
            if(i+3 <= exp.length() && exp.charAt(i) == '(' && exp.charAt(i+1) >= '0' && exp.charAt(i+1) <= '9' && 
                    isOperator(exp.charAt(i+2)) && (i+2 == exp.length()-1 || exp.charAt(i+3) == ')' ))
                exp = exp.substring(0, i+3) + "0" + exp.substring(i+3);
            if(i+3 <= exp.length() && exp.charAt(i) == '(' && isOperator(exp.charAt(i+1)) && exp.charAt(i+2) >= '0' && 
                    exp.charAt(i+2) <= '9' && (i+2 == exp.length()-1 || exp.charAt(i+3) == ')'))
                exp = exp.substring(0, i+1) + "0" + exp.substring(i+1);
        }
        return exp;
    }
    private static boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '×' || c == '÷';
    }
    private static String checkBrackets(String exp){
        String expression = exp;
        if(expression.charAt(expression.length()-1) == '(')
            expression += "0)";
        for(int i=0;i<expression.length();i++){
            if(i+1 <= expression.length())
                if(expression.charAt(i) == '(' && expression.charAt(i+1) == ')')
                    expression = expression.substring(0, i+1) + "0" + expression.substring(i+1);
        }
        int openBrackets = expression.length() - expression.replace("(", "").length();
        int closeBrackets = expression.length() - expression.replace(")", "").length();
        if(openBrackets == closeBrackets)
            return expression;
        else
            while(openBrackets > closeBrackets){
                expression += ")";
                closeBrackets++;
            }
        return expression;
    }
}