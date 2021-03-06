package ast;


/**
 * A representation of a binary Boolean condition: 'and' or 'or'
 *
 */
public class BinaryCondition implements Condition {
	protected Condition l;
	protected String op;
	protected Condition r;
	protected boolean hasBrace = false;
	private String feature = "bool";
	
    /**
     * Create an AST representation of l op r.
     * @param l
     * @param op
     * @param r
     * @param hasParen
     */
    public BinaryCondition(Condition l, String op, Condition r, boolean hasBrace) {
    	this.l = l;
    	this.op = op;
    	this.r = r;
    	this.hasBrace = hasBrace;
    }
    
    /**
     * Create an AST representation of only single l
     * @param l
     */
    public BinaryCondition(Condition l, boolean hasBrace) {
    	this.l = l;
    	this.hasBrace = hasBrace;
    }
    

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return this.l.size()+this.r.size()+1;
    }

    @Override
    public Node nodeAt(int index) {
        // TODO Auto-generated method stub
    	index%=this.size();
    	if(index==0) return this;
    	else if(index<=this.l.size()){
    		return this.l.nodeAt(index-1);
    	}
    	else
    		return this.r.nodeAt(index-1-this.l.size());
    }
    
    @Override
    public StringBuilder prettyPrint(StringBuilder sb) {
    	if (hasBrace) {
    		if (r != null) {
    			sb = sb.append("{");
    			sb = this.l.prettyPrint(sb).append(" " + op + " ");
    			return this.r.prettyPrint(sb).append("}");
    		}
    		else {
    			sb = sb.append("{");
    			return this.l.prettyPrint(sb).append("}");
    		}
    	}
    	else {
    		if (r != null) {
    			sb = this.l.prettyPrint(sb).append(" " + op + " ");
    			return this.r.prettyPrint(sb);
    		}
    		else {
    			return this.l.prettyPrint(sb);
    		}
    	}
    }

    /**
     * An enumeration of all possible binary condition operators.
     */
    public enum Operator {
        OR, AND;
    }

	@Override
	public Node copy() {
		if (r == null)
			return new BinaryCondition((Condition) l.copy(), this.hasBrace);
		else
			return new BinaryCondition((Condition) l.copy(), new String(op), (Condition) r.copy(), this.hasBrace);
	}

	@Override
	public Node getChild(int index) {
		if (this.r == null)
			return this.l;
		else {
			if (index % 2 == 0) // even to return left
				return this.l;
			else 
				return this.r;
		}
	}

	@Override
	public void setChild(int leftOrRight, Node newChild) {
		if (this.r == null)
			this.l = (Condition) newChild;
		else {
			if (leftOrRight % 2 == 0) // even to return left
				this.l = (Condition) newChild;
			else 
				this.r = (Condition) newChild;
		}
	}

	@Override
	public String getFeature() {
		// TODO Auto-generated method stub
		return feature;
	}
}
