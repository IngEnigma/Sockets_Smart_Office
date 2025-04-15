public class SensorHumedadFactory implements ISensorFactory {
    @Override
    public Sensor crearSensor() {
        return new SensorHumedad();
    }
}
