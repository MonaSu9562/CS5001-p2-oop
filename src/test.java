
public class test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // static only be initialised once. and every time it changed it changed.
        one a = new one();
        a.i ++;
        one b = new one();
        System.out.println(b.i);
    }

}

class one{
    static int i = 1;
}
