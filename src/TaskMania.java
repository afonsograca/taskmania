
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import Global.Time;
import States.State.InvalidTransitionException;
import Tasks.*;


public class TaskMania {

	public static void main(String[] args) throws ParseException, InterruptedException {
        //Initialize four Leafs
        Leaf Leaf1 = new Task("Estudar", "Asso");

        Leaf Leaf2 = new Task();
        Leaf Leaf3 = new Action();
        Leaf Leaf4 = new Task();
 
        //Initialize three composite graphics
        Job graphic = new Job("","");
        Job graphic1 = new Job("","");
        Job graphic2 = new Job("","");
 
        //Composes the graphics
        graphic1.addChild(Leaf1);
        graphic1.addChild(Leaf2);
        graphic1.addChild(Leaf3);
 
        graphic2.addChild(Leaf4);
 
        graphic.addChild(graphic1);
        graphic.addChild(graphic2);
        
        try{
	        ((Task) Leaf1).start();
	        Thread.sleep(1000);
	        ((Task) Leaf1).pause();
	        ((Task) Leaf1).start();
	        Thread.sleep(1000);
	        ((Task) Leaf1).close();
	        System.out.println(Leaf1.getState());
	        ((Task) Leaf4).start();
	        Thread.sleep(3000);
	        ((Task) Leaf4).pause();
	        System.out.println(Leaf4.getState());
	        
	        ((Task) Leaf2).start();
	        System.out.println(Leaf2.getState());
	        System.out.println(Leaf4.getState());
	         
	        System.out.println(Time.periodToFormat(Leaf1.getElapsedTime()));
        }catch(InvalidTransitionException e){
        	System.out.println(e.toString());
        }

        System.out.println(Time.periodToFormat(graphic1.getElapsedTime()));
        System.out.println(Time.periodToFormat(graphic2.getElapsedTime()));
        System.out.println(Time.periodToFormat(graphic.getElapsedTime()));
        
      /*  
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        Date date1 = new Date();
        Thread.sleep(5000);
        Date date2 = new Date();

        long remainder = date2.getTime() - date1.getTime();

        System.out.println("Time Difference = " + df.format(new Date(remainder)));
       */ 
    }

}
