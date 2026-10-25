import java.util.Scanner;
import java.util.Stack;

import javax.naming.OperationNotSupportedException;

/** STUDENT FILE: Complete evaluate. Leave all other project files unchanged. */
public class PostfixEvaluator {
    /**
     * Evaluates a space-separated postfix expression using integer arithmetic.
     * Supported operators: +, -, *, / (integer division).
     *
     * @throws PostFixException for blank, malformed, or invalid expressions
     * @throws ArithmeticException for division by zero
     */
    public static int evaluate(String expression) {
        expression = expression.trim();

        BoundedStackInterface<Integer> stack = new ArrayStack<>(100);
        Scanner tokens = new Scanner(expression);

        while (expression.length() != 0) {

            int index = expression.indexOf(" ");

            String component = "";
            if (index == -1) {
                component = expression;
                expression = "";
            }
            else {
                component = expression.substring(0,index);
                expression = expression.substring(index+1);
            }

            try {
                int num = Integer.parseInt(component);

                try {
                    stack.push(num);
                } catch (StackOverflowException e) {
                    throw new PostFixException("Too many ints.");
                }

                continue;
            } catch(NumberFormatException e) {}

            int one = 0;
            int two = 0;

            if (component.equals("+")) {
                
                try {
                    one = stack.top();
                    stack.pop();

                    two = stack.top();
                    stack.pop();

                } catch (StackUnderflowException e) {
                    throw new PostFixException("Invalid syntax of operation.");
                }

                try {
                    stack.push(one + two);
                } catch (StackOverflowException e) {
                    throw new PostFixException("Too many ints");
                }
            }
            else if (component.equals("-")) {

                try {
                    one = stack.top();
                    stack.pop();

                    two = stack.top();
                    stack.pop();

                } catch (StackUnderflowException e) {
                    throw new PostFixException("Invalid syntax of operation.");
                }

                try {
                    stack.push(one - two);
                } catch (StackOverflowException e) {
                    throw new PostFixException("Too many ints");
                }
            }
            else if (component.equals("*")) {

                try {
                    one = stack.top();
                    stack.pop();

                    two = stack.top();
                    stack.pop();

                } catch (StackUnderflowException e) {
                    throw new PostFixException("Invalid syntax of operation.");
                }

                try {
                    stack.push(one * two);
                } catch (StackOverflowException e) {
                    throw new PostFixException("Too many ints");
                }
            }
            else if (component.equals("/")) {

                try {
                    one = stack.top();
                    stack.pop();

                    two = stack.top();
                    stack.pop();

                } catch (StackUnderflowException e) {
                    throw new PostFixException("Invalid syntax of operation.");
                }

                int res = 0;
                try {
                    res = two / one;
                } catch (ArithmeticException e) {
                    throw new ArithmeticException("Division by zero");
                }

                try {
                    stack.push(res);
                } catch (StackOverflowException e) {
                    throw new PostFixException("Too many ints");
                }
            }
            else {
                throw new PostFixException("Invalid opperand: " + component + ".");
            }
        }

        int result = 0;
        try {
            result = stack.top();
            stack.pop();
        } catch (StackUnderflowException e) {
            throw new PostFixException("Invalid syntax of operation.");
        }

        if (!stack.isEmpty()) {
            throw new PostFixException("Invalid operation: too few operands.");
        }


        // TODO 1: Process tokens one at a time.
        // TODO 2: If a token is an integer, push it onto the stack.
        // TODO 3: If it is +, -, *, or /, pop two operands.
        //         IMPORTANT: the first popped number is the RIGHT operand.
        // TODO 4: Calculate and push the result.
        // TODO 5: Reject unknown tokens and expressions with too few operands.
        // TODO 6: After processing, require EXACTLY one result on the stack.
        //         Catch stack underflow/overflow or check before operations.
        // Close the Scanner when you are finished.

        tokens.close();
        return result;
    }
}
