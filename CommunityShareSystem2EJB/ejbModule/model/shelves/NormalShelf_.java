package model.shelves;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.rentals.Rental;

@Generated(value="Dali", date="2015-05-30T20:06:56.331+0100")
@StaticMetamodel(NormalShelf.class)
public class NormalShelf_ extends Shelf_ {
	public static volatile SetAttribute<NormalShelf, Rental> rentals;
}
