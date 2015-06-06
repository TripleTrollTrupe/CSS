package model.lendables;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebService;

import model.EMedium;

@Remote
@WebService
public interface LibraryHandlerRemote {
	public List<EMedium> getAllLendables();
	public EMedium getLendable(int id);
}
