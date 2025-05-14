\test play: { WhiteNoise.ar(0.1).dup };
\test play: { SinOsc.ar(200, 0, 0.1).dup };
\test play: ();

~test;
~test.stop;

~test = nil;