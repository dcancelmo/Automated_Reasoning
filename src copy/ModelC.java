import java.util.*;

/**
 * Created by joshuawolkoff on 10/12/17.
 */
public class ModelC implements Model{

    Hashtable<Symbol,Boolean> table;

    public ModelC() {
        this.table = new Hashtable<>();
    }

    public void set(Symbol symbol, boolean b) {
        if (table.contains(symbol)) {
            if (table.get(symbol) != b) {
                table.remove(symbol);
                table.put(symbol,b);
            }
        } else {
            table.put(symbol,b);
            //System.out.println("Inserted symbol " + symbol + ", " + table.get(symbol));
            //System.out.println(table);
        }
    }

    public void dump() {
        table.toString();
    }

    public boolean satisfies(Sentence s) {
        return s.isSatisfiedBy(this);
    }


    public boolean satisfies(KB kb) {
        List<Sentence> sentenceList = kb.sentences;
        boolean isSatisfied = true;
        for (Sentence s : sentenceList) {
            isSatisfied &= s.isSatisfiedBy(this);
        }

        return isSatisfied;
    }

    public boolean get(Symbol s) { //No boolean with S
        if (table.get(s) == null) System.out.println(s + " == NULL");
        return table.get(s);
    }

    public ModelC c() {
        ModelC ret = new ModelC();
        Hashtable<Symbol,Boolean> temp = new Hashtable<>();
        Enumeration<Boolean> valueEnum = table.elements();
        Enumeration<Symbol> symbolEnum = table.keys();

        while (valueEnum.hasMoreElements()) {
            temp.put(symbolEnum.nextElement(),valueEnum.nextElement());
        }

        ret.table = temp;
        return  ret;
    }
}
