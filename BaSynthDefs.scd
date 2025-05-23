// 土  3  5 2025 14:39
// Load SynthDefs for BALC
// Synthdefs found today in BALCI:
// bf, nastyS, soundC, samplerAn, grainAn, spaceklank, flute, klank01,
// sines, fms, soundA noiseL1

BaSynthDefs {
	*init {
		ServerBoot add: { this.loadSynthDefs };
		// Server.default waitForBoot: { this.loadSynthDefs }
	}

	*loadSynthDefs {
		"=== BaSynthDefs loads synthdefs ===".postln;
		this.loadBf;
		Server.default.sync;
		this.loadNastyS;
		Server.default.sync;
		this.loadBrownFlute;
		Server.default.sync;
		this.loadChaosCL;
		Server.default.sync;
		this.loadFmSynth;
		Server.default.sync;
		this.loadNastyPad;
		Server.default.sync;
		this.loadNastySynth;
		Server.default.sync;
		this.loadSamplerLiveAn;
		Server.default.sync;
		this.loadSoundA;
		Server.default.sync;
		this.loadSoundC;
		Server.default.sync;
		this.loadSpacePadA;
		Server.default.sync;
		this.loadSynthGens;
		Server.default.sync;
		this.loadSynthGrainA;
		Server.default.sync;
		this.loadSynthPad;
		Server.default.sync;
		this.loadSynthSines;
		"=== BaSynthDefs finished loading synthdefs ===".postln;
	}

	*loadBf {
		"Loading bf...".post;
		SynthDef(\bf, { | out=0, buf=1023, amp=1.0, pos = 0,
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
		"bf loaded".postln;
	}

	*loadNastyS {
		"Loading nastyS...".post;
		SynthDef(\nastyS, {| out = 0, wacky = 0.5, carfreq = 100, modfreq = 0.4, amp = 0.1, pan = 0, shape = 1 |
			var env, source;

			env = EnvGen.kr(Env([0, 1, 0], [shape, shape]), 1, doneAction: 2);
			source = BANastySynth.ar(wacky, carfreq, modfreq, amp);
			Out.ar(out, Pan2.ar(source*env, pan))
		}).add;
		"nastyS loaded".postln;
	}

	*loadBrownFlute {
		"Loading brownFlute...".post;
		SynthDef(\flute, {| out = 0, freq = 440, voices=10, fade = 1, gate = 1, vol= 0.25, amp = 0.5, pan = 0|
			var env, wave;
			env = EnvGen.kr(Env([0, 1, 0], [fade, fade]), gate, doneAction: 2);
			wave = Mix.fill(10,{|i|
				var harmonicnumber = 2*i+1; //odd harmonics only
				SinOsc.ar(freq*harmonicnumber, amp*0.7)*BrownNoise.ar(amp*0.5)/harmonicnumber
			})*vol;
			Out.ar(out, Pan2.ar(wave*env*amp, pan)) //stereo, panned centre
		}).add;
		"brownFlute loaded".postln;
	}

	*loadChaosCL {
		"Loading chaosCL...".post;
		SynthDef(\noiseL1, {|out = 0, freq = 220, alpha  = 1, beta = 1.9, xi = 0, amp = 0.1, cutoff = 3000, pan = 0, vol = 0.5, fadein = 1, fadeout = 1, gate = 1, shape = 4|
			var env, source;
			env = EnvGen.kr(Env([0, 1, 0], [shape, shape] ), gate, doneAction:2);
			source = SinOsc.ar(SinOsc.ar(freq*7, freq*6, freq/2), 0, amp)*Saw.ar(Saw.ar(freq*2, freq/0.5), amp)*WhiteNoise.ar(amp)+Saw.ar(freq/4, amp)*CuspL.ar(freq, alpha, beta, xi, amp)*0.2;
			Out.ar(out, Pan2.ar(source*env, pan))*vol
		}).add;
		"chaosCL loaded".postln;
	}

	*loadFmSynth {
		"Loading fmSynth...".post;
		"fmSynth loaded".postln;

	}

	*loadNastyPad {
		"Loading nastyPad...".post;
		"nastyPad loaded".postln;

	}

	*loadNastySynth {
		"Loading nastySynth...".post;
		"nastySynth loaded".postln;

	}

	*loadSamplerLiveAn {
		"Loading samplerLiveAn...".post;
		"samplerLiveAn loaded".postln;

	}

	*loadSoundA {
		"Loading soundA...".post;
		"soundA loaded".postln;

	}

	*loadSoundC {
		"Loading soundC...".post;
		"soundC loaded".postln;
	}

	*loadSpacePadA {
		"Loading spacePadA...".post;
		"spacePadA loaded".postln;

	}

	*loadSynthGens {
		"Loading synthGens...".post;
		"synthGens loaded".postln;

	}

	*loadSynthGrainA {
		"Loading synthGrainA...".post;
		SynthDef(\grainAn, {
			| out = 0, gate = 1, carfreq = 1000, modfreq = 122, ind = 0.5, rate = 1, pos = 0.5, in = 1, bufnum, trig = 1, dur = 1, amp = 1, fadein = 1, fadeout = 1, sndbuf, envbuf = 1, cutoff = 6000, pan = 0, vol = 0.5, sample, shape = 1 |

		var env, index, modulator, zoro, outbus, trigger;

		modulator= SinOsc.kr(modfreq,0,10*modfreq*ind, carfreq);
		env = EnvGen.kr(
        Env([0, 1, 0], [shape, shape]),
        gate,
		levelScale: amp, doneAction: 2);
		trigger = LPF.ar(GrainBuf.ar(2, Dust.kr(trig), dur, sndbuf, BufRateScale.kr(sndbuf)*(modulator/440)*rate, pos, 2, pan, envbuf), cutoff);

     outbus = Out.ar(out, trigger * env * vol)!2
		}).add;

		"synthGrainA loaded".postln;

	}

	*loadSynthPad {
		"Loading synthPad...".post;
		"synthPad loaded".postln;

	}

	*loadSynthSines {
		"Loading synthSines...".post;
		SynthDef(\sines, {|outbus = 0, buffoffset = 0, offsetval = 0, gate = 1, freq = 261.63, freq2 = 329.63, freq3 = 415.30, freqdust = 1, amp = 0.4, pan = 0, vol = 0.03, fadeinvl = 0, fadeoutvl = 0, fade = 4, char|

			var env, source;
			env = EnvGen.kr(Env([fadeinvl, 0.8, fadeoutvl], [fade, fade]), gate, doneAction: 2);
			//env = EnvGen.kr(Env.perc, doneAction:2);
			source = SinOsc.ar([freq, freq2, freq3], 0, amp*env)+Saw.ar([freq, freq2, freq3], 0, amp*env)+FSinOsc.ar([freq*2, freq2*2, freq3*2], 0.5, amp*env)+SinOsc.ar(SinOsc.ar(freq*4, freq2*4, freq3*4), 0, amp*env);
			Out.ar(outbus, Pan2.ar(source*env*vol, pan))!2
		}).add;

		"synthSines loaded".postln;

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