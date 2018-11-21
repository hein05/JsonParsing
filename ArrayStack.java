
public class ArrayStack<E> implements Stack<E> {
	static final int CAPACITY = 100;
	  E[] data;
	  int top;

	  public ArrayStack() {
	    this(CAPACITY); // calls the other constructor
	  }

	  public ArrayStack(int specifiedCapacity) {
	    data = (E[]) new Object[specifiedCapacity]; // warning
	    top = -1;
	  }

	  public int size() {
	    return top + 1;
	  }

	  public void push(E e) {
	    if (top > data.length - 1) {
	      System.out.println("Stack is full! Cannot add " + e);
	      return;
	    }
	    top++;
	    data[top] = e;
	  }

	  public E pop() {
	    if (top < 0) return null;
	    E temp = data[top];
	    data[top] = null; //help garbage collector
	    top--;
	    return temp;
	  }
	  
//	  public E popAll () {
//		  if (top < 0) return null;
//		  E temp;
//		  for (E obj: data){
//			  temp = obj;
//			  obj = null; //help garbage collector
//			  top--;
//			  return temp;
//		  }
//	  }
	  
	  public void printArray() {
		  for (int i = 0; i < data.length; i++) {
			  if (data[i] != null) {
				  System.out.print(data[i]);
			  }
		  }
		  System.out.println();
	  }
}
