package ast;

public class Update implements Expr{
	protected Expr left;
	protected Expr right;
	
	public Update(Expr left, Expr right) {
		this.left = left;
		this.right = right;
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
		// TODO Auto-generated method stub
		sb = this.left.prettyPrint(sb).append(" := ");
		return this.right.prettyPrint(sb);
	}

	@Override
	public Node copy() {
		return new Update((Expr) this.left.copy(), (Expr) this.right.copy());
	}
	
}
