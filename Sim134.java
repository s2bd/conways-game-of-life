import java.util.Random;
import javax.swing.Timer;
import java.awt.Color;

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
    private int[][] current;    // array of current cell states
    private Cell[][] previous;   // array of previous cell states
    private int dimensions;     // height and width of the visual grid
    private int zoom = 10;      // the magnification scale of the display
    private int iterations;     // limit for the maximum iterations in a simulation
    private Timer timer;
    private Picture display;

    /**
     * Launcher for the simulator
     *
     * @param  m  the maximum number of iterations to run the simulation for, for an integer value >= 0
     * @param  type the type of the simulation initialization, R for Random, J for Jam, D for Dart
     * @return    runs the simulation
     */
    public static void main(String[] args)
    {
        // Validating input
        if(args.length<=1 || args.length>2){
            System.out.println("Wrong number of inputs. Please use this syntax:\n java Sim134 [ITERATIONS] [TYPE]");
            System.exit(-1);
        }
        // Confirming input
        System.out.println("You have entered:\nIterations: "+args[0]+"\nType: "+args[1]+"\n");
        // Generating the simulator
        Sim134 simulator = new Sim134(Integer.parseInt(args[0]));
        // Initializing the simulator
        simulator.update();
        if(args[1].toLowerCase().equals("r")){
            // Random
            simulator.randomize();
            simulator.update();
            simulator.show();}
        // } else if(args[1].toLowerCase().equals("j")){
        // // Oscillator Jam

        // simulator.show();
        // } else if(args[1].toLowerCase().equals("d")){
        // // Dart Glider

        // simulator.show();
        // } else {
        // // Exceptions
        // System.out.println("Sorry, you can only choose between R for Random, J for Jam or D for Dart as the simulation types. Please try again.");
        // System.exit(-1);
        // }
    }

    /**
     * Constructor for the simulator
     */
    public Sim134(int m)
    {
        // Instance variables
        this.iterations = m; // maximum iterations, user-defined
        System.out.println("Max iterations: "+m);
        // Initializing the grid with dead cells by default
        current = new int[80][80];
        display = new Picture(80 * zoom, 80 * zoom);
    }

    private void show(){
        display.show();
    }

    private void update(){
        for (int i = 0; i < 80; i++)
        {
            for (int j = 0; j < 80; j++)
            {
                drawCell(i,j);
            }
        }
    }

    private void randomize(){
        for (int i = 0; i < 80; i++)
        {
            for (int j = 0; j < 80; j++)
            {
                current[i][j] = (Math.random() >= 0.5) ? 1 : 0;
            }
        }
    }

    /**
     * Draws an invidual cell in a visual grid
     * 
     * @param cellX position of cell in the grid
     * @param cellY position of cell in the grid
     */
    private void drawCell(int cellX, int cellY)
    {
        Color cellcolor = current[cellX][cellY]==1 ? Color.BLACK : Color.WHITE;
        for(int xcoord=0; xcoord<zoom; xcoord++){
            for(int ycoord=0;ycoord<zoom;ycoord++){
                display.set((cellX*zoom)+xcoord, (cellY*zoom)+ycoord, cellcolor);
            }
        }
    }
}