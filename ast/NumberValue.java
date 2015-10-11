package ast;

public class NumberValue implements Expr{
	private int value;
	protected boolean hasParen = false;
	private String feature = "value";
	
	public NumberValue(int value, boolean hasParen) {
		this.value = value;
		this.hasParen = hasParen;
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
		if (hasParen) {
			return sb.append("(").append(value).append(")");
		}
		else {
			return sb.append(value);
		}
	}


	@Override
	public Node copy() {
		// TODO Auto-generated method stub
		return new NumberValue(this.value, this.hasParen);
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
