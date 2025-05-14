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
		Server.default.waitForBoot {
			{ BaBus.init; }.defer(1);
			// { BaNdefs.init; }.defer(2);
			{ BaBufs.init; }.defer(3)
			{ BaSynthDefs.init; }.defer(4);
			{ M.init; }.defer(5);
		}
	}
	*globalizeBufs { BaBufs.globalizeBufs }
}