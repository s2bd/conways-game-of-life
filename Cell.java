
/**
 * A cell in a 2D array for simulating Conway's Game Of Life
 *
 * @author Dewan Mukto
 * @version 2023 Jan 20
 */
public class Cell
{
    private boolean alive;

    public Cell()
    {
        this.alive = false;
    }

    public void die(){this.alive = false;}
    public void live(){this.alive = true;}
}