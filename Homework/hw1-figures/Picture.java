
/**
 * This class represents a simple picture. You can draw the picture using
 * the draw method. But wait, there's more: being an electronic picture, it
 * can be changed. You can set it to black-and-white display and back to
 * colors (only after it's been drawn, of course).
 *
 * This class was written as an early example for teaching Java with BlueJ.
 * 
 * @author  Michael Kölling and David J. Barnes and Nicholas Chan
 * @version 1.1  (24 May 2001)
 */
public class Picture
{
    private Rect wall;
    private Rect window;
    private Rect door;
    private Rect pavement;
    private Triangle roof;
    private Circle sun;
    private boolean sunset = false;
    /**
     * Constructor for objects of class Picture
     */
    public Picture()
    {
        // nothing to do... instance variables are automatically set to null
    }

    /**
     * Draw this picture.
     */
    public void draw()
    {
        wall = new Rect(100,100, "red");
        wall.moveVertical(80);
        wall.makeVisible();

        window = new Rect(30,30, "black");
        window.moveHorizontal(20);
        window.moveVertical(100);
        window.makeVisible();
        
        door = new Rect(30,80, "blue");
        door.moveHorizontal(60);
        door.moveVertical(100);
        door.makeVisible();
        
        pavement = new Rect(600,60, "green");
        pavement.moveVertical(150);
        //pavement.moveHorizontal(0);
        pavement.makeVisible();
        
        roof = new Triangle();
        roof.changeSize(50, 140);
        roof.moveHorizontal(60);
        roof.moveVertical(70);
        roof.makeVisible();

        sun = new Circle();
        sun.changeColor("yellow");
        sun.moveHorizontal(180);
        sun.moveVertical(-10);
        sun.changeSize(60);
        sun.makeVisible();
    }

    /**
     * Change this picture to black/white display
     */
    public void setBlackAndWhite()
    {
        if(wall != null)   // only if it's painted already...
        {
            wall.changeColor("black");
            window.changeColor("white");
            roof.changeColor("black");
            sun.changeColor("black");
        }
    }

    /**
     * Change this picture to use color display
     */
    public void setColor()
    {
        if(wall != null)   // only if it's painted already...
        {
            wall.changeColor("red");
            window.changeColor("black");
            roof.changeColor("green");
            sun.changeColor("yellow");
        }
    }
   
    /**
     * Makes the sun set if the sun has already risen
     */
    public void sunset()
    {
        if(sunset == false)
        {
            sun.slowMoveVertical(150);
            sun.makeInvisible();
            sunset = true;
        }
    }
    
    /**
     * Makes the sun rise if the sun has already set
     */
    public void sunrise()
    {
        if(sunset == true)
        {
            sun.makeVisible();
            sun.slowMoveVertical(-150);
            sunset = false;
        }
    }
}
