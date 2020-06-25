import java.util.Scanner;

// 1. Create an Interface named Converter. This interface has a method with signature Object convert(Object) meaning that it takes an Object as a parameter and returns another Object.

interface Converter {
    Object convert(Object obj);
}

// 2. Create an Abstract class named TemperatureConverter that implements the Converter interface. 
// TemperatureConverter has an abstract method with signature protected boolean isHot(double) which takes a temperature as a parameter and decides if that temperature is hot or not.

abstract class TemperatureConverter implements Converter{
    abstract protected boolean isHot(double temp);
}

// 3. Create another Abstract class named SpeedConverter that implements Converter interface. 
//SpeedConverter has an abstract method with signature protected boolean isFast(double) which takes a speed as a parameter and decides if it is fast or not.

abstract class SpeedConverter implements Converter{
    abstract protected boolean isFast(double speed);
}

// 4. Create two classes Thermometer and Thermocouple. Both extends TemperatureConverter.

class Thermometer extends TemperatureConverter{
    
    public Object convert(Object obj1){
        double degC = (double) obj1;

        return ((degC * 9) / 5) + 32;
    }

    protected boolean isHot(double temp){
        if(temp > 35){
            return true;
        } else {
            return false;
        }
    }
}

class Thermocouple extends TemperatureConverter{

    public Object convert(Object obj1){
        double degC = (double) obj1;
        
        return degC + 273.15;        
    }

    protected boolean isHot(double temp) {
        if(temp > 35){
            return true;
        } else {
            return false;
        }        
    }
}

//5. Create two more classes PitotTube and ShaftLog` both extending SpeedConverter.

class PitotTube extends SpeedConverter{

    public Object convert(Object obj1){
        double kms = (double) obj1;
        
        return kms * 2.9411764705882;
    }

    protected boolean isFast(double speed){
        if(speed>.05){
            return true;
        } else {
            return false;
        }
    }
}

class ShaftLog extends SpeedConverter{

    public Object convert(Object obj1){
        double kmh = (double) obj1;
        return kmh * 1000;
    }

    protected boolean isFast(double speed){
        if(speed>60){
            return true;
        } else {
            return false;
        }
    }
}

class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Converter converter;
        int choice;

        System.out.println("\n Enter your choice of conversion \n\n 1. Thermometer - Conversion from Degree Celcius to Degree Farenhite \n 2. Thermocouple - Conversion from Degree Celcius to Degree Kelvin \n 3. Pitot Tube - Conversion from kms to mach \n 4. Shaft Log - Conversion from kmh to mph");
        choice = sc.nextInt();

        switch(choice){
            case 1:
                converter = new Thermometer();

                double degC = 36;
                double convertedK = (double) converter.convert(degC);
                System.out.println("Temerature in degree Farenhite is "+convertedK);
                
                if(((TemperatureConverter)converter).isHot(degC)){
                    System.out.println("Uffffff Man, Its veryyyy hot....");
                } else {
                    System.out.println("Its fine buddy...");
                }

                break;

            case 2:
                converter = new Thermocouple();

                double degCelcius = 25;
                double convertedF = (double) converter.convert(degCelcius);
                System.out.println("Temperature in degree Kelvin is "+convertedF);
                
                if(((TemperatureConverter)converter).isHot(degCelcius)){
                    System.out.println("Uffffff Man, Its veryyyy hot....");
                } else {
                    System.out.println("Its fine buddy...");
                }

                break;

            case 3:
                converter = new PitotTube();

                double kms = 12;
                double convertedMach = (double) converter.convert(kms);
                System.out.println("Spped in Mach is "+convertedMach);
            
                if(((SpeedConverter)converter).isFast(kms)){
                    System.out.println("Yeah Man, Its veryyyy fast....");
                } else {
                    System.out.println("You can assume it slow...");
                }

                break;

            case 4:
                converter = new ShaftLog();

                double kmh = 75;
                double convertedMph = (double) converter.convert(kmh);
                System.out.println("Speed in mph is "+convertedMph);
               
                if(((SpeedConverter)converter).isFast(kmh)){
                    System.out.println("Yeah Man, Its veryyyy fast....");
                } else {
                    System.out.println("You can assume it slow...");
                }
                
                break;

            default :
                System.out.println("Please enter valid input as given in the option");
                break;
        }

    }
}


//We can't call isHot() or isFast() method because it is not of Converter type.
//And to call this class name has to be embeded as ((TemperatureConverter)converter.isHot())
