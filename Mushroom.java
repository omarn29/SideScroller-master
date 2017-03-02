import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mushroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mushroom extends Actor
{
    private GreenfootImage mushroom = new GreenfootImage("Mushroom.png");

    /**
     * Mushroom will cunstuct a Mushroom to scale 
     * @param There is no paramters
     * @return Mushroom is returned 
     */
    public Mushroom()
    {
        mushroom.scale(25, 25);
        setImage(mushroom);
    }

    /**
     * act handles the actions must be taken everytime the program is run 
     * @param There is no paramters
     * @return Nothing is returned 
     */
    public void act() 
    {
        move(-2); 

        if(getX() <= 0)
        {
            getWorld().removeObject(this);
        } 

    }    
}
