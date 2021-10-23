
//import java.awt.Color;


public class JuliaSetGenerator{
  public static void main(String[] args){
    StdDraw.setCanvasSize(800,300);
    StdDraw.setXscale(0,800);
    StdDraw.setYscale(0,300);
    StdDraw.enableDoubleBuffering();



    ComplexNumber origin = new ComplexNumber();
    ComplexNumber Q1 = new ComplexNumber(1, 0.75);
    ComplexNumber Q2 = new ComplexNumber(-1, 0.75);
    ComplexNumber Q3 = new ComplexNumber(-1, -0.75);
    ComplexNumber Q4 = new ComplexNumber(1, -0.75);

    DisplayWindow leftW = new DisplayWindow(0,0,400,300);
    DisplayWindow rightW = new DisplayWindow(400,0,400,300);

    boolean leftClicked = true;//boolean for which screen is zooming in/out

    double newA, newB;//newCenter = ComplexNumber(newA, newB)
    ComplexNumber newCenter;//new center of the mandelbrot/juliaset controlled by arrowkeys


    //test map
    //StdDraw.setPenColor(StdDraw.BLACK);
    //StdDraw.filledCircle(leftW.mapX(origin.getReal()), leftW.mapY(origin.getImaginary()), 5);
    //StdDraw.filledCircle(rightW.mapX(origin.getReal()), rightW.mapY(origin.getImaginary()), 5);
    //StdDraw.setPenColor(StdDraw.BLUE);
    //StdDraw.filledCircle(leftW.mapX(Q1.getReal()), leftW.mapY(Q1.getImaginary()), 5);
    //StdDraw.filledCircle(rightW.mapX(Q1.getReal()), rightW.mapY(Q1.getImaginary()), 5);
    //StdDraw.setPenColor(StdDraw.CYAN);
    //StdDraw.filledCircle(leftW.mapX(Q2.getReal()), leftW.mapY(Q2.getImaginary()), 5);
    //StdDraw.filledCircle(rightW.mapX(Q2.getReal()), rightW.mapY(Q2.getImaginary()), 5);
    //StdDraw.setPenColor(StdDraw.GREEN);
    //StdDraw.filledCircle(leftW.mapX(Q3.getReal()), leftW.mapY(Q3.getImaginary()), 5);
    //StdDraw.filledCircle(rightW.mapX(Q3.getReal()), rightW.mapY(Q3.getImaginary()), 5);
    //StdDraw.setPenColor(StdDraw.MAGENTA);
    //StdDraw.filledCircle(leftW.mapX(Q4.getReal()), leftW.mapY(Q4.getImaginary()), 5);
    //StdDraw.filledCircle(rightW.mapX(Q4.getReal()), rightW.mapY(Q4.getImaginary()), 5);


    //double stepSize = 0.1;
    //for(double a=leftW.getRMin(); a<leftW.getRMax(); a+=stepSize){
    //  for(double b=leftW.getIMin(); b<leftW.getIMax(); b+=stepSize){
    //    ComplexNumber c = new ComplexNumber(a,b);
    //    StdDraw.setPenColor(StdDraw.GRAY);
    //    StdDraw.filledCircle(leftW.mapX(c.getReal()), leftW.mapY(c.getImaginary()), 1);
    //  }
    //}


    drawMandelbrotSet(leftW, 0.01);//draws mandelbrotset for the first time at standard position once






    StdDraw.show();

    while(true){//shows the mouse coordinate in the LEFTWINDOW as a complex number
      ComplexNumber mouse = new ComplexNumber();//mouse coordinate for left window, mandelbrot
      ComplexNumber mouseRightW = new ComplexNumber();//mouse coordinate for right window, juliaset
      if(StdDraw.mouseX()<400){
        StdDraw.setPenColor(100,100,100);
        StdDraw.filledRectangle(320,20,75,15);
        StdDraw.setPenColor(0,0,0);
        double mouseA = leftW.mapA(StdDraw.mouseX());
        double mouseB = leftW.mapB(StdDraw.mouseY());
        mouse = new ComplexNumber(mouseA,mouseB);
        StdDraw.text(320, 20, "" + mouse);
      }
      else{
        StdDraw.setPenColor(100,100,100);
        StdDraw.filledRectangle(720,20,75,15);
        StdDraw.setPenColor(0,0,0);
        double mouseAR = rightW.mapA(StdDraw.mouseX());
        double mouseBR = rightW.mapB(StdDraw.mouseY());
        mouseRightW = new ComplexNumber(mouseAR,mouseBR);
        StdDraw.text(720, 20, "" + mouseRightW);
      }


      if(StdDraw.isKeyPressed(73)){//zoom in
        //StdDraw.pause(10);
        System.out.println('i');

        if(leftClicked==true){
          leftW.zoomIn();
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(200,150,200,150);
          drawMandelbrotSet(leftW, leftW.getRMax()/400);
          System.out.print(leftW.getCenter());
        }
        else{
          rightW.zoomIn();
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(600,150,200,150);
          drawJuliaSet(rightW, rightW.getRMax()/400, leftW.getCenter());
        }
      }

      else if(StdDraw.isKeyPressed(79)){//zoom out
        //StdDraw.pause(10);
        System.out.println('o');
        if(leftClicked==true){
          leftW.zoomOut();
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(200,150,200,150);
          drawMandelbrotSet(leftW, leftW.getRMax()/400);
          System.out.print(leftW.getCenter());
        }
        else{
          rightW.zoomOut();
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(600,150,200,150);
          drawJuliaSet(rightW, rightW.getRMax()/400, leftW.getCenter());
        }
      }

      else if(StdDraw.isKeyPressed(82)){
        //StdDraw.pause(10);
        System.out.println('r');
        if(leftClicked==true){
          leftW.resetView();
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(200,150,200,150);
          drawMandelbrotSet(leftW, leftW.getRMax()/400);
          //System.out.print(leftW.getCenter());
        }
        else{
          rightW.resetView();
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(600,150,200,150);
          drawJuliaSet(rightW, rightW.getRMax()/400, leftW.getCenter());
        }

      }

      else if(StdDraw.mousePressed()){

        //selecting screen to zoom in/out *********CHECK
        if(StdDraw.mouseX()<400){
          leftClicked = true;
          //recenter mandelbrot
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(200,150,200,150);
          //StdDraw.pause(10);
          System.out.println('c');
          //System.out.print(leftW.getCenter());
          leftW.recenter(mouse);
          drawMandelbrotSet(leftW, leftW.getRMax()/400);


          //draw Juliaset on the right-hand screen
          drawJuliaSet(rightW, rightW.getRMax()/400, leftW.getCenter());
        }
        else{
          leftClicked = false;
          //recenter Juliaset
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(600,150,200,150);
          //StdDraw.pause(10);
          System.out.println("cr");
          rightW.recenter(mouseRightW);
          drawJuliaSet(rightW, rightW.getRMax()/400, leftW.getCenter());

        }

      }


      else if(StdDraw.isKeyPressed(37)){//left arrow key, move center left
        System.out.println("left arrow");
        if(leftClicked==true){
          newA = leftW.getCenter().getReal()+leftW.getRMax()/2;
          newB = leftW.getCenter().getImaginary();
          newCenter = new ComplexNumber(newA, newB);
          leftW.recenter(newCenter);
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(200,150,200,150);
          drawMandelbrotSet(leftW, leftW.getRMax()/400);
          System.out.print(leftW.getCenter());
        }
        else{
          newA = rightW.getCenter().getReal()+rightW.getRMax()/2;
          newB = rightW.getCenter().getImaginary();
          newCenter = new ComplexNumber(newA, newB);
          rightW.recenter(newCenter);
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(600,150,200,150);
          drawJuliaSet(rightW, rightW.getRMax()/400, leftW.getCenter());
        }
      }

      else if(StdDraw.isKeyPressed(39)){//right arrow key, move center right
        System.out.println("right arrow");
        if(leftClicked==true){
          newA = leftW.getCenter().getReal()-leftW.getRMax()/2;
          newB = leftW.getCenter().getImaginary();
          newCenter = new ComplexNumber(newA, newB);
          leftW.recenter(newCenter);
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(200,150,200,150);
          drawMandelbrotSet(leftW, leftW.getRMax()/400);
          System.out.print(leftW.getCenter());
        }
        else{
          newA = rightW.getCenter().getReal()-rightW.getRMax()/2;
          newB = rightW.getCenter().getImaginary();
          newCenter = new ComplexNumber(newA, newB);
          rightW.recenter(newCenter);
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(600,150,200,150);
          drawJuliaSet(rightW, rightW.getRMax()/400, leftW.getCenter());
        }
      }

      else if(StdDraw.isKeyPressed(38)){//up arrow key, move center up
        System.out.println("up arrow");
        if(leftClicked==true){
          newA = leftW.getCenter().getReal();
          newB = leftW.getCenter().getImaginary()-leftW.getIMax()/2;
          newCenter = new ComplexNumber(newA, newB);
          leftW.recenter(newCenter);
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(200,150,200,150);
          drawMandelbrotSet(leftW, leftW.getRMax()/400);
          System.out.print(leftW.getCenter());
        }
        else{
          newA = rightW.getCenter().getReal();
          newB = rightW.getCenter().getImaginary()-rightW.getIMax()/2;
          newCenter = new ComplexNumber(newA, newB);
          rightW.recenter(newCenter);
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(600,150,200,150);
          drawJuliaSet(rightW, rightW.getRMax()/400, leftW.getCenter());
        }
      }

      else if(StdDraw.isKeyPressed(40)){//down arrow key, move center down
        System.out.println("down arrow");
        if(leftClicked==true){
          newA = leftW.getCenter().getReal();
          newB = leftW.getCenter().getImaginary()+leftW.getIMax()/2;
          newCenter = new ComplexNumber(newA, newB);
          leftW.recenter(newCenter);
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(200,150,200,150);
          drawMandelbrotSet(leftW, leftW.getRMax()/400);
          System.out.print(leftW.getCenter());
        }
        else{
          newA = rightW.getCenter().getReal();
          newB = rightW.getCenter().getImaginary()+rightW.getIMax()/2;
          newCenter = new ComplexNumber(newA, newB);
          rightW.recenter(newCenter);
          StdDraw.setPenColor(255,255,255);
          StdDraw.filledRectangle(600,150,200,150);
          drawJuliaSet(rightW, rightW.getRMax()/400, leftW.getCenter());
        }
      }



      StdDraw.show();
    }

  }

