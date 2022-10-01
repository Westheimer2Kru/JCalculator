package org.Calculator;

import static org.Calculator.Utils.Utils.removeSpaces;

import org.Calculator.operations.consoleOperations.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//singleton pattern
public class ConsoleCalculator implements ICalculator{
    private ConsoleCalculator(){
        operationTable.put('+', new Addition());
        operationTable.put('/', new Division());
        operationTable.put('*', new Multiplication());
        operationTable.put('-', new Subtraction());
    }

    private static final ConsoleCalculator instance = new ConsoleCalculator();
    Map<Character, IOperation> operationTable = new HashMap<>();
    private static final Logger logger = Logger.getLogger(ConsoleCalculator.class);

    public static ConsoleCalculator getInstance(){
        return instance;
    }



    public BigDecimal calculate(String input) { //решение с помощью 2 стеков
        char[] charArr = removeSpaces(input).toCharArray();
        Stack<Character> operators = new Stack<>();
        Stack<BigDecimal> digits = new Stack<>();

        StringBuilder currentDigit = null;

        for(int i = 0; i < charArr.length; i++){
            char elem = charArr[i];  // для удобства обращения и чтения кода

            if(Character.isDigit(elem)){ //если число, сохраняем в первый стек
                if(currentDigit == null){ // для двузначных и более чисел необходимо последовательно сохранить каждый разряд
                    currentDigit = new StringBuilder().append(elem);
                } else {
                    currentDigit.append(elem);
                }
            } else if (elem == '.') { // проверка числа на десятичную дробь
                if(currentDigit != null){
                    currentDigit.append(elem);
                } else {
                    logger.log(Level.ERROR, "Incorrect decimal separator position");
                    throw new IllegalArgumentException("incorrect Decimal separator position");
                }
            } else if (isOperator(elem)){
                // если до этого мы записывали некоторое число, его необходимо сохранить первый стек
                if(currentDigit != null){
                    digits.push(new BigDecimal(currentDigit.toString()));
                    currentDigit = null;
                }

                if(operators.isEmpty() || operators.peek() == '(') {
                    operators.push(elem);
                } else {
                    try {
                        // при большем приоритете сохраняем оператор во второй стек
                        if (operationTable.get(operators.peek()).getPriority() < operationTable.get(elem).getPriority()) {
                            operators.push(elem);
                        } else {
                            // иначе вычисляем результат последних двух чисел и сохраняем в первый стек
                            digits.push(operationTable.get(operators.pop()).operationResult(digits.pop(), digits.pop()));
                            i--; // шаг назад, чтобы вернуться к символу оператора
                        }
                    } catch (EmptyStackException ex) {
                        logger.log(Level.ERROR, ex.getMessage());
                        throw ex;
                    }

                }
            } else if(isBracket(elem)){ // открывающую скобку сохраняем во второй стек, закрывающая вычисляет выражение в скобках
                if(elem == '('){
                    operators.push(elem);
                } else {
                    if(operators.isEmpty()) {
                        logger.log(Level.ERROR, "incorrect bracket order or number!");
                        throw new IllegalArgumentException("incorrect bracket order or number!");
                    }

                    if(currentDigit != null) {
                        digits.push(new BigDecimal(currentDigit.toString()));
                        currentDigit = null;
                    }

                    while (operators.peek() != '(') {
                        digits.push(operationTable.get(operators.pop()).operationResult(digits.pop(), digits.pop()));
                        if (operators.isEmpty()) {
                            logger.log(Level.ERROR, "incorrect bracket order or number!");
                            throw new IllegalArgumentException("incorrect bracket order or number!");
                        }
                    }
                    operators.pop();
                }
            } else {
                logger.log(Level.ERROR, "Incorrect character!");
                throw new IllegalArgumentException("Incorrect character!");
            }
        }
        if(currentDigit != null) {
            digits.push(new BigDecimal(currentDigit.toString()));
        }

        if(operators.size() >= digits.size()) { // число операторов должно быть на единицу меньше, чем чисел
            logger.log(Level.ERROR, "Too much operators!");
            throw new ArithmeticException("Too much operators!");
        }

        // вычисляем оставшиеся операторы
        while (!operators.isEmpty()){
            digits.push(operationTable.get(operators.pop()).operationResult(digits.pop(), digits.pop()));
        }

        return digits.pop();
    }

    private boolean isOperator(Character elem){
        return "+-/*".contains(elem.toString());
    }

    private boolean isBracket(Character elem){
        return "()".contains(elem.toString());
    }

}
