#include <jni.h>
#include <android/sensor.h>

ASensorManager* sensorManager;
ASensorEventQueue* sensorEventQueue;

extern "C" JNIEXPORT void JNICALL
Java_your_package_name_SensorActivity_startSensors(JNIEnv* env, jobject thiz) {
sensorManager = ASensorManager_getInstance();
ASensor const* proximitySensor = ASensorManager_getDefaultSensor(sensorManager, ASENSOR_TYPE_PROXIMITY);
ASensor const* accelerometerSensor = ASensorManager_getDefaultSensor(sensorManager, ASENSOR_TYPE_ACCELEROMETER);

sensorEventQueue = ASensorManager_createEventQueue(sensorManager, ALooper_prepare(ALOOPER_PREPARE_ALLOW_NON_CALLBACKS), 0, nullptr, nullptr);
ASensorEventQueue_enableSensor(sensorEventQueue, proximitySensor);
ASensorEventQueue_setEventRate(sensorEventQueue, proximitySensor, 100000); // Set proximity sensor refresh rate to 10 Hz

ASensorEventQueue_enableSensor(sensorEventQueue, accelerometerSensor);
ASensorEventQueue_setEventRate(sensorEventQueue, accelerometerSensor, 100000); // Set accelerometer refresh rate to 10 Hz
}

extern "C" JNIEXPORT void JNICALL
Java_your_package_name_SensorActivity_stopSensors(JNIEnv* env, jobject thiz) {
if (sensorEventQueue) {
ASensorEventQueue_disableSensor(sensorEventQueue, proximitySensor);
ASensorEventQueue_disableSensor(sensorEventQueue, accelerometerSensor);
ASensorManager_destroyEventQueue(sensorManager, sensorEventQueue);
}
}

extern "C" JNIEXPORT void JNICALL
Java_your_package_name_SensorActivity_getSensorData(JNIEnv* env, jobject thiz) {
ASensorEvent event;

while (ASensorEventQueue_getEvents(sensorEventQueue, &event, 1) > 0) {
if (event.type == ASENSOR_TYPE_PROXIMITY) {
// Handle proximity sensor data (event.proximity)
} else if (event.type == ASENSOR_TYPE_ACCELEROMETER) {
// Handle accelerometer sensor data (event.acceleration.x, event.acceleration.y, event.acceleration.z)
}
}
}
