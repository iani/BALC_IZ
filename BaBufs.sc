// 土  3  5 2025 10:47
// Alternative scheme for loading and accessing buffers in BALC* libraries

BaBufs {
	classvar all; // dictionary containing all buffers
	classvar <filesPath;
	// Note: If multiple subfolders contain audio files with the same filename,
	// only one filename per buffer (the last file found) will be stored.

	*all { ^all ?? { all = () } }

	*init {
		Server.default doWhenBooted: { this.loadBuffers };
	}

	*loadBuffers {
		postln("=== Loading buffers for" + this + "===");
		filesPath = PathName(Platform.userExtensionDir +/+ "BALC-lib/sounds/");
		// postln("Entries in " + filesPath.fullPath);
		filesPath.entries do: { | p |
			p.fullPath.entriesMatchingWav do: { | w |
				this.all[
					w.fileNameWithoutExtension.split($_)[1..].catList("_").asSymbol;
				] = Buffer.read(Server.default, w)
			};
		};
		"=== BaBufs Loaded buffers:".postln;
		this.all.keys.asArray.sort.postln;
		"==================".postln;
	}
}