// import LinkedList;
// 
public class DoublyLinkedList {
    ListNode head;
    ListNode tail;
    int size;
    
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFront(Object item) {
        ListNode newNode = new ListNode<Object>(item);
        if (this.size == 0) {
            this.tail = newNode;
            this.head = newNode;
        } else {
            newNode.next = head;
            this.head.previous = newNode;
            this.head = newNode;
        }
        this.size += 1;
    }

    public void addBack(Object item) {
        if (this.size == 0) {
            addFront(item);
        } else{
            ListNode newNode = new ListNode<Object>(item);
            tail.next = newNode;
            newNode.previous = tail;
            this.tail = newNode;
        }
        this.size += 1;
    }

    public void add(Object item, int index) {
        if (index > this.size - 1) {
            throw new IndexOutOfBoundsException();
        }
        ListNode pointer = this.head;

        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        ListNode newNode = new ListNode<Object>(item);
        newNode.next = pointer.next;
        newNode.previous = pointer;

        pointer.next.previous = newNode;
        pointer.next = newNode;
        this.size += 1;

    }

    public Object remove(int index) {
        if (this.size == 0) {
            return null;
        }

        ListNode pointer; 
        // ListNode fPointer = this.head;
        // ListNode bPointer = this.tai;
        if (index < this.size / 2) {
            pointer = this.head;
            for (int i = 0; i != index; i++) {
                pointer = pointer.next;
            }
        } else {
            pointer = this.tail;
            for (int i = 0; i != index - this.size; i++) {
                pointer = pointer.previous;
            }
        }
        pointer.previous.next = pointer.next;
        pointer.next.previous = pointer.previous;
        pointer.next = null;
        pointer.previous = null;
        this.size -= 1;
        
        return pointer;
    }

    public Object removeBack() {
        ListNode pointer = this.tail;
        pointer.previous = null;
        this.tail = this.tail.previous;
        this.tail.next = null;
        return pointer;
    }

    public Object removeFront() {
        ListNode pointer = this.head;
        pointer.next = null;
        this.head = this.head.next;
        this.head.previous = null;
        return pointer;
    }

    public boolean contains(Object e) {
        ListNode fPointer = this.head;
        ListNode bPointer = this.tail;
        for (int i = 0, j = this.size; i <= j  ;i++, j--) {
            if (fPointer.getElement() == e || bPointer.getElement() == e) {
                return true;
            }
            fPointer = fPointer.next;
            bPointer = bPointer.previous;
        }
        return false;
    }

    public void printList() {
        System.out.println(this.size);

        if (this.size == 0) {
            return;
        }

        ListNode pointer = this.head;
        for (int i = 0; i < this.size - 1; i++) {
            System.out.print(pointer.getElement() + "<->");   
            pointer = pointer.next;         
        }
        System.out.println(pointer.getElement() + "\n");
    }



    


    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll.add(6);
        ll.add(7);

        ll.printList();

        // ll.remove(4);

        ll.printList();
        ll.clear();
        ll.printList();
    }


    private class ListNode<Object> {
        ListNode previous;
        ListNode next;
        Object element;

        public ListNode (Object element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }

        public Object getElement() {
            return this.element;
        }
    }
}