package evaluator.arith;

import language.Operand;
import language.Operator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions.
 *
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;

	/**
	 * Constructs an {@link ArithPostfixEvaluator}
	 */
	public ArithPostfixEvaluator(){
        // Initialize to your LinkedStack
		stack = new LinkedStack<Operand<Integer>>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
		ArithPostfixParser parser = new ArithPostfixParser(expr);
		for (Token<Integer> token : parser) {
			Type type = token.getType();
			switch(type){
			case OPERAND:
			// What do we do when we see an operand?
				Operand<Integer> operand = token.getOperand();
				stack.push(operand);
				break;
			case OPERATOR:
            // What do we do when we see an operator?
				Operator<Integer> operator = token.getOperator();
				if(operator.getNumberOfArguments() == 1){
					operator.setOperand(0, stack.pop());
				}
				else if (operator.getNumberOfArguments() > 1){
					operator.setOperand(1, stack.pop());
					operator.setOperand(0, stack.pop());
				}
				stack.push(operator.performOperation());
				break;
			default:
				throw new IllegalStateException("Parser returned an invalid Type: " + type);
			}
		}
		// What do we return?`
		Integer result = stack.pop().getValue();
		if (!stack.isEmpty()){
			throw new IllegalPostfixExpressionException();
		}
		else{
			return result;
		}
	}
}
