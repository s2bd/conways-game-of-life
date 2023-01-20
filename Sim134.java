
/**
 * COMP3201 Assignment 01
 * A simulation of Conway's Game Of Life
 *
 * @author Dewan Mukto (dmimukto)
 * @version 2023 Jan 20
 * @attribution Mark Hatcher (mhatcher)
 */
public class Sim134
{
    int[][] current;    // array of current cell states
    int[][] previous;   // array of previous cell states
    int dimensions;     // height and width of the visual grid
    int iterations;     // limit for the maximum iterations in a simulation
    /**
     * Constructor for the simulator
     */
    public Sim134(int n, int m, String type)
    {
        this.dimensions = n;
        this.iterations = m;
        if(type.toLowerCase().equals("r")){
            
        } else if(type.toLowerCase().equals("j")){
        
        } else if(type.toLowerCase().equals("d")){
        
        } else {
            System.out.println("Sorry, you can only choose between R for Random, J for Jam or D for Dart as the simulation types. Please try again.");
        }
        // get values for n and m
        // initialize current[][] configuration of cells (specific or random)

        // // to run
        // for (t = 0 to max)
        // display current[][]
        // copy current[][] into previous[][]

            // // update each cell
        // for (x = 0 to n)
        // for (y = 0 to n)
        // determine the states of the neighbourhood cells, and
        // from those states determine this cell's next state
        // don't forget that the top row neighbours the bottom row
        // and that the left column neighbours the right column...

        // display current[][] one final time

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void main(String[] args)
    {
        System.out.println(args[0]+"\n"+args[1]+"\n"+args[2]);
    }
}
