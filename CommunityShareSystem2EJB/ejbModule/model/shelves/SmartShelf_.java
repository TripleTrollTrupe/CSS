package model.shelves;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.shelves.criteria.Criterion;

@Generated(value="Dali", date="2015-05-30T20:06:56.334+0100")
@StaticMetamodel(SmartShelf.class)
public class SmartShelf_ extends Shelf_ {
	public static volatile SingularAttribute<SmartShelf, Criterion> criteria;
	public static volatile SingularAttribute<SmartShelf, Shelf> myRentals;
}
