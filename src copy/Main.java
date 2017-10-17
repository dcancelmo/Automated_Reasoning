import java.util.LinkedList;
import java.util.List;

/**
 * Created by joshuawolkoff on 10/12/17.
 */
public class Main {

    public static void main(String[] args) {
//        SymbolTable sTable = new SymbolTable();
//
//        sTable.intern("p");
//
//        Implication I = new Implication(sTable.intern("p"),sTable.intern("q"));
//
//        List<Sentence> list = new LinkedList<>();
//        list.add(I);
//        list.add(sTable.intern("p"));
//
//        sTable.print();
//        System.out.println("Done");
//        KB kb = new KB(list,sTable);
//        kb.print();
//        System.out.println("Done");

        KB kb = new KB();
        Symbol p = kb.intern("P");
        Symbol q = kb.intern("Q");
        kb.add(p);
        kb.add(new Implication(p, q));


        //kb.symtab.print();
        System.out.println("Done");
        boolean print = kb.TTEntails(kb,new Symbol("Q"));
        System.out.println(print);




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

        System.out.println("Done");
        print = kb.TTEntails(kb,new Symbol("P1,2"));
        System.out.println(print);



    }

}

