package model.lendables;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.swing.event.EventListenerList;

import model.EMedium;
import model.EMediumAttribute;
import model.EMediumPropertiesData;
import model.EMediumType;
import model.EMediumValue;
import model.events.EMediumListener;

@Entity
public class Lendable implements EMedium {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private EMediumType type;
	
	@OneToOne(optional = false, cascade = CascadeType.ALL) // nao sei se é CascadeType, mas parece coincidir
	private EMediumPropertiesData properties;
	
	@Column(nullable= false , unique= true) // o ficheiro é unico para o Lendable pois é o ficheiro a partilhar
	private File file;
	
	@Enumerated(EnumType.ORDINAL) 
	private int licenses;
	
	@OneToMany
	private EventListenerList listeners;
		
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
		EMediumValue value = new EMediumValue(licenses);
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