package red.jad.sandbag.entity;

import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;
import red.jad.sandbag.registry.EntityRegistry;
import red.jad.sandbag.registry.ItemRegistry;
import red.jad.sandbag.registry.SoundRegistry;

public class SandbagEntity extends MobEntity {

	protected static final DataParameter<Float> LAST_DAMAGE = EntityDataManager.createKey(SandbagEntity.class,
			DataSerializers.FLOAT);

	public SandbagEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public SandbagEntity(World worldIn, double posX, double posY, double posZ) {
		this(EntityRegistry.SANDBAG.get(), worldIn);
		this.setPosition(posX, posY, posZ);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(LAST_DAMAGE, 0.0f);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.5F;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.BLOCK_WOOL_HIT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_WOOL_BREAK;
	}
	
	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(ItemRegistry.sandbag);
	}

	public float getLastDamage() {
		return this.dataManager.get(LAST_DAMAGE);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!this.world.isRemote) {
			if (source.getImmediateSource() instanceof PlayerEntity) {
				if (((PlayerEntity) source.getImmediateSource()).getHeldItemMainhand().isEmpty()) {
					this.playSound(SoundEvents.BLOCK_WOOL_BREAK);
					this.playParticles(50);
					if (!source.isCreativePlayer())
						this.entityDropItem(new ItemStack(Items.WHITE_WOOL, 6));
					this.remove();
				}
			}
			this.dataManager.set(LAST_DAMAGE, amount);
		}
		this.playParticles(new Random().nextInt((3)));
		return super.attackEntityFrom(source, 0);
	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
		if (entityIn instanceof LivingEntity) {
			Item itemInHand = ((LivingEntity) entityIn).getHeldItemMainhand().getItem();
			if (itemInHand.isIn(Tags.Items.RODS)) {
				strength *= 20;
				if (!this.world.isRemote)
					playSound(SoundRegistry.SANDBAG_STRIKE);
			}
		}
		super.knockBack(entityIn, strength, xRatio, zRatio);
	}

	private void playParticles(int particleCount) {
		if (this.world instanceof ServerWorld) {
			((ServerWorld) this.world).spawnParticle(
					new BlockParticleData(ParticleTypes.BLOCK, Blocks.WHITE_WOOL.getDefaultState()), this.getPosX(),
					this.getPosYHeight(0.6666666666666666D), this.getPosZ(), particleCount,
					(double) (this.getWidth() / 4.0F), (double) (this.getHeight() / 4.0F),
					(double) (this.getWidth() / 4.0F), 0.05D);
		}
	}

	private void playSound(SoundEvent sound) {
		this.world.playSound((PlayerEntity) null, this.getPosX(), this.getPosY(), this.getPosZ(), sound,
				this.getSoundCategory(), 1.0F, 1.0F);
	}
}
