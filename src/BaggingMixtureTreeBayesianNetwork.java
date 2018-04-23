import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class BaggingMixtureTreeBayesianNetwork extends ParameterLearningBN {
	
	int sizeOfLatentVariable;
	double[] latentParameters;
	private static final double epsilon = 0.001;
	int maxIterations;;
	List<int[]> trainingData;
	Boolean firstSampleFlag = true;
	int numberOfTrainingSamples = 0;
	final int[][] values = {{0,0},{0,1},{1,1},{1,0}};
	double testLogLikelihood = 0.0;
	int numberOfTestSamples = 0;
	Random rand;
	
	public BaggingMixtureTreeBayesianNetwork(int k) {
		// TODO Auto-generated constructor stub
		super();
		this.sizeOfLatentVariable = k;
		this.latentParameters = new double[k];
	}
	
	public void run(String[] args) {
		trainingData = null;
		this.firstSampleFlag = true;
		this.numberOfTrainingSamples = 0;
		this.testLogLikelihood = 0.0;
		this.numberOfTestSamples = 0;
		String trainingFile = args[0];
		processData(trainingFile, true);
	}

	@Override
	public void test(String[] sample) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void train(String[] sample) {
		if(this.firstSampleFlag) {
			trainingData = new ArrayList<int[]>();
			this.firstSampleFlag = false;	
			rand = new Random();
		}
	}

}
