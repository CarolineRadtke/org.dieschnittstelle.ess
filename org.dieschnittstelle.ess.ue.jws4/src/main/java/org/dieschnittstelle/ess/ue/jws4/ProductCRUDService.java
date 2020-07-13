package org.dieschnittstelle.ess.ue.jws4;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.Campaign;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.ProductType;

/*
 * TODO JWS4: machen Sie die Funktionalitaet dieser Klasse als Web Service verfuegbar und verwenden Sie fuer
 *  die Umetzung der Methoden die Instanz von GenericCRUDExecutor<AbstractProduct>,
 *  die Sie aus dem ServletContext auslesen koennen
 */

@WebService (targetNamespace = "http://dieschnittstelle.org/ess/jws", name = "IProductCRUDService", serviceName = "ProductCRUDWebService", portName = "ProductCRUDPort")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)

public class ProductCRUDService {

	@Resource
	private WebServiceContext wscontext;

	@WebMethod
	public List<AbstractProduct> readAllProducts() {
		ServletContext ctx = (ServletContext) wscontext.getMessageContext()
				.get(MessageContext.SERVLET_CONTEXT);
		HttpServletRequest httpRequest = (HttpServletRequest) wscontext
				.getMessageContext().get(MessageContext.SERVLET_REQUEST);
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ctx
				.getAttribute("productCRUD");
		return productCRUD.readAllObjects();
	}

	@WebMethod
	public AbstractProduct createProduct(AbstractProduct product) {
		ServletContext ctx = (ServletContext) wscontext.getMessageContext()
				.get(MessageContext.SERVLET_CONTEXT);
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ctx
				.getAttribute("productCRUD");
		return productCRUD.createObject(product);
	}

	@WebMethod
	public AbstractProduct updateProduct(AbstractProduct update) {
		ServletContext ctx = (ServletContext) wscontext.getMessageContext()
				.get(MessageContext.SERVLET_CONTEXT);
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ctx
				.getAttribute("productCRUD");
		return productCRUD.updateObject(update);
	}
	@WebMethod
	public boolean deleteProduct(long id) {
		ServletContext ctx = (ServletContext) wscontext.getMessageContext()
				.get(MessageContext.SERVLET_CONTEXT);
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ctx
				.getAttribute("productCRUD");
		return productCRUD.deleteObject(id);
	}

	@WebMethod
	public AbstractProduct readProduct(long id) {
		ServletContext ctx = (ServletContext) wscontext.getMessageContext()
				.get(MessageContext.SERVLET_CONTEXT);
		GenericCRUDExecutor<AbstractProduct> productCRUD = (GenericCRUDExecutor<AbstractProduct>) ctx
				.getAttribute("productCRUD");
		return productCRUD.readObject(id);
	}

}
