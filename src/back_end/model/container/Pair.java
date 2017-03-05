package back_end.model.container;

public class Pair<A, B> {
	private final A first;
	private final B second;
	
	public Pair(A a, B b){
		first = a;
		second = b;
	}
	
	public A getA(){
		return first;
	}
	
	public B getB(){
		return second;
	}

}
