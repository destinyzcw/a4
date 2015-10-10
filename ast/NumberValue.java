package ast;

public class NumberValue implements Expr{
	private int value;
	protected boolean hasParen = false;
	
	public NumberValue(int value, boolean hasParen) {
		this.value = value;
		this.hasParen = hasParen;
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
		if (hasParen) {
			return sb.append("(").append(value).append(")");
		}
		else {
			return sb.append(value);
		}
	}

}
