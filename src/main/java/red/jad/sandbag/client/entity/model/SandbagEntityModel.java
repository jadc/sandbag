package red.jad.sandbag.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import red.jad.sandbag.entity.SandbagEntity;

public class SandbagEntityModel extends EntityModel<SandbagEntity> {
	private final ModelRenderer Body;

	public SandbagEntityModel() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.setTextureOffset(16, 14).addBox(-1.5F, -33.0F, -8.0F, 3.0F, 5.0F, 2.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(-7.0F, -32.0F, -7.0F, 14.0F, 32.0F, 14.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(-7.0F, -33.0F, -7.0F, 14.0F, 1.0F, 14.0F, -1.0F, false);
		Body.setTextureOffset(1, 14).addBox(-8.0F, -33.0F, -1.5F, 2.0F, 5.0F, 3.0F, 0.0F, false);
		Body.setTextureOffset(44, 14).addBox(6.0F, -33.0F, -1.5F, 2.0F, 5.0F, 3.0F, 0.0F, false);
		Body.setTextureOffset(30, 14).addBox(-1.5F, -33.0F, 6.0F, 3.0F, 5.0F, 2.0F, 0.0F, false);
	}
	
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(SandbagEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
	}
}