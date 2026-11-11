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
        }
    }
}