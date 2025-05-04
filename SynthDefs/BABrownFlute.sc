// =====================================================================
// SuperCollider Workspace - VA2016
// BASynths version I
// =====================================================================

BABrownFlute {
	classvar <>server;
	var name;
	*new {
		arg n;
		var obj;
		server = Server.local;
		obj = super.new;
		obj.init(n);
		obj.patterns;
		^obj;
	}

	init { | n |
		SynthDef(\flute, {|freq = 440, voices=10, fade = 1, gate = 1, vol= 0.25, amp = 0.5, pan = 0|

			var env, wave;
			env = EnvGen.kr(Env([0, 1, 0], [fade, fade]), gate, doneAction: 2);

			wave = Mix.fill(10,{|i|

				var harmonicnumber = 2*i+1; //odd harmonics only

				SinOsc.ar(freq*harmonicnumber, amp*0.7)*BrownNoise.ar(amp*0.5)/harmonicnumber

			})*vol;
			Out.ar(0, Pan2.ar(wave*env*amp, pan)) //stereo, panned centre
		}).add;
		name = n;
	}

	patterns {
		var cmdPeriodFunc, stop;
		//Pdef
		Pdef(\flutepad).fadeTime_(4);
		fork{
			~metronomos = TempoClock(1); // create a TempoClock
			// schedule an event at next whole beat
			~metronomos.schedAbs(~metronomos.beats.ceil, { arg beat, sec; [beat, sec]; 1 });
			~metronomos.tempo = 2;
			~metronomos.tempo.postln;
			0.1.wait;
			Pdef(\flutepad,
				Pbind(\instrument, \flute,
					\freq, Prand((20..600), inf),
					\dur, 4,
					\fade, 1,
					\pan, 0,
					\amp, 0.0,
					\vol, 0.0
				)).play(~metronomos, quant: 4);
		};
		// set stop on cmd-period
		{if(stop.value, {Pdef(\flutepad).stop;}, {nil})};
		cmdPeriodFunc = { Pdef(\flutepad).stop; };
		CmdPeriod.add(cmdPeriodFunc);
	}
}