package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure
 * to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {
	private LLNode<T> top;
	private int stackSize = 0;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty() == false){
			T tempVar = top.getData();
			top = top.getNext();
			stackSize--;
			return tempVar;
		}
		else{
			throw new StackUnderflowException("Pop attempted on an empty stack.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T top() throws StackUnderflowException {
		if(isEmpty() == false){
			T returnTop = top.getData();
			return returnTop;
		}
		else{
			throw new StackUnderflowException("Top attempted on an empty stack.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		if(top == null){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
    	return stackSize;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T elem) {
		LLNode<T> newNode = new LLNode<T>(elem);
		newNode.setNext(top);
		top = newNode;
		stackSize++;
	}
}
