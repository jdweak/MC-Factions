package me.jdweak.learning.events;

public class EventFactory {
	
	public Event createEvent(String event) {
		if(event.equals("EventClass")) {
			System.out.println("main event class instantiated");
			return new EventClass();
		} else {
			return new EventClass();
		}
		
	}

}
