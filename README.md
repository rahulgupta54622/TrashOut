# TrashOut
SMART WASTE MANAGEMENT SYSTEM - IOT BASED PROJECT


Arduino code used for distance sensor. It uploads the realtime time distance in cm to firebase relatime databse

Here is the code:

#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

//Firebase settings
#define FIREBASE_HOST "<YOUR_FIREBASE_PROJECT_NAME>.firebaseio.com"
#define FIREBASE_AUTH "<YOUR_FIREBASE_SECRET>"

//Wi-Fi settings
#define WIFI_SSID "RedmiK20"
#define WIFI_PASSWORD "111111111"

//Define trigger and echo digital pins
const int trigPin = 5;
const int echoPin = 4;

// The amount of time the ultrassonic wave will be travelling for
long duration = 0;
// Define the distance variable
double distance = 0;

void setup()
{

    // Connect to Wi-Fi
    Serial.print("Wi-Fi...");
    WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
    Serial.print("Connecting...");
    while (WiFi.status() != WL_CONNECTED)
    {
        Serial.print(".");
        delay(500);
    }
    Serial.println();
    Serial.print("Connected to: ");
    Serial.println(WiFi.localIP());

    Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);

    // Ultrasonic sensor, set echo as Input and trigger as Output
    pinMode(trigPin, OUTPUT);
    pinMode(echoPin, INPUT);

    Serial.begin(9600);
}

void loop()
{

    getDistance();
    // Prints the distance value to the serial monitor
    Serial.print("Distance: ");
    Serial.println(distance);

     // handle error 
    if (Firebase.failed()) { 
      Serial.print("setting /number failed:"); 
      Serial.print(Firebase.error());   
      return; 
    }
  
    delay(500);
}

void getDistance()
{
    // Clear trigPin
    digitalWrite(trigPin, LOW);
    delayMicroseconds(2);

    // trigPin HIGH por 10ms
    digitalWrite(trigPin, HIGH);
    delayMicroseconds(10);
    digitalWrite(trigPin, LOW);

    //Reads echoPin, returns the travel time of the sound wave in ms
    duration = pulseIn(echoPin, HIGH);

    // Calculating the distance, in centimeters, using the formula described in the first section.
    distance = duration * 0.034 / 2;
    // Sends the distance value to Firebase
    Firebase.setFloat("GarbageCans/can1/distance", distance);
   
    

}
