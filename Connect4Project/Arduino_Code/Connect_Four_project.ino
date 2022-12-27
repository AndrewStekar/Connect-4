// Andrew Stekar
// Wed, June 15, 2022.

#include <Servo.h>

// declaring output pins
int servoPin1 = 2;
int servoPin2 = 4;
int servoPin3 = 6;
int servoPin4 = 7;
int servoPin5 = 8;
int servoPin6 = 10;
int servoPinHopper = 13;// the gate servo loads chips from above
int computerWin = 11;
int playerWin = 12;

// gate open / close states
int gateOpenStateOne = 20;
int gateCloseStateOne = 85;
int gateOpenStateTwo = 5;
int gateCloseStateTwo = 90;
int gateOpenStateThree = 0;
int gateCloseStateThree = 83;
int gateOpenStateFour = 10;
int gateCloseStateFour = 102;
int gateOpenStateFive = 0;
int gateCloseStateFive = 76;
int gateOpenStateSix = 7;
int gateCloseStateSix = 87;
int hopperOpenState = 107;
int hopperCloseState = 90;

// creating Servo objects
Servo servo1;
Servo servo2;
Servo servo3;
Servo servo4;
Servo servo5;
Servo servo6;
Servo servo7;
Servo servoHopper;

// opening the chip gates
void gateOpen(int servo) {
  switch (servo) {
    case 1:
      servo1.write(gateOpenStateOne);
      break;
    case 2:
      servo2.write(gateOpenStateTwo);
      break;
    case 3:
      servo3.write(gateOpenStateThree);
      break;
    case 4:
      servo4.write(gateOpenStateFour);
      break;
    case 5:
      servo5.write(gateOpenStateFive);
      break;
    case 6:
      servo6.write(gateOpenStateSix);
      break;
    case 7:
      break;
  }
}

// closing the chip gates
void gateClose(int servo) {
  switch (servo) {
    case 1:
      servo1.write(gateCloseStateOne);
      break;
    case 2:
      servo2.write(gateCloseStateTwo);
      break;
    case 3:
      servo3.write(gateCloseStateThree);
      break;
    case 4:
      servo4.write(gateCloseStateFour);
      break;
    case 5:
      servo5.write(gateCloseStateFive);
      break;
    case 6:
      servo6.write(gateCloseStateSix);
      break;
    case 7:
      break;
  }
}

// opening the hopper gate
void hopperOpen() {
  servoHopper.write(hopperOpenState);
}

// closing the hopper gate
void hopperClose() {
  servoHopper.write(hopperCloseState);
}

void setup() {

  Serial.begin(9600);// serial reader
  Serial.setTimeout(25);

  // attaching servo objects to ouptu pins
  servo1.attach(servoPin1);
  servo2.attach(servoPin2);
  servo3.attach(servoPin3);
  servo4.attach(servoPin4);
  servo5.attach(servoPin5);
  servo6.attach(servoPin6);
  servoHopper.attach(servoPinHopper);
  pinMode(computerWin, OUTPUT);
  pinMode(playerWin, OUTPUT);
  digitalWrite(computerWin, LOW);
  digitalWrite(playerWin, LOW);

  for (int i = 1; i < 7; i++) {// setting all gates to closed position
    gateClose(i);
    delay(100);
  }
  hopperClose();

}
void loop() {
  delay(10);
  int move = 0;// declaring variable
  if (Serial.available() > 0) {// searching serial port
    move = Serial.parseInt();
    if (move > 0 && move < 8) {
    gateOpen(move);
    hopperOpen();
    delay(235);
    hopperClose();
    delay(1000);
    gateClose(move);
    Serial.flush();
    } 
    if (move == 8) {
      digitalWrite(computerWin, HIGH);
    }
    if (move == 9) {
      digitalWrite(playerWin, HIGH);
    }
  }



}
