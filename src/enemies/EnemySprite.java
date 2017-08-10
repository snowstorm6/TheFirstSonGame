package enemies;

import bases.GameObject;
import bases.GameObjectPool;
import bases.Setting;
import bases.Vector2D;
import physics.BoxCollider;
import physics.Physics;
import physics.PhysicsBody;
import platforms.Thunder;
import players.Player;

public abstract class EnemySprite extends GameObject implements PhysicsBody, Setting {

    public EnemySprite() {
        super();
        setRenderer();
        setBoxCollider();
    }

    protected abstract void setRenderer();

    void hitEnemy() {
        Player player = Physics.bodyInRect(position.add(0, 0), boxCollider.width, boxCollider.height, Player.class);
        if (player != null) {
            float botPlayer = 0;
            float topthis = 0;
            if (player.renderer != null) {
                botPlayer = player.position.y + player.renderer.getHeight() / 2;
                topthis = position.y - renderer.getHeight() / 2;
            }
            if (botPlayer < topthis && this.getClass() != GumEnemy.class) {
                Player.velocity.y = SPEED_JUMPP_HIT_ENEMY;
                setActive(false);

                EnemyExplosion enemyExplosion = GameObjectPool.recycle(EnemyExplosion.class);
                enemyExplosion.position.set(this.position);
                enemyExplosion.renderer.reset();

                Player.instance.resetBullet();

                Thunder thunder = GameObjectPool.recycle(Thunder.class);
                thunder.position.set(this.position);
            } else {
                if (!Player.instance.immortal) {
                    if (this.getClass() == GumEnemy.class) {
                        Player.velocity.y = SPEED_JUMPP_HIT_ENEMY;
                    }
                    Player.instance.life--;
                    Player.instance.immortal = true;
                }
            }
        }
    }

    void setBoxCollider() {
        boxCollider = new BoxCollider(renderer.getWidth(), renderer.getHeight());
        this.children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        hitEnemy();
    }

    protected abstract void move();

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
