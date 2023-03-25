/** 
模擬雙色球
1.用戶選擇手選號or隨機號
2.接收用戶號碼
3.生成系統號碼 
4.比較系統號碼和用戶號碼，並記錄個數
5.驗證是否中獎
6.系統號碼排序
7.公布結果
 
*/
import java.util.*;
public class ball_2{
    public static void main(String[] args){
        Scanner input =new Scanner(System.in);
        int [] redBall=new int[33];//紅球33
        int [] sysRedball=new int[6];//系統生成紅球號碼
        int [] userRedball=new int[6];//自選紅球號碼
        int sysBlueBall=0;//系統籃球
        int userBlueBall=0;//用戶選擇籃球
        int redCount=0;//驗證紅球
        int blueCount=0;//驗證籃球

        for(int i =0;i<redBall.length;i++){
            redBall[i]=i+1;
        }
        //遊戲開始
        System.out.println("雙色球遊戲");
        System.out.println("請選擇電腦選號或手動選號:1.電腦選號 2.手動選號:");
        
        Random r= new Random();
        Boolean flag= true;
        while(flag){
            int isAuto=input.nextInt();
            switch(isAuto){
                case 1:computrSelection(redBall,userRedball);//機選紅球
                    userBlueBall=r.nextInt(16)+1;//機選籃球
                    flag=false;
                    break;
                case 2:System.out.println("請選擇六個紅球號碼1-33:");
                    for(int i=0;i<userRedball.length;i++){
                        userRedball[i]=input.nextInt();
                        
                    }
                    System.out.println("請選擇一個籃球號碼1-16:");
                    userBlueBall=input.nextInt();
                    flag=false;
                    break;
                    default:
                        System.out.println("請選擇電腦選號或手動選號:1.電腦選號 2.手動選號");
                    break;
            }
        }
        
        //系統隨機生成號碼
        //紅球
        computrSelection(redBall, sysRedball);
        //籃球
        sysBlueBall=r.nextInt(16)+1;
        
        //比較系統號碼跟用戶號碼

        for(int i=0;i<userRedball.length;i++){
            for(int j =0;j<sysRedball.length-redCount;j++){ 
                if(userRedball[i]==sysRedball[j]){
                    int temp=sysRedball[j];
                    sysRedball[j]=sysRedball[sysRedball.length-1-redCount];
                    sysRedball[sysRedball.length-1-redCount]=temp;
                    redCount++;
                    break;
                }
            }
        }
        //統計籃球

        if(userBlueBall==sysBlueBall){
            blueCount=1;
            
        }
    //驗證是否中獎
        if(blueCount==0 && redCount<=3){
            System.out.println("未中獎");
        }else if(blueCount==1 && redCount<3){
            System.out.println("六等獎，5元");
        }else if(blueCount==1 && redCount==3 ||blueCount==0 && redCount==4){
            System.out.println("五等獎");
        }else if(blueCount==1 && redCount==4 ||blueCount==0 && redCount==5){
                System.out.println("四等獎");
        }else if(blueCount==1 && redCount==5){
                System.out.println("三等獎");
        }else if(blueCount==0 && redCount==6){
                System.out.println("二等獎");
        }else if(blueCount==1 && redCount==6){
            System.out.println("頭獎");
        }
        //公布本期中獎號碼(系統)
        System.out.println("本期紅球號碼為:");
        sort(sysRedball);
        System.out.println(Arrays.toString(sysRedball));
        System.out.println("本期藍球號碼為:"+sysBlueBall);
        
        //公布用戶選擇號碼
        System.out.println("您的紅球號碼為:");
        sort(userRedball);
        System.out.println(Arrays.toString(userRedball));
        System.out.println("本期藍球號碼為:"+userBlueBall);
        System.out.println("買雙色球讚!");
    
    }
    //冒泡排序法
    public static void sort(int[]ball){
        for(int i =0;i<ball.length-1;i++){
                for(int j=0;j<ball.length-1;j++){
                    if(ball[j]>ball[j+1]){
                    ball[j]=ball[j]+ball[j+1];
                    ball[j+1]=ball[j]-ball[j+1];
                    ball[j]=ball[j]-ball[j+1];
                    }
                }
        }
    }
   


    //帶入隨機數
    public static void computrSelection(int[]redBall,int[]userRedBall){
        Random r= new Random();
        int index=-1;
        for(int i=0;i<userRedBall.length;i++){
            index=r.nextInt(redBall.length-i);
            userRedBall[i]=redBall[index];
            int temp=index;
            redBall[index]=redBall[redBall.length-1-i];
            redBall[redBall.length-1-i]=temp;
        }
    }
}