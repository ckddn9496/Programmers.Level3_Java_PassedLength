import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		System.out.println(new Solution().solution("ULURRDLLU"));
	}
	
}
class Solution {
    public int solution(String dirs) {
    	Passed passed = new Passed();
    	Point prevPoint = new Point(0, 0);
    	Point curPoint = new Point(0, 0);
    	for (int i = 0; i < dirs.length(); i++) {
    		char tok = dirs.charAt(i);
    		try {
				prevPoint = curPoint.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
    		switch (tok) {
			case 'U':
				curPoint.up();
				break;
			case 'D':
				curPoint.down();
				break;
			case 'R':
				curPoint.right();
				break;
			case 'L':
				curPoint.left();
				break;
			}
    		passed.movePoint(prevPoint, curPoint);
    	}
    	return passed.getLineNum();
    }
    class Line {
    	int x1,y1;
    	int x2,y2;
    	public Line(Point p1, Point p2) {
    		if (p1.x == p2.x) {
    			if (p1.y < p2.y) {
    				x1 = p1.x;
    				y1 = p1.y;
	        		x2 = p2.x;
	        		y2 = p2.y;
    			}
        		else if (p1.y > p2.y){
        			x1 = p2.x;
        			y1 = p2.y;
        			x2 = p1.x;
        			y2 = p1.y;
        		} else {
        			x1 = x2 = p1.x;
        			y1 = y2 = p1.y;
        		}
    		} else if (p1.x < p2.x) {
    			x1 = p1.x;
				y1 = p1.y;
        		x2 = p2.x;
        		y2 = p2.y;
    		} else {
    			x1 = p2.x;
    			y1 = p2.y;
    			x2 = p1.x;
    			y2 = p1.y;
    		}
    	}
    	boolean hasLine() {
    		if (x1 == x2 && y1 == y2) 
    			return false;
    		return true;
    	}
    	@Override
    	public String toString() {
    		return "(" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 +")";
    	}
    }
    
    class Point implements Cloneable {
    	int x, y;
    	public Point(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    	void up() {
    		if (y < 5) y++;
    	}
    	void down() {
    		if (y > -5) y--;
    	}
    	void right() {
    		if (x < 5) x++;
    	}
    	void left() {
    		if (x > -5) x--;
    	}
    	@Override
    	public String toString() {
    		return "(x, y) : (" + x + ", " + y + ")";
    	}
    	public Point clone() throws CloneNotSupportedException {
    		return (Point) super.clone();
    	}
    }
    class Passed {
    	HashSet<String> mapData;
    	public Passed() {
    		this.mapData = new HashSet<>();
    	}
    	public void movePoint(Point p1, Point p2) {
    		Line line = new Line(p1, p2);
    		if (line.hasLine())
    			this.mapData.add(line.toString());
    	}
    	public int getLineNum() {
    		return this.mapData.size();
    	}
    }
}