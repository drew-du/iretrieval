package com.zero.iretrieval.match.impl;

import com.zero.iretrieval.core.Distance;
import com.zero.iretrieval.core.IMath;
import com.zero.iretrieval.core.L;
import com.zero.iretrieval.feature.IFeature;
import com.zero.iretrieval.match.IMatchCalculator;

public class CHI2DMatchor implements IMatchCalculator {

	public float score(IFeature f1, IFeature f2) {
		L.log("CHI2DMatchor score " + f1.id() + ":" + f2.id() + " " + f1.value().length + " " + f2.value().length);
		float[] p1 = f1.value(); 
		float[] p2 = f2.value();
		IMath.normalize(p1);
		IMath.normalize(p2);
		float d = Distance.person(p1, p2);
		return d;
	}
	
	

}
