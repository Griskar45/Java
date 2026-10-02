import java.util.ArrayList;

public abstract class CommunicationDevice {
    private int id;
    private String manufacturer;
    private String model;
    private ArrayList<String> contacts;
    private boolean connected;
    private static int nextId = 1;
    private boolean turnedOn;
    public CommunicationDevice(String manufacturer, String model){
        this.model = model;
        this.manufacturer = manufacturer;
        id = nextId;
        nextId++;
        contacts =new ArrayList<>();
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
    public boolean hasContact(String contact){
        return contacts.contains(contact);
    }
    public void addContact(String contact){
        if (contact.isBlank()){
            System.out.println("Строка пуста");
        }
        else if (contacts.contains(contact)){
            System.out.println("Контакт уже существует");
        }
        else {
            contacts.add(contact);
            System.out.println("Контакт добавлен");
        }
    }
    public void removeContact(String contact){
        if (contact.isBlank()){
            System.out.println("Строка пуста");
        }
        else if (contacts.contains(contact)){
            contacts.remove(contact);
            System.out.println("Контакт удален");
        }
        else {
            System.out.println("Контакт не найден");
        }
    }
    public void showContacts(){
        if (contacts.isEmpty()){
            System.out.println("Список контактов пуст");
        }
        else {
            for (int i=0; i < contacts.size(); i++){
                System.out.println(contacts.get(i));
            }
        }

    }
    public abstract void sendMessage(String contact, String message);

    @Override
    public String toString() {
        return "ID: " + id +
                ", тип: " + getClass().getSimpleName() +
                ", производитель: " + manufacturer +
                ", модель: " + model +
                ", включено: " + turnedOn +
                ", подключено: " + connected;
    }
}
