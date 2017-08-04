package platforms;

import Utils.Utils;
import bases.GameObject;
import bases.renderers.Animation;
import physics.BoxCollider;
import physics.PhysicsBody;

public class Item extends GameObject implements PhysicsBody {

    public Item() {
        renderer = new Animation(Utils.loadImage("assets/image/item/item.png"));
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
