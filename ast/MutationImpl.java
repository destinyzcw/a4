package ast;

import java.util.Random;

public class MutationImpl implements Mutation{
	
	protected int indexP;
	protected int leftOrRight;
	protected int indexC;
	protected int type;
	
	public MutationImpl(int type){
		Random rand = new Random();
		this.indexP = Math.abs(rand.nextInt());
		rand = new Random();
		this.indexC = Math.abs(rand.nextInt());
		rand = new Random();
		this.leftOrRight = Math.abs(rand.nextInt());
		this.type = type;
	}
	
	public Program getMutate(Program program){
		Node p = program.nodeAt(indexP%program.size());
		Node newChild;
		Node child;
		switch(type){
		case 0:
			if(!hasChild(p)) {return program;}
			child = p.getChild(leftOrRight);
			newChild = child.nodeAt(indexC%child.size()).copy();
			if(child.getFeature().equals(newChild.getFeature())) {p.setChild(leftOrRight, newChild);}
			break;
		case 1:
			if(!hasChild(p)) {return program;}
			child = p.getChild(leftOrRight);
			newChild = p.getChild(indexC);
			p.setChild(leftOrRight, newChild);
			p.setChild(indexC, child);
			break;
		case 2:
			
		}
		return program;
	}
	
	private boolean hasChild(Node p){
		if((p instanceof Sensor) || (p instanceof NumberValue)){
			return false;
		}
		return true;
	}
	
	public int[] get(){
		int[] result = new int[3];
		result[0]=this.indexP;
		result[1]=this.indexC;
		result[2]=this.type;
		return result;
	}
	
	@Override
	public boolean equals(Mutation m) {
		// TODO Auto-generated method stub
		int[] mutate = ((MutationImpl) m).get();
		return this.indexP==mutate[0] && this.indexC==mutate[1] && this.type==mutate[2];
	}

}
