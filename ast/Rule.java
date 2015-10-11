package ast;

/**
 * A representation of a critter rule.
 */
public class Rule implements Node {
	private Condition left;
	private Command right;
	private String feature = "rule";
	
	public Rule(Condition left, Command right) {
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
    	sb = left.prettyPrint(sb).append(" --> ");
    	return right.prettyPrint(sb);
    }

	@Override
	public Node copy() {
		return new Rule((Condition) left.copy(), (Command) right.copy());
	}

	@Override
	public Node getChild(int index) {
		if (index % 2 == 0)
			return this.left;
		else
			return this.right;
	}

	@Override
	public void setChild(int leftOrRight, Node newChild) {
		if (leftOrRight % 2 == 0)
			this.left = (Condition) newChild;
		else
			this.right = (Command) newChild;	
		
	}

	@Override
	public String getFeature() {
		return feature;
	}
}
