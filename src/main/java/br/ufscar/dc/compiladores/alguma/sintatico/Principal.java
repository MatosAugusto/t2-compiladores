package br.ufscar.dc.compiladores.alguma.sintatico;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Principal {
    public static void main(String args[]) {
        try(PrintWriter pw = new PrintWriter(new File(args[1]))) { 
            CharStream cs = CharStreams.fromFileName(args[0]);
            AlgumaLexer lexer = new AlgumaLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AlgumaParser parser = new AlgumaParser(tokens);

            MyCustomErrorListener mcel = new MyCustomErrorListener(pw);
            parser.removeErrorListeners(); // Remove os listeners padrão
            parser.addErrorListener(mcel);

            ParseTree tree = parser.programa(); // Inicia o parsing na regra 'programa'
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}