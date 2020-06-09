package org.dieschnittstelle.ess.ejb.ejbmodule.erp;

import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * TODO JPA3/4/6:
 * - declare the web api for this interface using JAX-RS
 * - implement the interface as an EJB of an appropriate type
 * - in the EJB implementation, delegate method invocations to the corresponding methods of the StockSystem EJB via the local interface
 * - let the StockSystemClient in the client project access the web api via this interface - see ShoppingCartClient for an example
 */
public interface StockSystemRESTService {

	/**
	 * adds some units of a product to the stock of a point of sale
	 */
    void addToStock(long productId, long pointOfSaleId, int units);

	/**
	 * removes some units of a product from the stock of a point of sale
	 */
    void removeFromStock(long productId, long pointOfSaleId, int units);

	/**
	 * returns all products on stock of some pointOfSale
	 */
	@GET
	@Path("/products")
    List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId);

	/**
	 * returns all products on stock
	 */
	@GET
	@Path("/products")
    List<IndividualisedProductItem> getAllProductsOnStock();

	/**
	 * returns the units on stock for a product at some point of sale
	 */
	@GET
    int getUnitsOnStock(long productId, long pointOfSaleId);

	/**
	 * returns the total number of units on stock for some product
	 */
	@GET
    int getTotalUnitsOnStock(long productId);

	/**
	 * returns the points of sale where some product is available
	 */
    List<Long> getPointsOfSale(long productId);

}
