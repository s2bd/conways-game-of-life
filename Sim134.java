import java.util.Random;
import javax.swing.Timer;
import java.awt.Color;

/**
 * COMP3201 Assignment 01
 * A simulation of Conway's Game Of Life
 *
 * @author Dewan Mukto (dmimukto)
 * @student_id 202004321
 * @version 2023 Jan 20
 * @attribution Mark Hatcher (mhatcher)
 */
public class Sim134
{
    // Global variables
    private int[][] current;    // array of current cell states
    private int[][] previous;   // array of previous cell states
    private int dimensions;     // height and width of the visual grid
    private int zoom = 10;      // the magnification scale of the display
    private int iterations;     // limit for the maximum iterations in a simulation
    private Picture display;
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
        // Validating input
        if(args.length<=1 || args.length>2){
            System.out.println("Wrong number of inputs. Please use this syntax:\n java Sim134 [ITERATIONS] [TYPE]");
            System.exit(-1);
        } else if(!(Integer.parseInt(args[0])>=0)){
            System.out.println("Number of iterations must be >= 0!");
            System.exit(-1);
        }
        // Confirming input
        System.out.println("You have entered:\nMax Iterations: "+args[0]+"\nType: "+args[1]);
        // Generating the simulator
        Sim134 simulator = new Sim134(Integer.parseInt(args[0]),args[1]);
        System.out.println("Simulator generated!");
    }

    /**
     * Constructor for the simulator
     */
    public Sim134(int m, String type)
    {
        // Instance variables
        this.iterations = m; // maximum iterations, user-defined
        // Initializing the grid with dead cells by default
        current = new int[80][80];
        previous = new int[80][80];
        System.out.println("Cells generated.");
        display = new Picture(80 * zoom, 80 * zoom);
        System.out.println("Display ready.");
        // Initializing the simulator
        initialize();
        System.out.println("Cells initialized.");
        // CHOOSING INITIAL CONDITION TYPES
        if(type.toLowerCase().equals("r")){
            // Random
            show();
            System.out.println("Display shown.");
            randomize();
            timer = new Timer(100, tick -> pulse());
            timer.start();
        } else if(type.toLowerCase().equals("j")){
            // Oscillator Jam
            show();
            System.out.println("Display shown.");
            // Oscillator pattern
            current[14][13] = 1;
            current[14][14] = 1;
            current[14][15] = 1;
            current[16][12] = 1;
            current[17][13] = 1;
            current[18][14] = 1;
            current[15][17] = 1;
            current[16][17] = 1;
            current[17][16] = 1;
            current[17][11] = 1;
            current[18][11] = 1;
            current[19][12] = 1;
            current[19][13] = 1;
            timer = new Timer(100, tick -> pulse());
            timer.start();
        } else if(type.toLowerCase().equals("d")){
            // // Dart Glider
            show();
            System.out.println("Display shown.");
            // Glider pattern
            current[16][34] = 1;
            current[17][35] = 1;
            current[17][33] = 1;
            current[18][33] = 1;
            current[18][32] = 1;
            current[19][35] = 1;
            current[20][35] = 1;
            current[22][35] = 1;
            current[22][34] = 1;
            current[22][33] = 1;
            current[22][32] = 1;
            current[21][31] = 1;
            current[20][31] = 1;
            current[22][29] = 1;
            current[23][29] = 1;
            current[24][29] = 1;
            current[21][28] = 1;
            current[22][27] = 1;
            current[23][26] = 1;
            current[24][27] = 1;
            current[25][28] = 1;
            current[25][31] = 1;
            current[24][32] = 1;
            current[24][33] = 1;
            current[24][34] = 1;
            current[24][35] = 1;
            current[26][31] = 1;
            current[26][35] = 1;
            current[27][35] = 1;
            current[29][35] = 1;
            current[30][34] = 1;
            current[29][33] = 1;
            current[28][33] = 1;
            current[28][32] = 1;
            timer = new Timer(300, tick -> pulse());
            timer.start();
        } else {
            // Exceptions
            System.out.println("Sorry, you can only choose between\n R for Random,\n J for oscillator Jam or\n D for glider Dart as the simulation types. Please try again.");
            System.exit(-1);
        }
    }

    private void pulse(){
        update();
        iterations-=1;
        show();
        System.out.println("Iterations remaining: "+iterations);
        if(iterations<=0){timer.stop();};
    }

    private void show(){
        display.show();
    }

    private void initialize(){
        for (int i = 0; i < 80; i++)
        {
            for (int j = 0; j < 80; j++)
            {
                drawCell(i,j);
            }
        }
    }

    // Applying John Conway's Game of Life rules
    // (http://ddi.cs.uni-potsdam.de/HyFISCH/Produzieren/lis_projekt/proj_gamelife/ConwayScientificAmerican.htm)
    private void update(){
        for (int x = 0; x < 80; x++)
        {
            for (int y = 0; y < 80; y++)
            {
                previous[x][y] = current[x][y]; // storing previous state of cell
                int alives = 0;
                // A very crude way to detect number of alive neighboring cells
                if((previous[wrapcoord(x-1)][wrapcoord(y-1)] == 1)){alives+=1;}
                if((previous[wrapcoord(x-1)][wrapcoord(y)] == 1)){alives+=1;}
                if((previous[wrapcoord(x-1)][wrapcoord(y+1)] == 1)){alives+=1;}
                if((previous[wrapcoord(x)][wrapcoord(y-1)] == 1)){alives+=1;}
                if((previous[wrapcoord(x)][wrapcoord(y+1)] == 1)){alives+=1;}
                if((previous[wrapcoord(x+1)][wrapcoord(y-1)] == 1)){alives+=1;}
                if((previous[wrapcoord(x+1)][wrapcoord(y)] == 1)){alives+=1;}
                if((previous[wrapcoord(x+1)][wrapcoord(y+1)] == 1)){alives+=1;}
                // based on the number of alive cells, the individual cell's fate is decided
                if(alives>=4 || alives==1 || alives==0){
                    current[x][y] = 0; // the cell dies
                } else if(alives==3 || alives==2){
                    current[x][y] = 1; // a new cell is born
                } else {
                    current[x][y] = 1; // the cell survives
                }
                drawCell(x,y);
            }
        }
    }

    private int wrapcoord(int coord){
        // trying to wrap the 2D grid around like a 3D torus
        if(coord < 0){
            coord = 79;
            return coord;
        }
        else if(coord > 79){
            coord = 0;
            return coord;
        }
        else {return coord;}
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