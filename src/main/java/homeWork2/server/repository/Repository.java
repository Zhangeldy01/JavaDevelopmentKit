package homeWork2.server.repository;

/**
 * Интерфейс Repository определяет методы для сохранения и загрузки данных.
 *
 * @param <T> Тип данных, которые хранятся в репозитории.
 */
public interface Repository<T> {
    /**
     * Метод для сохранения данных.
     *
     * @param text Данные для сохранения.
     */
    void save(T text);

    /**
     * Метод для загрузки данных.
     *
     * @return Загруженные данные.
     */
    T load();
}
