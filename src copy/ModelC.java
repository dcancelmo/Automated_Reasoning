import java.util.*;

/**
 * An implementation of the model interface
 * Created by joshuawolkoff on 10/12/17.
 */
public class ModelC implements Model{

    Hashtable<Symbol,Boolean> table;

    public ModelC() {
        this.table = new Hashtable<>();
    }

    public ModelC(Hashtable<Symbol,Boolean> table) {
        this.table = table;
    }

    /**
     * Assigns a value to a symbol in the model 
     */
    public void set(Symbol symbol, boolean b) {
        if (symbol != null)
            table.put(symbol,b);
        else
            System.out.print("ERROR: cannot insert null symbol");
    }

    /**
     * Prints all values in this model.
     */
    public void dump() {
        System.out.println(table.toString());
    }

    /**
     * Checks if this model satisfies a given sentence
     */
    public boolean satisfies(Sentence s) {
        return s.isSatisfiedBy(this);
    }

    /**
     * Checks if this model satisfies all sentences in the given KB.
     */
    public boolean satisfies(KB kb) {
        List<Sentence> sentenceList = kb.sentences;
        boolean isSatisfied = true;
        for (Sentence s : sentenceList) {
            isSatisfied &= s.isSatisfiedBy(this);
        }

        return isSatisfied;
    }

    /**
     * Gets a symbol's value.
     */
    public boolean get(Symbol s) {
        if (table.get(s) == null || s == null) { //If 's' or it's value pair is empty
            System.out.println(s + " NULL");
        }
        return table.get(s);
    }

    /**
     * Returns a copy of this model.
     */
    public ModelC c() {
        ModelC ret = new ModelC();

        Hashtable<Symbol,Boolean> temp = new Hashtable<>();
        ret.table = temp;

        Enumeration<Boolean> valueEnum = table.elements();
        Enumeration<Symbol> symbolEnum = table.keys();

        while (valueEnum.hasMoreElements()) {
            temp.put(symbolEnum.nextElement(), valueEnum.nextElement());
        }

        return  ret;
    }
}
