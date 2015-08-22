import java.applet.*;
import java.awt.*;

import java.awt.*;

class MenuWindow extends Frame { 
	boolean inAnApplet = true;
	Graphics g;
	int last_x;
	int last_y;
	int drawing [][];
	
		
  int i,j,k;
  int pointX,pointY,pickX=-1,pickY=-1;
  int nodeStart[]={0,35,43};
  int numNodes[]={35,8,26};
  int numLayers=3;
  
  
  double charData[][]= {
    

    {1,1,1,1,1,
     1,1,1,1,1,
     1,1,1,1,1,
     1,1,1,1,1,
     1,1,1,1,1,
     1,1,1,1,1,
     1,1,1,1,1}
  };


  
 
  
  Color c[];
  Color background;
  Font myfont,myfont2;
  String outputstring;
  Button b1, b2, b3, b4,b5;
  Panel p1;
  Canvas c1;
  boolean drawBoard;
  int currentCharIndex;
  int count =0;

  public boolean action(Event e, Object arg) {
      Object target = e.target;
	  String str;
	  int i,j,xmin,xmax,ymin,ymax;
	  double max,moy,nb;

	  xmin=22;
	  ymin=30;
	  xmax=0;
	  ymax=0;
	  
	 
	
	  
	  if (target == b4) {
		  
		  for (i=0;i<22;i++){
			  for (j=0;j<30;j++) {
				  if (drawing[i][j]>0) {
					  if (i<xmin) xmin=i;
					  if (i>xmax) xmax=i;
					  if (j<ymin) ymin=j;
					  if (j>ymax) ymax=j;
				  }
			  }
		  }
		  charData[currentCharIndex] = new double[35];
		  if ((xmax>0)&&(ymax>0)) {
			  for (i=xmin;i<=xmax;i++) {
				  for (j=ymin;j<=ymax;j++) {
					  if (charData[currentCharIndex][(int)Math.ceil((i-xmin)*5/(xmax-xmin+1))+(int)(j-ymin)*7/(ymax-ymin+1)*5]<4) {
						  charData[currentCharIndex][(int)Math.ceil((i-xmin)*5/(xmax-xmin+1))+(int)(j-ymin)*7/(ymax-ymin+1)*5]+=drawing[i][j];
					  }
				  }
			  }
		  } else {
			  for (i=0;i<22;i++) {
				  for (j=0;j<30;j++) {
					  if (charData[currentCharIndex][(int)Math.ceil(i*5/22)+(int)Math.ceil(j*7/30)*5]<4) {
						  charData[currentCharIndex][(int)Math.ceil(i*5/22)+(int)Math.ceil(j*7/30)*5]+=drawing[i][j];
					  }
				  }
			  }
		  }
		  max=0;
		  nb=0;
		  moy=0;
		  for (i=0;i<35;i++) {
			  moy+=charData[currentCharIndex][i];
			  if (charData[currentCharIndex][i]>max) {
				  max=charData[currentCharIndex][i];
			  }
			  if (charData[currentCharIndex][i]>0) nb++;
		  }
		  
		  
		  ////*added
		  //for (int c=0;c<35;c++)
		  {
			  //System.out.print(charData[currentCharIndex][c]+"  ");
			  //if ( (c+1)%5==0)System.out.print("\n");
			  
		  }
		  
		  //System.out.print("\n");
		  //System.out.print("\n");
		  ////added*/
		  
		  
		  ////*added
		  double inValues[][]= 
		  			{
				     {-1,-1,-1,-1,-1,
				     -1,-1,-1,-1,-1,
				     -1,-1,-1,-1,-1,
				     -1,-1,-1,-1,-1,
				     -1,-1,-1,-1,-1,
				     -1,-1,-1,-1,-1,
				     -1,-1,-1,-1,-1}
				    };
		  
		  try{
		  for (int c=0;c<35;c++){
			  if(charData[currentCharIndex][c]>0)
				      inValues[0][c]=0.9;
				  else
					  inValues[0][c]=0.1;  
			  
			  //System.out.print(inValues[0][c]+"  ");
			  //if ( (c+1)%5==0)System.out.print(" >\n");
			  
			  
		  }
		  }
		  catch(java.lang.ArrayIndexOutOfBoundsException e1){
			  
			  
			  
		  }
		  
		////added*/
		  
		  //System.out.print("\n");
		  
		  
		  for (i=0;i<35;i++) {
			  charData[currentCharIndex][i]=charData[currentCharIndex][i]/max;
		  }

		  moy=(moy/max)/nb;
		  
		  
		  for (i=0;i<35;i++) {
			  if (charData[currentCharIndex][i]>moy/4) charData[currentCharIndex][i]=1-Math.pow(1-charData[currentCharIndex][i],2);
		  }
		  drawBoard=true;
		  ////////changed
		  ///////////oneFeedForwardCycle();
		  
		  
		  
		  ////////added
		  //for (i=0;i<22;i++){
			//  System.out.print(i+" :");
			  //for (j=0;j<30;j++) 
				//  System.out.print(drawing[i][j]);
		  //System.out.print("\n");
		  //}
		  
		  
		  for (i=0;i<22;i++) {
			  for (j=0;j<30;j++) {
				  drawing[i][j]=0;
			  }
		  }
		
		  
		  
		  
		  draw();
		  
		  if (count>0)
		  {
			  isTest=true;
		  
		  
		  double t[] = {};
		  BackPropagate(inValues[0],t);
		  
		  double maxout = -100.0;
		  int maxO = -1;
		  for(int o=0;o<10;o++){
			  if(outValues[o]>maxout)
				  {
				  maxout=outValues[o];
				  maxO = o;
				  }
		  
				  }
			  System.out.print("\n Recognized as :  "+maxO+"\n");
		  
		  
			  return true;
		  }
			
		  count++;
		  
	  
 
	  }
	  
	  return false;
  }
  public boolean handleEvent(Event e) {
	  if (e.id == Event.WINDOW_DESTROY) {
		  if (inAnApplet) {
			  dispose();
		  } else {
			  System.exit(0);
		  }
	  }

	  return super.handleEvent(e);

  }
  



