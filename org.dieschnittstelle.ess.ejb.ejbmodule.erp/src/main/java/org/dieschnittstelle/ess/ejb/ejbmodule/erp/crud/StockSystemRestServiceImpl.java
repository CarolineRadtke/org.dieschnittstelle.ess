package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemLocal;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemRESTService;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.graalvm.compiler.lir.LIRInstruction;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;
import java.util.List;

@Remote(StockSystemRESTService.class)
@Stateless
public class StockSystemRestServiceImpl implements StockSystemRESTService {

    @EJB
    private StockSystemLocal stockSystem;

    @EJB
    private ProductCRUDRemote productCRUD;

    @Override
    public void addToStock(long productId, long pointOfSaleId, int units) {
        AbstractProduct prod = productCRUD.readProduct(productId);
        stockSystem.addToStock((IndividualisedProductItem) prod, pointOfSaleId, units);

    }

    @Override
    public void removeFromStock(long productId, long pointOfSaleId, int units) {
        AbstractProduct prod = productCRUD.readProduct(productId);
        stockSystem.removeFromStock((IndividualisedProductItem) prod, pointOfSaleId, units);

    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        return null;
    }

    @Override
    public int getUnitsOnStock(long productId, long pointOfSaleId) {

        if (productId > 0 && pointOfSaleId > 0) {
            AbstractProduct prod = productCRUD.readProduct(productId);
            return stockSystem.getUnitsOnStock((IndividualisedProductItem) prod, pointOfSaleId);

        } else if (productId > 0) {
            AbstractProduct prod = productCRUD.readProduct(productId);
            return stockSystem.getTotalUnitsOnStock((IndividualisedProductItem) prod);
        } else {
            throw new BadRequestException("At least pointOfSaleId must refer to an extisting pointOfSale");

        }
    }

    @Override
    public List<Long> getPointsOfSale(long productId) 	{
		AbstractProduct prod = productCRUD.readProduct(productId);
		if(prod instanceof IndividualisedProductItem){
			return stockSystem.getPointsOfSale((IndividualisedProductItem) prod);
		} else {
			throw new BadRequestException("prodId" + productId + "does not refer to an Individualized Product Item");
		}

	};
}
