package org.dieschnittstelle.ess.basics.annotations.stockitemtypes;

import org.dieschnittstelle.ess.basics.ShowAnnotations;
import org.dieschnittstelle.ess.basics.annotations.Initialise;
import org.dieschnittstelle.ess.basics.annotations.Brandname;
import org.dieschnittstelle.ess.basics.annotations.StockItem;
import org.dieschnittstelle.ess.basics.annotations.Purchase;
import org.dieschnittstelle.ess.basics.annotations.Units;
import org.dieschnittstelle.ess.basics.annotations.DisplayAs;

@StockItem
public class Schokolade {

	@Units
	@DisplayAs("OwnAnnotation:Anzahl")
	private int anzahlStuecke;


	private String marke;


	public int getAnzahlStuecke() {
		return anzahlStuecke;
	}

	public void setAnzahlStuecke(int anzahlStuecke) {
		this.anzahlStuecke = anzahlStuecke;
	}

	@Brandname
	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}

	@Initialise
	public void insLagerUebernehmen(int units, String name) {
		this.anzahlStuecke = units;
		this.marke = name;
	}

	@Purchase
	public void indenEinkaufswagenLegen(int unitsToPurchase) {
		if (unitsToPurchase > this.anzahlStuecke) {
			throw new RuntimeException(
					"You cannot purchase more than what is available. Got: "
							+ unitsToPurchase
							+ " units to purchase, but have available only: "
							+ this.anzahlStuecke);
		}
		this.anzahlStuecke -= unitsToPurchase;
	}

	/**
	 * toString
	 */
	public String toString() {
		return "<Schokolade " + this.marke + " " + this.anzahlStuecke + ">";
	}
}
