package org.terasology.Sample.blocks;

import org.terasology.math.Rotation;
import org.terasology.math.Side;
import org.terasology.math.SideBitFlag;
import org.terasology.math.geom.Vector3i;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockBuilderHelper;
import org.terasology.world.block.BlockUri;
import org.terasology.world.block.family.BlockFamily;
import org.terasology.world.block.family.BlockSections;
import org.terasology.world.block.family.MultiConnectFamily;
import org.terasology.world.block.family.RegisterBlockFamily;
import org.terasology.world.block.loader.BlockFamilyDefinition;

@RegisterBlockFamily("ironplate")
@BlockSections({"single", "one_connection", "line", "corner", "t", "cross"})
public class IronPlateFamily extends MultiConnectFamily {

	public IronPlateFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
		super(definition, blockBuilder);
		
		BlockUri blockUri = new BlockUri(definition.getUrn());
		
		this.registerBlock(blockUri, definition, blockBuilder, "single", (byte) 0, Rotation.horizontalRotations());
	    this.registerBlock(blockUri, definition, blockBuilder, "one_connection", SideBitFlag.getSide(Side.RIGHT), Rotation.horizontalRotations());
	    this.registerBlock(blockUri, definition, blockBuilder, "line", SideBitFlag.getSides(Side.FRONT, Side.BACK), Rotation.horizontalRotations());
	    this.registerBlock(blockUri, definition, blockBuilder, "corner", SideBitFlag.getSides(Side.LEFT, Side.BACK), Rotation.horizontalRotations());
	    this.registerBlock(blockUri, definition, blockBuilder, "t", SideBitFlag.getSides(Side.LEFT, Side.BACK, Side.RIGHT), Rotation.horizontalRotations());
	    this.registerBlock(blockUri, definition, blockBuilder, "cross", SideBitFlag.getSides(Side.LEFT, Side.FRONT, Side.RIGHT, Side.BACK), Rotation.horizontalRotations());
	}

	@Override
	protected boolean connectionCondition(Vector3i blockLocation, Side connectSide) {
	    Vector3i neighborLocation = new Vector3i(blockLocation);
	    neighborLocation.add(connectSide.getVector3i());
	    if (worldProvider.isBlockRelevant(neighborLocation)) {
	        Block neighborBlock = worldProvider.getBlock(neighborLocation);
	        final BlockFamily blockFamily = neighborBlock.getBlockFamily();
	        if (blockFamily instanceof IronPlateFamily)
	            return true;
	    }
		return false;
	}

	@Override
	public byte getConnectionSides() {
		return SideBitFlag.getSides(Side.FRONT, Side.BACK, Side.LEFT, Side.RIGHT);
	}

	@Override
	public Block getArchetypeBlock() {
		return blocks.get((byte) 0);
	}

}
