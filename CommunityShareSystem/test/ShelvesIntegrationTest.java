import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;

import model.shelves.NormalShelf;
import model.shelves.Shelf;
import model.shelves.Shelves;
import model.shelves.SmartShelf;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


public class ShelvesIntegrationTest {
	
	@Mock private NormalShelf ns;
	@Mock private Shelves shelves;
	@Before
	public void setUp() throws Exception {
		ns = new NormalShelf("test");
		shelves = new Shelves(ns); // dunno how to initialize this
	}
	

	@Test
	public void testShelves() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNormalShelf() {
		NormalShelf test= new NormalShelf("NormalTest");
		NormalShelf test2 = new NormalShelf("NormalTest2");
		ArrayList <Shelf> miniS = new ArrayList<>();
		miniS.add(test);
		miniS.add(test2);
		shelves.addNormalShelf("NormalTest");
		shelves.addNormalShelf("NormalTest2");
		Iterator<Shelf> it = shelves.iterator();
		Iterator <Shelf> it2 = miniS.iterator();
		Shelf sh;
		Shelf sh2;
		while(it.hasNext()){
			sh=it.next();
			sh2=it2.next();
			assertEquals(sh.getName(),sh2.getName());
		}
	}

	@Test
	public void testIsTheRentalShelf() {
		assertEquals(shelves.isTheRentalShelf("test"),true);
		
	}

	@Test
	public void testAddSmartShelf() {
		NormalShelf test= new NormalShelf("NormalTest");
		NormalShelf test2 = new NormalShelf("NormalTest2");
		SmartShelf smartTest= new SmartShelf("smartTest", test, null);
		SmartShelf smartTest2 = new SmartShelf("smartTest2", test2, null);
		ArrayList <Shelf> miniS = new ArrayList<>();
		miniS.add(smartTest);
		miniS.add(smartTest2);
		shelves.addSmartShelf("smartTest", null);
		shelves.addSmartShelf("smartTest2", null);
		Iterator<Shelf> it = shelves.iterator();
		Iterator <Shelf> it2 = miniS.iterator();
		Shelf sh;
		Shelf sh2;
		while(it.hasNext()){
			sh=it.next();
			sh2=it2.next();
			assertEquals(sh.getName(),sh2.getName());
		}
		
	}

	@Test
	public void testIterator() {		
		assertTrue(shelves.iterator()instanceof Iterator);
	}

	@Test
	public void testRemoveShelf() {
		//fail("Not yet implemented");
		NormalShelf test2 = new NormalShelf("NormalTest2");
		shelves.addNormalShelf("NormalTest");
		shelves.addNormalShelf("NormalTest2");
		shelves.removeShelf("NormalTest");
		Iterator<Shelf> it = shelves.iterator();
		assertEquals(it.next().getName(),test2.getName());
	}

}
