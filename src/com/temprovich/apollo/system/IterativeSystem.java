package com.temprovich.apollo.system;

import java.util.ArrayList;
import java.util.List;

import com.temprovich.apollo.Entity;
import com.temprovich.apollo.Family;
import com.temprovich.apollo.Registry;

public abstract class IterativeSystem extends AbstractEntitySystem {

    private Family family;

    private List<Entity> entities;

    public IterativeSystem(Family family) {
        this(family, 0);
    }

    public IterativeSystem(Family family, int priority) {
        super(priority);
        this.family = family;
        this.entities = new ArrayList<Entity>();
    }

    @Override
    public void update(float dt) {
        for (int i = 0; i < entities.size(); i++) process(entities.get(i), dt);
    }

    protected abstract void process(Entity entity, float dt);

    @Override
    public void onBind(Registry registry) {
        entities = registry.view(family);
    }

    @Override
    public void onUnbind(Registry registry) {
        entities = null;
    }

    public List<Entity> getEntities() {
        return entities;
    }
    
}