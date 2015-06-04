package model.rentals;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.lendables.Lendable;

@Generated(value="Dali", date="2015-05-30T20:06:56.317+0100")
@StaticMetamodel(Rental.class)
public class Rental_ {
	public static volatile SingularAttribute<Rental, Integer> id;
	public static volatile SingularAttribute<Rental, Lendable> lendable;
	public static volatile SingularAttribute<Rental, Date> timestamp;
	public static volatile SingularAttribute<Rental, Boolean> expired;
	public static volatile ListAttribute<Rental, String> annotations;
}
