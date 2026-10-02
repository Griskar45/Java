public class Radio extends CommunicationDevice {
    private double frequency;
    private String band;
    private int range;
    private int volume;

    public Radio(String manufacturer, String model, double frequency, String band, int range, int volume){
        super(manufacturer, model);
        this.frequency = frequency;
        this.band = band;
        this.range = range;
        this.volume = volume;
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
    public void volumeUp() {
        if (volume < 10) {
            volume++;
            System.out.println("Громкость: " + volume);
        } else {
            System.out.println("Максимальная громкость");
        }
    }

    public void volumeDown() {
        if (volume > 0) {
            volume--;
            System.out.println("Громкость: " + volume);
        } else {
            System.out.println("Минимальная громкость");
        }
    }

    public void randomizeVolume() {
        volume = (int) (Math.random() * 11);
        System.out.println("Случайная громкость: " + volume);
    }

    public void tuneFrequency(double newFrequency) {
        if (newFrequency > 0) {
            frequency = newFrequency;
            System.out.println("Новая частота: " + frequency + " МГц");
        } else {
            System.out.println("Некорректная частота");
        }
    }
    public int getVolume() {
        return volume;
    }


}
