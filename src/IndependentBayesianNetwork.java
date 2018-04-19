public class IndependentBayesianNetwork extends ParameterLearningBN {

	static double defaultPrior = 0.0;
	static double[] posProb = new double[1];
	static int numberOfTrainingSamples = 0;
	static int[] count = new int[1];
	static double testLogLikelihood = 0.0;
	static int numberOfTestSamples = 0;
	Boolean firstSampleFlag = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		defaultPrior = 0.0;
		posProb = new double[1];
		numberOfTrainingSamples = 0;
		count = new int[1];
		testLogLikelihood = 0.0;
		numberOfTestSamples = 0;
		Boolean firstSampleFlag = true;
		String trainingFile = args[0];
		IndependentBayesianNetwork bn = new IndependentBayesianNetwork();
		bn.processData(trainingFile, true);
		learnParameters();
		for(double d : posProb) {
			System.out.println(d);
		}
//		System.out.println("--");
//		for(double d : posProb) {
//			System.out.println(1-d);
//		}
		String testFile = args[1];
		bn.processData(testFile, false);
		System.out.println(testFile+" : "+(testLogLikelihood/numberOfTestSamples));
	}	

	protected void test(String[] sample) {
		for(int i = 0; i < sample.length; i++) { 
			if(Integer.parseInt(sample[i]) == 1) {
				testLogLikelihood += (Math.log(posProb[i])/Math.log(2));
			} else {
				testLogLikelihood += (Math.log(1 - posProb[i])/Math.log(2));
			}
		}
		numberOfTestSamples++;
	}

	private static void learnParameters() {
		// Add One Laplace smoothing. 
		defaultPrior = (double) 1/(numberOfTrainingSamples+2);
		posProb = new double[count.length];
		for(int i =0; i< count.length; i++) {
			if(count[i] != 0)
				posProb[i] = (double) (count[i] +1)/(numberOfTrainingSamples+2);
			else
				posProb[i] = defaultPrior;
		}
	}

	protected void train(String[] sample) {
		if(firstSampleFlag) {
			count = new int[sample.length];
			firstSampleFlag = false;
		}
		for(int i = 0; i < sample.length; i++) { 
			count[i] += Integer.parseInt(sample[i]);
		}
		numberOfTrainingSamples++;
	}

}
