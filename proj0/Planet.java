public class Planet{

  public double xxPos; //Its current x position
  public double yyPos; //Its current y position
  public double xxVel; //Its current velocity in the x direction
  public double yyVel; //Its current velocity in the y direction
  public double mass; //Its mass
  public String imgFileName; //The name of the file that corresponds to the image that depicts the body

  public static final double G = 6.67e-11;

  public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
                this.xxPos = xP;
                this.yyPos = yP;
                this.xxVel = xV;
                this.yyVel = yV;
                this.mass = m;
                this.imgFileName = img;
              }

  public Planet(Planet p){
    this.xxPos = p.xxPos;
    this.yyPos = p.yyPos;
    this.xxVel = p.xxVel;
    this.yyVel = p.yyVel;
    this.mass = p.mass;
    this.imgFileName = p.imgFileName;
  }
  /* 两星球间的距离 */
  public double calcDistance(Planet other){
    double xxdiff = other.xxPos - this.xxPos;
    double yydiff = other.yyPos - this.yyPos;
    return Math.sqrt(xxdiff * xxdiff + yydiff * yydiff);

  }
  /* 两星球间的引力 */
  public double calcForceExertedBy(Planet other){
    double dist = this.calcDistance(other);
    return G * this.mass * other.mass / (dist * dist);

  }
  /* 两星球间的横向作用力  Fx=F*dx/r */
  public double calcForceExertedByX(Planet other){
      double forceX =  calcForceExertedBy(other)*(other.xxPos - this.xxPos)/calcDistance(other);
      return forceX;
  }
  /* 两星球间的横向作用力  Fx=F*dy/r */
  public double calcForceExertedByY(Planet other){
    double forceY =  calcForceExertedBy(other)*(other.yyPos - this.yyPos)/calcDistance(other);
    return forceY;
  }

  /*  横向作用力之和   */
  public double calcNetForceExertedByX(Planet[] planets){
    double netForceX = 0.0;
    for (Planet p : planets){
      if (this.equals(p)){
        continue;
      }
      netForceX = netForceX + this.calcForceExertedByX(p);
    }
    return netForceX;
  }

  /*  纵向作用力之和   */
  public double calcNetForceExertedByY(Planet[] planets){
    double netForceY = 0.0;
    for (Planet p : planets){
      if (this.equals(p)){
        continue;
      }
      netForceY = netForceY + this.calcForceExertedByY(p);
    }
    return netForceY;
  }

  /* 运动 更新速度和位置 */
  public void update (double time, double forceX, double forceY){
    double ax = forceX / this.mass;  //计算横向加速度
    double ay = forceY / this.mass; //计算纵向加速度
    this.xxVel = this.xxVel + time * ax;
    this.yyVel = this.yyVel + time * ay;
    this.xxPos = this.xxPos + time * this.xxVel;
    this.yyPos = this.yyPos + time * this.yyVel;
  }

  public void draw(){
    StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
  }

}
