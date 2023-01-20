import java.util.Random;
import javax.swing.Timer;

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
    // Global variables
    private Cell[][] current;    // array of current cell states
    private Cell[][] previous;   // array of previous cell states
    private int dimensions = 80;     // height and width of the visual grid
    private int iterations;     // limit for the maximum iterations in a simulation
    private Timer timer;

    /**
     * Launcher for the simulator
     *
     * @param  m  the maximum number of iterations to run the simulation for, for an integer value >= 0
     * @param  type the type of the simulation initialization, R for Random, J for Jam, D for Dart
     * @return    runs the simulation
     */
    public static void main(String[] args)
    {
        if(args.length<=1 || args.length>2){
            System.out.println("Wrong number of inputs. Please use this syntax:\n java Sim134 [ITERATIONS] [TYPE]");
            System.exit(-1);
        } else {
            System.out.println("You have entered:\nIterations: "+args[0]+"\nType: "+args[1]+"\n");
            Sim134 simulator = new Sim134(Integer.parseInt(args[0]), args[1]);
        }
    }

    /**
     * Constructor for the simulator
     */
    public Sim134(int m, String type)
    {
        // Instance variables
        this.iterations = m; // maximum iterations, user-defined
        System.out.println("Max iterations: "+m);

        if(type.toLowerCase().equals("r")){
            // Initializing the grid with dead cells by default
            for(int x=0;x<dimensions;x++){
                for(int y=0;y<dimensions;y++){
                    current[x][y] = new Cell();
                    System.out.println("Generated cell at x="+x+", y="+y);
                }
                System.out.println("Cells ready");
            }
            // Random
            for(int x=0;x<dimensions;x++){
                for(int y=0;y<dimensions;y++){
                    if(x / 2 == 0){
                        current[x][y].live();
                    } else {current[x][y].die();}
                }
            }
            timer = new Timer(100, t -> heartbeat()); // Run simulation
        } else if(type.toLowerCase().equals("j")){
            // Oscillator Jam
            timer = new Timer(100, t -> heartbeat()); // Run simulation
        } else if(type.toLowerCase().equals("d")){
            // Dart Glider
            timer = new Timer(100, t -> heartbeat()); // Run simulation
        } else {
            // Exceptions
            System.out.println("Sorry, you can only choose between R for Random, J for Jam or D for Dart as the simulation types. Please try again.");
            //System.exit(-1);
        }
    }

    public void heartbeat(){
        stepSim(iterations);
    }

    public void stepSim(int max){
        for(int t=0; t<=max; t++){

        }
    }
}
