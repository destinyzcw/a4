package ast;

public class Relation implements Condition{
	protected Expr left;
	protected String op;
	protected Expr right;
	protected boolean hasBrace = false;
	
	public Relation(Expr left, String op, Expr right, boolean hasBrace) {
		this.left = left;
		this.op = op;
		this.right = right;
		this.hasBrace = hasBrace;
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
		if (hasBrace) {
			sb = sb.append("{");
			sb = this.left.prettyPrint(sb).append(" " + this.op + " ");
			return this.right.prettyPrint(sb).append("}");
		}
		else {
			sb = this.left.prettyPrint(sb).append(" " + this.op + " ");
			return this.right.prettyPrint(sb);
		}
	}
	
	public enum Operator {
       BiggerEqual, SmallerEqual, Equal, Bigger, Smaller, Notequal;
    }

}
