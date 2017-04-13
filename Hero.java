import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Actor
{
    private GreenfootImage original = new GreenfootImage( "Hero.png" );
    private GreenfootImage jumping = new GreenfootImage( "Hero_Jumping.png" );
    private GreenfootImage bigMario = new GreenfootImage( "Hero.png" );
    private GreenfootImage bigJump = new GreenfootImage( "Hero_Jumping.png" );
    private GreenfootImage fireMario = new GreenfootImage( "fireMario.png" );
    private GreenfootImage fireMarioJumping = new GreenfootImage( "fireMarioJumping.png" );

    private int y = 0;
    private int ySpeed = 1;
    private int smallUp = -9;
    private int up = -15;
    private boolean cannotJump = false;
    private boolean lookingRight = true;
    private int live = 1;
    private int reloadTimer = 25;
    private int marioSz = 1; //Number will change to 1,2,3 depending on different interactions to change
    //the Heros appearance  
    /**
     * Hero will cunstuct a Hero to scale and the right direction 
     * @param There is no paramters
     * @return Hero is returned 
     */  
    public Hero()
    {
        original.scale(30, 30);
        jumping.scale(32, 32);
        original.mirrorHorizontally();
        setImage(original);
        bigMario.scale(36,36);
        bigJump.scale(38,38);
        bigMario.mirrorHorizontally();
        fireMario.scale(36,36);
        fireMarioJumping.scale(38,38);
    }

    /**
     * act handles the actions must be taken everytime the program is run 
     * @param There is no paramters
     * @return Nothing is returned 
     */
    public void act() 
    {
        movement();

        checkCollision();

        ScrollerWorld myWorld = (ScrollerWorld)getWorld();
        if ( marioSz ==3 )//if marioSz = 3, then only will the Hero be able to throw fireBalls
        {

            if( Greenfoot.isKeyDown("space") )
            {
                if(reloadTimer >= 25)
                {
                    if( lookingRight == true ) 
                    {
                        myWorld.addObject(new fireBall(), getX() + 30, getY());
                        reloadTimer = 0;
                    }
                    else
                    { 
                        myWorld.addObject(new fireBall(), getX() - 30, getY());
                        reloadTimer = 0;
                    }
                }
                reloadTimer++;
            }

        }

    }

    /**
     * movement will determine which way to move, rotate, and the Hero's apperance  
     * @param There is no paramters
     * @return Nothing is returned 
     */
    private void movement()
    {
        if( Greenfoot.isKeyDown("right") )
        { 

            if (lookingRight == false)
            {
                original.mirrorHorizontally();
                jumping.mirrorHorizontally();
                bigMario.mirrorHorizontally();
                bigJump.mirrorHorizontally();
                fireMario.mirrorHorizontally();
                fireMarioJumping.mirrorHorizontally();
            }

            lookingRight = true; 
            setLocation( getX()+3, getY() );

        }

        if( Greenfoot.isKeyDown("left") )
        { 

            if (lookingRight == true)
            {
                original.mirrorHorizontally(); 
                jumping.mirrorHorizontally();
                bigMario.mirrorHorizontally();
                bigJump.mirrorHorizontally();          
                fireMario.mirrorHorizontally();        
                fireMarioJumping.mirrorHorizontally();
            }

            lookingRight = false; 
            setLocation( getX()-3, getY() );        

        }

        if( Greenfoot.isKeyDown("up") )
        { 

            if (cannotJump == false)
            {

                if(marioSz == 1)
                {
                    setImage(jumping);
                }
                else if(marioSz == 2)
                {
                    setImage(bigJump);
                }
                else if(marioSz == 3)
                {
                    setImage(fireMarioJumping);
                }
                y = up;
                fall();

            }

        }

        if( getY() >= 360 )
        { 
            setLocation( getX(), 370 );
            y = 0; 
        }

    }

    /**
     * fall will make the fireBall fall to the platform when its in the air  
     * @param There is no paramters
     * @return Nothing is returned 
     */
    private void fall()
    {
        cannotJump = true;
        setLocation( getX(), getY()+y ); 
        y = y + ySpeed;
    }

    /**
     * -- checkCollision will determine how to react when the Hero comes in contact with different classes,
     * will change the healthbar by a value of +/- 25 if it touches 1up mushroom or goomba
     * 
     * @param There is no paramters
     * @return Nothing is returned
     */
    private void checkCollision()
    {
        ScrollerWorld myWorld = (ScrollerWorld)getWorld();
        HealthBar bar = getWorld().getObjects(HealthBar.class).get(0);

        if(getOneObjectAtOffset(0, getImage().getHeight()-15, Enemy.class) != null)
        {
            getWorld().removeObject(getOneObjectAtOffset(0, getImage().getHeight()-15, Enemy.class));
            myWorld.addToScore(); 
            y = smallUp;// will make the Hero bounce off the enemy 
            fall();// will bring the Hero back to the Platform
        }
        else if( isTouching(Mushroom.class) )
        {
            getWorld().removeObject(getOneIntersectingObject(Mushroom.class) );

            if(marioSz == 3)//if statment was left empty so when marioSz = 3 and comes in contact with a
            {               //it wont react
            }
            else
            {    
                setImage(bigMario);//Will increase the Sz of the Hero
                marioSz = 2;
            }

        }        
        else if( isTouching(Enemy.class))
        {

            if(marioSz == 3)//Will set the Hero back to its original apperance and mamke marioSz = 1
            {                
                getWorld().removeObject(getOneIntersectingObject(Enemy.class) );
                setImage(original);
                marioSz = 1;
            }
            else if(marioSz == 2)//Will set the Hero back to its original apperance and mamke marioSz = 1
            {                
                getWorld().removeObject(getOneIntersectingObject(Enemy.class) );
                setImage(original);
                marioSz = 1;
            }            
            else if(marioSz == 1)//If Hero = 1 and touches Enemy he will lose a life 
            {    
                bar.add(-25);
                live--;
                myWorld.subtractToLives();
                getWorld().removeObject(getOneIntersectingObject(Enemy.class) );
            }

            //if(live == 0)// If live = 0 it will call for the gameOver method ScrollerWorld
            //{
            //    myWorld.gameOver();             
            //}

        }
        else if(getOneObjectAtOffset(0, getImage().getHeight()-15, Platform.class) != null)
        {
            //This else if statment will allow the Hero to junp again and set the Hero to the correct appearance
            if(marioSz == 1)
            {
                setImage(original);
            }
            else if(marioSz == 2)
            {
                setImage(bigMario);
            }
            else if(marioSz == 3)
            {
                setImage(fireMario);
            }            
            cannotJump = false;
            y = 0;

        }
        else//if nothing else happens he will fall to the Platform
        {
            fall();
        }

        if(getOneObjectAtOffset(0, getImage().getHeight()-15, Block.class) != null)
        {
            //This if statment will allow the Hero to junp again and set the Hero to the correct appearance
            if(marioSz == 1)
            {
                setImage(original);
            }
            else if(marioSz == 2)
            {
                setImage(bigMario);
            }
            else if(marioSz == 3)
            {
                setImage(fireMario);
            }
            cannotJump = false;
            y = 0;

        }
        else if(isTouching(Block.class))
        {
            fall();
        }

        if( isTouching(Lives.class))
        {
            getWorld().removeObject(getOneIntersectingObject(Lives.class) );
            myWorld.addToLives();
            live++; // will increase amt of lives
            bar.add(+25);
        }

        if( isTouching(Flower.class))
        {
            getWorld().removeObject(getOneIntersectingObject(Flower.class) );
            marioSz = 3;
            setImage(fireMario);
        }
    }
}
