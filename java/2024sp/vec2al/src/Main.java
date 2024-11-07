//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Stack stack = new Stack();

        stack.push("아이템1");
        String item1 =stack.peek(0);
        System.out.println(item1);
        stack.pop();
        item1 = stack.peek(0);
        System.out.println(item1);
    }
}