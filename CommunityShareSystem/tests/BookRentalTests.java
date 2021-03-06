import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import model.EMediumAttribute;
import model.EMediumPropertiesData;
import model.EMediumType;
import model.EMediumValue;
import model.lendables.Lendable;
import model.rentals.BookRental;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BookRentalTests {
	//TODO no idea how to test constructor, help plz, also not sure if tests are enough might need to be more intensive IDK
	private BookRental bookrental;
	@Before
	public void setUp() throws Exception {
		EMediumPropertiesData prop = new EMediumPropertiesData();
		prop.addAttribute(EMediumAttribute.PATH, (EMediumValue<?>)(Object)"lolinsertfilepathplz"); //TODO insert a proper file path
		prop.addAttribute(EMediumAttribute.LICENSES, (EMediumValue<?>)(Object)1);
		Lendable book= new Lendable(EMediumType.DOCUMENT, prop); 				 //TODO see how to initialize
		bookrental = new BookRental(book); // dunno how to initialize this
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetAnnotations() {
		ArrayList <String> strings = new ArrayList<>();
		strings.add("test1");
		strings.add("test2");
		for(int i=0;i<strings.size();i++){
			bookrental.addAnnotation(1, strings.get(i));
		}
		Iterator<String> it1 =strings.iterator();
		Iterator<String> it2 =bookrental.getAnnotations(1).iterator();
		String st1;
		String st2;
		while(it1.hasNext()){
			st1= it1.next();
			st2= it2.next();
			assertEquals(st1,st2);
		}
	}

	@Test
	public final void testGetAnnotatins() {
		ArrayList <String> strings = new ArrayList<>();
		strings.add("test1");
		strings.add("test2");
		strings.add("test3");
		bookrental.addAnnotation(1,strings.get(1));
		bookrental.addAnnotation(1, strings.get(2));
		bookrental.addAnnotation(2,strings.get(3));
		assertEquals(bookrental.getAnnotatins(),strings);
		
	}

	@Test
	public final void testHasAnnotations() {
		//TODO multiple sceneries (multiple pages)
		bookrental.addAnnotation(1, "test");
		assertTrue(bookrental.hasAnnotations(1));
	}

	@Test
	public final void testCanBookmarkPage() {
		assertTrue(bookrental.canBookmarkPage());
	}

	@Test
	public final void testCanAnnotatePage() {
		assertTrue(bookrental.canAnnotatePage());
	}

	@Test
	public final void testBookRental() {
		fail("Not yet implemented");
	}

	@Test
	public final void testAddAnnotationIntString() {
		//TODO think of a way to do for multiple pages
		bookrental.addAnnotation(1, "test");
		assertEquals(bookrental.getAnnotationText(1),"test");
		bookrental.addAnnotation(2,"test2");
		assertEquals(bookrental.getAnnotationText(2),"test2");
	}

	@Test
	public final void testRemoveAnnotationIntInt() {
		//TODO better tests
		bookrental.addAnnotation(1, "test");
		bookrental.addAnnotation(2, "test2");
		bookrental.addAnnotation(3, "test3");
		bookrental.addAnnotation(1, "test");
		bookrental.removeAnnotation(2, 1);
		assertNull(bookrental.getAnnotations(1));
		assertEquals(bookrental.getAnnotatins().size(),3);
	}

	@Test
	public final void testGetAnnotationTextIntInt() {
		//args: pagenum, annotnum
		bookrental.addAnnotation(1, "test");
		bookrental.addAnnotation(1,"test2");
		bookrental.addAnnotation(2,"test3");
		assertEquals(bookrental.getAnnotationText(1, 1),"test");
		assertEquals(bookrental.getAnnotationText(1, 2),"test2");
		assertEquals(bookrental.getAnnotationText(2, 1),"test2");
		
	}

	@Test
	public final void testIsBookmarked() {
		bookrental.toggleBookmark(1);
		assertTrue(bookrental.isBookmarked(1));
		bookrental.toggleBookmark(1);
		assertFalse(bookrental.isBookmarked(1));
	}

	@Test
	public final void testGetBookmarks() {
		ArrayList <Integer> bookmarks=new ArrayList<>();
		for(int i=0;i<10;i++){
			bookmarks.add(i);
			bookrental.toggleBookmark(i);
		}
		assertEquals(bookmarks,bookrental.getBookmarks());
	}

	@Test
	public final void testToggleBookmark() {
		//TODO multiple pages or multiple toggles
		assertFalse(bookrental.isBookmarked(1));
		bookrental.toggleBookmark(1);
		assertTrue(bookrental.isBookmarked(1));
	}

	@Test
	public final void testGetLastPageVisited() {
		//TODO multiple scenarios
		assertNull(bookrental.getLastPageVisited()); //or whatever the default value is
		bookrental.setLastPageVisited(1);
		assertEquals(bookrental.getLastPageVisited(),1);
		
	}

	@Test
	public final void testSetLastPageVisited() {
		//TODO multiple scenarios
		bookrental.setLastPageVisited(1);
		assertEquals(bookrental.getLastPageVisited(),1);
	}

}
