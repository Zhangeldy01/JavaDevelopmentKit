package homeWork2.client.ui;

public interface ClientView {

    /**
     * Метод для отображения сообщения.
     * @param message Сообщение для отображения.
     */
    void showMessage(String message);

    /**
     * Метод для отключения от сервера.
     */
    void disconnectFromServer();
}
