public class ch6_q2 {

    private Expr conditional() {

        Expr expr = or();

        if (match(QUESTION)) {
            Token question = previous();

            Expr thenBranch = expression();

            consume(COLON, "Expect ':' after the branch.");

            Expr elseBranch = conditional();

            expr = new Expr.Conditional(expr, question, thenBranch, elseBranch);
        }

        return expr;
    }

}