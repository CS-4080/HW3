public class ch6_q3 {

    private Expr factor() {

        Expr expr;

        if(match(SLASH, STAR)) {
            Token badOperand = previous();
            error(badOperand, "Missing left-hand operand before '" + badOperand.lexeme + "'.");
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

    private Expr equality() {
        Expr expr;

        if(match(BANG_EQUAL, EQUAL_EQUAL)) {
            Token badOperand = previous();

            error(badOperand, "Missing left-hand operand before '" + badOperand.lexeme + "'.");

            Expr right = comparison();

            expr = right;
        } else {
            expr = comparison();
        }

        while(match(BANG_EQUAL, EQUAL_EQUAL)) {
            Token operator = previous();
            Expr right = comparison();
            expr = new Expr.Binary(expr, operator, right);
        }

        return expr;

    }

    private Expr comparison() {
        Expr expr;

        if(match(GREATER, GREATER_EQUAL, LESS, LESS_EQUAL)) {
            Token badOperand = previous();

            error(badOperand, "Missing left-hand operand before '" + badOperand.lexeme + "',");

            Expr right = term();

            expr = right;
        } else {
            expr = term();
        }

        while(match(GREATER, GREATER_EQUAL, LESS, LESS_EQUAL)) {
            Token operator = previous();
            Expr right = term();
            expr = new Expr.Binary(expr, operator, right);
        }

        return expr;
    }

}