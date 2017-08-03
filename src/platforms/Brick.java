package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;

public class Brick extends GameObject {
    public Brick() {
        super();
        renderer = new Animation(Utils.loadImage("assets/image/brick/brick_grey.png"));
    }
}