// =====================================================================
// SuperCollider Workspace - VA2016
// BASynths version I
// =====================================================================

BANastyPad{



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

	//SynthDef

SynthDef(\nastyS, {|wacky = 0.5, carfreq = 100, modfreq = 0.4, amp = 0.1, pan = 0, shape = 1|
	var env, source;

	env = EnvGen.kr(Env([0, 1, 0], [shape, shape]), 1, doneAction: 2);
	source = BANastySynth.ar(wacky, carfreq, modfreq, amp);
	Out.ar(0, Pan2.ar(source*env, pan))
}).add;

		name = n;


		"Pbindef(\\nasty,\\wacky, \\carfreq,
\\modfreq, \\amp)".postln

	}


	////// -  Patterns - //////

	initPattern{

		var cmdPeriodFunc, stop;
//Pdef
		Pdef(\nasty).fadeTime_(4);
fork{
		~metronomos = TempoClock(1); // create a TempoClock

// schedule an event at next whole beat
	//~t.schedAbs(~t.beats.ceil, { arg beat, sec; [beat, sec]; 1 });

			~metronomos.tempo = 2;

			~metronomos.tempo.postln;
		"~metronomos -> ~metronomos.tempo = 2".postln;
	0.1.wait;
Pdef(\nasty, Pbind(\instrument, \nastyS,
	\wacky, Pseq([0.01, 0.002, 0.3, 0.004, 1, 3], inf),
	\carfreq, Pseq([10, 2200, 300, 7000, 4000], inf),
	\modfreq, Pseq([0.01, 0.02, 0.005, 0.74, 0.02], inf),
	\pan, Pseq([-1, 0, 1, 0.1, 0.7, 0.3, 0.5], inf),
	\amp,0.0// Pseq([0.1, 0.04, 0.02, 0.03], inf)

)).play(~metronomos, quant: 4)
};
	// set stop on cmd-period

	{if(stop.value, {Pdef(\nasty).stop;}, {nil})};

		cmdPeriodFunc = { Pdef(\nasty).stop; };
CmdPeriod.add(cmdPeriodFunc);


}

}
