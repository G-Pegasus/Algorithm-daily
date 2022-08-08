package array;

public class TestArray {

    public static void main(String[] args) {
        ArrayList<Integer> arrayInt = new ArrayList<>();
        arrayInt.add(11);
        arrayInt.add(12);
        arrayInt.add(13);
        arrayInt.remove(1);
        arrayInt.add(2, 14);
        System.out.println(arrayInt);
        arrayInt.set(1, 12);
        System.out.println(arrayInt);
        System.out.println(arrayInt.size());
        System.out.println(arrayInt.get(1));
        System.out.println(arrayInt.contains(15));
        System.out.println(arrayInt.indexOf(11));
        System.out.println(arrayInt.isEmpty());
        arrayInt.clear();
        System.out.println(arrayInt);

        ArrayList<Float> arrayFloat = new ArrayList<>();
        arrayFloat.add(10.5f);
        arrayFloat.add(3.14f);
        arrayFloat.add(5.56f);
        System.out.println(arrayFloat);
    }
}
