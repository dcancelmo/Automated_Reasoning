import java.util.ArrayList;
import java.util.List;

public class Implication extends BinaryCompoundSentence {

    public Implication(Sentence lhs, Sentence rhs) {
        super(BinaryConnective.IMPLIES, lhs, rhs);
    }

    /**
     * Return true if this Implication is satisfied by the given Model.
     * That is, if either its lhs is not satisfied by the Model, or
     * its rhs is satisified by the Model.
     */
    public boolean isSatisfiedBy(Model model) {
        return !lhs.isSatisfiedBy(model) || rhs.isSatisfiedBy(model);
    }

    public void print() {
        System.out.println(lhs.toString() + " Implies " + rhs.toString());
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
