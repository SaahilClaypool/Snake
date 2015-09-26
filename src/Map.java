

public class Map {
	private int rows; 
	private int cols;

	Object[][] field;
	Fruit f;
	Snake s1;



    Snake s2;


    public Snake getS2() {
        return s2;
    }
    public Snake getS1() {
        return s1;
    }


	boolean isOver = false;
	public Map(int rows, int cols){
		f = new Fruit(new Point((rows - 1)/2 + 1, (cols -1)/2));
		this.rows = rows;
		this.cols = cols;
		field = new Object[rows][cols];
		for(int i = 0; i < rows; i ++){
			for (int j = 0; j < cols; j++){
				field[i][j] = null;
			}
		}
		s1 = new Snake (3, new Point(rows / 2, 0));
		s2 = new Snake (3, new Point(rows / 2, cols/2));
	}
	public void MoveFruit(){
		Point p = new Point((int)(Math.random() * rows), (int)(Math.random()*cols) );
		if(this.getSpot(p) == null){
			f.setP(p);
		}
		else MoveFruit();
	}
	public boolean isContained(Point p ){
		return p.getRow() >= 0 && p.getRow() < rows
				&& p.getCol() >= 0 && p.getCol() < cols;
	}
	public void setSpot(Point p, Object o){
		if(isContained(p)){
			field[p.getRow()][p.getCol()] = o;
		}
	}
	public Object getSpot(Point p){
		return field[p.getRow()][p.getCol()];
	}
	public void updateBoard(){
		// clear
		for(int i = 0; i < rows; i ++){
			for (int j = 0; j < cols; j++){
				field[i][j] = null;
			}
		}
		this.setSpot(f.getP(), f);
		
		
		Node[] n = s1.getPoints();
        for(int i = 0; i < n.length; i++){
            if(n[i] != null){
                if(this.isContained(n[i].getP())){
                    if(this.getSpot(n[i].getP()) == null)
                        this.setSpot(n[i].getP(), n[i]);
                    else if(this.getSpot(n[i].getP()) instanceof Fruit){
                        s1.growLength(2);
                        this.setSpot(n[i].getP(), n[i]);
                        this.MoveFruit();
                    }
                    else{
                        isOver = true;
                    }
                }
                else isOver = true;
            }
        }

        Node[] n2 = s2.getPoints();
        for(int i = 0; i < n2.length; i++){
            if(n2[i] != null){
                if(this.isContained(n2[i].getP())){
                    if(this.getSpot(n2[i].getP()) == null)
                        this.setSpot(n2[i].getP(), n2[i]);
                    else if(this.getSpot(n2[i].getP()) instanceof Fruit){
                        s2.growLength(2);
                        this.setSpot(n2[i].getP(), n2[i]);
                        this.MoveFruit();
                    }
                    else{
                        isOver = true;
                    }
                }
                else isOver = true;
            }
        }
	}
	public void setDirS(double d, Snake s){
		s.setDirection(d);
	}

	public void moveSnake(Snake s){
		//System.out.println(s1.getDirection());
		Point old = s.getPoints()[0].getP();
		System.out.println("OLD  " + old);
		System.out.println("ROW CHANGE " + old.getRow() + Math.sin(s1.getDirection()));
		System.out.println("COL CHANGE " + old.getCol() + Math.cos(s1.getDirection()));
		
		Point n = new Point(Math.round(old.getRow() + Math.sin(s1.getDirection())),
				Math.round(old.getCol() + Math.cos(s1.getDirection())));
		System.out.println("NEW  " + n);
		
		s.move(n);
	}
	public String toString(){
		if(isOver){
			return "END";
		}
		String s = "";
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field[0].length; j++){
				
				if(this.getSpot(new Point(i,j)) == null) s += "*";
				else{
					if(this.getSpot(new Point(i,j)) instanceof Fruit){
						s+='f';
					}
					else if(this.getSpot(new Point(i,j)) instanceof Node){
						s+= 's';
					}
				}
			}
			s+="\n";
		}
		return s;
	}
 public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}
public static void main (String[] args){
	 System.out.println("Working");
	 Map m = new Map(10,10);
	 m.updateBoard();
	 System.out.println(m);
	 
	m.moveSnake(m.getS1());
	m.updateBoard();
	System.out.println(m);
	
	m.moveSnake(m.getS1());
	m.updateBoard();
	System.out.println(m);
	
//	m.moveSnake();
//	m.updateBoard();
//	System.out.println(m);
	 


	
	m.setDirS(Snake.UP, m.getS1());

	
	m.moveSnake(m.getS1());
	m.updateBoard();
	System.out.println(m);
 }
}
