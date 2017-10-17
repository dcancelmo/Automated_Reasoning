import java.util.ArrayList;
import java.util.List;

public class Conjunction extends BinaryCompoundSentence {

    public Conjunction(Sentence lhs, Sentence rhs) {
        super(BinaryConnective.AND, lhs, rhs);
    }

    /**
     * Return true if this Conjunction is satisfied by the given Model.
     * That is, if both its arguments are satisfied by the Model.
     */
    public boolean isSatisfiedBy(Model model) {
        return lhs.isSatisfiedBy(model) && rhs.isSatisfiedBy(model);
    }

    public void print() {
        System.out.println(lhs.toString() + " AND " + rhs.toString());
    }

    public List<Symbol> getSymbol() {
        List<Symbol> ret = new ArrayList<>();
        List<Symbol> temp = lhs.getSymbol();
        List<Symbol> temp2 = rhs.getSymbol();

        for (int i = 0; i < temp.size(); i++) {
            ret.add(temp.get(i));
        }
        for (int i = 0; i < temp2.size(); i ++) {
            ret.add(temp2.get(i));
        }
        return ret;
    }

}
