public class Queue {
    private final int SIZE = 20;
    private int[] queArray = new int[SIZE];
    private int front = 0;
    private int rear = -1;
    private int currentSize=0;

    public void insert(int i){
        if(!isFull()) {
            rear++;
            if (rear==SIZE-1){
                rear=0;
            }
            queArray[rear] = i;
            currentSize++;
        }
    }

    public int remove(){
        int result = -1;
        if(!isEmpty()){
            result=queArray[front];
            front++;
            currentSize--;
            if(front==SIZE-1){
                front=0;
            }
        }
        return result;
    }

    public boolean isEmpty(){
        return currentSize==0;
    }

    public boolean isFull(){
        return currentSize==SIZE;
    }
}
