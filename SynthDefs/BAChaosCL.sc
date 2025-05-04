// =====================================================================
// SuperCollider Workspace - VA2016
// BASynths version I
// =====================================================================



BAChaosCL{



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
Ndef.all.clear;
	//SynthDef

		SynthDef(\noiseL1, {|out = 0, freq = 220, alpha  = 1, beta = 1.9, xi = 0, amp = 0.1, cutoff = 3000, pan = 0, vol = 0.5, fadein = 1, fadeout = 1, gate = 1, shape = 4|

			var env, source;

			env = EnvGen.kr(Env([0, 1, 0], [shape, shape] ), gate, doneAction:2);
			//env = EnvGen.kr(Env.adsr, gate, doneAction:2);
		source = SinOsc.ar(SinOsc.ar(freq*7, freq*6, freq/2), 0, amp)*Saw.ar(Saw.ar(freq*2, freq/0.5), amp)*WhiteNoise.ar(amp)+Saw.ar(freq/4, amp)*CuspL.ar(freq, alpha, beta, xi, amp)*0.2;
		//	source = CuspL.ar(freq, alpha, beta, xi, amp)*0.2;
			//source = SinOsc.ar(SinOsc.ar(freq*7, freq*6, freq/2), 0, amp)*Saw.kr(Saw.ar(freq*2, freq/0.5), amp)*WhiteNoise.ar(amp);//+Saw.ar(freq/4, amp)*0.2;
        // source = Decay.ar(source, decay, mul: amp).softclip;
        // source = LPF.ar(source, cutoff,  amp).softclip;

      //   source = LPF.ar(source, cutoff, 0.4, amp).softclip;
         Out.ar(out, Pan2.ar(source*env, pan))*vol
         }).add;
~mbusesChaos1 = [	~mbusChaos1 = Bus.audio(server, 2);];
		Ndef(\chaosFilter).put(0, { InFeedback.ar(~mbusChaos1, 2) }).fadeTime_(0.2).play;
		name = n;


		"Pbindef(\\chaos1): args:
|(freq: 22050, alpha: 1, beta: 1.9, xi: 0, amp: 1)|".postln

	}


	////// -  Patterns - //////

	initPattern{

		var cmdPeriodFunc, stop;
		~chaos1 = Pdef(\chaos1).fadeTime_(4);

		fork{

			~metronomos = TempoClock(1); // create a TempoClock

// schedule an event at next whole beat
			~metronomos.schedAbs(~metronomos.beats.ceil, { arg beat, sec; [beat, sec]; 1 });

			~metronomos.tempo = 1;

			~metronomos.tempo.postln;
			"~metronomos->~metronomos.tempo = 1".postln;

	0.1.wait;
//Pdef
~chaos1 = Pdef(\chaos1,


     Pbind(\instrument, \noiseL1,
           //\freq, Prand([4.9, 2.1, 5.4, 2.3], inf),
           //\freq, Pwhite((12050..22050)),

           \dur, 4,
           \pan, 0,
           \amp, 0.0,
	\out, ~mbusChaos1
           )).play(~metronomos, quant: 4);
		};
	// set stop on cmd-period

	{if(stop.value, {Pdef(\chaos1).stop;}, {nil})};

		cmdPeriodFunc = { Pdef(\chaos1).stop; };
CmdPeriod.add(cmdPeriodFunc);


}

}





