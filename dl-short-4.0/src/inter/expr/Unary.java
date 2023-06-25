package inter.expr;

import inter.Emitter;
import lexer.Tag;
import lexer.Token;

public class Unary extends Expr{
    protected Expr expr1;
    
    public Unary(Expr e1) {
		super(new Token(Tag.NOT, "!"), Tag.BOOL);
		if(e1.op().lexeme().equals("verdadeiro") ||
			e1.op().lexeme().equals("falso"))
			error("O operador lógico ! "
					+ "não pode ser aplicado em "
					+ "literais booleanos");
		expr1 = e1;
		addChild(expr1);
	}

    @Override
	public Expr gen() {
		expr1.gen();
		int t = code.newLabel();
		int f = code.newLabel();
		int out = code.newLabel();
		Temp d = new Temp(type);
		this.jumping(t, f, "Unary");
		code.emitLabel(t);

		code.emitStore(d, Emitter.LIT_TRUE );
		code.emitBreak(out);
		code.emitLabel(f);
		code.emitStore(d, Emitter.LIT_FALSE);
		code.emitBreak(out);
		code.emitLabel(out);
		Temp d2 = new Temp(Tag.BOOL);
		code.emitLoad(d2, d);
		return d2;
	}

	@Override
	public void jumping(int t, int f, String type) {
		expr1.jumping(f,t,"Unary");
	}	
    
}
