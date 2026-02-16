public class ch6_q1 {

    private Expr comma() {

        Expr expr = assignment();

        while(match(COMMA)) {
            Token operator = previous();
            Expr right = assignment();
            expr = new Expr.Comma(expr, operator, right);
        }

        return expr;
    }

}