  /**
    Determines if the ComplexNumber c is in the Mandelbrot Set or not
    @param Zn the starting ComplexNumber (0,0)
    @param c the ComplexNumber in question
    @param n number of times recursive is repeated, the nth Z
    @return int 255 if the ComplexNumber c is in the set, <255 if c is out of the set.
  **/


  public static int countTerms(ComplexNumber Zn, ComplexNumber c, int n){
    n++;
    if(Zn.magnitude()>2 || n==255){
      return n;
    }

    else{
      return countTerms(Zn.square().add(c), c, n);
    }
  }


  /**
    Draw the Mandelbrot Set
    @param leftW the window used to draw the mandelbrot set
    @param stepAdjuster the spaces between each point checked
  **/

  public static void drawMandelbrotSet(DisplayWindow leftW, double stepAdjuster){
    double stepSize = stepAdjuster;
    for(double a=leftW.getRMin(); a<leftW.getRMax(); a+=stepSize){
      for(double b=leftW.getIMin(); b<leftW.getIMax(); b+=stepSize){
        ComplexNumber c = new ComplexNumber(a,b);
        int count = countTerms(new ComplexNumber(), c, 0);
        if(count==255){
          StdDraw.setPenColor(StdDraw.BLACK);
        }
        else if(count<3){
          StdDraw.setPenColor(StdDraw.BLUE);
        }
        else if(count<4){
          StdDraw.setPenColor(StdDraw.CYAN);
        }
        else if(count<5){
          StdDraw.setPenColor(StdDraw.GREEN);
        }
        else if(count<7){
          StdDraw.setPenColor(StdDraw.MAGENTA);
        }
        else if(count<12){
          StdDraw.setPenColor(StdDraw.ORANGE);
        }
        else if(count<18){
          StdDraw.setPenColor(StdDraw.PINK);
        }
        else if(count<25){
          StdDraw.setPenColor(StdDraw.YELLOW);
        }
        else if(count<30){
          StdDraw.setPenColor(StdDraw.RED);
        }
        else if(count<50){
          StdDraw.setPenColor(StdDraw.DARK_GRAY);
        }
        else{
          StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        }

        StdDraw.filledCircle(leftW.mapX(c.getReal()), leftW.mapY(c.getImaginary()), 1);
    //System.out.println((countTerms(origin, c, 0)));

      }
    }
  }

