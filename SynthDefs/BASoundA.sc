// =====================================================================
// SuperCollider Workspace - VA2016
// BASynths version I
// =====================================================================

BASoundA{

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
		fork{
	//SynthDef
							~samplespath = Platform.userExtensionDir +/+ "BALC-lib/sounds/stereo/"; // drums
	~bufferSoundA = (~samplespath ++ "*.wav").pathMatch.collect({ |i|  Buffer.read(server, i)});

	~bufferSoundA.postln;
1.wait;

//~record = Server.local.prepareForRecord("granular01.aif");
SynthDef(\soundA, {| bufnum, chan = 1, gate = 1, cutoff = 4440, rate = 1, freq = 200, trigger = 0, start = 0.2, pos = 0.1, end = 1, pan = 0, vol = 0.5, amp = 0.9, fade = 0, fadeout = 0, durin = 2, durout = 2, loopy = 0|

	var sampler, env;


	env = EnvGen.kr(Env([0, 1, 0], [fade, fade]), gate, doneAction:2);
	//env = EnvGen.kr(Env.adsr, gate, doneAction:2);
	sampler = PlayBuf.ar(2, bufnum,

	rate*BufRateScale.kr(bufnum), trigger, startPos:BufFrames.kr(bufnum)*pos, doneAction:2, loop:0);

	//sampler = LPF.ar(sampler, cutoff, amp);
	Out.ar(0, Pan2.ar(sampler*env*amp, pan))!2
}).add;//send(server, [\bufnum, ~bufferSound1[5]]);

0.5.wait;
			//~soundA = Synth(\soundA, \gate, 0);
			//Synth(\soundA, [\bufnum, ~bufferSound1]);
		name = n;
		};
		"Pbindef(\\monsterA): args:
|\\rate,\\pos, \\amp, \\bufnum, ~bufferSoundA[6]|".postln
	}

// 	////// -  Patterns - //////

 	initPattern{

 		var cmdPeriodFunc, stop;
//Pdef
		Pdef(\monsterA).fadeTime_(4);


Pdef(\monsterA,


     Pbind(\instrument, \soundA,
		 \bufnum, ~bufferSoundA,
         \rate, Pseq([1], 1),
		 \pos, Pseq([0.5], 1),
           \pan, Pseq([0], 1),
           \amp, Pseq([0.4], 1)
	 ));





		//(~t, quant: 4);

	// set stop on cmd-period

		{if(stop.value, {Pdef(\monsterA).stop;}, {nil})};
		
		cmdPeriodFunc = { Pdef(\monsterA).stop;
			~bufferSound1.free.postln;
		};

CmdPeriod.add(cmdPeriodFunc);

	}


}


