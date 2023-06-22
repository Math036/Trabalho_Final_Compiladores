package inter.expr;

import lexer.Tag;
import lexer.Token;

public class Unary extends Expr{
    protected Expr expr1;
    
    public Unary(Expr e1) {
		super(new Token(Tag.NOT, "!"), Tag.BOOL);
		if ( !e1.type().isBool() )
			error("O operador lógico ! só "
					+ "pode ser aplicado em "
					+ "tipos booleanos");
		expr1 = e1;
		addChild(expr1);
	}

    @Override
	public Expr gen() {
		return null;
	}
    
}
