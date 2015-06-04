package model.shelves;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ShelvesHandler implements ShelvesHandlerLocal,ShelvesHandlerRemote{

	@EJB private Shelves shelves;
	
	
	public void adicionarShelf(String name){
		shelves.addNormalShelf(name);
	}
	
	public void removerShelf( String name) throws Exception{
		try {
			shelves.removeShelf(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("erro a remover a Shelf: ",e);
		}
	}
	
	public List<String> ShelvesActuais() throws Exception{
		try {
			return shelves.getShelves();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro a mostrar as shelves: ", e);
		}
	}
	
	
}
