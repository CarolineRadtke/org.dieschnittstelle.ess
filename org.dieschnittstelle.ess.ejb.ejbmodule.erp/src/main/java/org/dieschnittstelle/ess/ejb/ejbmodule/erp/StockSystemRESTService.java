package org.dieschnittstelle.ess.ejb.ejbmodule.erp;

import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * TODO JPA3/4/6:
 * - declare the web api for this interface using JAX-RS
 * - implement the interface as an EJB of an appropriate type
 * - in the EJB implementation, delegate method invocations to the corresponding methods of the StockSystem EJB via the local interface
 * - let the StockSystemClient in the client project access the web api via this interface - see ShoppingCartClient for an example
 */
@Path("/stocksystem")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface StockSystemRESTService {

	/**
	 * adds some units of a product to the stock of a point of sale
	 */
	@POST
	@Path("/{productId}/{pointOfSaleId}/{units}")
	void addToStock(@PathParam("productId")long productId, @PathParam("pointOfSaleId") long pointOfSaleId, @PathParam("units") int units);

	/**
	 * removes some units of a product from the stock of a point of sale
	 */
	@DELETE
	@Path("/{productId}/{pointOfSaleId}/{units}")
	void removeFromStock(@PathParam("productId")long productId, @PathParam("pointOfSaleId") long pointOfSaleId, @PathParam("units") int units);

	/**
	 * returns all products on stock of some pointOfSale
	 */
	@GET
	@Path("/products")
	List<IndividualisedProductItem> getProductsOnStock(@QueryParam("pointOfSaleId") long pointOfSaleId);

	/**
	 * returns all products on stock
	 */
//	@GET
//	@Path("/products")
//    List<IndividualisedProductItem> getAllProductsOnStock();

	/**
	 * returns the units on stock for a product at some point of sale
	 */
	@GET
    int getUnitsOnStock(@QueryParam("productId") long productId, @QueryParam("pointOfSaleId") long pointOfSaleId);

//	};

	/**
	 * returns the total number of units on stock for some product
//	 */
//	@GET
//    int getTotalUnitsOnStock(@QueryParam("productId") long productId);

	/**
	 * returns the points of sale where some product is available
	 */
	@GET
	@Path("/poss")
    List<Long> getPointsOfSale(@QueryParam("productId") long productId);



}