  public void initf() {
	setLayout(new BorderLayout());
	c = new Color[20];
    makeColors();
	//c1 = new Canvas();
	resize(600,450);
	//c1.resize(new Dimension(600,390));
	//add("South", c1);

    currentCharIndex=0;
    p1=new Panel();
    
	drawing = new int[22][30];
    
    
	b4 = new Button("Drawing recognition");
	
	
	p1.add(b4);
	p1.setBackground(background);
	validate(); 
   
	add("South",p1);
	show();
    
    

    fillMap();
   
    
    
    myfont= new Font("Courier",Font.PLAIN,14);
    myfont2= new Font("Times",Font.ITALIC,12);
	drawBoard = true;
	/////closed
	////oneFeedForwardCycle();
	/////draw();
  }

  
  /////all variables
  
  double inWeights[][] = { 
		  {0.45150063239913724,  -0.8784979359432299,  -0.8619956091089283,  -0.9058918604420025,  -0.15545430334357352,  1.1929100373442467,  0.15068980067706222,  0.14463363922953482,  0.1545772439310913,  0.9510772629010825,  0.8671670633804746,  0.17712504991469147,  0.18106192049554964,  0.15575422971841613,  0.9094534205650951,  0.32293393044011415,  1.4505485890931566,  1.4763988121512497,  1.4473102098808786,  -0.1338693727374523,  -0.8775055140372552,  0.1856509029803679,  0.17672935068669648,  0.14383186478001309,  1.0900431245280866,  -0.8760327523886946,  0.16142711902948315,  0.14812559712361384,  0.17951779113798433,  1.2229913941770254,  -0.32173956502198137,  -1.9558689325992513,  -1.968778038644719,  -1.9411672877865054,  -0.2907370455899486},  
		  {-0.1843092529049458,  0.9667387575817463,  0.9463623553169063,  0.9425655938009212,  -0.6258787972047333,  1.4734144160082716,  -0.005720453943155559,  -0.00893206477613015,  -0.028213730228076436,  -0.8169597189486996,  1.4321701758262817,  -0.03929072107770421,  -0.03142724358537855,  -0.04577401393127913,  -0.8032092075037361,  0.35998171413294716,  -0.08274183251449407,  -0.10606214856251958,  -0.08740326237666998,  -0.5424833782813365,  0.956614143299415,  -0.011921931465901047,  -0.046887430223286154,  -0.0235753782352224,  -0.6119238294265028,  0.9916652811743843,  -0.02317945331824071,  -0.023513117253995876,  -0.04456879307732071,  -0.8331120985826512,  1.6732126368771967,  1.6131828703918192,  1.6300781062016436,  1.6067792563306291,  -0.6819642380230234},  
		  {-0.32729680357009133,  1.258425329600595,  1.276288635137618,  1.275160273863799,  0.6105990747369693,  -3.1501828237273974,  -0.06120314077939079,  -0.08490055086227147,  -0.07756094237430697,  1.0105820917369084,  -2.2984755814621627,  -0.10415814504760237,  -0.08831618198566676,  -0.0927677996004414,  1.009603090832511,  -1.2722989062507464,  -0.6460970624370125,  -0.6236959254747391,  -0.6502955356206223,  0.28248515235441374,  2.4738237217075114,  -0.08717033482880637,  -0.06999919975837673,  -0.060156181729927954,  0.07902761889782195,  2.4448686501788304,  -0.06613692968537746,  -0.09498579859224326,  -0.07176749685546058,  0.11565726271986979,  -0.36985265396651335,  0.9546829635190546,  0.9713116030458113,  0.9478098132611776,  0.3000480767983587},  
		  {0.3858777223677455,  -1.2672617805641546,  -1.2597420370208838,  -1.2882513409196834,  -0.11610790053366302,  1.3011847494091864,  -0.030585788387607678,  -0.038577808058024986,  -0.0185937838354628,  -1.5385864051794502,  1.332916472695684,  -0.01137558233491938,  -0.014874104390852282,  -0.013208122730369842,  -1.5335679714187245,  -0.03541427679508027,  0.41142917400387724,  0.38563217896287616,  0.40952637613962095,  -0.2034927393550021,  3.4545970482266664,  -0.03197128523997527,  -0.028460112484229118,  -0.013153358844565024,  0.3075528229074141,  3.4348933037775944,  -0.03588247096888381,  -0.01818031550122695,  -0.026633254083061594,  0.3091779930444523,  -0.37346689427884877,  -1.1670030180168829,  -1.1338516162630254,  -1.1618945872624442,  -0.18026765970326045},  
		  {0.20130953616493705,  0.634610178711407,  0.6455210929228178,  0.6018505184078572,  0.21405891001478888,  -2.1731651995222543,  0.11390338801821613,  0.12164147460152405,  0.07612342465889675,  1.033002962084008,  -1.9802006308688411,  0.08686502997171745,  0.11894948804810808,  0.10583552444145981,  0.9908554541604101,  -0.5240127100638668,  -0.43364026544560824,  -0.43350118237821667,  -0.4450187061561756,  0.22634788048098006,  1.1072985362280514,  0.0974112771025371,  0.10994483149970062,  0.0974018916194835,  -2.8244592123074095,  1.1040489102239284,  0.11670378457217401,  0.09255006468485215,  0.11103234990807644,  -2.8752256791935937,  -0.3524778601092803,  -0.8206263709515887,  -0.8374348565217641,  -0.8461536554564416,  0.2624440904609677},  
		  {-0.15397955838328237,  -0.40725180835244057,  -0.40757184488641274,  -0.39519607823283764,  0.2938229065556922,  -1.371432619043368,  0.024993356186451647,  0.05217100689057677,  0.06277174347205894,  -2.918018773451936,  -1.220789498278509,  0.055719855837703613,  0.023992112484895155,  0.05768422436059911,  -2.900590732883716,  -0.030612702748280554,  2.4234469231763285,  2.433716088868583,  2.42162496438989,  -0.05066748110124258,  -0.3099687096597682,  0.05367234989705183,  0.06384084539231463,  0.04477481036849344,  0.017272196074688724,  -0.347690242328777,  0.029591877508993683,  0.05139789210157564,  0.06540627744743246,  0.20183433261491027,  -0.657311644009484,  0.06707973476926425,  0.07683610396244563,  0.03895094736920818,  0.8206959522149281},  
		  {0.6350739670663268,  0.23837143620645523,  0.2454948849559096,  0.223949524432774,  0.14231803879640226,  -0.021133567082181653,  0.13397802968834077,  0.12316382155563427,  0.1255685387061801,  1.6470811258717304,  -0.11984071092124964,  0.1608634533282739,  0.1227246111223248,  0.13269006854446333,  1.619482295080033,  0.3451935697458815,  -1.4625185636093592,  -1.4702981425943078,  -1.4439344332837722,  0.23827847655376264,  -2.086253962257977,  0.1156053088883372,  0.1382886132622096,  0.14545331285160484,  1.2684294866719106,  -2.11749643680035,  0.12384687295470687,  0.13516174568940004,  0.13202995395594405,  1.2974424661895716,  0.016654550080608874,  -0.09981186611336881,  -0.08904082895423446,  -0.10023431446784167,  0.4629126276728694},  
		  {-0.02919412688319101,  0.6142096314555241,  0.6204075829074773,  0.6309583918707491,  0.321871718056004,  1.3554128700885482,  0.17489639353899974,  0.18718472797632293,  0.17601871807287026,  -2.4149324091799933,  1.1784015473960967,  0.1769578931491034,  0.19561832399724902,  0.1815862590329487,  -2.4150069215292715,  0.06607123952831358,  -1.5882417856189144,  -1.590296535186055,  -1.5968623734238863,  0.23373577883039798,  -0.5166240330741191,  0.17281559918392522,  0.20634707477052225,  0.1850253623529273,  0.5026244498928127,  -0.5395119566075764,  0.2040737086535994,  0.19860209550991068,  0.21755310657469093,  0.5856114200629915,  0.19581620918898476,  -0.3817546045872145,  -0.3654017956391666,  -0.37762071239756617,  0.046863620874286804}  
		};

double hiddenBias[]={1.4687238876617834,  -0.5505815374053724,  -1.0741879546035906,  -0.434271173978366,  0.765269740901016,  0.20885674871095486,  1.1947775917956371,  1.72359754870882};

double inValue[][] = {
			 //0
			{0.9,0.9,0.9,0.9,0.9,
			 0.9,0.1,0.1,0.1,0.9,			
			 0.9,0.1,0.1,0.1,0.9,
			 0.9,0.1,0.1,0.1,0.9,
			 0.9,0.1,0.1,0.1,0.9,
			 0.9,0.1,0.1,0.1,0.9,
			 0.9,0.9,0.9,0.9,0.9},
									 
			
			{0.9,0.1,0.1,0.1,0.1,
			 0.9,0.1,0.1,0.1,0.1,			
			 0.9,0.1,0.1,0.1,0.1,
			 0.9,0.1,0.1,0.1,0.1,
			 0.9,0.1,0.1,0.1,0.1,
			 0.9,0.1,0.1,0.1,0.1,
			 0.9,0.1,0.1,0.1,0.1},
			
			{0.9,0.9,0.9,0.9,0.9,
		     0.1,0.1,0.1,0.1,0.9,			
			 0.1,0.1,0.1,0.1,0.9,
			 0.9,0.9,0.9,0.9,0.9,
			 0.9,0.1,0.1,0.1,0.1,
			 0.9,0.1,0.1,0.1,0.1,
			 0.9,0.9,0.9,0.9,0.9},
			 
			 
			{0.9,0.9,0.9,0.9,0.9,
			 0.1,0.1,0.1,0.1,0.9,			
			 0.1,0.1,0.1,0.1,0.9,
			 0.9,0.9,0.9,0.9,0.9,
			 0.1,0.1,0.1,0.1,0.9,
			 0.1,0.1,0.1,0.1,0.9,
			 0.9,0.9,0.9,0.9,0.9},
			
			 
			{0.9,0.1,0.1,0.1,0.9,
			 0.9,0.1,0.1,0.1,0.9,			
			 0.9,0.1,0.1,0.1,0.9,
			 0.9,0.9,0.9,0.9,0.9,
			 0.1,0.1,0.1,0.1,0.9,
			 0.1,0.1,0.1,0.1,0.9,
			 0.1,0.1,0.1,0.1,0.9,}, 
			 
			 
			{0.9,0.9,0.9,0.9,0.9,
			 0.9,0.1,0.1,0.1,0.1,
			 0.9,0.1,0.1,0.1,0.1,
			 0.9,0.9,0.9,0.9,0.9,
			 0.1,0.1,0.1,0.1,0.9,
			 0.1,0.1,0.1,0.1,0.9,
			 0.9,0.9,0.9,0.9,0.9},
			 
			 
			{0.9,0.9,0.9,0.9,0.9,
			 0.9,0.1,0.1,0.1,0.1,
			 0.9,0.1,0.1,0.1,0.1,
			 0.9,0.9,0.9,0.9,0.9,
			 0.9,0.1,0.1,0.1,0.9,
			 0.9,0.1,0.1,0.1,0.9,
			 0.9,0.9,0.9,0.9,0.9},
			 
		   {0.9,0.9,0.9,0.9,0.9,
			0.1,0.1,0.1,0.1,0.9,		
		    0.1,0.1,0.1,0.1,0.9,
		    0.1,0.1,0.1,0.1,0.9,
			0.1,0.1,0.1,0.1,0.9,
		    0.1,0.1,0.1,0.1,0.9,
		    0.1,0.1,0.1,0.1,0.9},
			
		    	{0.9,0.9,0.9,0.9,0.9,
				 0.9,0.1,0.1,0.1,0.9,
				 0.9,0.1,0.1,0.1,0.9,
				 0.9,0.9,0.9,0.9,0.9,
				 0.9,0.1,0.1,0.1,0.9,
				 0.9,0.1,0.1,0.1,0.9,
				 0.9,0.9,0.9,0.9,0.9},
				 
				 	{0.9,0.9,0.9,0.9,0.9,
					 0.9,0.1,0.1,0.1,0.9,
					 0.9,0.1,0.1,0.1,0.9,
					 0.9,0.9,0.9,0.9,0.9,
					 0.1,0.1,0.1,0.1,0.9,
					 0.1,0.1,0.1,0.1,0.9,
					 0.9,0.9,0.9,0.9,0.9},
				
			 
				  

};


double hiddenWeights[][] = {{-4.2353156039350885,  0.4618841613367588,  -0.12602122344205746,  1.2874435352479947,  -0.8536874627954171,  -0.9117284666220087,  3.0285967334650077,  0.9193063056569024},  
		{0.7776854022864008,  0.8447716417576259,  -1.7942843021670118,  0.7649160048120873,  1.3313893404028523,  -2.334546806311676,  -1.4724509262464804,  1.3200468869077788},  
		{-1.6492641608662417,  0.01297784482572091,  -0.8614307021204102,  -0.8182801159275065,  2.7044055200732595,  0.1308722212774174,  -1.2727159966997499,  -1.1979553423994442},  
		{-1.850160059382113,  -2.244060023771765,  3.4764148038137046,  -2.471885570832909,  -3.952170883965121,  4.414954002025718,  1.7177704311111273,  -2.8889052775813737},  
		{-1.8699904370375255,  -2.1625017592561733,  -1.7978792836594129,  2.298655819979951,  0.2655668324982532,  0.9336283834999264,  1.9767867951039673,  -1.8088112268668364},  
		{-0.8935050329153224,  -0.2057405152460309,  -2.1825003180268645,  -2.9215431919662365,  -0.014275868443022888,  2.1732667510011767,  -0.0773604265070795,  2.5062093498540756},  
		{-2.57074902739168,  -0.060685833412282814,  0.5955795746488562,  2.1765291133712013,  -3.178064144242369,  2.7067984343466036,  -1.7189205114830504,  -0.7807093903478629},  
		{1.9740881378005075,  -3.0717613991916926,  1.8336101403631258,  -1.8540682888031228,  0.3162247603295466,  -0.8111681122700571,  -1.404788158181966,  1.8987529952548032},  
		{0.921181216949448,  -0.6221209563291495,  3.3850369040615473,  3.359300699128945,  -2.075067234164966,  -4.099444103170954,  -1.9072124044052112,  -4.491089966001373},  
		{1.0099730697807017,  3.3414250499602742,  -4.368736220992038,  -3.9470219490078073,  1.723381197818903,  -4.718044622261287,  1.8282001567536574,  -2.091875175622573}
					};

double outputBias[]={-1.4263996898360167,  -1.0682944453169514,  1.2175986027629608,  -2.10146357259425,  0.746649145552836,  -0.5338516624556814,  -0.6265316329857812,  -0.7032016025466914,  -0.9992574238464064,  -0.6289741460333251};

double hiddenValues[] = {0,0,0,0,0,0,0,0};

double outValues[] = {0,0,0,0,0,0,0,0,0,0};

double targetValues[][] = {
				   {0.9,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1},
				   {0.1,0.9,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1},
				   {0.1,0.1,0.9,0.1,0.1,0.1,0.1,0.1,0.1,0.1},
				   {0.1,0.1,0.1,0.9,0.1,0.1,0.1,0.1,0.1,0.1},
				   {0.1,0.1,0.1,0.1,0.9,0.1,0.1,0.1,0.1,0.1},
				   {0.1,0.1,0.1,0.1,0.1,0.9,0.1,0.1,0.1,0.1},
				   {0.1,0.1,0.1,0.1,0.1,0.1,0.9,0.1,0.1,0.1},
				   {0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.9,0.1,0.1},
				   {0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.9,0.1},
				   {0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.9}
				   
				  }; //for 1

double learningRate = 0.001;
double momentum = 0.9;

double outputError []={0,0,0,0,0,0,0,0,0,0};
double hiddenError []={0,0,0,0,0,0,0,0};

double currentInputWeightUpdate[][]  = new double[8][36];
double previousInputWeightUpdate[][] = new double[8][36];
double totalInputWeightUpdate [][]   = new double[8][36];


double currentHiddenWeightUpdate[][]  = new double[10][9];
double previousHiddenWeightUpdate[][] = new double[10][9];
double totalHiddenWeightUpdate [][]   = new double[10][9];

boolean isTest = false;
  

