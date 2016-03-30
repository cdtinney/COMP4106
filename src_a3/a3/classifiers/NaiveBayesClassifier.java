package a3.classifiers;

import java.util.List;
import java.util.Map;

import a3.model.Iris;

public class NaiveBayesClassifier extends Classifier {
	
	public void train(List<Iris> animals) {
		
		// Partition data into sets for each class
		Map<String, List<Iris>> classMap = getClassMap(animals);
		
		// Do pairwise classification on all the classes
		String[] classes = classMap.keySet().toArray(new String[classMap.size()]);
		for (int i=0; i<classes.length; i++) {
			for (int j=i+1; j<classes.length; j++) {
				
				List<Iris> classA = classMap.get(classes[i]);
				List<Iris> classB = classMap.get(classes[j]);
				
				Double[] meanA = calculateSampleMean(classA);
				Double[] meanB = calculateSampleMean(classB);
				
				logMean(classes[i], meanA);
				logMean(classes[j], meanB);
				
				double[][] covarianceA = calcDiagonalCovarianceMatrix(classA, meanA);
				double[][] covarianceB = calcDiagonalCovarianceMatrix(classB, meanB);
				
				logCovariance(classes[i], covarianceA);
				logCovariance(classes[j], covarianceB);
				
			}
		}
		
	}
	
	private double[][] calcDiagonalCovarianceMatrix(List<Iris> irises, Double[] sampleMean) {
		
		double[][] result = new double[Iris.NUM_FEATURES][Iris.NUM_FEATURES];
		for (int i=0; i<result.length; i++) {
			
			double sum = 0;
			for (Iris a : irises) {
				sum += Math.pow(a.getFeatures()[i] - sampleMean[i], 2);
			}
			
			result[i][i] = Math.pow(sum / irises.size(), 2);
			
		}
		
		return result;
		
	}

}
