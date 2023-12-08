# Weather application

This is our group work for the course "Programming 3". We are aiming at grade 5.
The application is described in the "Documentation" section. There you can find
a uml-diagram and the documentation for the application. 

How to run the application:

Run application
1. git clone git@course-gitlab.tuni.fi:compcs140-fall2023/group3191.git
2. cd group3191/WeatherApp
3. touch .env
4. Add following to .env file
- API_KEY=secret #replace with your api-key that has pro license
- GEONAMES_USERNAME=group3191
- USE_PRO_API=true # Add this if you have pro license to OpenWeatherMap API.
5. Mvn clean javafx:run

Run tests
1. Ensure you are in group3191/WeatherApp directory
2. mvn clean test
