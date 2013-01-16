package colorwater;

import net.minecraft.item.Item;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class ForgeEventHandler {

	@ForgeSubscribe
	public void handleFillBucketEvent(FillBucketEvent event)
	{
		if(Item.bucketEmpty.itemID == event.current.itemID){
			if(event.target.typeOfHit == EnumMovingObjectType.TILE){
				int blockID = event.world.getBlockId(event.target.blockX, event.target.blockY, event.target.blockZ);
				int blockMeta = event.world.getBlockMetadata(event.target.blockX, event.target.blockY, event.target.blockZ);
				if(blockID == ModConfiguration.idBlockGel){
					event.result = ModItems.bucketGelSub[blockMeta].getItemStack(1);
					event.setResult(Result.ALLOW);
					event.world.setBlockWithNotify(event.target.blockX, event.target.blockY, event.target.blockZ, 0);
				}
			}
		}
	}
}
