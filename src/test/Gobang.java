import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @DESCRIPTION 五子棋
 * @AUTHER administrator zhangna
 * @create 2018-05-25
 */
public class Gobang {
    //定义棋盘的大小
    private static  int BARD_SIZE = 15;
    //定义二维数组充当棋盘
    private String [][] board;
    public void initBoard(){
        //初始化棋盘
        board = new String[BARD_SIZE][BARD_SIZE];
        for(int i=0 ; i<BARD_SIZE;i++){
            for(int j=0; j<BARD_SIZE;j++){
                board[i][j] = "✚";
            }
        }
    }
    //在控制台输出棋盘的方法
    public void printBoird(){
        for (int i = 0; i < BARD_SIZE ; i++) {
            for (int j =0 ;j< BARD_SIZE; j++){
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static void main(String []arg0) throws Exception {
        Gobang gb = new Gobang();
        gb.initBoard();
        gb.printBoird();
        //获取键盘输入的方法
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = null;
        while ((inputStr = rd.readLine()) !=null){
            //将用户输入的值用，分隔分为两个字符串
            String [] posArr= inputStr.split(",");
            int xPos = Integer.parseInt(posArr[0]);
            int yPos = Integer.parseInt(posArr[1]);
            //把对应的位置赋值为·
            gb.board[xPos][yPos] = "●";

            gb.printBoird();
            System.out.println("请输入您下棋的坐标，格式为x,y：");
        }
    }

}
