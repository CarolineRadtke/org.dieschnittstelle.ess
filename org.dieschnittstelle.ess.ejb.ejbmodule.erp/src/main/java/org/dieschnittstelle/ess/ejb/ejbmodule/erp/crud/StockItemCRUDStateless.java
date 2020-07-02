package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.ProductAtPosPK;
import org.dieschnittstelle.ess.entities.erp.StockItem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

// Rein Datenverabeitend --> überträgt Daten in eine Datenbank

@Stateless
public class StockItemCRUDStateless implements StockItemCRUDLocal {

    @PersistenceContext(unitName = "erp_PU")
    private EntityManager em;

    @Override
    public StockItem createStockItem(StockItem item) {
        item = em.merge(item);
        return item;
    }

    @Override
    public StockItem readStockItem(IndividualisedProductItem prod, PointOfSale pos) {
//        //zum ableiten der anderen Methoden --> highlighter
        Query qu = em.createQuery(
                "SELECT si FROM StockItem si WHERE si.pos = "
                        + pos.getId() + " AND si.product = " + prod.getId());
        List<StockItem> stockItems = qu.getResultList();
        if (stockItems.size() > 0) {
            return stockItems.get(0);
        }
        return null;
//        return em.find(StockItem.class, new ProductAtPosPK(prod,pos));
    }

    @Override
    public StockItem updateStockItem(StockItem item) {
        return em.merge(item);
    }

    @Override
    public List<StockItem> readAllStockItems(){
        Query qu = em.createQuery("SELECT si FROM StockItem");
        List<StockItem> stockItems = qu.getResultList();
        if (stockItems.size() > 0) {
            return stockItems;
        }
        return null;
    }

    @Override
    public List<StockItem> readStockItemsForProduct(IndividualisedProductItem prod) {
        Query qu = em.createQuery("SELECT si FROM StockItem si WHERE si.product = " + prod.getId());
        List<StockItem> stockItems = qu.getResultList();
        if (stockItems.size() > 0) {
            return stockItems;
        }
        return null;
    }

    @Override
    public List<StockItem> readStockItemsForPointOfSale(PointOfSale pos) {
        Query qu = em.createQuery(
                "SELECT si FROM StockItem si WHERE si.pos = "
                        + pos.getId());
        List<StockItem> stockItems = qu.getResultList();
        return stockItems;

    }
}
