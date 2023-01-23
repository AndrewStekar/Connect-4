In this project, me and two classmates created a robotic Connect 4 board that plays optimal moves against a player. 
Link to video: https://youtu.be/YFcao2U2tKg

The project requires multiple threads for its execution.
The first thread saves an image from a camera every 0.5 seconds and saves it to a local directory.
After every new image is saved, a second thread searches the image for the position and colour of the pieces on the board. This will compare the configuration of the previous board to the current board and find any new moves played.
This new move will then be sent to a recursive minimax algorithm, which will calculate an optimal move for the computer to play.
This optimal move will then be sent through the serial port to an Arduino, which will open the specified gate and drop a piece into the correct location.
When a win or loss is detected, a red or green LED will light up to indicate the player's outcome.
All of the transmission of data between threads is done by writing to and reading from test files.

This project ended up being a huge success, receiving a grade of 100% and being used to advertise my school's computer science class to future students.
