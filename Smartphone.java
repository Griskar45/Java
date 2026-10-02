public class Smartphone extends CommunicationDevice {
    public Smartphone(String manufacturer, String model){
        super(manufacturer, model);
    }

    @Override
    public void sendMessage(String contact, String message) {
        if(!isTurnedOn()){
            System.out.println("Отправка невозможна, устройство выключено");
        }
        else if (!isConnected()){
            System.out.println("Отправка невозможна, нет подключения");
        }
        else if(!hasContact(contact)){
            System.out.println("Контакт не найден");
        }
        else if (message.isBlank()){
            System.out.println("Сообщение пустое");
        }
        else {
            System.out.println("Сообщение отправлено контакту" + contact);
        }
    }
}
