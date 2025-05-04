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
			BaBus.init;
			BaNdefs.init;
			BaBufs.init;
			BaSynthDefs.init;
			Metro.init;
		}
	}
}