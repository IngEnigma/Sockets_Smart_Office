public class SensorLuzFactory implements ISensorFactory {
    @Override
    public Sensor crearSensor() {
        return new SensorLuz();
    }
    
}
