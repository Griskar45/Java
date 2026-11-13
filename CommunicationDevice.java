import java.util.ArrayList;
import java.util.List;

public abstract class CommunicationDevice {
    private int id;
    private String manufacturer;
    private String model;
    private List<Contact> contacts;
    private boolean connected;
    private static int nextId = 1;
    private boolean turnedOn;
    public CommunicationDevice(String manufacturer, String model){
        this.model = model;
        this.manufacturer = manufacturer;
        id = nextId;
        nextId++;
        this.contacts = new ArrayList<>();
        this.connected = false;
        this.turnedOn = false;
    }
    public int getId(){
        return id;
    }

    public String getManufacturer(){
        return manufacturer;
    }
    public String getModel(){
        return model;
    }
    public boolean isConnected(){
        return connected;
    }
    public boolean isTurnedOn() {
        return turnedOn;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public void turnOn(){
        if (isTurnedOn()){
            System.out.println("Устройство уже включено");
        }
        else {
            turnedOn = true;
            System.out.println("Устройство включено");
        }
    }
    public void turnOff(){
        if (!isTurnedOn()){
            System.out.println("Устройство уже выключено");
        }
        else {
            turnedOn = false;
            connected = false;
            System.out.println("Устройство выключено");
        }
    }
    public void connect(){
        if(!isTurnedOn()){
            System.out.println("Подключиться нельзя");
        }
        else{
         if (isConnected()){
             System.out.println("Уже подключено");
         }
         else{
             connected = true;
             System.out.println("Устройство подключено");
         }
        }
    }
    public void disconnect(){
        if(!connected){
            System.out.println("Уже отключено");
        }
        else{
            connected = false;
            System.out.println("Устройство отключено");
        }
    }
    public boolean hasContact(Contact contact){
        return contacts.contains(contact);
    }
    public void addContact(Contact contact){
        if (hasContact(contact)){
            System.out.println("Контакт уже существует");
        }
        else {
            contacts.add(contact);
            System.out.println("Контакт добавлен");
        }
    }

    public void removeContact(String name){

        boolean removed = contacts.removeIf(c -> c.name().equalsIgnoreCase(name));
        if (removed) {
            System.out.println("Контакт " + name + " удален.");
        } else {
            System.out.println("Контакт не найден.");
        }
    }
    public void showContacts(){
        if (contacts.isEmpty()){
            System.out.println("Список контактов пуст");
        }
        else {
            for (Contact c : contacts){
                System.out.println(c.name());
            }
        }

    }
    public abstract void sendMessage(Contact contact, String message);

    @Override
    public String toString() {
        return "ID: " + id +
                ", тип: " + getClass().getSimpleName() + ", включено: " + turnedOn + ", подключено: " + connected;
    }
}
