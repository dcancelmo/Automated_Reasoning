import java.util.ArrayList;
import java.util.List;

public class Disjunction extends BinaryCompoundSentence {

    public Disjunction(Sentence lhs, Sentence rhs) {
        super(BinaryConnective.OR, lhs, rhs);
    }

    /**
     * Return true if this Disjunction is satisfied by the given Model.
     * That is, if either of its arguments are satisfied by the Model.
     */
    public boolean isSatisfiedBy(Model model) {
        return lhs.isSatisfiedBy(model) || rhs.isSatisfiedBy(model);
    }

    public void print() {
        System.out.println(lhs.toString() + " OR " + rhs.toString());
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
