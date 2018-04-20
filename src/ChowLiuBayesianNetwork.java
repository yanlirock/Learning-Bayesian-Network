import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;


public class ChowLiuBayesianNetwork extends ParameterLearningBN{

	static List<int[]> trainingData;
	static Boolean firstSampleFlag = true;
	static double[] posProb = new double[1];
	static int numberOfTrainingSamples = 0;
	static int[] count = new int[1];
	static final int[][] values = {{0,0},{0,1},{1,1},{1,0}};
	static DirectedGraph<Integer, DefaultEdge> network ;
	static List<Object> networkParameters ;
	static double testLogLikelihood = 0.0;
	static int numberOfTestSamples = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		trainingData = null;
		firstSampleFlag = true;
		posProb = new double[1];
		numberOfTrainingSamples = 0;
		count = new int[1];
		testLogLikelihood = 0.0;
		networkParameters = null;
		network = null;
		numberOfTestSamples = 0;
		String trainingFile = args[0];
		ChowLiuBayesianNetwork bn = new ChowLiuBayesianNetwork();
		bn.processData(trainingFile, true);
		network = learnBNStructure();
//		System.out.println(network.toString());
		networkParameters = learnBNParameters(network);
//		System.out.println(networkParameters);
		String testFile = args[1];
		bn.processData(testFile, false);
		System.out.println(testFile+" : "+(testLogLikelihood/numberOfTestSamples));
	}

	private static List<Object> learnBNParameters(DirectedGraph<Integer, DefaultEdge> network) {
		Integer s, v =0;
		Set<DefaultEdge> edges;
		List<Object> parameters = new ArrayList<>();
		for(v = 0; v < count.length; v++) {
			// Any variable will have exactly one parent or zero
			edges = network.incomingEdgesOf(v);
			if(!edges.isEmpty()) {
				s = network.getEdgeSource(edges.iterator().next());
				parameters.add(learnCPT(s,v));
			}
			else
				parameters.add(learnCPT(v));
		}
		return parameters;
	}

	private static Double learnCPT(Integer v) {
		// TODO Auto-generated method stub
		return (double) (count[v]+1)/(numberOfTrainingSamples+2);
	}

	private static Double[] learnCPT(Integer s, Integer v) {
		// TODO Auto-generated method stub
		int[] jointcount = getCounts(s,v);
		Double[] prob = new Double[2];
		prob[0] = (double)(jointcount[1]+1)/(jointcount[1]+ jointcount[0]+2);
		prob[1] = (double) (jointcount[2]+1)/(jointcount[2]+ jointcount[3]+2);
		if(prob[0] < 0.0 || prob[1] <0.0) {
			System.out.println("Error prob is negative");
			System.exit(0);
		}
			
		return prob;
	}

	private static DirectedGraph<Integer, DefaultEdge> learnBNStructure() {
		WeightedPseudograph<Integer, DefaultWeightedEdge> weightedGraph = new WeightedPseudograph<>(DefaultWeightedEdge.class);
		DirectedGraph<Integer, DefaultEdge> directedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
		for(int i=0; i< count.length; i++) {
			weightedGraph.addVertex(i);
			directedGraph.addVertex(i);
		}
		for(int i=0; i< count.length; i++) {
			for(int j=i+1; j< count.length; j++) {
				
					DefaultWeightedEdge e = new DefaultWeightedEdge();
					weightedGraph.addEdge(i, j, e);
					double mi = -1 * calculateMutualIndependence(i, j);
					weightedGraph.setEdgeWeight(e, mi);				
			}
		}
		KruskalMinimumSpanningTree<Integer, DefaultWeightedEdge> mstAlgo = new KruskalMinimumSpanningTree<>(weightedGraph);
		SpanningTree<DefaultWeightedEdge> mst = mstAlgo.getSpanningTree();
		Set<DefaultWeightedEdge> edges = mst.getEdges();
		int[] edgeCount = new int[count.length];
		for(DefaultWeightedEdge edge: edges) {
			edgeCount[weightedGraph.getEdgeSource(edge)] ++;
			edgeCount[weightedGraph.getEdgeTarget(edge)] ++;
		}
		Set<DefaultWeightedEdge> removeEdges = new LinkedHashSet<DefaultWeightedEdge>(weightedGraph.edgeSet());
		removeEdges.removeAll(edges);
		weightedGraph.removeAllEdges(removeEdges);
		GraphIterator<Integer, DefaultWeightedEdge> iterator = 
	                new DepthFirstIterator<Integer, DefaultWeightedEdge>(weightedGraph, 6);		
		Integer v;
		Integer t;
		while (iterator.hasNext()) {
			v = iterator.next();
			edges = weightedGraph.edgesOf(v);
			for(DefaultWeightedEdge edge: edges) {
				t = weightedGraph.getEdgeSource(edge);
				if(t == v)
					t = weightedGraph.getEdgeTarget(edge);
				if(!directedGraph.containsEdge(t, v))
					directedGraph.addEdge(v,t);
			}
        }
        return directedGraph;
	}

	private static double calculateMutualIndependence(int X, int Y) {
		double mutualInfo = 0.0, c_X, c_Y, N, mi;
		int[] jointCount = getCounts(X, Y);
		for (int i = 0; i< values.length ; i++) {
			 if(values[i][0]==1) // Add one in Mutual Info
				 c_X = count[X] + 2;
			 else
				 c_X = numberOfTrainingSamples - count[X] + 2;
			 if(values[i][1]==1)
				 c_Y = count[Y] + 2;
			 else
				 c_Y = numberOfTrainingSamples - count[Y] + 2;
			 if (jointCount[i] ==0 )
				 continue;
			 N = (double) numberOfTrainingSamples +4;
			 mi = jointCount[i]*Math.log((double) (jointCount[i] * N)/(c_X*c_Y))/N;
			 mutualInfo += mi ;
		}
		if(mutualInfo < 0 || Double.isNaN(mutualInfo)) {
			System.out.println(" Error Mutual info negative edge: or NaN"+ X +" - "+ Y);
			System.exit(0);
		}
		return mutualInfo;
	}
	
	
	private static int[] getCounts(int x, int y) {
		int[] jointCounts = new int[]{1,1,1,1};// Add One in counts
		for(int[] sample : trainingData ) {
			for(int i = 0; i < values.length; i++) {
				if(sample[x]==values[i][0] && sample[y]==values[i][1])
					jointCounts[i]++;
			}
		}
		return jointCounts;
	}

	@Override
	protected void test(String[] sample) {
		// TODO Auto-generated method stub
		Integer[] data = new Integer[sample.length];
		for(int i = 0; i < sample.length; i++) { 
			data[i] = Integer.parseInt(sample[i]);
		}
		double posProb;
		Set<DefaultEdge> edges;
		int s;
		
		for(int i = 0; i < sample.length; i++) {
			edges = network.incomingEdgesOf(i);
			if(!edges.isEmpty()) {
				s = network.getEdgeSource(edges.iterator().next());
				posProb = ((Double[]) networkParameters.get(i))[data[s]];
			}
			else
				posProb = (Double) networkParameters.get(i);
			if(data[i]==1)
				testLogLikelihood += (Math.log(posProb)/Math.log(2));
			else
				testLogLikelihood += (Math.log(1-posProb)/Math.log(2));
		}
		numberOfTestSamples++;
	}

	@Override
	protected void train(String[] sample) {
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
