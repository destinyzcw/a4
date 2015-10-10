package ast;

/**
 * A representation of a critter rule.
 */
public class Rule implements Node {
	private Condition left;
	private Command right;
	
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
  
}
