import java.util.Vector;

public class Stack {
    private Vector<String> stack = new Vector<String>();

    public void push(String item){
        this.stack.add(item);
    }

    public void pop(){
        this.stack.remove(this.stack.getLast());
    }

    public String peek(int index){
        if(this.stack.get(index)!= null){
            return this.stack.get(index);
        }
        else return "없음";
    }
}
