// =====================================================================
// SuperCollider Workspace - VA2016
// BASynths version I
// =====================================================================

BASpacePadA{

	classvar <>server;
		var name;



	// Constructor

	*new{
		arg n;
		var obj;


		server = Server.local;
		obj = super.new;

		obj.init(n);

		obj.initPattern;

		^obj;
	}

	init{ arg n;

		SynthDef(\spaceklank, {|outbus = 0, gate = 1, freq = 261.63, freq1 = 261.63, freq3 = 329.63, freq5aug = 415.30,
	freqs = #[freq1, freq3, freq5aug], rings = #[0.1, 0.3, 0.4, 0.6, 1, 0.7, 0.02], freqres = 329.63, bwr = 0.7, trigger = 1, amp = 0.4, pan = 0, vol = 0.5, shape = 1|

	var env, source, rez, cutoff;



	env = EnvGen.kr(Env([0, 1, 0], [shape, shape]), gate, doneAction: 2);

	source =
	Klank.ar(`[freqs, nil, rings
 ], Decay.ar(SinOsc.ar([freq, freq*0.5], 0, amp*0.09), 0.03, LFSaw.ar(freq, 0.5, 0.009))*PinkNoise.ar(amp));

		rez = Resonz.ar(source, freqres, bwr, amp);
		Out.ar(outbus, Pan2.ar(rez*env*vol, pan))
}).add;

		name = n;


		"Pbindef(\\spacepad): args:
|\\freq, \\freq1, \\freq3, \\freq5aug, \\bwr, \\freqres,
 \\amp, \\pan
|".postln

	}


	////// -  Patterns - //////

	initPattern{

		var cmdPeriodFunc, stop;
//Pdef
		Pdef(\spacepad).fadeTime_(4);
fork{
		~metronomos = TempoClock(1); // create a TempoClock

// schedule an event at next whole beat
	~metronomos.schedAbs(~metronomos.beats.ceil, { arg beat, sec; [beat, sec]; 1 });

			~metronomos.tempo = 2;

			~metronomos.tempo.postln;
	"~metronomos -> ~metronomos.tempo = {~metronomos.tempo}".postln;
	0.1.wait;
Pdef(\spacepad,


     Pbind(\instrument, \spaceklank,
           //\freq, Prand([4.9, 2.1, 5.4, 2.3], inf),
		 \freq, Pwhite((2.0..6.2)),
		 \freq1, Pwhite((2.0..6.2)),
		 \freq2, Pwhite((2.0..6.2)),
	 	 \freq3, Pwhite((2.0..6.2)),
		 \freq5aug, Pwhite((2.0..6.2)),
           \dur, 4,
           \pan, 0,
           \amp, 0.0
	 )).play(~metronomos, quant: 4);
};
	// set stop on cmd-period

	{if(stop.value, {Pdef(\spacepad).stop;}, {nil})};

		cmdPeriodFunc = { Pdef(\spacepad).stop; };
CmdPeriod.add(cmdPeriodFunc);


}
}