import javax.xml.transform.TransformerConfigurationException;

class LinkedList {
    ListNode head;
    ListNode tail;
    int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public LinkedList(Object element) {
        this.head = new ListNode<Object>(element);
        this.tail = this.head;
    }

    public void add(Object e) {
        ListNode new_item = new ListNode<Object>(e);
        if (this.size == 0) {
            this.head = new_item;
            this.tail = new_item;
            this.size += 1;
        } else {
            this.tail.next = new_item;
            this.tail = new_item;
            this.size += 1;
        }
    }

    public void addAt(Object e, int index) {
        if (index < this.size - 1) {
            throw new IndexOutOfBoundsException();
        }
        ListNode pointer = new ListNode<Object>(e);
        for (int i = 0; i < index; i += 1) {
            pointer = pointer.next;
        }
        ListNode newNode = new ListNode<Object>(e);
        newNode.next = pointer.next;
        pointer.next = newNode;
    }

    public void addFirst(Object e) {
        ListNode newNode = new ListNode<Object>(e);
        newNode.next = this.head;
        this.head = newNode;
        this.size += 1;

    }

    public void addLast(Object e) {
        add(e);
    }

    public Object getFirst() {
        if (this.size == 0) {
            return null;
        }
        return this.head.getElement();        
    }

    public Object getLast() {
        if (this.size == 0) {
            return null;
        }
        return this.tail.getElement();
    }

    public Object get(int index) {
        if (this.size == 0) {
            return null;
        }
        ListNode pointer = this.head;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.getElement();
    }

    // clear the list. DONE
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Object remove(int index) {
        if (index > this.size - 1) {
            throw new IndexOutOfBoundsException();
        }


        ListNode toRemove = this.head;
        ListNode toSetPointer = null;
        // ListNode fastPointer = this.head;

        for (int i = 0; i < index - 1; i += 1) {
            toSetPointer = toRemove;
            toRemove = toRemove.next;
        }
        toSetPointer = toSetPointer.next.next;

        // ListNode temp = pointer.next;
        System.out.println("Removing " + toRemove.getElement());
        // System.out.println("Removing " + temp.getElement());

        // pointer.next = pointer.next.next;
        this.size -= 1;

        if (index == 0) {
            this.head = this.head.next;
        }
        if (index == this.size - 1) {
            this.tail = toSetPointer;
        }
        toRemove.next = null;
        return toRemove;
    }

    public boolean contains(Object o) {
        ListNode pointer = head;
        while (pointer != null) {
            if (pointer.getElement() == o) {
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public void printList() {
        System.out.println(this.size);

        if (this.size == 0) {
            return;
        }

        ListNode pointer = this.head;
        for (int i = 0; i < this.size - 1; i++) {
            System.out.print(pointer.getElement() + "->");   
            pointer = pointer.next;         
        }
        System.out.println(pointer.getElement() + "\n");
    }



    private class ListNode<Object> {
        ListNode next;
        Object element;

        public ListNode (Object element) {
            this.element = element;
            this.next = null;
        }

        public Object getElement() {
            return this.element;
        }
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        ll.add(0);
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll.add(6);

        ll.printList();

        ll.remove(4);
        ll.printList();

        ll.remove(0);

        ll.printList();
        // ll.clear();
        // ll.printList();

        // System.out.println(ll.get(0));
    }
}