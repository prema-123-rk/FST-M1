package activities;

public class Activity3 {

    public static String adjustDevice(String device, int value) {

        String status;

        if (device == null) {
            return "[Unknown] Device is null.";
        }

        switch (device.toUpperCase()) {
            case "THERMOSTAT":
                if (value >= 40) {
                    status = "[Thermostat] Warning: Temperature high.";
                } else {
                    status = "[Thermostat] Temperature is set to " + value + ".";
                }
                break;

            case "LIGHT":
                status = "[Light] Adjusting brightness to " + value + "%.";
                break;

            default:
                status = "[Unknown] Device not supported.";
                break;
        }

        return status;
    }

    public static void main(String[] args) {

        System.out.println(adjustDevice("THERMOSTAT", 45));
        System.out.println(adjustDevice("THERMOSTAT", 25));
        System.out.println(adjustDevice("LIGHT", 80));
        System.out.println(adjustDevice(null, 0));
    }
}
