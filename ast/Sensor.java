package ast;

public class Sensor implements Expr{
	protected String keyword = "";
	protected Expr expr;
	protected boolean hasParen = false;
	
	public Sensor(String keyword, Expr expr, boolean hasParen) {
		this.keyword = keyword;
		this.expr = expr;
		this.hasParen = hasParen;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return expr.size()+1;
	}

	@Override
	public Node nodeAt(int index) {
		// TODO Auto-generated method stub
    	index%=this.size();
		if(index==0) return this;
		return expr.nodeAt(index-1);
	}

	@Override
	public StringBuilder prettyPrint(StringBuilder sb) {
		if (hasParen) {
			sb = sb.append("(").append(this.keyword).append("[");
			return this.expr.prettyPrint(sb).append("]").append(")");
		}
		else {
			sb = sb.append(this.keyword).append("[");
			return this.expr.prettyPrint(sb).append("]");
		}
	}

	@Override
	public Node copy() {
		return new Sensor(new String(this.keyword), (Expr) expr.copy(), this.hasParen);
	}
}
