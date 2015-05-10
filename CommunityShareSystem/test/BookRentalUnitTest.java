import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.lendables.Lendable;
import model.rentals.BookRental;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import adts.Pair;

@RunWith(MockitoJUnitRunner.class)
public class BookRentalUnitTest {
	@Mock private EntityManager em;
	@Mock private EntityManagerFactory emf;
	@Mock private Lendable book;
	private BookRental bookrental;
	@Before
	public void setUp() throws Exception {
		
		bookrental = new BookRental(book); 
		given(emf.createEntityManager()).willReturn(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
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
		bookrental.addAnnotation(1,strings.get(0));
		bookrental.addAnnotation(1, strings.get(1));
		bookrental.addAnnotation(2,strings.get(2));
		Iterator<String> itS = strings.iterator();
		Iterator<Pair<Integer, List<String>>> it = bookrental.getAnnotatins().iterator();
		while(it.hasNext()){
			Iterator<String> itL = it.next().getSecond().iterator();
			while(itL.hasNext())
				assertEquals(itL.next(),itS.next());
		}		
	}

	@Test
	public final void testHasAnnotations() {
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
		bookrental.addAnnotation(0, "test");
		assertTrue(bookrental.hasAnnotations(0));
		assertEquals(bookrental.getAnnotationText(0),"test");
		bookrental.addAnnotation(1,"test2");
		assertTrue(bookrental.hasAnnotations(1));
		assertEquals(bookrental.getAnnotationText(1),"test2");
	}

	@Test
	public final void testRemoveAnnotationIntInt() {
		bookrental.addAnnotation(0, "test");
		bookrental.addAnnotation(1, "test2");
		bookrental.addAnnotation(2, "test3");
		bookrental.addAnnotation(0, "test");
		bookrental.removeAnnotation(1, 0);
		assertEquals(bookrental.getAnnotations(1),new ArrayList<>());
		assertEquals(bookrental.getAnnotatins().size(),3);
	}

	@Test
	public final void testGetAnnotationTextIntInt() {
		bookrental.addAnnotation(0, "test");
		bookrental.addAnnotation(0,"test2");
		bookrental.addAnnotation(1,"test3");
		assertEquals(bookrental.getAnnotationText(0, 0),"test");
		assertEquals(bookrental.getAnnotationText(0, 1),"test2");
		assertEquals(bookrental.getAnnotationText(1, 0),"test3");
		
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
		assertFalse(bookrental.isBookmarked(1));
		bookrental.toggleBookmark(1);
		assertTrue(bookrental.isBookmarked(1));
		bookrental.toggleBookmark(1);
		assertFalse(bookrental.isBookmarked(1));
	}

	@Test
	public final void testGetLastPageVisited() {
		assertEquals(bookrental.getLastPageVisited(),0); //or whatever the default value is
		bookrental.setLastPageVisited(1);
		assertEquals(bookrental.getLastPageVisited(),1);
		
	}

	@Test
	public final void testSetLastPageVisited() {
		bookrental.setLastPageVisited(1);
		assertEquals(bookrental.getLastPageVisited(),1);
	}

}
