package AlexsGameEnhancers;

import java.util.ArrayList;

public class ClickManager {
    private ArrayList<Clickable> clickableArrayList;

    public ClickManager() {
        clickableArrayList = new ArrayList<>();
    }

    public void addClickable(Clickable clickable) {
        clickableArrayList.add(clickable);
    }

    public void removeClickable(Clickable clickable) {
        clickableArrayList.remove(clickable);
//        for(int i = 0; i < clickableArrayList.size(); i++) {
//            if(clickableArrayList.get(i).equals(clickable)) {
//                clickableArrayList.remove()
//            }
//        }
    }

    public void clickAllGeneral(ClickCheck clickChecker) {
        for(Clickable clickable: clickableArrayList) {
            clickChecker.clickAll(clickable);
        }
    }

    public void clickAll(Vector location, int mouseButton) {
        clickAllGeneral((clickable) -> clickable.mouseClicked(location, mouseButton));
    }

    public void pressAll(Vector location, int mouseButton) {
        clickAllGeneral((clickable) -> clickable.mousePressed(location, mouseButton));
    }

    public void releasesAll(Vector location, int mouseButton) {
        clickAllGeneral((clickable) -> clickable.mouseReleased(location, mouseButton));
    }


    private interface ClickCheck {
        void clickAll(Clickable clickable);
    }

}