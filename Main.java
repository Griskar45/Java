import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int readInt(Scanner scanner, String text, int min, int max) {
        while (true) {
            System.out.print(text);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value < min || value > max) {
                    System.out.println("Введите число от " + min + " до " + max);
                } else {
                    return value;
                }

            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести целое число");
            }
        }
    }
    public static double readDouble(Scanner scanner, String text, double min, double max) {
        while (true) {
            System.out.print(text);
            String input = scanner.nextLine().trim().replace(',', '.');

            try {
                double value = Double.parseDouble(input);

                if (value < min || value > max) {
                    System.out.println("Введите число от " + min + " до " + max);
                } else {
                    return value;
                }

            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести число");
            }
        }
    }
    public static String readString(Scanner scanner, String text, int minLength, int maxLength) {
        while (true) {
            System.out.print(text);
            String value = scanner.nextLine().trim();

            if (value.length() < minLength) {
                System.out.println("Слишком короткое значение");
            } else if (value.length() > maxLength) {
                System.out.println("Максимум символов: " + maxLength);
            } else {
                return value;
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<CommunicationDevice> devices = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        devices.add(new Smartphone("Samsung", "S24", 12, 6.2, 50, 100));
        devices.add(new Radio("Baofeng", "UV-5R", 145.5, "VHF", 10, 5));
        devices.add(new SatellitePhone("Iridium", "Extreme", "Iridium-1", 80, 90));

        int choice;

        do {
            System.out.println("\n=== МЕНЮ ===");
            System.out.println("1. Показать устройства");
            System.out.println("2. Добавить устройство");
            System.out.println("3. Удалить устройство");
            System.out.println("4. Изменить устройство");
            System.out.println("5. Работа с устройством");
            System.out.println("0. Выход");

            choice = readInt(scanner, "Выберите пункт: ", 0, 5);

            switch (choice) {

                case 1: {
                    if (devices.isEmpty()) {
                        System.out.println("Список устройств пуст");
                    } else {
                        for (CommunicationDevice device : devices) {
                            System.out.println(device);
                        }
                    }
                    break;
                }

                case 2: {
                    System.out.println("1. Смартфон");
                    System.out.println("2. Радио");
                    System.out.println("3. Спутниковый телефон");
                    int type = readInt(scanner,"Тип устройства: ", 1,3);

                    String manufacturer = readString(scanner, "Производитель: ", 1, 30);
                    String model = readString(scanner, "Модель: ", 1, 30);

                    if (type == 1) {
                        int ram = readInt(scanner, "RAM: ", 1,128);


                        double screenSize = readDouble(scanner, "Размер экрана (1-20): ", 1, 20);
                        double cameraMegaPixels = readDouble(scanner, "Мегапиксели камеры (1-500): ", 1, 500);
                        int batteryLevel = readInt(scanner, "Заряд батареи: ", 1,100);

                        devices.add(new Smartphone(manufacturer, model, ram, screenSize, cameraMegaPixels, batteryLevel));
                        System.out.println("Смартфон добавлен");
                    }

                    else if (type == 2) {

                        double frequency = readDouble(scanner, "Частота (0.1-1000 МГц): ", 0.1, 1000);

                        String band = readString(scanner, "Диапазон: ", 1, 15);

                        int range = readInt(scanner, "Дальность: ", 0, 1_000_000_000 );

                        int volume = readInt(scanner, "Громкость: ", 1,100);

                        devices.add(new Radio(manufacturer, model, frequency, band, range, volume));
                        System.out.println("Радио добавлено");
                    }

                    else if (type == 3) {

                        String satelliteName = readString(scanner, "Название спутника: ", 1, 32);


                        int signalStrength = readInt(scanner,"Уровень сигнала(1-100): ",1,100);

                        int batteryLevel = readInt(scanner,"Заряд батареи(1-100): ",1,100);

                        devices.add(new SatellitePhone(manufacturer, model, satelliteName, signalStrength, batteryLevel));
                        System.out.println("Спутниковый телефон добавлен");
                    }

                    else {
                        System.out.println("Нет такого типа");
                    }

                    break;
                }

                case 3: {

                    int deleteId = readInt(scanner, "Введите ID устройства: ",1, devices.size());

                    boolean removed = devices.removeIf(device -> device.getId() == deleteId);

                    if (removed) {
                        System.out.println("Устройство удалено");
                    } else {
                        System.out.println("Устройство не найдено");
                    }

                    break;
                }

                case 4: {
                    System.out.print("Введите ID устройства: ");
                    int editId = readInt(scanner, "Введите ID устройства: ",1, devices.size());;

                    CommunicationDevice foundDevice = null;

                    for (CommunicationDevice device : devices) {
                        if (device.getId() == editId) {
                            foundDevice = device;
                            break;
                        }
                    }

                    if (foundDevice == null) {
                        System.out.println("Устройство не найдено");
                        break;
                    }

                    String manufacturer = readString(scanner, "Новый производитель: ", 1, 32 );

                    String model = readString(scanner, "Новая модель: ", 1, 32 );

                    foundDevice.setManufacturer(manufacturer);
                    foundDevice.setModel(model);

                    System.out.println("Данные изменены");
                    break;
                }

                case 5: {
                    int id = readInt(scanner, "Введите ID устройства: ",1, devices.size());;

                    CommunicationDevice device = null;

                    for (CommunicationDevice d : devices) {
                        if (d.getId() == id) {
                            device = d;
                            break;
                        }
                    }

                    if (device == null) {
                        System.out.println("Устройство не найдено");
                        break;
                    }

                    int action;

                    do {
                        System.out.println("\nУстройство: " + device);
                        System.out.println("1. Включить");
                        System.out.println("2. Выключить");
                        System.out.println("3. Подключиться к сети");
                        System.out.println("4. Отключиться от сети");
                        System.out.println("5. Добавить контакт");
                        System.out.println("6. Удалить контакт");
                        System.out.println("7. Показать контакты");
                        System.out.println("8. Отправить сообщение");

                        if (device instanceof Smartphone) {
                            System.out.println("9. Случайный заряд батареи");
                            System.out.println("10. Потратить заряд");
                            System.out.println("11. Зарядить телефон");
                        }

                        else if (device instanceof Radio) {
                            System.out.println("9. Увеличить громкость");
                            System.out.println("10. Уменьшить громкость");
                            System.out.println("11. Случайная громкость");
                            System.out.println("12. Изменить частоту");
                        }

                        else if (device instanceof SatellitePhone) {
                            System.out.println("9. Случайный уровень сигнала");
                            System.out.println("10. Случайный заряд батареи");
                            System.out.println("11. Отправить SOS");
                            System.out.println("12. Отменить SOS");
                        }

                        System.out.println("0. Назад");
                        System.out.print("Выберите действие: ");

                        action = readInt(scanner, "Выберите действие: ",0, 12);

                        switch (action) {

                            case 1:
                                device.turnOn();
                                break;

                            case 2:
                                device.turnOff();
                                break;

                            case 3:
                                device.connect();
                                break;

                            case 4:
                                device.disconnect();
                                break;

                            case 5: {
                                String contact = readString(scanner, "Введите контакт: ", 1,32);
                                device.addContact(contact);
                                break;
                            }

                            case 6: {
                                String contact = readString(scanner, "Введите контакт: ", 1,32);
                                device.removeContact(contact);
                                break;
                            }

                            case 7:
                                device.showContacts();
                                break;

                            case 8: {
                                String contact = readString(scanner, "Контакт: ", 1,32);


                                String message = readString(scanner, "Сообщение: ", 1,32);

                                device.sendMessage(contact, message);
                                break;
                            }

                            case 9: {
                                if (device instanceof Smartphone) {
                                    Smartphone phone = (Smartphone) device;
                                    phone.randomizeBatteryLevel();
                                }

                                else if (device instanceof Radio) {
                                    Radio radio = (Radio) device;
                                    radio.volumeUp();
                                }

                                else if (device instanceof SatellitePhone) {
                                    SatellitePhone phone = (SatellitePhone) device;
                                    phone.randomizeSignalStrength();
                                }

                                break;
                            }

                            case 10: {
                                if (device instanceof Smartphone) {
                                    Smartphone phone = (Smartphone) device;
                                    phone.useBattery();
                                }

                                else if (device instanceof Radio) {
                                    Radio radio = (Radio) device;
                                    radio.volumeDown();
                                }

                                else if (device instanceof SatellitePhone) {
                                    SatellitePhone phone = (SatellitePhone) device;
                                    phone.randomizeBatteryLevel();
                                }

                                break;
                            }

                            case 11: {
                                if (device instanceof Smartphone) {
                                    Smartphone phone = (Smartphone) device;
                                    phone.chargeBattery();
                                }

                                else if (device instanceof Radio) {
                                    Radio radio = (Radio) device;
                                    radio.randomizeVolume();
                                }

                                else if (device instanceof SatellitePhone) {
                                    SatellitePhone phone = (SatellitePhone) device;
                                    phone.sendSOS();
                                }

                                break;
                            }

                            case 12: {
                                if (device instanceof Radio) {
                                    Radio radio = (Radio) device;


                                    double frequency = readDouble(scanner,"Новая частота: ",1,1000);

                                    radio.tuneFrequency(frequency);
                                }

                                else if (device instanceof SatellitePhone) {
                                    SatellitePhone phone = (SatellitePhone) device;
                                    phone.cancelSOS();
                                }

                                break;
                            }

                            case 0:
                                break;

                            default:
                                System.out.println("Нет такого действия");
                        }

                    } while (action != 0);

                    break;
                }

                case 0:
                    System.out.println("Выход");
                    break;

                default:
                    System.out.println("Нет такого пункта");
            }

        } while (choice != 0);

        scanner.close();
    }
}