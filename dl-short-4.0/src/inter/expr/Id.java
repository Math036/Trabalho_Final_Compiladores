package inter.expr;

import lexer.Tag;
import lexer.Token;

public class Id extends Expr {
	
	public Id(Token op, Tag type) { 
		super(op, type);
	}

	@Override
	public Expr gen() {
		Temp d = new Temp(type);
		code.emitLoad(d, this);
		return d;
	}
	
	@Override
	public void jumping(int t, int f, String type) {
		Expr e = this.gen();	
		if(type == "Unary"){
			Temp d = new Temp(type());
			code.emitOperation(d, e, null, Tag.NE);
		}
		code.emitBreak(e, t, f);
	}
	
	@Override
	public String toString() {
		return "%" + op.lexeme();
	}
}