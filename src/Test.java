
public class Test {

	static String[][] datasets = {
			{"accidents.test.data","accidents.ts.data", "accidents.valid.data"}, 
			{"baudio.test.data", "baudio.ts.data", "baudio.valid.data"}, 
//			{"bnetflix.test.data","bnetflix.ts.data","bnetflix.valid.data"}, 
//			{"dna.test.data","dna.ts.data","dna.valid.data"},  
			{"jester.test.data", "jester.ts.data","jester.valid.data"},  
//			{"kdd.test.data", "kdd.ts.data","kdd.valid.data"},  
//			{"msnbc.test.data", "msnbc.ts.data","msnbc.valid.data"},  
//			{"nltcs.test.data", "nltcs.ts.data", "nltcs.valid.data"}, 
//			{"plants.test.data", "plants.ts.data", "plants.valid.data"},  
			//			{"r52.test.data", "r52.ts.data", "r52.valid.data"}
	};

	public static void main(String[] args) {
		String[] testArgs, validationArgs;
		int[] kValues = {20};
		int numberOfIterations = 10;
		double bestLogLikelihood = Double.NEGATIVE_INFINITY;
		for(String[] dataset : datasets) {
			MixtureTreeBayesianNetwork bn = null;
			MixtureTreeBayesianNetwork bestKBN = null;			
			validationArgs = new String[] {"datasets/"+dataset[1], "datasets/"+dataset[0]};
			System.out.println("Running dataset "+ dataset[1]+ " ...");
			double prevLogLikelihood = Double.NEGATIVE_INFINITY;
			for(int k : kValues) {
				double avgLogLikelihood = 0.0;
				for(int j=0; j<numberOfIterations;j++) {
					long startTime = System.currentTimeMillis();
					bn = new MixtureTreeBayesianNetwork(k, 100);
					bn.run(validationArgs);					
					avgLogLikelihood += (bn.testLogLikelihood/bn.numberOfTestSamples);
					long endTime = System.currentTimeMillis();
					System.out.println("time: "+ (endTime - startTime)/1000);
				}
				avgLogLikelihood = avgLogLikelihood/numberOfIterations;
				System.out.println(" Average logLikelihood for K="+ k+ " is "+ avgLogLikelihood);
				if(avgLogLikelihood > bestLogLikelihood) {
					bestLogLikelihood = avgLogLikelihood;
					bestKBN = bn;
				}
//				if(prevLogLikelihood > (avgLogLikelihood) ) {
//					System.out.println("Threshold found at: "+ k);
//					break;
//				}
				prevLogLikelihood = avgLogLikelihood;
			}
			long startTime = System.currentTimeMillis();
//			System.out.println(" Best BN is "+ bestKBN.sizeOfLatentVariable);
//			bestKBN.testLogLikelihood = 0.0;
//			bestKBN.numberOfTestSamples	= 0;
//			bestKBN.processData("datasets/"+dataset[0], false);
//			System.out.println("K value: "+ bestKBN.sizeOfLatentVariable +" | "+dataset[0]+" : "+(bestKBN.testLogLikelihood/bestKBN.numberOfTestSamples));
			long endTime   = System.currentTimeMillis();
			System.out.println("time: "+ (endTime - startTime)/1000);
		}
	}

}
