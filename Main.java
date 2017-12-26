/**
 * Project2 
 * Stack and Queues
 * Cans of Beans
 * Main Class
 * 
 * @author Elikleinworm
 */
import java.util.Scanner;
import java.io.*;


public class Main {
	
	// write separate text files for costumer
	
	public static void main(String args[]) 
			throws FileNotFoundException {
		// read file
		try
		{
		Scanner input = new Scanner(new File("//Users/Elikleinworm/Documents/CS313/Project2.0/src/transactions.txt"));
		/*
		 * The Stack shipments hold the shipments of cans of beans the Queue orders
		 * holds the back orders of Beans
		 */
		Stacks shipments = new Stacks();
		Queue orders = new Queue();
		Beans ship;
		// array holds line split up
		String[] split;
		double currCost = 0.0;
		
		while (input.hasNextLine()) {
			// gets line in file
			String line = input.nextLine();
			split = line.split(" ");
			// R receives shipments
			// push on stack
			if (line.charAt(0) == 'R') {
				// how each line is being split
				ship = new Beans(Integer.parseInt(split[1]), Double.parseDouble(split[2]));
				// cost for Cans sold gets updated when new shipments come in.
				currCost = Double.parseDouble(split[2]) * 1.3;
				// adding shipments to the stack
				shipments.push(ship);
		
				while (orders.isEmpty() && shipments.isEmpty()) {
					
					Beans current_order = orders.DeQ();
					Object array[] = fillorders(current_order, shipments, orders);
					orders = (Queue) array[0];
					shipments = (Stacks) array[1];
				}
			} 
			else 
			{
				int amountordered = Integer.parseInt(split[1]);
				Beans currorder = new Beans(amountordered, currCost);
				/*
				 * if the stack of shipments isn't empty then fill orders
				 * otherwise put the order in the queue
				 */
				if (!shipments.isEmpty()) {
					Object array[] = fillorders(currorder, shipments, orders);
					orders = (Queue) array[0];
					shipments = (Stacks) array[1];
				} else
					orders.EnQ(currorder);

			}
		
		}
		// close file

	input.close();
		}

	catch (FileNotFoundException FnFe){
		System.out.print("file not found");
		}
	}
	
	public static Object[] fillorders(Beans currorder, Stacks shipments, Queue order) {
		// price that Can of Beans are sold for
		int amountfilled = 0;
		int amount = currorder.getAmount();
		int original_amount = currorder.getAmount();

		Beans inventory = shipments.pop();

		double inventory_price = inventory.getCost();
		int inventory_amount = inventory.getAmount();

		while (amount != 0) 
		{
			if (inventory_amount > amount) {
				amountfilled = original_amount;
				inventory.setAmount(inventory_amount - amount);
				shipments.push(inventory);
				book_keeping(amount, inventory_price); // prints out bookkeeping and receipt
				amount = 0;
				break;

			}
		
			else 
			{
				amountfilled += inventory_amount;
				amount -= inventory_amount;
				currorder.setAmount(amount);
				book_keeping(inventory_amount, inventory_price);
		
				if (shipments.isEmpty()) {

					Queue replace = new Queue();
					replace.EnQ(currorder);
					while (!order.isEmpty()) {
						replace.EnQ(order.DeQ());
						order = replace;
						break;
					}
				}
				
				else {
					inventory = shipments.pop();
					amount = currorder.getAmount();
					inventory_price = inventory.getCost();

					inventory_amount = inventory.getAmount();

				}
			}
		}
		costumer_receipt(amountfilled, currorder.getCost());
		// put stack and queue into array
		Object arr[] = new Object[2];
		arr[0] = order;
		arr[1] = shipments;
		return arr;
	}

	/**
	 * Prints costumer receipt.
	 */

	public static void costumer_receipt(int amount, double cost) {
		double price = cost * amount;
		System.out.println("Costumer receipt:");
		System.out.print(amount + " @ $");
		//makes 2 decimals
		System.out.printf("%.2f", cost);
		System.out.print(" = $");
		System.out.printf("%.2f", price);
		System.out.println("\n");
	}

	/**
	 * this will print out the bookkeeping and the actual amount
	 * the cans of beans cost
	 *
	 */
	public static void book_keeping(int amount, double cost) {
		// total price
		double price = cost * amount;
		System.out.println("Total actual cost:");
		System.out.print(amount + " @ $");
		//makes 2 decimals
		System.out.printf("%.2f", cost);
		System.out.print(" = $");
		System.out.printf("%.2f", price);
		System.out.println("\n");
	}
	

	

	
	
}
