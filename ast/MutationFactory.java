package ast;

/**
 * A factory that produces the public static Mutation objects corresponding to each
 * mutation
 */
public class MutationFactory {
    public static Mutation getRemove() {
        // TODO Auto-generated method stub
        Mutation mutation = new MutationImpl(0);
        return mutation;
    }

    public static Mutation getSwap() {
        // TODO Auto-generated method stub
        Mutation mutation = new MutationImpl(1);
        return mutation;
    }

    public static Mutation getReplace() {
        // TODO Auto-generated method stub
        Mutation mutation = new MutationImpl(2);
        return mutation;
    }

    public static Mutation getTransform() {
        // TODO Auto-generated method stub
        Mutation mutation = new MutationImpl(3);
        return mutation;
    }

    public static Mutation getInsert() {
        // TODO Auto-generated method stub
        Mutation mutation = new MutationImpl(4);
        return mutation;
    }

    public static Mutation getDuplicate() {
        // TODO Auto-generated method stub
        Mutation mutation = new MutationImpl(5);
        return mutation;
    }
}
