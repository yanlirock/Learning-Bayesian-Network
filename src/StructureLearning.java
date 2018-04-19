import java.util.ArrayList;
import java.util.List;
import org.jgrapht.*;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.*;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;

public class StructureLearning extends ParameterLearningBN{

	static List<int[]> trainingData;
	static Boolean firstSampleFlag = true;
	static double[] posProb = new double[1];
	static int numberOfTrainingSamples = 0;
	static int[] count = new int[1];
	static int[][] values = {{0,0},{0,1},{1,1},{1,0}};
	static double p = 0.0;
	static double mutualInfo = 0.0;
	static double p_X = 0.0;
	static double p_Y = 0.0;
	static int[] jointCounts = {0,0,0,0};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		trainingData = null;
		firstSampleFlag = true;
		posProb = new double[1];
		numberOfTrainingSamples = 0;
		count = new int[1];
		String trainingFile = args[0];
		StructureLearning bn = new StructureLearning();
		bn.processData(trainingFile, true);
		createGraph();
//		String testFile = args[1];
//		bn.processData(testFile, false);
	}

	private static void createGraph() {
		// TODO Auto-generated method stub
		Graph<Integer, DefaultWeightedEdge> graph = new WeightedPseudograph<>(DefaultWeightedEdge.class);
		for(int i=0; i< count.length; i++) {
			graph.addVertex(i);
		}
		for(int i=0; i< count.length; i++) {
			for(int j=i+1; j< count.length; j++) {
				
					DefaultWeightedEdge e = new DefaultWeightedEdge();
					graph.addEdge(i, j, e);
					double mi = calculateMutualIndependence(i, j);
//					System.out.println(i+","+j+" --> "+mi);
					graph.setEdgeWeight(e, mi);				
//					System.out.println("Edge Weight: "+ graph.getEdgeWeight(graph.getEdge(j, i)));
			}
		}
		System.out.println(graph.toString());
		KruskalMinimumSpanningTree<Integer, DefaultWeightedEdge> mstAlgo = new KruskalMinimumSpanningTree<>(graph);
		SpanningTree<DefaultWeightedEdge> mst = mstAlgo.getSpanningTree();
		System.out.println(mst.toString());
	}

	private static double calculateMutualIndependence(int X, int Y) {
		// TODO Auto-generated method stub
		mutualInfo = 0.0;
		int[] jointCount = getCounts(X, Y);
		for (int i = 0; i< values.length ; i++) {
			 if(values[i][0]==1)
				 p_X = (double) count[X]/numberOfTrainingSamples;
			 else
				 p_X = 1- ((double)count[X]/numberOfTrainingSamples);
			 if(values[i][0]==1)
				 p_Y = (double) count[Y]/numberOfTrainingSamples;
			 else
				 p_Y = 1- ((double) count[Y]/numberOfTrainingSamples);
			 mutualInfo += (((double) jointCount[i]/numberOfTrainingSamples)*Math.log(( (double) jointCount[i]/numberOfTrainingSamples)/(p_X*p_Y)));
		}
		return mutualInfo;
	}
	
	
	private static int[] getCounts(int x, int y) {
		// TODO Auto-generated method stub
		jointCounts = new int[]{0,0,0,0};
		for(int[] sample : trainingData ) {
			if (sample[x]==values[0][0] && sample[y]==values[0][1])
				jointCounts[0]++;
			else if (sample[x]==values[1][0] && sample[y]==values[1][1])
				jointCounts[1]++;
			else if (sample[x]==values[2][0] && sample[y]==values[2][1])
				jointCounts[2]++;
			else if (sample[x]==values[3][0] && sample[y]==values[3][1])
				jointCounts[3]++;
		}
		return jointCounts;
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
