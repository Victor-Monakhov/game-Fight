package arrListLib.vm;

public class VMList<T> implements VMCollection<T> {
    private int length = 0;
    private Item<T> item;
    public VMList(){
        item = new Item<>();
    }
    public class Item<T>{
        private T value;
        private Item<T> next;
    }
    @Override
    public int size() {
        return length;
    }
    @Override
    public void addFirst(T value){
        Item<T> temp = new Item<>();
        temp.next = item;
        temp.value = value;
        item = temp;
        ++length;
    }
    @Override
    public void addLast(T value){
        Item<T> temp = item;
        while(true){
            if(temp.next == null) {
                temp.next = new Item<>();
                temp.value = value;
                break;
            }
            temp = temp.next;
        }
        ++length;
    }
    @Override
    public void deleteFirst(){
        item = item.next;
        --length;
    }
    @Override
    public void deleteLast(){
        Item<T> temp = item;
        while(true){
            if(temp.next.next == null) {
                temp.next = null;
                break;
            }
            temp = temp.next;
        }
        --length;
    }
    public void show(){
        Item<T> temp = item;
        while (temp.next != null){
            System.out.println(temp.value);
            temp = temp.next;
        }
    }
}

