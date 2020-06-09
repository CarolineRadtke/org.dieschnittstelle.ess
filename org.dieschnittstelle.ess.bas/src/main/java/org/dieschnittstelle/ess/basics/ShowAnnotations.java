package org.dieschnittstelle.ess.basics;


import org.dieschnittstelle.ess.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.ess.basics.annotations.DisplayAs;
import org.dieschnittstelle.ess.basics.annotations.StockItemProxyImpl;
import org.dieschnittstelle.ess.basics.annotations.Units;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.dieschnittstelle.ess.utils.Utils.*;

public class ShowAnnotations {

	public static void main(String[] args) {
		// we initialise the collection
		StockItemCollection collection = new StockItemCollection(
				"stockitems_annotations.xml", new AnnotatedStockItemBuilder());
		// we load the contents into the collection
		collection.load();

		for (IStockItem consumable : collection.getStockItems()) {
			;
			showAttributes(((StockItemProxyImpl) consumable).getProxiedObject());
		}

//        // we initialise a consumer
//        Consumer consumer = new Consumer();
//        // ... and let them consume
//        consumer.doShopping(collection.getStockItems());
	}

	/*
	 * TODO BAS2
	 */
	private static void showAttributes(Object consumable) {
		ArrayList resultArrayList = new ArrayList();
		Class klasse = consumable.getClass();
		String output = "";

		// get the Fields
		for (Field attr : klasse.getDeclaredFields()) {
//			show("true or false " + attr.isAnnotationPresent(DisplayAs.class));

			// get get-method for all attributes
			String getterName = getAccessorNameForField("get", attr.getName());
			Method getterMethod = null;

			for (Method getter : klasse.getDeclaredMethods()) {
				if (getterName.equals(getter.getName())) {
					getterMethod = getter;
				}
			}

			try {
				// invoke  the found getter-Method
				String getit = getterMethod.invoke(consumable).toString();
				// string with attribute and its value
				// first check for Display As Annotation
				if(attr.isAnnotationPresent(DisplayAs.class)){
					output = output + attr.getAnnotation(DisplayAs.class).value() + ": "+ getit + ",";
				} else {
				}


			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		resultArrayList.add("{ " + klasse.getSimpleName() + " --> " + output + "}");

		// show everything in console
		for (int i = 0; i < resultArrayList.size(); i++) {
			show(resultArrayList.get(i));
		}


	}


	// create getter/setter names ( from ReflectedStockItemBuilder.java )
	public static String getAccessorNameForField(String accessor, String fieldName) {
		return accessor + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}





}
