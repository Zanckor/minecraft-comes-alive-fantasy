package net.mca.client.render;

import net.mca.client.model.VillagerEntityModelMCA;
import net.mca.client.render.layer.ClothingLayer;
import net.mca.client.render.layer.FaceLayer;
import net.mca.client.render.layer.HairLayer;
import net.mca.client.render.layer.SkinLayer;
import net.mca.entity.VillagerEntityMCA;
import net.mca.entity.race.Race;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

import java.util.List;

public class VillagerEntityMCARenderer extends VillagerLikeEntityMCARenderer<VillagerEntityMCA> {
    private static final List<Race> HAIRY_RACES = List.of(Race.HUMAN, Race.ELF, Race.DRAGONBORN, Race.FAIRY);

    public VillagerEntityMCARenderer(EntityRendererFactory.Context ctx) {
        super(ctx, createModel(VillagerEntityModelMCA.bodyData(Dilation.NONE)).hideWears());

        addFeature(new SkinLayer<>(this, model));
        addFeature(new FaceLayer<>(this, createModel(VillagerEntityModelMCA.bodyData(new Dilation(0.01F))).hideWears(), "normal"));
        addFeature(new HairLayer<>(this, createModel(VillagerEntityModelMCA.hairData(new Dilation(0.125F)))));
        addFeature(new ClothingLayer<>(this, createModel(VillagerEntityModelMCA.bodyData(new Dilation(0.0625F))), null));
    }

    @Override
    public void render(VillagerEntityMCA mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    private static VillagerEntityModelMCA<VillagerEntityMCA> createModel(ModelData data) {
        return new VillagerEntityModelMCA<>(TexturedModelData.of(data, 64, 64).createModel());
    }
}
