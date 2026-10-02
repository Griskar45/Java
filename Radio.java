public class Radio extends CommunicationDevice {
    private double frequency;
    private String band;
    private int range;

    public Radio(String manufacturer, String model, double frequency, String band, int range){
        super(manufacturer, model);
        this.frequency = frequency;
        this.band = band;
        this.range = range;
    }

    public double getFrequency() {
        return frequency;
    }

    public String getBand() {
        return band;
    }

    public int getRange() {
        return range;
    }

    @Override
    public void sendMessage(String contact, String message) {
        if (!isTurnedOn()) {
            System.out.println("Радио выключено. Отправка невозможна");
        } else if (!isConnected()) {
            System.out.println("Радио не подключено");
        } else if (!hasContact(contact)) {
            System.out.println("Контакт не найден");
        } else if (message.isBlank()) {
            System.out.println("Сообщение пустое");
        } else {
            System.out.println("Радиосообщение отправлено контакту " + contact + " на частоте " + frequency + " МГц");
        }
    }


}
