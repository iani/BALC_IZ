// =====================================================================
// SuperCollider Workspace - VA2016
// BASynths version I
// =====================================================================


BAFmSynth{

 	classvar <>server;
	var name;
//classvar <dictNames;


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
	Ndef.all.clear;
SynthDef(\fms, {|out = 0, gate = 1, vol = 0.0001, shape,  carfreq = 440, modfreq = 440, ind = 444, freq = 440, decay = 0.02, cutoff = 2000, amp = 0.001, trig = 1, pan = 0|
         var env, source, filter;
         env = EnvGen.kr(Env([0, 1, 0], [shape, shape]), gate, doneAction: 2);
         source = SinOsc.ar(SinOsc.ar(carfreq, modfreq, ind)+freq, 0, amp)*PinkNoise.ar(amp);

         source = LPF.ar(source, cutoff, amp).softclip;
         Out.ar(out, Pan2.ar(source*env, pan))
         }).add;
/*
		dictNames = IdentityDictionary[
			\pfm->Pdef(\fm),
			\nfm->Ndef(\fmFilter)
		];
	//	dictNames.freeze;
		*/
~mbusesFm1 = [
		~mbusFm1 = Bus.audio(server, 2);
		];
		~ndfFm = Ndef(\fmFilter).put(0, { InFeedback.ar(~mbusFm1, 2) }).fadeTime_(0.2).play;
		name = n;


		"Pbindef(\\fm): args:
|\\freq = 440, \\decay = 0.02,
\\cutoff = 2000, \\amp = 0.01,
\\carfreq = 440,
\\modfreq = 440,
\\ind = 444,
\\shape, 2|".postln

	}


	////// -  Patterns - //////

	initPattern{

		var cmdPeriodFunc, stop;
//Pdef
~fm = Pdef(\fm).fadeTime_(4);
		~metronomos = TempoClock(1); // create a TempoClock

// schedule an event at next whole beat
			~metronomos.schedAbs(~metronomos.beats.ceil, { arg beat, sec; [beat, sec]; 1 });

			~metronomos.tempo = 1;

			~metronomos.tempo.postln;
			"~metronomos->~metronomos.tempo = 1".postln;
~fm = Pdef(\fm,


     Pbind(\instrument, \fms,
           
           \carfreq, Pwhite((2.0..6.2)),
			\shape, Pseq([4, 2.5, 4, 3.3], inf),
           \dur, 4,
           \pan, 0,
           \amp, 0.0,
		 \out, ~mbusFm1
		)).play(~metronomos, quant:4);

	// set stop on cmd-period

	{if(stop.value, {Pdef(\fm).stop;}, {nil})};

		cmdPeriodFunc = { Pdef(\fm).stop; };
CmdPeriod.add(cmdPeriodFunc);


}

}
