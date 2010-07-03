package org.socialgraph.util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.neo4j.graphdb.Node;

public class ObjectConverter {

	/**
	 * Converts an object to a map 
	 * @param obj
	 * @return
	 */
	public static Map<String,Object> convert(Object obj) {
		Map<String,Object> attrs = null;
		
		return attrs;
	}
	
	
	/**
	 * Populates an object from a node
	 * 
	 * @param obj
	 * @param node
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object populate(Object obj, Node node) throws IllegalAccessException, InvocationTargetException {
		Map<String,Object> properties = new HashMap<String, Object>();
		Iterable<String> keys = node.getPropertyKeys();
		for(String key: keys) {
			properties.put(key,node.getProperty(key));
		}
		properties.put("id",node.getId());
		BeanUtils.populate(obj, properties);
		return obj;
	}
	
}
