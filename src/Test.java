
public class Test {

	static String[][] datasets = {
			{"accidents.test.data","accidents.ts.data", "accidents.valid.data"}, 
			{"baudio.test.data", "baudio.ts.data", "baudio.valid.data"}, 
			{"bnetflix.test.data","bnetflix.ts.data","bnetflix.valid.data"}, 
			{"dna.test.data","dna.ts.data","dna.valid.data"},  
			{"jester.test.data", "jester.ts.data","jester.valid.data"},  
			{"kdd.test.data", "kdd.ts.data","kdd.valid.data"},  
			{"msnbc.test.data", "msnbc.ts.data","msnbc.valid.data"},  
			{"nltcs.test.data", "nltcs.ts.data", "nltcs.valid.data"}, 
			{"plants.test.data", "plants.ts.data", "plants.valid.data"},  
			//			{"r52.test.data", "r52.ts.data", "r52.valid.data"}
	};

	public static void main(String[] args) {
		String[] arguments;
		for(String[] dataset : datasets) {
			long startTime = System.currentTimeMillis();
			arguments = new String[] {"datasets/"+dataset[1], "datasets/"+dataset[0]};
			System.out.println("Running dataset "+ dataset[1]+ " ...");
//			TreeBayesianNetwork bn = new TreeBayesianNetwork();
//			bn.run(arguments);
//			System.out.println(bn.network.toString());
//			System.out.println(bn.networkParameters);
			MixtureTreeBayesianNetwork bn2 = new MixtureTreeBayesianNetwork(2,100000);
			bn2.run(arguments);
			long endTime   = System.currentTimeMillis();
			System.out.println("time: "+ (endTime - startTime)/1000);
		}
	}

}
