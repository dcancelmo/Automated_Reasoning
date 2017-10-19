import java.util.ArrayList;

public class EntailmentChecker {

    public EntailmentChecker() {

    }

    /**
     * Returns true if the given KB entails a sentence.
     */
    public boolean entails(KB kb, Sentence s) {

        //Create set of symbols from kb and s
        ArrayList<Symbol> symbols = new ArrayList<Symbol>();
        for (Symbol thisSym : kb.symbols())
            symbols.add(thisSym);
        for (Symbol thisSym : s.getSymbol()) {
            if (!symbols.contains(thisSym))
                symbols.add(thisSym);
        }

        //Update model to include new symbols (if there are any)
        System.out.println("==Symbols Before==");
        kb.dumpSupermodel();
        kb.updateSupermodel(symbols);
        System.out.println("==Symbols After==");
        kb.dumpSupermodel();

        //Compute entailment
        boolean ret = true;
        System.out.println(kb.supermodel);
        for (ModelC m : kb.supermodel) {
            if (m.satisfies(kb)) {
                ret &= m.satisfies(s);
            }
        }
        return ret;
    }
}