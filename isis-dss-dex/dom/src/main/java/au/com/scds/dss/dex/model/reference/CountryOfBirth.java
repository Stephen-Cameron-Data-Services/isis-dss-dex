package au.com.scds.dss.dex.model.reference;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Query;

@PersistenceCapable()
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "CountryOfBirth")
@Query(name="all", language="JDOQL", value="SELECT FROM au.com.scds.dss.dex.model.reference.CountryOfBirth ORDER BY orderNumber ASC;")
public class CountryOfBirth extends AbstractReferenceItem {
}
