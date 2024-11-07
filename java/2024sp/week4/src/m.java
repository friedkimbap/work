public class m {

    private int num;

    public void m1(int a,int b){
        num = 1;
        if(a+b>0){
            num = m2();
        }
        System.out.println(num);
    }

    private int m2(){
        return 5;
    }
}
