// 土  3  5 2025 17:01
// Container for the metronome

M {
	classvar <>metronomos;
	classvar <all; // dictionary holding other tempo clocks

	*initClass {
		StartUp add: { this.init }
	}

	*init {
		all = ();
		metronomos = TempoClock(1).permanent_(true);
		all[\default] = metronomos;
		all[\metronomos] = metronomos;
	}

	*m { ^metronomos }

	*put { | name = \default, clock |
		all[name] = clock ?? { TempoClock(1) };
	}

	*doesNotUnderstand { | message | ^this.get(message); }

	*get { | name = \default |
		var result;
		result = all[name];
		result ?? { result = TempoClock(1); all[name] = result };
		^result;
	}

	*sync { this.push }
	*push {
		currentEnvironment[\clock] = metronomos;
		currentEnvironment[\quant] = 1;
	}
	*async {
		currentEnvironment[\clock] = nil;
		currentEnvironment[\quant] = nil;
	}
}