/**
 * Project2 
 * Stack and Queues
 * Cans of Beans
 * Beans class
 * 
 * @author Elikleinworm
 *
 */
public class Beans extends Object
{
	private int amount; //amount = a
	private double cost; //cost = c
	public Beans(int a, double c)
	{
		
		if(a<0 || c<0)
			throw new IllegalArgumentException("Invalid amount or cost");
		amount = a;
		cost = c;
		}

	public int getAmount()
	{
		return amount;
		}

	public double getCost()
	{
		return cost;
		}

	public void setAmount (int a)
	{
		
		if (a<0) 
			throw new IllegalArgumentException("amount is Invalid");
		amount = a;
		}

	public void setCost(double c)
	{
		
		if (c<0) 
			throw new IllegalArgumentException("cost is Invalid");
		cost=c;
		}
}
