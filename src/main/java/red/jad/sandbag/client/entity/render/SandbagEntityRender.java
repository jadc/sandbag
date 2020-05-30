package red.jad.sandbag.client.entity.render;

import java.text.DecimalFormat;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import red.jad.sandbag.Sandbag;
import red.jad.sandbag.client.entity.model.SandbagEntityModel;
import red.jad.sandbag.entity.SandbagEntity;

public class SandbagEntityRender extends MobRenderer<SandbagEntity, SandbagEntityModel> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(Sandbag.MOD_ID, "textures/sandbag.png");

	public SandbagEntityRender(EntityRendererManager renderManager) {
		super(renderManager, new SandbagEntityModel(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(SandbagEntity entity) {
		return TEXTURE;
	}

	@Override
	public void render(SandbagEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		String damage = new DecimalFormat("0.00").format(entityIn.getLastDamage());
		RenderDamageplateEvent renderDamageplateEvent = new RenderDamageplateEvent(entityIn, damage, this,
				matrixStackIn, bufferIn, packedLightIn);
		MinecraftForge.EVENT_BUS.post(renderDamageplateEvent);
		if (renderDamageplateEvent.getResult() != Event.Result.DENY) {
			this.renderDamage(entityIn, renderDamageplateEvent.getContent(), matrixStackIn, bufferIn, packedLightIn);
		}

		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	// Render damage display
	protected void renderDamage(SandbagEntity entityIn, String displayNameIn, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		double d0 = this.renderManager.squareDistanceTo(entityIn);
		if (!(d0 > 4096.0D)) {
			matrixStackIn.push();
			matrixStackIn.translate(0.0D, entityIn.getHeight() + 0.5F, 0.0D);
			matrixStackIn.rotate(this.renderManager.getCameraOrientation());
			matrixStackIn.scale(-0.035F, -0.035F, 0.035F);
			Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
			FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
			float f2 = (float) (-fontrenderer.getStringWidth(displayNameIn) / 2);
			fontrenderer.renderString(displayNameIn, f2, 0, 0, false, matrix4f, bufferIn, false, -1, packedLightIn);
			fontrenderer.renderString(displayNameIn, f2, 0, 0, false, matrix4f, bufferIn, false, 0, packedLightIn);
			matrixStackIn.pop();
		}
	}
	
	// move nametags above damage tag
	@Override
	protected void renderName(SandbagEntity entityIn, String displayNameIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
			int packedLightIn) {
		double d0 = this.renderManager.squareDistanceTo(entityIn);
		if (!(d0 > 4096.0D)) {
			boolean flag = !entityIn.isDiscrete();
			float f = entityIn.getHeight() + 0.8F;
			int i = "deadmau5".equals(displayNameIn) ? -10 : 0;
			matrixStackIn.push();
			matrixStackIn.translate(0.0D, (double) f, 0.0D);
			matrixStackIn.rotate(this.renderManager.getCameraOrientation());
			matrixStackIn.scale(-0.025F, -0.025F, 0.025F);
			Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
			Minecraft mc = Minecraft.getInstance();
			float f1 = mc.gameSettings.getTextBackgroundOpacity(0.25F);
			int j = (int) (f1 * 255.0F) << 24;
			FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
			float f2 = (float) (-fontrenderer.getStringWidth(displayNameIn) / 2);
			fontrenderer.renderString(displayNameIn, f2, (float) i, 553648127, false, matrix4f, bufferIn, flag, j,
					packedLightIn);
			if (flag) {
				fontrenderer.renderString(displayNameIn, f2, (float) i, -1, false, matrix4f, bufferIn, false, 0,
						packedLightIn);
			}

			matrixStackIn.pop();
		}
	}
}
