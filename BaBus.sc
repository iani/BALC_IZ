// 土  3  5 2025 11:38
// Allocate buses from specs by name
// Access allocated buses by name.
// Buses are created right after the Local Server boots
// The specs should be set only once, before the Server boots.
//
// Specs are of the form:
// \busName
// (Which defaults to:) Bus.audio(Server.default, 2);
// or:
// [\busName, <rate>, <numChannels>]
// where <rate> is \control or \audio and <numChannels> defaults to 2

BaBus {
	classvar all;
	classvar specs;

	*all { ^all ?? { all = () } }
	*specs { ^specs ?? { specs = () } }
	*loadSpecs { | argSpecs | this.addSpecs(argSpecs).load; }

	*init {
		this addSpecs: BaBusSpecs.specs;
		Server.default doWhenBooted: { this.load };
	}

	*addSpecs { | argSpecs |
		argSpecs do: { | s | this.addSpec(s) };
	}

	*addSpec { | argSpec |
		var name, rate, numChans;
		#name, rate, numChans = argSpec.asArray;
		this.specs[name] = [
			name, rate ? \audio, numChans ? 2
		];
	}

	*load {
		var name, rate, numChans;
		this.all; // create event if needed;
		"=== BaBus allocates buses ===".postln;
		specs do: { | spec |
			#name, rate, numChans = spec.asArray;
			all[name] = Bus.perform(
				rate ? \audio,
				Server.default,
				numChans ? 2
			);
		};
		postln("BaBus made followig buses:" +
			all.keys.asArray.sort;
		)
	}


}