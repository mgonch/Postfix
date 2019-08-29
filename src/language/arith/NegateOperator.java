package language.arith;

import language.BinaryOperator;
import language.Operand;

/**
 * The {@code NegateOperator} is an operator that performs negation on a single integer
 * @author jcollard
 *
 */

public class NegateOperator extends UnaryOperator<Integer> {

    @Override
    public Operand<Integer> performOperation() {

        if(op == null)
            throw new IllegalStateException("Could not perform operation prior to operands being set.");
        Integer result = op.getValue() * (-1);
        return new Operand<Integer>(result);
    }

    public String toString() {
        return "!";
    }
}
