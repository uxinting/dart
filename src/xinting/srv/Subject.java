package xinting.srv;

import java.util.HashSet;
import java.util.Set;

public interface Subject {
	
	void Attach( Observer observer );
	
	void Detach( Observer observer );
	
	void Notify();

}
