public class NBody{

  public static double readRadius(String path){
    In in = new In(path);
    in.readDouble();
    double radius = in.readDouble();
    return radius;
  }

  public static Planet[] readPlanets(String path){
    In in = new In(path);
    int universeSize = in.readInt();
    Planet[] planets = new Planet[universeSize];
    in.readDouble();

    for (int i=0; i<universeSize; i++){
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();
      Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
      planets[i] = planet;

    }
    return planets;
  }

  public static void main(String[] args){
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Planet[] planets = readPlanets(filename);

   /* 开始绘制 */

  StdDraw.enableDoubleBuffering();  //prevent flickering in the animation 防止动画中的闪烁
  StdDraw.setScale(-radius, radius);
  double timer = 0.0;

  while (timer < T){
     // Sets up the universe so it goes from -radius up to radius


    double[] forceX = new double[planets.length];
    double[] forceY = new double[planets.length];

    for (int i=0 ; i< planets.length; i++){
      forceX[i] = planets[i].calcNetForceExertedByX(planets);
      forceY[i] = planets[i].calcNetForceExertedByY(planets);
    }

    for (int j=0 ; j< planets.length; j++){
      planets[j].update(dt,forceX[j], forceY[j]);
    }
    /* Draw the background image */
    StdDraw.picture(0,0, "images/starfield.jpg");

    for (Planet p : planets){
      p.draw();
    }

    /* Show the offscreen buffer */
    StdDraw.show();
    /* Pause the animation for 10 milliseconds*/
    StdDraw.pause(40);
    /* Increase your time variable */

    StdDraw.clear();

    timer = timer + dt;

  }

  /* the final state of the universe */
  StdOut.printf("%d\n", planets.length);
  StdOut.printf("%.2e\n", radius);
  for (int i = 0; i < planets.length; i++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
              }

   }

}
