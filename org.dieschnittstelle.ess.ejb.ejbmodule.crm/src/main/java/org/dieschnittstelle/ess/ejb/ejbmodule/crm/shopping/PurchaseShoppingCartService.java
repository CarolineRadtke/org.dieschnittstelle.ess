package org.dieschnittstelle.ess.ejb.ejbmodule.crm.shopping;

import org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

import javax.ejb.Remote;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/purchase")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Remote

public interface PurchaseShoppingCartService {

	@POST
	public void purchase(@QueryParam("shoppingCardId") long shoppingCartId, @QueryParam("touchpointId") long touchpointId, @QueryParam("customerId") long customerId) throws ShoppingException;
	
}
