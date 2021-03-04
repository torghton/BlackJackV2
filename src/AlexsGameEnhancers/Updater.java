package AlexsGameEnhancers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Updater {
	private List<Updateable> updateables;
	
	public Updater() {
		updateables = new CopyOnWriteArrayList<>();
	}
	
	public void addUpdateable(Updateable updateable) {
		updateables.add(updateable);
	}
	
	public void removeUpdateable(Updateable updateable) {
		updateables.remove(updateable);
	}
	
	public void updateAll() {
		for(Updateable updateable: updateables) {
			updateable.update();
		}
	}
}