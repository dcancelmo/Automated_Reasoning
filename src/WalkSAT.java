import java.lang.Math;
import java.util.*;

/**
 * 
 */
public class WalkSAT {

	int info;

	public WalkSAT(int info) {
		this.info = info;
	}

	public ModelC solve(KB kb, double probability, int maxFlips) {
		//Get symbols
		Collection<Symbol> symbols = kb.symbols();
		Set<Clause> clauses = new HashSet<>();
		
		//Convert all sentences in KB to a set of clauses in CNF
		CNFConverter converter = new CNFConverter();
		for (Sentence s : kb.sentences) {
			Set<Clause> converted = converter.convert(s);
			clauses.addAll(converted);
		}

		//Create a random model
		ModelC randomModel = randomModel(symbols);

		//Pass into loop for WalkSAT to perform search
		return computeWalkSAT(randomModel, clauses, probability, maxFlips);
	}

	private ModelC computeWalkSAT(ModelC model, Set<Clause> clauses, double probability, int maxFlips) {

		//Search model for SAT
		for (int i=0; i<maxFlips; i++) {
			boolean ret = true;
			for (Clause c : clauses)
				ret &= (c.isSatisfiedBy(model));
			if (ret) return model;
			
			Clause randomFalseClause = getRandomClause(getFalseClauses(clauses, model));
			if (Math.random() <= probability) {
				//Get a random literal and flip its value
				flipLiteral(getRandomLiteral(randomFalseClause.elements), model);
			} else {
				flipBest(randomFalseClause, clauses, model);
			}
		}
		return new ModelC();
	}

	private Set<Clause> getFalseClauses(Set<Clause> clauses, ModelC model) {
		Set<Clause> bigolSet = new HashSet<>();
		for (Clause c : clauses) {
			if (!c.isSatisfiedBy(model))
				bigolSet.add(c);
		}
		return bigolSet;
	}

	private Literal getRandomLiteral(ArrayList<Literal> list) {
		return list.get(new Random().nextInt(list.size()));
	}

	private Clause getRandomClause(Set<Clause> clauses) {

		return clauses.toArray(new Clause[clauses.size()])[new Random().nextInt(clauses.size())];
	}

	private ModelC randomModel(Collection<Symbol> symbolList) {
		ModelC m = new ModelC();
		int flip;
		for (Symbol s : symbolList) {
			m.set(s, ((Math.random() <= 0.5) ? true : false));
		}
		return m;
	}

	private void flipLiteral(Literal l, ModelC m) {
		Symbol s = l.getContent();
		m.set(s, !m.get(s));
	}

	private void flipBest(Clause clause, Set<Clause> clauses, ModelC model) {
		int max = Integer.MIN_VALUE;
		Literal deadassBest = new Literal(info);
		
		for (Literal s : clause) {
			flipLiteral(s, model);
			int satcount = 0;
			for (Clause c : clauses)
				satcount += (c.isSatisfiedBy(model)) ? 1 : 0;
			if (satcount > max) {
				max = satcount;
				deadassBest = s;
			}
			flipLiteral(s, model);
		}
		flipLiteral(deadassBest, model);
	}
}