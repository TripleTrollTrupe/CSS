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
	//ID of the next Sale
	private static int nextID=1;
	//The customer
	private CustomerRowDataGateway customer;
	//The list of products belonging to the sale
	private List<SaleProductRowDataGateway> soldProducts;
	//Total of the sale
	private double totalCost;
	//Status of the sale
	private SaleStatus status;


	public SaleRowDataGateway(){

	}
	public SaleRowDataGateway(int vat) throws RecordNotFoundException{ // opens a sale
		this.customer = CustomerRowDataGateway.getCustomerByVATNumber(vat);
		this.soldProducts=new ArrayList<SaleProductRowDataGateway>();
		SaleRowDataGateway.saleID=nextID++; //TODO a way to get distinct ID's
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
	public void addProduct(int saleProductID, double qty) throws RecordNotFoundException{
		SaleProductRowDataGateway added =SaleProductRowDataGateway.getSaleProductByID(saleProductID);
		added.setQuantity(qty);
		this.soldProducts.add(added);
	}
	//TODO I think this is the only thing left now
	public double getDiscountTotal(){
		double discountTotal;
		return discountTotal;
			
		}
	}

}
