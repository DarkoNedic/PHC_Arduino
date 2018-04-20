#define EXTRASTROM4 4
#define EXTRASTROM8 8
#define EXTRASTROM12 12

#define LED1 2
#define LED2 3
#define LED3 9
#define LED4 10

#define BTN1 6
#define BTN2 7
#define BTN3 11
#define BTN4 13


void setup() {
  pinMode(LED1, OUTPUT);
  pinMode(LED2, OUTPUT);
  pinMode(LED3, OUTPUT);
  pinMode(LED4, OUTPUT);
  pinMode(BTN1, INPUT);
  pinMode(BTN2, INPUT);
  pinMode(BTN3, INPUT);
  pinMode(BTN4, INPUT);
  pinMode(EXTRASTROM4, OUTPUT);
  pinMode(EXTRASTROM8, OUTPUT);
  pinMode(EXTRASTROM12, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  digitalWrite(EXTRASTROM4, HIGH);
  digitalWrite(EXTRASTROM8, HIGH);
  digitalWrite(EXTRASTROM12, HIGH);

  if (digitalRead(BTN1) == LOW) {
    digitalWrite(LED1, HIGH);
    Serial.println("Client1");
    delay(550);
  } else {
    digitalWrite(LED1, LOW);
  }

  if (digitalRead(BTN2) == LOW) {
    digitalWrite(LED2, HIGH);
    Serial.println("Client2");
    delay(550);
  } else {
    digitalWrite(LED2, LOW);
  }

  if (digitalRead(BTN3) == LOW) {
    digitalWrite(LED3, HIGH);
    Serial.println("Server1");
    delay(550);
  } else {
    digitalWrite(LED3, LOW);
  }

  if (digitalRead(BTN4) == LOW) {
    digitalWrite(LED4, HIGH);
    Serial.println("Server2");
    delay(550);
  } else {
    digitalWrite(LED4, LOW);
  }
}
