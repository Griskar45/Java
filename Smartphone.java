public class Smartphone extends CommunicationDevice {
    private int ram;
    private double screenSize;
    private double cameraMegaPixels;
    public Smartphone(String manufacturer, String model, int ram, double screenSize, double cameraMegaPixels){
        super(manufacturer, model);
        this.ram = ram;
        this.screenSize = screenSize;
        this.cameraMegaPixels = cameraMegaPixels;
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
    public int getRam() {
        return ram;
    }
    public double getScreenSize() {
        return screenSize;
    }
    public double getCameraMegaPixels() {
        return cameraMegaPixels;
    }

}
