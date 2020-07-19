package de.maxhenkel.car.entity.car.parts;

import de.maxhenkel.car.entity.car.base.EntityGenericCar;
import de.maxhenkel.corelib.client.obj.OBJModel;
import de.maxhenkel.corelib.client.obj.OBJModelInstance;
import de.maxhenkel.corelib.client.obj.OBJModelOptions;
import de.maxhenkel.corelib.math.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;

import java.util.ArrayList;
import java.util.List;

public class PartModel extends Part {

    protected OBJModel model;
    protected ResourceLocation texture;
    protected Vector3d offset;
    protected Rotation rotation;

    public PartModel(OBJModel model, ResourceLocation texture, Vector3d offset, Rotation rotation) {
        this.model = model;
        this.texture = texture;
        this.offset = offset;
        this.rotation = rotation;
    }

    public PartModel(OBJModel model, ResourceLocation texture, Vector3d offset) {
        this(model, texture, offset, null);
    }

    public PartModel(OBJModel model, ResourceLocation texture) {
        this(model, texture, new Vector3d(0D, 0D, 0D), null);
    }

    public OBJModel getModel() {
        return model;
    }

    public List<OBJModelInstance> getInstances(EntityGenericCar car) {
        List<OBJModelInstance> list = new ArrayList<>();
        list.add(new OBJModelInstance(model, new OBJModelOptions(texture, offset, rotation)));
        onPartAdd(list);
        return list;
    }

    protected void onPartAdd(List<OBJModelInstance> list) {

    }

}
