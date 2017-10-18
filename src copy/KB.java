
import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.*;

/**
 * A KB is a set (actually a List) of Sentences and a SymbolTable
 * holding the PropositionalSymbols used in those sentences.
 */
public class KB implements Sentence {
    //Look for code in lecture 2.2 slide 118

    protected List<Sentence> sentences;
    protected SymbolTable symtab;

    public KB(List<Sentence> sentences, SymbolTable symtab) {
        this.sentences = sentences;
        this.symtab = symtab;
    }

    public KB() {
        this(new LinkedList<Sentence>(), new SymbolTable());
    }


    public void print() {
        Sentence temp;
        Object[] a = sentences.toArray();
        int length = a.length;

        for (int i = 0; i < length; i ++) {
            temp = sentences.get(i);
            temp.print();
        }

    }

    public List<Symbol> getSymbol() {
        System.out.println("You fucked up");
        return null;
    }

    /**
     * Return the Symbols interned in this KB's SymbolTable
     * as a Collection.
     */
    public Collection<Symbol> symbols() {
        return symtab.symbols();
    }

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

    public boolean TTEntails(KB kb, Sentence s) {

        Collection<Symbol> KBsymbols = kb.symtab.symbols();  //Fucking up

        Iterator<Symbol> iterator = KBsymbols.iterator();
        //List<Symbol> symbols = s.getSymbol();
        Iterator<Symbol> use;
        Symbol temp;
        List<Symbol> symbols = new ArrayList<>();
        while (iterator.hasNext()) {
            temp = iterator.next();
            symbols.add(temp);
            iterator.remove();
        }

        return TTCheckAll(kb, s, symbols, new ModelC());
    }

    public boolean TTCheckAll(KB kb, Sentence s, List<Symbol> symbols, ModelC model) {

        if (symbols.isEmpty()) {
            //model.dump();
            //System.out.println(kb.toString() + " here");
            if (model.satisfies(kb)) return model.satisfies(s);
            else {
                return true;
            }
        }
        //System.out.println(model.table.toString());
        Symbol p = symbols.remove(0);
        boolean ret;

        ModelC temp1 = model.c();
        //System.out.println(temp1);
        temp1.set(p,true);
        ModelC temp2 = model.c();
        temp2.set(p,false);


        return (TTCheckAll(kb,s,symbols,temp1) && TTCheckAll(kb,s,symbols,temp2));

    }


    //Go through what the method is doing
    //Figure out what "Follows from" means- the sentence holds within the model?
    //Figure out how to implement "Model" -- is it just one symbol or more?
    //How to check this using the things he wants us to
    //How do we know the model will have the same symbols as the sentence?




}
