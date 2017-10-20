import java.util.*;
import java.lang.Math;

/**
 * A KB is a set (actually a List) of Sentences and a SymbolTable
 * holding the PropositionalSymbols used in those sentences.
 */
public class KB implements Sentence {

    protected List<Sentence> sentences;
    protected SymbolTable symtab;
    protected ModelC[] supermodel;

    public KB(List<Sentence> sentences, SymbolTable symtab) {
        this.sentences = sentences;
        this.symtab = symtab;
    }

    public KB() {
        this(new LinkedList<Sentence>(), new SymbolTable());
    }

    /**
     * Prints every Sentence in the KB.
     */
    public void print() {
        for (Sentence thisSentence : this.sentences)
            thisSentence.print();
    }

    /**
     * This should never be called. Here to satisfy Sentence implementation.
     */
    public List<Symbol> getSymbol() {
        System.out.println("You messed up");
        return null;
    }

    /**
     * Return the Symbols interned in this KB's SymbolTable
     * as a Collection.
     */
    public Collection<Symbol> symbols() {
        return symtab.symbols();
    }

    /**
     * Return true if a given Model satisfies this KB.
     */
    public boolean isSatisfiedBy(Model model) {
        return model.satisfies(this);
    }

    /**
     * Return this KB's Sentences as a Collection.
     */
    public Collection<Sentence> sentences() {
        return sentences;
    }

    /**
     * Intern the given name in this KB's SymbolTable and return
     * the corresponding Symbol.
     */
    public Symbol intern(String name) {
        return symtab.intern(name);
    }

    /**
     * Add the given Sentence to this KB.
     */
    public void add(Sentence s) {
        sentences.add(s);
    }

    /**
     * Print the contents of this KB to System.out.
     */
    public void dump() {
        for (Sentence s : sentences()) {
            System.out.println(s);
        }
    }

    /**
     * Prints the contents of every Model in this KB to System.out.
     */
    public void dumpSupermodel() {
        if (supermodel == null) {
            System.out.println("(Empty)");
            return;
        }
        for (ModelC m : supermodel)
            m.dump();
    }

    /**
     * Updates the supermodel of every Model in this KB to include new Symbols.
     */
    public void updateSupermodel(List<Symbol> symbolList) {
        supermodel = new ModelC[(int) Math.pow(2, symbolList.size())];
        for (int i = 0; i < supermodel.length; i++) {
            String binaryString = String.format("%" + symbolList.size() + "s", Integer.toBinaryString(i)).replace(" ", "0");
            supermodel[i] = new ModelC();
            for (int p = 0; p < binaryString.length(); p++) {
                supermodel[i].set(symbolList.get(p), '1' == binaryString.charAt(p));
            }
        }
    }
}