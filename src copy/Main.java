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
        modusPonens();
        unicorns();
        wumpus();
        liarsTruth();
    }

    //Modus Ponens entailment
    public static void modusPonens() {
        System.out.println("________________Modus Ponens________________");
        KB kb = new KB();
        Symbol p = kb.intern("P");
        Symbol q = kb.intern("Q");
        kb.add(p);
        kb.add(new Implication(p, q));

        Sentence query = p;
        entailmentCheck(kb, query);
    }
    //Unicorn entailment
    public static void unicorns() {
        System.out.println("________________Unicorns________________");
        KB kb = new KB();
        Symbol mythical = kb.intern("mythical");
        Symbol immortal = kb.intern("immortal");
        Symbol mammal = kb.intern("mammal");
        Symbol horned = kb.intern("horned");
        Symbol magical = kb.intern("magical");
        kb.add(new Implication(mythical, immortal));
        kb.add(new Implication(new Negation(mythical), mammal));
        kb.add(new Implication(new Disjunction(immortal, mammal), horned));
        kb.add(new Implication(horned, magical));

        Sentence query = magical;
        entailmentCheck(kb, query);
    }
    //Wumpus World entailment
    public static void wumpus() {
        System.out.println("________________Wumpus World________________");
        KB kb = new KB();
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

        Sentence query = p12;
        entailmentCheck(kb, query);
    }
    //Liars and truth-tellers
    public static void liarsTruth() {
        System.out.println("________Liars and Truth-Tellers Part A________");
        KB kb = new KB();
        Symbol amy = kb.intern("amy");
        Symbol bob = kb.intern("bob");
        Symbol cal = kb.intern("cal");
        kb.add(new Biconditional(amy, new Conjunction(cal, amy)));
        kb.add(new Biconditional(bob, new Negation(cal)));
        kb.add(new Biconditional(cal, new Disjunction(bob, new Negation(amy))));
        liarChecker(kb);

        System.out.println("________Liars and Truth-Tellers Part B________");
        kb = new KB();
        amy = kb.intern("amy");
        bob = kb.intern("bob");
        cal = kb.intern("cal");
        kb.add(new Biconditional(amy, new Negation(cal)));
        kb.add(new Biconditional(bob, new Conjunction(amy, cal)));
        kb.add(new Biconditional(cal, bob));
        liarChecker(kb);
    }
    public static void liarChecker(KB kb) {
        Sentence query = new Symbol("amy");
        System.out.println("Determining Amy's truthfulness");
        entailmentCheck(kb, query);
        query = new Symbol("bob");
        System.out.println("Determining Bob's truthfulness");
        entailmentCheck(kb, query);
        query = new Symbol("cal");
        System.out.println("Determining Cal's truthfulness");
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