  public void BackPropagate(double currentInputValue[],double currentTargetValue[]){
		double y = 0.0;
		double o = 0.0;
		
		//hidden layer unit value calculation
		//if(!isTest)
		for(int i=0;i<8;i++)
		{
			y = hiddenBias[i];
			o = 0.0;
			
			for(int j=0;j<35;j++)
			    {
				y=y+(currentInputValue[j])*(inWeights[i][j]);
			    
			    }
			
			o=1/(1+Math.exp(-1*y));
			hiddenValues[i]=o;
		//System.out.print(hiddenValues[i]+"  ");
		}
		
		//System.out.print("\n");

		//output layer unit value calculation
		
		//System.out.print("OUTPUT::\n");
		//if(!isTest)
		for(int i=0;i<10;i++)
		{
			y = outputBias[i];
			o = 0.0;
			
			for(int j=0;j<8;j++)
			    {
				y=y+(hiddenValues[j])*(hiddenWeights[i][j]);
			    
			    }
			
			o=1/(1+Math.exp(-1*y));
			outValues[i]=o;
		
			
			
			if(isTest)
			System.out.print(outValues[i]+"  ");
		
		
			
			
			{
				
				//System.out.print(outValues[i]+"  ");
				//if(outValues[0]>=0.4)	
					//System.out.print("\nRecognized as 0 ...");
			}
			
			
	}	
		
		
		
		
		System.out.print("\n");
		
		if(!isTest)
		//output layer Error calculation
		for(int i=0;i<10;i++)
		{
			
			outputError[i]=outValues[i]*(1-outValues[i])*(currentTargetValue[i]-outValues[i]);
			////System.out.print(outputError[i]+" ");
		}
		
		
		////System.out.print("\n\n");
		
		if(!isTest)
		//hidden layer Error calculation
		for(int i=0;i<8;i++)
		{
			double sum = 0;
			for(int j=0;j<10;j++)
				
				sum+=outputError[j]*hiddenWeights[j][i]; //this is critical as hidden weights are in reverse as for output value calculation
			
				
			hiddenError[i]=hiddenValues[i]*(1-hiddenValues[i])*sum;
			////System.out.print(hiddenError[i]+"  ");
		
		}
	
	//update Weights
	
		
		
	
		
		if(!isTest)
		//update hidden bias
		for(int hb=0;hb<8;hb++){
		currentInputWeightUpdate[hb][0] = learningRate*hiddenError[hb];
		totalInputWeightUpdate [hb][0] = momentum * previousInputWeightUpdate[hb][0]+currentInputWeightUpdate[hb][0];
		hiddenBias[hb]+=totalInputWeightUpdate [hb][0];
		previousInputWeightUpdate[hb][0] = totalInputWeightUpdate [hb][0]; 
		}
		
		////					System.out.print("\n\n");
							///System.out.print(previousInputWeightUpdate[0][0]);
		
			
		//update input weights
		
		if(!isTest)
		for(int h=0;h<8;h++)
		{
			for(int i=0;i<35;i++)
			{
				
				currentInputWeightUpdate[h][i+1]=learningRate*hiddenError[h]*currentInputValue[i];
				
				totalInputWeightUpdate [h][i+1] = momentum * previousInputWeightUpdate[h][i+1]+currentInputWeightUpdate[h][i+1];

				inWeights[h][i]+=totalInputWeightUpdate[h][i+1];
				
		/////		System.out.print(inWeights[h][i]+"  ");
				
				previousInputWeightUpdate[h][i+1]=totalInputWeightUpdate[h][i+1];
				
			}
		
		////System.out.print("\n");
		}
		
		
		
		if(!isTest)
		//update output bias
		for(int ob=0;ob<10;ob++){
			currentHiddenWeightUpdate[ob][0]=learningRate*outputError[ob];
			totalHiddenWeightUpdate[ob][0]=momentum * previousHiddenWeightUpdate[ob][0]+currentHiddenWeightUpdate[ob][0];
			
			outputBias[ob]+=totalHiddenWeightUpdate[ob][0];
			
			previousHiddenWeightUpdate[ob][0]=totalHiddenWeightUpdate[ob][0];
			
		////System.out.print(outputBias[ob]+" ");	
		
		}
		
		////System.out.print("\n\n");	
			//update hidden weights
		if(!isTest)	
		for(int ol=0;ol<10;ol++)
		{
			for(int h=0;h<8;h++)
			{
				
				currentHiddenWeightUpdate[ol][h+1]=learningRate*outputError[ol]*hiddenValues[h];
				
				totalHiddenWeightUpdate[ol][h+1]=momentum * previousHiddenWeightUpdate[ol][h+1]+currentHiddenWeightUpdate[ol][h+1];
				
				hiddenWeights[ol][h]+=totalHiddenWeightUpdate[ol][h+1];
				
				previousHiddenWeightUpdate[ol][h+1]=totalHiddenWeightUpdate[ol][h+1];
				
			////	System.out.print(hiddenWeights[ol][h]+" ");
				
				
			}
		////System.out.print("\n");
		
		}
			
	}
	
  
  
  
  

