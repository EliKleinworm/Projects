/**
 * Project2 
 * Stack and Queue
 * Cans of Beans
 * Queue Class
 * @author Elikleinworm
 *
 */
public class Queue extends Object 
{
		private Node rear;
		public Queue(){
			rear=null;
		}
		/**
		 * determines if the queue is empty
		 */
		public boolean isEmpty(){
			return rear==null;
		}
		public void EnQ(Beans x){
			if(isEmpty()){
				rear= new Node(x);
				rear.next=rear;
			}
			else {
			rear.next.data=x;
			rear.next = rear.next;
			rear=rear.next;	
			}
		}
		public Beans DeQ(){
			if(isEmpty()){
				throw new NullPointerException("Queue is empty");
		}
		Beans Oldfront = rear.next.data;
		if (rear== rear.next)
				rear=null;
		else
			rear.next= rear.next.next;
		return Oldfront;
	}
}