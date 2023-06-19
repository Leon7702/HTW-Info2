package lab10;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
	
	//getting the max screensize 
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int wHeight = (int) screenSize.getHeight();
    private static int wWidth;
    private JPanel canvas;
    private static final int stopCriterion = 10; // stopping criterion for recursion
    private static int A_x, A_y, B_x, B_y, C_x, C_y;

    private int depth; //counts the recurisve calls
    
    private Color[] colors = { Color.WHITE, Color.BLACK, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.CYAN, Color.PINK,
            Color.YELLOW, Color.RED, Color.WHITE, Color.BLUE };

    
    
    public Main() {
        super("Sierpinski");

        // Calculate initial width based on the initial height
        wWidth = (int) (2 * wHeight / (Math.sqrt(3)));

        //setting the size of the window
        setSize(wWidth, wHeight);
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a canvas panel and add it to the frame
        canvas = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                //calls the draw method to start recursion 
                draw(new Point(A_x, A_y), new Point(B_x, B_y), new Point(C_x, C_y), g);
            }
            
        };
        
        //adds the canvas to the component
        add(canvas);
        
        //displays it in the middle of the display
        setLocationRelativeTo(canvas);

        setVisible(true);

        // Add a component listener to handle window size changes
        // Method gets called anyways, because it is in Main constructor
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Calculate new width and height based on the maximum height of the window
                wHeight = getContentPane().getHeight();
                wWidth = (int) (2 * wHeight / (Math.sqrt(3)));
                
             // Setting or updating the coordinates of the triangle
                A_x = wWidth / 2;
                A_y = 0;

                B_x = 0;
                B_y = wHeight;

                C_x = wWidth;
                C_y = wHeight;
                
             // Set the preferred size of the canvas to maintain the aspect ratio
                canvas.setPreferredSize(new Dimension(wWidth, wHeight));
                pack(); // Pack the frame to adjust its size to the preferred size


                // Repaint the canvas when the window is resized
                canvas.repaint();
            }
        });
    }
    
    
    
    

    // method that computes the midpoint from 2 given points
    public Point getMiddle(Point A, Point B) {
    	//calculates new X value und new Y value of middlepoint and retuns point
        return new Point((int) (A.getX() + B.getX()) / 2, (int) (A.getY() + B.getY()) / 2);
    }
    
    
    
    

    public void draw(Point A, Point B, Point C, Graphics g) {
    	
        // Termination condition
        if (A.distance(B) < stopCriterion && A.distance(C) < stopCriterion && B.distance(C) < stopCriterion)
            return; // Stop recursion
        
        // Draw the current triangle
        //getting the values for X and Y of the 3 points.
        int[] xpoints = { (int) A.getX(), (int) B.getX(), (int) C.getX() }; // Top, Right, Left
        int[] ypoints = { (int) A.getY(), (int) B.getY(), (int) C.getY() };
       
        
        g.drawPolygon(new Polygon(xpoints, ypoints, 3));
        g.setColor(colors[depth % colors.length]);
        g.fillPolygon(xpoints, ypoints, 3);

        
        //Set the middle points calling our getMiddle method
        Point midAB = getMiddle(A, B);
        Point midBC = getMiddle(B, C);
        Point midCA = getMiddle(C, A);
        
        //Adding +1 because we are entering the recursive call 
        depth++;

        // Recursive calls - Recursively draw the 3 smaller corner triangles
        draw(A, midAB, midCA, g);
        draw(B, midBC, midAB, g);
        draw(C, midCA, midBC, g);

        //we only want to increment when entering a new level, not each time of recursive call method - that why we have to subtract one
        depth--;
        
    } 

    public static void main(String[] args) {
        new Main();
    }        
}



