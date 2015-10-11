package ast;

public class Command implements Expr{
	protected Expr left;
	protected Expr right;
	private String feature = "command";
	public Command(Expr left, Expr right) { // right may be null. take care
		this.left = left;
		this.right = right;
	}
	
	public void addUpdate(Update update) {
		((Update)left).addUpdate(update);
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
		if (right != null) {
			sb = this.left.prettyPrint(sb).append(" ");
			return this.right.prettyPrint(sb);
		}
		else {
			return this.left.prettyPrint(sb);
		}
	}

	@Override
	public Node copy() {
		if (right == null)
			return new Command((Expr) left.copy(), null);
		else
			return new Command((Expr) left.copy(), (Expr) right.copy());
	}

	@Override
	public Node getChild(int index) {
		if (right == null)
			return left;
		else {
			if (index % 2 == 0)
				return left;
			else 
				return right;
		}
	}

	@Override
	public void setChild(int leftOrRight, Node newChild) {
		if (right == null)
			this.left = (Expr) newChild;
		else {
		if (leftOrRight % 2 == 0)
			this.left = (Expr) newChild;
		else
			this.right = (Expr) newChild;
		}
		
	}

	@Override
	public String getFeature() {
		return feature;
	}
}
