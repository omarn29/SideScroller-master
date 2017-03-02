import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Flower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flower extends Actor
{
    private GreenfootImage flower = new GreenfootImage("Flower.png");

    /**
     * Flower will scale the image and display a picture for the class
     * @param There is no paramters
     * @return a flower is returned 
     */
    public Flower()
    {
        flower.scale(25, 25);
        setImage(flower);

    }

    /**
     * act handles the actions must be taken everytime the program is run 
     * @param There is no paramters
     * @return Nothing is returned 
     */
    public void act() 
    {
        if( Greenfoot.isKeyDown("right") )
        { 
            move(-1); 
        }              

        if(getX() <= 0)
        {
            getWorld().removeObject(this);
        }

    } 
}
