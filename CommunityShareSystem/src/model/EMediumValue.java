package model;

import javax.persistence.Embeddable;
import javax.persistence.Basic;
import javax.persistence.Lob;

@Embeddable
public class EMediumValue<WrapObject> {
	
	@Basic @Lob
	private EMediumValueObject o;
	
	/**
	 * Constructor needed by JPA.
	 */
	public EMediumValue(){
	}
	
	public EMediumValue(Object o){
		this.o = new EMediumValueObject(o);
	}
	
	public Object getValue(){
		return o.getEMediumObject();
	}
}
