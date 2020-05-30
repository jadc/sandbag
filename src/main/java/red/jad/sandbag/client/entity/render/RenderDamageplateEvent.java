package red.jad.sandbag.client.entity.render;

import javax.annotation.Nullable;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.Event;

@Event.HasResult
public class RenderDamageplateEvent extends EntityEvent
{

    private String nameplateContent;
    private final String originalContent;
    private final EntityRenderer<?> entityRenderer;
    private final MatrixStack matrixStack;
    private final IRenderTypeBuffer renderTypeBuffer;
    private final int packedLight;

    public RenderDamageplateEvent(Entity entity, String content, EntityRenderer<?> entityRenderer, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int packedLight)
    {
        super(entity);
        this.originalContent = content;
        this.setContent(this.originalContent);
        this.entityRenderer = entityRenderer;
        this.matrixStack = matrixStack;
        this.renderTypeBuffer = renderTypeBuffer;
        this.packedLight = packedLight;
    }

    /**
     * Sets the content that is to be rendered on the name plate/tag
     */
    public void setContent(String contents)
    {
        this.nameplateContent = contents;
    }

    /**
     * The content being rendered on the name plate/tag
     */
    public String getContent()
    {
        return this.nameplateContent;
    }

    /**
     * The original content being rendered on the name plate/tag
     */
    public String getOriginalContent()
    {
        return this.originalContent;
    }

    /**
     * The entity renderer that renders the name plate/tag, if it was provided
     */
    @Nullable
    public EntityRenderer<?> getEntityRenderer()
    {
        return this.entityRenderer;
    }

    /**
     * The matrix stack used during the rendering of the name plate/tag
     */
    public MatrixStack getMatrixStack()
    {
        return this.matrixStack;
    }

    /**
     * The render type buffer used during the rendering of the name plate/tag
     */
    public IRenderTypeBuffer getRenderTypeBuffer()
    {
        return this.renderTypeBuffer;
    }

    /**
     * The packed values of sky and block light used during the rendering of the name plate/tag
     */
    public int getPackedLight()
    {
        return this.packedLight;
    }
}
