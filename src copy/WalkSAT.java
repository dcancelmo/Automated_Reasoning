import java.util.Set;

/**
 * 
 */
public class WalkSAT {

	public WalkSAT() {

	}

	public KB solve(KB kb, double probability, int maxFlips) {

		Random random = new Random();
		//Convert all sentences in the KB into CNF
		Set<Clause> clauses = new Set<Clause>();
		for (Sentence s : kb.sentences)
			clauses.add(CNFConverter.convert(s));

		//Turn all clauses back into sentences
		//Reinsert all sentences back into the KB

		//Randomly intialize supermodel

		for (int i=0; i<maxFlips; i++) {
			ModelC m = kb.supermodel[0]); //IDK???
			if (m.satisfies(kb)) return m;
			Sentence falseClause = getRandomClause(getFalseClauses(kb, m));
			if (random.random() <= p) {
				// Invert value of a random symbol in 'm'
			} else {
				//Flip whichever value in clauses of 'm' maximize # of satisfied clauses
			}
		}
		return null;
	}

	public Set<Sentence> getFalseClauses(KB kb, ModelC model) {
		Set<Sentence> falseClauses = new Set<Sentence>();
		for (Sentence s : kb.sentences) {
			if (!s.satisfies(model))
				falseClauses.add(s);
		}
		return falseClauses;
	}

	public Sentence getRandomClause(Set<Sentence> clauses) {
		Sentence clause;
		Random random = new Random();
		// Implement here
		return clause;
	}

}