package homeWork2.server.repository;

import java.io.FileReader; // Импорт класса FileReader для чтения из файла
import java.io.FileWriter; // Импорт класса FileWriter для записи в файл

/**
 * Класс FileStorage реализует интерфейс Repository для хранения истории сообщений в файле.
 * Обеспечивает сохранение и загрузку истории сообщений из файла.
 */
public class FileStorage implements Repository<String> {
    private static final String LOG_PATH = "src/main/java/homeWork2/server/repository/history.txt"; // Путь к файлу хранения истории

    /**
     * Метод для сохранения текста в файл истории.
     *
     * @param text Текст для сохранения в файл.
     */
    @Override
    public void save(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) { // Открываем файл для записи в режиме добавления
            writer.write(text); // Записываем текст в файл
            writer.write("\n"); // Добавляем символ новой строки
        } catch (Exception e) { // Обработка исключений
            e.printStackTrace(); // Вывод трассировки стека при возникновении исключения
        }
    }

    /**
     * Метод для загрузки истории из файла.
     *
     * @return Строка, содержащая историю, или null в случае ошибки.
     */
    @Override
    public String load() {
        StringBuilder stringBuilder = new StringBuilder(); // Создание объекта StringBuilder для построения строки из содержимого файла
        try (FileReader reader = new FileReader(LOG_PATH);) { // Открываем файл для чтения
            int c; // Переменная для хранения символа, прочитанного из файла
            while ((c = reader.read()) != -1) { // Чтение файла посимвольно до конца
                stringBuilder.append((char) c); // Добавление прочитанного символа в StringBuilder
            }
            // stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length()); // Удаление последнего символа (новая строка, если она была)
            return stringBuilder.toString(); // Возвращение строки с историей
        } catch (Exception e) { // Обработка исключений
            e.printStackTrace(); // Вывод трассировки стека при возникновении исключения
            return null; // Возврат null в случае ошибки
        }
    }
}
