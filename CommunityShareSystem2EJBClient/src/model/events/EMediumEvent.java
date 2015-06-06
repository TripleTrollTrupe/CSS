package model.events;

import java.util.EventObject;

import model.EMedium;

public class EMediumEvent extends EventObject {
	private static final long serialVersionUID = 945504911542242329L;
	
	private int pageNum;
	private boolean isBookmarked;
	private int annotationNum;
	private boolean hasAnnotations;
	private String annotationText;

	public EMediumEvent(EMedium rental, int pageNum, boolean isBookmarked) {
		super(rental);
		this.pageNum = pageNum;
		this.isBookmarked = isBookmarked;
	}

	public EMediumEvent(EMedium rental, int pageNum, 
			int annotationNum, boolean hasAnnotations) {
		super(rental);
		this.pageNum = pageNum;
		this.annotationNum = annotationNum;
		this.hasAnnotations = hasAnnotations;
	}

	public EMediumEvent(EMedium rental, int pageNum, int annotationNum,
			boolean hasAnnotations, String annotationText) {
		this(rental, pageNum, annotationNum, hasAnnotations);
		this.annotationText = annotationText;
	}

	public EMedium getEMedium() {
		return (EMedium) getSource();
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public boolean isBookmarked() {
		return isBookmarked;
	}

	public int getAnnotationNum() {
		return annotationNum;
	}

	public boolean hasAnnotations() {
		return hasAnnotations;
	}

	public String getAnnotationText() {
		return annotationText;
	}
}
