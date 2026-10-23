public class DynArray {
    private int size;
    private int capacity;
    private int [] arr;


    public DynArray(int size) {
        this.capacity = size;
        this.size = 0;
        this.arr = new int[this.capacity];
    }



    public void appendDA(int value) {
        if (this.size == this.capacity) {
            capacity *= 2;
            this.arr = new int[capacity];
        }

        this.arr[this.size] = value;
        this.size++;
    }





    public int[] getArrDA() {
        return this.arr;
    }




    public void fillDA(int value) {
        for (int i = 0; i < this.capacity; i++) {
            this.arr[i] = value;
        }
    }

    public int getSize() {
        return size;
    }
}
