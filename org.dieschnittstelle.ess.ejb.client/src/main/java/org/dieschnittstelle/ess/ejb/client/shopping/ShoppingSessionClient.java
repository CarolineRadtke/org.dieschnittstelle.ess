package org.dieschnittstelle.ess.ejb.client.shopping;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.ejb.client.ejbclients.EJBProxyFactory;
import org.dieschnittstelle.ess.ejb.client.ejbclients.ShoppingCartClient;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.shopping.PurchaseShoppingCartService;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.entities.crm.ShoppingCartItem;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.Campaign;

public class ShoppingSessionClient implements ShoppingBusinessDelegate {

	protected static Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(ShoppingSessionClient.class);
	private ShoppingCartClient shoppingCartClient;

	private PurchaseShoppingCartService purchaseShoppingCartService;
	private AbstractTouchpoint touchpoint;
	private Customer customer;

	
	/*
	 * TODO PAT1: use an appropriate proxy for the server-side shopping interface, depending on whether
	 *  it is a stateful shopping session ejb (ShoppingSessionFacadeRemote) or a stateless
	 *  rest service (PurchaseShoppingCartClient)
	 *  Note that, if the rest service is used, touchpoint and customer need to be stored locally
	 *  before purchase() is invoked. For accessing shopping cart data use a local ShoppingCartClient
	 *  in this case and access the shopping cart using the provided getter method
	 */

	public ShoppingSessionClient() {
		/* TODO: instantiate the proxy using the EJBProxyFactory (see the other client classes) */
		try {
			this.shoppingCartClient = new ShoppingCartClient(); 
			this.purchaseShoppingCartService = EJBProxyFactory.getInstance().getProxy(PurchaseShoppingCartService.class, "", true);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/* TODO: implement the following methods s */

	@Override
	public void setTouchpoint(AbstractTouchpoint touchpoint) {
		this.touchpoint = touchpoint;
	
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	
	}

	@Override
	public void addProduct(AbstractProduct product, int units) {
		ShoppingCartItem item = new ShoppingCartItem(product.getId(), units);
		if(product instanceof Campaign){
			item.setCampaign(true); 
		}
		this.shoppingCartClient.addItem(item);

// Totoaluse case usePurchaseClient = ture setzen 
	
	}

	@Override
	public void purchase() throws ShoppingException {
		this.purchaseShoppingCartService.purchase(shoppingCartClient.getShoppingCartEntityId(), touchpoint.getId(), customer.getId());

	}

}
