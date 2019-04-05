package br.univali.kob.model;


import java.util.Arrays;
import java.util.Objects;

/**
 * Generic class for manipulate array.
 * @param <T>
 */
public class List<T> {
    /**
     * Array max size.
     */
    private int length;
    /**
     * Array "real"size.
     */
    private int size;
    /**
     * The array.
     */
    private Object[] values;

    /**
     * Default List<T> constructor.
     * @param length: vector max size.
     */
    public List(int length) {
        this.length = length;
        this.values = new Object[length];
    }

    /**
     * Deep copy constructor
     * @param list: object that will be copied.
     */
    public List(List<T> list) {
        this.length = list.getLength();
        this.values = list.getValues();
    }

    /**
     * Get array real size.
     * @return size class attribute (int).
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Get array max size.
     * @return length class attribute (int).
     */
    public int getLength() {
        return length;
    }

    /**
     * Get array.
     * @return values class attribute (Object[]).
     */
    public Object[] getValues() {
        return values;
    }

    /**
     * Verify if the array is empty.
     * @return true if the array is empty, and false if not (boolean).
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Verify if the array has space for more elements.
     * @return true if the array has space, and false if not (boolean).
     */
    public boolean hasSpace() {
        return this.size < this.length;
    }

    /**
     * Verify if the element is inside the array.
     * @param element: element that will be verify if the array has.
     * @return true if the array has space, and false if not (boolean).
     */
    public boolean isElementOnList(Object element) {
        for (int i = 0; i < this.size; i++) {
            if (this.values[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPositionValid(int i) {
        return i <= this.size;
    }

    /**
     * Get element on array in position i.
     * @param i: index of the element on array.
     * @return element on the position given (Object).
     */
    public Object getElement(int i) {
        return this.values[i];
    }

    /**
     * Get element position on array.
     * @param element: element that will be find your position on the array.
     * @return index of the element given (int).
     */
    public int getElementPosition(Object element) {
        for (int i = 0; i < this.size; i++) {
            if (this.values[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Insert a element on the array.
     * @param element:the element that will inserted.
     */
    public void insert(Object element) {
        if (this.size < this.length) {
            this.values[this.size] = element;
            this.size++;
        }
    }

    /**
     * Insert element on the specific position.
     * @param element: the element that will be inserted.
     * @param index: the position the element will be inserted.
     */
    public void insertIntoTo(Object element, int index) {
        if (this.isPositionValid(index)) {
            this.size++;
            Object newArray[] = copy();
            Object holder = this.values[index];
            newArray[index] = element;
            for(int i = index + 1; i < this.size; i++) {
                Object nextHolder = this.values[i];
                newArray[i] = holder;
                holder = nextHolder;
            }
            this.values = newArray;
        }

    }

    /**
     * Remove a element from array.
     * @param index: position of the element that will be removed from the array.
     */
    public void remove(int index) {
        if (this.isPositionValid(index)) {
            Object newArray[] = copy();
            for (int i = index; i < this.size - 1; i++) {
                newArray[i] = newArray[i + 1];
            }
            this.size--;
            newArray[size] = new Object();
            this.values = newArray;
        }
    }

    /**
     * Sorted the array.
     */
    public void sort() {
        Arrays.sort(this.values);
    }

    /**
     * Deep coping the array.
     * @return array that will be a deep copy of the values on this array.
     */
    public List<T> deepCopy() {
        List<T> obj = new List<>(this.length);
        obj.values = copy();
        return obj;
    }

    /**
     * Find a element and replace for other (on entire array).
     * @param objToRemove: object that will be replaced.
     * @param objToChange: object that will be replace the other.
     */
    public void findAndReplace(Object objToRemove, Object objToChange) {
        if (this.size >= 1) {
            for (int i = 0; i < this.size; i++) {
                if (this.values[i].equals(objToRemove)) {
                    this.values[i] = objToChange;
                }
            }
        }
    }

    /**
     * Print the array on the console.
     */
    public void print() {
        System.out.print("Values = [");
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.values[i]);
            if ((i + 1) == this.size) {
                System.out.print("]");
            } else {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    /**
     * Convert a Object for the class type of the List<T>
     * @param o: the object tha will be converted.
     * @param clazz: the class that the object wil be converted.
     * @param <T>: type of the class will be converted.
     * @return element Object converted to the type of class passed.
     */
    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch(ClassCastException e) {
            return null;
        }
    }

    private Object[] copy() {
        Object listCopied[] = new Object[this.length];
        for (int i = 0; i < this.size; i++) {
            listCopied[i] = this.values[i];
        }
        return listCopied;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof List)) return false;
        List<?> that = (List<?>) o;
        return length == that.length &&
                size == that.size &&
                Arrays.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(length, size);
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }
}
