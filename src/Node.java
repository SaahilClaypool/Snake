

public class Node extends Object{
		private Node next;
		private Point p;
		
		public Node(Point p, Node next){
			this.p = p;
			this.next = next;
		}
		
		public Point getP() {
			return p;
		}
		public void setP(Point p) {
			this.p = p;
		}
		
		public Node getNext(){
			return next;
		}
		public void setNext(Node n){
			next = n;
		}
	}