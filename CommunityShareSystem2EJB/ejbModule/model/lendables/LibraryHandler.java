package model.lendables;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LibraryHandler implements LibraryHandlerRemote, LibraryHandlerLocal{

	@EJB private Library library;
//	 TODO maybe we don't need this
	
//	public void adicionarLendable(String MEDIUM_TYPE,String TITLE,String PATH,String LANGUAGE,String AUTHOR,
//			String MIME_TYPE, List TAGS,String ALBUM,int WIDTH,int HEIGHT,int LICENSES){
//		
//	}
	
}
