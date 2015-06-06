package model.lendables;

import java.util.Iterator;

import javax.ejb.Local;

import model.EMedium;
import model.EMediumProperties;

@Local
public interface LibraryHandlerLocal extends LibraryHandlerRemote {
	public boolean addLendable(EMediumProperties properties);
	public boolean revokeLending(EMedium eMedium) throws Exception;
	public Iterator<EMedium> iterator();
	public EMedium getLastAddedLendable();
	public EMediumProperties readProperties(EMedium eMedium) throws Exception;
}
