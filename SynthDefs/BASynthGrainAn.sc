// =====================================================================
// SuperCollider Workspace - VA2016
// BASynths version I
// =====================================================================

BASynthGrainAn{

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


		//fork{


		

		//setup sample paths and \bf synth
							~samplespathm =  Platform.userExtensionDir +/+ "BALC-lib/sounds/mono/"; // drums
	~bufferGrain = (~samplespathm ++ "*.wav").pathMatch.collect({ |i|  Buffer.read(server, i)});

	~bufferGrain.postln;

		


~winEnvHn = Signal.hanningWindow(1024);
~hanning = Buffer.sendCollection(server, ~winEnvHn, 1);
~winEnvHm = Signal.hammingWindow(1024);
~hamming = Buffer.sendCollection(server, ~winEnvHm, 1);
~winEnvS = Signal.sineFill(1000, 1.0/[1, 2, 3, 4, 5, 6]);
~sine = Buffer.sendCollection(server, ~winEnvS, 1);
~winEnvR = Signal.rectWindow(1024);
~rect = Buffer.sendCollection(server, ~winEnvR, 1);
~winEnvC = Signal.chebyFill(1000, [0.3, -0.8, 1.1]);
~cheby = Buffer.sendCollection(server, ~winEnvC, 1);
~winEnvW = Signal.welchWindow(1024);
~welch = Buffer.sendCollection(server, ~winEnvW, 1);

//~record = Server.local.prepareForRecord("granular01.aif");
		
		SynthDef(\grainAn, { |out = 0, gate = 1, carfreq = 1000, modfreq = 122, ind = 0.5, rate = 1, pos = 0.5, in = 1, bufnum,   trig = 1, dur = 1, amp = 1, fadein = 1, fadeout = 1, sndbuf, envbuf, cutoff = 6000, pan = 0, vol = 0.5, sample, shape|
    var env,index, modulator, zoro, outbus, trigger;


			
		modulator= SinOsc.kr(modfreq,0,10*modfreq*ind, carfreq);
		env = EnvGen.kr(
        Env([0, 1, 0], [shape, shape]),
        gate,
			levelScale: amp, doneAction: 2);
			trigger = LPF.ar(GrainBuf.ar(2, Dust.kr(trig), dur, sndbuf, BufRateScale.kr(sndbuf)*(modulator/440)*rate, pos, 2, pan, envbuf), cutoff);

			
     outbus = Out.ar(out, trigger * env * vol)!2
		}).add;

//using busses example

						//~mbus36 = Bus.audio(server, 2);
		//1.0.wait;

		//	~nodeGr = Synth(\grainAn, [\gate, 1, \bufnum, ~bufferGrain[4], \envbuf, -1, \trig, 12, \dur, 0.02, \carfreq, 200, \modfreq, 2000, \ind, 12, \amp, 0.5 , \vol, 1, \out, ~mnus36]);
						//~nodeGr = Synth(\grainAn, [\gate, 0, \bufnum, ~bufferGrain.bufnum, \envbuf, 0]);
						//1.0.wait;

						//};

		name = n;

			"Pbindef(\\grain): args:
|\\carfreq, \\modfreq, \\ind,
\\cutoff, \\trig, \dur, \\pos, \\rate, \\pan, \\amp|".postln;
		"Envelopes ->\\envbuf, ~hanning, ~hamming, ~rect, ~sine, ~rect, ~cheby, ~welch ".postln;


	}

	////// -  Patterns - //////

	initPattern{

		var cmdPeriodFunc, stop;
		//Pdef
		Pdef.all.asCompileString;

		Pdef(\grain).fadeTime_(4);


	Pdef(\grain,
		Pbind(\instrument,
			\grainAn,
			\sndbuf, ~bufferGrain[0],

				\carfreq, 500,
			\modfreq, 400,
				\ind, 20,
			\trig, 1,
			\dur, 0.5,
				\pos, 0.5,
			\rate, 1,
			\pan, 0,
			\cutoff, 6000,
		 // \freqblow, 1200,
		 //\rq, 0.1,
			\amp, 0.0,
			\vol, 0.00,
			\envbuf, ~sine)).stop;


	// set stop on cmd-period

	{if(stop.value, {Pdef(\grain).stop;}, {nil})};

		cmdPeriodFunc = { Pdef(\grain).stop; };
CmdPeriod.add(cmdPeriodFunc);


	}


	}

