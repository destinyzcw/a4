package ast;

import java.util.Random;

public class MutationImpl implements Mutation{
	
	protected int indexP;
	protected int indexC;
	protected int type;
	
	public MutationImpl(int type){
		Random rand = new Random();
		this.indexP = Math.abs(rand.nextInt());
		rand = new Random();
		this.indexC = Math.abs(rand.nextInt());
		this.type = type;
	}
	
	public Program getMutate(Program program){
		Node p = program.nodeAt(indexP%program.size());
		Node c;
		switch(type){
		case 0:
			c = p.nodeAt(indexC%p.size());
			if(isValid(p, c, this.type)) {p=c;}
			break;
		case 1:
			c = program.nodeAt(indexC%program.size());
			Node tmp = p;
			
		}
		return program;
	}
	
	private boolean isValid(Node p, Node c, int type){
		return false;
	}
	
	public int[] get(){
		int[] result = new int[3];
		result[0]=this.indexP;
		result[1]=this.indexC;
		result[2]=this.type;
		return result;
	}
	
	
	public void runMutate(){
		
	}
	
	@Override
	public boolean equals(Mutation m) {
		// TODO Auto-generated method stub
		int[] mutate = ((MutationImpl) m).get();
		return this.indexP==mutate[0] && this.indexC==mutate[1] && this.type==mutate[2];
	}

}
