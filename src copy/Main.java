import java.util.LinkedList;
import java.util.List;

/**
 * Created by joshuawolkoff on 10/12/17.
 */

//TODO:
//Fix Nullpointer when dump.supermodel() is removed
//Remove extraneous imports
//Fix ArraySet import for CNF classes
//Add test cases for Part II.

public class Main {

    public static void main(String[] args) {

        KB kb;
        Sentence query;

        //Modus Ponens entailment
        kb = new KB();
        Symbol p = kb.intern("P");
        Symbol q = kb.intern("Q");
        kb.add(p);
        kb.add(new Implication(p, q));

        query = new Symbol("P");
        entailmentCheck(kb, query);


        //Unicorn entailment
        kb = new KB();
        Symbol mythical = kb.intern("mythical");
        Symbol immortal = kb.intern("immortal");
        Symbol mammal = kb.intern("mammal");
        Symbol horned = kb.intern("horned");
        Symbol magical = kb.intern("magical");
        kb.add(new Implication(mythical, immortal));
        kb.add(new Implication(new Negation(mythical), mammal));
        kb.add(new Implication(new Disjunction(immortal, mammal), horned));
        kb.add(new Implication(horned, magical));

        query = magical;
        entailmentCheck(kb, query);


        //Wumpus World entailment
        kb = new KB();
        Symbol p11 = kb.intern("P1,1");
        Symbol p12 = kb.intern("P1,2");
        Symbol p21 = kb.intern("P2,1");
        Symbol p22 = kb.intern("P2,2");
        Symbol p31 = kb.intern("P3,1");
        Symbol b11 = kb.intern("B1,1");
        Symbol b21 = kb.intern("B2,1");
        kb.add(new Negation(p11));
        kb.add(new Biconditional(b11, new Disjunction(p12, p21)));
        kb.add(new Biconditional(b21, new Disjunction(p12, new Disjunction(p22, p31))));
        kb.add(new Negation(b11));
        kb.add(b21);

        query = new Symbol("P1,2");
        entailmentCheck(kb, query);
    }

    public static void entailmentCheck(KB kb, Sentence query) {
        boolean result;
        EntailmentChecker checker = new EntailmentChecker();

        System.out.println("Symbol Table\n============");
        kb.symtab.print();
        System.out.println("*Done*");
        System.out.println("Knowledge Base\n==============");
        kb.print();
        System.out.println("*Done*");
        System.out.println("Entailment\n==========");
        result = checker.entails(kb, query);
        System.out.println("ENTAILS? "+result+".");
        System.out.println("*Done*");
        System.out.println();
    }

}

