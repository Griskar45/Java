public class Smartphone extends CommunicationDevice {
    private int ram;
    private double screenSize;
    private double cameraMegaPixels;
    private int batteryLevel;
    public Smartphone(String manufacturer, String model, int ram, double screenSize, double cameraMegaPixels, int batteryLevel){
        super(manufacturer, model);
        this.ram = ram;
        this.screenSize = screenSize;
        this.cameraMegaPixels = cameraMegaPixels;
        this.batteryLevel = batteryLevel;
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
            System.out.println("Сообщение отправлено контакту " + contact);
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
    public void randomizeBatteryLevel() {
        batteryLevel = (int) (Math.random() * 101);
        System.out.println("Новый уровень заряда: " + batteryLevel + "%");
    }

    public void useBattery() {
        if (batteryLevel > 0) {
            batteryLevel--;
            System.out.println("Заряд: " + batteryLevel + "%");
        } else {
            System.out.println("Батарея разряжена");
        }
    }
    public void chargeBattery() {
        if (batteryLevel >= 100) {
            System.out.println("Батарея уже полностью заряжена");
        } else {
            batteryLevel += 10;

            if (batteryLevel > 100) {
                batteryLevel = 100;
            }

            System.out.println("Заряд: " + batteryLevel + "%");
        }
    }
    public int getBatteryLevel() {
        return batteryLevel;
    }

}
