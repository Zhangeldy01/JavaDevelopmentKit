package seminar3.tasks.task2_MyCollection;

/*
Описать собственную коллекцию - список на основе массива.
Коллекция должна иметь возможность хранить любые типы данных, иметь методы добавления и удаления метода.
 */
public class App {
    public static void main(String[] args) {
        MyCollection<String> myCollection = new MyCollection<>();
        myCollection.add("Anna");
        myCollection.add("Alsu");
        myCollection.add("Morris");
        myCollection.add("Chack");

        System.out.println(myCollection);

        myCollection.remove((2));
        myCollection.remove("Anna");

        System.out.println(myCollection);

    }



}
