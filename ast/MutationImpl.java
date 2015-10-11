package ast;

import java.util.Random;

public class MutationImpl implements Mutation{
	
	protected int indexP;
	protected int indexC;
	protected int indexR;
	protected int type;
	
	public MutationImpl(int type){
		Random rand = new Random();
		this.indexP = Math.abs(rand.nextInt());
		this.indexR = Math.abs(rand.nextInt());
		this.indexC = Math.abs(rand.nextInt());
		this.type = type;
	}
	
	public Program getMutate(Program program){
		Node parent = program.nodeAt(indexP%program.size());
		Node child;
		Node replace;
		switch(type){
		case 0:
			if(!hasChild(parent)) {return program;}
			child = parent.getChild(indexC);
			replace = child.nodeAt(indexR%program.size()).copy();
			if(child.getFeature().equals(replace.getFeature())) {parent.setChild(indexC, child);}
			break;
		case 1:
			if(!hasChild(parent)) {return program;}
			child = parent.getChild(indexC);
			replace = parent.getChild(indexR);
			parent.setChild(indexC, replace);
			parent.setChild(indexR, child);
			break;
		case 2:
			if(!hasChild(parent)) {return program;}
			child = parent.getChild(indexC);
			replace = program.nodeAt(indexR%program.size()).copy();
			if(child.getFeature().equals(replace.getFeature())) parent.setChild(indexC, replace);
			break;
		case 3:
			if(!hasChild(parent) || (parent instanceof ProgramImpl)) {return program;}
			child = parent.getChild(indexC);
			replace = program.nodeAt(indexR%program.size());
			if(child.getFeature().equals(replace.getFeature())){
				if(hasChild(child)){
					if(child.getFeature().equals(replace.getFeature())) {
						replace = replace.copy();
						replace.setChild(0, child.getChild(0).copy());
						replace.setChild(1, child.getChild(1).copy());
						parent.setChild(indexC, replace);
					}
				}
				else{
					parent.setChild(indexC, replace);
				}
			}
			break;
		case 4:
			break;
		case 5:
			if(parent instanceof ProgramImpl){
				child = parent.getChild(indexC).copy();
				((ProgramImpl)parent).addRules((Rule)child);
			}
			else if(parent instanceof Command){
				child = parent.getChild(indexC).copy();
				if(child instanceof Update)
					((Command)parent).addUpdate((Update)child);
			}
		}
		return program;
	}
	
	private boolean hasChild(Node p){
		if((p instanceof Sensor) || (p instanceof NumberValue) || (p instanceof Action)){
			return false;
		}
		return true;
	}
	
	public int[] get(){
		int[] result = new int[3];
		result[0]=this.indexC;
		result[1]=this.indexR;
		result[2]=this.type;
		return result;
	}
	
	public void setIndex(int index){
		this.indexP=index;
	}
	
	@Override
	public boolean equals(Mutation m) {
		// TODO Auto-generated method stub
		int[] mutate = ((MutationImpl) m).get();
		return this.indexC==mutate[0] && this.indexR==mutate[1] && this.type==mutate[2];
	}

}
