package rtg.world.biome.realistic.enhancedbiomes;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAspenHills;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAspenHills;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBAspenHills extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{EnhancedBiomesBlocks.stoneEB, EnhancedBiomesBlocks.stoneEB};
    public static byte[] ebDominantStoneMeta = new byte[]{EBAPI.CHERT, EBAPI.LIMESTONE};
    public static Block[] ebDominantCobblestoneBlock = new Block[]{EnhancedBiomesBlocks.stoneCobbleEB, EnhancedBiomesBlocks.stoneCobbleEB};
    public static byte[] ebDominantCobblestoneMeta = new byte[]{EBAPI.CHERT, EBAPI.LIMESTONE};
    
	public RealisticBiomeEBAspenHills(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBAspenHills(230f, 120f, 0f),
			new SurfaceEBAspenHills(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Aspen Hills");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBAspenHills;
		this.generateVillages = ConfigEB.villageEBAspenHills;
        
    }
}
