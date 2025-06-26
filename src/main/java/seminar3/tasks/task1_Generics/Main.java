package seminar3.tasks.task1_Generics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream; // Импорт класса ByteArrayInputStream
import java.io.DataInputStream; // Импорт класса DataInputStream
import java.io.IOException; // Импорт класса IOException

/*
* Создать обобщенный классс тремя параметрами (T, V, K).
Класс содержит три переменные типа (T, V, K),
конструктор, принимающий на вход параметры типа (T, V, K),
методы возвращающие значения трех переменных.

Создать метод, выводящий на консоль имена классов для трех переменных класса.
Наложить ограничения на параметры типа:
Т должен реализовать интерфейс Comparable,
V должен реализовать интерфейс DataInput  и расширять класс InputStream,
K должен расширять класс Number.
 */

/**
 * Класс Main для демонстрации использования класса GenericsClass.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        // Пример использования Genericsclass с Integer, DataInputStream и Double
        Integer first = 10; // Создание объекта Integer
        byte[] byteArray = {72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33}; // Создание массива байтов (Hello World!)
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArray); // Создание ByteArrayInputStream из массива байтов
        DataInputStream second = new DataInputStream(bais); // Создание DataInputStream на основе ByteArrayInputStream
        Double third = 3.14; // Создание объекта Double

        GenericsClass<Integer, DataInputStream, Double> genericsObject = new GenericsClass<>(first, second, third); // Создание экземпляра GenericsClass

        System.out.println("First variable: " + genericsObject.getTVarName()); // Вывод значения первой переменной (Integer)

        // Способ 1: Чтение массива байтов из DataInputStream (через InputStream)
        try {
            byte[] buffer = readByteArray(second, byteArray.length);
            //second.readFully(buffer); // Читаем все байты из DataInputStream в буфер
            System.out.print("Second variable (Byte array): ");
            for(byte b : buffer) { // Перебираем массив байтов
                System.out.print(b + " "); // Выводим каждый байт в консоль
            }
            System.out.println();
            // Востанавливаем DataInputStream для следующего чтения (обязательно!)
            bais.reset(); // Перемещаем указатель обратно в начало ByteArrayInputStream
        } catch (IOException e) { // Обработка исключения IOException
            // System.err.println("error reading byte array: " + e.getMessage()); // Вывод сообщения об ошибке
            logger.error("Error reading byte array: {}", e.getMessage(), e); // Логируем ошибку
        }

        // Способ 2: Чтение массива как строки (только если это текст)
        // Простое преобразование в строку не даст желаемого результата (получится хэш-код)
        System.out.println("Second variable: " + genericsObject.getVVarName()); // Выведется в виде heshcode

        // Чтобы вывести содержимое в виде строки, нужно прочитать данные из потока InputStream  и преобразовать их в строку.
        // Читаем данные из DataInputStream и преобразуем их в строку
//        try {
//            StringBuilder sb = new StringBuilder(); // Создание StringBuilder для построения строки
//            int data; // Переменная для хранения прочитанного байта
//            bais.reset(); // Перемещаем указатель в начало, если уже читали
//            while((data = second.read()) != -1) { // Читаем данные из DataInputStream пока не достигнем конца потока
//                sb.append((char)data); // Преобразуем байт в символ и добавляем в StringBuilder
//
//            }
//            System.out.println("Second variable (String): " + sb.toString()); // Вывод строки
//        } catch (IOException e) { // Обработка исключения IOException
//            System.err.println("error reading as String: " + e.getMessage()); // Вывод сообщения об ошибке
//        } finally { // Важно закрывать InputStream'ы, когда они больше не нужны
//            try {
//                second.close(); // Закрываем DataInputStream
//                bais.close(); // Закрываем ByteArrayInputStream
//            } catch (IOException e) { // Обработка исключения IOException
//                System.err.println("Error closing streams: " + e.getMessage()); // Вывод сообщения об ошибке
//            }
//        }
        try {
            bais.reset(); // Перемещаем указатель в начало, если уже читали
            String stringValue = readStringFromDataInputStream(second);
            System.out.println("Second variable (String): " + stringValue); // Вывод строки
        } catch (IOException e) { // Обработка исключения IOException
            // System.err.println("error reading as String: " + e.getMessage()); // Вывод сообщения об ошибке
            logger.error("Error reading as String: {}", e.getMessage(), e); // Логируем ошибку

        } finally { // Важно закрывать InputStream'ы, когда они больше не нужны
            try {
                second.close(); // Закрываем DataInputStream
                bais.close(); // Закрываем ByteArrayInputStream
            } catch (IOException e) { // Обработка исключения IOException
                // System.err.println("Error closing streams: " + e.getMessage()); // Вывод сообщения об ошибке
                logger.error("Error closing streams: {}", e.getMessage(), e); // Логируем ошибку
            }
        }

            System.out.println("Third variable: " + genericsObject.getKVarName()); // Вывод значения третьей переменной (Double)

            genericsObject.printNamesOfClasses(); // Выведет имена классов

    }
    private static byte[] readByteArray(DataInputStream dis, int length) throws IOException {
        byte[] buffer = new byte[length];
        dis.readFully(buffer); // Читаем все байты из DataInputStream в буфер
        return buffer;
    }

    private static String readStringFromDataInputStream(DataInputStream dis) throws IOException {
        StringBuilder sb = new StringBuilder(); // Создание StringBuilder для построения строки
        int data; // Переменная для хранения прочитанного байта
        while ((data = dis.read()) != -1) { // Читаем данные из DataInputStream пока не достигнем конца потока
            sb.append((char) data); // Преобразуем байт в символ и добавляем в StringBuilder
        }
        return sb.toString();
    }
}
