import java.util.*;
class CustomLinkedlist
{
    private class Node
    {
        int val;
        Node next;
        Node(int val)
        {
            this.val=val;
        }
        Node(int val,Node index)
        {
            this.val=val;
            this.next=index;
        }
    }

    Node head;
    Node tail;
    int size=0;
    public void add(int val)
    {
        if(head==null)
        {
            Node node=new Node(val);
            head=node;
            tail=head;
            size++;
            return;
        }

        Node node=new Node(val);
        tail.next=node;
        tail=node;
        size++;

    }
    public void addFirst(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;

        if (tail == null) {
            tail = head;
        }
        size+=1;
    }

    public void addLast(int val) {
        if (tail == null) {
            addFirst(val);
            return;
        }
        Node node = new Node(val);
        tail.next = node;
        tail = node;
        size++;
    }
    public void insert(int val, int index) {
        if (index == 0) {
            addFirst(val);
            return;
        }
        if (index == size) {
            addLast(val);
            return;
        }

        Node temp = head;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }
        Node node = new Node(val, temp.next);
        temp.next = node;

        size++;
    }
    public int indexOf(int val)
    {   Node node=head;
        int size=1;
        while(node!=null)
        {

            if(val==node.val)
            {
                return size;
            }
            size++;
            node=node.next;
        }
        return -1;
    }
    public int remove(int index)
    {    if (index == 0) {
        head=head.next;
    }
        if (index == size) {
            Node prev=get(index-1);
            tail=prev;
        }

        Node prev = get(index - 1);
        int val = prev.next.val;

        prev.next = prev.next.next;
        size--;
        return val;


    }
    public Node get(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void display()
    {
        Node temp=head;
        while(temp!=null)
        {
            System.out.print(temp.val+"->");
            temp=temp.next;
        }
        System.out.println("END");
    }
    public int head()
    {
        return head.val;
    }
    public int  tail()
    {
        return tail.val;
    }
    public int size()
    {
        return size;
    }
    public static void main(String[] args)
    {
        CustomLinkedlist list=new CustomLinkedlist();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.insert(7,2);
        list.display();
        System.out.println("head "+list.head());
        System.out.println("tail "+list.tail());
        System.out.println("size "+list.size());
        System.out.println("indexval " +list.indexOf(7));
        list.remove(2);
        list.display();
    }
}
