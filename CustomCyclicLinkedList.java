public class CustomCyclicLinkedList {
    private class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }

    private Node head;
    private Node tail;

    public CustomCyclicLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void add(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        node.next = head;
        tail = node;
    }

    public void display() {
        Node node = head;
        if (head != null) {
            do {
                System.out.print(node.val + " -> ");
                if (node.next != null) {
                    node = node.next;
                }
            } while (node != head);
        }
        System.out.println("HEAD");
    }

    public void remove(int val) {
        Node node = head;
        if (node == null) {
            return;
        }

        if (head == tail){
            head = null;
            tail = null;
            return;
        }

        if (node.val == val) {
            head = head.next;
            tail.next = head;
            return;
        }

        do {
            Node n = node.next;
            if (n.val == val) {
                node.next = n.next;
                break;
            }
            node = node.next;
        } while (node != head);

    }

    public static void main(String[] args)
    {
        CustomCyclicLinkedList l = new CustomCyclicLinkedList();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.display();
        l.remove(4);
        l.display();

    }
}