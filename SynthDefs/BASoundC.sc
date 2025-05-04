// =====================================================================
// SuperCollider Workspace - VA2016
// BASynths version I
// =====================================================================
 
BASoundC{

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
~bufferSoundC = (~samplespath ++ "*.wav").pathMatch.collect({ |i|  Buffer.read(server, i)});

~bufferSoundC.postln;
1.wait;
SynthDef(\soundC, {| out = 0, pos = 0, amp= 0.1, gate = 1, loop=0, bufnum, rate = 1, fade = 1, pan = 0 |
    var env, source;
//env = EnvGen.kr(Env.adsr, gate, doneAction:2);
//env = EnvGen.kr(Env([0, 1, 0], [fade, fade]), gate, doneAction: 2);

//rate = LFNoise2.kr(XLine.kr(1, 20, 60), 2);
source = PlayBuf.ar(2, bufnum, BufRateScale.kr(bufnum) * rate, 1, pos, loop, doneAction:2);
 //source = PlayBuf.ar(1,bufnum, BufRateScale.kr(bufnum), loop:1);
//Out.ar(out, Pan2.ar(source*env*amp, pan))
Out.ar(out, Pan2.ar(source*amp, pan))


}).add;
//(s, [\out, 0, \bufnum, b]);
0.5.wait;
//~soundA = Synth(\soundA, \gate, 0);
//Synth(\soundA, [\bufnum, ~bufferSound1]);
name = n;
};
"Pbindef(\\monsterC): args:
|\\rate,\\pos, \\amp, \\bufnum, ~bufferSound1[6]|".postln
}

// ////// -  Patterns - //////

  initPattern{

  var cmdPeriodFunc, stop;
//Pdef
Pdef(\monsterC).fadeTime_(4);


Pdef(\monsterC,


     Pbind(\instrument, \soundC,
\bufnum, ~bufferSoundC,
         \rate, Pseq([1], 1),
\pos, Pseq([0.5], 1),
           \pan, Pseq([0], 1),
\loop, 0,
           \amp, Pseq([0.4], 1)
));





//(~t, quant: 4);

// set start button to zero upon a cmd-period

{if(stop.value, {Pdef(\monsterC).stop;}, {nil})};
//{if(stop.value, {~nodeSoundA = Synth(\soundA, [\gate, 0, \rate, 0]);}, {nil})};

cmdPeriodFunc = { Pdef(\monsterC).stop;
~bufferSound1.free.postln;
};

CmdPeriod.add(cmdPeriodFunc);

}


}

