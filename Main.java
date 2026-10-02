import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<CommunicationDevice> devices = new ArrayList<>();
        devices.add(new Smartphone("Samsung", "S24", 12, 6.2, 50, 100));
        devices.add(new Radio("Baofeng", "UV-5R", 145.5, "VHF", 10, 5));
        devices.add(new SatellitePhone("Iridium", "Extreme", "Iridium-1", 80, 90));
        System.out.println("Количество устройств: " + devices.size());
    }
}