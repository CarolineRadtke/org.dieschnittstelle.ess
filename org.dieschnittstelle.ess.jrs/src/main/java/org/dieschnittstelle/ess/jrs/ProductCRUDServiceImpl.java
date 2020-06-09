package org.dieschnittstelle.ess.jrs;

import java.util.List;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

/*
 * TODO JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 *
 *  2. klasse implementieren --> dazu schluss von Demo verwenden und Servlet context attribut deklarieren, dass ich mir über
 *  die Kontextannotation injecten lasse // hilffunktion kann aus Demo übernommen haben. // Siehe Touchpoint Crud
 *  // Servlet liegt unter ProductServletContextListener
 * // Server neu starten --> implementierung serverseitig
 *
 * // dann im Client weiter
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	@Context // Rest-eays als JXRS --> Context und auch
	private ServletContext servletContext;



	private GenericCRUDExecutor<AbstractProduct> readExecFromServletContext(){
		return (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("productCRUD");
	}

	@Override
	public AbstractProduct createProduct(
			AbstractProduct prod) {


		return (AbstractProduct) this.readExecFromServletContext().createObject(prod);

	}

	@Override
	public List<AbstractProduct> readAllProducts() {

		return (List<AbstractProduct>) this.readExecFromServletContext().readAllObjects();
	}

	@Override // warum hier noch id ?
	public AbstractProduct updateProduct(long id,
										 AbstractProduct update) {
		return (AbstractProduct) this.readExecFromServletContext().updateObject(update);
	}

	@Override
	public boolean deleteProduct(long id) {
		// TODO Auto-generated method stub
		return this.readExecFromServletContext().deleteObject(id);
	}

	@Override
	public AbstractProduct readProduct(long id) {
		// TODO Auto-generated method stub
		return (AbstractProduct) this.readExecFromServletContext().readObject(id);
	}

}
