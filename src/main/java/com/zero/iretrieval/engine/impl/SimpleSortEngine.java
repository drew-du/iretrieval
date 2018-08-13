package com.zero.iretrieval.engine.impl;

import com.zero.iretrieval.core.*;
import com.zero.iretrieval.core.LimitHeap.Comparator;
import com.zero.iretrieval.core.LimitHeap.Node;
import com.zero.iretrieval.engine.IRetrievalEngine;
import com.zero.iretrieval.feature.ExtractorFactory;
import com.zero.iretrieval.feature.IFeature;
import com.zero.iretrieval.feature.IFeatureExtractor;
import com.zero.iretrieval.match.IMatchCalculator;
import com.zero.iretrieval.match.MatchorFactory;

import java.util.ArrayList;
import java.util.List;

public class SimpleSortEngine implements IRetrievalEngine {

	private List<IFeature> features = new ArrayList<IFeature>();
	private int featureType = IRetrievalEngine.DEFAULT_FEATURE;
	private int extractorType = IRetrievalEngine.DEFAULT_EXTRACTOR;
	private int matchorType = IRetrievalEngine.DEFAULT_MATCHOR;
	private int topN = 3;
	private IFeatureExtractor extractor;
	private IMatchCalculator matchor;

	public void clean() {
		features.clear();
	}

	public void build() {
		L.log("build");
		for (IFeature feature : features)
			IMath.normalize(feature.value());
		extractor = ExtractorFactory.newExtractor(extractorType);
		matchor = MatchorFactory.newMatchor(matchorType);
	}

	public void loadImages(List<Image> images) {
		L.log("addImages");
		if (extractor == null || images == null)
			return;
		for (Image image : images)
			features.add(extractor.extract(image, featureType));
	}

	public void loadFromURI(List<String> uriList) {
		L.log("loadFromURI");
		if (extractor == null || uriList == null)
			return;
		for (String url : uriList) {
			IFeature feature = extractor.extract(new Image(url), featureType);
			features.add(feature);
			//Util.saveFeature(feature);
		}
		Util.saveFeatures(features);
	}
	public void loadFeature(List<IFeature> features) {
		L.log("loadFeature");
		this.features.addAll(features);
	}
	
	public List<String> retrieval(Image image) {
		L.log("retrieval " + image.getImageId());
		IFeature feature = extractor.extract(image, featureType);
		LimitHeap<Node<Float, IFeature>> heap = new LimitHeap<Node<Float, IFeature>>(topN,
				new MyComparator());
		for (int i = 0; i < features.size(); i++)
			heap.add(new Node<Float, IFeature>(matchor.score(feature, features.get(i)), features.get(i)));
		List<String> result = new ArrayList<String>(heap.size());
		for(int i = heap.size() - 1; i >= 0; i--)
			result.add(heap.getList().get(i).getValue().id());
		return result;
	}

	class MyComparator implements Comparator<Node<Float, IFeature>> {

		public int compare(Node<Float, IFeature> n1, Node<Float, IFeature> n2) {
			return n1.getKey() > n2.getKey() ? 1 : n1.getKey() < n2.getKey() ? -1 : 0;
		}
	}

	public void setParam(int name, int param) {
		switch (name) {
		case IRetrievalEngine.PARAM_FEATURE:
			featureType = param;
			break;
		case IRetrievalEngine.PARAM_EXTRACTOR:
			extractorType = param;
			break;
		case IRetrievalEngine.PARAM_TOP_N:
			topN = param;
			break;
		case IRetrievalEngine.PARAM_MATCHOR:
			matchorType = param;
			break;
		default:
		}
	}

	public int getParam(int name) {
		switch (name) {
		case IRetrievalEngine.PARAM_FEATURE:
			return featureType;
		case IRetrievalEngine.PARAM_EXTRACTOR:
			return extractorType;
		case IRetrievalEngine.PARAM_TOP_N:
			return topN;
		case IRetrievalEngine.PARAM_MATCHOR:
			return matchorType;
		default:
			return 0;
		}
	}
}
