package org.dieschnittstelle.ess.ejb.ejbmodule.crm;

import javax.ejb.Local;

@Local
public interface ShoppingCartServiceLocal {

    public ShoppingCartRemote getCartForId(long cartId);
}
