import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Life here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lives extends Actor
{
    private int imageCounter = 3;
    private GreenfootImage lives = new GreenfootImage("life.PNG");

    /**
     * Lives will scale the image and display a picture for the class
     * @param There is no paramters
     * @return lives(oneUp mushroom) is returned 
     */
    public Lives()
    {
        lives.scale(19, 19);
        setImage(lives);

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
