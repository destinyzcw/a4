package ast;

public class Command implements Expr{
	protected Expr left;
	protected Expr right;
	
	public Command(Expr left, Expr right) {
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
		if (right != null) {
			sb = this.left.prettyPrint(sb).append(" ");
			return this.right.prettyPrint(sb);
		}
		else {
			return this.left.prettyPrint(sb);
		}
	}

}
