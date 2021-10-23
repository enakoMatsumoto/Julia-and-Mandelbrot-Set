public class DisplayWindow{
  private double leftX, bottomY; //The position of the DisplayWindow
  private double length, width; //The length along the x-axis and width along the y-axis of the DisplayWindow
  private double rMax, rMin; //The min and max values on the real axis. Starts at -2 ≤ a ≤ 2
  private double iMax, iMin; //The min and max values on the imaginary axis. Starts at-1.5 ≤ a ≤ 1.5
  private double zoomFactor; //Keeps track of the zoom ratio. Starts at 1. Increases/decreases according to “i”/“I” and “o”/“O” key presses.
  private ComplexNumber center;  //starts at 0+0i. Changes according to mouse clicks on the DisplayWindow

  /**
	 * Creates a new DisplayWindow
	 * @param leftX x-coordinate of the bottom left corner of the DisplayWindow
	 * @param bottomY y-coordinate of the bottom left corner of the DisplayWindow
   * @param length length of the DisplayWindow
   * @param width width of the DisplayWindow
	 */

  DisplayWindow(double leftX, double bottomY, double length, double width){
    this.leftX = leftX;
    this.bottomY = bottomY;
    this.length = length;
    this.width = width;

    resetView();
  }

  /**
    * Resets the intervals and zoom ratio of the DisplayWindow
    *
    */
  public void resetView(){
    this.rMax = 2;
    this.rMin = -2;
    this.iMax = 1.5;
    this.iMin = -1.5;
    this.zoomFactor = 1;
    this.center = new ComplexNumber();
  }

  /**
    * reduces the intervals looked at in the DisplayWindow by increasing the zoom ratio
    * to look like the window has zoomed in
    */

  public void zoomIn(){
    this.zoomFactor++;
    this.rMax = (2)/this.zoomFactor+this.center.getReal();
    this.rMin = (-2)/this.zoomFactor+this.center.getReal();
    this.iMax = (1.5)/this.zoomFactor+this.center.getImaginary();
    this.iMin = (-1.5)/this.zoomFactor+this.center.getImaginary();
  }

  /**
    * expands the intervals looked at in the DisplayWindow by decreasing the zoom ratio
    * to look like the window has zoomed out
    */

  public void zoomOut(){
    if(this.zoomFactor>1){
      this.zoomFactor--;
      this.rMax = (2)/this.zoomFactor+this.center.getReal();
      this.rMin = (-2)/this.zoomFactor+this.center.getReal();
      this.iMax = (1.5)/this.zoomFactor+this.center.getImaginary();
      this.iMin = (-1.5)/this.zoomFactor+this.center.getImaginary();
    }
  }

  /**
    * "recenters" by changing the intervals looked at in the DisplayWindow
    * and making the clicked mousepoint the center of the intervals
    */
  public void recenter(ComplexNumber center){
    this.rMax = (2/this.zoomFactor)+center.getReal();
    this.rMin = (-2/this.zoomFactor)+center.getReal();
    this.iMax = (1.5/this.zoomFactor)+center.getImaginary();
    this.iMin = (-1.5/this.zoomFactor)+center.getImaginary();
    this.center = center;
  }


  /**
	 * An "accessor" method
	 * Returns the max value of the real axis
	 * @return this.rMax, the max value of the real axis
	 */
  public double getRMax(){ return this.rMax; }

  /**
	 * An "accessor" method
	 * Returns the min value of the real axis
	 * @return this.rMin, the min value of the real axis
	 */
  public double getRMin(){ return this.rMin; }

  /**
	 * An "accessor" method
	 * Returns the max value of the imaginary axis
	 * @return this.iMax, the max value of the imaginary axis
	 */
  public double getIMax(){ return this.iMax; }

  /**
	 * An "accessor" method
	 * Returns the min value of the imaginary axis
	 * @return this.iMin, the min value of the imaginary axis
	 */
  public double getIMin(){ return this.iMin; }

  /**
	 * An "accessor" method
	 * Returns the zoom ratio, zoomFactor
	 * @return this.zoomFactor, the zoom ratio
	 */
  public double getZoomFactor(){ return this.zoomFactor; }

  /**
	 * An "accessor" method
	 * Returns ComplexNumber that is the center of the intervals displayed in the DisplayWindow
	 * @return ComplexNumber this.center
	 */
  public ComplexNumber getCenter(){ return this.center; }


  /**
    Translate a value from one range into
    a proportionally-equivalent value from another range
    @param value the value you'd like to translate from one range to another range
    @param oldMin the minimum value of the original range
    @param oldMax the maximum value of the original range
    @param newMin the minimum value of the new range
    @param newMax the maximum value of the new range

  **/

  private double map(double value, double oldMin, double oldMax, double newMin, double newMax){
  return ((value-oldMin)*(newMax-newMin)/(oldMax-oldMin))+newMin;
  }

  /**
    Translate the real component of the complex number to a
    x-value on the portion of the canvas described by the DisplayWindow

    @param a The real component of a complex Number
    @return the proportionally equivalent x-value on the portion of
            the canvas described by the DisplayWindow

  **/
  public double mapX(double a){
    return map(a, this.rMin, this.rMax, this.leftX, this.leftX+this.length);
  }

  /**
    Translate the imaginary component of the complex number to b
    y-value on the portion of the canvas described by the DisplayWindow

    @param b The imaginary component of a complex Number
    @return the proportionally equivalent y-value on the portion of
            the canvas described by the DisplayWindow

  **/

  public double mapY(double b){
    return map(b, this.iMin, this.iMax, this.bottomY, this.bottomY+this.width);
  }

  /**
    Translate the x-value on the portion of the canvas described by the DisplayWindow
    to the real component of the complex number to a

    @param windowX the x-value on the portion of the canvas described by the DisplayWindow
    @return the proportionally equivalent real component of the complex number

  **/
  public double mapA(double windowX){
    return map(windowX, this.leftX, this.leftX+this.length, this.rMin, this.rMax);
  }

  /**
    Translate the y-value on the portion of the canvas described by the DisplayWindow
    to imaginary component of the complex number to b

    @param windowY the y-value on the portion of the canvas described by the DisplayWindow
    @return the proportionally equivalent imaginary component of the complex number

  **/

  public double mapB(double windowY){
    return map(windowY, this.bottomY, this.bottomY+this.width, this.iMin, this.iMax);
  }


}
