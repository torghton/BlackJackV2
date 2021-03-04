package AlexsGameEnhancers;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class KeyInteractor {
	private List<KeyInteractable> keyInteractors;
	
	private List<Integer> keysPressed;
	
	public KeyInteractor() {
		keyInteractors = new ArrayList<>();
		
		keysPressed = new CopyOnWriteArrayList<>();
	}
	
	public void addKeyInteractable(KeyInteractable keyInteractable) {
		keyInteractors.add(keyInteractable);
	}
	
	
	public void keyPressed(Integer keycode) {
		if(!keysPressed.contains(keycode)) {
			keysPressed.add(keycode);
			for(KeyInteractable keyInteractable: keyInteractors) {
				keyInteractable.keyPressed(keycode);
			}
		}
	}
	
	public void keyReleased(Integer keycode) {
		if(keysPressed.contains(keycode)) {
			keysPressed.remove(keysPressed.indexOf(keycode));
			for(KeyInteractable keyInteractable: keyInteractors) {
				keyInteractable.keyReleased(keycode);
			}
		}
	}
}