public class StackX {
    private final  int SIZE = 20;
    private int[] st = new int[SIZE];
    private int top=-1;

    public void push(int i){
        st[++top]=i;
    }

    public int pop(){
        if(!isEmpty()) {
            return this.st[top--];
        }
        return -1;
    }

    public int peek(){
        return st[top];
    }

    public boolean isEmpty(){
        return top==-1;
    }
}
