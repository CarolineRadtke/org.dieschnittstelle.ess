package org.dieschnittstelle.ess.ejb.ejbmodule.erp;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.PointOfSaleCRUDLocal;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.StockItemCRUDLocal;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Remote(StockSystemRemote.class)
public class StockSystemSingleton implements StockSystemLocal, StockSystemRemote
{

    @EJB
    private StockItemCRUDLocal siCrud;

    @EJB
    private PointOfSaleCRUDLocal posCrud;


    @Override
    public void addToStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        System.out.println("addToStock(): siCrud: " + siCrud + "of class: " + siCrud.getClass());
        System.out.println("addToStock(): posCrud: " + posCrud + "of class: " + posCrud.getClass());

        PointOfSale pos = posCrud.readPointOfSale((pointOfSaleId));
        StockItem stockItem = siCrud.readStockItem(product, pos);
        if(stockItem == null){
            stockItem = new StockItem(product, pos, units);
            siCrud.createStockItem(stockItem);
        } else {
            stockItem.setUnits(stockItem.getUnits()+units);
            siCrud.updateStockItem(stockItem);
        }


    }

    @Override
    public void removeFromStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        addToStock(product, pointOfSaleId, -units);
    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
       List <IndividualisedProductItem> products = new ArrayList<>();
       PointOfSale pos = posCrud.readPointOfSale(pointOfSaleId);
       List<StockItem> stockItems = siCrud.readStockItemsForPointOfSale(pos);
       for (int i = 0; i < stockItems.size(); i++){
           products.add(stockItems.get(i).getProduct());

       }
       return products;
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        List <IndividualisedProductItem> allProds = new ArrayList<>();
        List <PointOfSale> allPos = posCrud.readAllPointsOfSale();
        for(PointOfSale pos : allPos){
            List <StockItem> stockItems = siCrud.readStockItemsForPointOfSale(pos);
            System.out.println("Liste: "+ stockItems);

//            if(stockItems != null){
                for(StockItem item : stockItems){
                    IndividualisedProductItem prod = item.getProduct();
                    if(!allProds.contains(prod)){
                        allProds.add(prod);
                    }

                }
//            }

        }
    return allProds;

    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        PointOfSale pos = posCrud.readPointOfSale(pointOfSaleId);
        StockItem si = siCrud.readStockItem(product, pos);
        return  si.getUnits();

    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        int totalUnits = 0;
        List<PointOfSale> poss = posCrud.readAllPointsOfSale();
        for(int i = 0; i < poss.size(); i++){
            PointOfSale pos = posCrud.readPointOfSale(poss.get(i).getId());
            if(siCrud.readStockItem(product, pos) != null){
                StockItem si = siCrud.readStockItem(product, pos);
                totalUnits += si.getUnits();
            }
        }
        return totalUnits;
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {

        List<Long> list = new ArrayList<>();
        List<PointOfSale> poss = posCrud.readAllPointsOfSale();
        for(int i = 0; i < poss.size(); i++){
            PointOfSale pos = posCrud.readPointOfSale(poss.get(i).getId());
            if(siCrud.readStockItem(product, pos) != null){
                list.add(poss.get(i).getId());
            }
        }
        return list;
    }

    @Override
    public List<StockItem> getCompleteStock() {
        throw new UnsupportedOperationException("getCompleteStock() is not supported!");
    }
}
