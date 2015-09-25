
public class Point {
	int r;
	int c;
	public Point(int row,int col){
		r = row;
		c = col;
	}
	public Point(double row,double col){
		r = (int)row;
		c = (int)col;
	}
	public int getRow(){
		return r;
	}
	public int getCol(){
		return c;
	}
	public String toString(){
		return "Row: " + r + "  Col:  " + c;
	}
	
}
