public class SensorTemperaturaFactory implements ISensorFactory {
    @Override
    public Sensor crearSensor() {
        return new SensorTemperatura();
    }
}