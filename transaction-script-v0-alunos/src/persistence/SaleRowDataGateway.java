package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.DiscountType;
import application.SaleStatus;

public class SaleRowDataGateway {

	private static Map<Integer, SaleRowDataGateway> saleByID = new HashMap<> ();  

	//The ID of the sale
	private static int saleID;
	//The customer
	private CustomerRowDataGateway customer;
	//The list of products belonging to the sale
	private List<ProductRowDataGateway> products;
	//Total of the sale
	private double totalCost;
	//Status of the sale
	private SaleStatus status;


	public SaleRowDataGateway(){

	}
	public SaleRowDataGateway(int vat) throws RecordNotFoundException{ // opens a sale
		this.customer = CustomerRowDataGateway.getCustomerByVATNumber(vat);
		this.products=new ArrayList<ProductRowDataGateway>();
		SaleRowDataGateway.saleID=0; //TODO a way to get distinct ID's
		this.status=SaleStatus.OPEN;
	}



	//soh para ficar bonito
	public void closeSale(){
		this.status=SaleStatus.CLOSED;
	}



	public int getSaleId(){
		return this.saleID;
	}
	public static SaleRowDataGateway getSaleByID(int vat){
		SaleRowDataGateway sale = saleByID.get(saleID);
		return sale;
	}

	//adds product to the sale in the determined quantity
	public void addProduct(int productCode, double qty) throws RecordNotFoundException{
		ProductRowDataGateway added =ProductRowDataGateway.getProductByCode(productCode);
		added.setQty(qty);
		this.products.add(added);
	}
	
	public double getDiscountTotal(){
		DiscountType discountType = customer.getDiscountType();
		double percentage=0;
		if(discountType==DiscountType.NO_DISCOUNT)
			return 0;
		if(discountType==DiscountType.SALE_AMOUNT){
			//TODO
			return this.totalCost * percentage;
		}
		if(discountType==DiscountType.ELIGIBLE_PRODUCTS){
			//TODO
			
		}
	}

}
