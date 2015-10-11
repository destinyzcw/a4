package ast;

/** A data structure which represents a string like:  wait, forward.... */
public class Action implements Expr{
	protected String symbol = "";
	private String feature = "action";
	public Action(String symbol) {
		this.symbol = symbol;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Node nodeAt(int index) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public StringBuilder prettyPrint(StringBuilder sb) {
		// TODO Auto-generated method stub
		return sb.append(this.symbol);
	}
	
	@Override
	public Node copy() {
		return new Action(symbol);
	}
	@Override
	public Node getChild(int index) {
		return null;
	}
	@Override
	public void setChild(int leftOrRight, Node newChild) {
		return;
	}
	@Override
	public String getFeature() {
		return feature;
	}

}
