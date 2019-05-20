package com.mahal.simulation;

/**  les 8 directions 0 ->(-1,-1), 1->(-1,0),...
 * <li>   0    7    6
 * <li>
 * <li>      \ | /
 * <li>   1     -    5
 * <li>      / | \
 * <li>
 * <li>   2    3    4
 */
public class Dir {

private int  coded; // entier entre 0 et 7
private static int[] ddx = {-1, -1, -1,  0,  1, 1, 1, 0};
private static int[] ddy = { 1,  0, -1, -1, -1, 0, 1, 1};
  /**************************************************************
  * constructeur construit une direction dont on donne le code
  * @param cd le code de la direction entre 0 et 7
  * ***********************************************************/
public Dir(int cd){
  if (cd < 0 || cd > 7) {
    System.out.println("La valeur rentrée est incorrecte");
    this.coded = 0;
  }
  this.coded = cd;
}
  /****************************************************
  * construit une direction al�atoire entre 0 et 7
  * *************************************************/
public Dir(){
    this.coded = (int) (Math.random() * 7);

}
/************************************************************
 * construit la direction pour aller de p1 � p2
 * @param p1 position de depart
 * @param p2 position arrivee
 *************************************************************/
public Dir(Pos p1, Pos p2){
  int x = p2.getX() - p1.getX();
  int y = p2.getY() - p1.getY();
  int i = 0;
  coded = i;
  double vc = Math.acos(cos(x,y,ddx[i],ddy[i]));
  for (i=1; i<8;i++){
    if (Math.acos(cos(x,y,ddx[i],ddy[i])) < vc){
      vc = Math.acos(cos(x,y,ddx[i],ddy[i]));
      coded = i;
    }
  }
}
  /*************************************************
  * transforme en String
  * @return l'objet Dir  transform� en String
  * **********
 * @param p1 position de depart
 * @param p2 position arrivee
 ***********************************************************************/
public String toString(){
  return "Dir : "+coded + "\nddx:" + this.dx() + "ddy:" + this.dy();
}
  /*************************************************
  * accesseur qui rend la composante x de la direction
  * @return la composante x (-1,0 ou 1)
  * ***********************************************/
public int dx(){
  return ddx[coded];
}
 /*************************************************
  * accesseur qui rend la composante y de la direction
  * @return la composante y (-1, 0 ou 1)
  * ***********************************************/
public int dy(){
  return ddy[coded];
}
 /*********************************************************************
  * rend une direction voisine al�atoire sans changer la direction
  * courante :
  * @return la position voisine
  * ******************************************************************/
public static Dir DirAleatoire(){
    return new Dir();
}
   /*********************************************************************
  * rend une direction opposee � la direction courante
  * @return la position voisine
  * ******************************************************************/
 public Dir  dirOppose(){
  int dop = (coded+4)%8;
  return new Dir(dop);
}

public static Dir dirVoisine(Dir d) {
     int debug = Math.floorMod((d.coded +  (int)(Math.random() * 3) - 1), 8);
    return new Dir(debug);
}
 /***************************************************************
   * donne la position voisine de la Direction indiqu�e
   * @param p la position
   * @return la position
   * ***************************************************************/
 public Pos posVoisine(Dir d){
   Dir next = dirVoisine(d);
   return (new Pos(ddx[next.dx()], ddy[next.dy()]));
 }
 public Dir getNormalDirection() {
     return (new Dir(Math.floorMod(coded - 3, 8)));
 }
/* ce qui suit peut sans doute �tre d�port� dans
 * une boite � outils
 */
private  double ps(int a, int b, int c, int d){
  return a*c + b*d;
}

public  double norme(int a,int b){
  return Math.sqrt(a*a+b*b);
}

public  double cos(int a, int b, int c, int d){
  return ps(a,b,c,d)/(norme(a,b)*norme(c,d));
}


}

