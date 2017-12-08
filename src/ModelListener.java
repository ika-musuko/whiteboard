import java.awt.event.ComponentListener;

/*
Interface to listen for shape change notifications.
The modelChanged() notification includes a pointer to the model that changed. There is not detail about
what the exact change
was. */
public interface ModelListener extends ComponentListener {
	
	public void modelChanged(Info model); 
	
	
	
	
}