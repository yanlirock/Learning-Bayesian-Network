import java.util.ArrayList;
import java.util.List;

public class StructureLearning extends ParameterLearningBN{

	static List<int[]> trainingData;
	Boolean firstSampleFlag = true;
	static double[] posProb = new double[1];
	static int numberOfTrainingSamples = 0;
	static int[] count = new int[1];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String trainingFile = args[0];
		IndependentBayesianNetwork bn = new IndependentBayesianNetwork();
		bn.processData(trainingFile, true);
		createGraph();
		String testFile = args[1];
		bn.processData(testFile, false);
		System.out.println(testFile+" : "+(testLogLikelihood/numberOfTestSamples));
	}

	private static void createGraph() {
		// TODO Auto-generated method stub
		
	}

	private static void calculateMutualIndependence(int X, int Y) {
		// TODO Auto-generated method stub
		double mutualInfo = 0.0;
		//X=1, Y=1
		double p =  getProbability(X, Y, 0, 0);
		//X=1, Y=0
		//X=0, Y=1
		//X=0 Y=0
	}
	
	
	private static double getProbability(int x, int y, int i, int j) {
		// TODO Auto-generated method stub
		int count = 0;
		for(int[] sample : trainingData ) {
			
		}
		return 0;
	}

	@Override
	protected void test(String[] sample) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void train(String[] sample) {
		// TODO Auto-generated method stub
		if(firstSampleFlag) {
			trainingData = new ArrayList<int[]>();
			count = new int[sample.length];
			firstSampleFlag = false;
		}
		int[] s = new int[sample.length];
		for(int i = 0; i < sample.length; i++) {
			s[i] = Integer.parseInt(sample[i]);
			count[i] += s[i];
		}
		trainingData.add(s);
		numberOfTrainingSamples++;
	}
	
	
}
