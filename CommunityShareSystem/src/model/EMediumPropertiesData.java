package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MapKeyColumn;

@Embeddable
public class EMediumPropertiesData implements Cloneable {
	
	@MapKeyColumn	private Map<EMediumAttribute, Object> attributes;
	
	@Enumerated(EnumType.STRING)	@Column(length = 35) private EMediumType type;
	
	
	public EMediumPropertiesData() {
		attributes = new HashMap<EMediumAttribute,Object>();
	}

	public Object getAttribute(EMediumAttribute attribute) {
		return attributes.get(attribute);
	}

	public void addAttribute(EMediumAttribute attribute, Object value) {
		attributes.put(attribute, value);
	}
	
	public boolean isMediumType(EMediumType type) {
		return this.type == type; 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EMediumPropertiesData clone() {
		EMediumPropertiesData newData = null;
		try {
			newData = (EMediumPropertiesData) super.clone();
			newData.attributes = new HashMap<EMediumAttribute, Object>(attributes);
			if (attributes.containsKey(EMediumAttribute.TAGS)) {
				
				Object cloned = new LinkedList<String>((Collection<? extends String>)attributes.get(EMediumAttribute.TAGS));
				newData.attributes.put(EMediumAttribute.TAGS, cloned);
				
			}
		} catch (CloneNotSupportedException e) {
			// never happens
			e.printStackTrace();
		}
		return newData;
	}
}
