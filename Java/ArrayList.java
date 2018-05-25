import java.util.NoSuchElementException;

/**
 * ArrayList
 */
public class ArrayList<Item> {
    public int arrSize;
    public int capacity;
    public int endPointer;
    public Item[] array;
    public int resizeFactor = 2;

    public ArrayList(int capacity) {
        this.capacity = capacity;
        this.array = (Item[]) new Object[this.capacity];
    }

    public ArrayList() {
        this(1);
    }

    private void resize() {
        int newCapacity = this.capacity * 2;
        Item[] newArray = (Item[]) new Object[newCapacity];
        
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = this.array[i];
        }

        this.array = newArray;
    }

    public void add(Item item) {
        if (this.size() == this.capacity) {
            resize();
        }
        this.array[this.endPointer] = item;
        this.arrSize += 1;
        this.endPointer += 1;
    }

    public boolean contains(Item o) {
        for (int i = 0; i < this.size(); i += 1) {
            if (this.array[i] == o) {
                return true;
            }
        }
        return false;
    }

    public Item get(int index) {
        return this.array[index];
    }

    public int indexOf(Item o) {
        for (int i = 0; i < this.size(); i += 1) {
            if (this.array[i] == o) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public void remove(Item o) {
        int index = this.indexOf(o);

        for (int i = index, j = index + 1; this.array[j] != null; i++, j++) {
            this.array[i] = this.array[j];
        }
        this.endPointer -= 1;
        this.arrSize -= 1;
    }

    public int size() {
        return this.arrSize;
    }

    public void printList() {
        int i = 0;
        for (; i < this.size() - 1; i++) {
            if (this.array[i] != null) {
                System.out.print(this.array[i] + " -> ");
            }
        }
        if (this.array[i] != null) {
            System.out.print(this.array[i] + "\n");
        }
    }

    public void clear() {
        this.array = (Item[]) new Object[this.capacity];
    }

    public static void main(String[] args) {
        ArrayList arr = new ArrayList<Integer>(10);

        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);
        arr.add(7);

        arr.printList();

        arr.remove(4);

        arr.printList();
        arr.clear();
        arr.printList();
    }
}