  /**
    Draw the Julia Set
    @param rightW the window used to draw the mandelbrot set
    @param stepAdjuster the spaces between each point checked
    @param pointClicked the point that was clicked on the Mandebrot Set to derive the Julia Set
  **/
  
  public static void drawJuliaSet(DisplayWindow rightW, double stepAdjuster, ComplexNumber pointClicked){
    double stepSize = stepAdjuster;
    for(double a=rightW.getRMin(); a<rightW.getRMax(); a+=stepSize){
      for(double b=rightW.getIMin(); b<rightW.getIMax(); b+=stepSize){
        ComplexNumber c = new ComplexNumber(a,b);
        int count = countTerms(c, pointClicked, 0);
        if(count==255){
          StdDraw.setPenColor(StdDraw.BLACK);
        }
        else if(count<3){
          StdDraw.setPenColor(StdDraw.BLUE);
        }
        else if(count<4){
          StdDraw.setPenColor(StdDraw.CYAN);
        }
        else if(count<5){
          StdDraw.setPenColor(StdDraw.GREEN);
        }
        else if(count<7){
          StdDraw.setPenColor(StdDraw.MAGENTA);
        }
        else if(count<12){
          StdDraw.setPenColor(StdDraw.ORANGE);
        }
        else if(count<18){
          StdDraw.setPenColor(StdDraw.PINK);
        }
        else if(count<25){
          StdDraw.setPenColor(StdDraw.YELLOW);
        }
        else if(count<30){
          StdDraw.setPenColor(StdDraw.RED);
        }
        else if(count<50){
          StdDraw.setPenColor(StdDraw.DARK_GRAY);
        }
        else{
          StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        }

        StdDraw.filledCircle(rightW.mapX(c.getReal()), rightW.mapY(c.getImaginary()), 1);
    //System.out.println((countTerms(origin, c, 0)));

      }
    }
  }


}//ENDS CLASS
