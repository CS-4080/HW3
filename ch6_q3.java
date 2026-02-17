public class ch6_q3 {

    private Expr factor() {

        Expr expr;

        if(match(SLASH, STAR)) {
            Token badOp = previous();
            error(badOp, "Missing left-hand operand before '" + badOp.lexeme + "'.");
            Expr right = unary();
            expr = right;
        } else {
            expr = unary();
        }

        while(match(SLASH, STAR)) {
            Token operator = previous();
            Expr right = unary();
            expr = new Expr.Binary(expr, operator, right);
        }

        return expr;

    }

    private Expr term() {

        Expr expr;

        if(match(MINUS, PLUS)) {
            Token badOperand = previous();

            error(badOperand, "Missing left-hand operand before '" + badOperand.lexeme + "'.");

            Expr right = factor();

            expr = right;
        } else {
            expr = factor();
        }

        while(match(MINUS, PLUS)) {
            Token operator = previous();
            Expr right = factor();
            expr = new Expr.Binary(expr, operator, right);
        }

        return expr;

    }

}