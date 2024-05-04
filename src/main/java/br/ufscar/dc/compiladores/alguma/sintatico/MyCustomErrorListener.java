package br.ufscar.dc.compiladores.alguma.sintatico;

import java.io.PrintWriter;
import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener; // cuidado para importar a versão 4
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token; // Vamos também precisar de Token
// Outros imports vão ser necessários aqui. O NetBeans ou IntelliJ fazem isso automaticamente
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

public class MyCustomErrorListener implements ANTLRErrorListener {
    
    PrintWriter pw;
    public MyCustomErrorListener(PrintWriter pw) {
        this.pw = pw;    
    }
    @Override
    public void	reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
        // Não será necessário para o T2, pode deixar vazio
    }
    
    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
        // Não será necessário para o T2, pode deixar vazio
    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
        // Não será necessário para o T2, pode deixar vazio
    }

   
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        if (offendingSymbol instanceof Token) {
            Token token = (Token) offendingSymbol;
            pw.println("Linha " + line + ": erro sintatico proximo a " + token.getText());
        } else {
            pw.println("Linha " + line + ": erro sintatico");
        }
        pw.println("Fim da compilacao");
        pw.close();
    }
    

   
}