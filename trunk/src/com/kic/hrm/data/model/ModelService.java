package com.kic.hrm.data.model;

import java.util.Arrays;
import java.util.Collection;

import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;

public class ModelService {

	public static Filter CompositeAndFilter(Collection<Filter> subFilters) {
		return CompositeFilterOperator.and(subFilters);
	}

	public static Filter CompositeAndFilter(Filter... subFilters) {
		return CompositeFilterOperator.and(Arrays.asList(subFilters));
	}

	public static Filter CompositeOrFilter(Collection<Filter> subFilters) {
		// Filter test = CompositeFilterOperator.and(subFilters)
		return CompositeFilterOperator.or(subFilters);
	}
	
}
