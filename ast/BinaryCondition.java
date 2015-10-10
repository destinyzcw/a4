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
        return 0;
    }

    @Override
    public Node nodeAt(int index) {
        // TODO Auto-generated method stub
        return null;
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
}
