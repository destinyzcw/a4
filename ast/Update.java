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
		sb = this.left.prettyPrint(sb).append(" := ");
		return this.right.prettyPrint(sb);
	}
	
}
