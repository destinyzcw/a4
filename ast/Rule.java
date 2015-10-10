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
        return 0;
    }

    @Override
    public Node nodeAt(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StringBuilder prettyPrint(StringBuilder sb) {
    	sb = left.prettyPrint(sb).append(" --> ");
    	return right.prettyPrint(sb);
    }
  
}
