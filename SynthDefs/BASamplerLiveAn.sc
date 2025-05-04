// =====================================================================
// SuperCollider Workspace - VA2016
// BASynths version I
// =====================================================================

BASamplerLiveAn{
 
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




  
			// setup sample paths and \bf synth

~samplespath =  Platform.userExtensionDir +/+ "BALC-lib/sounds/stereo/";
	~bufferSampler1 = (~samplespath ++ "*.wav").pathMatch.collect({ |i|  Buffer.read(server, i)});

	~bufferSampler1.postln;

 
//~record = Server.local.prepareForRecord("granular01.aif");
SynthDef(\samplerAn, {| bufnum, chan = 1, gate = 1, cutoff = 4440, rate = 1, freq = 200, trig = 1, start = 0.2, startPos = 0.1, end = 1, pan = 0, vol = 0.5, amp = 0.1, fadein = 1, fadeout = 1|
	
	var env, trigger, sampler, rand;
	
	//	env = EnvGen.kr(Env.adsr, gate, doneAction: 2);
	env = EnvGen.kr(Env([0, 1, 0], [fadein, fadeout]), gate, doneAction: 2);
	trigger = Dust.kr(trig);
	//	env = EnvGen.kr(Env.adsr, gate); 
	//rand = LFNoise0.kr(freq);
	//BufRd.ar(numChannels, bufnum, phase, loop, interpolation)

	//	Phasor*ar(trig, rate, start, end, resetPos)
	sampler = PlayBuf.ar(2, bufnum,
				
		rate * BufRateScale.kr(bufnum), // control the rate of playback,
		trigger,
		startPos * BufFrames.kr(bufnum) // define start point of playback
		
				
			);
			sampler = LPF.ar(sampler, cutoff, amp);
	Out.ar(0, Pan2.ar(sampler*env*amp, pan), 0, 1)*vol;
	}).add;





		name = n;

		"Pbindef(\\samplerLiveAn): args: 
|\\gate = 1, \\cutoff = 4440, \\rate = 1, \\freq = 200, \\trig = 1,\\startPos = 0.1, \\pan = 0, \\vol = 0.5, \\amp = 0.1, \\fadein = 1, \\fadeout = 1, \\bufnum, ~bufferSampler1[5]|".postln
	}
	
	////// -  Patterns - //////

	initPattern{

		var cmdPeriodFunc, stop;
//Pdef
		Pdef(\samplerLiveAn).fadeTime_(4);

			~metronomos = TempoClock(1); // create a TempoClock

// schedule an event at next whole beat
		~ metronomos.schedAbs(~t.beats.ceil, { arg beat, sec; [beat, sec]; 1 });

			~metronomos.tempo = 2;

			~metronomos.tempo.postln;
		"metronomos ->  = {metronomos}".postln;

Pdef(\samplerLiveAn,


     Pbind(\instrument, \samplerAn,  
		 \bufnum, ~bufferSampler1[6],          
           \rate, 1,
		  \trig, 1,
		 \startPos, 0.1,
		 \cutoff, 4000,
           \pan, 0,
           \amp, 0.4
	 ));
		//(~t, quant: 4);

	// set stop on cmd-period

{if(stop.value, {Pdef(\samplerLiveAn).stop;}, {nil})};

		cmdPeriodFunc = { Pdef(\samplerLiveAn).stop;
			~bufferSampler1.free.postln;
		};
		 
CmdPeriod.add(cmdPeriodFunc);

	}


}


