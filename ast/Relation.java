package ast;

public class Relation implements Condition{
	protected Expr left;
	protected String op;
	protected Expr right;
	protected boolean hasBrace = false;
	private String feature = "bool";
	
	public Relation(Expr left, String op, Expr right, boolean hasBrace) {
		this.left = left;
		this.op = op;
		this.right = right;
		this.hasBrace = hasBrace;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.left.size()+this.right.size()+1;
	}

	@Override
	public Node nodeAt(int index) {
		// TODO Auto-generated method stub
    	index%=this.size();
    	if(index==0) return this;
    	else if(index<=this.left.size()){
    		return this.left.nodeAt(index-1);
    	}
    	else
    		return this.right.nodeAt(index-1-this.left.size());
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

	@Override
	public Node copy() {
		return new Relation((Expr) left.copy(), new String(this.op), (Expr) right.copy(), this.hasBrace);
	}

	@Override
	public Node getChild(int index) {
		if (this.right == null)
			return left;
		else {
			if (index % 2 == 0) // even to return left child
				return left;
			else
				return right;
		}
	}

	@Override
	public void setChild(int leftOrRight, Node newChild) {
		if (leftOrRight % 2 == 0)
			this.left = (Expr) newChild;
		else
			this.right = (Expr) newChild;		
	}

	@Override
	public String getFeature() {
		return feature;
	}
}
