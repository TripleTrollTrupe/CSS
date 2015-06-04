package model.rentals;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-05-30T20:06:56.315+0100")
@StaticMetamodel(Page.class)
public class Page_ {
	public static volatile SingularAttribute<Page, Integer> id;
	public static volatile SingularAttribute<Page, Boolean> bookmark;
	public static volatile ListAttribute<Page, String> annotations;
	public static volatile SingularAttribute<Page, Integer> pageNum;
}
