import java.util.List;

public interface Sentence {

    /**
     * Return true if this Sentence is satisfied by the given Model.
     */
    public boolean isSatisfiedBy(Model model);
//A sentence satisifies a model if the values from the model make the sentence true

    public void print();

    public List<Symbol> getSymbol();

}
