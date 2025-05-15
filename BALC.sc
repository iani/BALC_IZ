// 土  3  5 2025 14:45
// Global setup class.
// Run BALC.init to boot the server and load everything
// Init also runs when the default server boots.
// So you do not have to run init explicitly.

BALC {

	// *initClass { // init when the server boots.
	// 	StartUp add: { ServerBoot add: { this.init } };
	// }
	*init {
		ServerBoot add:
		{
			BaBus.init;
			// { BaNdefs.init; }.defer(2);
			BaBufs.init;
			BaSynthDefs.loadSynthDefs;
			M.init;
		}
	}
	*globalizeBufs { BaBufs.globalizeBufs }
}