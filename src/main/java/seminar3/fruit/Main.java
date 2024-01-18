package seminar3.fruit;

/*
* Есть классы Fruit -> Apple, Orange; (больше не надо);
* Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку
  нельзя сложить и яблоки, и апельсины; Для хранеения фруктов внутри коробки можете использовать ArrayList;
* Сделать метод getWeight() который высчиывает вес коробки, зная количество фруктов и вес одного фрукта
  (вес яблока - 1,0f, апельсина - 1.5f не важно в каких еденицах);
* Внутри класса коробки сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут
  в compare() в качестве параметра, true - если их веса равны, false  в противном случае (коробки с яблоками возможно
  сравнивать с коробками с апельсинами);
* Написать метод, который позволяет пересыпать фрукты  из текущей коробки в другую коробку (при этом, нельзя яблоки
  высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются
  объекты, которые были в этой коробке.
  */
public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Fruit> fruitBox = new Box<>();

        fruitBox.add(new Orange());
        fruitBox.add(new Apple());

        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        orangeBox.add(new Orange());
        orangeBox.add(new Orange());

        System.out.println(appleBox.compareByWeight(orangeBox));
        appleBox.transferTo(fruitBox);
        System.out.println(fruitBox);
    }
}
