// 土  3  5 2025 11:35
// Access a buffer loaded by BaBufs by name.

+ Symbol {
	babuf {
		var buf;
		buf = BaBufs.all[this];
		buf ?? { postln("No buffer named" + this + "was found"); };
		^buf;
	}
	babus {
		var bus;
		bus = BaBus.all[this];
		bus ?? { postln("No bus named" + this + "was found"); };
		^bus;
	}
}