  public void fillMap()
  {
    
    drawBoard=true;
  }
  

  public void makeColors()
  {
    for (i=0;i<20;i++)
	  c[i]=new Color(0,10*i+55,0);
  
    background=new Color(203,202,202);
  }



  public void fillBox(Graphics g,int x,int y,Color c)
  {
    g.setColor(c);
    g.fillRect(x,y,16,16);
  }
  

  public boolean mouseDrag(Event evt, int x, int y) {
	  //g = getGraphics ( );
	  if ((x>427)&&(x<515)&&(y>47)&&(y<167)){
		  g.drawLine ( x, y, x, y );
		  last_x = x;
		  last_y = y;
		  (drawing[(x-427)/4][(y-47)/4])++;
	  }
	  return true;
  }
  
  

  public boolean mouseDown(java.awt.Event evt, int x, int y)
  {
    pointX=-100;
    pointY=-100;
    pickX=-1;
    pickY=-1;
    i=(y-48)/17;
    j=(x-48)/17;
    drawBoard=true;
    
    
    if ((x>427)&&(x<515)&&(y>47)&&(y<167)){
		last_x = x;
		last_y = y;
		g.setColor ( Color.black );

		return true;
	}
    
    return false;
  }
  
 

  
  public void fixBox(Graphics g,int x,int y,int w,int h,Color c)
  {
    g.setColor(c);
    g.fillRect(x,y,w,h);
  }
  
  
  public void draw() {
	  double carval=-1;
	  int car=-1;
	  g = getGraphics();
	  if (drawBoard) {
		  g.setFont(myfont);
		  g.setColor(background);
		  
		  /////closed
		  /*
		  g.fillRect(0,0,size().width,size().height);
		  */
		  
		  fixBox(g,29,49,88,122,Color.gray);
		  fixBox(g,27,47,86,120,Color.blue);
		  fixBox(g,429,49,90,122,Color.gray);
		  fixBox(g,427,47,88,120,Color.blue);

		  for (i=0;i<7;i++) {
			  for (j=0;j<5;j++) {
				  if (charData[currentCharIndex][i*5+j]>=0.5) 
					  fillBox(g,28+j*17,48+i*17,Color.green);
				  if (charData[currentCharIndex][i*5+j]<0.5)
					  fillBox(g,28+j*17,48+i*17,Color.blue);
			  }
		  }
		  
	  
		  drawBoard=false;
		  
	  
	  } else {
		  if (charData[currentCharIndex][pickY*5+pickX]>=0.5) {
			  fillBox(g,28+pickX*17,48+pickY*17,Color.green);
		  }
		  if (charData[currentCharIndex][pickY*5+pickX]<0.5) {
			  fillBox(g,28+pickX*17,48+pickY*17,Color.blue);
		  }
	  }
  }	
  

}


public class DigitRecog extends Applet
{
	MenuWindow win;

	public DigitRecog()
	{
	}

	public String getAppletInfo()
	{
		return "Name: reccar\r\n" +
		       "Author: Pierre Lang\r\n" +
		       "Created with Microsoft Visual J++ Version 1.1";
	}



	public void init() {
		//Canvas c = new Canvas();
		//c.resize(600,350);
		win = new MenuWindow();
		win.setTitle("Character recognition applet");
		win.resize(600,450);
		//win.add("Center",c);;
		
		win.initf();
		
		//win.drawBoard=true;
		//win.draw();

	}
  

	public void destroy()
	{
	}

	public void paint(Graphics g)
	{
	}

	public void start()
	{
		//win.drawBoard=true;
		//win.draw();
	}
	
	public void stop()
	{
	}



}
