// 土  3  5 2025 14:39
// Load SynthDefs for BALC
// Synthdefs found today in BALCI:
// bf, nastyS, soundC, samplerAn, grainAn, spaceklank, flute, klank01,
// sines, fms, soundA noiseL1

BaSynthDefs {
	*init {
		Server.default doWhenBooted: { this.loadSynthDefs }
	}

	*loadSynthDefs {
		"=== BaSynthDefs loads synthdefs ===".postln;
		SynthDef(\bf, {|out=0, buf=1023, amp=1.0, pos = 0,
			freq=261.6255653006, cutoff = 16000, pan = 0, shape= 0.1, char |
			var sig, env, comp;

			env = EnvGen.kr(Env.perc(shape, 0.1), 1, doneAction: 2);
			sig = PlayBuf.ar(
				1, buf, BufRateScale.kr(buf)
				* freq/60.midicps, 1, pos, doneAction:2);

			sig  = LPF.ar(sig, cutoff, amp);
			comp =  Compander.ar(sig, sig,
				thresh: 0.2,
				slopeBelow: 1,
				slopeAbove: 0.5,
				clampTime:  0.01,
				relaxTime:  0.01
			);
			Out.ar(out, Pan2.ar(comp * env * amp, pan))
		}).add;
		"=== BaSynthDefs finished loading synthdefs ===".postln;
	}

	*altSynthDefs { // debugging tests
		SynthDef(\bf2, {|out=0, buf=1023, amp=0.1, pos = 0,
			freq=261.6255653006, cutoff = 16000, pan = 0, shape= 0.1, char |
			var sig, env, comp;

			env = EnvGen.kr(Env.perc(shape, 0.1), 1, doneAction: 2);
			sig = PlayBuf.ar(
				1, // numchannels
				buf, // buffer
				BufRateScale.kr(buf) * freq / 60.midicps, // rate scaled by middle-c
				1, // trigger: start immediately
				pos, // start position (0 = beginning of buffer)
				doneAction:2 // free synth when done
			);
			sig  = LPF.ar(sig, cutoff, amp);
			comp =  Compander.ar(sig, sig,
				thresh: 0.2,
				slopeBelow: 1,
				slopeAbove: 0.5,
				clampTime:  0.01,
				relaxTime:  0.01
			);
			Out.ar(out, Pan2.ar(comp * env * amp, pan));
		}).add;
	}

}