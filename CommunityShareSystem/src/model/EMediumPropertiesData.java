package model;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

@Entity
public class EMediumPropertiesData implements Cloneable {

	@Id
	@GeneratedValue(strategy = AUTO)
	private int id;
	
	@SuppressWarnings("rawtypes")
	@MapKeyColumn
	private Map<EMediumAttribute,EMediumValue> attributes;
	
	@Enumerated(STRING)
	@Column(length = 35)
	private EMediumType type;
	
	
	@SuppressWarnings("rawtypes")
	public EMediumPropertiesData() {
		attributes = new HashMap<EMediumAttribute,EMediumValue>();
	}

	public Object getAttribute(EMediumAttribute attribute) {
		return attributes.get(attribute);
	}

	public void addAttribute(EMediumAttribute attribute, EMediumValue<?> value) {
		attributes.put(attribute, value);
	}
	
	public boolean isMediumType(EMediumType type) {
		return this.type == type; 
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public EMediumPropertiesData clone() {
		EMediumPropertiesData newData = null;
		try {
			newData = (EMediumPropertiesData) super.clone();
			newData.attributes = new HashMap<EMediumAttribute, EMediumValue>(attributes);
			if (attributes.containsKey(EMediumAttribute.TAGS)) {
				
				EMediumValue<?> cloned = new EMediumValue<Object>(new LinkedList<String>((Collection<? extends String>)attributes.get(EMediumAttribute.TAGS).getValue()));
				newData.attributes.put(EMediumAttribute.TAGS, cloned);
				
			}
		} catch (CloneNotSupportedException e) {
			// never happens
			e.printStackTrace();
		}
		return newData;
	}
}
