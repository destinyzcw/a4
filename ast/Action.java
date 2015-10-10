package ast;

/** A data structure which represents a string like:  wait, forward.... */
public class Action implements Expr{
	protected String symbol = "";
	
	public Action(String symbol) {
		this.symbol = symbol;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Node nodeAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuilder prettyPrint(StringBuilder sb) {
		// TODO Auto-generated method stub
		return sb.append(this.symbol);
	}

}
