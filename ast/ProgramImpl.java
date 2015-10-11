package ast;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A data structure representing a critter program.
 *
 */
public class ProgramImpl implements Program {
	protected LinkedList<Node> rules;
	private String feature = "program";
	
	public ProgramImpl() {
		rules = new LinkedList<Node>();
	}

	public ProgramImpl(LinkedList<Node> newRules) {
		rules = newRules;
	}
		
	public void addRules(Rule rule) {
		rules.add(rule);
	}
	
	public Node getRule(int index){
		index%=this.rules.size();
		return this.rules.get(index);
	}

	@Override
    public int size() {
        // TODO Auto-generated method stub
    	int size = 0;
    	for(Node p: rules){
    		size+=p.size();
    	}
        return size;
    }

    @Override
    public Node nodeAt(int index) {
        // TODO Auto-generated method stub
    	index%=this.size();
    	if(index==0){
    		return this;
    	}
    	index--;
        for(Node p: rules){
        	if(index>=p.size())
        		index-=p.size();
        	else
        		return p.nodeAt(index);
        }
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

	@Override
	public Node copy() {
		LinkedList<Node> newRules = new LinkedList<Node>();
		for (Iterator iter = rules.iterator(); iter.hasNext();) {
			Rule rule = (Rule) iter.next();
			newRules.add(rule.copy());
		}
		return new ProgramImpl(newRules);
	}

	@Override
	public Node getChild(int index) {
		int id = index % rules.size();
		return rules.get(id);
	}

	@Override
	public void setChild(int index, Node newChild) {
		int id = index % rules.size();
		rules.remove(id);
		rules.add(id, newChild);
	}

	@Override
	public String getFeature() {
		return feature;
	}

}
