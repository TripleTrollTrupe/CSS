package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.ApplicationException;
import application.DiscountType;
import application.SaleStatus;

public class SaleRowDataGateway {

	private static Map<Integer, SaleRowDataGateway> saleByID = new HashMap<> ();  

	//The ID of the sale
	private int saleID;
	//ID of the next Sale
	private static int nextID=1;
	//The customer
	@SuppressWarnings("unused")
	private CustomerRowDataGateway customer;
	//The list of products belonging to the sale
	private List<SaleProductRowDataGateway> soldProducts;
	//Total of the sale
	@SuppressWarnings("unused")
	private double totalCost;
	//Status of the sale	
	@SuppressWarnings("unused")
	private SaleStatus status;


	public SaleRowDataGateway(){

	}
	public SaleRowDataGateway(int vat) throws ApplicationException{ // opens a sale
		try {
			this.customer = CustomerRowDataGateway.getCustomerByVATNumber(vat);
		} catch (RecordNotFoundException e) {

			throw new ApplicationException ("Customer does not exist");
			
		}
		this.soldProducts=new ArrayList<SaleProductRowDataGateway>();
		this.saleID=nextID++; //TODO a way to get distinct ID's
		this.status=SaleStatus.OPEN;
	}



	//soh para ficar bonito
	public void closeSale(){
		this.status=SaleStatus.CLOSED;
	}



	public int getSaleId(){
		return this.saleID;
	}
	public static SaleRowDataGateway getSaleByID(int saleID){
		SaleRowDataGateway sale = saleByID.get(saleID);
		return sale;
	}

	//adds product to the sale in the determined quantity
	public void addProduct(int saleProductID, double qty)throws ApplicationException{
		SaleProductRowDataGateway added;
		try {
			added = SaleProductRowDataGateway.getSaleProductByID(saleProductID);
		} catch (RecordNotFoundException e) {
			throw new ApplicationException ("SaleProduct does not exist");
			
		}
		added.setQuantity(qty);
		this.soldProducts.add(added);
	}
	//TODO I think this is the only thing left now
	public double getDiscountTotal(){
		double discountTotal=0;
		ConfigurationRowDataGateway conf = ConfigurationRowDataGateway.getConfiguration();
		if(customer.getDiscountType() == DiscountType.NO_DISCOUNT)
			return 0;
		for(SaleProductRowDataGateway saleProd : soldProducts){
			if(saleProd.isEligibleForDiscount()){
				//get the type?
				if(saleProd.getQuantity()>=conf.getAmountThreshold()){
					discountTotal += (saleProd.getSaleProductTotal()*conf.getEligiblePercentage());
				}
			}
			totalCost += saleProd.getSaleProductTotal();
		}
		if(discountTotal > (totalCost*conf.getAmountThresholdPercentage()))
			return totalCost*conf.getAmountThresholdPercentage();
		else
			return discountTotal;

	}
}


