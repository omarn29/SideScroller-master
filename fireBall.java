import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class fireBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class fireBall extends Actor
{
    private GreenfootImage fire = new GreenfootImage("fireBall.png");

    private int y = -4;
    private int ySpeed = 3;
    private int up = -8;
    private boolean cannotJump = false;
    private boolean lookingRight = true;

    /**
     * fireBall will cunstuct a fireBall to scale 
     * @param There is no paramters
     * @return fireBall is returned 
     */
    public fireBall()
    {
        fire.scale(15, 15);
        setImage(fire);

    }

    /**
     * act handles the actions must be taken everytime the program is run 
     * @param There is no paramters
     * @return Nothing is returned 
     */
    public void act() 
    {
        checkCollision();
        fall();
        movement(); 

        ScrollerWorld myWorld = (ScrollerWorld)getWorld(); 
        if( isTouching(Enemy.class))
        {
            getWorld().removeObject(getOneIntersectingObject(Enemy.class) );
            getWorld().removeObject(this);
            myWorld.addToScore();
        }
        else if(getX() >= 559)
        {
            getWorld().removeObject(this);
        } 
        else if(getX() <= 0)
        {
            getWorld().removeObject(this);
        }

    }

    /**
     * fall will make the fireBall fall to the platform when its in the air  
     * @param There is no paramters
     * @return Nothing is returned 
     */
    private void fall()
    {        
        if( lookingRight == true ) 
        {
            setLocation( getX() + 5, getY()+y );

        }    
        else if( lookingRight == false ) 
        {
            setLocation( getX() - 5, getY()+y );
        }
        cannotJump = true;
        y = y + ySpeed;
    }

    /**
     * movement will determine which way the fireBall is thrown 
     * @param There is no paramters
     * @return Nothing is returned 
     */
    private void movement()
    {
        if( Greenfoot.isKeyDown("right") )
        { 
            lookingRight = true;           
        }

        else if(Greenfoot.isKeyDown("left"))
        { 
            lookingRight = false; 
        }

    }

    /**
     * checkCollision will determine how to react when the fireBall comes in contact with different classes  
     * @param There is no paramters
     * @return Nothing is returned 
     */
    private void checkCollision()
    {

        if(isTouching(Platform.class))
        {
            y = up;
            fall();
        }

        else if(isTouching(Block.class))
        {
            y = up;
            fall();
        }
    }
}
