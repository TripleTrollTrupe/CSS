package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class EMediumProperties implements Cloneable, Serializable {
	private static final long serialVersionUID = 4707296197139206943L;
	
	private Map<EMediumAttribute, Object> attributes;
	
	public EMediumProperties() {
		attributes = new HashMap<EMediumAttribute, Object>();
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(EMediumAttribute attribute) {
		return (T)attributes.get(attribute);
	}

	public <T> void addAttribute(EMediumAttribute attribute, T value) {
		attributes.put(attribute, value);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EMediumProperties clone() {
		EMediumProperties newData = null;
		try {
			newData = (EMediumProperties) super.clone();
			newData.attributes = new HashMap<>(attributes);
			if (attributes.containsKey(EMediumAttribute.TAGS)) {
				newData.attributes.put(EMediumAttribute.TAGS, 
						new LinkedList<String>((Collection<? extends String>) 
								attributes.get(EMediumAttribute.TAGS)));
			}
		} catch (CloneNotSupportedException e) {
			// never happens
			e.printStackTrace();
		}
		return newData;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getSimpleName());
		sb.append('[');
		sb.append(String.join(",", 
				attributes.entrySet().stream()
				.map(entry -> (entry.getKey().toString() + '=' + entry.getValue().toString()))
				.collect(Collectors.toList())));
		sb.append(']');
		return sb.toString();
		
	}
}
