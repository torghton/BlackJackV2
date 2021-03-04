package AlexsGameEnhancers;

import java.util.ArrayList;

interface SceneChanged {
    void run(Integer scene);
}

public class Scene {

    Integer scene;

    private ArrayList<SceneChanged> onSceneChanges;

    public Scene() {
        this(0, temp -> {});
    }

    public Scene(Integer scene) {
        this(scene, temp -> {});
    }

    public Scene(Integer scene, SceneChanged onSceneChange) {
        onSceneChanges = new ArrayList<>();

        this.scene = scene;
        onSceneChanges.add(onSceneChange);
    }

    public void setScene(Integer scene) {
        callSceneChange(this.scene, scene);
        this.scene = scene;
    }

    private void callSceneChange(Integer lastScene, Integer currentScene) {
        if(lastScene != currentScene) {
            for(SceneChanged sceneChanged: onSceneChanges) {
                sceneChanged.run(currentScene);
            }

        }
    }

    public Integer getScene() {
        return scene;
    }

    public void onSceneChange(SceneChanged onSceneChange) {

        onSceneChanges.add(onSceneChange);
    }
}
