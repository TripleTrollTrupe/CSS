package model;

import java.io.Serializable;

public class EMediumValueObject implements Serializable {
	
	private static final long serialVersionUID = -1432351615227615203L;
	private Object o;

	public EMediumValueObject(Object o){
		this.o = o;
	}

	public Object getEMediumObject(){
		return o;
	}
}
