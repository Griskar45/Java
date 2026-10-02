import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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
            System.out.print("Выберите пункт: ");

            choice = Integer.parseInt(scanner.nextLine());

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
                    System.out.print("Тип устройства: ");
                    int type = Integer.parseInt(scanner.nextLine());

                    System.out.print("Производитель: ");
                    String manufacturer = scanner.nextLine();

                    System.out.print("Модель: ");
                    String model = scanner.nextLine();

                    if (type == 1) {
                        System.out.print("RAM: ");
                        int ram = Integer.parseInt(scanner.nextLine());

                        System.out.print("Размер экрана: ");
                        double screenSize = Double.parseDouble(scanner.nextLine());

                        System.out.print("Мегапиксели камеры: ");
                        double cameraMegaPixels = Double.parseDouble(scanner.nextLine());

                        System.out.print("Заряд батареи: ");
                        int batteryLevel = Integer.parseInt(scanner.nextLine());

                        devices.add(new Smartphone(manufacturer, model, ram, screenSize, cameraMegaPixels, batteryLevel));
                        System.out.println("Смартфон добавлен");
                    }

                    else if (type == 2) {
                        System.out.print("Частота: ");
                        double frequency = Double.parseDouble(scanner.nextLine());

                        System.out.print("Диапазон: ");
                        String band = scanner.nextLine();

                        System.out.print("Дальность: ");
                        int range = Integer.parseInt(scanner.nextLine());

                        System.out.print("Громкость: ");
                        int volume = Integer.parseInt(scanner.nextLine());

                        devices.add(new Radio(manufacturer, model, frequency, band, range, volume));
                        System.out.println("Радио добавлено");
                    }

                    else if (type == 3) {
                        System.out.print("Название спутника: ");
                        String satelliteName = scanner.nextLine();

                        System.out.print("Уровень сигнала: ");
                        int signalStrength = Integer.parseInt(scanner.nextLine());

                        System.out.print("Заряд батареи: ");
                        int batteryLevel = Integer.parseInt(scanner.nextLine());

                        devices.add(new SatellitePhone(manufacturer, model, satelliteName, signalStrength, batteryLevel));
                        System.out.println("Спутниковый телефон добавлен");
                    }

                    else {
                        System.out.println("Нет такого типа");
                    }

                    break;
                }

                case 3: {
                    System.out.print("Введите ID устройства: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());

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
                    int editId = Integer.parseInt(scanner.nextLine());

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

                    System.out.print("Новый производитель: ");
                    String manufacturer = scanner.nextLine();

                    System.out.print("Новая модель: ");
                    String model = scanner.nextLine();

                    foundDevice.setManufacturer(manufacturer);
                    foundDevice.setModel(model);

                    System.out.println("Данные изменены");
                    break;
                }

                case 5: {
                    System.out.print("Введите ID устройства: ");
                    int id = Integer.parseInt(scanner.nextLine());

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

                        action = Integer.parseInt(scanner.nextLine());

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
                                System.out.print("Введите контакт: ");
                                String contact = scanner.nextLine();
                                device.addContact(contact);
                                break;
                            }

                            case 6: {
                                System.out.print("Введите контакт: ");
                                String contact = scanner.nextLine();
                                device.removeContact(contact);
                                break;
                            }

                            case 7:
                                device.showContacts();
                                break;

                            case 8: {
                                System.out.print("Контакт: ");
                                String contact = scanner.nextLine();

                                System.out.print("Сообщение: ");
                                String message = scanner.nextLine();

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

                                    System.out.print("Новая частота: ");
                                    double frequency = Double.parseDouble(scanner.nextLine());

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