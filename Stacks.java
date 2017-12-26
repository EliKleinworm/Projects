/**
 * Project2 
 * Stack and Queues
 * Cans of Beans
 * Stack Class
 * 
 * @author Elikleinworm
 *
 */
public class Stacks extends Object 
{
		private Node top;
		public Stacks() 
		{
			
		}
		
		// checks if a stack is empty
		public boolean isEmpty(){ 
		return top==(null);
		}
		public Beans peak (){
			if (isEmpty())
				throw new NullPointerException("Stack is empty");
				
			return top.data;
		}

			public Beans pop (){
				if (isEmpty())
					throw new NullPointerException("Stack is empty");
				Beans Opeak = top.data;
				top= top.next;
				return Opeak;
				
			}
			
			public void push (Beans d){
				Node Ntop = new Node(d);
				Ntop.next = top;
				top = Ntop;
			}
	}