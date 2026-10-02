 public class SatellitePhone extends CommunicationDevice {

        private String satelliteName;
        private int signalStrength;
        private int batteryLevel;
        private boolean emergencyMode;

        public SatellitePhone(String manufacturer, String model, String satelliteName, int signalStrength, int batteryLevel) {
            super(manufacturer, model);
            this.satelliteName = satelliteName;
            this.signalStrength = signalStrength;
            this.batteryLevel = batteryLevel;
            this.emergencyMode = false;
        }

        public String getSatelliteName() {
            return satelliteName;
        }

        public int getSignalStrength() {
            return signalStrength;
        }

        public int getBatteryLevel() {
            return batteryLevel;
        }

        public boolean isEmergencyMode() {
            return emergencyMode;
        }

        public void randomizeSignalStrength() {
            signalStrength = (int) (Math.random() * 101);
            System.out.println("Новый уровень сигнала: " + signalStrength + "%");
        }

        public void randomizeBatteryLevel() {
            batteryLevel = (int) (Math.random() * 101);
            System.out.println("Новый уровень заряда: " + batteryLevel + "%");
        }

        public void sendSOS() {
            if (!isTurnedOn()) {
                System.out.println("Телефон выключен");
            } else if (batteryLevel <= 0) {
                System.out.println("Батарея разряжена");
            } else if (signalStrength <= 0) {
                System.out.println("Нет спутникового сигнала");
            } else {
                emergencyMode = true;
                System.out.println("SOS отправлен через спутник " + satelliteName);
            }
        }

        public void cancelSOS() {
            if (!emergencyMode) {
                System.out.println("Режим SOS не включен");
            } else {
                emergencyMode = false;
                System.out.println("Режим SOS отключен");
            }
        }

        @Override
        public void sendMessage(String contact, String message) {
            if (!isTurnedOn()) {
                System.out.println("Спутниковый телефон выключен");
            } else if (!isConnected()) {
                System.out.println("Телефон не подключен");
            } else if (batteryLevel <= 0) {
                System.out.println("Батарея разряжена");
            } else if (signalStrength <= 0) {
                System.out.println("Нет спутникового сигнала");
            } else if (!hasContact(contact)) {
                System.out.println("Контакт не найден");
            } else if (message.isBlank()) {
                System.out.println("Сообщение пустое");
            } else {
                System.out.println("Спутниковое сообщение отправлено контакту " + contact + " через спутник " + satelliteName);
            }
        }
    }

