import java.util.*;

class Node {
    public int value; // unique
    public Collection<Node> siblings; // collection of nodes such that for each sibling in colection edge between this node and sibling exists

    public Node (int value, Collection<Node> siblings) {
        this.value = value;
        this.siblings = siblings;
    }
}

class NodeIterator implements Iterator<java.lang.Integer> {
    private final Set<Node> visited = new HashSet<>();
    private final Queue<Node> q = new LinkedList<>();




    public NodeIterator(Node start) {
        this.q.add(start);
        this.visited.add(start);
    }

    @Override
    public boolean hasNext() {
        // TODO implement
        return !this.q.isEmpty();
        }



    @Override
    public java.lang.Integer next() {

        // TODO implement
        if(hasNext()){
            Node next = q.remove();
            for(Node n : next.siblings){
                if(!this.visited.contains(n)){
                    this.q.add(n);
                    this.visited.add(n);
                }
            }
            return next.value;
            } else {
            throw  new NoSuchElementException();
        }


    }

    public static void main(String[] args) {
        Node start = new Node(1, Arrays.asList(
                new Node(2, Collections.emptyList()),
                new Node(3, Collections.singleton(
                        new Node(4, Collections.emptyList()))
                )
        ));


        NodeIterator iterator = new NodeIterator(start);
        // should print numbers 1-4 in any order
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}