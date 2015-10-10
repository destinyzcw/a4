package ast;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A data structure representing a critter program.
 *
 */
public class ProgramImpl implements Program {
	protected LinkedList<Node> rules;
	
	public ProgramImpl() {
		rules = new LinkedList<Node>();
	}
	
	public void addRules(Rule rule) {
		rules.add(rule);
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
    public Program mutate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Program mutate(int index, Mutation m) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StringBuilder prettyPrint(StringBuilder sb) {
    	for (Iterator iter = rules.iterator(); iter.hasNext();) {
    		Rule rule = (Rule) iter.next();
    		if (iter.hasNext())
    			sb = rule.prettyPrint(sb).append("\n");
    		else
    			sb = rule.prettyPrint(sb);
    	}
    	return sb;
    }
    

}
