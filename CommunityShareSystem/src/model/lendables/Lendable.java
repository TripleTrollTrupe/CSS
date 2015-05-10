package model.lendables;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.swing.event.EventListenerList;

import model.EMedium;
import model.EMediumAttribute;
import model.EMediumPropertiesData;
import model.EMediumType;
import model.events.EMediumListener;

@Entity
@NamedQuery(name=Lendable.FIND_BY_ID, query="SELECT l FROM Lendable l WHERE l.id = :" + 
		Lendable.ID_NUMBER)
public class Lendable implements EMedium {
	
	public static final String FIND_BY_ID = "Lendable.id";
	public static final String ID_NUMBER = "id";

	@Id @GeneratedValue(strategy = IDENTITY)	private int id;
	
	@Column(nullable=false)	private EMediumType type;
	
	@Embedded @OneToOne(optional = false, cascade = CascadeType.ALL) private EMediumPropertiesData properties;
	
	private File file;
	
	@Column	private int licenses;
	
	private EventListenerList listeners;
	
	public Lendable(){
		
	}
		
	public Lendable(EMediumType type, EMediumPropertiesData properties) {
		this.type = type;
		this.properties = properties.clone();
		this.licenses = (Integer) properties.getAttribute(EMediumAttribute.LICENSES);
		this.file = new File((String) properties.getAttribute(EMediumAttribute.PATH));
		listeners = new EventListenerList();
	}
	
	public File getFile() {
		return file;
	}
	
	public String getTitle() {
		return (String) properties.getAttribute(EMediumAttribute.TITLE);
	}
	
	public String getAuthor() {
		return (String) properties.getAttribute(EMediumAttribute.AUTHOR);
	}

	public String getMimeType() {
		return (String) properties.getAttribute(EMediumAttribute.MIME_TYPE);
	}

	@SuppressWarnings("unchecked")
	public List<String> getTags() {
		return new LinkedList<String> ((List<String>) properties.getAttribute(EMediumAttribute.TAGS));
	}
	
	public void addEMediumListener(EMediumListener listener) {
        listeners.add(EMediumListener.class, listener);
    }
	
	public void removeEMediumListener(EMediumListener listener) {
        listeners.remove(EMediumListener.class, listener);
    }

	@Override
	public boolean canBookmarkPage() {
		return false;
	}

	@Override
	public boolean canAnnotate() {
		return false;
	}

	@Override
	public boolean canAnnotatePage() {
		return false;
	}

	@Override
	public EMediumType getType() {
		return type;
	}
	
	// @pre hasLicenses()
	public void rent() {
		licenses--;
		Object value = licenses;
		//EMediumValueObject lin = new EMediumValueObject(licenses); not sure
		properties.addAttribute(EMediumAttribute.LICENSES, value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + licenses;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lendable other = (Lendable) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public boolean hasLicensesAvailable() {
		return licenses > 0;
	}

	@Override
	public int compareTo(EMedium other) {
		return getTitle().compareTo(other.getTitle());
	}

	@Override
	public EMediumPropertiesData getEMediumProperties() {
		return properties.clone();
	}

	@Override
	public boolean isExpired() {
		return false;
	}

}