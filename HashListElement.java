public class HashListElement<T> {
        private int key;
        private int counter;
        private T value;
        private HashListElement<T> next;

        public HashListElement(int key, T value, HashListElement<T> next){
            if(key<0 | value==null)
                throw new RuntimeException();
            this.key=key;
            this.value=value;
            this.next=next;
            counter=1;
        }

        public T getValue(){return value;}
        public int getKey(){return this.key;}
        public int getCounter(){return this.counter;}
        public HashListElement<T> getNext(){return next;}
        public void setNext(HashListElement<T> next){this.next=next;}
        public void setCounter(){counter++;}
        public void setValue(T value){this.value=value;}
        public void setKey(int key){this.key=key;}
}
