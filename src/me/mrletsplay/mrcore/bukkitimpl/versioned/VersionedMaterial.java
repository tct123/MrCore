package me.mrletsplay.mrcore.bukkitimpl.versioned;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum VersionedMaterial {

	WHITE_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("WHITE_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	ORANGE_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 1), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("ORANGE_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	MAGENTA_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 2), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MAGENTA_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	LIGHT_BLUE_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 3), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("LIGHT_BLUE_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	YELLOW_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 4), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("YELLOW_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	LIME_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 5), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("LIME_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	PINK_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 6), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("PINK_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	GRAY_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 7), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("GRAY_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	LIGHT_GRAY_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 8), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("LIGHT_GRAY_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	CYAN_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 9), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("CYAN_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	PURPLE_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 10), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("PURPLE_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	BLUE_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 11), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("BLUE_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	BROWN_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 12), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("BROWN_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	GREEN_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 13), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("GREEN_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	RED_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 14), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("RED_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	BLACK_STAINED_CLAY(
			of(new MaterialDefinition("STAINED_CLAY", 15), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("BLACK_TERRACOTTA", 0), NMSVersion.V1_13_R1)
		),
	BLACK_BANNER(
			new ColoredDefinition(VersionedDyeColor.BLACK),
			of(new MaterialDefinition("BANNER"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("WHITE_BANNER", 0), NMSVersion.V1_13_R1)
		),
	RED_BANNER(
			new ColoredDefinition(VersionedDyeColor.RED),
			of(new MaterialDefinition("BANNER", 1), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("RED_BANNER", 0), NMSVersion.V1_13_R1)
		),
	GREEN_BANNER(
			new ColoredDefinition(VersionedDyeColor.GREEN),
			of(new MaterialDefinition("BANNER", 2), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("GREEN_BANNER", 0), NMSVersion.V1_13_R1)
		),
	BROWN_BANNER(
			new ColoredDefinition(VersionedDyeColor.BROWN),
			of(new MaterialDefinition("BANNER", 3), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("BROWN_BANNER", 0), NMSVersion.V1_13_R1)
		),
	BLUE_BANNER(
			new ColoredDefinition(VersionedDyeColor.BLUE),
			of(new MaterialDefinition("BANNER", 4), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("BLUE_BANNER", 0), NMSVersion.V1_13_R1)
		),
	PURPLE_BANNER(
			new ColoredDefinition(VersionedDyeColor.PURPLE),
			of(new MaterialDefinition("BANNER", 5), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("PURPLE_BANNER", 0), NMSVersion.V1_13_R1)
		),
	CYAN_BANNER(
			new ColoredDefinition(VersionedDyeColor.CYAN),
			of(new MaterialDefinition("BANNER", 6), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("CYAN_BANNER", 0), NMSVersion.V1_13_R1)
		),
	LIGHT_GRAY_BANNER(
			new ColoredDefinition(VersionedDyeColor.LIGHT_GRAY),
			of(new MaterialDefinition("BANNER", 7), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("LIGHT_GRAY_BANNER", 0), NMSVersion.V1_13_R1)
		),
	GRAY_BANNER(
			new ColoredDefinition(VersionedDyeColor.GRAY),
			of(new MaterialDefinition("BANNER", 8), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("GRAY_BANNER", 0), NMSVersion.V1_13_R1)
		),
	PINK_BANNER(
			new ColoredDefinition(VersionedDyeColor.PINK),
			of(new MaterialDefinition("BANNER", 9), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("PINK_BANNER", 0), NMSVersion.V1_13_R1)
		),
	LIME_BANNER(
			new ColoredDefinition(VersionedDyeColor.LIME),
			of(new MaterialDefinition("BANNER", 10), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("LIME_BANNER", 0), NMSVersion.V1_13_R1)
		),
	YELLOW_BANNER(
			new ColoredDefinition(VersionedDyeColor.YELLOW),
			of(new MaterialDefinition("BANNER", 11), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("YELLOW_BANNER", 0), NMSVersion.V1_13_R1)
		),
	LIGHT_BLUE_BANNER(
			new ColoredDefinition(VersionedDyeColor.LIGHT_BLUE),
			of(new MaterialDefinition("BANNER", 12), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("LIGHT_BLUE_BANNER", 0), NMSVersion.V1_13_R1)
		),
	MAGENTA_BANNER(
			new ColoredDefinition(VersionedDyeColor.MAGENTA),
			of(new MaterialDefinition("BANNER", 13), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MAGENTA_BANNER", 0), NMSVersion.V1_13_R1)
		),
	ORANGE_BANNER(
			new ColoredDefinition(VersionedDyeColor.ORANGE),
			of(new MaterialDefinition("BANNER", 14), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("ORANGE_BANNER", 0), NMSVersion.V1_13_R1)
		),
	WHITE_BANNER(
			new ColoredDefinition(VersionedDyeColor.WHITE),
			of(new MaterialDefinition("BANNER", 15), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("WHITE_BANNER", 0), NMSVersion.V1_13_R1)
		),
	WHITE_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.WHITE),
			of(new MaterialDefinition("STAINED_GLASS_PANE"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("WHITE_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	ORANGE_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.ORANGE),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 1), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("ORANGE_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	MAGENTA_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.MAGENTA),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 2), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MAGENTA_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	LIGHT_BLUE_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.LIGHT_BLUE),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 3), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("LIGHT_BLUE_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	YELLOW_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.YELLOW),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 4), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("YELLOW_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	LIME_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.LIME),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 5), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("LIME_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	PINK_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.PINK),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 6), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("PINK_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	GRAY_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.GRAY),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 7), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("GRAY_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	LIGHT_GRAY_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.LIGHT_GRAY),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 8), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("LIGHT_GRAY_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	CYAN_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.CYAN),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 9), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("CYAN_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	PURPLE_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.PURPLE),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 10), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("PURPLE_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	BLUE_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.BLUE),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 11), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("BLUE_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	BROWN_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.BROWN),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 12), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("BROWN_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	GREEN_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.GREEN),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 13), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("GREEN_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	RED_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.RED),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 14), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("RED_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	BLACK_STAINED_GLASS_PANE(
			new ColoredDefinition(VersionedDyeColor.BLACK),
			of(new MaterialDefinition("STAINED_GLASS_PANE", 15), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("BLACK_STAINED_GLASS_PANE", 0), NMSVersion.V1_13_R1)
		),
	WHITE_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.WHITE),
			of(new MaterialDefinition("STAINED_GLASS"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("WHITE_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	ORANGE_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.ORANGE),
			of(new MaterialDefinition("STAINED_GLASS", 1), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("ORANGE_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	MAGENTA_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.MAGENTA),
			of(new MaterialDefinition("STAINED_GLASS", 2), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MAGENTA_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	LIGHT_BLUE_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.LIGHT_BLUE),
			of(new MaterialDefinition("STAINED_GLASS", 3), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("LIGHT_BLUE_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	YELLOW_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.YELLOW),
			of(new MaterialDefinition("STAINED_GLASS", 4), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("YELLOW_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	LIME_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.LIME),
			of(new MaterialDefinition("STAINED_GLASS", 5), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("LIME_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	PINK_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.PINK),
			of(new MaterialDefinition("STAINED_GLASS", 6), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("PINK_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	GRAY_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.GRAY),
			of(new MaterialDefinition("STAINED_GLASS", 7), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("GRAY_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	LIGHT_GRAY_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.LIGHT_GRAY),
			of(new MaterialDefinition("STAINED_GLASS", 8), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("LIGHT_GRAY_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	CYAN_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.CYAN),
			of(new MaterialDefinition("STAINED_GLASS", 9), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("CYAN_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	PURPLE_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.PURPLE),
			of(new MaterialDefinition("STAINED_GLASS", 10), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("PURPLE_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	BLUE_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.BLUE),
			of(new MaterialDefinition("STAINED_GLASS", 11), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("BLUE_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	BROWN_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.BROWN),
			of(new MaterialDefinition("STAINED_GLASS", 12), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("BROWN_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	GREEN_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.GREEN),
			of(new MaterialDefinition("STAINED_GLASS", 13), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("GREEN_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	RED_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.RED),
			of(new MaterialDefinition("STAINED_GLASS", 14), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("RED_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	BLACK_STAINED_GLASS(
			new ColoredDefinition(VersionedDyeColor.BLACK),
			of(new MaterialDefinition("STAINED_GLASS", 15), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("BLACK_STAINED_GLASS", 0), NMSVersion.V1_13_R1)
		),
	MUSIC_DISC_13(
			of(new MaterialDefinition("GOLD_RECORD"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MUSIC_DISC_13"), NMSVersion.V1_13_R1)
		),
	MUSIC_DISC_CAT(
			of(new MaterialDefinition("GREEN_RECORD"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MUSIC_DISC_CAT"), NMSVersion.V1_13_R1)
		),
	MUSIC_DISC_BLOCKS(
			of(new MaterialDefinition("RECORD_3"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MUSIC_DISC_BLOCKS"), NMSVersion.V1_13_R1)
		),
	MUSIC_DISC_CHIRP(
			of(new MaterialDefinition("RECORD_4"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MUSIC_DISC_CHIRP"), NMSVersion.V1_13_R1)
		),
	MUSIC_DISC_FAR(
			of(new MaterialDefinition("RECORD_5"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MUSIC_DISC_FAR"), NMSVersion.V1_13_R1)
		),
	MUSIC_DISC_MALL(
			of(new MaterialDefinition("RECORD_6"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MUSIC_DISC_MALL"), NMSVersion.V1_13_R1)
		),
	MUSIC_DISC_MELLOHI(
			of(new MaterialDefinition("RECORD_7"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MUSIC_DISC_MELLOHI"), NMSVersion.V1_13_R1)
		),
	MUSIC_DISC_STAL(
			of(new MaterialDefinition("RECORD_8"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MUSIC_DISC_13"), NMSVersion.V1_13_R1)
		),
	MUSIC_DISC_STRAD(
			of(new MaterialDefinition("RECORD_9"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MUSIC_DISC_STRAD"), NMSVersion.V1_13_R1)
		),
	MUSIC_DISC_WARD(
			of(new MaterialDefinition("RECORD_10"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MUSIC_DISC_WARD"), NMSVersion.V1_13_R1)
		),
	MUSIC_DISC_11(
			of(new MaterialDefinition("RECORD_11"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MUSIC_DISC_11"), NMSVersion.V1_13_R1)
		),
	MUSIC_DISC_WAIT(
			of(new MaterialDefinition("RECORD_12"), NMSVersion.v1_8to1_12()),
			of(new MaterialDefinition("MUSIC_DISC_WAIT"), NMSVersion.V1_13_R1)
		),
	;
	
	private TypeDefinition typeDefinition;
	private Map<NMSVersion, MaterialDefinition> definitions;
	
	@SafeVarargs
	private VersionedMaterial(Map.Entry<NMSVersion, MaterialDefinition>[]... defs) {
		this.definitions = new HashMap<>();
		for(Map.Entry<NMSVersion, MaterialDefinition>[] d : defs) {
			for(Map.Entry<NMSVersion, MaterialDefinition> d2 : d) {
				this.definitions.put(d2.getKey(), d2.getValue());
			}
		}
	}
	
	@SafeVarargs
	private VersionedMaterial(TypeDefinition typeDefinition, Map.Entry<NMSVersion, MaterialDefinition>[]... defs) {
		this.typeDefinition = typeDefinition;
		this.definitions = new HashMap<>();
		for(Map.Entry<NMSVersion, MaterialDefinition>[] d : defs) {
			for(Map.Entry<NMSVersion, MaterialDefinition> d2 : d) {
				this.definitions.put(d2.getKey(), d2.getValue());
			}
		}
	}
	
	public TypeDefinition getTypeDefinition() {
		return typeDefinition;
	}
	
	public MaterialDefinition getMaterialDefinition(NMSVersion version) {
		return definitions.get(version);
	}
	
	public MaterialDefinition getCurrentMaterialDefinition() {
		return getMaterialDefinition(NMSVersion.getCurrentServerVersion());
	}
	
	@SuppressWarnings("unchecked")
	private static Map.Entry<NMSVersion, MaterialDefinition>[] of(MaterialDefinition def, NMSVersion... versions) {
		return Arrays.stream(versions)
				.map(v -> new AbstractMap.SimpleEntry<>(v, def))
				.toArray(Map.Entry[]::new);
	}
	
	public static VersionedMaterial getBanner(VersionedDyeColor bannerColor) {
		return Arrays.stream(values())
				.filter(m ->
						m.getTypeDefinition() != null &&
						m.getTypeDefinition().asColored() != null &&
						m.getTypeDefinition().asColored().getDyeColor().equals(bannerColor)
					)
				.findFirst().orElse(null);
	}
	
	public static VersionedMaterial getGlassPane(VersionedDyeColor bannerColor) {
		return Arrays.stream(values())
				.filter(m ->
						m.getTypeDefinition() != null &&
						m.getTypeDefinition().asColored() != null &&
						m.getTypeDefinition().asColored().getDyeColor().equals(bannerColor)
					)
				.findFirst().orElse(null);
	}
	
}
