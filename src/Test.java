
public class Test {

	static String[][] datasets = {
//			{"accidents.test.data","accidents.ts.data", "accidents.valid.data"}, 
//			{"baudio.test.data", "baudio.ts.data", "baudio.valid.data"}, 
//			{"bnetflix.test.data","bnetflix.ts.data","bnetflix.valid.data"}, 
//			{"dna.test.data","dna.ts.data","dna.valid.data"},  
//			{"jester.test.data", "jester.ts.data","jester.valid.data"},  
//			{"kdd.test.data", "kdd.ts.data","kdd.valid.data"},  
//			{"msnbc.test.data", "msnbc.ts.data","msnbc.valid.data"},  
//			{"nltcs.test.data", "nltcs.ts.data", "nltcs.valid.data"}, 
			{"plants.test.data", "plants.ts.data", "plants.valid.data"},  
			//			{"r52.test.data", "r52.ts.data", "r52.valid.data"}
	};

	public static void main(String[] args) {
		String[] testArgs, validationArgs;
		int[] kValues = {2, 3, 4, 5};
		MixtureTreeBayesianNetwork bn;
		double bestLogLikelihood = Double.NEGATIVE_INFINITY;
		MixtureTreeBayesianNetwork bestKBN = null;
		for(String[] dataset : datasets) {
			
			validationArgs = new String[] {"datasets/"+dataset[1], "datasets/"+dataset[2]};
			System.out.println("Running dataset "+ dataset[1]+ " ...");
			
			for(int k : kValues) {
				long startTime = System.currentTimeMillis();
				bn = new MixtureTreeBayesianNetwork(k,100);
				bn.run(validationArgs);
				
				if(bn.testLogLikelihood > bestLogLikelihood) {
					bestLogLikelihood = bn.testLogLikelihood;
					bestKBN = bn;
				}
				long endTime = System.currentTimeMillis();
				System.out.println("time: "+ (endTime - startTime)/1000);
			}
			long startTime = System.currentTimeMillis();
			System.out.println(" Best BN is "+ bestKBN.sizeOfLatentVariable);
			bestKBN.testLogLikelihood = 0.0;
			bestKBN.numberOfTestSamples	= 0;
			bestKBN.processData("datasets/"+dataset[0], false);
			System.out.println("K value: "+ bestKBN.sizeOfLatentVariable +" | "+dataset[0]+" : "+(bestKBN.testLogLikelihood/bestKBN.numberOfTestSamples));
			long endTime   = System.currentTimeMillis();
			System.out.println("time: "+ (endTime - startTime)/1000);
		}
	}

}
