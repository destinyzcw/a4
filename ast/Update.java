package ast;

public class Update implements Expr{
	protected Expr left;
	protected Expr right;
	private String feature = "update";
	public Update(Expr left, Expr right) {
		this.left = left;
		this.right = right;
	}
	
	public void addUpdate(Update update){
		if(right==null){
			right=update;
		}
		else{
			((Update)right).addUpdate(update);
		}
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
		sb = this.left.prettyPrint(sb).append(" := ");
		return this.right.prettyPrint(sb);
	}

	@Override
	public Node copy() {
		return new Update((Expr) this.left.copy(), (Expr) this.right.copy());
	}

	@Override
	public Node getChild(int index) {
		if (index % 2 == 0)
			return left;
		else
			return right;
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
