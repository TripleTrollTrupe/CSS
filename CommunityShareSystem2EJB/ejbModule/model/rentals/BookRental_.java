package model.rentals;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-06-01T15:06:29.284+0100")
@StaticMetamodel(BookRental.class)
public class BookRental_ {
	public static volatile SingularAttribute<BookRental, Integer> lastPageVisited;
	public static volatile MapAttribute<BookRental, Integer, Page> pages;
}
