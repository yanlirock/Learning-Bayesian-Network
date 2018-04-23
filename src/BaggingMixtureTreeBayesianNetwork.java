
public class BaggingMixtureTreeBayesianNetwork extends ParameterLearningBN {
	
	int sizeOfLatentVariable;
	double[] latentParameters;
	
	public BaggingMixtureTreeBayesianNetwork(int k) {
		// TODO Auto-generated constructor stub
		super();
		this.sizeOfLatentVariable = k;
		this.latentParameters = new double[k];
	}


	@Override
	public void test(String[] sample) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void train(String[] sample) {
		// TODO Auto-generated method stub
		
	}

}
