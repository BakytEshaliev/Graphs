public class Vertex {
    public char label;
    public boolean wasVisited;
    int index;

    public Vertex(char label, int index){
        this.label = label;
        wasVisited = false;
        this.index = index;
    }
}
