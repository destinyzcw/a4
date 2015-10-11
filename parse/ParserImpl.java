package parse;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import ast.Action;
import ast.BinaryCondition;
import ast.Command;
import ast.Condition;
import ast.Expr;
import ast.Expression;
import ast.MutationImpl;
import ast.NumberValue;
import ast.Program;
import ast.ProgramImpl;
import ast.Relation;
import ast.Rule;
import ast.Sensor;
import ast.Update;
import exceptions.SyntaxError;

class ParserImpl implements Parser {

	protected Tokenizer tokenizer;
	protected static ProgramImpl ret;
    @Override
    public Program parse(Reader r) {
    	tokenizer = new Tokenizer(r);

    	try {
			ret = ParserImpl.parseProgram(tokenizer);
		} catch (SyntaxError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
    }

    /** Parses a program from the stream of tokens provided by the Tokenizer,
     *  consuming tokens representing the program. All following methods with
     *  a name "parseX" have the same spec except that they parse syntactic form
     *  X.
     *  @return the created AST
     *  @throws SyntaxError if there the input tokens have invalid syntax
     */
    public static ProgramImpl parseProgram(Tokenizer t) throws SyntaxError {
    	ProgramImpl temp = new ProgramImpl();
    	while (!t.peek().getType().stringRep.equals("EOF")) {
    		temp.addRules(parseRule(t));
    	}
    	StringBuilder critter = new StringBuilder();
    	critter = temp.prettyPrint(critter);
    	System.out.println(critter);
    	return temp; 
    }

    /** parse the rule which is "Condition --> Command" */
    public static Rule parseRule(Tokenizer t) throws SyntaxError {
        // TODO
    	Condition left = parseCondition(t);
    	if (t.peek().toString().equals("-->"))
    		t.next();
    	Command right = parseCommand(t);
    	if (t.peek().getType().stringRep.equals(";"))
    		t.next();
    	return new Rule(left, right);
    }
    
    /** parse the command */
    public static Command parseCommand(Tokenizer t) throws SyntaxError {
    	if (t.hasNext() && !t.peek().isAction()) { // if not an action
    		Expr left = parseUpdate(t);
    		if (t.hasNext() && t.peek().isAction()) // command = update + action
    			return new Command(left, parseAction(t));
    		else if (!t.hasNext()) // command = update
    			return new Command(left, null);
    		else // command = update + update + ... + action
    			return new Command(left, parseCommand(t));
    	}
    	else { // command = action
    		return new Command(parseAction(t), null);
    	}
    }
    
    /** parse update which is similar with parse factor + parse expression: mem[expr] := expr */
    public static Expr parseUpdate(Tokenizer t) throws SyntaxError {
    	Expr left = parseFactor(t);
    	t.next();
    	Expr right = parseExpression(t);
    	return new Update(left, right);
    }
    
    /** parse action */
    public static Expr parseAction(Tokenizer t) throws SyntaxError {
    	String symbol = t.next().getType().stringRep;
    	if (!t.hasNext()) {
    		return new Action(symbol);
    	}
    	else if (t.hasNext() && !t.peek().getType().stringRep.equals("[")) {
    		return new Action(symbol);
    	}
    	else {
    		ParserImpl.consume(t, TokenType.LBRACKET);
    		Expr expr = parseExpression(t);
    		ParserImpl.consume(t, TokenType.RBRACKET);
    		return new Sensor(symbol, expr, false);
    	}
    }

    /** parse condition which is relation and/or relation and/or relation and/or .... */
    public static Condition parseCondition(Tokenizer t) throws SyntaxError {
    	boolean hasBrace = false;
    	if (t.peek().getType().stringRep.equals("{")) {
    		hasBrace = true; // if there is {, flag true so when print, this condition can have {}
    		t.next(); // consume it
    	}
    	Condition left = parseRelation(t);
    	while (t.hasNext()) {
    		String op = t.peek().getType().stringRep;
    		if (op.equals("and") || op.equals("or")) {
    			t.next();
    			left = new BinaryCondition(left, op, parseRelation(t), hasBrace); // to make each condition with {} if first has {
    		}
    		else
    			break;
    	}
    	while (t.peek().getType().stringRep.equals("}")) // consume if there is }
    		t.next();
    	return left;
    }

    /** parse relation  */
    public static Relation parseRelation(Tokenizer t) throws SyntaxError {
    	boolean hasBrace = false;
    	if (t.peek().getType().stringRep.equals("{")) {
    		hasBrace = true; // if there is {, flag true
    		t.next();
    	}
    	if (hasBrace) { // we assume that each relation has at most one pair of {}. so delete if there is more
    		while (t.peek().getType().stringRep.equals("{")) // if there is }, consume it
        		t.next();
    	}
    	Expr left = parseExpression(t); // similar with parseE()
    	String op = t.next().getType().stringRep;
    	Expr right = parseExpression(t);
    	while (t.peek().getType().stringRep.equals("}")) // if there is }, consume it
    		t.next();
    	return new Relation(left, op, right, hasBrace);
    }
    
    public static Expr parseExpression(Tokenizer t) throws SyntaxError {
    	boolean hasParen = false;
    	if (t.peek().getType().stringRep.equals("(")) {
    		hasParen = true; // if there is (, flag true
    		t.next();
    	}
    	Expr e = parseTerm(t); // similar with parseT()
    	String op = t.peek().getType().stringRep;
    	if (op.equals("+") || op.equals("-")) {
    		t.next();
    		e = new Expression(e, op, parseExpression(t), hasParen);
    	}

    	while (t.peek().getType().stringRep.equals(")")) // if there is ), consume it
    		t.next();
    	return e;
    }

    public static Expr parseTerm(Tokenizer t) throws SyntaxError {
    	boolean hasParen = false;
    	if (t.peek().getType().stringRep.equals("(")) {
    		hasParen = true; // if there is (, flag true
    		t.next();
    	}
    	Expr e = parseFactor(t); // similar with parseF
    	String op = t.peek().getType().stringRep;
    	if (op.equals("*") || op.equals("/") || op.equals("mod")) {
    		t.next();
    		e = new Expression(e, op, parseTerm(t), hasParen);
    	}
    	
    	while (t.peek().getType().stringRep.equals(")")) // if there is ), consume it
    		t.next();
    	return e;
    }

    public static Expr parseFactor(Tokenizer t) throws SyntaxError {
    	boolean hasParen = false;
    	if (t.peek().getType().stringRep.equals("(")) {
    		hasParen = true; // if there is (, flag true
    		t.next();
    	}
    	Token temp = t.peek();
    	if (temp.isNum()) {
    		int value = t.next().toNumToken().getValue(); // it is the only way to get the number value
    		while (t.peek().getType().stringRep.equals(")")) // if there is ), consume it
        		t.next();
    		return (Expr) new NumberValue(value, hasParen);
    	}
    	else if (temp.getType().stringRep.equals("-")) { // if it is negative. take "-" first and negative the value
    		t.next();
    		int value = t.next().toNumToken().getValue();
    		while (t.peek().getType().stringRep.equals(")")) // if there is ), consume it
        		t.next();
    		return (Expr) new NumberValue((-1) * value, hasParen);
    	}
    	else if (temp.isMemSugar()) { // I combine mem, sensor both into Sensor class because they are similar
    		String symbol = t.next().getType().stringRep;
    		ParserImpl.consume(t, TokenType.LBRACKET);
    		Expr expr = parseExpression(t);
    		ParserImpl.consume(t, TokenType.RBRACKET);
    		while (t.peek().getType().stringRep.equals(")")) // if there is ), consume it
        		t.next();
    		return new Sensor(symbol, expr, hasParen);
    	}
    	else if (temp.isSensor()) {
    		if (t.peek().isNum()) {
    			while (t.peek().getType().stringRep.equals(")")) // if there is ), consume it
    	    		t.next();
    			int value = t.next().toNumToken().getValue(); // it is the only way to get the number value
    			return new Sensor("smell", new NumberValue(value, false), hasParen);
    		}
    		
    		String symbol = t.next().getType().stringRep;
    		//ParserImpl.consume(t, TokenType.LBRACKET);
    		t.next();
    		Expr expr = parseExpression(t);
    		//ParserImpl.consume(t, TokenType.RBRACKET);
    		t.next();
    		while (t.peek().getType().stringRep.equals(")")) // if there is ), consume it
        		t.next();
    		return new Sensor(symbol, expr, hasParen);
    	}
    	else {
    		return parseExpression(t);
    	}
    }

    // TODO
    // add more as necessary...

    /**
     * Consumes a token of the expected type.
     * @throws SyntaxError if the wrong kind of token is encountered.
     */
    public static void consume(Tokenizer t, TokenType tt) throws SyntaxError {
        // TODO
    	if (!t.hasNext())
    		throw new SyntaxError();
    	Token temp = t.peek();
    	if (temp.getType().stringRep.equals(tt.stringRep))
    		t.next();
    	else
    		throw new SyntaxError();
    }
    
    public static void main(String[] args) {
    	ParserImpl test = new ParserImpl();
    	Reader r = null;
		try {
			r = new FileReader(args[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Program p = test.parse(r);
    	for(int i=0; i<100; i++){
        	MutationImpl m = new MutationImpl(0);
        	m.getMutate(p);
        StringBuilder sb = new StringBuilder();
        p.prettyPrint(sb);
//        System.out.println(sb);
    	}
    }
}
