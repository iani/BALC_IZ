// 土  3  5 2025 14:01
// Bus specs for BaBus

BaBusSpecs {
	*specs {
		var path;
		path = this.filenameSymbol.asString.folder +/+ "busSpecs.scd";
		if (File.exists(path)) {
			postln("BaBusSpecs returns specs from" + path);
			^path.load;
		}{
			postln("BaBusSpecs did not find path\n" + path);
			"Returning empty spec array".postln;
			^[]
		}
	}
}