import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceFileReader {
    private int totalDevices = 0;
    private int successfulProps = 0;
    private int missingProps = 0;

    public List<CommunicationDevice> readFromFile(String filename) {
        List<CommunicationDevice> devices = new ArrayList<>();

        //Сброс статы
        totalDevices = 0;
        successfulProps = 0;
        missingProps = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            Map<String, String> currentData = new HashMap<>();
            boolean inObject = false;

            //скипаем пустые строки
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // начало нового объекта
                if (line.startsWith("Type:")) {
                    if (inObject && !currentData.isEmpty()) {
                        CommunicationDevice device = buildDevice(currentData);
                        if (device != null) devices.add(device);
                    }
                    currentData.clear();
                    inObject = true;
                }

                // парсинг строки "Ключ: Значение,"
                if (inObject && line.contains(":")) {
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        // Удаляем запятую в конце, если она есть
                        if (value.endsWith(",")) {
                            value = value.substring(0, value.length() - 1).trim();
                        }
                        currentData.put(key, value);
                    }
                }
            }
            if (inObject && !currentData.isEmpty()) {
                CommunicationDevice device = buildDevice(currentData);
                if (device != null) devices.add(device);
            }
        }  catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }
        return devices;
    }

    private CommunicationDevice buildDevice(Map<String, String> data) {
        String type = data.getOrDefault("Type", "").trim();
        if (type.isEmpty()) return null;

        totalDevices++;

        String manufacturer = getString(data, "Manufacturer", "Unknown");
        String model = getString(data, "Model", "Unknown");

        if (type.equalsIgnoreCase("Smartphone")) {
            int ram = getInt(data, "RAM", 4);
            double screenSize = getDouble(data, "ScreenSize", 6.0);
            double camera = getDouble(data, "Camera", 12.0);
            int battery = getInt(data, "Battery", 100);
            return new Smartphone(manufacturer, model, ram, screenSize, camera, battery);

        } else if (type.equalsIgnoreCase("Radio")) {
            double frequency = getDouble(data, "Frequency", 100.0);
            String band = getString(data, "Band", "FM");
            int range = getInt(data, "Range", 5);
            int volume = getInt(data, "Volume", 5);
            return new Radio(manufacturer, model, frequency, band, range, volume);

        } else if (type.equalsIgnoreCase("SatellitePhone")) {
            String satName = getString(data, "SatelliteName", "DefaultSat");
            int signal = getInt(data, "SignalStrength", 50);
            int battery = getInt(data, "Battery", 100);
            // Предполагаем, что конструктор SatellitePhone принимает эти 5 параметров
            return new SatellitePhone(manufacturer, model, satName, signal, battery);
        }

        return null;
    }
    private String getString(Map<String, String> data, String key, String defaultVal) {
        if (data.containsKey(key)) {
            successfulProps++;
            return data.get(key);
        } else {
            missingProps++;
            return defaultVal;
        }
    }
    private int getInt(Map<String, String> data, String key, int defaultVal) {
        if (data.containsKey(key)) {
            try {
                int val = Integer.parseInt(data.get(key));
                successfulProps++;
                return val;
            } catch (NumberFormatException e) {
                missingProps++;
                return defaultVal;
            }
        } else {
            missingProps++;
            return defaultVal;
        }
    }
    private double getDouble(Map<String, String> data, String key, double defaultVal){
        if (data.containsKey(key)) {
            try {
                double val = Double.parseDouble(data.get(key).replace(',', '.'));
                successfulProps++;
                return val;
            } catch (NumberFormatException e) {
                missingProps++;
                return defaultVal;
            }
        } else {
            missingProps++;
            return defaultVal;
        }
    }
    public int getTotalDevices() { return totalDevices; }
    public int getSuccessfulProps() { return successfulProps; }
    public int getMissingProps() { return missingProps; }
}