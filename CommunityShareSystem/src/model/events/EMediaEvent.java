package model.events;

import java.util.EventObject;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class EMediaEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 945504911542242329L;
	
	@Id
	private int id;
	
	@Column
	private int pageNum;
	@Column
	private boolean isBookmarked;
	@Column
	private int annotationNum;
	@Column
	private boolean hasAnnotations;
	@Column
	private String annotationText;
	
	public EMediaEvent (Object document) {
		super (document);
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setBookmarked(boolean isBookmarked) {
		this.isBookmarked = isBookmarked;
	}

	public boolean isBookmarked() {
		return isBookmarked;
	}

	public void setAnnotationNum(int annotationNum) {
		this.annotationNum = annotationNum;
	}

	public int getAnnotationNum() {
		return annotationNum;
	}

	public void setHasAnnotations(boolean hasAnnotations) {
		this.hasAnnotations = hasAnnotations;
	}

	public boolean isHasAnnotations() {
		return hasAnnotations;
	}

	public void setAnnotationText(String annotationText) {
		this.annotationText = annotationText;
	}

	public String getAnnotationText() {
		return annotationText;
	}
}
