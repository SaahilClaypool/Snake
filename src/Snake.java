
public class Snake {
	private int length; // start at 0
	private Node head;
	private double direction = this.RIGHT;
	
	public static final double UP = 3.0/2 * Math.PI;
	public static final double DOWN = .5 * Math.PI;
	public static final double LEFT = Math.PI;
	public static final double RIGHT = 0;
	
	int x = 0;
	 
	
	
	public Snake(int length, Point start){
		this.length = length;
		head = new Node(start, null);
		
	}
	// adds to head.
	public void move(Point p){System.out.println(++x);
		Node n = head;
		head = new Node(p, n);
		
		// cuts off things longer than length
		n = head;
		for(int i = 0; i < length; i++){
			if(n.getNext() == null) break;
			n = n.getNext();
			
		}
		n.setNext(null);
	}
	
	public Node[] getPoints(){
		Node[] p = new Node[length];
		Node n = head;
		for(int i = 0;i < length; i ++){
			if(n != null){ 
				p[i] = n;
				n = n.getNext();
			}
		}
		return p;
	}
	
	public void growLength(int i){
		length += i;
	}

	
	public void setDirection (double d){
		if(Math.abs(direction - d) == Math.PI) return;
		direction = d;
	}
	public double getDirection(){
		return direction;
	}
	
}
