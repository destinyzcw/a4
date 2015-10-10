package ast;

public class Expression implements Expr{
	protected Expr left;
	protected String op;
	protected Expr right;
	protected boolean hasParen = false;
	
	/** for two-hand expression */
	public Expression(Expr left, String op, Expr right, boolean hasParen) {
		this.left = left;
		this.op = op;
		this.right = right;
		this.hasParen = hasParen;
	}
	
	/** for single-hand expression */
	public Expression(Expr left, boolean hasParen) {
		this.left = left;
		this.hasParen = hasParen;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		if(this.right==null)
			return this.left.size()+1;
		else
			return this.left.size()+this.right.size()+1;
	}

	@Override
	public Node nodeAt(int index) {
		// TODO Auto-generated method stub
    	if(index==0) return this;
    	else if(index<=this.left.size()){
    		return this.left.nodeAt(index-1);
    	}
    	else
    		return this.right.nodeAt(index-1-this.left.size());
	}

	@Override
	public StringBuilder prettyPrint(StringBuilder sb) {
		if (hasParen) {
    		if (right != null) {
    			sb = sb.append("(");
    			sb = this.left.prettyPrint(sb).append(" " + op + " ");
    			return this.right.prettyPrint(sb).append(")");
    		}
    		else {
    			sb = sb.append("(");
    			return this.left.prettyPrint(sb).append(")");
    		}
    	}
    	else {
    		if (right != null) {
    			sb = sb.append(this.left.prettyPrint(sb)).append(" " + op + " ");
    			return this.right.prettyPrint(sb);
    		}
    		else {
    			return this.left.prettyPrint(sb);
    		}
    	}
	}

	public enum Addop {
        Plus, Minus, Mul, Div, Mod;
    }

	@Override
	public Node copy() {
		// TODO Auto-generated method stub
		return null;
	}
}
