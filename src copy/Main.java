import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by joshuawolkoff on 10/12/17.
 */

//TODO:
//Fix Nullpointer when dump.supermodel() is removed
//Remove extraneous imports
//Fix ArraySet import for CNF classes
//Add test cases for Part II.

public class Main {
    private static int fullInfo = -1;
    private static int check = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print("Enter 0 to run all tests at once or enter 1 to run one at a time: ");
                check = sc.nextInt();
                if (check == 0 || check == 1) {
                    isValid = true;
                } else {
                    System.out.println("Invalid input!");
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input!");
            }
        }
        isValid = false;
        while (!isValid) {
            try {
                System.out.print("Enter 0 to get prints of symtables, kb, etc. (warning can get messy) or enter 1 to just see results: ");
                fullInfo = sc.nextInt();
                if (fullInfo == 0 || fullInfo == 1) {
                    isValid = true;
                } else {
                    System.out.println("Invalid input!");
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input!");
            }
        }
        part1Testing(sc);
    }

    public static void part1Testing(Scanner sc) {
        System.out.println("=========================Part 1: Basic Model Checking=========================");
        if (check == 0) {
            modusPonens();
            wumpus();
            unicorns();
            liarsTruth();
            liarsExtended();
        } else if (check == 1) {
            modusPonens();
            System.out.print("Enter any character for the next test (Wumpus World): ");
            sc.next();
            wumpus();
            System.out.print("Enter any character for the next test (Unicorns): ");
            sc.next();
            unicorns();
            System.out.print("Enter any character for the next test (Liars & Truth-Tellers): ");
            sc.next();
            liarsTruth();
            System.out.print("Enter any character for the next test (More Liars & Truth-Tellers): ");
            sc.next();
            liarsExtended();
        } else if (check == -1) {
            System.out.println("An error has occurred!");
        }
    }

    //Modus Ponens entailment - Sample 1
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
    //Unicorn entailment - Sample 3
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
    //Wumpus World entailment - Sample 2
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
    //Liars and truth-tellers - Sample 4a and 4b
    public static void liarsTruth() {
        System.out.println("________________Liars and Truth-Tellers________________");
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
    //Helper function for liarsTruth() to reuse code
    public static void liarChecker(KB kb) {
        Sentence query = new Symbol("amy");
        System.out.println("____Determining Amy's truthfulness____");
        entailmentCheck(kb, query);
        query = new Symbol("bob");
        System.out.println("____Determining Bob's truthfulness____");
        entailmentCheck(kb, query);
        query = new Symbol("cal");
        System.out.println("____Determining Cal's truthfulness____");
        entailmentCheck(kb, query);
    }
    //Extended Liars and truth-tellers - Sample 5
    public static void liarsExtended() {
        System.out.println("________________More Liars and Truth-Tellers________________");
        KB kb = new KB();
        Symbol amy = kb.intern("amy");
        Symbol bob = kb.intern("bob");
        Symbol cal = kb.intern("cal");
        Symbol dee = kb.intern("dee");
        Symbol eli = kb.intern("eli");
        Symbol fay = kb.intern("fay");
        Symbol gil = kb.intern("gil");
        Symbol hal = kb.intern("hal");
        Symbol ida = kb.intern("ida");
        Symbol jay = kb.intern("jay");
        Symbol kay = kb.intern("kay");
        Symbol lee = kb.intern("lee");

        kb.add(new Biconditional(amy, new Conjunction(hal, ida)));
        kb.add(new Biconditional(bob, new Conjunction(amy, lee)));
        kb.add(new Biconditional(cal, new Conjunction(bob, gil)));
        kb.add(new Biconditional(dee, new Conjunction(eli, lee)));
        kb.add(new Biconditional(eli, new Conjunction(cal, hal)));
        kb.add(new Biconditional(fay, new Conjunction(dee, ida)));
        kb.add(new Biconditional(gil, new Conjunction(new Negation(eli), new Negation(jay))));
        kb.add(new Biconditional(hal, new Conjunction(new Negation(fay), new Negation(kay))));
        kb.add(new Biconditional(ida, new Conjunction(new Negation(gil), new Negation(kay))));
        kb.add(new Biconditional(jay, new Conjunction(new Negation(amy), new Negation(cal))));
        kb.add(new Biconditional(kay, new Conjunction(new Negation(dee), new Negation(fay))));
        kb.add(new Biconditional(lee, new Conjunction(new Negation(bob), new Negation(jay))));

        for (Symbol person : kb.symbols()) {
            System.out.println("____Determining " + person.name + "'s truthfulness____");
            Sentence query = person;
            entailmentCheck(kb, query);
        }

    }

    public static void entailmentCheck(KB kb, Sentence query) {
        boolean result;
        EntailmentChecker checker = new EntailmentChecker();

        if (fullInfo == 0) {
            System.out.println("Symbol Table\n============");
            kb.symtab.print();
            System.out.println("\nKnowledge Base\n==============");
            kb.print();
            System.out.println("\nEntailment\n==========");
            result = checker.entails(kb, query, fullInfo);
            System.out.println("\nWith a query of '" + query + "' ENTAILS? "+result+".");
            System.out.println("*Done*\n");
        } else {
            result = checker.entails(kb, query, fullInfo);
            System.out.println("\nWith a query of '" + query + "' ENTAILS? "+result+".");
            System.out.println("*Done*\n");
        }

    }

}

